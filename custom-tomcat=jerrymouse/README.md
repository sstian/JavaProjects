# Jerrymouse
> 手写Tomcat
> not run!

![Build Status](https://github.com/michaelliao/jerrymouse/actions/workflows/maven.yml/badge.svg)

![logo](logo.png)

# Overview

Jerrymouse is a lightweight servlet container that implements the Jakarta Servlet 6 specification. It serves as a minimalist version of Apache Tomcat, focusing on core servlet functionality while maintaining a straightforward architecture that's easy to understand.

[Download](download)

# Purpose and Scope

Jerrymouse demonstrates the essential concepts of a servlet container in a simplified manner, making it valuable for educational purposes and for understanding how servlet containers work. It implements the core parts of the Jakarta Servlet 6 specification while intentionally excluding more complex features to maintain clarity.

# Key Features and Limitations

Jerrymouse implements a focused subset of the Jakarta Servlet 6 standard:

- Servlet support;
- Filter support;
- Listener support;
- HttpSession support (Cookie-mode only).

Unsupported features:

- Not support JSP;
- Not support async;
- Not support WebSocket.

Additionally, Jerrymouse has the following deployment constraints:

- Supports deploying a single standard web application;
- Does not support multiple web application deployment;
- Does not support hot reloading of applications.

---
Jerrymouse Server设计目标如下：
- 支持Servlet 6的大部分功能：
- 支持Servlet组件；
- 支持Filter组件；
- 支持Listener组件；
- 支持Sesssion（仅限Cookie模式）；
- 不支持JSP；
- 不支持async模式与WebSocket；
- 可部署一个标准的Web App；
- 不支持同时部署多个Web App；
- 不支持热部署。

# Tutorial

[Chinese Tutorial](https://liaoxuefeng.com/books/jerrymouse/)

# 服务器架构
设计Jerrymouse Server可以大幅简化为：
- 仅一个HTTP Connector，不支持HTTPS；
- 仅支持挂载到/的一个Context，不支持多个Host与多个Context，也只能有一个Web App。

运行多个Jerrymouse Server就可以运行多个Web App了。
Nginx还可以定义多个Host，根据Host转发给不同的Jerrymouse Server，所以，我们专注于实现一个仅支持HTTP、仅支持一个Web App的Web服务器，把HTTPS、HTTP/2、HTTP/3、Host、Cluster（集群）等功能全部扔给Nginx即可。

Jerrymouse Server的架构如下：
```
  ┌───────────────────────────────┐
  │       Jerrymouse Server       │
  │                 ┌───────────┐ │
  │  ┌─────────┐    │  Context  │ │
  │  │  HTTP   │    │┌─────────┐│ │
◀─┼─▶│Connector│◀──▶││ Web App ││ │
  │  └─────────┘    │└─────────┘│ │
  │                 └───────────┘ │
  └───────────────────────────────┘

```

# 设计与实现
## 实现HTTP服务器
编写HTTP Server实际上就是：
1. 监听TCP端口，等待浏览器连接；
2. 接受TCP连接后，创建一个线程处理该TCP连接：
   1. 接收浏览器发送的HTTP请求；
   2. 解析HTTP请求；
   3. 处理请求；
   4. 发送HTTP响应；
   5. 重复1～4直到TCP连接关闭。

直接使用JDK内置的jdk.httpserver。 
jdk.httpserver从JDK 9开始作为一个公开模块可以直接使用，它的包是com.sun.net.httpserver，主要提供以下几个类：
- HttpServer：通过指定IP地址和端口号，定义一个HTTP服务实例；
- HttpHandler：处理HTTP请求的核心接口，必须实现handle(HttpExchange)方法；
- HttpExchange：可以读取HTTP请求的输入，并将HTTP响应输出给它。

## 实现Servlet服务器
使用Adapter模式，把基于HttpExchange的操作转换为基于HttpServletRequest和HttpServletResponse。
- 定义HttpExchangeAdapter，它持有一个HttpExchange实例，并实现HttpExchangeRequest和HttpExchangeResponse接口
- 编写HttpServletRequestImpl，它内部持有HttpServletRequest，并实现了HttpServletRequest接口
- 同理，编写HttpServletResponseImpl

```
   ┌──────────────────────┐ ┌───────────────────────┐
   │  HttpServletRequest  │ │  HttpServletResponse  │
   └──────────────────────┘ └───────────────────────┘
               ▲                        ▲
               │                        │
   ┌──────────────────────┐ ┌───────────────────────┐
   │HttpServletRequestImpl│ │HttpServletResponseImpl│
┌──│- exchangeRequest     │ │- exchangeResponse ────┼──┐
│  └──────────────────────┘ └───────────────────────┘  │
│                                                      │
│  ┌──────────────────────┐ ┌───────────────────────┐  │
└─▶│ HttpExchangeRequest  │ │ HttpExchangeResponse  │◀─┘
   └──────────────────────┘ └───────────────────────┘
                      ▲         ▲
                      │         │
                      │         │
                 ┌───────────────────┐
                 │HttpExchangeAdapter│   ┌────────────┐
                 │- httpExchange ────┼──▶│HttpExchange│
                 └───────────────────┘   └────────────┘

```

改造处理HTTP请求的HttpHandler接口。
在handle(HttpExchange)方法内部，我们创建的对象如下：
- HttpExchangeAdapter实例：它内部引用了jdk.httpserver提供的HttpExchange实例；
- HttpServletRequestImpl实例：它内部引用了HttpExchangeAdapter实例，但是转换为HttpExchangeRequest接口；
- HttpServletResponseImpl实例：它内部引用了HttpExchangeAdapter实例，但是转换为HttpExchangeResponse接口。
- 
## 实现Servlet组件

### 实现ServletContext
既然ServletContext是一个Web App的全局唯一实例，而Web App又运行在Servlet容器中，我们在实现ServletContext时，完全可以把它当作Servlet容器来实现，它在内部维护一组Servlet实例，并根据Servlet配置的路由信息将请求转发给对应的Servlet处理。假设我们编写了两个Servlet：
- IndexServlet：映射路径为/；
- HelloServlet：映射路径为/hello。

那么，处理HTTP请求的路径如下：
```
                     ┌────────────────────┐
                     │   ServletContext   │
                     ├────────────────────┤
                     │     ┌────────────┐ │
    ┌─────────────┐  │ ┌──▶│IndexServlet│ │
───▶│HttpConnector│──┼─┤   ├────────────┤ │
    └─────────────┘  │ └──▶│HelloServlet│ │
                     │     └────────────┘ │
                     └────────────────────┘
```

---
编写Servlet容器时，直接实现ServletContext接口，并在内部完成所有Servlet的管理，就可以实现根据路径路由到匹配的Servlet。

###  实现FilterChain
将Filter纳入ServletContext中管理。

---
实现FilterChain时，要首先在ServletContext内完成所有Filter的初始化和映射，然后，根据请求路径匹配所有合适的Filter和唯一的Servlet，构造FilterChain并处理请求。

###  实现HttpSession
首先，我们需要一个SessionManager，用来管理所有的Session。SessionManager由ServletContextImpl持有唯一实例。

再编写一个HttpSession的实现类HttpSessionImpl。由于HttpSession是从HttpServletRequest获得的，因此，必须在HttpServletRequestImpl中引用ServletContextImpl，才能访问SessionManager。

最后，我们还需要实现Session的自动过期。由于我们管理的Session实际上是以Map<String, HttpSession>存储的，所以，让Session自动过期就是定期扫描所有的Session，然后根据最后一次访问时间将过期的Session自动删除。给SessionManager加一个Runnable接口，并启动一个Daemon线程。

和HttpServletRequest不同，访问HttpServletRequest实例的一定是一个线程，因此，HttpServletRequest的getAttribute()和setAttribute()不需要同步，底层存储用HashMap即可。但是，访问HttpSession实例的可能是多线程，所以，HttpSession的getAttribute()和setAttribute()需要实现并发访问，底层存储用ConcurrentHashMap即可。

---
使用Cookie模式实现HttpSession时，需要实现一个HttpSessionManager，它在内部维护一个Session ID到HttpSession实例的映射；

HttpSessionManager通过定期扫描所有HttpSession，将过期的HttpSession自动删除，因此，Session自动失效的时间不是特别精确；

由于没有对HttpSession进行持久化处理，重启服务器后，将丢失所有用户的Session。如果希望重启服务器后保留用户的Session，则需要将Session数据持久化到文件或数据库，此功能要求用户放入HttpSession的Java对象必须是可序列化的；

因为Session不容易扩展，因此，大规模集群的Web App通常自己管理Cookie来实现登录功能，这样，将用户状态完全保存在浏览器端，不使用Session，服务器就可以做到无状态集群。

###  实现Listener
首先我们需要在ServletContextImpl中注册并管理所有的Listener，所以用不同的List持有注册的Listener。

然后，实现ServletContext的addListener()接口，用于注册Listener。

接下来，就是在合适的时机，触发这些Listener。
以ServletContextAttributeListener为例，统一触发的方法放在ServletContextImpl中。当Web App的任何组件调用ServletContext的setAttribute()或removeAttribute()时，就可以触发事件通知。

---
Servlet规范定义了各种Listener组件，我们支持了其中常用的大部分EventListener组件；

Listener组件由ServletContext统一管理，并提供统一调度入口方法；

通知机制允许多线程同时调用，如果要防止并发调用Listener的回调方法，需要Listener组件本身在内部做好同步。

## 加载Web App

### 加载ClassLoader
1. 编写ClassLoader
JDK提供了一个URLClassLoader方便编写从文件加载的ClassLoader。
修改HttpConnector，传入ClassLoader和扫描的Class，就可以把所有Servlet、Filter和Listener添加到ServletContext中。

2. 设置ContextClassLoader
在HttpConnector中，我们还需要对handler()方法进行改进，正确设置线程的ContextClassLoader（上下文类加载器）。

为什么需要设置线程的ContextClassLoader？执行handle()方法的线程是由线程池提供的，线程池是HttpConnector创建的，因此，handle()方法内部加载的任何类都是由AppClassLoader加载的，而我们希望加载的类是由WebAppClassLoader从解压的war包中加载，此时，就需要设置线程的上下文类加载器。

---
开发Web服务器时，需要编写自定义的ClassLoader，才能从war包中加载.class文件；

处理Servlet请求的线程必须正确设置ContextClassLoader。

### 部署Web App
用Maven打包为标准的war包，我们得到一个hello-webapp-1.0.war文件。
启动Web Server并加载war文件，使用以下命令：
```bash
$ java -jar /path/to/jerrymouse-1.0.0.jar -w /path/to/hello-webapp-1.0.war
```

### 部署Spring WebApp
我们已经干掉了通过web.xml配置的方式，只支持自动扫描@WebServlet，因此，需要编写一个继承自DispatcherServlet的AppDispatcherServlet。
```java
@WebServlet(
    urlPatterns = "/",
    initParams = {
        @WebInitParam(name = "contextClass", value = "org.springframework.web.context.support.AnnotationConfigWebApplicationContext"),
        @WebInitParam(name = "contextConfigLocation", value = "com.itranswarp.sample.AppConfig") })
public class AppDispatcherServlet extends DispatcherServlet {
}

```

根据@WebServlet的配置，启动Spring容器，类型为AnnotationConfigWebApplicationContext，配置类com.itranswarp.sample.AppConfig，所以还需要编写配置类AppConfig。
```java
@Configuration
@ComponentScan
@EnableWebMvc
@EnableTransactionManagement
@PropertySource("classpath:/jdbc.properties")
public class AppConfig {
    @Bean
    WebMvcConfigurer createWebMvcConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addResourceHandlers(ResourceHandlerRegistry registry) {
                registry.addResourceHandler("/static/**").addResourceLocations("/static/");
                registry.addResourceHandler("/favicon.ico").addResourceLocations("/");
            }
        };
    }

    @Bean
    ViewResolver createViewResolver(@Autowired ServletContext servletContext) {
        var engine = new PebbleEngine.Builder().autoEscaping(true)
                // loader:
                .loader(new Servlet5Loader(servletContext))
                // build:
                .build();
        var viewResolver = new PebbleViewResolver(engine);
        viewResolver.setPrefix("/WEB-INF/templates/");
        viewResolver.setSuffix("");
        return viewResolver;
    }

    @Bean
    DataSource createDataSource(@Value("${jdbc.driver}") String jdbcDriver, @Value("${jdbc.url}") String jdbcUrl,
            @Value("${jdbc.username}") String jdbcUsername, @Value("${jdbc.password}") String jdbcPassword) {
        HikariConfig config = new HikariConfig();
        config.setDriverClassName(jdbcDriver);
        config.setJdbcUrl(jdbcUrl);
        config.setUsername(jdbcUsername);
        config.setPassword(jdbcPassword);
        config.addDataSourceProperty("autoCommit", "false");
        config.addDataSourceProperty("connectionTimeout", "5");
        config.addDataSourceProperty("idleTimeout", "60");
        return new HikariDataSource(config);
    }

    @Bean
    JdbcTemplate createJdbcTemplate(@Autowired DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    @Bean
    PlatformTransactionManager createTxManager(@Autowired DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }
}

```
AppConfig是标准的Spring配置类，我们正常配置MVC、ViewResolver、JDBC相关的DataSource、JdbcTemplate和PlatformTransactionManager，以及Spring容器需要的Service、Controller等，就可以实现一个完整的Spring MVC的WebApp。

用Maven编译spring-webapp，得到spring-webapp-1.0.war文件。用以下命令运行：
```bash
$ java -jar /path/to/jerrymouse-1.0.0.jar -w /path/to/spring-webapp-1.0.war
```

# 运行
```bash
# Normal WebApp
$ java -jar jerrymouse-1.0.0.jar --war hello-webapp-1.0.war

# Spring WebApp
$ java -jar jerrymouse-1.0.0.jar --war spring-webapp-1.0.war
```

# 常见问题
## 如何正确实现getOutputStream()和getWriter()？
根据Servlet规范，getOutputStream()和getWriter()在一次HTTP处理中只能二选一，不能都调用，因此，HttpServletResponse内部会用callOutput记录调用状态：
- null：未调用getOutputStream()和getWriter()；
- Boolean.TRUE：已调用getOutputStream()；
- Boolean.FALSE：已调用getWriter()。
- 
违反调用规则会抛出IllegalStateException。

## HttpServletResponse为什么要实现cleanup()？
因为Web App可能不会调用getOutputStream()或getWriter()，而是直接设置Header后返回：
resp.setStatus(403);

此时，HttpConnector要调用cleanup()，如果发现没有发送Header，则需要立刻发送Header，否则浏览器无法收到响应。
此外，根据HttpConnector的实现方式，基于JDK的HttpExchange的OutputStream也需要关闭（但不一定会关闭对应的TCP连接）。

## 如何对Servlet排序？
Servlet需要根据映射进行排序，遵循以下原则：
- 路径长的优先级高，例如，/auth/login排在/auth前；
- 前缀匹配比后缀匹配优先级高，例如，/auth/*排在*.do前。

## 如何处理“/”映射？
根据Servlet规范，/相当于/*，但还是有所不同，因为/表示默认的Servlet，即所有规则均不匹配时，最后匹配/。

如果一个Web App没有提供/映射，则Web Server可以自动提供一个默认的映射到/的Servlet。Jerrymouse和Tomcat类似，提供一个浏览文件的DefaultServlet。

## 如何处理静态文件？
处理静态文件时，将URL路径/path/to/file.doc转换为本地文件路径${webroot}/path/to/file.doc，然后根据扩展名设置正确的Content-Type，读取文件内容，发送即可。

需要注意的是，Servlet规范规定，不允许访问/WEB-INF/开头的URL，因此，遇到访问/WEB-INF/*的请求时，直接返回404错误码。

## 如何对Filter排序？
Servlet规范没有对Filter排序的要求，但我们在实现时还是按@WebFilter的filterName()进行排序，这样Web App可以根据名称调整Filter的顺序。

## 如何启用虚拟线程？
默认情况下，Jerrymouse Server采用线程池模式，要启用虚拟线程，可以加上配置项，以创建不同类型的ExecutorService：
```java
ExecutorService executor = config.server.enableVirtualThread ?
        Executors.newVirtualThreadPerTaskExecutor() :
        new ThreadPoolExecutor(0, config.server.threadPoolSize, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>());

```


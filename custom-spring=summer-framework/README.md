# Summer Framework

> 手写Spring
> not run!

![Maven Central](https://img.shields.io/maven-central/v/com.itranswarp.summer/summer-boot) ![Build Status](https://github.com/michaelliao/summer-framework/actions/workflows/maven.yml/badge.svg)

![logo](logo.png)

# Overview

Summer Framework is a lightweight dependency injection and web framework inspired by Spring Framework, providing essential functionality for Java application development.

# What is Summer Framework?

Summer Framework is a modular Java framework designed to simplify application development through dependency injection, aspect-oriented programming, database access, and web MVC capabilities. It follows a similar architectural pattern to Spring Framework but with a more focused feature set and lightweight design.

The framework is built on Java 21 and provides a complete solution for building web applications, from IoC container to web request handling and database access.

# Framework Architecture

The Summer Framework is organized into five core modules that build upon each other to provide a comprehensive development stack:

- summer-context: Core IoC container that support annotation-based injection;
- summer-aop: AOP support for annotation-based subclassing proxy;
- summer-jdbc: Provides JdbcTemplate and declarative transaction management;
- summer-web: Support web application with Servlet 6.0;
- summer-boot: Run Summer application simple and quick like Spring Boot.

---
Summer Framework设计目标如下：
- context模块：实现ApplicationContext容器与Bean的管理；
- aop模块：实现AOP功能；
- jdbc模块：实现JdbcTemplate，以及声明式事务管理；
- web模块：实现Web MVC和REST API；
- boot模块：实现一个简化版的“Spring Boot”，用于打包运行。

# Tutorial

[Chinese Tutorial](https://liaoxuefeng.com/books/summerframework/)

# 架构

# 设计与实现
## 实现IoC容器
下表列出了Spring Framework和Summer Framework在IoC容器方面的异同：

| 功能     | Spring Framework                 | Summer Framework      |
|--------|----------------------------------|-----------------------|
| IoC容器  | 支持BeanFactory和ApplicationContext | 仅支持ApplicationContext |
| 配置方式   | 支持XML与Annotation                 | 仅支持Annotation         |
| 扫描方式   | 支持按包名扫描                          | 支持按包名扫描               |
| Bean类型 | 支持Singleton和Prototype            | 仅支持Singleton          |
| Bean工厂 | 支持FactoryBean和@Bean注解            | 仅支持@Bean注解            |
| 定制Bean | 支持BeanPostProcessor              | 支持BeanPostProcessor   |
| 依赖注入   | 支持构造方法、Setter方法与字段               | 支持构造方法、Setter方法与字段    |
| 多容器    | 支持父子容器                           | 不支持                   |

### 实现ResourceResolver - 扫描Class名称
实现@ComponentScan，即解决“在指定包下扫描所有Class”的问题。搜索Class变成了搜索文件。

### 实现PropertyResolver
它支持3种查询方式：
- 按配置的key查询，例如：getProperty("app.title");
- 以${abc.xyz}形式的查询，例如，getProperty("${app.title}")，常用于@Value("${app.title}")注入；
- 带默认值的，以${abc.xyz:defaultValue}形式的查询，例如，getProperty("${app.title:Summer}")，常用于@Value("${app.title:Summer}")注入。

### 创建BeanDefinition
先定义BeanDefinition，它能从Annotation中提取到足够的信息，便于后续创建Bean、设置依赖、调用初始化方法等。
在BeanDefinition中，存储的beanClass是声明类型，实际类型不必存储，因为可以通过instance.getClass()获得。

对于getBean(Class)方法，必须遍历找出所有符合类型的Bean，如果不唯一，再判断@Primary，才能返回唯一Bean或报错。

对于自己定义的带@Component注解的Bean，我们需要获取Class类型，获取构造方法来创建Bean，然后收集@PostConstruct和@PreDestroy标注的初始化与销毁的方法，以及其他信息，如@Order定义Bean的内部排序顺序，@Primary定义存在多个相同类型时返回哪个“主要”Bean。一个典型的定义如下：
```java
@Component
public class Hello {
    @PostConstruct
    void init() {}

    @PreDestroy
    void destroy() {}
}

```
对于@Configuration定义的@Bean方法，我们把它看作Bean的工厂方法，我们需要获取方法返回值作为Class类型，方法本身作为创建Bean的factoryMethod，然后收集@Bean定义的initMethod和destroyMethod标识的初始化于销毁的方法名，以及其他@Order、@Primary等信息。一个典型的定义如下：
```java
@Configuration
public class AppConfig {
    @Bean(initMethod="init", destroyMethod="close")
    DataSource createDataSource() {
        return new HikariDataSource(/*...*/);
    }
}

```

获取所有BeanDefinition信息，实际分两步： 
1. 第一步是扫描指定包下的所有Class，然后返回Class名字
2. 第二步是真正处理各种注解。判断是否存在@Component，不但要在当前类查找@Component，还要在当前类的所有注解上，查找该注解是否有@Component，需要递归查找注解。


### 创建Bean实例
创建Bean的实例，同时注入强依赖。
此阶段必须检测循环依赖。检测循环依赖其实非常简单，就是定义一个Set<String>跟踪当前正在创建的所有Bean的名称。

### 初始化Bean
根据Setter方法和字段完成弱依赖注入，接着调用用@PostConstruct标注的init方法，就完成了所有Bean的初始化。
要注意一点，就是不仅要在当前类查找，还要在父类查找，因为有些@Autowired写在父类，所有子类都可使用，这样更方便。

处理@PreDestroy方法更简单，在ApplicationContext关闭时遍历所有Bean，调用destroy方法即可。

### 实现BeanPostProcessor
```
┌───────────────┐
│MvcController  │
├───────────────┤   ┌────────────────┐
│- userService ─┼──▶│UserServiceProxy│
└───────────────┘   ├────────────────┤
                    │- jdbcTemplate  │
                    ├────────────────┤   ┌────────────────┐
                    │- target       ─┼──▶│UserService     │
                    └────────────────┘   ├────────────────┤   ┌────────────┐
                                         │- jdbcTemplate ─┼──▶│JdbcTemplate│
                                         └────────────────┘   └────────────┘
```
引入BeanPostProcessor可以实现Proxy机制，但也让依赖注入变得更加复杂。

但是我们仔细分析依赖关系，还是可以总结出两条原则：
- 一个Bean如果被Proxy替换，则依赖它的Bean应注入Proxy，即上图的MvcController应注入UserServiceProxy；
- 一个Bean如果被Proxy替换，如果要注入依赖，则应该注入到原始对象，即上图的JdbcTemplate应注入到原始的UserService。

先改造创建Bean的流程，在创建@Configuration后，接着创建BeanPostProcessor，再创建其他普通Bean。

## 实现AOP
### 实现ProxyResolver
拦截器需要定义接口，这里我们直接用Java标准库的InvocationHandler，免去了自定义接口。
假定我们已经从IoC容器中获取了原始Bean与实现了InvocationHandler的拦截器Bean，那么就可以编写一个ProxyResolver来实现AOP代理。

### 实现Around
首先，客户端需要定义一个原始Bean，例如OriginBean，用@Around注解标注。
@Around注解的值aroundInvocationHandler指出应该按什么名字查找拦截器，因此，客户端应再定义一个AroundInvocationHandler

有了原始Bean、拦截器，就可以在IoC容器中装配AOP。
装配AOP是通过AroundProxyBeanPostProcessor实现的，而这个类是由Framework提供，客户端并不需要自己实现。因此，我们需要开发一个AroundProxyBeanPostProcessor
AroundProxyBeanPostProcessor的机制非常简单：检测每个Bean实例是否带有@Around注解，如果有，就根据注解的值查找Bean作为InvocationHandler，最后创建Proxy，返回前保存了原始Bean的引用，因为IoC容器在后续的注入阶段要把相关依赖和值注入到原始Bean。

实际上Around拦截本身就包含了Before和After拦截，我们没必要去修改ProxyResolver，只需要用Adapter模式提供两个拦截器模版，一个是BeforeInvocationHandlerAdapter，一个是AfterInvocationHandlerAdapter。

---
总结一下，Summer Framework提供的包括：
- Around注解；
- AroundProxyBeanPostProcessor实现AOP。

客户端代码需要提供的包括：
- 带@Around注解的原始Bean；
- 实现InvocationHandler的Bean，名字与@Around注解value保持一致。

## 实现JDBC和事务

| Spring Framework           | Summer Framework |     |
|----------------------------|------------------|-----|
| JdbcTemplate               | 支持               | 支持  |
| NamedParameterJdbcTemplate | 支持               | 不支持 |
| 转换SQL错误码                   | 支持               | 不支持 |
| ORM                        | 支持               | 不支持 |
| 手动管理事务                     | 支持               | 不支持 |
| 声明式事务                      | 支持               | 支持  |

### 实现JdbcTemplate
1. 配置DataSource。先定义默认的数据源配置项，再实现一个HikariCP支持的DataSource。
2. 定义JdbcTemplate。定义JdbcTemplate，唯一依赖是注入DataSource。

### 实现声明式事务

| Spring Framework            | Summer Framework |     |
|-----------------------------|------------------|-----|
| JDBC事务                      | 支持               | 支持  |
| JTA（Java Transaction API）事务 | 支持               | 不支持 |
| REQUIRED传播模式                | 支持               | 支持  |
| 其他传播模式                      | 支持               | 不支持 |
| 设置隔离级别                      | 支持               | 不支持 |

编写声明式事务管理：
1. 定义@Transactional，这里就不允许单独在方法处定义，直接在class级别启动所有public方法的事务。
2. 定义接口PlatformTransactionManager。
3. 定义TransactionStatus，表示当前事务状态。
4. 写个DataSourceTransactionManager，它持有一个ThreadLocal存储的TransactionStatus，以及一个DataSource。
   DataSourceTransactionManager是真正执行开启、提交、回归事务的地方，在在invoke()内部执行。
5. 提供一个TransactionalBeanPostProcessor，使得AOP机制生效，才能拦截@Transactional标注的Bean的public方法

---
总结下各个组件的作用：
- 由JdbcConfiguration创建的DataSource，实现了连接池；
- 由JdbcConfiguration创建的JdbcTemplate，实现基本SQL操作；
- 由JdbcConfiguration创建的PlatformTransactionManager，负责拦截@Transactional标识的Bean的public方法，自动管理事务；
- 由JdbcConfiguration创建的TransactionalBeanPostProcessor，负责给@Transactional标识的Bean创建AOP代理，拦截器正是PlatformTransactionManager。

应用程序除了导入一个JdbcConfiguration，加上默认配置项，什么也不用干，就可以开始写自动带声明式事务的代

## 实现Web MVC

### 启动IoC容器

| Spring Framework   | Summer Framework |     |
|--------------------|------------------|-----|
| DispatcherServlet  | 支持               | 支持  |
| @Controller注解      | 支持               | 支持  |
| @RestController注解  | 支持               | 支持  |
| ViewResolver       | 支持               | 支持  |
| HandlerInterceptor | 支持               | 不支持 |
| Exception Handler  | 支持               | 不支持 |
| CORS               | 支持               | 不支持 |
| 异步处理               | 支持               | 不支持 |
| WebSocket          | 支持               | 不支持 |

我们在开发Summer Framework的Web MVC模块时，应该以如下方式初始化：
1. 应用程序必须配置一个Summer Framework提供的Listener；
2. Tomcat完成Servlet容器的创建后，立刻根据配置创建Listener；
    1. Listener初始化时创建IoC容器；
    2. Listener继续创建DispatcherServlet实例，并向Servlet容器注册；
DispatcherServlet初始化时获取到IoC容器中的Controller实例，因此可以根据URL调用不同Controller实例的不同处理方法。

Web应用程序的初始化全部流程：
1. 先写一个只能输出Hello World的Servlet。
2. 编写一个ContextLoaderListener，它实现了ServletContextListener接口，能监听Servlet容器的启动和销毁，在监听到初始化事件时，完成创建IoC容器和注册DispatcherServlet两个工作。

### 实现MVC

在整个HTTP处理流程中，入口是DispatcherServlet的service()方法，整个流程如下：
1. Servlet容器调用DispatcherServlet的service()方法处理HTTP请求；
2. service()根据GET或POST调用doGet()或doPost()方法；
3. 根据URL依次匹配Dispatcher，匹配后调用process()方法，获得返回值；
4. 根据返回值写入响应：
    1. void或null返回值无需写入响应；
    2. String或byte[]返回值直接写入响应（或重定向）；
    3. REST类型写入JSON序列化结果；
    4. ModelAndView类型调用ViewResolver写入渲染结果。
5. 未匹配到判断是否静态资源：
    1. 符合静态目录（默认/static/）则读取文件，写入文件内容；
    2. 网站图标（默认/favicon.ico）则读取.ico文件，写入文件内容；
6. 其他情况返回404。

### 开发Web应用
创建一个hello-webapp的应用，它基于Maven项目，符合webapp标准。
1. 首先，我们在src/main/resources下定义配置文件application.yml。
2. 紧接着，定义IoC容器的配置类，以及相关的UserService、MvcController等Bean。
3. 接下来是在src/main/webapp/WEB-INF目录下创建Servlet容器所需的配置文件web.xml。
Servlet容器会自动读取web.xml，根据配置的Listener启动Summer Framework的web模块的ContextLoaderListener，它又会读取web.xml配置的<context-param>获得配置类的全名com.itranswarp.hello.HelloConfiguration，最后用这个配置类完成IoC容器的创建。创建后自动注册Summer Framework的DispatcherServlet，以及Web应用程序定义的FilterRegistrationBean，这样就完成了整个Web应用程序的初始化。
4. 最后，运行mvn clean package命令，在target目录得到最终的war包，改名为ROOT.war，复制到Tomcat的webapps目录下，启动Tomcat，可以正常访问http://localhost:8080

## 实现Boot
注意Spring Boot除了直接打包运行外，还提供很多其他功能，而Summer Framework的boot模块只提供打包运行功能，无其他额外功能。

### 启动嵌入式Tomcat
boot模块的开发，它其实就包含两个组件：
- SummerApplication：负责启动嵌入式Tomcat；
- ContextLoaderInitializer：负责启动IoC容器，注册Filter与DispatcherServlet。

### 开发Boot应用
先定义配置类HelloConfiguration，以及UserService、MvcController等业务Bean。

我们要运行的xyz.war包必须同时具有Web App的结构，又能在根目录下搜索到应用程序自己编写的Main。
解决方案是在打包时复制所有编译的class到war包根目录，并添加启动类入口；Main运行时先自解压，再让JVM能搜索到WEB-INF/lib/summer-boot-1.x.x.jar。

把war包解压到tmp-webapp，它的结构如下：
```
tmp-webapp
├── META-INF
│   └── MANIFEST.MF
├── WEB-INF
│   ├── classes
│   ├── lib
│   │   ├── summer-boot-1.0.3.jar
│   │   └── ... other jars ...
│   └── templates
│       └── ... templates.html
├── application.yml
├── com
│   └── itranswarp
│       └── hello
│           ├── Main.class
│           └── ... other classes ...
├── favicon.ico
├── logback.xml
└── static
    └── ... static files ...
```

META-INF/MANIFEST.MF:
```
Manifest-Version: 1.0
Created-By: Maven WAR Plugin 3.3.2
Build-Jdk-Spec: 17
Main-Class: com.itranswarp.hello.Main
Class-Path: tmp-webapp/WEB-INF/lib/summer-boot-1.0.3.jar tmp-webapp/WEB-
 INF/lib/summer-web-1.0.3.jar tmp-webapp/WEB-INF/lib/summer-context-1.0.
 3.jar tmp-webapp/WEB-INF/lib/snakeyaml-2.0.jar tmp-webapp/WEB-INF/lib/j
 ackson-databind-2.14.2.jar tmp-webapp/WEB-INF/lib/jackson-annotations-2
 .14.2.jar tmp-webapp/WEB-INF/lib/jackson-core-2.14.2.jar tmp-webapp/WEB
 -INF/lib/jakarta.annotation-api-2.1.1.jar tmp-webapp/WEB-INF/lib/slf4j-
 api-2.0.7.jar tmp-webapp/WEB-INF/lib/logback-classic-1.4.6.jar tmp-weba
 pp/WEB-INF/lib/logback-core-1.4.6.jar tmp-webapp/WEB-INF/lib/freemarker
 -2.3.32.jar tmp-webapp/WEB-INF/lib/summer-jdbc-1.0.3.jar tmp-webapp/WEB
 -INF/lib/summer-aop-1.0.3.jar tmp-webapp/WEB-INF/lib/byte-buddy-1.14.2.
 jar tmp-webapp/WEB-INF/lib/HikariCP-5.0.1.jar tmp-webapp/WEB-INF/lib/to
 mcat-embed-core-10.1.7.jar tmp-webapp/WEB-INF/lib/tomcat-annotations-ap
 i-10.1.7.jar tmp-webapp/WEB-INF/lib/tomcat-embed-jasper-10.1.7.jar tmp-
 webapp/WEB-INF/lib/tomcat-embed-el-10.1.7.jar tmp-webapp/WEB-INF/lib/ec
 j-3.32.0.jar tmp-webapp/WEB-INF/lib/sqlite-jdbc-3.41.2.1.jar

```

---
总结一下，打包时做了哪些工作：

- 复制所有编译的class到war包根目录；
- 修改META-INF/MANIFEST.MF：
    - 添加Main-Class条目；
    - 添加Class-Path条目。

运行时的流程如下：
1. JVM从war包加载Main类，执行main()方法；
2. 立刻自解压war包至tmp-webapp目录；
3. 后续加载SummerApplication时，JVM根据Class-Path能找到tmp-webapp/WEB-INF/lib/summer-boot-1.x.x.jar，因此可顺利加载；
4. 启动Tomcat，将tmp-webapp做为Web目录；
5. 作为Web App使用Tomcat的ClassLoader加载其他组件。

这样我们就实现了一个可以直接用java -jar xyz.war启动的Web应用程序！

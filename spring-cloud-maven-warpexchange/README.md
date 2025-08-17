# Warp Exchange 证券交易系统
> not run!

A simple, super fast, 7x24 exchange.

### Technology

- Java 17 + SpringBoot 3.x + Spring 6.x + Maven.
- 100% in-memory trading.

### Screenshot

![Screenshot](https://github.com/michaelliao/warpexchange/blob/main/screenshot.png?raw=true)

### Tutorial

[从零开始搭建一个7x24小时运行的证券交易所](https://liaoxuefeng.com/books/java/springcloud/)

```cmd
// 编译
cd build && mvn clean package

// 启动容器
cd build && docker-compose up -d
```
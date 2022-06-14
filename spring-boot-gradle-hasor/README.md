# spring-boot-gradle-hasor

## Hasor Dataway

Dataway可以通过UI页面定义数据接口，然后通过自测和冒烟以后才能发布这个接口，当接口发布成功以后，就可以通过我们定义的接口路径来访问获取数据了，这些接口数据都是保存在我们上面创建的interface_info表中的，当然接口发布以后也可以修改，也即接口可以有历史版本，这些信息保存在interface_release 表中，这也就是为什么我们什么要在上面定义这两个表；（Ps:当然Dataway也可以通过编写代码来实现）在Dataway的数据访问中使用的是一种名字叫做DataQL的脚本语言，类似JavaScript。

DataQL（Data Query Language）DataQL 是一种查询语言。旨在通过提供直观、灵活的语法来描述客户端应用程序的数据需求和交互。

数据的存储根据其业务形式通常是较为简单的，并不适合直接在页面上进行展示。因此开发页面的前端工程师需要为此做大量的工作，这就是 DataQL 极力解决的问题。另外还支持使用SQL来，但是SQL在Dataway中最终也都是转换成DataQL。

## 发现

1. url拦截路径 /api/，先到 dataway
```yml
# dataway  API工作路径（可选，默认：/api/）
HASOR_DATAQL_DATAWAY_API_URL: /api/
```
http://127.0.0.1:9800/api/hello?name=456
{"success":false,"message":"404 : not found api.","code":404,"lifeCycleTime":37,"executionTime":17,"value":"not found api."}

需要指定为不同的路径，如：http://127.0.0.1:9800/test/hello?name=456

2. Parameters自动映射
GET: request parameter
POST: request body

## References

[SpringBoot整合Hasor实践入坑指南](https://blog.csdn.net/chen15369337607/article/details/107210806/)


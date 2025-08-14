{
  "properties" : { },
  "id" : "6ec20e9003b44ed798598fea458eec6e",
  "script" : null,
  "groupId" : "3363a4ef601543c399be4b6691ea3538",
  "name" : "swagger",
  "createTime" : null,
  "updateTime" : 1656514920845,
  "lock" : null,
  "createBy" : null,
  "updateBy" : "admin",
  "path" : "/",
  "method" : "GET",
  "parameters" : [ ],
  "options" : [ ],
  "requestBody" : "",
  "headers" : [ ],
  "paths" : [ ],
  "responseBody" : null,
  "description" : null,
  "requestBodyDefinition" : null,
  "responseBodyDefinition" : null
}
================================
import response;
//重定向到该地址，内部利用HttpServletResponse的sendRedirect方法
return response.redirect('/swagger-ui/index.html');
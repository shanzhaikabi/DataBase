<%--
  Created by IntelliJ IDEA.
  User: Shanzhai
  Date: 2018-12-12
  Time: 14:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>数据库</title>
</head>
<body>
    <form action="${pageContext.request.contextPath}/login" method="GET">
        <label class="block clearfix">
            客户登陆
        </label>
        <input type="text" name="id" value="C114514">
        <input type="submit" value="前往" style="width:100px;">
    </form>
    <form action="${pageContext.request.contextPath}/slogin" method="GET">
        <label class="block clearfix">
            商家登录
        </label>
        <input type="text" name="id" value="11451401">
        <input type="submit" value="前往" style="width:100px;">
    </form>
</body>
</html>

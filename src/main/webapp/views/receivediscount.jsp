<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/12/25
  Time: 21:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
    <meta charset="utf-8" />
    <title>领取优惠券页面</title>

    <meta name="description" content="User login page" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />

    <!-- bootstrap & fontawesome -->
    <link rel="stylesheet" href="${pageContext.request.contextPath }/resource/register/assets/css/bootstrap.min.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath }/resource/register/assets/font-awesome/4.2.0/css/font-awesome.min.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath }/resource/register/assets/fonts/fonts.googleapis.com.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath }/resource/register/assets/css/ace.min.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath }/resource/register/assets/css/ace-rtl.min.css" />

</head>
<body class="login-layout">

<div class="widget-body" style="margin-left: 30%;margin-right: 30%">
    <div class="widget-main">
        <h4 class="header blue lighter bigger">
            <i class="ace-icon fa fa-coffee green"></i>
        </h4>
        <div class="space-6"></div>
        <fieldset><legend>菜单</legend>
            <a href = "search">搜索商品</a>&nbsp&nbsp
            <a href = "showcart">我的购物车</a>&nbsp&nbsp
            <a href = "mydiscount">我的优惠券</a>&nbsp&nbsp
            <a href = "showorder">我的订单</a><br>
        </fieldset>
        <%--${pageContext.request.contextPath }--%>
        <form>
            <a href="javascript:history.back(-1)">返回上一页</a>
            ${discount}
            ${result}
        </form>

    </div>
</div>
</body>
</html>

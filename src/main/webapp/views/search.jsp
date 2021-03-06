<%@ page import="com.ssh.entity.Product" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Iterator" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
    <meta charset="utf-8" />
    <title>搜索页面</title>

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
        <fieldset><legend>菜单</legend>
            <a href = "search">搜索商品</a>&nbsp&nbsp
            <a href = "showcart">我的购物车</a>&nbsp&nbsp
            <a href = "mydiscount">我的优惠券</a>&nbsp&nbsp
            <a href = "showorder">我的订单</a><br>
        </fieldset>
        <div class="space-6"></div>
        <%--${pageContext.request.contextPath }--%>
        <form action="${pageContext.request.contextPath}/search.do" method="POST">
            <fieldset><legend>搜索栏</legend>
                <div class="block input-icon input-icon-right">
                    关键字<input type="text" name="keyword" class="form-control" placeholder="请输入关键字"/>
                </div>
                <div>
                    <input type="radio" name="searchCheck" value="product" checked="checked"/>商品
                    <input type="radio" name="searchCheck" value="class"/>商品类别
                    <input type="radio" name="searchCheck" value="shop"/>商家
                </div>
                <div class="space"></div>
                <div class="clearfix" style="text-align: center;">
                    <input type="submit" value="搜索" style="width:100px;"/>
                </div>
                <div class="space-4"></div>
            </fieldset>
            <fieldset>
                <legend>${from}</legend>
                ${result}
            </fieldset>
        </form>
        <h4 class="header blue lighter bigger">
            <i class="ace-icon fa fa-coffee green"></i>
            ${failReason}
        </h4>
    </div>
</div>
</body>
</html>
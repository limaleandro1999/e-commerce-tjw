<%@ page import="com.ecommerce.model.User" %><%--
  Created by IntelliJ IDEA.
  User: limal
  Date: 13/02/2021
  Time: 01:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% User user = (User) request.getAttribute("user"); %>
<html>
<head>
    <title><%= user.getFirstName() %></title>
    <%@include file="../../commom/head-setting.jsp" %>
</head>
<body>
    <%@include file="../../commom/navbar.jsp" %>
    <h3><%= user.getFirstName() %> <%= user.getLastName() %></h3>
    <h3><%= user.getEmail() %></h3>
</body>
</html>

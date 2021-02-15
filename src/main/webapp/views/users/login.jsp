<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: limal
  Date: 14/02/2021
  Time: 14:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="message" value='${requestScope["errorMsg"]}' />
<html>
    <head>
        <title>Login</title>
        <%@include file="../../commom/head-setting.jsp" %>
    </head>
    <body>
        <c:if test="${message != null}">
            <p>${message}</p>
        </c:if>
        <h1>Login</h1>
        <form action="user?action=login" method="post">
            <div class="form-field">
                <label>Email</label>
                <input type="text" name="email">
            </div>

            <div class="form-field">
                <label>Senha</label>
                <input type="password" name="password">
            </div>

            <input type="submit" value="Login">
            <a href="user?action=registration">Cadastrar</a>
        </form>
    </body>
</html>

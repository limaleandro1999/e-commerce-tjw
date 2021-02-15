<%--
  Created by IntelliJ IDEA.
  User: limal
  Date: 15/02/2021
  Time: 16:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Finalizar Compra</title>
        <%@include file="../../commom/head-setting.jsp" %>
    </head>
    <body>
        <h1>Finalizar Compra</h1>
        <form action="order?action=create" method="post">
            <div class="form-field">
                <label>Endereço</label>
                <input type="text" name="address">
            </div>
            <div class="form-field">
                <label>Cidade</label>
                <input type="text" name="city">
            </div>
            <div class="form-field">
                <label>Estado</label>
                <input type="text" name="state">
            </div>
            <div class="form-field">
                <label>CEP</label>
                <input type="text" name="zipcode">
            </div>
            <input type="submit" value="Finalizar">
        </form>
    </body>
</html>

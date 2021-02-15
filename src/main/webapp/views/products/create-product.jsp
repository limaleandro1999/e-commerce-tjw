<%--
  Created by IntelliJ IDEA.
  User: limal
  Date: 15/02/2021
  Time: 20:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Cadastrar Produto</title>
        <%@include file="../../commom/head-setting.jsp" %>
    </head>
    <body>
        <h1>Cadastrar Produto</h1>
        <form action="product?action=create" method="post">
            <div class="form-field">
                <label>Nome</label>
                <input type="text" name="name">
            </div>

            <div class="form-field">
                <label>Descrição</label>
                <input type="textarea" name="description">
            </div>

            <div class="form-field">
                <label>Preço em centavos</label>
                <input type="number" name="price">
            </div>

            <div class="form-field">
                <label>URL da imagem</label>
                <input type="text" name="imageUrl">
            </div>

            <input type="submit" value="Cadastrar">
        </form>
    </body>
</html>

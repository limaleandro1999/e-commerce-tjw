<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="com.ecommerce.model.Product" %>
<%@ page import="java.util.List" %>
<%--
  Created by IntelliJ IDEA.
  User: limal
  Date: 13/02/2021
  Time: 12:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% List<Product> productList = (List<Product>) request.getAttribute("productList"); %>
<html>
    <head>
        <title>E-Commerce</title>
        <%@include file="../../commom/head-setting.jsp" %>
    </head>
    <body>
        <%@include file="../../commom/navbar.jsp" %>
        <h1>Produtos à venda</h1>
        <div class="product-list-container">
            <c:forEach items="${productList}" var="product">
                <div class="product-container">
                    <img src="${product.imageUrl}">
                    <p class="product-name">${product.name}</p>
                    <p class="product-description">${product.description}</p>
                    <div class="product-price-container">
                            <fmt:setLocale value="pt-BR"/>
                            <fmt:formatNumber type="currency" minFractionDigits="2" value="${(product.price / 100)}" />
                        <c:choose>
                            <c:when test="${sessionScope.userId == null}">
                                <a href="user?action=login">
                                    <button>Adicionar</button>
                                </a>
                            </c:when>
                            <c:otherwise>
                                <a href="cart?action=addItem&productId=${product.id}">
                                    <button>Adicionar</button>
                                </a>
                            </c:otherwise>
                        </c:choose>
                    </div>
                </div>
            </c:forEach>
        </div>
    </body>
</html>

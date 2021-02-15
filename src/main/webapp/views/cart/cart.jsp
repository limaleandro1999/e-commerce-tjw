<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%--
  Created by IntelliJ IDEA.
  User: limal
  Date: 15/02/2021
  Time: 14:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="cart" value='${requestScope["cart"]}' />
<c:set var="cartQuantity" value='${requestScope["cartQuantity"]}' />
<c:set var="cartTotalAmount" value='${requestScope["cartTotalAmount"]}' />
<fmt:setLocale value="pt-BR"/>
<html>
    <head>
        <title>Carrinho</title>
        <%@include file="../../commom/head-setting.jsp" %>
    </head>
    <body>
        <%@include file="../../commom/navbar.jsp" %>
        <h1>Carrinho (${cartQuantity})</h1>
        <div class="cart-page-container">
            <div class="cart-item-list-container">
                <c:forEach items="${cart}" var="cartItem">
                    <div class="cart-item-container">
                        <div class="product-info">
                            <img src="${cartItem.product.imageUrl}">
                            <div class="product-info-text">
                                <p class="cart-item-name">${cartItem.product.name}</p>
                                <div>
                                    <p>Preço por unidade: <fmt:formatNumber type="currency" minFractionDigits="2" value="${(cartItem.product.price / 100)}" /></p>
                                    <p>Quantidade:&nbsp;${cartItem.quantity}</p>
                                </div>
                            </div>
                        </div>
                        <div class="cart-item-price-container">
                            <div>
                                <p><fmt:formatNumber type="currency" minFractionDigits="2" value="${(cartItem.product.price * cartItem.quantity / 100)}" /></p>
                                <a href="cart?action=addItem&productId=${cartItem.product.id}"><button>Adicionar</button></a>
                            </div>
                            <div></div>
                        </div>
                    </div>
                </c:forEach>
            </div>
            <div class="cart-total-container">
                <h1>Sumário</h1>
                <p>Total: <fmt:formatNumber type="currency" minFractionDigits="2" value="${(cartTotalAmount / 100)}" /></p>
                <a href="order?action=orderForm">Finalizar</a>
            </div>
        </div>
    </body>
</html>

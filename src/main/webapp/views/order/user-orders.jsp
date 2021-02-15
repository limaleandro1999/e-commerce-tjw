<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%--
  Created by IntelliJ IDEA.
  User: limal
  Date: 15/02/2021
  Time: 18:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="orders" value='${requestScope["orderList"]}' />
<c:set var="totalAmountByOrderId" value='${requestScope["totalAmountByOrderId"]}' />
<fmt:setLocale value="pt-BR"/>
<html>
    <head>
        <title>Minhas Compras</title>
        <%@include file="../../commom/head-setting.jsp" %>
    </head>
    <body>
        <%@include file="../../commom/navbar.jsp" %>
        <div class="orders-list-container">
            <c:forEach items="${orders}" var="order">
                <div class="order-item-container">
                    <div class="order-item-shipping-info">
                        <h2>Envio</h2>
                        <p>${order.user.firstName} ${order.user.lastName}</p>
                        <p>${order.address} - ${order.city} - ${order.state} - ${order.zipcode}</p>
                    </div>
                    <div class="order-item-products-info">
                        <h2>Produtos</h2>
                        <ul>
                            <c:forEach items="${order.orderSegments}" var="orderItem">
                                <li>${orderItem.product.name} - <fmt:formatNumber type="currency" minFractionDigits="2" value="${(orderItem.product.price / 100)}" /></li>
                            </c:forEach>
                        </ul>
                    </div>
                    <div class="order-item-billing-info">
                        <h3>Total</h3>
                        <fmt:formatNumber type="currency" minFractionDigits="2" value="${(totalAmountByOrderId.get(order.id) / 100)}" />
                    </div>
                </div>
            </c:forEach>
        </div>
    </body>
</html>

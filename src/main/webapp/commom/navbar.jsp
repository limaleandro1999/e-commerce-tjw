<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: limal
  Date: 13/02/2021
  Time: 10:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="cartQuantity" value='${requestScope["cartQuantity"]}' />
<div class="navbar">
    <a href="home">Home</a>
    <div class="user-actions">
        <c:choose>
            <c:when test="${sessionScope.userId == null}">
                <a href="user?action=login">Login</a>
            </c:when>
            <c:otherwise>
                <a href="cart?action=getCart">
                    <div class="cart-quantity">
                        <i class="fas fa-shopping-cart"></i>${cartQuantity}
                    </div>
                </a>
                <a href="order?action=myOrders">Minhas compras</a>
                <a href="user?action=logout">Logout</a>
            </c:otherwise>
        </c:choose>
    </div>
</div>

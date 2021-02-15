package com.ecommerce.controller;

import com.ecommerce.dao.CartDAO;
import com.ecommerce.model.Cart;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class BaseServlet extends HttpServlet {
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        appendCartInfo(req, res);
    }

    void appendCartInfo(HttpServletRequest req, HttpServletResponse res) {
        CartDAO cartDAO = new CartDAO();
        HttpSession session = req.getSession();
        Long userId = (Long) session.getAttribute("userId");
        List<Cart> cart = cartDAO.getCartItems(userId);
        Integer cartItemsNumber = cart.stream().reduce(0, (partialCartQuantityResult, cart1) -> partialCartQuantityResult + cart1.getQuantity(), Integer::sum);
        Integer cartTotalAmount = cart.stream().reduce(0, (partialCartQuantityResult, cart1) -> Math.toIntExact(partialCartQuantityResult + (cart1.getQuantity() * cart1.getProduct().getPrice())), Integer::sum);
        req.setAttribute("cartQuantity", cartItemsNumber);
        req.setAttribute("cart", cart);
        req.setAttribute("cartTotalAmount", cartTotalAmount);
    }
}

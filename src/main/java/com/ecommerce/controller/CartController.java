package com.ecommerce.controller;

import com.ecommerce.dao.CartDAO;
import com.ecommerce.model.Cart;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class CartController extends BaseServlet {
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        super.doGet(req, res);
        handleGetRequest(req, res);
    }

    void handleGetRequest(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        String action = req.getParameterValues("action")[0];

        switch (action) {
            case "addItem":
                addItemGet(req, res);
                break;

            case "getCart":
                getCart(req, res);
                break;
        }
    }

    void addItemGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
        HttpSession session = req.getSession();
        Long userId = (Long) session.getAttribute("userId");
        Long productId = req.getParameterValues("productId")[0] != null ? Long.parseLong(req.getParameterValues("productId")[0]) : null;

        if (productId == null) {
            res.sendError(400, "productId é necessário");
        } else if(userId == null) {
            res.sendError(401, "Usuário não está logado");
        } else {
            CartDAO cartDAO = new CartDAO();
            cartDAO.addItem(productId, userId);
            res.sendRedirect("home");
        }
    }

    void getCart(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Long userId = (Long) session.getAttribute("userId");
        CartDAO cartDAO = new CartDAO();
        List<Cart> cart = cartDAO.getCartItems(userId);
        req.setAttribute("cart", cart);
        req.getRequestDispatcher("views/cart/cart.jsp").forward(req, res);
    }
}

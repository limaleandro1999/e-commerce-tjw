package com.ecommerce.controller;

import com.ecommerce.dao.CartDAO;
import com.ecommerce.dao.OrderDAO;
import com.ecommerce.dao.UserDAO;
import com.ecommerce.model.Cart;
import com.ecommerce.model.LineItem;
import com.ecommerce.model.Order;
import com.ecommerce.model.User;
import com.sun.org.apache.xpath.internal.operations.Or;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OrderController extends BaseServlet {
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        super.doGet(req, res);
        handleGetRequest(req, res);
    }

    public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        handlePostRequest(req, res);
    }

    void handleGetRequest(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        String action = req.getParameterValues("action")[0];

        switch (action) {
            case "orderForm":
                req.getRequestDispatcher("views/order/order-form.jsp").forward(req, res);
                break;

            case "myOrders":
                myOrdersGet(req, res);
                break;
        }
    }

    void handlePostRequest(HttpServletRequest req, HttpServletResponse res) throws IOException {
        String action = req.getParameterValues("action")[0];

        switch (action) {
            case "create":
                createOrderPost(req, res);
                break;
        }
    }

    void createOrderPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
        HttpSession session = req.getSession();
        Long userId = (Long) session.getAttribute("userId");
        CartDAO cartDAO = new CartDAO();
        List<Cart> cartItems = cartDAO.getCartItems(userId);
        String address =  req.getParameter("address");
        String city = req.getParameter("city");
        String state = req.getParameter("state");
        String zipcode = req.getParameter("zipcode");
        List<LineItem> orderSegments = cartItems.stream().map(cartItem -> new LineItem(cartItem.getProduct(), cartItem.getQuantity())).collect(Collectors.toList());

        UserDAO userDAO = new UserDAO();
        User user = userDAO.findById(userId);

        Order order = new Order();
        order.setAddress(address);
        order.setCity(city);
        order.setState(state);
        order.setZipcode(zipcode);
        order.setOrderSegments(orderSegments);
        order.setUser(user);

        OrderDAO orderDAO = new OrderDAO();
        orderDAO.createOrder(order);

        cartDAO.deleteUserCart(userId);

        res.sendRedirect("order?action=myOrders");
    }

    void myOrdersGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        HttpSession session = req.getSession();
        Long userId = (Long) session.getAttribute("userId");
        OrderDAO orderDAO = new OrderDAO();
        List<Order> orderList = orderDAO.getUserOrders(userId);
        Map<Long, Integer> totalAmountByOrderId = new HashMap<>();

        for (Order order: orderList) {
            totalAmountByOrderId.put(order.getId(), order.getOrderTotal());
        }

        req.setAttribute("orderList", orderList);
        req.setAttribute("totalAmountByOrderId", totalAmountByOrderId);
        req.getRequestDispatcher("views/order/user-orders.jsp").forward(req, res);
    }
}

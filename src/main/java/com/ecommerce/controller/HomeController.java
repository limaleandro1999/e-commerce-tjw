package com.ecommerce.controller;

import com.ecommerce.dao.ProductDAO;
import com.ecommerce.model.Product;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class HomeController extends BaseServlet {
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        super.doGet(req, res);
        handleGetRequest(req, res);
    }

    void handleGetRequest(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException  {
        String action = req.getParameterValues("action") != null ? req.getParameterValues("action")[0] : "";

        switch (action) {
            default:
                ProductDAO productDAO = new ProductDAO();
                List<Product> productList = productDAO.getProductList();
                req.setAttribute("productList", productList);
                req.getRequestDispatcher("views/home/home.jsp").forward(req, res);
        }
    }
}

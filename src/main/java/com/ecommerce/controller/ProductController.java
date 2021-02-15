package com.ecommerce.controller;

import com.ecommerce.dao.ProductDAO;
import com.ecommerce.model.Product;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ProductController extends BaseServlet {
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        handlePostRequest(req, res);
    }

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        super.doGet(req, res);
        handleGetRequest(req, res);
    }

    void handlePostRequest(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String action = req.getParameterValues("action")[0];

        switch (action) {
            case "create":
                createProductGet(req, res);
                break;
        }
    }

    void handleGetRequest(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String action = req.getParameterValues("action")[0];

        switch (action) {
            case "productForm":
                req.getRequestDispatcher("views/products/create-product.jsp").forward(req, res);
                break;
        }
    }

    void createProductGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
        String name =  req.getParameter("name");
        String description =  req.getParameter("description");
        Long price = Long.parseLong(req.getParameter("price"));
        String imageUrl = req.getParameter("imageUrl");

        Product product = new Product();
        product.setName(name);
        product.setDescription(description);
        product.setPrice(price);
        product.setImageUrl(imageUrl);

        ProductDAO productDAO = new ProductDAO();
        productDAO.createProduct(product);

        res.sendRedirect("home");
    }
}

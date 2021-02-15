package com.ecommerce.controller;


import com.ecommerce.dao.UserDAO;
import com.ecommerce.model.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Arrays;

public class UserController extends BaseServlet {
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        super.doGet(req, res);
        handleGetRequest(req, res);
    }

    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        handlePostRequest(req, res);
    }

    void handleGetRequest(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException  {
        String action = req.getParameterValues("action")[0];

        switch (action) {
            case "registration":
                req.getRequestDispatcher("views/users/create-user.jsp").forward(req, res);
                break;

            case "login":
                req.getRequestDispatcher("views/users/login.jsp").forward(req, res);
                break;

            case "logout":
                logout(req, res);
                break;
        }
    }

    void handlePostRequest(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String action = req.getParameterValues("action")[0];

        switch (action) {
            case "create":
                createUserPost(req, res);
                break;

            case "login":
                loginPost(req, res);
                break;
        }
    }

    void createUserPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        User user = new User();
        UserDAO userDAO = new UserDAO();

        user.setFirstName(req.getParameter("firstName"));
        user.setLastName(req.getParameter("lastName"));
        user.setEmail(req.getParameter("email"));
        user.setPassword(req.getParameter("password"));
        user.setCpf(req.getParameter("cpf"));

        userDAO.createUser(user);

        req.setAttribute("user", user);
        req.getRequestDispatcher("views/users/details-user.jsp").forward(req,res);
    }

    void loginPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        UserDAO userDAO = new UserDAO();
        User user = userDAO.findByEmail(email);

        if (user == null) {
            req.setAttribute("errorMsg", "Usuário não cadastrado");
            req.getRequestDispatcher("views/users/login.jsp").forward(req, res);
        } else if (!password.equals(user.getPassword())) {
            req.setAttribute("errorMsg", "Credenciais incorretas");
            req.getRequestDispatcher("views/users/login.jsp").forward(req, res);
        } else {
            HttpSession session = req.getSession();
            session.setAttribute("userId", user.getId());
            session.setAttribute("userFirstName", user.getFirstName());
            session.setAttribute("userLastName", user.getLastName());
            res.sendRedirect("home");
        }
    }

    void logout(HttpServletRequest req, HttpServletResponse res) throws IOException {
        HttpSession session = req.getSession();
        session.invalidate();
        res.sendRedirect("home");
    }
}

package org.cs157.studentApplication;


import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    private final UserDAO userDAO = new UserDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        // Forward request to the actual HTML or JSP
        req.getRequestDispatcher("/register.html").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        // Get form values
        String first_name = req.getParameter("first_name");
        String last_name = req.getParameter("last_name");
        String email = req.getParameter("email");
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String major = req.getParameter("major");
        int school_year = Integer.parseInt(req.getParameter("school_year"));

        User user = new User(first_name, last_name, email, username, password, major, school_year);

        boolean success = userDAO.registerUser(user);

        if (success) {
            resp.sendRedirect("home.html");
        } else {
            System.out.println("REGISTRATION FAILED");
            resp.sendRedirect("register");
        }
    }
}
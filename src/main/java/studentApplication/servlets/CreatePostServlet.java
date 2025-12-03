
package studentApplication.servlets;


import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDateTime;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import studentApplication.daos.UserDAO;
import studentApplication.classes.Post;
import studentApplication.classes.User;
import studentApplication.daos.PostDAO;
import util.DBConnection;
import java.sql.Timestamp;

@WebServlet("/createpost")
public class CreatePostServlet extends HttpServlet {
    private PostDAO postDAO;
    private User user;
    
    @Override
    public void init() throws ServletException{
        try {    
            postDAO = new PostDAO(DBConnection.getConnection());
        } catch (SQLException e) {
            throw new ServletException("Unable to initialize PostDAO", e);
        }
        
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if (session != null) {
             user = (User) session.getAttribute("user");  
            if (user != null) {
                req.setAttribute("user", user);  
                req.getRequestDispatcher("/createpost.jsp").forward(req, resp);
            } else {
                resp.sendRedirect("login?error=true");
            }
        } else {
            resp.sendRedirect("login?error=true");
        }
        
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        
        String description= req.getParameter("description");
        String className = req.getParameter("className");

        Post post = new Post(description, new java.sql.Date(System.currentTimeMillis()), className, false, 0, user.getUserId());
        boolean success = postDAO.createPost(post, user);

        if (success) {
            req.getRequestDispatcher("home").forward(req, resp);
        } else {
            System.out.println("POST CREATION FAILED");
            resp.sendRedirect("home?error=true");
        }
        
    }
}


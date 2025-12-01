/* 
package studentApplication.servlets;


import java.io.IOException;
import java.util.Date;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import studentApplication.daos.UserDAO;
import studentApplication.classes.Post;

@WebServlet("/createpost")
public class CreatePostServlet extends HttpServlet {
    private final PostDAO postDAO = new PostDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        // Forward request to the actual HTML or JSP
        req.getRequestDispatcher("/createpost.html").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        // Get form values
        String  description= req.getParameter("description");
        Date date_posted = req.getParameter("date_posted");
        String class = req.getParameter("class");
        Boolean status = req.getParameter("status");
        String  = req.getParameter("");
        int  = Integer.parseInt(req.getParameter(""));

        Post post = new Post();

        boolean success = postDAO.createPost(post);

        if (success) {
            resp.sendRedirect("profile");
        } else {
            System.out.println("REGISTRATION FAILED");
            resp.sendRedirect("createpost");
        }
    }
}

*/
package studentApplication.servlets;

import java.io.IOException;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import studentApplication.classes.Comment;
import studentApplication.classes.Post;
import studentApplication.classes.User;
import studentApplication.daos.CommentDAO;
import studentApplication.daos.PostDAO;
import util.DBConnection;

@WebServlet("/comment")
public class CommentServlet extends HttpServlet{
    private CommentDAO commentDAO;
    
    @Override
    public void init() throws ServletException{
        try {    
            commentDAO = new CommentDAO(DBConnection.getConnection());
        } catch (SQLException e) {
            throw new ServletException("Unable to initialize PostDAO", e);
        }
        
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        
                HttpSession session = req.getSession(false);
        if(session == null) {
            resp.sendRedirect("login?error=true");
            return;
        }
        User user = (User) session.getAttribute("user");
        if(user == null) {
            resp.sendRedirect("login?error=true");
            return;
        }

        String commentString = req.getParameter("commentText");
        Comment comment = new Comment(commentString, new java.sql.Date(System.currentTimeMillis()), user.getUserId(), Integer.parseInt(req.getParameter("postId")));
        boolean success = commentDAO.insertComment(comment.getCommentText(), comment.getDateCommented(), comment.getUserId(), comment.getPostId());

        if (success) {
            //req.getRequestDispatcher("home").forward(req, resp);
            resp.sendRedirect("home");
        } else {
            System.out.println("POST CREATION FAILED");
            resp.sendRedirect("home?error=true");
        }
    }
}

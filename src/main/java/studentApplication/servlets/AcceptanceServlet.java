package studentApplication.servlets;

import java.io.IOException;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import studentApplication.classes.Acceptance;
import studentApplication.classes.Post;
import studentApplication.classes.User;
import studentApplication.daos.AcceptanceDAO;
import studentApplication.daos.PostDAO;
import util.DBConnection;

@WebServlet("/acceptance")
public class AcceptanceServlet extends HttpServlet{
    private AcceptanceDAO acceptanceDAO;
    
    @Override
    public void init() throws ServletException{
        try {    
            acceptanceDAO = new AcceptanceDAO(DBConnection.getConnection());
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

        Acceptance acceptance = new Acceptance(new java.sql.Date(System.currentTimeMillis()), user.getUserId(), Integer.parseInt(req.getParameter("postId")));
        if(req.getParameter("acceptance").equals("true")) {
            boolean success = acceptanceDAO.insertAcceptance(acceptance.getDateAccepted(), acceptance.getUserId(), acceptance.getPostId());
            if (success) {
                resp.sendRedirect("home");
            } else {
                System.out.println("ACCEPTANCE FAILED");
                resp.sendRedirect("home?error=true");
            }
        }
    }
}

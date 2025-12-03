package studentApplication.servlets;


import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import studentApplication.daos.UserDAO;
import studentApplication.classes.Message;
import studentApplication.classes.User;
import studentApplication.daos.MessageDAO;
import util.DBConnection;
import java.sql.Timestamp;

@WebServlet("/message")
public class MessageServlet extends HttpServlet {
    private MessageDAO messageDAO;
    private UserDAO userDAO;
    
    @Override
    public void init() throws ServletException{
        try {    
            Connection conn = DBConnection.getConnection();
            messageDAO = new MessageDAO(conn);
            userDAO = new UserDAO(conn);
        } catch (SQLException e) {
            throw new ServletException("Unable to initialize DAOs", e);
        }
        
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        
        HttpSession session = req.getSession(false);
        if (session != null) {
            User user = (User) session.getAttribute("user");
            
            if (user != null) {
                req.setAttribute("user", user);  
                req.getRequestDispatcher("/message.jsp").forward(req, resp);
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
        req.setAttribute("user", user);  

        String messageString= req.getParameter("message");
        String receiverName = req.getParameter("receiverName");
                
        Message message = new Message(messageString, new Timestamp(System.currentTimeMillis()), user.getUserId(), userDAO.findUserByUsername(receiverName).getUserId());
                    
        boolean success = messageDAO.insertMessage(message);

        if(userDAO.findUserByUsername(receiverName) == null) {
            System.out.println("USER NOT FOUND");
            resp.sendRedirect("message>?error=true");
        } else if (receiverName.equals(user.getUsername())) {
            System.out.println("CANNOT MESSAGE YOURSELF");
            resp.sendRedirect("message?error=true");
        } else if (success) {
            resp.sendRedirect("message");
        } else {
            System.out.println("MESSAGE FAILED");
            resp.sendRedirect("message?error=true");
        }

        

            
        
    }
}

package studentApplication.servlets;


import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import studentApplication.daos.UserDAO;
import studentApplication.classes.User;

/*
* Servlet that handles user registration. 
* Mapped to the /register URL.
*/

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {

    /*
    * Handles GET requests to logout of account.
    * Forwards request to login page
    * @param req HttpServletRequest object that contains the request the client made
    * @param resp HttpServletResponse object that contains the response the servlet returns
    * @throws ServletException if a servlet-specific error occurs
    * @throws IOException if an I/O error occurs
    */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if (session != null) {
            session.invalidate();
        }

        resp.sendRedirect("login");
    }


    
    
}
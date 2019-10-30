package controller;

import model.UserBean;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/login")
public class LoginController extends HttpServlet {

    private static final String PAGE_LOGIN_JSP = "/WEB-INF/jsp/login.jsp";
    private static final String PAGE_SCORE_JSP = "/scores";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {

        HttpSession session = request.getSession();
        UserBean bean = ( UserBean ) session.getAttribute( "userBean" );
        if ( bean == null ) {
            bean = new UserBean();
            session.setAttribute( "userBean", bean );
        }
        if ( bean.isConnected( request ) ) {
            response.sendRedirect( request.getContextPath() + PAGE_SCORE_JSP);
        } else {
            request.getServletContext().getRequestDispatcher( PAGE_LOGIN_JSP ).forward( request, response );
        }
    }

    @Override
    protected void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {

        HttpSession session = request.getSession();
        UserBean bean = new UserBean();
        bean.authenticate( request );
        session.setAttribute( "userBean", bean );
        doGet( request, response );
    }
}

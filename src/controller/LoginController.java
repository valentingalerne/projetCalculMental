package controller;

import model.UserBean;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

@WebServlet("/login")
public class LoginController extends HttpServlet {

    private static final String PAGE_LOGIN_JSP = "/WEB-INF/jsp/login.jsp";
    private static final String PAGE_GAME_JSP = "/game";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {

        UserBean bean = ( UserBean ) request.getAttribute( "userBean" );
        if ( bean == null ) {
            bean = new UserBean();
            request.setAttribute( "userBean", bean );
        }
        if ( bean.isConnected( request ) ) {
            response.sendRedirect( request.getContextPath() + PAGE_GAME_JSP );
        } else {
            request.getServletContext().getRequestDispatcher( PAGE_LOGIN_JSP ).forward( request, response );
        }
    }

    @Override
    protected void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {

        UserBean bean = new UserBean();
        bean.authenticate( request );
        request.setAttribute( "userBean", bean );
        doGet( request, response );
    }
}

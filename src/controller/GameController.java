package controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet( urlPatterns = {"/game"} )
public class GameController extends HttpServlet {

    private static final String PAGE_GAME_JSP = "/WEB-INF/jsp/game.jsp";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
        request.getServletContext().getRequestDispatcher( PAGE_GAME_JSP ).forward( request, response );
    }
}

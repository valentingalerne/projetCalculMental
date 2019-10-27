package controller;

import model.GameBean;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet( urlPatterns = {"/game"} )
public class GameController extends HttpServlet {

    private static final String PAGE_GAME_JSP = "/WEB-INF/jsp/game.jsp";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
        //request.getServletContext().getRequestDispatcher( PAGE_GAME_JSP ).forward( request, response );

        GameBean bean = new GameBean();
        request.setAttribute("gameBean", bean);

        String path = request.getServletPath();
        String realPath = path.substring(path.lastIndexOf("/") + 1);

        if (realPath.equals("game")) {
            bean.loadGame(request);
            request.getServletContext().getRequestDispatcher(PAGE_GAME_JSP).forward(request, response);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        GameBean bean = new GameBean();
        HttpSession session = request.getSession();
        int score = 0;

        if (bean.checkResult(request)) {
            score ++;
            session.setAttribute("score", score);
        } else {
            session.setAttribute("score", score);
        }
    }
}

package controller;

import bo.Game;
import dal.jdbc.GameDAO;
import model.GameBean;
import model.UserBean;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet( urlPatterns = {"/game"} )
public class GameController extends HttpServlet {

    private static final String PAGE_GAME_JSP = "/WEB-INF/jsp/game.jsp";
    private static final String NB_ETAPE_CALCUL = "nbEtape";
    private static final String SCORE_PARTIE = "scorePartie";

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
        int etape = (int) session.getAttribute(NB_ETAPE_CALCUL);
        int score = (int) session.getAttribute(SCORE_PARTIE);

        if (bean.checkResult(request)) {
            score++;
            session.setAttribute(SCORE_PARTIE, score);
        }
        etape++;
        session.setAttribute(NB_ETAPE_CALCUL, etape);
        System.out.println("Numéro étape : " + session.getAttribute(NB_ETAPE_CALCUL));
        System.out.println("score : " + session.getAttribute(SCORE_PARTIE));

        if (etape <= 10) {
            bean.loadGame(request);
            request.getServletContext().getRequestDispatcher(PAGE_GAME_JSP).forward(request, response);
        } else {
            System.out.println("terminé!");
            UserBean us = ( UserBean ) session.getAttribute( "userBean" );
            System.out.println("USER : " + us.getUser().toString());
            Game game = new Game(us.getUser(), score);
            GameDAO gameDAO = new GameDAO();
            try {
                gameDAO.create(game);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}

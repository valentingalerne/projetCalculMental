package controller;

import model.ScoreBean;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.logging.Level;

@WebServlet( urlPatterns = {"/scores", "/scores_final"} )
public class ScoreController extends HttpServlet {

    private static final String PAGE_SCORE_JSP = "/WEB-INF/jsp/liste_scores.jsp";
    private static final String PAGE_SCORE_FIN_JSP = "/WEB-INF/jsp/liste_fin.jsp";
    private static final String NB_ETAPE_CALCUL = "nbEtape";
    private static final String SCORE_PARTIE = "scorePartie";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
        //request.getServletContext().getRequestDispatcher( PAGE_GAME_JSP ).forward( request, response );

        HttpSession session = request.getSession();
        ScoreBean bean = new ScoreBean();
        request.setAttribute( "scoreBean", bean );

        String path = request.getServletPath();
        String realPath = path.substring( path.lastIndexOf( "/" )+ 1 );

        session.setAttribute(NB_ETAPE_CALCUL, 0);
        session.setAttribute(SCORE_PARTIE, 0);

        switch ( realPath ) {
            case "scores_final":
                String score = request.getParameter("score");
                if (score == null) {
                    request.setAttribute( "score", "?" );
                } else {
                    request.setAttribute( "score", score );
                }
                bean.loadScoreBoard( request );
                request.getServletContext().getRequestDispatcher( PAGE_SCORE_FIN_JSP ).forward( request, response );
                break;

            case "scores":
            default:
                bean.loadGameList( request );
                request.getServletContext().getRequestDispatcher( PAGE_SCORE_JSP ).forward( request, response );
        }

    }

    @Override
    protected void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {

        ScoreBean bean = new ScoreBean();
        response.sendRedirect( request.getContextPath()+PAGE_SCORE_JSP );

//        if ( bean.setScoreFromForm(request) ) {
//            response.sendRedirect( request.getContextPath()+PAGE_SCORE_JSP );
//        } else {
//            request.setAttribute( "contactBean", bean );
//            request.getServletContext().getRequestDispatcher( PAGE_SCORE_JSP ).forward( request, response );
//        }
    }
}

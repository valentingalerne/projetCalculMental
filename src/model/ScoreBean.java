package model;

import bo.Game;
import bo.User;
import dal.jdbc.GameDAO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.*;

public class ScoreBean implements Serializable {


    private static final String FORM_FIELD_NAME = "form-name";
    private static final String ATT_SESS_GAME_LIST = "gamesList";

    private User currentUser;
    private Map<Integer, Game> games;

    public ScoreBean() {
    }

    public void loadGameList( HttpServletRequest request ) {

        GameDAO gameDao = new GameDAO();
        HttpSession session = request.getSession();
        games = ( Map<Integer, Game> ) session.getAttribute(ATT_SESS_GAME_LIST);
        if ( null == games) {
            games = new HashMap<Integer, Game>();
            try {
                List<Game> temp = gameDao.findByAllTenLast();
                for (Game game : temp) {
                    games.put(game.getUser().getId(), game);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            session.setAttribute(ATT_SESS_GAME_LIST, games);
        }
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    public Map<Integer, Game> getGames() {
        return games;
    }

    public void setGames(Map<Integer, Game> games) {
        this.games = games;
    }
}

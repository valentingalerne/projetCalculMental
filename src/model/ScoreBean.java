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
    private static final String ATT_SESS_USERS_LIST = "gamesList";

    private User currentUser;
    private Map<Integer, Game> games;

    public ScoreBean() {
    }

    public void loadGameList( HttpServletRequest request ) {

        GameDAO gameDao = new GameDAO();
        HttpSession session = request.getSession();
        games = ( Map<Integer, Game> ) session.getAttribute( ATT_SESS_USERS_LIST );
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
            session.setAttribute( ATT_SESS_USERS_LIST, games);
        }
    }

//    public boolean setScoreFromForm( HttpServletRequest request ) {
//
//        String id = request.getParameter( "id" );
//        String name = request.getParameter( FORM_FIELD_NAME );
//        loadContactsList( request );
//        if ( users.containsKey( id ) ) {
//            currentUser = users.get( id );
//        } else {
//            currentUser = new User( );
//            id = UUID.randomUUID().toString();
//        }
//        currentUser.setLogin( name );
//        users.put( id , currentUser );
//        return true;
//    }

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

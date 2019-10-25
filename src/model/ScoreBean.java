package model;

import bo.User;
import dal.jdbc.UserDAO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.*;

public class ScoreBean implements Serializable {


    private static final String FORM_FIELD_NAME = "form-name";
    private static final String ATT_SESS_USERS_LIST = "usersList";

    private User currentUser;
    private Map<Integer, String> users;

    public ScoreBean() {
    }

    public void loadContactsList( HttpServletRequest request ) {

        UserDAO userDAO = new UserDAO();
        HttpSession session = request.getSession();
        users = ( Map<Integer, String> ) session.getAttribute( ATT_SESS_USERS_LIST );
        if ( null == users ) {
            users = new HashMap<Integer, String>();
            try {
                List<User> temp = userDAO.findByAll();
                for (User user : temp) {
                    users.put(user.getId(), user.getLogin());
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            session.setAttribute( ATT_SESS_USERS_LIST, users );
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

    public Map<Integer, String> getUsers() {
        return users;
    }

    public void setUsers(Map<Integer, String> users) {
        this.users = users;
    }
}

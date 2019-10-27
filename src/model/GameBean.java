package model;

import bo.Expression;
import bo.Game;
import bo.User;
import dal.jdbc.GameDAO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GameBean implements Serializable {


    private static final String FORM_FIELD_NAME = "form-name";
    private static final String ATT_SESS_GAME = "game";

    private Expression currentExpression;

    public GameBean() {
    }

    public void loadGame( HttpServletRequest request ) {

        HttpSession session = request.getSession();
        Expression expression = new Expression();
        expression.generateCalcul();

        session.setAttribute(ATT_SESS_GAME, expression);
    }

    public Expression getCurrentExpression() {
        return currentExpression;
    }

    public void setCurrentExpression(Expression currentExpression) {
        this.currentExpression = currentExpression;
    }
}

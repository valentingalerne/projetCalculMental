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

    private static final String ATT_SESS_GAME = "game";
    private static final String ATT_SESS_GAME_RESULT = "game_res";
    private static final String FORM_FIELD_RESULT = "form-result";

    private Expression currentExpression;

    public GameBean() {
    }

    public void loadGame( HttpServletRequest request ) {

        HttpSession session = request.getSession();
        Expression expression = new Expression();

        session.setAttribute(ATT_SESS_GAME, expression);
        session.setAttribute(ATT_SESS_GAME_RESULT, String.valueOf(expression.getResult()));
    }

    public boolean checkResult(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String result = request.getParameter(FORM_FIELD_RESULT);
        String expression = (String) session.getAttribute(ATT_SESS_GAME_RESULT);

        if (result.equals(expression)) {
            return true;
        } else {
            return false;
        }
    }

    public Expression getCurrentExpression() {
        return currentExpression;
    }

    public void setCurrentExpression(Expression currentExpression) {
        this.currentExpression = currentExpression;
    }
}

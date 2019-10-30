package dal.jdbc;

import bo.Game;
import bo.User;
import dal.DAOFactory;
import dal.IDAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GameDAO implements IDAO<Long, Game> {

    private static final String INSERT_QUERY = "INSERT INTO game (id_user, score) VALUES(?,?)";
    private static final String UPDATE_QUERY = "UPDATE game SET id_user = ?, score = ? WHERE id = ?";
    private static final String REMOVE_QUERY = "DELETE FROM game WHERE id = ?";
    private static final String FIND_QUERY = "SELECT * FROM game WHERE id = ?";
    private static final String FIND_ALL_QUERY = "SELECT * FROM game ORDER BY score DESC";
    private static final String FIND_ALL_QUERY_LAST = "SELECT * FROM game ORDER BY score ASC LIMIT 10";

    @Override
    public void create(Game game) throws SQLException {
        Connection connection = DAOFactory.getJDBCConnection();
        if (connection != null) {
            try (PreparedStatement ps = connection
                    .prepareStatement(INSERT_QUERY, Statement.RETURN_GENERATED_KEYS)) {
                ps.setInt(1, game.getUser().getId());
                ps.setInt(2, game.getScore());
                ps.executeUpdate();
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) {
                        game.setId(rs.getInt(1));
                    }
                }
            }
        }
    }

    @Override
    public void update(Game game) throws SQLException {
        Connection connection = DAOFactory.getJDBCConnection();
        if (connection != null) {
            try (PreparedStatement ps = connection.prepareStatement(UPDATE_QUERY)) {
                ps.setInt(1, game.getUser().getId());
                ps.setInt(2, game.getScore());
                ps.setInt(3, game.getId());
                ps.executeUpdate();
            }
        }
    }

    @Override
    public void delete(Game game) throws SQLException {
        Connection connection = DAOFactory.getJDBCConnection();
        if (connection != null) {
            try (PreparedStatement ps = connection.prepareStatement(REMOVE_QUERY)) {
                ps.setInt(1, game.getId());
                ps.executeUpdate();
            }
        }
    }

    @Override
    public Game findById(Long aLong) throws SQLException {
        int id = Math.toIntExact(aLong);
        Game game = null;
        Connection connection = DAOFactory.getJDBCConnection();
        if (connection != null) {
            try (PreparedStatement ps = connection.prepareStatement(FIND_QUERY)) {
                ps.setInt(1, id);
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        game = new Game();
                        game.setId(rs.getInt("id"));
                        game.setScore(rs.getInt("score"));

                        UserDAO userDAO = new UserDAO();
                        User user = userDAO.findById((long) rs.getInt("id_user"));
                        game.setUser(user);
                    }
                }
            }
        }
        return game;
    }

    @Override
    public List<Game> findByAll() throws SQLException {
        List<Game> list = new ArrayList<>();
        Connection connection = DAOFactory.getJDBCConnection();
        if (connection != null) {
            try (PreparedStatement ps = connection.prepareStatement(FIND_ALL_QUERY)) {
                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        Game game = new Game();
                        game.setId(rs.getInt("id"));
                        game.setScore(rs.getInt("score"));

                        UserDAO userDAO = new UserDAO();
                        User user = userDAO.findById((long) rs.getInt("id_user"));
                        game.setUser(user);

                        list.add(game);
                    }
                }
            }
        }
        return list;
    }


    public List<Game> findByAllTenLast() throws SQLException {
        List<Game> list = new ArrayList<>();
        Connection connection = DAOFactory.getJDBCConnection();
        if (connection != null) {
            try (PreparedStatement ps = connection.prepareStatement(FIND_ALL_QUERY_LAST)) {
                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        Game game = new Game();
                        game.setId(rs.getInt("id"));
                        game.setScore(rs.getInt("score"));

                        UserDAO userDAO = new UserDAO();
                        User user = userDAO.findById((long) rs.getInt("id_user"));
                        game.setUser(user);

                        list.add(game);
                    }
                }
            }
        }
        return list;
    }
}

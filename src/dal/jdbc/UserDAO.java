package dal.jdbc;

import bo.Game;
import bo.User;
import dal.DAOFactory;
import dal.IUserDAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO implements IUserDAO<Long, User> {

	private static final String AUTHENT_QUERY = "SELECT * FROM user WHERE login = ? AND password = ?";
	private static final String INSERT_QUERY = "INSERT INTO user (login, password, connections) VALUES(?,?,?)";
	private static final String UPDATE_QUERY = "UPDATE user SET login = ?, password = ?, connections = ? WHERE id = ?";
	private static final String REMOVE_QUERY = "DELETE FROM user WHERE id = ?";
	private static final String FIND_QUERY = "SELECT * FROM user WHERE id = ?";
	private static final String FIND_ALL_QUERY = "SELECT * FROM user";
	private static final String FIND_ALL_GAME = "SELECT * FROM game WHERE id_user = ?";
	private static final String FIND_BEST_GAME = "SELECT game.*, user.id AS user FROM game INNER JOIN user ON game.id_user = user.id WHERE id = ? ORDER BY score LIMIT 1";

	@Override
	public User authenticate(String login, String password ) throws SQLException {
		Connection connection = DAOFactory.getJDBCConnection();
		User user = null;
		if ( null != connection ) {
			try ( PreparedStatement ps = connection
					.prepareStatement( AUTHENT_QUERY, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE ) ) {
				ps.setString( 1, login );
				ps.setString( 2, password );
				try ( ResultSet rs = ps.executeQuery() ) {
					if ( rs.next() ) {
						int nbConnections = rs.getInt( "connections" ) + 1;
						rs.updateInt( "connections", nbConnections );
						rs.updateRow();
						user = new User();
						user.setId( rs.getInt( "id" ) );
						user.setLogin( rs.getString( "login" ) );
						user.setPassword( rs.getString( "password" ) );
						user.setConnections( nbConnections );
					}
				}
			}
		}
		return user;
	}

	@Override
	public void create( User user ) throws SQLException {
		Connection connection = DAOFactory.getJDBCConnection();
		if (connection != null) {
			try (PreparedStatement ps = connection
					.prepareStatement(INSERT_QUERY, Statement.RETURN_GENERATED_KEYS)) {
				ps.setString(2, user.getLogin());
				ps.setString(3, user.getPassword());
				ps.setInt(4, user.getConnections());
				ps.executeUpdate();
				try (ResultSet rs = ps.getGeneratedKeys()) {
					if (rs.next()) {
						user.setId(rs.getInt(1));
					}
				}
			}
		}
	}

	@Override
	public void update( User user ) throws SQLException {
		Connection connection = DAOFactory.getJDBCConnection();
		if (connection != null) {
			try (PreparedStatement ps = connection.prepareStatement(UPDATE_QUERY)) {
				ps.setString(1, user.getLogin());
				ps.setString(2, user.getPassword());
				ps.setInt(3, user.getConnections());
				ps.setInt(4, user.getId());
				ps.executeUpdate();
			}
		}
	}

	@Override
	public void delete( User user ) throws SQLException {
		Connection connection = DAOFactory.getJDBCConnection();
		if (connection != null) {
			try (PreparedStatement ps = connection.prepareStatement(REMOVE_QUERY)) {
				ps.setInt(1, user.getId());
				ps.executeUpdate();
			}
		}
	}

	@Override
	public User findById( Long aLong ) throws SQLException {
		int id = Math.toIntExact(aLong);
		User user = null;
		Connection connection = DAOFactory.getJDBCConnection();
		if (connection != null) {
			try (PreparedStatement ps = connection.prepareStatement(FIND_QUERY)) {
				ps.setInt(1, id);
				try (ResultSet rs = ps.executeQuery()) {
					if (rs.next()) {
						user = new User();
						user.setId(rs.getInt("id"));
						user.setLogin(rs.getString("login"));
						user.setPassword(rs.getString("password"));
						user.setConnections(rs.getInt("connections"));
					}
				}
			}
		}
		return user;
	}

	@Override
	public List<User> findByAll() throws SQLException {
		List<User> list = new ArrayList<>();
		Connection connection = DAOFactory.getJDBCConnection();
		if (connection != null) {
			try (PreparedStatement ps = connection.prepareStatement(FIND_ALL_QUERY)) {
				try (ResultSet rs = ps.executeQuery()) {
					while (rs.next()) {
						User user = new User();
						user.setId(rs.getInt("id"));
						user.setLogin(rs.getString("login"));
						user.setPassword(rs.getString("password"));
						user.setConnections(rs.getInt("connections"));

						list.add(user);
					}
				}
			}
		}
		return list;
	}

	public Game findBestGame(Integer id) throws SQLException {
		Game game = null;
		Connection connection = DAOFactory.getJDBCConnection();
		if (connection != null) {
			try (PreparedStatement ps = connection.prepareStatement(FIND_BEST_GAME)) {
				ps.setInt(1, id);
				try (ResultSet rs = ps.executeQuery()) {
					while (rs.next()) {
						game = new Game();
						game.setId(rs.getInt("id"));
						game.setScore(rs.getInt("score"));
						game.setUser(this.findById((long) rs.getInt("id_user")));
					}
				}
			}
		}
		return game;
	}

	public List<Game> findAllGame(Integer id) throws SQLException {
		List<Game> list = new ArrayList<>();
		Connection connection = DAOFactory.getJDBCConnection();
		if (connection != null) {
			try (PreparedStatement ps = connection.prepareStatement(FIND_ALL_GAME)) {
				ps.setInt(1, id);
				try (ResultSet rs = ps.executeQuery()) {
					while (rs.next()) {
						Game game = new Game();
						game.setId(rs.getInt("id"));
						game.setScore(rs.getInt("score"));
						game.setUser(this.findById((long) rs.getInt("id_user")));

						list.add(game);
					}
				}
			}
		}
		return list;
	}
}
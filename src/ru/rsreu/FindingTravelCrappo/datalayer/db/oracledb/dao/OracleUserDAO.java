package ru.rsreu.FindingTravelCrappo.datalayer.db.oracledb.dao;

import ru.rsreu.FindingTravelCrappo.datalayer.db.dao.UserDAO;
import ru.rsreu.FindingTravelCrappo.datalayer.db.oracledb.OracleConnectionFactory;
import ru.rsreu.FindingTravelCrappo.utils.ResourcerManager;
import ru.rsreu.FindingTravelCrappo.datalayer.db.DAOException;
import ru.rsreu.FindingTravelCrappo.datalayer.data.User;
import ru.rsreu.FindingTravelCrappo.datalayer.data.Gender;
import ru.rsreu.FindingTravelCrappo.datalayer.data.Role;

import java.util.List;
import java.util.Locale;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OracleUserDAO implements UserDAO {

	private OracleConnectionFactory connectionFactory = OracleConnectionFactory.getInstance();

	@Override
	public boolean userIsExists(String login, String password) {

		return false;
	}

	@Override
	public List<User> getUsers() throws DAOException {
		String query = ResourcerManager.getString("query.users.get");

		List<User> usersList = new ArrayList<>();

		try (Connection connection = connectionFactory.getConnection();
				Statement statement = connection.createStatement()) {

			ResultSet resultSet = statement.executeQuery(query);

			while (resultSet.next()) {
				int id = resultSet.getInt("ID");
				String login = resultSet.getString("LOGIN");
				String password = resultSet.getString("PASSWORD");
				Role role = Role.valueOf(resultSet.getString("ROLE"));
				Gender gender = Gender.valueOf(resultSet.getString("GENDER"));
				boolean status = resultSet.getInt("BLOCK_STATUS") == 1;
				int car = resultSet.getInt("CAR");
				String phone = resultSet.getString("PHONE");
				String name = resultSet.getString("NAME");
				boolean isAuthorized = resultSet.getInt("ISAUTHORIZED") == 1;
				boolean deleteStattus = resultSet.getInt("DELETE_STATUS") == 1;
				usersList.add(new User(id, login, password, role, gender, car, status, phone, name, isAuthorized, deleteStattus));

			}
		} catch (SQLException e) {
			System.err.println(e);
			throw new DAOException();
		}
		return usersList;
	}

	@Override
	public void addUser(User user) throws DAOException {
		String query = ResourcerManager.getString("query.user.add");

		try (Connection connection = connectionFactory.getConnection();
				PreparedStatement statement = connection.prepareStatement(query)) {
			statement.setString(1, user.getLogin());
			statement.setString(2, user.getPassword());
			statement.setInt(3, user.getRole().equals(Role.valueOf("Admin")) ? 3
					: user.getRole().equals(Role.valueOf("Moderator")) ? 2 : 1); // role
			statement.setInt(4, user.getGender().equals(Gender.valueOf("None")) ? 3
					: user.getGender().equals(Gender.valueOf("Women")) ? 2 : 1); // gender
			statement.setInt(5, user.getIdCar());
			statement.setInt(6, user.getStatus() ? 1 : 0);
			statement.setString(7, user.getPhone());
			statement.setString(8, user.getName());
			statement.setInt(9, user.isAuthorized() ? 1 : 0);
			statement.setInt(10, 0);

			statement.executeQuery();

		} catch (SQLException e) {
			System.err.println(e);
			throw new DAOException();
		}
	}

	@Override
	public void login(User user) throws DAOException {
		changeAutorizidedStatus(user, true);

	}

	@Override
	public void logout(User user) throws DAOException {
		changeAutorizidedStatus(user, false);

	}

	private void changeAutorizidedStatus(User user, boolean status) throws DAOException {
		String query = ResourcerManager.getString("query.user.change.auth.status");
		try (Connection connection = connectionFactory.getConnection();
				PreparedStatement statement = connection.prepareStatement(query)) {
			statement.setInt(1, status ? 1 : 0);
			statement.setInt(2, user.getId());
			statement.executeUpdate();

		} catch (SQLException e) {
			System.err.println(e);
			throw new DAOException();
		}

	}

	@Override
	public User getUserById(int id) throws DAOException {
		String query = ResourcerManager.getString("query.user.getbyid");
		
		User user = null;

		try (Connection connection = connectionFactory.getConnection();
				PreparedStatement statement = connection.prepareStatement(query)) {

			statement.setInt(1, id);
			ResultSet resultSet = statement.executeQuery();

			while (resultSet.next()) {
				String login = resultSet.getString("LOGIN");
				String password = resultSet.getString("PASSWORD");
				Role role = Role.valueOf(resultSet.getString("ROLE"));
				Gender gender = Gender.valueOf(resultSet.getString("GENDER"));
				boolean status = resultSet.getInt("BLOCK_STATUS") == 1;
				int car = resultSet.getInt("CAR");
				String phone = resultSet.getString("PHONE");
				String name = resultSet.getString("NAME");
				boolean isAuthorized = resultSet.getInt("ISAUTHORIZED") == 1;
                boolean deleteStattus = resultSet.getInt("DELETE_STATUS") == 1;
				user = new User(id, login, password, role, gender, car, status, phone, name, isAuthorized, deleteStattus);

			}
		} catch (SQLException e) {
			System.err.println(e);
			throw new DAOException();
		}
		return user;
	}

	@Override
	public void deleteUserById(int id) throws DAOException {
		String query = ResourcerManager.getString("query.user.delete");
		try (Connection connection = connectionFactory.getConnection();
				PreparedStatement statement = connection.prepareStatement(query)) {

			statement.setInt(1, id);
			statement.executeQuery();

		} catch (SQLException e) {
			System.err.println(e);
			throw new DAOException();
		}
	}

	@Override
	public void changeUserStatus(int id, boolean status) throws DAOException {
		String query = ResourcerManager.getString("query.user.change.block.status");

		try (Connection connection = connectionFactory.getConnection();
				Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
						ResultSet.CONCUR_UPDATABLE)) {

			ResultSet resultSet = statement.executeQuery(query);
			resultSet.beforeFirst();
			while (resultSet.next()) {
				if (id == resultSet.getInt("ID")) {
					resultSet.updateInt("BLOCK_STATUS", status ? 1 : 0);
				}
				resultSet.updateRow();
			}

		} catch (SQLException e) {
			System.err.println(e);
			throw new DAOException();
		}

	}

	@Override
	public User getUserByLogin(String login) throws DAOException {
		Locale.setDefault(Locale.ENGLISH);
		String query = ResourcerManager.getString("query.user.getbylogin");
		User user = null;

		try (Connection connection = connectionFactory.getConnection();
				PreparedStatement statement = connection.prepareStatement(query)) {
			statement.setString(1, login);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				int id = resultSet.getInt("ID");
				String password = resultSet.getString("PASSWORD");
				Role role = Role.valueOf(resultSet.getString("ROLE"));
				Gender gender = Gender.valueOf(resultSet.getString("GENDER"));
				boolean status = resultSet.getInt("BLOCK_STATUS") == 1;
				int car = resultSet.getInt("CAR");
				String phone = resultSet.getString("PHONE");
				String name = resultSet.getString("NAME");
				boolean isAuthorized = resultSet.getInt("ISAUTHORIZED") == 1;
                boolean deleteStattus = resultSet.getInt("DELETE_STATUS") == 1;
				user = new User(id, login, password, role, gender, 1, status, phone, name, isAuthorized, deleteStattus);

			}
		} catch (SQLException e) {
			System.err.println(e);
			throw new DAOException();
		}
		return user;
	}

	public List<User> getBlockedUsers() throws DAOException {
		String query = ResourcerManager.getString("query.user.get.blocked");

		List<User> usersList = new ArrayList<>();
		try (Connection connection = connectionFactory.getConnection();
				Statement statement = connection.createStatement()) {
			ResultSet resultSet = statement.executeQuery(query);

			while (resultSet.next()) {
				int id = resultSet.getInt("ID");
				String login = resultSet.getString("LOGIN");
				String password = resultSet.getString("PASSWORD");
				Role role = Role.valueOf(resultSet.getString("ROLE"));
				Gender gender = Gender.valueOf(resultSet.getString("GENDER"));
				boolean status = resultSet.getInt("BLOCK_STATUS") == 1;
				int car = resultSet.getInt("CAR");
				String phone = resultSet.getString("PHONE");
				String name = resultSet.getString("NAME");
				boolean isAuthorized = resultSet.getInt("ISAUTHORIZED") == 1;
				boolean deleteStattus = resultSet.getInt("DELETE_STATUS") == 1;
				usersList.add(new User(id, login, password, role, gender, 1, status, phone, name, isAuthorized, deleteStattus));

			}
		} catch (SQLException e) {
			System.err.println(e);
			throw new DAOException();
		}
		return usersList;
	}

	public int countUsers() throws DAOException {
		String query = ResourcerManager.getString("query.user.count");

		int count = 0;

		try (Connection connection = connectionFactory.getConnection();
				Statement statement = connection.createStatement()) {

			ResultSet resultSet = statement.executeQuery(query);

			while (resultSet.next()) {
				count = resultSet.getInt("COUNT");
			}
		} catch (SQLException e) {
			System.err.println(e);
			throw new DAOException();
		}
		return count;
	}
}
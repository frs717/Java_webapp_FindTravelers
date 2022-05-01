package ru.rsreu.FindingTravelCrappo.datalayer.db.oracledb.dao;

import java.util.List;

import ru.rsreu.FindingTravelCrappo.datalayer.data.Passenger;
import ru.rsreu.FindingTravelCrappo.datalayer.db.dao.PassengerDAO;

import ru.rsreu.FindingTravelCrappo.datalayer.db.oracledb.OracleConnectionFactory;
import ru.rsreu.FindingTravelCrappo.datalayer.db.DAOException;
import ru.rsreu.FindingTravelCrappo.utils.ResourcerManager;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class OraclePassangerDAO implements PassengerDAO {
	private OracleConnectionFactory connectionFactory = OracleConnectionFactory.getInstance();

	@Override
	public List<Passenger> getPassengersByTravellingId(int id) throws DAOException {
		String query = ResourcerManager.getString("query.passengers.bytravel");
		List<Passenger> passengerList = new ArrayList<>();
		try (Connection connection = connectionFactory.getConnection();
				PreparedStatement statement = connection.prepareStatement(query)) {
			statement.setInt(1, id);
			ResultSet resultSet = statement.executeQuery();

			while (resultSet.next()) {
				int idPassenger = resultSet.getInt("ID");
				int idUser = resultSet.getInt("ID_USER");
				int idTravel = resultSet.getInt("ID_TRAVEL");
				boolean status = resultSet.getInt("APPROVED") == 1;
				passengerList.add(new Passenger(idPassenger, idUser, idTravel, status));
			}
		} catch (SQLException e) {
			System.err.println(e);
			throw new DAOException();
		}
		return passengerList;
	}

	@Override
	public boolean isExists(int idUser, int idTravel) throws DAOException {
		String query = ResourcerManager.getString("query.passengers.exists");
		int count = 0;
		try (Connection connection = connectionFactory.getConnection();
				PreparedStatement statement = connection.prepareStatement(query)) {
			statement.setInt(1, idUser);
			statement.setInt(2, idTravel);
			ResultSet resultSet = statement.executeQuery();

			while (resultSet.next()) {
				count = resultSet.getInt("COUNT");
			}
		} catch (SQLException e) {
			System.err.println(e);
			throw new DAOException();
		}
		return count > 0;
	}

	@Override
	public boolean getStatusPassengerByTravelID(int idUser, int idTravel) throws DAOException {
		String query = ResourcerManager.getString("query.passengers.getstatus");
		boolean status = false;
		try (Connection connection = connectionFactory.getConnection();
				PreparedStatement statement = connection.prepareStatement(query)) {
			statement.setInt(1, idUser);
			statement.setInt(2, idTravel);
			ResultSet resultSet = statement.executeQuery();

			while (resultSet.next()) {
				// status = resultSet.getInt("APPROVED") == 1;
				status = true;

			}
		} catch (SQLException e) {
			System.err.println(e);
			throw new DAOException();
		}
		return status;
	}

	public void deletePassengersByTravelId(int id) throws DAOException {
		String query = ResourcerManager.getString("query.passengers.deletebytravel");
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
	public void join(int idUser, int idTravel) throws DAOException {
		String query = ResourcerManager.getString("query.passengers.join");
		try (Connection connection = connectionFactory.getConnection();
				PreparedStatement statement = connection.prepareStatement(query)) {

			statement.setInt(1, idUser);
			statement.setInt(2, idTravel);
			statement.executeQuery();

		} catch (SQLException e) {
			System.err.println(e);
			throw new DAOException();
		}
	}

	@Override
	public void leave(int idUser, int idTravel) throws DAOException {
		String query = ResourcerManager.getString("query.passengers.leave");
		try (Connection connection = connectionFactory.getConnection();
				PreparedStatement statement = connection.prepareStatement(query)) {

			statement.setInt(1, idUser);
			statement.setInt(2, idTravel);
			statement.executeQuery();

		} catch (SQLException e) {
			System.err.println(e);
			throw new DAOException();
		}
	}

	@Override
	public void accept(int id) throws DAOException {
		changeUserStatus(id, true);
	}

	@Override
	public void reject(int id) throws DAOException {
		changeUserStatus(id, false);
	}

	private void changeUserStatus(int id, boolean status) throws DAOException {
		String query = ResourcerManager.getString("query.passengers.changestatus");
		try (Connection connection = connectionFactory.getConnection();
				PreparedStatement statement = connection.prepareStatement(query)) {
			statement.setInt(1, status ? 1 : 0);
			statement.setInt(2, id);
			statement.executeQuery();

		} catch (SQLException e) {
			System.err.println(e);
			throw new DAOException();
		}
	}

	public void leaveAll(int idUser) throws DAOException {
		String query = ResourcerManager.getString("query.passengers.leaveall");
		try (Connection connection = connectionFactory.getConnection();
				PreparedStatement statement = connection.prepareStatement(query)) {

			statement.setInt(1, idUser);
			statement.executeQuery();

		} catch (SQLException e) {
			System.err.println(e);
			throw new DAOException();
		}

	}

}

package ru.rsreu.FindingTravelCrappo.datalayer.db.oracledb.dao;

import ru.rsreu.FindingTravelCrappo.datalayer.data.Travel;
import ru.rsreu.FindingTravelCrappo.datalayer.db.dao.TravelDAO;
import ru.rsreu.FindingTravelCrappo.datalayer.db.oracledb.OracleConnectionFactory;
import ru.rsreu.FindingTravelCrappo.utils.ResourcerManager;
import ru.rsreu.FindingTravelCrappo.datalayer.db.DAOException;

import java.sql.Timestamp;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import java.sql.Timestamp;

public class OracleTravelDAO implements TravelDAO {
	private OracleConnectionFactory connectionFactory = OracleConnectionFactory.getInstance();

	@Override
	public Travel getTravelById(int id) throws DAOException {
		String query = ResourcerManager.getString("query.travels.byid");

		Travel travel = null;

		try (Connection connection = connectionFactory.getConnection();
				PreparedStatement statement = connection.prepareStatement(query)) {

			statement.setInt(1, id);
			ResultSet resultSet = statement.executeQuery();

			while (resultSet.next()) {
				int idDriver = resultSet.getInt("DRIVER");
				int maxFreePlaces = resultSet.getInt("MAX_FREE_PLACES");
				Timestamp date = resultSet.getTimestamp("DATE_TRAVELS");
				int sendingPlace = resultSet.getInt("SENDING_PLACE");
				int arrivalPlace = resultSet.getInt("ARRIVAL_PLACE");
				boolean activeStatus = resultSet.getInt("ACTIVE_STATUS") == 1;

				travel = new Travel(id, idDriver, date, sendingPlace, arrivalPlace, activeStatus, maxFreePlaces);

			}
		} catch (SQLException e) {
			System.err.println(e);
			throw new DAOException();
		}
		return travel;
	}

	@Override
	public void addTravel(Travel travel) throws DAOException {
		String query = ResourcerManager.getString("query.travels.add");

		try (Connection connection = connectionFactory.getConnection();
				PreparedStatement statement = connection.prepareStatement(query)) {

			statement.setInt(1, travel.getIdDriver());
			statement.setTimestamp(2, travel.getDate());
			statement.setInt(3, travel.getSendingPlace());
			statement.setInt(4, travel.getArrivalPlace());
			statement.setInt(5, travel.getActiveStatus() ? 1 : 0);
			statement.setInt(6, travel.getMaxFreePlaces());
			statement.executeQuery();

		} catch (SQLException e) {
			System.err.println(e);
			throw new DAOException();
		}
	}

	@Override
	public void finishTravelById(int id) throws DAOException {
		String query = ResourcerManager.getString("query.travels.finish");
		try (Connection connection = connectionFactory.getConnection();
				PreparedStatement statement = connection.prepareStatement(query)) {

			statement.setInt(1, 0);
			statement.setInt(2, id);
			statement.executeUpdate();

		} catch (SQLException e) {
			System.err.println(e);
			throw new DAOException();
		}
	}

	@Override
	public void deleteTravelById(int id) throws DAOException {
		String query = ResourcerManager.getString("query.travels.delete");
		try (Connection connection = connectionFactory.getConnection();
				PreparedStatement statement = connection.prepareStatement(query)) {

			statement.setInt(1, id);
			statement.execute();

		} catch (SQLException e) {
			System.err.println(e);
			throw new DAOException();
		}
	}

	@Override
	public List<Travel> getActiveTravels() throws DAOException {
		String query = ResourcerManager.getString("query.travels.active");
		List<Travel> travelsList = new ArrayList<>();

		try (Connection connection = connectionFactory.getConnection();
				Statement statement = connection.createStatement()) {
			ResultSet resultSet = statement.executeQuery(query);

			while (resultSet.next()) {
				int id = resultSet.getInt("ID");
				int maxFreePlaces = resultSet.getInt("MAX_FREE_PLACES");
				int idDriver = resultSet.getInt("DRIVER");
				Timestamp date = resultSet.getTimestamp("DATE_TRAVELS");
				int sendingPlace = resultSet.getInt("SENDING_PLACE");
				int arrivalPlace = resultSet.getInt("ARRIVAL_PLACE");
				boolean activeStatus = resultSet.getInt("ACTIVE_STATUS") == 1;
				travelsList
						.add(new Travel(id, idDriver, date, sendingPlace, arrivalPlace, activeStatus, maxFreePlaces));

			}
		} catch (SQLException e) {
			System.err.println(e);
			throw new DAOException();
		}
		return travelsList;
	}

	@Override
	public List<Travel> getTravelsByPassenger(int id) throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Travel> getTravelsByDriver(int idDriver) throws DAOException {
		String query = ResourcerManager.getString("query.travels.bydriver");
		List<Travel> travelsList = new ArrayList<>();

		try (Connection connection = connectionFactory.getConnection();
				PreparedStatement statement = connection.prepareStatement(query)) {

			statement.setInt(1, idDriver);
			ResultSet resultSet = statement.executeQuery();

			while (resultSet.next()) {
				int id = resultSet.getInt("ID");
				int maxFreePlaces = resultSet.getInt("MAX_FREE_PLACES");
				Timestamp date = resultSet.getTimestamp("DATE_TRAVELS");
				int sendingPlace = resultSet.getInt("SENDING_PLACE");
				int arrivalPlace = resultSet.getInt("ARRIVAL_PLACE");
				boolean activeStatus = resultSet.getInt("ACTIVE_STATUS") == 1;

				travelsList
						.add(new Travel(id, idDriver, date, sendingPlace, arrivalPlace, activeStatus, maxFreePlaces));

			}
		} catch (SQLException e) {
			System.err.println(e);
			throw new DAOException();
		}
		return travelsList;
	}

	@Override
	public int getDriverByTravelId(int id) throws DAOException {
		String query = ResourcerManager.getString("query.travels.getdriver");
		int driver = 0;

		try (Connection connection = connectionFactory.getConnection();
				PreparedStatement statement = connection.prepareStatement(query)) {

			statement.setInt(1, id);
			ResultSet resultSet = statement.executeQuery();

			while (resultSet.next()) {
				driver = resultSet.getInt("DRIVER");

			}
		} catch (SQLException e) {
			System.err.println(e);
			throw new DAOException();
		}
		return driver;
	}

	@Override
	public List<Travel> findTravels(int sendingPlaceID, int arrivalPlaceID, Timestamp date) throws DAOException {
		String query = ResourcerManager.getString("query.travels.find");
		List<Travel> travelsList = new ArrayList<>();

		try (Connection connection = connectionFactory.getConnection();
				PreparedStatement statement = connection.prepareStatement(query)) {

			statement.setInt(1, sendingPlaceID);
			statement.setInt(2, arrivalPlaceID);
			ResultSet resultSet = statement.executeQuery();

			while (resultSet.next()) {
				int id = resultSet.getInt("ID");
				int maxFreePlaces = resultSet.getInt("MAX_FREE_PLACES");
				int idDriver = resultSet.getInt("DRIVER");
				Timestamp sqlDate = resultSet.getTimestamp("DATE_TRAVELS");
				boolean activeStatus = resultSet.getInt("ACTIVE_STATUS") == 1;
				if (sqlDate.getDate() == (date.getDate())) {
					travelsList.add(new Travel(id, idDriver, date, sendingPlaceID, arrivalPlaceID, activeStatus,
							maxFreePlaces));
				}
			}
		} catch (Exception e) {
			System.err.println(e);
			throw new DAOException();
		}
		return travelsList;
	}

	public int countTravels() throws DAOException {
		String query = ResourcerManager.getString("query.travels.count");
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

	public List<Travel> getTravels() throws DAOException {
		String query = ResourcerManager.getString("query.travels.all");
		List<Travel> travelsList = new ArrayList<>();

		try (Connection connection = connectionFactory.getConnection();
				Statement statement = connection.createStatement()) {

			ResultSet resultSet = statement.executeQuery(query);

			while (resultSet.next()) {
				int id = resultSet.getInt("ID");
				int maxFreePlaces = resultSet.getInt("MAX_FREE_PLACES");
				int idDriver = resultSet.getInt("DRIVER");
				Timestamp date = resultSet.getTimestamp("DATE_TRAVELS");
				int sendingPlace = resultSet.getInt("SENDING_PLACE");
				int arrivalPlace = resultSet.getInt("ARRIVAL_PLACE");
				boolean activeStatus = resultSet.getInt("ACTIVE_STATUS") == 1;
				travelsList
						.add(new Travel(id, idDriver, date, sendingPlace, arrivalPlace, activeStatus, maxFreePlaces));

			}
			statement.close();
		} catch (SQLException e) {
			System.err.println(e);
			throw new DAOException();
		}
		return travelsList;
	}
}

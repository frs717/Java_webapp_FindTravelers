package ru.rsreu.FindingTravelCrappo.datalayer.db.oracledb.dao;

import ru.rsreu.FindingTravelCrappo.datalayer.data.Place;
import ru.rsreu.FindingTravelCrappo.datalayer.db.dao.PlaceDAO;

import ru.rsreu.FindingTravelCrappo.datalayer.db.oracledb.OracleConnectionFactory;
import ru.rsreu.FindingTravelCrappo.datalayer.db.DAOException;
import ru.rsreu.FindingTravelCrappo.utils.ResourcerManager;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OraclePlaceDAO implements PlaceDAO {
	private OracleConnectionFactory connectionFactory = OracleConnectionFactory.getInstance();

	@Override
	public Place getPlaceById(int id) throws DAOException {
		String query = ResourcerManager.getString("query.places.byid");
		Place place = null;

		try (Connection connection = connectionFactory.getConnection();
				PreparedStatement statement = connection.prepareStatement(query)) {

			statement.setInt(1, id);
			ResultSet resultSet = statement.executeQuery();

			while (resultSet.next()) {
				String city = resultSet.getString("CITY");
				String state = resultSet.getString("STATE");

				place = new Place(id, city, state);

			}
		} catch (SQLException e) {
			System.err.println(e);
			throw new DAOException();
		}
		return place;
	}

	@Override
	public List<Place> getPlacesList() throws DAOException {
		String query = ResourcerManager.getString("query.places.all");
		List<Place> placesList = new ArrayList<>();

		try (Connection connection = connectionFactory.getConnection();
				Statement statement = connection.createStatement()) {

			ResultSet resultSet = statement.executeQuery(query);

			while (resultSet.next()) {
				int id = resultSet.getInt("ID");
				String city = resultSet.getString("CITY");
				String state = resultSet.getString("STATE");
				placesList.add(new Place(id, city, state));
			}
		} catch (SQLException e) {
			System.err.println(e);
			throw new DAOException();
		}
		return placesList;
	}
}

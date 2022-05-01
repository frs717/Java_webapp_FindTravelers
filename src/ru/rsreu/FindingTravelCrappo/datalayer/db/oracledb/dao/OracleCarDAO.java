package ru.rsreu.FindingTravelCrappo.datalayer.db.oracledb.dao;

import java.util.List;

import ru.rsreu.FindingTravelCrappo.datalayer.data.Car;
import ru.rsreu.FindingTravelCrappo.datalayer.db.dao.CarDAO;
import ru.rsreu.FindingTravelCrappo.datalayer.db.oracledb.OracleConnectionFactory;
import ru.rsreu.FindingTravelCrappo.datalayer.db.DAOException;
import ru.rsreu.FindingTravelCrappo.utils.ResourcerManager;

import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class OracleCarDAO implements CarDAO {
	private OracleConnectionFactory connectionFactory = OracleConnectionFactory.getInstance();

	@Override
	public Car getCarByIdUser(int idUser) throws DAOException {
		String query = ResourcerManager.getString("query.cars.byuser");
		Car car = null;
		try (Connection connection = connectionFactory.getConnection();
				PreparedStatement statement = connection.prepareStatement(query)) {

			statement.setInt(1, idUser);
			ResultSet resultSet = statement.executeQuery();

			while (resultSet.next()) {
				int id = resultSet.getInt("ID");
				String brand = resultSet.getString("BRAND");
				String numbers = resultSet.getString("NUMBERS");
				String color = resultSet.getString("COLOR");
				car = new Car(id, brand, numbers, color);
			}
		} catch (SQLException e) {
			System.err.println(e);
			throw new DAOException();
		}
		return car;
	}

	@Override
	public void addCar(Car car) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteCarById(int id) {
		// TODO Auto-generated method stub

	}
}

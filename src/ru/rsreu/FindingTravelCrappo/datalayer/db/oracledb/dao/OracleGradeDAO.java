package ru.rsreu.FindingTravelCrappo.datalayer.db.oracledb.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ru.rsreu.FindingTravelCrappo.datalayer.data.Grade;
import ru.rsreu.FindingTravelCrappo.datalayer.data.Passenger;
import ru.rsreu.FindingTravelCrappo.datalayer.db.DAOException;
import ru.rsreu.FindingTravelCrappo.datalayer.db.dao.GradeDAO;
import ru.rsreu.FindingTravelCrappo.datalayer.db.oracledb.OracleConnectionFactory;
import ru.rsreu.FindingTravelCrappo.utils.ResourcerManager;

public class OracleGradeDAO implements GradeDAO {

	private OracleConnectionFactory connectionFactory = OracleConnectionFactory.getInstance();

	@Override
	public void grade(int idUser, int idTravel, int mark) throws DAOException {
		String query = ResourcerManager.getString("query.grades.add");
		try (Connection connection = connectionFactory.getConnection();
				PreparedStatement statement = connection.prepareStatement(query)) {
			statement.setInt(1, idUser);
			statement.setInt(2, idTravel);
			statement.setInt(3, mark);

			statement.executeQuery();

		} catch (SQLException e) {
			System.err.println(e);
			throw new DAOException();
		}
	}

	@Override
	public boolean checkUser(int idTravel, int idUser) throws DAOException {
		String query = ResourcerManager.getString("query.grades.checkuser");
		int count = 0;
		try (Connection connection = connectionFactory.getConnection();
				PreparedStatement statement = connection.prepareStatement(query)) {
			statement.setInt(1, idUser);
			statement.setInt(2, idTravel);
			ResultSet resultSet = statement.executeQuery();

			while (resultSet.next()) {
				count = resultSet.getInt("count");
			}
		} catch (SQLException e) {
			System.err.println(e);
			throw new DAOException();
		}
		return count > 0;
	}

	@Override
	public List<Grade> getGrades(int idTravel) throws DAOException {
		String query = ResourcerManager.getString("query.grades.bytravel");
		List<Grade> gradesList = new ArrayList<>();
		try (Connection connection = connectionFactory.getConnection();
				PreparedStatement statement = connection.prepareStatement(query)) {

			statement.setInt(1, idTravel);
			ResultSet resultSet = statement.executeQuery();

			while (resultSet.next()) {
				int id = resultSet.getInt("ID");
				int idUser = resultSet.getInt("ID_USER");
				int mark = resultSet.getInt("MARK");
				gradesList.add(new Grade(id, idUser, idTravel, mark));
			}
		} catch (SQLException e) {
			System.err.println(e);
			throw new DAOException();
		}

		return gradesList;
	}

	@Override
	public float getAverage(int idTravel) throws DAOException {
		List<Grade> gradesList = getGrades(idTravel);
		float avg = 0;
		for (Grade grade : gradesList){
		    avg += grade.getMark();
		}
		avg /= gradesList.size();
		
		return avg;
	}

}

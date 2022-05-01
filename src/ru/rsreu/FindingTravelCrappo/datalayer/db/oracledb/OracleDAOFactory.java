package ru.rsreu.FindingTravelCrappo.datalayer.db.oracledb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Locale;

import ru.rsreu.FindingTravelCrappo.datalayer.db.DAOFactory;
import ru.rsreu.FindingTravelCrappo.datalayer.db.dao.CarDAO;
import ru.rsreu.FindingTravelCrappo.datalayer.db.dao.GradeDAO;
import ru.rsreu.FindingTravelCrappo.datalayer.db.dao.PassengerDAO;
import ru.rsreu.FindingTravelCrappo.datalayer.db.dao.PlaceDAO;
import ru.rsreu.FindingTravelCrappo.datalayer.db.dao.TravelDAO;
import ru.rsreu.FindingTravelCrappo.datalayer.db.dao.UserDAO;
import ru.rsreu.FindingTravelCrappo.datalayer.db.dao.GradeDAO;
import ru.rsreu.FindingTravelCrappo.datalayer.db.oracledb.dao.OracleCarDAO;
import ru.rsreu.FindingTravelCrappo.datalayer.db.oracledb.dao.OraclePlaceDAO;
import ru.rsreu.FindingTravelCrappo.datalayer.db.oracledb.dao.OracleTravelDAO;
import ru.rsreu.FindingTravelCrappo.datalayer.db.oracledb.dao.OracleUserDAO;
import ru.rsreu.FindingTravelCrappo.datalayer.db.oracledb.dao.OraclePassangerDAO;
import ru.rsreu.FindingTravelCrappo.datalayer.db.oracledb.dao.OracleGradeDAO;

public class OracleDAOFactory extends DAOFactory {
	private UserDAO userDAO;
	private PlaceDAO placeDAO;
	private TravelDAO travelDAO;
	private CarDAO carDAO;
	private PassengerDAO passengerDAO;
	private GradeDAO gradeDAO;

	@Override
	public UserDAO getUserDAO() {
		if(userDAO == null){
		    userDAO = new OracleUserDAO();
		}
		return userDAO;
	}

	@Override
	public PlaceDAO getPlaceDAO() {
		if(placeDAO == null){
		    placeDAO = new OraclePlaceDAO();
		}
		return placeDAO;
	}

	@Override
	public TravelDAO getTravelDAO() {
		if(travelDAO == null){
		   travelDAO = new OracleTravelDAO();
		}
		return travelDAO;
	}

	@Override
	public CarDAO getCarDAO() {
		if(carDAO == null){
		    carDAO = new OracleCarDAO();
		}
		return carDAO;
	}

	@Override
	public PassengerDAO getPassengerDAO() {
		if(passengerDAO == null){
			passengerDAO = new OraclePassangerDAO();
        	}
		return passengerDAO;
	}

	@Override
	public GradeDAO getGradeDAO() {
		if(gradeDAO == null){
		    gradeDAO = new OracleGradeDAO();
		}
		return gradeDAO;
	}

}

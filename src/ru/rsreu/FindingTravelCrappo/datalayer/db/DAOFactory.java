package ru.rsreu.FindingTravelCrappo.datalayer.db;

import ru.rsreu.FindingTravelCrappo.datalayer.db.dao.TravelDAO;
import ru.rsreu.FindingTravelCrappo.datalayer.db.dao.PlaceDAO;
import ru.rsreu.FindingTravelCrappo.datalayer.db.dao.UserDAO;
import ru.rsreu.FindingTravelCrappo.datalayer.db.dao.CarDAO;
import ru.rsreu.FindingTravelCrappo.datalayer.db.dao.PassengerDAO;
import ru.rsreu.FindingTravelCrappo.datalayer.db.dao.GradeDAO;

public abstract class DAOFactory {
	public static DAOFactory getInstance(DBType dbType) {
		DAOFactory result = dbType.getDAOFactory();
		return result;
	}

	// FOR WEB
	public abstract UserDAO getUserDAO();

	public abstract PlaceDAO getPlaceDAO();

	public abstract TravelDAO getTravelDAO();

	public abstract CarDAO getCarDAO();
	
	public abstract PassengerDAO getPassengerDAO();

	public abstract GradeDAO getGradeDAO();
}

package ru.rsreu.FindingTravelCrappo.datalayer.data;

import java.sql.Timestamp;

import ru.rsreu.FindingTravelCrappo.datalayer.db.DAOException;
import ru.rsreu.FindingTravelCrappo.datalayer.db.DAOFactory;
import ru.rsreu.FindingTravelCrappo.datalayer.db.DBType;
import ru.rsreu.FindingTravelCrappo.datalayer.db.dao.PlaceDAO;
/**
 * Data-class for describing an entity Travel
 * @author Anastasia
 *
 */
public class Travel {
    private static final String TYPE_DB = "ORACLE";
    
	private int id;
	private int idDriver;
	private Timestamp date;
	private int sendingPlace;
	private int arrivalPlace;
	private boolean activeStatus;
	private int maxFreePlaces;

	private String sendingPlaceString;
    private String arrivalPlaceString;

	public Travel() {
		super();
	}

	public Travel(int id, int idDriver, Timestamp date, int sendingPlace, int arrivalPlace, boolean activeStatus,
			int maxFreePlaces) throws DAOException {
		super();
		this.id = id;
		this.idDriver = idDriver;
		this.date = date;
		this.sendingPlace = sendingPlace;
		this.arrivalPlace = arrivalPlace;
		this.activeStatus = activeStatus;
		this.maxFreePlaces = maxFreePlaces;
		DAOFactory daoFactory = DBType.getTypeByName(TYPE_DB).getDAOFactory();
		PlaceDAO placeDAO = daoFactory.getPlaceDAO();
		Place place = placeDAO.getPlaceById(sendingPlace);
		this.sendingPlaceString = place.getCity() + " " + place.getState();
		place = placeDAO.getPlaceById(arrivalPlace);
		this.arrivalPlaceString = place.getCity() + " " + place.getState();
	}

	public int getId() {
		return this.id;
	}

	public String getSendingPlaceString() {
		return sendingPlaceString;
	}

	public void setSendingPlaceString(String sendingPlaceString) {
		this.sendingPlaceString = sendingPlaceString;
	}

	public String getArrivalPlaceString() {
		return arrivalPlaceString;
	}

	public void setArrivalPlaceString(String arrivalPlaceString) {
		this.arrivalPlaceString = arrivalPlaceString;
	}

	public int getIdDriver() {
		return this.idDriver;
	}

	public Timestamp getDate() {
		return this.date;
	}

	public int getSendingPlace() {
		return this.sendingPlace;
	}

	public int getArrivalPlace() {
		return this.arrivalPlace;
	}

	public boolean getActiveStatus() {
		return this.activeStatus;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setIdDriver(int idDriver) {
		this.idDriver = idDriver;
	}

	public void setDate(Timestamp date) {
		this.date = date;
	}

	public void setSendingPlace(int sendingPlace) {
		this.sendingPlace = sendingPlace;
	}

	public void setArrivalPlace(int arrivalPlace) {
		this.arrivalPlace = arrivalPlace;
	}

	public void setActiveStatus(boolean activeStatus) {
		this.activeStatus = activeStatus;
	}

	public int getMaxFreePlaces() {
		return maxFreePlaces;
	}

	public void setMaxFreePlaces(int maxFreePlaces) {
		this.maxFreePlaces = maxFreePlaces;
	}

}

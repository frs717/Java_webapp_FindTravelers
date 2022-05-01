package ru.rsreu.FindingTravelCrappo.datalayer.data;

import ru.rsreu.FindingTravelCrappo.datalayer.db.DAOException;
import ru.rsreu.FindingTravelCrappo.datalayer.db.DAOFactory;
import ru.rsreu.FindingTravelCrappo.datalayer.db.DBType;
import ru.rsreu.FindingTravelCrappo.datalayer.db.dao.UserDAO;

/**
 * data class containing passenger description
 * 
 * @author Anastasia
 *
 */
public class Passenger {
	private static final String TYPE_DB = "ORACLE";
	private int id;
	private int idUser;
	private int idTravel;
	private boolean approved;
	private String nameUser;
	private String phoneUser;
	private String genderUser;

	public Passenger(int id, int idUser, int idTravel, boolean approved) throws DAOException {
		this.id = id;
		this.idUser = idUser;
		this.idTravel = idTravel;
		this.approved = approved;
		DAOFactory daoFactory = DBType.getTypeByName(TYPE_DB).getDAOFactory();
		UserDAO userDAO = daoFactory.getUserDAO();
		User user = userDAO.getUserById(idUser);
		this.nameUser = user.getName();
		this.phoneUser = user.getPhone();
		this.genderUser = user.getGender().toString();
	}

	public String getNameUser() {
		return nameUser;
	}

	public void setNameUser(String nameUser) {
		this.nameUser = nameUser;
	}

	public String getPhoneUser() {
		return phoneUser;
	}

	public void setPhoneUser(String phoneUser) {
		this.phoneUser = phoneUser;
	}

	public String getGenderUser() {
		return genderUser;
	}

	public void setGenderUser(String genderUser) {
		this.genderUser = genderUser;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIdUser() {
		return idUser;
	}

	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}

	public int getIdTravel() {
		return idTravel;
	}

	public void setIdTravel(int idTravel) {
		this.idTravel = idTravel;
	}

	public boolean getApproved() {
		return this.approved;
	}

	public void setApproved(boolean status) {
		this.approved = status;
	}

}

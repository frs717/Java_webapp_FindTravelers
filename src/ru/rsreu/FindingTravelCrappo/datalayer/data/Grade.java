package ru.rsreu.FindingTravelCrappo.datalayer.data;

/**
 * data class containing the description of the rating for the trip
 * 
 * @author Anastasia
 *
 */
public class Grade {

	int id;
	int idTravel;
	int idUser;
	int mark;

	public Grade(int id, int idTravel, int idUser, int mark) {
		super();
		this.id = id;
		this.idTravel = idTravel;
		this.idUser = idUser;
		this.mark = mark;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIdTravel() {
		return idTravel;
	}

	public void setIdTravel(int idTravel) {
		this.idTravel = idTravel;
	}

	public int getIdUser() {
		return idUser;
	}

	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}

	public int getMark() {
		return mark;
	}

	public void setMark(int mark) {
		this.mark = mark;
	}

}

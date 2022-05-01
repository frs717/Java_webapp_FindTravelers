package ru.rsreu.FindingTravelCrappo.datalayer.data;

/**
 * data class containing descriptions of places to travel
 * 
 * @author Anastasia
 *
 */
public class Place {

	private int id;
	private String city;
	private String state;

	public Place(int id, String city, String state) {
		this.id = id;
		this.city = city;
		this.state = state;
	}

	public Place() {
		super();
	}

	public int getId() {
		return this.id;
	}

	public String getCity() {
		return this.city;
	}

	public String getState() {
		return this.state;
	}

}

package ru.rsreu.FindingTravelCrappo.datalayer.data;

/**
 * data class containing a list of genders
 * 
 * @author Anastasia
 *
 */
public enum Gender {
	Man(1, "�������"), Women(2, "�������"), None(3, "�� ������");

	private int id;
	private String name;

	Gender(int id, String name) {
		this.name = name;
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public int getId() {
		return this.id;
	}

}

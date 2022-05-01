package ru.rsreu.FindingTravelCrappo.datalayer.data;
/**
 * Data-class for describing an entity Role
 * @author Anastasia
 *
 */
public enum Role {
	Admin(3, "�������������"),
	Moderator(2, "���������"),
	User(1, "������������");

	private int id;
	private String name;

	Role(int id, String name) {
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

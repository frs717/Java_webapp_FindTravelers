package ru.rsreu.FindingTravelCrappo.datalayer.data;

/**
 * data class containing a description of the user's machine
 * 
 * @author Anastasia
 *
 */
public class Car {

	private int id;
	private String brand;
	private String numbers;
	private String color;

	public Car(int id, String brand, String numbers, String color) {
		this.id = id;
		this.brand = brand;
		this.numbers = numbers;
		this.color = color;
	}

	public int getId() {
		return this.id;
	}

	public String getBrand() {
		return this.brand;
	}

	public String getNumbers() {
		return this.numbers;
	}

	public String getColor() {
		return this.color;
	}

}

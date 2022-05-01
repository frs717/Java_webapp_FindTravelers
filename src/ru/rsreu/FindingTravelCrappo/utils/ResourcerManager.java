package ru.rsreu.FindingTravelCrappo.utils;

import java.util.ResourceBundle;

public class ResourcerManager {
	public static ResourceBundle bundle = ResourceBundle.getBundle("resources.strings");

	public static String getString(String name) {
		return bundle.getString(name);
	}
}

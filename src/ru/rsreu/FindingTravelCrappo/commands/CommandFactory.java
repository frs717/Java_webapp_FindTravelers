package ru.rsreu.FindingTravelCrappo.commands;

import java.lang.reflect.InvocationTargetException;

public class CommandFactory {
	public Command getCommand(String name)
			throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
		CommandType commandType = CommandType.getCommandType(name);
		Command command = commandType.getCommandClass().getConstructor().newInstance();
		return command;
	}
}

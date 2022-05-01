package ru.rsreu.FindingTravelCrappo.commands;

import java.util.HashMap;
import java.util.Map;

public class CommandResult {

    private String view;
    private CommandActionType commandAction;
    private Map<String, Object> attributes = new HashMap<>();

    public CommandResult(String view, CommandActionType commandAction) {
        this.view = view;
        this.commandAction = commandAction;
    }

    public void addAttribute(String name, Object attribute) {
        attributes.put(name, attribute);
    }

    public String getView() {
        return view;
    }

    public CommandActionType getCommandAction() {
        return commandAction;
    }

    public Map<String, Object> getAttributes() {
        return attributes;
    }
}

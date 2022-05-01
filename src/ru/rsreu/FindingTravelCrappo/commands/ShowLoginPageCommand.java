package ru.rsreu.FindingTravelCrappo.commands;

import javax.servlet.http.HttpServletRequest;

import ru.rsreu.FindingTravelCrappo.datalayer.db.DAOException;

public class ShowLoginPageCommand implements Command {
    
    @Override
    public CommandResult execute(HttpServletRequest req) throws DAOException {
        CommandResult commandResult = new CommandResult("/jsp/login.jsp", CommandActionType.FORWARD);
        return commandResult;
    }
    
}

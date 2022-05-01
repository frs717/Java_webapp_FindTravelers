package ru.rsreu.FindingTravelCrappo.commands;

import javax.servlet.http.HttpServletRequest;

import ru.rsreu.FindingTravelCrappo.datalayer.db.DAOException;

public interface Command {
    CommandResult execute(HttpServletRequest req) throws DAOException;
}

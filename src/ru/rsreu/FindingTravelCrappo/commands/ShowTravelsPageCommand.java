package ru.rsreu.FindingTravelCrappo.commands;

import ru.rsreu.FindingTravelCrappo.datalayer.db.dao.TravelDAO;
import ru.rsreu.FindingTravelCrappo.datalayer.db.DAOException;

import javax.servlet.http.HttpServletRequest;

public class ShowTravelsPageCommand implements Command {

    @Override
    public CommandResult execute(HttpServletRequest req) throws DAOException {
        TravelDAO travelDAO = (TravelDAO) req.getServletContext().getAttribute("travelDao");
        CommandResult commandResult = new CommandResult("/jsp/travels.jsp", CommandActionType.FORWARD);
        commandResult.addAttribute("travels", travelDAO.getActiveTravels());
      
        return commandResult;
    }

}

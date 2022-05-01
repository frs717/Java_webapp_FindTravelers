package ru.rsreu.FindingTravelCrappo.commands;

import ru.rsreu.FindingTravelCrappo.datalayer.data.User;
import ru.rsreu.FindingTravelCrappo.datalayer.db.DAOException;
import javax.servlet.http.HttpServletRequest;
import ru.rsreu.FindingTravelCrappo.datalayer.db.dao.PlaceDAO;

public class ShowMainPageCommand implements Command {

	@Override
	public CommandResult execute(HttpServletRequest req) throws DAOException {
		PlaceDAO placeDAO = (PlaceDAO) req.getServletContext().getAttribute("placeDao");
		CommandResult commandResult = new CommandResult("/jsp/main.jsp", CommandActionType.FORWARD);
		commandResult.addAttribute("places", placeDAO.getPlacesList());
		return commandResult;
	}

}

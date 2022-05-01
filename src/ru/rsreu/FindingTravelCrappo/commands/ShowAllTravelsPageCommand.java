package ru.rsreu.FindingTravelCrappo.commands;

import java.util.ArrayList;
import java.util.List;

import ru.rsreu.FindingTravelCrappo.datalayer.db.dao.TravelDAO;
import ru.rsreu.FindingTravelCrappo.datalayer.db.dao.PlaceDAO;
import ru.rsreu.FindingTravelCrappo.datalayer.db.DAOException;
import ru.rsreu.FindingTravelCrappo.datalayer.data.Travel;
import ru.rsreu.FindingTravelCrappo.datalayer.data.Place;
import ru.rsreu.FindingTravelCrappo.datalayer.data.Role;
import ru.rsreu.FindingTravelCrappo.datalayer.data.User;

import javax.servlet.http.HttpServletRequest;

public class ShowAllTravelsPageCommand implements Command {

	@Override
	public CommandResult execute(HttpServletRequest req) throws DAOException {
		User user = (User) req.getSession().getAttribute("user");
		CommandResult commandResult;
		if (user.getRole() == Role.Admin || user.getRole() == Role.Moderator) {
			TravelDAO travelDAO = (TravelDAO) req.getServletContext().getAttribute("travelDao");
			commandResult = new CommandResult("/jsp/travels.jsp", CommandActionType.FORWARD);
			commandResult.addAttribute("travels", travelDAO.getTravels());
		} else {
			commandResult = new ShowMainPageCommand().execute(req);
		}
		return commandResult;
	}
}
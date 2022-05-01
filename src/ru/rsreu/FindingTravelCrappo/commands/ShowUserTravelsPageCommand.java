package ru.rsreu.FindingTravelCrappo.commands;

import javax.servlet.http.HttpServletRequest;

import ru.rsreu.FindingTravelCrappo.datalayer.data.User;
import ru.rsreu.FindingTravelCrappo.datalayer.db.DAOException;
import ru.rsreu.FindingTravelCrappo.datalayer.db.dao.TravelDAO;

public class ShowUserTravelsPageCommand implements Command {

	@Override
	public CommandResult execute(HttpServletRequest req) throws DAOException {
		User user = (User) req.getSession().getAttribute("user");
		TravelDAO travelDAO = (TravelDAO) req.getServletContext().getAttribute("travelDao");
		CommandResult commandResult = new CommandResult("/jsp/travels.jsp", CommandActionType.FORWARD);
		commandResult.addAttribute("travels", travelDAO.getTravelsByDriver(user.getId()));
				// + .addAll(travelDAO.getTravelsByDriver(user.getId()))
		return commandResult;
	}

}

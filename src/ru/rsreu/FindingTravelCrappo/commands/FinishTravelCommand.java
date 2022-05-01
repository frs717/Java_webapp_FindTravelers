package ru.rsreu.FindingTravelCrappo.commands;

import javax.servlet.http.HttpServletRequest;

import ru.rsreu.FindingTravelCrappo.datalayer.data.Travel;
import ru.rsreu.FindingTravelCrappo.datalayer.data.User;
import ru.rsreu.FindingTravelCrappo.datalayer.db.DAOException;
import ru.rsreu.FindingTravelCrappo.datalayer.db.dao.TravelDAO;

public class FinishTravelCommand implements Command {

	@Override
	public CommandResult execute(HttpServletRequest req) throws DAOException {
		User user = (User) req.getSession().getAttribute("user");
		CommandResult commandResult;
		TravelDAO travelDAO = (TravelDAO) req.getServletContext().getAttribute("travelDao");
		int travelId = Integer.parseInt(req.getParameter("travelId"));
		int driverId = travelDAO.getDriverByTravelId(travelId);
		Travel travel = travelDAO.getTravelById(travelId);
		if (driverId == user.getId() && travel.getActiveStatus()) {
			travelDAO.finishTravelById(travelId);
			commandResult = new ShowTravelsPageCommand().execute(req);
		} else {
			commandResult = new ShowMainPageCommand().execute(req);
		}
		return commandResult;
	}

}

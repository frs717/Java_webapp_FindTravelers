package ru.rsreu.FindingTravelCrappo.commands;

import javax.servlet.http.HttpServletRequest;

import ru.rsreu.FindingTravelCrappo.datalayer.data.Travel;
import ru.rsreu.FindingTravelCrappo.datalayer.data.User;
import ru.rsreu.FindingTravelCrappo.datalayer.db.DAOException;
import ru.rsreu.FindingTravelCrappo.datalayer.db.dao.PassengerDAO;
import ru.rsreu.FindingTravelCrappo.datalayer.db.dao.TravelDAO;

public class JoinCommand implements Command {

	@Override
	public CommandResult execute(HttpServletRequest req) throws DAOException {
		User user = (User) req.getSession().getAttribute("user");
		int travelId = Integer.parseInt(req.getParameter("travelId"));
		TravelDAO travelDAO = (TravelDAO) req.getServletContext().getAttribute("travelDao");
		Travel travel = travelDAO.getTravelById(travelId);
		CommandResult commandResult;
		if (!user.getStatus() && travel.getActiveStatus()) {
			PassengerDAO passengerDAO = (PassengerDAO) req.getServletContext().getAttribute("passengerDao");

			passengerDAO.join(user.getId(), travelId);
			commandResult = new ShowTravelPageCommand().execute(req);
		} else {
			commandResult = new ShowMainPageCommand().execute(req);
		}
		return commandResult;
	}
}
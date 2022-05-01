package ru.rsreu.FindingTravelCrappo.commands;

import ru.rsreu.FindingTravelCrappo.datalayer.db.dao.PassengerDAO;
import ru.rsreu.FindingTravelCrappo.datalayer.db.dao.TravelDAO;
import ru.rsreu.FindingTravelCrappo.datalayer.db.DAOException;
import ru.rsreu.FindingTravelCrappo.datalayer.data.Role;
import ru.rsreu.FindingTravelCrappo.datalayer.data.User;

import javax.servlet.http.HttpServletRequest;

public class DeleteTravelCommand implements Command {

	@Override
	public CommandResult execute(HttpServletRequest req) throws DAOException {
		User user = (User) req.getSession().getAttribute("user");
		CommandResult commandResult;
		TravelDAO travelDAO = (TravelDAO) req.getServletContext().getAttribute("travelDao");
		int travelId = Integer.parseInt(req.getParameter("travelId"));
		int driverId = travelDAO.getDriverByTravelId(travelId);
		if (user.getRole() == Role.Admin || user.getRole() == Role.Moderator || driverId == user.getId()) {
			PassengerDAO passengerDAO = (PassengerDAO) req.getServletContext().getAttribute("passengerDao");
			passengerDAO.deletePassengersByTravelId(travelId);
			travelDAO.deleteTravelById(travelId);
			commandResult = new ShowTravelsPageCommand().execute(req);
		} else {
			commandResult = new ShowMainPageCommand().execute(req);
		}
		return commandResult;
	}
}

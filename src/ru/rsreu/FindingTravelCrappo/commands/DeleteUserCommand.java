package ru.rsreu.FindingTravelCrappo.commands;

import ru.rsreu.FindingTravelCrappo.datalayer.db.dao.PassengerDAO;
import ru.rsreu.FindingTravelCrappo.datalayer.db.dao.TravelDAO;
import ru.rsreu.FindingTravelCrappo.datalayer.db.dao.UserDAO;
import ru.rsreu.FindingTravelCrappo.datalayer.db.DAOException;
import ru.rsreu.FindingTravelCrappo.datalayer.data.Role;
import ru.rsreu.FindingTravelCrappo.datalayer.data.Travel;
import ru.rsreu.FindingTravelCrappo.datalayer.data.User;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

public class DeleteUserCommand implements Command {

	@Override
	public CommandResult execute(HttpServletRequest req) throws DAOException {
		User user = (User) req.getSession().getAttribute("user");
		CommandResult commandResult;
		if (user.getRole() == Role.Admin) {
			UserDAO userDAO = (UserDAO) req.getServletContext().getAttribute("userDao");
			TravelDAO travelDAO = (TravelDAO) req.getServletContext().getAttribute("travelDao");
			PassengerDAO passengerDAO = (PassengerDAO) req.getServletContext().getAttribute("passengerDao");
			int userId = Integer.parseInt(req.getParameter("userId"));
			userDAO.deleteUserById(userId);
			List<Travel> userTravels = travelDAO.getTravelsByDriver(userId);
			for (Travel travel : userTravels) {
				travelDAO.finishTravelById(travel.getId());
			}
			passengerDAO.leaveAll(userId);
			commandResult = new ShowUsersPageCommand().execute(req);
		} else {
			commandResult = new ShowMainPageCommand().execute(req);
		}
		return commandResult;
	}
}

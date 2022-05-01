package ru.rsreu.FindingTravelCrappo.commands;

import javax.servlet.http.HttpServletRequest;

import ru.rsreu.FindingTravelCrappo.datalayer.data.Travel;
import ru.rsreu.FindingTravelCrappo.datalayer.data.User;
import ru.rsreu.FindingTravelCrappo.datalayer.db.DAOException;
import ru.rsreu.FindingTravelCrappo.datalayer.db.dao.PassengerDAO;
import ru.rsreu.FindingTravelCrappo.datalayer.db.dao.TravelDAO;

public class RejectCommand implements Command {

	@Override
	public CommandResult execute(HttpServletRequest req) throws DAOException {
        User user = (User) req.getSession().getAttribute("user");
		int travelId = Integer.parseInt(req.getParameter("travelId"));
		TravelDAO travelDAO = (TravelDAO) req.getServletContext().getAttribute("travelDao");
		int driverId = travelDAO.getDriverByTravelId(travelId);

        Travel travel = travelDAO.getTravelById(travelId);
		CommandResult commandResult;
		if (user.getId() == driverId && travel.getActiveStatus()) {
			PassengerDAO passengerDAO = (PassengerDAO) req.getServletContext().getAttribute("passengerDao");

			int passengerId = Integer.parseInt(req.getParameter("passengerId"));
			System.out.println("TRAVELID = " + travelId + "\n PASSENGERID = " + passengerId);
			passengerDAO.reject(passengerId);
			commandResult = new ShowTravelPageCommand().execute(req);
		} else {
			commandResult = new ShowMainPageCommand().execute(req);
		}
		return commandResult;
	}

}

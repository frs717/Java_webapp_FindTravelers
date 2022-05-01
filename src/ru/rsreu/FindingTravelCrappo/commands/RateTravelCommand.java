package ru.rsreu.FindingTravelCrappo.commands;

import javax.servlet.http.HttpServletRequest;

import ru.rsreu.FindingTravelCrappo.datalayer.data.Travel;
import ru.rsreu.FindingTravelCrappo.datalayer.data.User;
import ru.rsreu.FindingTravelCrappo.datalayer.db.DAOException;
import ru.rsreu.FindingTravelCrappo.datalayer.db.dao.GradeDAO;
import ru.rsreu.FindingTravelCrappo.datalayer.db.dao.PassengerDAO;
import ru.rsreu.FindingTravelCrappo.datalayer.db.dao.TravelDAO;

public class RateTravelCommand implements Command {

	@Override
	public CommandResult execute(HttpServletRequest req) throws DAOException {
		User user = (User) req.getSession().getAttribute("user");
		int travelId = Integer.parseInt(req.getParameter("travelId"));
		int mark = Integer.parseInt(req.getParameter("mark"));
		TravelDAO travelDAO = (TravelDAO) req.getServletContext().getAttribute("travelDao");
		PassengerDAO passengerDAO = (PassengerDAO) req.getServletContext().getAttribute("passengerDao");
		Travel travel = travelDAO.getTravelById(travelId);
		CommandResult commandResult;
		System.out.println(passengerDAO.isExists(user.getId(), travelId));
		if (passengerDAO.isExists(user.getId(), travelId) && !travel.getActiveStatus()) {
			GradeDAO gradeDAO = (GradeDAO) req.getServletContext().getAttribute("gradeDao");

			gradeDAO.grade(user.getId(), travelId, mark);
			commandResult = new ShowTravelPageCommand().execute(req);
		} else {
			commandResult = new ShowMainPageCommand().execute(req);
		}
		return commandResult;
	}

}

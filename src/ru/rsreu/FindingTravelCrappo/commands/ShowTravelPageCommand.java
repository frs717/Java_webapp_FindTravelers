package ru.rsreu.FindingTravelCrappo.commands;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import ru.rsreu.FindingTravelCrappo.datalayer.data.User;
import ru.rsreu.FindingTravelCrappo.datalayer.data.Passenger;
import ru.rsreu.FindingTravelCrappo.datalayer.data.Travel;
import ru.rsreu.FindingTravelCrappo.datalayer.db.DAOException;
import ru.rsreu.FindingTravelCrappo.datalayer.db.dao.TravelDAO;
import ru.rsreu.FindingTravelCrappo.datalayer.db.dao.CarDAO;
import ru.rsreu.FindingTravelCrappo.datalayer.db.dao.GradeDAO;
import ru.rsreu.FindingTravelCrappo.datalayer.db.dao.UserDAO;
import ru.rsreu.FindingTravelCrappo.datalayer.db.dao.PassengerDAO;

public class ShowTravelPageCommand implements Command {

	@Override
	public CommandResult execute(HttpServletRequest req) throws DAOException {
		UserDAO userDAO = (UserDAO) req.getServletContext().getAttribute("userDao");
		TravelDAO travelDAO = (TravelDAO) req.getServletContext().getAttribute("travelDao");
		CarDAO carDAO = (CarDAO) req.getServletContext().getAttribute("carDao");
		PassengerDAO passengerDAO = (PassengerDAO) req.getServletContext().getAttribute("passengerDao");
		GradeDAO gradeDAO = (GradeDAO) req.getServletContext().getAttribute("gradeDao");

		int travelId = Integer.parseInt(req.getParameter("travelId"));
		int driver = travelDAO.getDriverByTravelId(travelId);
		CommandResult commandResult = new CommandResult("/jsp/travel.jsp", CommandActionType.FORWARD);

		User user = (User) req.getSession().getAttribute("user");

		commandResult.addAttribute("travel", travelDAO.getTravelById(travelId));
		commandResult.addAttribute("car", carDAO.getCarByIdUser(driver));
		commandResult.addAttribute("driver", userDAO.getUserById(driver));
		boolean allowJoin = true;
		if (passengerDAO.isExists(user.getId(), travelId)) {
			allowJoin = false;
		}
		commandResult.addAttribute("allowJoin", allowJoin);

		boolean allowRait = false;
		if (!gradeDAO.checkUser(travelId, user.getId()) && passengerDAO.isExists(user.getId(), travelId)
				&& passengerDAO.getStatusPassengerByTravelID(user.getId(), travelId)) {

			allowRait = true;
		}

		commandResult.addAttribute("allowRait", allowRait);

		List<Passenger> passengersList = passengerDAO.getPassengersByTravellingId(travelId);
		List<Integer> passengersIdList = new ArrayList<>();
		for (Passenger passenger : passengersList) {
			passengersIdList.add(passenger.getIdUser());
		}

		List<User> userList = new ArrayList<>();
		for (Integer id : passengersIdList) {
			userList.add(userDAO.getUserById(id));
		}

		commandResult.addAttribute("usersPassengers", userList);
		commandResult.addAttribute("passengers", passengersList);
		commandResult.addAttribute("avgGrade", gradeDAO.getAverage(travelId));
		return commandResult;
	}
}

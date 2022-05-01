package ru.rsreu.FindingTravelCrappo.commands;

import ru.rsreu.FindingTravelCrappo.datalayer.db.dao.TravelDAO;
import ru.rsreu.FindingTravelCrappo.datalayer.db.DAOException;
import ru.rsreu.FindingTravelCrappo.datalayer.data.Travel;
import ru.rsreu.FindingTravelCrappo.datalayer.data.User;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

public class CreateTravelCommand implements Command {

	@Override
	public CommandResult execute(HttpServletRequest req) throws DAOException {
		User user = (User) req.getSession().getAttribute("user");
		CommandResult commandResult;
		if (!user.getStatus()) {
			TravelDAO travelDAO = (TravelDAO) req.getServletContext().getAttribute("travelDao");
			int where = Integer.parseInt(req.getParameter("where"));
			int arrive = Integer.parseInt(req.getParameter("arrive"));
			String date = req.getParameter("date");
			String time = req.getParameter("time");
			int maxPassengers = Integer.parseInt(req.getParameter("count"));
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm", Locale.ENGLISH);
			Date datetime = null;
			try {
				datetime = formatter.parse(date + " " + time);
			} catch (ParseException e) {
				e.printStackTrace();
			}

			Timestamp sqlDate = new java.sql.Timestamp(datetime.getTime());
			int idDriver = user.getId();

			Travel travel = new Travel(0, idDriver, sqlDate, where, arrive, true, maxPassengers);
			travelDAO.addTravel(travel);
			commandResult = new ShowTravelsPageCommand().execute(req);
		} else {
			commandResult = new ShowMainPageCommand().execute(req);
		}
		return commandResult;
	}
}
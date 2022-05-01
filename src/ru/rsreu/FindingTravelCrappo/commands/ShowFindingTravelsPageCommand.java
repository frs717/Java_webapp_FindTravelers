package ru.rsreu.FindingTravelCrappo.commands;

import ru.rsreu.FindingTravelCrappo.datalayer.db.dao.TravelDAO;
import java.text.SimpleDateFormat;
import javax.servlet.http.HttpServletRequest;
import ru.rsreu.FindingTravelCrappo.datalayer.db.DAOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.Date;
import java.util.Locale;

public class ShowFindingTravelsPageCommand implements Command {

	@Override
	public CommandResult execute(HttpServletRequest req) throws DAOException {
		TravelDAO travelDAO = (TravelDAO) req.getServletContext().getAttribute("travelDao");
		CommandResult commandResult = new CommandResult("/jsp/travels.jsp", CommandActionType.FORWARD);
		int sendingPlace = Integer.parseInt(req.getParameter("where"));
		int arrivalPlace = Integer.parseInt(req.getParameter("arrive"));
		String date = req.getParameter("date");
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
		Date datetime = null;
		try {
			datetime = formatter.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		Timestamp sqlDate = new java.sql.Timestamp(datetime.getTime());

		commandResult.addAttribute("travels", travelDAO.findTravels(sendingPlace, arrivalPlace, sqlDate));
		return commandResult;
	}

}

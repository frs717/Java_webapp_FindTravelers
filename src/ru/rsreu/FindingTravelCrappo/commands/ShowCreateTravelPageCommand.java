package ru.rsreu.FindingTravelCrappo.commands;

import javax.servlet.http.HttpServletRequest;
import ru.rsreu.FindingTravelCrappo.datalayer.db.dao.PlaceDAO;
import ru.rsreu.FindingTravelCrappo.datalayer.db.DAOException;
import ru.rsreu.FindingTravelCrappo.datalayer.data.User;

public class ShowCreateTravelPageCommand implements Command {

	@Override
	public CommandResult execute(HttpServletRequest req) throws DAOException {
		User user = (User) req.getSession().getAttribute("user");
		CommandResult commandResult;
		if (!user.getStatus()) {
			PlaceDAO placeDAO = (PlaceDAO) req.getServletContext().getAttribute("placeDao");
			commandResult = new CommandResult("/jsp/createTravel.jsp", CommandActionType.FORWARD);
			commandResult.addAttribute("places", placeDAO.getPlacesList());
		} else {
			commandResult = new ShowMainPageCommand().execute(req);
		}
		return commandResult;
	}
}
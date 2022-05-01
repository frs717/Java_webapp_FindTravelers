package ru.rsreu.FindingTravelCrappo.commands;

import javax.servlet.http.HttpServletRequest;

import ru.rsreu.FindingTravelCrappo.datalayer.db.dao.UserDAO;
import ru.rsreu.FindingTravelCrappo.datalayer.db.dao.PlaceDAO;
import ru.rsreu.FindingTravelCrappo.datalayer.data.User;
import ru.rsreu.FindingTravelCrappo.datalayer.db.DAOException;

public class LoginCommand implements Command {

	@Override
	public CommandResult execute(HttpServletRequest req) throws DAOException {
		UserDAO userDAO = (UserDAO) req.getServletContext().getAttribute("userDao");
		PlaceDAO placeDAO = (PlaceDAO) req.getServletContext().getAttribute("placeDao");
		String login = req.getParameter("login");
		String password = req.getParameter("password");
		User user = userDAO.getUserByLogin(login);
		CommandResult commandResult;
		if (user == null || user.isDeleteStatus()) {
			commandResult = new CommandResult("/jsp/login.jsp", CommandActionType.FORWARD);
			commandResult.addAttribute("error", "no user");
			commandResult.addAttribute("requestedURL", req.getParameter("requestedURL"));
		} else {
			if (!user.getPassword().equals(password)) {
				commandResult = new CommandResult("/jsp/login.jsp", CommandActionType.FORWARD);
				commandResult.addAttribute("error", "wrong password");
				commandResult.addAttribute("requestedURL", req.getParameter("requestedURL"));
			} else {
				user.setAuthorized(true);
				userDAO.login(user);
				req.getSession(false).setAttribute("user", user);
				commandResult = new ShowMainPageCommand().execute(req);

				 commandResult.addAttribute("places", placeDAO.getPlacesList());
			}
		}
		return commandResult;
	}

}
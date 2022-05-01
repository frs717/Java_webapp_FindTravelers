package ru.rsreu.FindingTravelCrappo.commands;

import javax.servlet.http.HttpServletRequest;

import ru.rsreu.FindingTravelCrappo.datalayer.db.dao.UserDAO;
import ru.rsreu.FindingTravelCrappo.datalayer.data.User;
import ru.rsreu.FindingTravelCrappo.datalayer.db.DAOException;

public class LogoutCommand implements Command {

	@Override
	public CommandResult execute(HttpServletRequest req) throws DAOException {
		UserDAO userDAO = (UserDAO) req.getServletContext().getAttribute("userDao");
		User user = (User) req.getSession().getAttribute("user");
		userDAO.logout(user);
		req.getSession().invalidate();
		CommandResult commandResult = new CommandResult(req.getContextPath() + req.getServletPath(),
				CommandActionType.REDIRECT);
		return commandResult;
	}
}

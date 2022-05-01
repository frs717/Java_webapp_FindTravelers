package ru.rsreu.FindingTravelCrappo.commands;

import ru.rsreu.FindingTravelCrappo.datalayer.db.dao.UserDAO;
import ru.rsreu.FindingTravelCrappo.datalayer.data.Role;
import ru.rsreu.FindingTravelCrappo.datalayer.data.User;
import ru.rsreu.FindingTravelCrappo.datalayer.db.DAOException;

import javax.servlet.http.HttpServletRequest;

public class BlockCommand implements Command {
	@Override
	public CommandResult execute(HttpServletRequest req) throws DAOException {
		User user = (User) req.getSession().getAttribute("user");
		CommandResult commandResult;
		if (user.getRole() == Role.Admin || user.getRole() == Role.Moderator) {
			UserDAO userDAO = (UserDAO) req.getServletContext().getAttribute("userDao");
			int userId = Integer.parseInt(req.getParameter("userId"));
			userDAO.changeUserStatus(userId, true);
			commandResult = new ShowUsersPageCommand().execute(req);
		} else {
			commandResult = new ShowMainPageCommand().execute(req);
		}
		return commandResult;
	}
}

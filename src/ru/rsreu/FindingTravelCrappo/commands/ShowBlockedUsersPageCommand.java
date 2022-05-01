package ru.rsreu.FindingTravelCrappo.commands;

import ru.rsreu.FindingTravelCrappo.datalayer.db.dao.UserDAO;
import ru.rsreu.FindingTravelCrappo.datalayer.db.DAOException;
import ru.rsreu.FindingTravelCrappo.datalayer.data.Role;
import ru.rsreu.FindingTravelCrappo.datalayer.data.User;

import javax.servlet.http.HttpServletRequest;

public class ShowBlockedUsersPageCommand implements Command {

	@Override
	public CommandResult execute(HttpServletRequest req) throws DAOException {
		User user = (User) req.getSession().getAttribute("user");
		CommandResult commandResult;
		if (user.getRole() == Role.Admin || user.getRole() == Role.Moderator) {
			UserDAO userDAO = (UserDAO) req.getServletContext().getAttribute("userDao");
			commandResult = new CommandResult("/jsp/BlockUserList.jsp", CommandActionType.FORWARD);
			commandResult.addAttribute("usersList", userDAO.getBlockedUsers());
		} else {
			commandResult = new ShowMainPageCommand().execute(req);
		}
		return commandResult;
	}
}
package ru.rsreu.FindingTravelCrappo.commands;

import ru.rsreu.FindingTravelCrappo.datalayer.db.dao.UserDAO;
import ru.rsreu.FindingTravelCrappo.datalayer.db.DAOException;

import javax.servlet.http.HttpServletRequest;

import ru.rsreu.FindingTravelCrappo.datalayer.data.Role;
import ru.rsreu.FindingTravelCrappo.datalayer.data.User;

public class ShowUsersPageCommand implements Command {

	@Override
	public CommandResult execute(HttpServletRequest req) throws DAOException {
		User user = (User) req.getSession().getAttribute("user");
		CommandResult commandResult;
		if (user.getRole() == Role.Admin || user.getRole() == Role.Moderator){
			UserDAO userDAO = (UserDAO) req.getServletContext().getAttribute("userDao");
			commandResult = new CommandResult("/jsp/usersList.jsp", CommandActionType.FORWARD);
			commandResult.addAttribute("usersList", userDAO.getUsers());
		} else {
			commandResult = new ShowMainPageCommand().execute(req);
		}
		return commandResult;
	}
}

package ru.rsreu.FindingTravelCrappo.commands;

import ru.rsreu.FindingTravelCrappo.datalayer.db.dao.UserDAO;
import ru.rsreu.FindingTravelCrappo.datalayer.db.DAOException;
import ru.rsreu.FindingTravelCrappo.datalayer.data.User;
import ru.rsreu.FindingTravelCrappo.datalayer.data.Role;
import ru.rsreu.FindingTravelCrappo.datalayer.data.Gender;

import javax.servlet.http.HttpServletRequest;

public class CreateUserCommand implements Command {

	@Override
	public CommandResult execute(HttpServletRequest req) throws DAOException {
		User user = (User) req.getSession().getAttribute("user");
		CommandResult commandResult;
		if (user.getRole() == Role.Admin) {
			UserDAO userDAO = (UserDAO) req.getServletContext().getAttribute("userDao");
			String login = req.getParameter("login");
			String password = req.getParameter("password");
			Role role = Role.valueOf(req.getParameter("role"));
			Gender gender = Gender.valueOf(req.getParameter("gender"));
			int car = Integer.valueOf(req.getParameter("car"));
			String phone = req.getParameter("phone");
			String name = req.getParameter("name");
			userDAO.addUser(new User(0, login, password, role, gender, car, false, phone, name, false, false));
			commandResult = new ShowUsersPageCommand().execute(req);
		} else {
			commandResult = new ShowMainPageCommand().execute(req);
		}
		return commandResult;
	}
}

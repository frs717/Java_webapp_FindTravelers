package ru.rsreu.FindingTravelCrappo.listeners;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import ru.rsreu.FindingTravelCrappo.datalayer.data.User;
import ru.rsreu.FindingTravelCrappo.datalayer.db.DAOException;
import ru.rsreu.FindingTravelCrappo.datalayer.db.dao.UserDAO;

public class LogOutListener implements HttpSessionListener {
	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		UserDAO userDAO = (UserDAO) se.getSession().getServletContext().getAttribute("userDao");
		User user = (User) se.getSession().getAttribute("user");
		try {
			userDAO.logout(user);
		} catch (DAOException e) {
			e.printStackTrace();
		}
	}
}

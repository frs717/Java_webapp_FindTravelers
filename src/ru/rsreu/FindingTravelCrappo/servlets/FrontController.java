package ru.rsreu.FindingTravelCrappo.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import ru.rsreu.FindingTravelCrappo.datalayer.db.DAOFactory;
import ru.rsreu.FindingTravelCrappo.datalayer.db.DBType;
import ru.rsreu.FindingTravelCrappo.commands.Command;
import ru.rsreu.FindingTravelCrappo.commands.CommandActionType;
import ru.rsreu.FindingTravelCrappo.commands.CommandFactory;
import ru.rsreu.FindingTravelCrappo.commands.CommandResult;
import ru.rsreu.FindingTravelCrappo.datalayer.data.User;
import ru.rsreu.FindingTravelCrappo.datalayer.db.dao.UserDAO;

/**
 * 
 * A class that accepts requests and sends a response to the user.
 * 
 * @author Anastasia, Ivan
 *
 */
public class FrontController extends HttpServlet {
	private static final String TYPE_DB = "ORACLE";

	private CommandFactory commandFactory;

	/**
	 * 
	 * initialization a servlet. initialization DAO
	 */
	@Override
	public void init() {
		commandFactory = new CommandFactory();

		DAOFactory daoFactory = DBType.getTypeByName(TYPE_DB).getDAOFactory();
		getServletContext().setAttribute("userDao", daoFactory.getUserDAO());
		getServletContext().setAttribute("travelDao", daoFactory.getTravelDAO());
		getServletContext().setAttribute("placeDao", daoFactory.getPlaceDAO());
		getServletContext().setAttribute("carDao", daoFactory.getCarDAO());
		getServletContext().setAttribute("passengerDao", daoFactory.getPassengerDAO());
		getServletContext().setAttribute("gradeDao", daoFactory.getGradeDAO());
	}

	/**
	 * get request processing
	 * 
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
		processRequest(req, resp);
	}

	/**
	 * post request processing
	 */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
		processRequest(req, resp);
	}

	/**
	 * Processing request
	 * 
	 * @param req
	 * @param resp
	 */
	private void processRequest(HttpServletRequest req, HttpServletResponse resp) {
		String commandName = req.getParameter("command");
		try {
			Command command = commandFactory.getCommand(commandName);
			CommandResult commandResult = command.execute(req);
			executeCommandAction(req, resp, commandResult);
		} catch (Exception ex) {
			ex.printStackTrace();
			req.setAttribute("error", ex);
			try {
				req.getRequestDispatcher("/jsp/error.jsp").forward(req, resp);
			} catch (ServletException | IOException forwardEx) {
				forwardEx.printStackTrace();
			}
		}
	}

	/**
	 * 
	 * creating and executing a command
	 * 
	 * @param req
	 * @param resp
	 * @param commandResult
	 * @throws IOException
	 * @throws ServletException
	 */
	private void executeCommandAction(HttpServletRequest req, HttpServletResponse resp, CommandResult commandResult)
			throws IOException, ServletException {
		if (commandResult.getCommandAction() == CommandActionType.REDIRECT) {

			resp.sendRedirect(commandResult.getView());
		} else if (commandResult.getCommandAction() == CommandActionType.FORWARD) {
			commandResult.getAttributes().forEach(req::setAttribute);
			req.getRequestDispatcher(commandResult.getView()).forward(req, resp);
		} else {
			req.getRequestDispatcher("/jsp/main.jsp").forward(req, resp);
		}
	}

	/**
	 * 
	 * destroying the servlet
	 */
	@Override
	public void destroy() {
		UserDAO userDAO = (UserDAO) getServletContext().getAttribute("userDAO");
		try {
			List<User> users = userDAO.getUsers();
			for (User user : users) {
				userDAO.logout(user);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
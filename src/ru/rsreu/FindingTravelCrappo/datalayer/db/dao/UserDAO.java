package ru.rsreu.FindingTravelCrappo.datalayer.db.dao;

import ru.rsreu.FindingTravelCrappo.datalayer.data.User;
import ru.rsreu.FindingTravelCrappo.datalayer.db.DAOException;

import java.util.List;

/**
 * DAO interface for user's requests
 * 
 * @author Anastasia
 *
 */
public interface UserDAO {

	/**
	 * Check user
	 * 
	 * @param login
	 * @param password
	 * @return
	 * @throws DAOException
	 */
	boolean userIsExists(String login, String password) throws DAOException;

	/**
	 * Get user by login
	 * 
	 * @param login
	 * @return
	 * @throws DAOException
	 */
	User getUserByLogin(String login) throws DAOException;

	/**
	 * Get all users
	 * 
	 * @return
	 * @throws DAOException
	 */
	List<User> getUsers() throws DAOException;

	/**
	 * Create user
	 * 
	 * @param user
	 * @throws DAOException
	 */
	void addUser(User user) throws DAOException;

	/**
	 * Change authorizeted user status
	 * 
	 * @param user
	 * @throws DAOException
	 */
	void login(User user) throws DAOException;

	/**
	 * Change authorizeted user status
	 * 
	 * @param user
	 * @throws DAOException
	 */
	void logout(User user) throws DAOException;

	/**
	 * Get user by id
	 * 
	 * @param id
	 * @return
	 * @throws DAOException
	 */
	User getUserById(int id) throws DAOException;

	/**
	 * Delete user by id
	 * 
	 * @param id
	 * @throws DAOException
	 */
	void deleteUserById(int id) throws DAOException;

	/**
	 * Change user block status
	 * 
	 * @param id
	 * @param status
	 * @throws DAOException
	 */
	void changeUserStatus(int id, boolean status) throws DAOException;

	/**
	 * Get blocked users
	 * 
	 * @return
	 * @throws DAOException
	 */
	List<User> getBlockedUsers() throws DAOException;

	/**
	 * Get users count
	 * 
	 * @return
	 * @throws DAOException
	 */
	int countUsers() throws DAOException;

}

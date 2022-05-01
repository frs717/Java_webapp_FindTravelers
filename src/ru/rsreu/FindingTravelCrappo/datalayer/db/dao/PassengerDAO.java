package ru.rsreu.FindingTravelCrappo.datalayer.db.dao;

import java.util.List;

import ru.rsreu.FindingTravelCrappo.datalayer.data.Passenger;
import ru.rsreu.FindingTravelCrappo.datalayer.db.DAOException;

/**
 * DAO interface for passengers's requests
 * 
 * @author Anastasia
 *
 */
public interface PassengerDAO {
	/**
	 * Get passengers by travel
	 * 
	 * @param id
	 * @return
	 * @throws DAOException
	 */
	public List<Passenger> getPassengersByTravellingId(int id) throws DAOException;

	/**
	 * Check user in travel
	 * 
	 * @param idUser
	 * @param idTravel
	 * @return
	 * @throws DAOException
	 */
	public boolean isExists(int idUser, int idTravel) throws DAOException;

	/**
	 * Get user status in travel
	 * 
	 * @param idUser
	 * @param idTravel
	 * @return
	 * @throws DAOException
	 */
	public boolean getStatusPassengerByTravelID(int idUser, int idTravel) throws DAOException;

	/**
	 * Delete all passengers from travel
	 * 
	 * @param id
	 * @throws DAOException
	 */
	public void deletePassengersByTravelId(int id) throws DAOException;

	/**
	 * Add user in travel (join)
	 * 
	 * @param idUser
	 * @param idTravel
	 * @throws DAOException
	 */
	public void join(int idUser, int idTravel) throws DAOException;

	/**
	 * Delete passenger from (leave)
	 * 
	 * @param idUser
	 * @param idTravel
	 * @throws DAOException
	 */
	public void leave(int idUser, int idTravel) throws DAOException;

	/**
	 * Change passenger status in travel (accept)
	 * 
	 * @param id
	 * @throws DAOException
	 */
	public void accept(int id) throws DAOException;

	/**
	 * Change passenger status in travel (reject)
	 * 
	 * @param id
	 * @throws DAOException
	 */
	public void reject(int id) throws DAOException;

	/**
	 * Delete user from all travels
	 * 
	 * @param idUser
	 * @throws DAOException
	 */
	public void leaveAll(int idUser) throws DAOException;

}

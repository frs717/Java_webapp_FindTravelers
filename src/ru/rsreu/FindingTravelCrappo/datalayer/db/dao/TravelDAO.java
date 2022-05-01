package ru.rsreu.FindingTravelCrappo.datalayer.db.dao;

import ru.rsreu.FindingTravelCrappo.datalayer.data.Travel;
import ru.rsreu.FindingTravelCrappo.datalayer.db.DAOException;

import java.sql.Timestamp;

import java.util.List;

/**
 * DAO interface for travel's requests
 * 
 * @author Anastasia
 *
 */
public interface TravelDAO {

	/**
	 * Get travel by id
	 * 
	 * @param id
	 * @return
	 * @throws DAOException
	 */
	public Travel getTravelById(int id) throws DAOException;

	/**
	 * Add new travel
	 * 
	 * @param travel
	 * @throws DAOException
	 */
	public void addTravel(Travel travel) throws DAOException;

	/**
	 * Finish travel by id (not delete)
	 * 
	 * @param id
	 * @throws DAOException
	 */
	public void finishTravelById(int id) throws DAOException;

	/**
	 * Delete travel by id
	 * 
	 * @param id
	 * @throws DAOException
	 */
	public void deleteTravelById(int id) throws DAOException;

	/**
	 * Get travel driver
	 * 
	 * @param id
	 * @return
	 * @throws DAOException
	 */
	public int getDriverByTravelId(int id) throws DAOException;

	/**
	 * Get active travels list
	 * 
	 * @return
	 * @throws DAOException
	 */
	public List<Travel> getActiveTravels() throws DAOException;

	/**
	 * Get all travels
	 * 
	 * @return
	 * @throws DAOException
	 */
	public List<Travel> getTravels() throws DAOException;

	/**
	 * Get passenger's travels
	 * 
	 * @param id
	 * @return
	 * @throws DAOException
	 */
	public List<Travel> getTravelsByPassenger(int id) throws DAOException;

	/**
	 * Get driver's travels
	 * 
	 * @param idDriver
	 * @return
	 * @throws DAOException
	 */
	public List<Travel> getTravelsByDriver(int idDriver) throws DAOException;

	/**
	 * Find travel
	 * 
	 * @param sendingPlaceID
	 * @param arrivalPlaceID
	 * @param date
	 * @return
	 * @throws DAOException
	 */
	public List<Travel> findTravels(int sendingPlaceID, int arrivalPlaceID, Timestamp date) throws DAOException;

	/**
	 * Get count travels
	 * 
	 * @return
	 * @throws DAOException
	 */
	public int countTravels() throws DAOException;

}

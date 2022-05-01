package ru.rsreu.FindingTravelCrappo.datalayer.db.dao;

import java.util.List;

import ru.rsreu.FindingTravelCrappo.datalayer.data.Place;
import ru.rsreu.FindingTravelCrappo.datalayer.db.DAOException;

/**
 * DAO interface for place's requests
 * 
 * @author Anastasia
 *
 */
public interface PlaceDAO {

	/**
	 * Get place by id
	 * 
	 * @param id
	 * @return
	 * @throws DAOException
	 */
	public Place getPlaceById(int id) throws DAOException;

	/**
	 * Get all places
	 * 
	 * @return
	 * @throws DAOException
	 */
	public List<Place> getPlacesList() throws DAOException;

}

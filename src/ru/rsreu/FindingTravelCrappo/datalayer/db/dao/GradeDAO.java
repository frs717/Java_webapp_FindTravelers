package ru.rsreu.FindingTravelCrappo.datalayer.db.dao;

import java.util.List;

import ru.rsreu.FindingTravelCrappo.datalayer.data.Grade;
import ru.rsreu.FindingTravelCrappo.datalayer.db.DAOException;

/**
 * DAO interface for grade's requests
 * 
 * @author Anastasia
 *
 */
public interface GradeDAO {

	/**
	 * Rait travel by user
	 * 
	 * @param idUser
	 * @param idTravel
	 * @param mark
	 * @throws DAOException
	 */
	void grade(int idUser, int idTravel, int mark) throws DAOException;

	/**
	 * Check u
	 * 
	 * @param idTravel
	 * @param idUser
	 * @return
	 * @throws DAOException
	 */
	boolean checkUser(int idTravel, int idUser) throws DAOException;

	/**
	 * Get grades by travel
	 * 
	 * @param idTravel
	 * @return
	 * @throws DAOException
	 */
	List<Grade> getGrades(int idTravel) throws DAOException;

	/**
	 * Get average travel mark
	 * 
	 * @param idTravel
	 * @return
	 * @throws DAOException
	 */
	float getAverage(int idTravel) throws DAOException;

}

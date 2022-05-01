package ru.rsreu.FindingTravelCrappo.datalayer.db.dao;

import ru.rsreu.FindingTravelCrappo.datalayer.data.Car;
import ru.rsreu.FindingTravelCrappo.datalayer.db.DAOException;

import java.util.List;

/**
 * DAO interface for car's requests
 * 
 * @author Anastasia
 *
 */
public interface CarDAO {

	/**
	 * Get car by user
	 * 
	 * @param id
	 * @return
	 * @throws DAOException
	 */
	public Car getCarByIdUser(int id) throws DAOException;

	/**
	 * Add car
	 * 
	 * @param car
	 * @throws DAOException
	 */
	public void addCar(Car car) throws DAOException;

	/**
	 * delete car
	 * 
	 * @param id
	 * @throws DAOException
	 */
	public void deleteCarById(int id) throws DAOException;

}

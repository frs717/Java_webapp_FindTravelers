package ru.rsreu.FindingTravelCrappo.datalayer.db.oracledb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import ru.rsreu.FindingTravelCrappo.datalayer.db.DAOException;

public class OracleConnectionFactory {
	private static OracleConnectionFactory instance;
	private Connection connection;

	private OracleConnectionFactory() throws SQLException, DAOException {
		getConnection();

	}

	public static OracleConnectionFactory getInstance() {
		if (instance == null) {
			try {
				instance = new OracleConnectionFactory();
			} catch (SQLException | DAOException e) {
				System.err.println(e);
			}
		}
		return instance;
	}

	public Connection getConnection() throws SQLException, DAOException {
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			String user = "SYS as SYSDBA";
			String password = "111";
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
				connection = DriverManager.getConnection(url, user, password);
			} catch (SQLException | ClassNotFoundException e) {
				System.err.println(e);
			}	
		return connection;
	}
}

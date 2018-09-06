package com.cg.uas.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import com.cg.uas.exception.UniversityException;

import oracle.jdbc.pool.OracleDataSource;



public class DBConnection {
	private static Connection conn = null;
	private static DBConnection instance = null;
	private static Properties props = null;
	private static OracleDataSource dataSource = null;

	/*****************************************************************************
	 * - @throws DonorException - Private Constructor - Desc:Loads the
	 * jdbc.properties file and Driver Class and gets the connection
	 * 
	 * @throws DonorException
	 ********************************************************************************/
	private DBConnection() throws UniversityException {
		try {
			props = loadProperties();
			dataSource = prepareDataSource();
		} catch (IOException e) {
			throw new UniversityException(" Could not read the database details from properties file ");
		} catch (SQLException e) {
			throw new UniversityException(e.getMessage());
		}

	}

	/*****************************************************************
	 * - Method Name:getInstance() - Input Parameters : - Return Type :
	 * DBConnection instance - Throws : DonorException - Author : IGATE -
	 * Creation Date : 09/11/2014 - Description : Singleton and Thread safe
	 * class
	 *******************************************************************/
	public static DBConnection getInstance() throws UniversityException {
		synchronized (DBConnection.class) {
			if (instance == null) {
				instance = new DBConnection();
			}
		}
		return instance;
	}

	/****************************************************
	 * - Method Name:getConnection() - Return Type : Connection object - Author
	 * : IGATE - Creation Date : 09/11/2014 - Description : Returns connection
	 * object
	 ****************************************************/

	public Connection getConnection() throws UniversityException {
		try {

			conn = dataSource.getConnection();

		} catch (SQLException e) {
			throw new UniversityException(" Database connection problem");
		}
		return conn;
	}

	/****************************************************
	 * - Method Name:loadProperties() 
	 * - Return Type : Properties object 
	 * - Author : IGATE 
	 * - Creation Date : 09/11/2014 
	 * - Description : Returns Properties
	 * object
	 ****************************************************/
	private Properties loadProperties() throws IOException {

		if (props == null) {
			Properties newProps = new Properties();
			String fileName = "src/jdbc.properties";

			InputStream inputStream = new FileInputStream(fileName);
			newProps.load(inputStream);

			inputStream.close();

			return newProps;
		} else {
			return props;
		}
	}

	/****************************************************
	 * - Method Name:prepareDataSource()
	 * - Return Type : OracleDataSource object 
	 * - Author	     : cg 
	 * - Creation Date : 09/11/2014 
	 * - Description : Returns OracleDataSource object
	 ****************************************************/
	private OracleDataSource prepareDataSource() throws SQLException {

		if (dataSource == null) {
			if (props != null) {
				String connectionURL = props.getProperty("dburl");
				String username = props.getProperty("username");
				String password = props.getProperty("password");

				dataSource = new OracleDataSource();

				dataSource.setURL(connectionURL);
				dataSource.setUser(username);
				dataSource.setPassword(password);
			}
		}
		return dataSource;
	}
}

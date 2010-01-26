package org.simpl.core.datasource;

import java.sql.Connection;

import commonj.sdo.DataObject;


/**
 * <p>
 * Defines the methods that a data source service provider must implement 
 * to work with underlying data source.
 * </p>
 * 
 * @author hahnml
 */
public interface IDatasourceService {

	/**
	 * Opens a connection to a given data source.
	 * 
	 * @param dsAddress The address of the datasource.
	 * @return the opened connection.
	 * @throws ConnectionException if any connection problem occurs.
	 */
	public Connection openConnection(String dsAddress) throws ConnectionException;
	
	/**
	 * Closes an open connection to a given data source.
	 * 
	 * @param connection to close.
	 * @return If the connection is closed.
	 * @throws ConnectionException if any connection problem occurs.
	 */
	public boolean closeConnection(Connection connection) throws ConnectionException;
	
	
	/**
	 * This method is used to query data from a datasource.
	 * 
	 * @param dsAddress of the datasource to connect to.
	 * @param statement to execute.
	 * @return The queried data.
	 * @throws ConnectionException if any connection problem occurs.
	 */
	public DataObject queryData(String dsAddress, String statement) throws ConnectionException;
	
	/**
	 * Supports all Data Manipulation Language (DML)-Statements 
	 * like INSERT, UPDATE and DELETE.
	 * 
	 * @param dsAddress of the datasource to connect to.
	 * @param statement DML-Statement to execute.
	 * @param data which is used in the manipulation statement, e.g. the result
	 * of a query as input of an insert statement.
	 * @return If the manipulation of the data was successfully.
	 * @throws ConnectionException 
	 */
	public boolean manipulateData(String dsAddress, String statement, DataObject data) throws ConnectionException;
	
	/**
	 * Supports all Data Definition Language (DDL)-Statements 
	 * like CREATE, DROP and ALTER.
	 * 
	 * @param dsAddress of the datasource to connect to.
	 * @param statement DDL-Statement to execute.
	 * @return If the data definition was successfully.
	 * @throws ConnectionException 
	 */
	public boolean defineData(String dsAddress, String statement) throws ConnectionException;
}
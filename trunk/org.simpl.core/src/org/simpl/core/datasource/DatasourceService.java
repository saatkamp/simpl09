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
public interface DatasourceService {

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
	 * Sends a statement to a connected data source.
	 * 
	 * @param dsAddress The address of the datasource.
	 * @param statement to send.
	 * @return If the statement was correctly send 
	 * 		   and processed on the data source.
	 * @throws ConnectionException if any connection problem occurs.
	 * @throws DataException if statement processing fails.
	 */
	public boolean sendStatement(String dsAddress, String statement) 
			throws ConnectionException, DataException;
	
	/**
	 * Sends a statement and corresponding data to a connected data source.
	 * 
	 * @param dsAddress The address of the datasource.
	 * @param statement to send.
	 * @param data for processing the statement on the data source.
	 * @return If the statement was correctly send 
	 * 		   and processed on the data source.
	 * @throws ConnectionException if any connection problem occurs.
	 * @throws DataException if statement processing fails.
	 */
	public boolean sendStatement(String dsAddress, String statement, DataObject data)
			throws ConnectionException, DataException;
	
	/**
	 * Sends and executes a statement on a connected data source.
	 * 
	 * @param dsAddress The address of the datasource.
	 * @param statement to send.
	 * @return The data which is processed by the statement from the data source.
	 * @throws ConnectionException if any connection problem occurs.
	 * @throws DataException if statement processing fails.
	 */
	public DataObject executeStatement(String dsAddress, String statement)
			throws ConnectionException, DataException;
	
	/**
	 * Sends and executes a statement 
	 * and corresponding data to a connected data source.
	 * 
	 * @param dsAddress The address of the datasource.
	 * @param statement to send.
	 * @param data for processing the statement on the data source.
	 * @return The data which is processed by the statement from the data source.
	 * @throws ConnectionException if any connection problem occurs.
	 * @throws DataException if statement processing fails.
	 */
	public DataObject executeStatement(String dsAddress, String statement, DataObject data)
			throws ConnectionException, DataException;
}

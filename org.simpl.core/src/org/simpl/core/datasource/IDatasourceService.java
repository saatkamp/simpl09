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
	 * This method is used to query data out of a datasource.
	 * 
	 * @param dsAddress
	 * @param statement
	 * @return
	 * @throws ConnectionException 
	 */
	public DataObject queryData(String dsAddress, String statement) throws ConnectionException;
	
	/**
	 * Supports all DML-Statements like INSERT, UPDATE and DELETE.
	 * 
	 * @param dsAddress
	 * @param statement
	 * @param data
	 * @return
	 */
	public boolean manipulateData(String dsAddress, String statement, DataObject data);
	
	/**
	 * Supports all DDL-Statements like CREATE, DROP and ALTER.
	 * 
	 * @param dsAddress
	 * @param statement
	 * @return
	 * @throws ConnectionException 
	 */
	public boolean defineData(String dsAddress, String statement) throws ConnectionException;
}
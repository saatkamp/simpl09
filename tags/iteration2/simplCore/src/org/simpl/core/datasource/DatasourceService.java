package org.simpl.core.datasource;

import org.eclipse.emf.ecore.EObject;


/**
 * <p>
 * Defines the methods that a data source service provider must implement 
 * to work with underlying data source.
 * </p>
 * 
 * @author hahnml, StuProA: SIMPL, 25.11.2009
 */
public interface DatasourceService {

	/**
	 * Opens a connection to a given data source.
	 * 
	 * @return If the connection is opened.
	 * @throws ConnectionException if any connection problem occurs.
	 */
	public boolean openConnection() throws ConnectionException;
	
	/**
	 * Closes an open connection to a given data source.
	 * 
	 * @return If the connection is closed.
	 * @throws ConnectionException if any connection problem occurs.
	 */
	public boolean closeConnection() throws ConnectionException;

	/**
	 * Sends a statement to a connected data source.
	 * 
	 * @param statement to send.
	 * @return If the statement was correctly send 
	 * 		   and processed on the data source.
	 * @throws ConnectionException if any connection problem occurs.
	 * @throws DataException if statement processing fails.
	 */
	public boolean sendStatement(String statement) 
			throws ConnectionException, DataException;
	
	/**
	 * Sends a statement and corresponding data to a connected data source.
	 * 
	 * @param statement to send.
	 * @param data for processing the statement on the data source.
	 * @return If the statement was correctly send 
	 * 		   and processed on the data source.
	 * @throws ConnectionException if any connection problem occurs.
	 * @throws DataException if statement processing fails.
	 */
	public boolean sendStatement(String statement, EObject data)
			throws ConnectionException, DataException;
	
	/**
	 * Sends and executes a statement on a connected data source.
	 * 
	 * @param statement to send.
	 * @return The data which is processed by the statement from the data source.
	 * @throws ConnectionException if any connection problem occurs.
	 * @throws DataException if statement processing fails.
	 */
	public EObject executeStatement(String statement)
			throws ConnectionException, DataException;
	
	/**
	 * Sends and executes a statement 
	 * and corresponding data to a connected data source.
	 * 
	 * @param statement to send.
	 * @param data for processing the statement on the data source.
	 * @return The data which is processed by the statement from the data source.
	 * @throws ConnectionException if any connection problem occurs.
	 * @throws DataException if statement processing fails.
	 */
	public EObject executeStatement(String statement, EObject data)
			throws ConnectionException, DataException;
}
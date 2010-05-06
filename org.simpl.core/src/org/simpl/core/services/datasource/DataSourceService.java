package org.simpl.core.services.datasource;

import org.simpl.core.services.datasource.exceptions.ConnectionException;

import commonj.sdo.DataObject;

/**
 * <b>Purpose:</b>Defines the methods that a data source service must implement to work
 * with the underlying data source.<br>
 * <b>Description:</b> <br>
 * <b>Copyright:</b> <br>
 * <b>Company:</b>SIMPL<br>
 * 
 * @author schneimi
 * @version $Id: DataSourceService.java 1122 2010-04-18 14:48:40Z
 *          michael.schneidt@arcor.de $<br>
 * @link http://code.google.com/p/simpl09/
 */
public interface DataSourceService {
  /**
   * Executes a statement on the data source.
   * 
   * @param dataSource
   * @param statement
   * @return <i>true</i> if the statement was successfully executed, <i>false</i>
   *         otherwise
   * @throws ConnectionException
   */
  public boolean executeStatement(DataSource dataSource, String statement)
      throws ConnectionException;

  /**
   * Retrieves data from the data source specified by a statement.
   * 
   * @param dataSource
   * @param statement
   * @return the retrieved data as SDO
   * @throws ConnectionException
   */
  public DataObject retrieveData(DataSource dataSource, String statement)
      throws ConnectionException;

  /**
   * Writes back data that was retrieved by {@link #retrieveData(DataSource, String)}.
   * 
   * @param dataSource
   * @param data
   * @return <i>true</i> if the data was successfully written back, <i>false</i> otherwise
   * @throws ConnectionException
   */
  public boolean writeBack(DataSource dataSource, DataObject data)
      throws ConnectionException;

  /**
   * Writes new data to the data source.
   * 
   * @param dataSource
   * @param data
   * @param target
   * @return <i>true</i> if the data was successfully written, <i>false</i> otherwise
   */
  public boolean writeData(DataSource dataSource, DataObject data)
      throws ConnectionException;

  /**
   * Deposits the data that is specified by the statement on the data source. The data is
   * deposit to a target reference that can be used in further statements.
   * 
   * @param dataSource
   * @param statement
   * @param target
   * @return <i>true</i> if the data was successfully deposit, <i>false</i> otherwise
   * @throws ConnectionException
   */
  public boolean depositData(DataSource dataSource, String statement, String target)
      throws ConnectionException;

  /**
   * Returns meta data information about the data source and offers the possibility to
   * filter the data by a XPath expression.
   * 
   * @param dataSource
   * @param filter
   *          XPath expression
   * @return the meta data as SDO
   * @throws ConnectionException
   */
  public DataObject getMetaData(DataSource dataSource, String filter)
      throws ConnectionException;
}
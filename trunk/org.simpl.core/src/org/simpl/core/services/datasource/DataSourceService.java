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
 * 
 * @param <S>
 *          incoming data type
 * @param <T>
 *          outgoing data type
 */
public interface DataSourceService<S, T> {
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
  public T retrieveData(DataSource dataSource, String statement)
      throws ConnectionException;

  /**
   * Writes back data that was retrieved by {@link #retrieveData(DataSource, String)}.
   * 
   * @param dataSource
   * @param data
   * @return <i>true</i> if the data was successfully written back, <i>false</i> otherwise
   * @throws ConnectionException
   */
  public boolean writeBack(DataSource dataSource, S data) throws ConnectionException;

  /**
   * Writes new data to the data source.
   * 
   * @param dataSource
   * @param data
   * @param target
   * @return <i>true</i> if the data was successfully written, <i>false</i> otherwise
   */
  public boolean writeData(DataSource dataSource, S data, String target)
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

  /**
   * Creates a new target (e.g. a database table) that is used to write new data as
   * required by {@link #depositData(DataSource, String, String)} and
   * {@link #writeData(DataSource, Object, String)}.
   * 
   * @param dataSource
   * @param dataObject
   * @param target
   * @return
   */
  public boolean createTarget(DataSource dataSource, DataObject dataObject, String target)
      throws ConnectionException;
}
package org.simpl.core.connector;

import org.simpl.core.exceptions.ConnectionException;
import org.simpl.resource.management.data.DataSource;

import commonj.sdo.DataObject;

/**
 * <b>Purpose:</b>Defines the methods that a connector must implement to work with a data
 * source.<br>
 * <b>Description:</b> <br>
 * <b>Copyright:</b>Licensed under the Apache License, Version 2.0.
 * http://www.apache.org/licenses/LICENSE-2.0<br>
 * <b>Company:</b>SIMPL<br>
 * 
 * @author schneimi
 * @version $Id: Connector.java 1788 2011-04-10 12:30:51Z michael.schneidt@arcor.de $<br>
 * @link http://code.google.com/p/simpl09/
 * 
 * @param <S>
 *          incoming data type
 * @param <T>
 *          outgoing data type
 */
public interface Connector<S, T> {
  /**
   * Executes a statement on the data source.
   * 
   * @param dataSource
   * @param statement
   * @return <i>true</i> if the statement was successfully executed, <i>false</i>
   *         otherwise
   * @throws ConnectionException
   */
  public boolean issueCommand(DataSource dataSource, String statement)
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
   * Writes data to the data source.
   * 
   * @param dataSource
   * @param data
   * @param target
   * @return <i>true</i> if the data was successfully written, <i>false</i> otherwise
   */
  public boolean writeDataBack(DataSource dataSource, S data, String target)
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
  public boolean queryData(DataSource dataSource, String statement, String target)
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
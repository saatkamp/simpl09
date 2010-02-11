package org.simpl.core.datasource;

import java.sql.Connection;

import org.simpl.core.datasource.exceptions.ConnectionException;

import commonj.sdo.DataObject;

/**
 * <p>
 * Defines the methods that a data source service provider must implement to work with
 * underlying data source.
 * </p>
 * 
 * @author hahnml, StuProA: SIMPL, 25.11.2009
 */
public interface DatasourceService {
  public Connection openConnection(String dsAddress) throws ConnectionException;

  public boolean closeConnection(Connection connection) throws ConnectionException;

  public DataObject queryData(String dsAddress, String statement)
      throws ConnectionException;

  public boolean depositData(String dsAddress, String statement, String target)
      throws ConnectionException;

  public boolean defineData(String dsAddress, String statement)
      throws ConnectionException;

  public boolean manipulateData(String dsAddress, String statement, DataObject data)
      throws ConnectionException;

  public DataObject getMetaData(String dsAddress) throws ConnectionException;
}
package org.simpl.core.datasource;

import java.sql.Connection;

import org.simpl.core.datasource.exceptions.ConnectionException;

import commonj.sdo.DataObject;

/**
 * <b>Purpose:</b> Defines the methods that a datasource service must implement to work
 * with the underlying datasource. <br>
 * <b>Description:</b> TODO: Beschreibung, auch von allen Funktionen.<br>
 * <b>Copyright:</b> <br>
 * <b>Company:</b> SIMPL<br>
 * 
 * @author hahnml
 * @version $Id$<br>
 * @link http://code.google.com/p/simpl09/
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
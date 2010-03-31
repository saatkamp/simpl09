package org.simpl.core.services.datasource;

import org.simpl.core.services.datasource.exceptions.ConnectionException;

import commonj.sdo.DataObject;

/**
 * <b>Purpose:</b> Defines the methods that a data source service must implement to work
 * with the underlying data source. <br>
 * <b>Description:</b> TODO: Beschreibung, auch von allen Funktionen.<br>
 * <b>Copyright:</b> <br>
 * <b>Company:</b> SIMPL<br>
 * 
 * @author hahnml
 * @version $Id$<br>
 * @link http://code.google.com/p/simpl09/
 */
public interface DataSourceService {
  public <T> T openConnection(String dsAddress) throws ConnectionException;

  public <T> boolean closeConnection(T connection) throws ConnectionException;

  public DataObject retrieveData(String dsAddress, String statement)
      throws ConnectionException;

  public boolean depositData(String dsAddress, String statement, String target)
      throws ConnectionException;

  public boolean executeStatement(String dsAddress, String statement)
      throws ConnectionException;

  public boolean writeBack(String dsAddress, String statement, DataObject data)
      throws ConnectionException;

  public DataObject getMetaData(String dsAddress) throws ConnectionException;
}
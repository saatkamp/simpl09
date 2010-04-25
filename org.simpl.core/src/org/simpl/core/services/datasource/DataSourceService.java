package org.simpl.core.services.datasource;

import org.simpl.core.services.datasource.auth.Authentication;
import org.simpl.core.services.datasource.exceptions.ConnectionException;

import commonj.sdo.DataObject;

/**
 * <b>Purpose:</b>Defines the methods that a data source service must implement
 * to work with the underlying data source.<br>
 * <b>Description:</b> <br>
 * <b>Copyright:</b> <br>
 * <b>Company:</b>SIMPL<br>
 * 
 * @author hahnml
 * @version $Id: DataSourceService.java 1122 2010-04-18 14:48:40Z
 *          michael.schneidt@arcor.de $<br>
 * @link http://code.google.com/p/simpl09/
 */
public interface DataSourceService {
  public DataObject retrieveData(DataSource source, Authentication auth,
      String statement) throws ConnectionException;

  public boolean depositData(DataSource source, Authentication auth,
      String statement, String target) throws ConnectionException;

  public boolean executeStatement(DataSource source, Authentication auth,
      String statement) throws ConnectionException;

  public boolean writeBack(DataSource source, Authentication auth,
      DataObject data) throws ConnectionException;

  public DataObject getMetaData(DataSource source, Authentication auth,
      String filter) throws ConnectionException;
}
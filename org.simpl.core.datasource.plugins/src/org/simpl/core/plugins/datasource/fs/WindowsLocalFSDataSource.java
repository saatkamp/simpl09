package org.simpl.core.plugins.datasource.fs;

import java.sql.Connection;

import org.simpl.core.plugins.DataSourcePlugin;
import org.simpl.core.services.datasource.exceptions.ConnectionException;

import commonj.sdo.DataObject;

/**
 * <b>Purpose:</b> <br>
 * <b>Description:</b> <br>
 * <b>Copyright:</b> <br>
 * <b>Company:</b> SIMPL<br>
 * 
 * @author Michael Schneidt <michael.schneidt@arcor.de><br>
 * @version $Id$<br>
 * @link http://code.google.com/p/simpl09/
 */
public class WindowsLocalFSDataSource extends DataSourcePlugin {
  public WindowsLocalFSDataSource() {
    this.setType("Local Filesystem");
    this.setMetaDataType("tFilesystemMetaData");
    this.addSubtype("Windows");
    this.addLanguage("Windows", "Shell Command");
  }

  /*
   * (non-Javadoc)
   * @see org.simpl.core.datasource.DatasourceService#closeConnection(java.sql.Connection)
   */
  @Override
  public boolean closeConnection(Connection arg0) throws ConnectionException {
    return true;
  }

  /*
   * (non-Javadoc)
   * @see org.simpl.core.datasource.DatasourceService#defineData(java.lang.String,
   * java.lang.String)
   */
  @Override
  public boolean defineData(String arg0, String arg1) throws ConnectionException {
    // TODO Auto-generated method stub
    return false;
  }

  /*
   * (non-Javadoc)
   * @see org.simpl.core.datasource.DatasourceService#depositData(java.lang.String,
   * java.lang.String, java.lang.String)
   */
  @Override
  public boolean depositData(String arg0, String arg1, String arg2)
      throws ConnectionException {
    // TODO Auto-generated method stub
    return false;
  }

  /*
   * (non-Javadoc)
   * @see org.simpl.core.datasource.DatasourceService#getMetaData(java.lang.String)
   */
  @Override
  public DataObject getMetaData(String arg0) throws ConnectionException {
    // TODO Auto-generated method stub
    return null;
  }

  /*
   * (non-Javadoc)
   * @see org.simpl.core.datasource.DatasourceService#manipulateData(java.lang.String,
   * java.lang.String, commonj.sdo.DataObject)
   */
  @Override
  public boolean manipulateData(String arg0, String arg1, DataObject arg2)
      throws ConnectionException {
    // TODO Auto-generated method stub
    return false;
  }

  /*
   * (non-Javadoc)
   * @see org.simpl.core.datasource.DatasourceService#openConnection(java.lang.String)
   */
  @Override
  public Connection openConnection(String arg0) throws ConnectionException {
    return null;
  }

  /*
   * (non-Javadoc)
   * @see org.simpl.core.datasource.DatasourceService#queryData(java.lang.String,
   * java.lang.String)
   */
  @Override
  public DataObject queryData(String arg0, String arg1) throws ConnectionException {
    // TODO Auto-generated method stub
    return null;
  }
}

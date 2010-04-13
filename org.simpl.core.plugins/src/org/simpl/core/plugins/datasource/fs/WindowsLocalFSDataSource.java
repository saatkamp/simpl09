package org.simpl.core.plugins.datasource.fs;

import org.simpl.core.plugins.datasource.DataSourcePlugin;
import org.simpl.core.services.datasource.exceptions.ConnectionException;

import commonj.sdo.DataObject;

/**
 * <b>Purpose:</b> <br>
 * <b>Description:</b> <br>
 * <b>Copyright:</b> <br>
 * <b>Company:</b> SIMPL<br>
 * 
 * @author Michael Schneidt <michael.schneidt@arcor.de><br>
 * @version $Id: WindowsLocalFSDataSource.java 1014 2010-03-29 09:16:08Z
 *          michael.schneidt@arcor.de $<br>
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
   * @see
   * org.simpl.core.services.datasource.DataSourceService#openConnection(java.lang.String)
   */
  @Override
  public <T> T openConnection(String arg0) throws ConnectionException {
    // TODO Auto-generated method stub
    return null;
  }

  /*
   * (non-Javadoc)
   * @see org.simpl.core.datasource.DatasourceService#closeConnection(java.sql.Connection)
   */
  @Override
  public <T> boolean closeConnection(T arg0) throws ConnectionException {
    return true;
  }

  /*
   * (non-Javadoc)
   * @see org.simpl.core.datasource.DatasourceService#defineData(java.lang.String,
   * java.lang.String)
   */
  @Override
  public boolean executeStatement(String arg0, String arg1) throws ConnectionException {
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
   * @see org.simpl.core.datasource.DatasourceService#manipulateData(java.lang.String, commonj.sdo.DataObject)
   */
  @Override
  public boolean writeBack(String arg0, DataObject arg2)
      throws ConnectionException {
    // TODO Auto-generated method stub
    return false;
  }

  /*
   * (non-Javadoc)
   * @see org.simpl.core.datasource.DatasourceService#queryData(java.lang.String,
   * java.lang.String)
   */
  @Override
  public DataObject retrieveData(String arg0, String arg1) throws ConnectionException {
    // TODO Auto-generated method stub
    return null;
  }
}

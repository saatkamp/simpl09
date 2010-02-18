package org.simpl.core.plugins.datasource.sndb;

import java.sql.Connection;

import org.simpl.core.datasource.DatasourceServicePlugin;
import org.simpl.core.datasource.exceptions.ConnectionException;

import commonj.sdo.DataObject;

public class SNDBDatasourceService extends DatasourceServicePlugin {
  public SNDBDatasourceService() {
    this.setDatasourceType("Sensornet");
    this.setDatasourceMetaDataType("tDatabaseMetaData");
    this.addDatasourceSubtype("TinyDB");
    this.addDatasourceLanguage("TinyDB", "TinySQL");
  }

  /*
   * (non-Javadoc)
   * @see org.simpl.core.datasource.DatasourceService#closeConnection(java.sql.Connection)
   */
  @Override
  public boolean closeConnection(Connection connection) throws ConnectionException {
    // TODO Auto-generated method stub
    return false;
  }

  /*
   * (non-Javadoc)
   * @see org.simpl.core.datasource.DatasourceService#defineData(java.lang.String,
   * java.lang.String)
   */
  @Override
  public boolean defineData(String dsAddress, String statement)
      throws ConnectionException {
    // TODO Auto-generated method stub
    return false;
  }

  /*
   * (non-Javadoc)
   * @see org.simpl.core.datasource.DatasourceService#manipulateData(java.lang.String,
   * java.lang.String, org.apache.tuscany.sdo.model.DataObject)
   */
  @Override
  public boolean manipulateData(String dsAddress, String statement, DataObject data)
      throws ConnectionException {
    // TODO Auto-generated method stub
    return false;
  }

  /*
   * (non-Javadoc)
   * @see org.simpl.core.datasource.DatasourceService#openConnection(java.lang.String)
   */
  @Override
  public Connection openConnection(String dsAddress) throws ConnectionException {
    // TODO Auto-generated method stub
    return null;
  }

  /*
   * (non-Javadoc)
   * @see org.simpl.core.datasource.DatasourceService#queryData(java.lang.String,
   * java.lang.String)
   */
  @Override
  public DataObject queryData(String dsAddress, String statement)
      throws ConnectionException {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public boolean depositData(String dsAddress, String statement, String target)
      throws ConnectionException {
    // TODO Auto-generated method stub
    return false;
  }

  /*
   * (non-Javadoc)
   * @see org.simpl.core.datasource.DatasourceService#getMetaData(java.lang.String)
   */
  @Override
  public DataObject getMetaData(String dsAddress) throws ConnectionException {
    // TODO Auto-generated method stub
    return null;
  }
}
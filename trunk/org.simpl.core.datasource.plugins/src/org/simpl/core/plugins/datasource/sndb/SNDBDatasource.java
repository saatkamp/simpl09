package org.simpl.core.plugins.datasource.sndb;

import java.sql.Connection;

import org.simpl.core.plugins.datasource.DataSourcePlugin;
import org.simpl.core.services.datasource.exceptions.ConnectionException;

import commonj.sdo.DataObject;

public class SNDBDatasource extends DataSourcePlugin {
  public SNDBDatasource() {
    this.setType("Sensornet");
    this.setMetaDataType("tDatabaseMetaData");
    this.addSubtype("TinyDB");
    this.addLanguage("TinyDB", "TinySQL");
  }

  /*
   * (non-Javadoc)
   * @see
   * org.simpl.core.services.datasource.DataSourceService#closeConnection(java.lang.Object
   * )
   */
  @SuppressWarnings("hiding")
  @Override
  public <Connection> boolean closeConnection(Connection connection) {
    // TODO Auto-generated method stub
    return false;
  }

  /*
   * (non-Javadoc)
   * @see org.simpl.core.datasource.DatasourceService#defineData(java.lang.String,
   * java.lang.String)
   */
  @Override
  public boolean executeStatement(String dsAddress, String statement)
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
  public boolean writeBack(String dsAddress, String statement, DataObject data)
      throws ConnectionException {
    // TODO Auto-generated method stub
    return false;
  }

  /*
   * (non-Javadoc)
   * @see
   * org.simpl.core.services.datasource.DataSourceService#openConnection(java.lang.String)
   */
  @SuppressWarnings("unchecked")
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
  public DataObject retrieveData(String dsAddress, String statement)
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
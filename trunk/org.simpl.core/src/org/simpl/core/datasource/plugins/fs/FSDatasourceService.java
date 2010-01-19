package org.simpl.core.datasource.plugins.fs;

import java.sql.Connection;
import java.util.Arrays;

import org.simpl.core.datasource.DatasourceServicePlugin;
import org.simpl.core.datasource.exceptions.ConnectionException;

import commonj.sdo.DataObject;

public class FSDatasourceService extends DatasourceServicePlugin {
  /**
   * 
   */
  public FSDatasourceService() {
    this.datasourceType = "filesystem";
    this.datasourceSubtypes.add("EXT3");
    this.datasourceSubtypes.add("NTFS");
    this.datasourceSubtypes.add("FAT32");
    this.datasourceLanguages.put("EXT3", Arrays.asList(new String[] {"OSCall"}));
    this.datasourceLanguages.put("NTFS", Arrays.asList(new String[] {"OSCall"}));
    this.datasourceLanguages.put("FAT32", Arrays.asList(new String[] {"OSCall"}));
  }
  
  /* (non-Javadoc)
   * @see org.simpl.core.datasource.DatasourceService#closeConnection(java.sql.Connection)
   */
  @Override
  public boolean closeConnection(Connection connection) throws ConnectionException {
    // TODO Auto-generated method stub
    return false;
  }

  /* (non-Javadoc)
   * @see org.simpl.core.datasource.DatasourceService#defineData(java.lang.String, java.lang.String)
   */
  @Override
  public boolean defineData(String dsAddress, String statement)
      throws ConnectionException {
    // TODO Auto-generated method stub
    return false;
  }

  /* (non-Javadoc)
   * @see org.simpl.core.datasource.DatasourceService#manipulateData(java.lang.String, java.lang.String, commonj.sdo.DataObject)
   */
  @Override
  public boolean manipulateData(String dsAddress, String statement, DataObject data)
      throws ConnectionException {
    // TODO Auto-generated method stub
    return false;
  }

  /* (non-Javadoc)
   * @see org.simpl.core.datasource.DatasourceService#openConnection(java.lang.String)
   */
  @Override
  public Connection openConnection(String dsAddress) throws ConnectionException {
    // TODO Auto-generated method stub
    return null;
  }

  /* (non-Javadoc)
   * @see org.simpl.core.datasource.DatasourceService#queryData(java.lang.String, java.lang.String)
   */
  @Override
  public DataObject queryData(String dsAddress, String statement)
      throws ConnectionException {
    // TODO Auto-generated method stub
    return null;
  }
}
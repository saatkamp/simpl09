package org.simpl.core.storage;

import org.simpl.core.datasource.exceptions.ConnectionException;
import org.simpl.core.datasource.exceptions.DataException;

import commonj.sdo.DataObject;

public class StorageServiceImpl implements StorageService {
  /*
   * (non-Javadoc)
   * @see org.simpl.core.administration.AdministrationService#loadACDefaultSettings()
   */
  @Override
  public DataObject loadACDefaultSettings() throws ConnectionException, DataException {
    // TODO Auto-generated method stub
    return null;
  }

  /*
   * (non-Javadoc)
   * @see org.simpl.core.administration.AdministrationService#loadACSettings()
   */
  @Override
  public DataObject loadACSettings() throws ConnectionException, DataException {
    // TODO Auto-generated method stub
    return null;
  }

  /*
   * (non-Javadoc)
   * @see
   * org.simpl.core.administration.AdministrationService#loadServiceSettings(java.lang
   * .String)
   */
  @Override
  public DataObject loadServiceSettings(String serviceName) throws ConnectionException,
      DataException {
    // TODO Auto-generated method stub
    return null;
  }

  /*
   * (non-Javadoc)
   * @see
   * org.simpl.core.administration.AdministrationService#saveACSettings(commonj.sdo.DataObject)
   */
  @Override
  public boolean saveACSettings(DataObject settings) throws ConnectionException,
      DataException {
    // TODO Auto-generated method stub
    return false;
  }

  /*
   * (non-Javadoc)
   * @see
   * org.simpl.core.administration.AdministrationService#saveServiceSettings(java.lang
   * .String, commonj.sdo.DataObject)
   */
  @Override
  public boolean saveServiceSettings(String serviceName, DataObject settings)
      throws ConnectionException, DataException {
    // TODO Auto-generated method stub
    return false;
  }
}
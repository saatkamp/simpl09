package org.simpl.core.storage;

import org.eclipse.emf.ecore.EObject;
import org.simpl.core.datasource.exceptions.ConnectionException;
import org.simpl.core.datasource.exceptions.DataException;

public class StorageServiceImpl implements StorageService {
  /*
   * (non-Javadoc)
   * @see org.simpl.core.administration.AdministrationService#loadACDefaultSettings()
   */
  @Override
  public EObject loadACDefaultSettings() throws ConnectionException, DataException {
    // TODO Auto-generated method stub
    return null;
  }

  /*
   * (non-Javadoc)
   * @see org.simpl.core.administration.AdministrationService#loadACSettings()
   */
  @Override
  public EObject loadACSettings() throws ConnectionException, DataException {
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
  public EObject loadServiceSettings(String serviceName) throws ConnectionException,
      DataException {
    // TODO Auto-generated method stub
    return null;
  }

  /*
   * (non-Javadoc)
   * @see
   * org.simpl.core.administration.AdministrationService#saveACSettings(org.eclipse.emf
   * .ecore.EObject)
   */
  @Override
  public boolean saveACSettings(EObject settings) throws ConnectionException,
      DataException {
    // TODO Auto-generated method stub
    return false;
  }

  /*
   * (non-Javadoc)
   * @see
   * org.simpl.core.administration.AdministrationService#saveServiceSettings(java.lang
   * .String, org.eclipse.emf.ecore.EObject)
   */
  @Override
  public boolean saveServiceSettings(String serviceName, EObject settings)
      throws ConnectionException, DataException {
    // TODO Auto-generated method stub
    return false;
  }
}
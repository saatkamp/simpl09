package org.simpl.core.services.storage;

import org.simpl.core.services.datasource.exceptions.ConnectionException;
import org.simpl.core.services.datasource.exceptions.DataException;

import commonj.sdo.DataObject;

public interface StorageService {
  /**
   * Saves the settings of the admin console into a given data source.
   * 
   * @param settings
   *          to save, represented as a Service Data Object (SDO).
   * @return If the data was saved without any problems.
   * @throws ConnectionException
   *           if any connection problem occurs.
   * @throws DataException
   *           if storing fails.
   */
  public boolean saveACSettings(DataObject settings) throws ConnectionException,
      DataException;

  /**
   * Loads the last stored settings of the admin console.
   * 
   * @return The stored settings of the admin console in SDO format or the default
   *         settings if loading of the last stored settings fails.
   * @throws ConnectionException
   *           if any connection problem occurs.
   * @throws DataException
   *           if loading fails.
   */
  public DataObject loadACSettings() throws ConnectionException, DataException;

  /**
   * Loads the default settings of the admin console.
   * 
   * @return The default settings in SDO format.
   * @throws ConnectionException
   *           if any connection problem occurs.
   * @throws DataException
   *           if loading fails.
   */
  public DataObject loadACDefaultSettings() throws ConnectionException, DataException;

  /**
   * Stores any type of settings from a service of the SIMPL Core.
   * 
   * @param serviceName
   *          The name of the service, which wants to store its data.
   * @param settings
   *          to store in SDO format.
   * @return If the data was saved without any problems.
   * @throws ConnectionException
   *           if any connection problem occurs.
   * @throws DataException
   *           if storing fails.
   */
  public boolean saveServiceSettings(String serviceName, DataObject settings)
      throws ConnectionException, DataException;

  /**
   * Loads any type of settings from a service of the SIMPL Core.
   * 
   * @param serviceName
   *          The name of the service, which wants to load its data.
   * @return The loaded data or null if loading fails.
   * @throws ConnectionException
   *           if any connection problem occurs.
   * @throws DataException
   *           if loading fails.
   */
  public DataObject loadServiceSettings(String serviceName) throws ConnectionException,
      DataException;
}
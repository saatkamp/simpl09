package org.simpl.core.administration;

import org.eclipse.emf.ecore.EObject;


/**
 * <p>
 * Defines the methods that a data service provider must implement to manage
 * the SIMPL administration data.
 * </p>
 * 
 * @author hahnml, StuProA: SIMPL, 25.11.2009
 */
public interface AdministrationService {

	/**
	 * Saves the settings of the admin console into a given data source.
	 * 
	 * @param settings
	 *            to save, represented as a Service Data Object (SDO).
	 * @return If the data was saved without any problems.
	 * @throws ConnectionException if any connection problem occurs.
	 * @throws DataException if storing fails. 
	 */
	public boolean saveACSettings(EObject settings)
			throws ConnectionException, DataException;

	/**
	 * Loads the last stored settings of the admin console.
	 * 
	 * @return The stored settings of the admin console in SDO format or the
	 *         default settings if loading of the last stored settings fails.
	 * @throws ConnectionException if any connection problem occurs.
	 * @throws DataException if loading fails.
	 */
	public EObject loadACSettings()
			throws ConnectionException, DataException;

	/**
	 * Loads the default settings of the admin console.
	 * 
	 * @return The default settings in SDO format.
	 * @throws ConnectionException if any connection problem occurs.
	 * @throws DataException if loading fails.
	 */
	public EObject loadACDefaultSettings()
			throws ConnectionException, DataException;
	
	/**
	 * Stores any kind of settings from a service of the SIMPL Core.
	 * 
	 * @param serviceName
	 *            The name of the service, which wants to store its data.
	 * @param settings
	 *            to store in SDO format.
	 * @return If the data was saved without any problems.
	 * @throws ConnectionException if any connection problem occurs.
	 * @throws DataException if storing fails.
	 */
	public boolean saveServiceSettings(String serviceName, EObject settings)
			throws ConnectionException, DataException;

	/**
	 * Loads any kind of settings from a service of the SIMPL Core.
	 * 
	 * @param serviceName
	 *            The name of the service, which wants to load its data.
	 * @return The loaded data or null if loading fails.
	 * @throws ConnectionException if any connection problem occurs.
	 * @throws DataException if loading fails.
	 */
	public EObject loadServiceSettings(String serviceName)
			throws ConnectionException, DataException;

}
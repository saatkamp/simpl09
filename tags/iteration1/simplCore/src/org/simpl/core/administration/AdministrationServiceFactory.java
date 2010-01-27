package org.simpl.core.administration;

import org.simpl.core.administration.derby.DerbyAdministrationServiceFactory;

/**
 * <p>
 * Provides access to a data service provider that can be used to
 * load and store administration data on a data source. The {@link AdministrationService} interface
 * specifies the methods that must be implemented by such a data service provider.
 * </p>
 * <p>
 * This class implements the abstract factory pattern.
 * </p>
 *  
 * @author hahnml, StuProA: SIMPL, 25.11.2009
 */

public abstract class AdministrationServiceFactory {

	/**
	 * Provides access to an instance of an implementation of
	 * this class.
	 * 
	 * @return Instance of an implementation of this class.
	 */
	public static AdministrationServiceFactory getInstance(String kind) {

//		if (kind.contains("derbyDB")) {
//			return DerbyAdministrationServiceFactory.getInstance();
//		}else {
//			
//		}

		// The first release will only support the Apache Derby database
		// to store and load settings.
		return DerbyAdministrationServiceFactory.getInstance();
	}
	
	/**
	 * Provides access to a data service provider for 
	 * loading and storing administration data. 
	 * Such a data service provider implements the {@link AdministrationService}
	 * interface.
	 * 
	 * @return data service provider
	 */
	public abstract AdministrationService getServiceProvider();
}
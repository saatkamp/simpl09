package org.simpl.core.administration.derby;

import org.simpl.core.administration.AdministrationService;
import org.simpl.core.administration.AdministrationServiceFactory;



/**
 * <p>
 * An implementation of the {@link AdministrationServiceFactory} that
 * creates data service provider objects of the type {@link DerbyAdministrationService}.
 * </p>
 * 
 * @author hahnml, StuProA: SIMPL, 25.11.2009
 *
 */
public class DerbyAdministrationServiceFactory extends AdministrationServiceFactory {
	
	/**
	 * Holds an instance of this class.
	 */
	private static DerbyAdministrationServiceFactory instance = null;
	
	/**
	 * Holds an instance of a {@link DerbyAdministrationService} object.
	 */
	private AdministrationService derbyAdminService = null;

	private DerbyAdministrationServiceFactory() {
		super();
	}

	/**
	 * Provides access to an instance of this class.
	 * 
	 * @return Instance of this class.
	 */
	public static DerbyAdministrationServiceFactory getInstance() {
		if (instance == null) {
			instance = new DerbyAdministrationServiceFactory();
		}
		return instance;
	}
	
	/**
	 * Provides access to a data service provider object.In this case
	 * to an object of type {@link DerbyAdministrationService}. 
	 * 
	 * @see org.simpl.core.administration.AdministrationServiceFactory#getServiceProvider()
	 */
	@Override
	public AdministrationService getServiceProvider() {
		if (derbyAdminService == null) {
			derbyAdminService = new DerbyAdministrationService();
		}
		return derbyAdminService;
	}
}
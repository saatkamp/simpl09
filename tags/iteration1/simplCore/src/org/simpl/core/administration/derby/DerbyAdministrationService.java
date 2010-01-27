package org.simpl.core.administration.derby;

import org.eclipse.emf.ecore.EObject;
import org.simpl.core.administration.AdministrationService;
import org.simpl.core.administration.ConnectionException;
import org.simpl.core.administration.DataException;

/**
 * <p>
 * Implements the {@link AdministrationService} interface. The implementation
 * stores and loads administration data on an embedded Apache Derby database.
 * </p>
 * 
 * @author hahnml, StuProA: SIMPL, 25.11.2009
 *
 */
public class DerbyAdministrationService implements AdministrationService {

	/*
	 * @see org.simpl.core.administration.AdministrationService#loadACDefaultSettings()
	 */
	@Override
	public EObject loadACDefaultSettings() throws ConnectionException,
			DataException {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * @see org.simpl.core.administration.AdministrationService#loadACSettings()
	 */
	@Override
	public EObject loadACSettings() throws ConnectionException,
			DataException {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * @see org.simpl.core.administration.AdministrationService#loadServiceSettings(java.lang.String)
	 */
	@Override
	public EObject loadServiceSettings(String serviceName)
			throws ConnectionException, DataException {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * @see org.simpl.core.administration.AdministrationService#saveACSettings(org.eclipse.emf.ecore.sdo.EObject)
	 */
	@Override
	public boolean saveACSettings(EObject settings)
			throws ConnectionException, DataException {
		// TODO Auto-generated method stub
		return false;
	}

	/*
	 * @see org.simpl.core.administration.AdministrationService#saveServiceSettings(java.lang.String, org.eclipse.emf.ecore.sdo.EObject)
	 */
	@Override
	public boolean saveServiceSettings(String serviceName, EObject settings)
			throws ConnectionException, DataException {
		// TODO Auto-generated method stub
		return false;
	}


}


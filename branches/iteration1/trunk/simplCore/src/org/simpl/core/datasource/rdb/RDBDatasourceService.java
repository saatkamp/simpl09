package org.simpl.core.datasource.rdb;

import org.eclipse.emf.ecore.EObject;
import org.simpl.core.datasource.ConnectionException;
import org.simpl.core.datasource.DataException;
import org.simpl.core.datasource.DatasourceService;

/**
 * <p>
 * Implements the {@link DatasourceService} interface. The implementation
 * supports relational databases.
 * </p>
 * 
 * @author hahnml, StuProA: SIMPL, 25.11.2009
 *
 */
public class RDBDatasourceService implements DatasourceService {

	/* (non-Javadoc)
	 * @see org.simpl.core.datasource.DatasourceService#closeConnection()
	 */
	@Override
	public boolean closeConnection() throws ConnectionException {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see org.simpl.core.datasource.DatasourceService#executeStatement(java.lang.String)
	 */
	@Override
	public EObject executeStatement(String statement)
			throws ConnectionException, DataException {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.simpl.core.datasource.DatasourceService#executeStatement(java.lang.String, org.eclipse.emf.ecore.sdo.EObject)
	 */
	@Override
	public EObject executeStatement(String statement, EObject data)
			throws ConnectionException, DataException {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.simpl.core.datasource.DatasourceService#openConnection()
	 */
	@Override
	public boolean openConnection() throws ConnectionException {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see org.simpl.core.datasource.DatasourceService#sendStatement(java.lang.String)
	 */
	@Override
	public boolean sendStatement(String statement) throws ConnectionException,
			DataException {
		// TODO This is only a test implementation!
		return RDBTest.sendStatement(statement);
	}

	/* (non-Javadoc)
	 * @see org.simpl.core.datasource.DatasourceService#sendStatement(java.lang.String, org.eclipse.emf.ecore.sdo.EObject)
	 */
	@Override
	public boolean sendStatement(String statement, EObject data)
			throws ConnectionException, DataException {
		// TODO Auto-generated method stub
		return false;
	}


}


package org.simpl.core.datasource.rdb;

import org.simpl.core.datasource.DatasourceService;
import org.simpl.core.datasource.DatasourceServiceFactory;




/**
 * <p>
 * An implementation of the {@link DatasourceServiceFactory} that
 * creates data source service provider objects of the type {@link RDBDatasourceService}.
 * </p>
 * 
 * @author hahnml, StuProA: SIMPL, 25.11.2009
 *
 */
public class RDBDatasourceServiceFactory extends DatasourceServiceFactory {
	
	/**
	 * Holds an instance of this class.
	 */
	private static RDBDatasourceServiceFactory instance = null;
	
	/**
	 * Holds an instance of a {@link RDBDatasourceService} object.
	 */
	private DatasourceService rdbDatasourceService = null;

	private RDBDatasourceServiceFactory() {
		super();
	}

	/**
	 * Provides access to an instance of this class.
	 * 
	 * @return Instance of this class.
	 */
	public static RDBDatasourceServiceFactory getInstance() {
		if (instance == null) {
			instance = new RDBDatasourceServiceFactory();
		}
		return instance;
	}
	
	/**
	 * Provides access to a data source service provider object.In this case
	 * to an object of type {@link RDBDatasourceService}. 
	 * 
	 * @see org.simpl.core.datasource.DatasourceServiceFactory#getDatasourceProvider()
	 */
	@Override
	public DatasourceService getDatasourceProvider() {
		if (rdbDatasourceService == null) {
			rdbDatasourceService = new RDBDatasourceService();
		}
		return rdbDatasourceService;
	}
}
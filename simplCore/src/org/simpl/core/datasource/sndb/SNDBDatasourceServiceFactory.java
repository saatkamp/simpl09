package org.simpl.core.datasource.sndb;

import org.simpl.core.datasource.DatasourceService;
import org.simpl.core.datasource.DatasourceServiceFactory;



/**
 * <p>
 * An implementation of the {@link DatasourceServiceFactory} that
 * creates data source service provider objects of the type {@link SNDBDatasourceService}.
 * </p>
 * 
 * @author hahnml, StuProA: SIMPL, 25.11.2009
 *
 */
public class SNDBDatasourceServiceFactory extends DatasourceServiceFactory {
	
	/**
	 * Holds an instance of this class.
	 */
	private static SNDBDatasourceServiceFactory instance = null;
	
	/**
	 * Holds an instance of a {@link SNDBDatasourceService} object.
	 */
	private DatasourceService sndbDatasourceService = null;

	private SNDBDatasourceServiceFactory() {
		super();
	}

	/**
	 * Provides access to an instance of this class.
	 * 
	 * @return Instance of this class.
	 */
	public static SNDBDatasourceServiceFactory getInstance() {
		if (instance == null) {
			instance = new SNDBDatasourceServiceFactory();
		}
		return instance;
	}
	
	/**
	 * Provides access to a data source service provider object.In this case
	 * to an object of type {@link SNDBDatasourceService}. 
	 * 
	 * @see org.simpl.core.datasource.DatasourceServiceFactory#getDatasourceProvider()
	 */
	@Override
	public DatasourceService getDatasourceProvider() {
		if (sndbDatasourceService == null) {
			sndbDatasourceService = new SNDBDatasourceService();
		}
		return sndbDatasourceService;
	}
}
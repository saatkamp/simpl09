package org.simpl.core.datasource.fs;



import org.simpl.core.datasource.DatasourceService;
import org.simpl.core.datasource.DatasourceServiceFactory;



/**
 * <p>
 * An implementation of the {@link DatasourceServiceFactory} that
 * creates data source service provider objects of the type {@link FSDatasourceService}.
 * </p>
 * 
 * @author hahnml, StuProA: SIMPL, 25.11.2009
 *
 */
public class FSDatasourceServiceFactory extends DatasourceServiceFactory {
	
	/**
	 * Holds an instance of this class.
	 */
	private static FSDatasourceServiceFactory instance = null;
	
	/**
	 * Holds an instance of a {@link FSDatasourceService} object.
	 */
	private FSDatasourceService fsDatasourceService = null;

	private FSDatasourceServiceFactory() {
		super();
	}

	/**
	 * Provides access to an instance of this class.
	 * 
	 * @return Instance of this class.
	 */
	public static FSDatasourceServiceFactory getInstance() {
		if (instance == null) {
			instance = new FSDatasourceServiceFactory();
		}
		return instance;
	}
	
	/**
	 * Provides access to a data source service provider object.In this case
	 * to an object of type {@link FSDatasourceService}. 
	 * 
	 * @see org.simpl.core.datasource.DatasourceServiceFactory#getDatasourceProvider()
	 */
	@Override
	public DatasourceService getDatasourceProvider() {
		if (fsDatasourceService == null) {
			fsDatasourceService = new FSDatasourceService();
		}
		return fsDatasourceService;
	}
}
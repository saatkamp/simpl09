package org.simpl.core.datasource.xmldb;

import org.simpl.core.datasource.DatasourceService;
import org.simpl.core.datasource.DatasourceServiceFactory;





/**
 * <p>
 * An implementation of the {@link DatasourceServiceFactory} that
 * creates data source service provider objects of the type {@link XMLDBDatasourceService}.
 * </p>
 * 
 * @author hahnml, StuProA: SIMPL, 25.11.2009
 *
 */
public class XMLDBDatasourceServiceFactory extends DatasourceServiceFactory {
	
	/**
	 * Holds an instance of this class.
	 */
	private static XMLDBDatasourceServiceFactory instance = null;
	
	/**
	 * Holds an instance of a {@link XMLDBDatasourceService} object.
	 */
	private DatasourceService xmlDatasourceService = null;

	private XMLDBDatasourceServiceFactory() {
		super();
	}

	/**
	 * Provides access to an instance of this class.
	 * 
	 * @return Instance of this class.
	 */
	public static XMLDBDatasourceServiceFactory getInstance() {
		if (instance == null) {
			instance = new XMLDBDatasourceServiceFactory();
		}
		return instance;
	}
	
	/**
	 * Provides access to a data source service provider object.In this case
	 * to an object of type {@link XMLDBDatasourceService}. 
	 * 
	 * @see org.simpl.core.datasource.DatasourceServiceFactory#getDatasourceProvider()
	 */
	@Override
	public DatasourceService getDatasourceProvider() {
		if (xmlDatasourceService == null) {
			xmlDatasourceService = new XMLDBDatasourceService();
		}
		return xmlDatasourceService;
	}
}
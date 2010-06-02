package org.simpl.core.datasource;

import org.simpl.core.datasource.fs.FSDatasourceServiceFactory;
import org.simpl.core.datasource.rdb.RDBDatasourceServiceFactory;
import org.simpl.core.datasource.sndb.SNDBDatasourceServiceFactory;
import org.simpl.core.datasource.xmldb.XMLDBDatasourceServiceFactory;

/**
 * <p>
 * Provides access to a data source service provider that can be used to
 * work with underlying data sources. The {@link DatasourceService} interface
 * specifies the methods that must be implemented 
 * by such a data source service provider.
 * </p>
 * <p>
 * This class implements the abstract factory pattern.
 * </p>
 *  
 * @author hahnml, StuProA: SIMPL, 25.11.2009
 */

public abstract class DatasourceServiceFactory {

	/**
	 * Provides access to an instance of an implementation of
	 * this class.
	 * 
	 * @return Instance of an implementation of this class.
	 */
	public static DatasourceServiceFactory getInstance(String kind) {
		if (kind.contains("RDB")) {
			return RDBDatasourceServiceFactory.getInstance();
		}else{
			if (kind.contains("XMLDB")) {
				return XMLDBDatasourceServiceFactory.getInstance();
			}else{
				if (kind.contains("SNDB")) {
					return SNDBDatasourceServiceFactory.getInstance();
				}else{
					return FSDatasourceServiceFactory.getInstance();
				}
			}
		}
	}
	
	/**
	 * Provides access to a data source service provider for 
	 * working with underlying data sources. 
	 * Such a data service provider implements the {@link DatasourceService}
	 * interface.
	 * 
	 * @return A data source service provider
	 */
	public abstract DatasourceService getDatasourceProvider();
}
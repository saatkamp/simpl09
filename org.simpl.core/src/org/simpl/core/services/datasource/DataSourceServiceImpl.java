package org.simpl.core.services.datasource;

import java.util.List;

import org.simpl.core.plugins.datasource.DataSourceServicePlugin;
import org.simpl.core.services.dataformat.converter.DataFormatConverter;
import org.simpl.core.services.dataformat.converter.DataFormatConverterProvider;
import org.simpl.core.services.datasource.exceptions.ConnectionException;

import commonj.sdo.DataObject;

/**
 * <b>Purpose:</b> <br>
 * <b>Description:</b> <br>
 * <b>Copyright:</b> <br>
 * <b>Company:</b> SIMPL<br>
 * 
 * @author schneimi<br>
 * @version $Id$<br>
 * @link http://code.google.com/p/simpl09/
 */
public class DataSourceServiceImpl implements DataSourceService {
  private DataSourceService dataSourceService;
  private DataFormatConverter dataFormatConverter;

  /*
   * (non-Javadoc)
   * @see
   * org.simpl.core.services.datasource.DataSourceService#depositData(org.simpl.core.services
   * .datasource.DataSource, java.lang.String, java.lang.String)
   */
  @Override
  public boolean depositData(DataSource dataSource, String statement, String target)
      throws ConnectionException {
    boolean success = false;
    this.dataSourceService = DataSourceServiceProvider.getInstance(dataSource.getType(),
        dataSource.getSubType());

    success = this.dataSourceService.depositData(dataSource, statement, target);

    return success;
  }

  /*
   * (non-Javadoc)
   * @see
   * org.simpl.core.services.datasource.DataSourceService#executeStatement(org.simpl.core
   * .services.datasource.DataSource, java.lang.String)
   */
  @Override
  public boolean executeStatement(DataSource dataSource, String statement)
      throws ConnectionException {
    boolean success = false;
    this.dataSourceService = DataSourceServiceProvider.getInstance(dataSource.getType(),
        dataSource.getSubType());

    success = this.dataSourceService.executeStatement(dataSource, statement);

    return success;
  }

  /*
   * (non-Javadoc)
   * @see
   * org.simpl.core.services.datasource.DataSourceService#getMetaData(org.simpl.core.services
   * .datasource.DataSource, java.lang.String)
   */
  @Override
  public DataObject getMetaData(DataSource dataSource, String filter)
      throws ConnectionException {
    DataObject data = null;
    this.dataSourceService = DataSourceServiceProvider.getInstance(dataSource.getType(),
        dataSource.getSubType());

    data = this.dataSourceService.getMetaData(dataSource, filter);

    return data;
  }

  /*
   * (non-Javadoc)
   * @see
   * org.simpl.core.services.datasource.DataSourceService#retrieveData(org.simpl.core.
   * services.datasource.DataSource, java.lang.String)
   */
  @Override
  public DataObject retrieveData(DataSource dataSource, String statement)
      throws ConnectionException {
    DataObject data = null;
    this.dataSourceService = DataSourceServiceProvider.getInstance(dataSource.getType(),
        dataSource.getSubType());

    data = this.dataSourceService.retrieveData(dataSource, statement); 
    
    return data;
  }

  /*
   * (non-Javadoc)
   * @see
   * org.simpl.core.services.datasource.DataSourceService#writeBack(org.simpl.core.services
   * .datasource.DataSource, commonj.sdo.DataObject)
   */
  @Override
  public boolean writeBack(DataSource dataSource, DataObject data)
      throws ConnectionException {
    boolean success = false;
    this.dataSourceService = DataSourceServiceProvider.getInstance(dataSource.getType(),
        dataSource.getSubType());

    // compare this data source data format with the given data data format
    if (!data.getString("formatType").equals(
        ((DataSourceServicePlugin) this.dataSourceService).getDataFormat())) {
      // find converter and convert the data
      dataFormatConverter = DataFormatConverterProvider.getInstance(dataSource.getType(),
          dataSource.getSubType(), data.getString("formatType"));

      if (dataFormatConverter != null) {
        success = this.dataSourceService.writeBack(dataSource, dataFormatConverter.convert(data));
      }
    } else {
      success = this.dataSourceService.writeBack(dataSource, data);
    }

    return success;
  }

  /**
   * Returns all data source types supported by the SIMPL Core.
   * 
   * @return A list of data source types.
   */
  public List<String> getDataSourceTypes() {
    return DataSourceServiceProvider.getTypes();
  }

  /**
   * Returns all data source subtypes of a given data source type.
   * 
   * @param dsType
   * @return A list of data source subtypes.
   */
  public List<String> getDataSourceSubTypes(String dsType) {
    return DataSourceServiceProvider.getSubTypes(dsType);
  }

  /**
   * Returns all data source languages of a given data source subtype.
   * 
   * @param dsSubtype
   * @return List of data source languages.
   */
  public List<String> getDataSourceLanguages(String dsSubtype) {
    return DataSourceServiceProvider.getLanguages(dsSubtype);
  }

  /**
   * Returns all data source types supported by the SIMPL Core.
   * 
   * @return List of data source types.
   */
  public List<String> getDataFormatTypes() {
    return DataSourceServiceProvider.getTypes();
  }
}
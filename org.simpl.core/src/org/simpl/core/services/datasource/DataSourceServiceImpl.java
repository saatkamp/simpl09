package org.simpl.core.services.datasource;

import java.util.List;

import org.simpl.core.plugins.datasource.DataSourceServicePlugin;
import org.simpl.core.services.dataformat.converter.DataFormatConverter;
import org.simpl.core.services.dataformat.converter.DataFormatConverterProvider;
import org.simpl.core.services.datasource.exceptions.ConnectionException;
import org.simpl.core.services.strategy.StrategyService;
import org.simpl.core.services.strategy.StrategyServiceImpl;

import commonj.sdo.DataObject;

/**
 * <b>Purpose:</b>Implementation of the data source service.<br>
 * <b>Description:</b>Receives all requests to data sources and forward them to the an
 * appropriate data source service implementation that is provided by a data source
 * service plugin. <br>
 * If the given data source is not fully specified and contains late binding information,
 * the strategy service is consulted to find a matching full specified data source.<br>
 * The given data on writeback is tested to match the given data source's data format and
 * if necessary converts the data with a data format converter.<br>
 * <b>Copyright:</b><br>
 * <b>Company:</b>SIMPL<br>
 * 
 * @author schneimi<br>
 * @version $Id: DataSourceServiceImpl.java 1224 2010-04-28 14:17:34Z
 *          michael.schneidt@arcor.de $<br>
 * @link http://code.google.com/p/simpl09/
 */
public class DataSourceServiceImpl implements DataSourceService {
  private DataSourceService dataSourceService;
  private StrategyService strategyService = new StrategyServiceImpl();
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

    // late binding
    if (!isFullSpecified(dataSource)) {
      dataSource = strategyService.findDataSource(dataSource);
    }

    if (dataSource != null) {
      this.dataSourceService = DataSourceServiceProvider.getInstance(
          dataSource.getType(), dataSource.getSubType());
      success = this.dataSourceService.depositData(dataSource, statement, target);
    }
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

    // late binding
    if (!isFullSpecified(dataSource)) {
      dataSource = strategyService.findDataSource(dataSource);
    }

    if (dataSource != null) {
      this.dataSourceService = DataSourceServiceProvider.getInstance(
          dataSource.getType(), dataSource.getSubType());
      success = this.dataSourceService.executeStatement(dataSource, statement);
    }

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

    // late binding
    if (!isFullSpecified(dataSource)) {
      dataSource = strategyService.findDataSource(dataSource);
    }

    if (dataSource != null) {
      this.dataSourceService = DataSourceServiceProvider.getInstance(
          dataSource.getType(), dataSource.getSubType());
      data = this.dataSourceService.getMetaData(dataSource, filter);
    }

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

    // late binding
    if (!isFullSpecified(dataSource)) {
      dataSource = strategyService.findDataSource(dataSource);
    }

    if (dataSource != null) {
      this.dataSourceService = DataSourceServiceProvider.getInstance(
          dataSource.getType(), dataSource.getSubType());
      data = this.dataSourceService.retrieveData(dataSource, statement);
    }

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

    // late binding
    if (!isFullSpecified(dataSource)) {
      dataSource = strategyService.findDataSource(dataSource);
    }

    if (dataSource != null) {
      this.dataSourceService = DataSourceServiceProvider.getInstance(
          dataSource.getType(), dataSource.getSubType());

      // compare this data source data format with the given data data format
      if (!data.getString("formatType").equals(
          ((DataSourceServicePlugin) this.dataSourceService).getDataFormat())) {
        // find converter and convert the data
        dataFormatConverter = DataFormatConverterProvider.getInstance(dataSource
            .getType(), dataSource.getSubType(), data.getString("formatType"));

        if (dataFormatConverter != null) {
          success = this.dataSourceService.writeBack(dataSource, dataFormatConverter
              .convert(data));
        }
      } else {
        success = this.dataSourceService.writeBack(dataSource, data);
      }
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

  /**
   * Checks if a given data source is full specified to be able to request a data source
   * service.
   * 
   * @param dataSource
   * @return
   */
  private boolean isFullSpecified(DataSource dataSource) {
    boolean fullSpecified = false;

    fullSpecified = dataSource.getLateBinding().getPolicy() != null
        && dataSource.getLateBinding().getStrategy() != null
        && dataSource.getLateBinding().getUddiAddress() != null;

    return fullSpecified;
  }
}
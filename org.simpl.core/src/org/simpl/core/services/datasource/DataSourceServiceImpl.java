package org.simpl.core.services.datasource;

import java.util.List;

import org.simpl.core.plugins.dataformat.DataFormatPlugin;
import org.simpl.core.plugins.datasource.DataSourceServicePlugin;
import org.simpl.core.services.dataformat.DataFormatProvider;
import org.simpl.core.services.dataformat.converter.DataFormatConverter;
import org.simpl.core.services.dataformat.converter.DataFormatConverterProvider;
import org.simpl.core.services.datasource.exceptions.ConnectionException;
import org.simpl.core.services.strategy.StrategyService;
import org.simpl.core.services.strategy.StrategyServiceImpl;

import commonj.sdo.DataObject;

/**
 * <b>Purpose:</b>Implementation of the data source service.<br>
 * <b>Description:</b>Receives all requests to data sources and forward them to one of the
 * data source service instances that are provided by data source service plugins.<br>
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
    if (this.hasLateBinding(dataSource)) {
      dataSource = this.strategyService.findDataSource(dataSource);
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
    if (this.hasLateBinding(dataSource)) {
      dataSource = this.strategyService.findDataSource(dataSource);
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
   * org.simpl.core.services.datasource.DataSourceService#retrieveData(org.simpl.core.
   * services.datasource.DataSource, java.lang.String)
   */
  @Override
  public DataObject retrieveData(DataSource dataSource, String statement)
      throws ConnectionException {
    DataObject data = null;
  
    // late binding
    if (this.hasLateBinding(dataSource)) {
      dataSource = this.strategyService.findDataSource(dataSource);
    }
  
    // retrieve data
    if (dataSource != null) {
      this.dataSourceService = DataSourceServiceProvider.getInstance(
          dataSource.getType(), dataSource.getSubType());
      
      data =  this.dataSourceService.retrieveData(dataSource, statement);
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
    if (this.hasLateBinding(dataSource)) {
      dataSource = this.strategyService.findDataSource(dataSource);
    }
  
    // write back
    if (dataSource != null) {
      this.dataSourceService = DataSourceServiceProvider.getInstance(
          dataSource.getType(), dataSource.getSubType());
      
      success = this.dataSourceService.writeBack(dataSource, data);
    }
  
    return success;
  }

  /*
   * (non-Javadoc)
   * @see
   * org.simpl.core.services.datasource.DataSourceService#writeData(org.simpl.core.services
   * .datasource.DataSource, commonj.sdo.DataObject, java.lang.String)
   */
  @Override
  public boolean writeData(DataSource dataSource, DataObject data, String target)
      throws ConnectionException {
    boolean success = false;
  
    // late binding
    if (this.hasLateBinding(dataSource)) {
      dataSource = this.strategyService.findDataSource(dataSource);
    }
  
    // TODO: integrate conversion
    if (dataSource != null) {
      this.dataSourceService = DataSourceServiceProvider.getInstance(
          dataSource.getType(), dataSource.getSubType());

      success = this.dataSourceService.writeData(dataSource, data, target);
      
      // compare this data source data format with the given data data format
//      if (isSupported(data)) {
//        // find converter and convert the data
//        this.dataFormatConverter = DataFormatConverterProvider.getInstance(
//            ((DataSourceServicePlugin) this.dataSourceService).getDataFormat().getType(),
//            data.getString("formatType"));
//  
//        if (this.dataFormatConverter != null) {
//          // TODO: success = this.dataSourceService.write(dataSource,
//          // this.dataFormatConverter
//          // .convertFrom(data));
//        }
//      } else {
//        // TODO: success = this.dataSourceService.write(dataSource, data);
//      }
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
    if (this.hasLateBinding(dataSource)) {
      dataSource = this.strategyService.findDataSource(dataSource);
    }

    if (dataSource != null) {
      this.dataSourceService = DataSourceServiceProvider.getInstance(
          dataSource.getType(), dataSource.getSubType());
      
      data = this.dataSourceService.getMetaData(dataSource, filter);
    }

    return data;
  }

  /**
   * Returns all data source types that are supported by the SIMPL Core.
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
   * Returns all data format types that are available.
   * 
   * @return A list of data format types.
   */
  public List<String> getDataFormatTypes() {
    return DataFormatProvider.getTypes();
  }

  /**
   * Returns all data format types that can be converted to and from the given data
   * format.
   * 
   * @param dataFormat
   * @return A list of data format types.
   */
  public List<String> getConvertDataFormats(String dataFormat) {
    return DataFormatConverterProvider.getConvertDataFormats(dataFormat);
  }

  /**
   * Checks if a given data source has late binding information.
   * 
   * @param dataSource
   * @return
   */
  private boolean hasLateBinding(DataSource dataSource) {
    boolean hasLateBinding = false;

    hasLateBinding = dataSource != null
        && dataSource.getLateBinding().getPolicy() != null
        && dataSource.getLateBinding().getStrategy() != null
        && dataSource.getLateBinding().getUddiAddress() != null;

    return hasLateBinding;
  }

  /**
   * Checks if the given data's format is supported by the current data source service
   * instance.
   * 
   * @param dataFormat
   * @return
   */
  private boolean isSupported(DataObject data) {
    boolean supported = ((DataFormatPlugin<Object, Object>) ((DataSourceServicePlugin) this.dataSourceService)
        .getDataFormat()).getType().equals(data.getString("formatType"));

    return supported;
  }
}
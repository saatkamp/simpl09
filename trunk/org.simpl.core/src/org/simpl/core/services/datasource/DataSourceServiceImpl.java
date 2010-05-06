package org.simpl.core.services.datasource;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import org.simpl.core.services.dataformat.DataFormat;
import org.simpl.core.services.dataformat.DataFormatProvider;
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
public class DataSourceServiceImpl implements DataSourceService<DataObject, DataObject> {
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
    DataSourceService<Object, Object> dataSourceService;
    StrategyService strategyService = new StrategyServiceImpl();

    // late binding
    if (this.hasLateBinding(dataSource)) {
      dataSource = strategyService.findDataSource(dataSource);
    }

    if (dataSource != null) {
      dataSourceService = DataSourceServiceProvider.getInstance(dataSource.getType(),
          dataSource.getSubType());

      success = dataSourceService.depositData(dataSource, statement, target);
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
    DataSourceService<Object, Object> dataSourceService = null;
    StrategyService strategyService = new StrategyServiceImpl();

    // late binding
    if (this.hasLateBinding(dataSource)) {
      dataSource = strategyService.findDataSource(dataSource);
    }

    if (dataSource != null) {
      dataSourceService = DataSourceServiceProvider.getInstance(dataSource.getType(),
          dataSource.getSubType());

      success = dataSourceService.executeStatement(dataSource, statement);
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
    DataObject retrieveData = null;
    DataFormat<Object, Object> dataFormat = null;
    Object data = null;
    DataSourceService<Object, Object> dataSourceService = null;
    StrategyService strategyService = new StrategyServiceImpl();

    // late binding
    if (this.hasLateBinding(dataSource)) {
      dataSource = strategyService.findDataSource(dataSource);
    }

    // retrieve data
    if (dataSource != null) {
      dataSourceService = DataSourceServiceProvider.getInstance(dataSource.getType(),
          dataSource.getSubType());

      data = dataSourceService.retrieveData(dataSource, statement);
    }

    // convert data to data format
    // TODO: search for a data format that converts from dataSourceService T to DataObject
    dataFormat = findDataFormatToSDO(dataSourceService);
    retrieveData = dataFormat.toSDO(data);

    return retrieveData;
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
    
    DataSourceService<Object, Object> dataSourceService = null;
    StrategyService strategyService = new StrategyServiceImpl();
    DataFormat<Object, Object> dataFormat = null;
    Object writeData = null;
    
    // late binding
    if (this.hasLateBinding(dataSource)) {
      dataSource = strategyService.findDataSource(dataSource);
    }

    // write back
    if (dataSource != null) {
      dataSourceService = DataSourceServiceProvider.getInstance(dataSource.getType(),
          dataSource.getSubType());

      // format data
      dataFormat = findDataFormatFromSDO(dataSourceService);
      writeData = dataFormat.fromSDO(data);

      // write data
      success = dataSourceService.writeBack(dataSource, writeData); 
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
    boolean targetCreated = false;

    DataSourceService<Object, Object> dataSourceService = null;
    DataFormat<Object, Object> dataFormat = null;
    StrategyService strategyService = new StrategyServiceImpl();
    Object writeData = null;
    String createTargetStatement = null;

    // late binding
    if (this.hasLateBinding(dataSource)) {
      dataSource = strategyService.findDataSource(dataSource);
    }

    if (dataSource != null) {
      dataSourceService = DataSourceServiceProvider.getInstance(dataSource.getType(),
          dataSource.getSubType());

      // format data
      dataFormat = findDataFormatFromSDO(dataSourceService);
      writeData = dataFormat.fromSDO(data);

      // create target
      createTargetStatement = dataFormat.getCreateTargetStatement(data, target);
      targetCreated = dataSourceService.executeStatement(dataSource, createTargetStatement);
      
      // write data
      if (targetCreated) {
        success = dataSourceService.writeData(dataSource, writeData, target);
      }

      // TODO: integrate conversion
      // compare this data source data format with the given data data format
      // if (isSupported(data)) {
      // // find converter and convert the data
      // this.dataFormatConverter = DataFormatConverterProvider.getInstance(
      // ((DataSourceServicePlugin) this.dataSourceService).getDataFormat().getType(),
      // data.getString("formatType"));
      //  
      // if (this.dataFormatConverter != null) {
      // // TODO: success = this.dataSourceService.write(dataSource,
      // // this.dataFormatConverter
      // // .convertFrom(data));
      // }
      // } else {
      // // TODO: success = this.dataSourceService.write(dataSource, data);
      // }
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
    DataSourceService<Object, Object> dataSourceService;
    StrategyService strategyService = new StrategyServiceImpl();

    // late binding
    if (this.hasLateBinding(dataSource)) {
      dataSource = strategyService.findDataSource(dataSource);
    }

    if (dataSource != null) {
      dataSourceService = DataSourceServiceProvider.getInstance(dataSource.getType(),
          dataSource.getSubType());

      data = dataSourceService.getMetaData(dataSource, filter);
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
   * Finds a data format that converts a SDO to the incoming data type of a data source
   * service.
   * 
   * @param dataSourceService
   * @return
   */
  private DataFormat<Object, Object> findDataFormatToSDO(
      DataSourceService<Object, Object> dataSourceService) {
    DataFormat<Object, Object> dataFormat = null;
    ParameterizedType dataSourceServicepluginType = null;
    ParameterizedType dataFormatPluginType = null;
    Type dataSourceServiceOutgoingType = null;
    Type dataFormatToSDOType = null;

    dataSourceServicepluginType = (ParameterizedType) dataSourceService.getClass()
        .getGenericSuperclass();
    dataSourceServiceOutgoingType = dataSourceServicepluginType.getActualTypeArguments()[1];

    for (String type : DataFormatProvider.getTypes()) {
      dataFormat = DataFormatProvider.getInstance(type);

      dataFormatPluginType = (ParameterizedType) dataFormat.getClass()
          .getGenericSuperclass();
      dataFormatToSDOType = dataFormatPluginType.getActualTypeArguments()[0];

      if (dataSourceServiceOutgoingType.equals(dataFormatToSDOType)) {
        break;
      }
    }

    return dataFormat;
  }

  /**
   * Finds a data format that converts the outgoing data type of a data source service to
   * a SDO. service.
   * 
   * @param dataSourceService
   * @return
   */
  private DataFormat<Object, Object> findDataFormatFromSDO(
      DataSourceService<Object, Object> dataSourceService) {
    DataFormat<Object, Object> dataFormat = null;
    ParameterizedType dataSourceServicepluginType = null;
    ParameterizedType dataFormatPluginType = null;
    Type dataSourceServiceOutgoingType = null;
    Type dataFormatToSDOType = null;

    dataSourceServicepluginType = (ParameterizedType) dataSourceService.getClass()
        .getGenericSuperclass();
    dataSourceServiceOutgoingType = dataSourceServicepluginType.getActualTypeArguments()[0];

    for (String type : DataFormatProvider.getTypes()) {
      dataFormat = DataFormatProvider.getInstance(type);

      dataFormatPluginType = (ParameterizedType) dataFormat.getClass()
          .getGenericSuperclass();
      dataFormatToSDOType = dataFormatPluginType.getActualTypeArguments()[1];

      if (dataSourceServiceOutgoingType.equals(dataFormatToSDOType)) {
        break;
      }
    }

    return dataFormat;
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
  // private boolean isSupported(DataObject data) {
  // boolean supported = ((DataFormatPlugin<Object, Object>) ((DataSourceServicePlugin)
  // this.dataSourceService)
  // .getDataFormat()).getType().equals(data.getString("formatType"));
  //
  // return supported;
  // }

}
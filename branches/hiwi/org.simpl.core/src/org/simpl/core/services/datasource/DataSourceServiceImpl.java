package org.simpl.core.services.datasource;

import java.io.InputStream;
import java.util.List;

import org.simpl.core.plugins.datasource.DataSourceServicePlugin;
import org.simpl.core.services.dataformat.DataFormatProvider;
import org.simpl.core.services.dataformat.converter.DataFormatConverterProvider;
import org.simpl.core.services.datasource.exceptions.ConnectionException;
import org.simpl.core.services.discovery.DiscoveryService;
import org.simpl.core.services.discovery.DiscoveryServiceImpl;
import org.simpl.resource.management.client.DataSource;

import commonj.sdo.DataObject;

/**
 * <b>Purpose:</b>Implementation of a common data source service that unites all data
 * source service plug-ins.<br>
 * <b>Description:</b>Receives all requests to data source services and forward them to one of the
 * data source service instances that are provided by data source service plugins.<br>
 * The given data on write back is tested to match the given data source's data format and
 * if necessary converts the data with a data format converter.<br>
 * <b>Copyright:</b>Licensed under the Apache License, Version 2.0.
 * http://www.apache.org/licenses/LICENSE-2.0<br>
 * <b>Company:</b>SIMPL<br>
 * 
 * @author schneimi<br>
 * @version $Id: DataSourceServiceImpl.java 1224 2010-04-28 14:17:34Z
 *          michael.schneidt@arcor.de $<br>
 * @link http://code.google.com/p/simpl09/
 */
public class DataSourceServiceImpl implements DataSourceService<DataObject, DataObject> {
  DiscoveryService discoveryService = new DiscoveryServiceImpl();
  
  /*
   * (non-Javadoc)
   * @see
   * org.simpl.core.services.datasource.DataSourceService#depositData(org.simpl.core.services
   * .datasource.DataSource, java.lang.String, java.lang.String)
   */
  @Override
  public synchronized boolean depositData(DataSource dataSource, String statement,
      String target) throws ConnectionException {
    boolean success = false;
    DataSourceService<Object, Object> dataSourceService;
    
    try {
      if (this.isDataSourceComplete(dataSource)) {
        dataSourceService = DataSourceServiceProvider.getInstance(dataSource.getType(),
            dataSource.getSubType());

        success = dataSourceService.depositData(dataSource, statement, target);
      }
    } catch (Exception e) {
      throw new ConnectionException(e.getCause());
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
  public synchronized boolean executeStatement(DataSource dataSource, String statement) throws ConnectionException {
    boolean success = false;

    DataSourceService<Object, Object> dataSourceService = null;

    try {
      // execute statement
      if (this.isDataSourceComplete(dataSource)) {
        // get data source service instance
        dataSourceService = DataSourceServiceProvider.getInstance(dataSource.getType(),
            dataSource.getSubType());

        // execute statement
        success = dataSourceService.executeStatement(dataSource, statement);
      }
    } catch (Exception e) {
      throw new ConnectionException(e.getCause());
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
  public synchronized DataObject retrieveData(DataSource dataSource, String statement)
      throws ConnectionException {
    DataObject retrievedData = null;
    Object data = null;
    DataSourceService<Object, Object> dataSourceService = null;

    try {
      // retrieve data
      if (this.isDataSourceComplete(dataSource)) {
        // get data source service instance
        dataSourceService = DataSourceServiceProvider.getInstance(dataSource.getType(),
            dataSource.getSubType());

        // retrieve data
        data = dataSourceService.retrieveData(dataSource, statement);
      }

      // format data to SDO
      if (data != null) {
        retrievedData = formatRetrievedData(dataSourceService, data, dataSource
            .getConnector().getConverterDataFormat().getName());
      }
    } catch (Exception e) {
      e.printStackTrace();
      throw new ConnectionException(e.getCause());
    }

    return retrievedData;
  }

  /*
   * (non-Javadoc)
   * @see
   * org.simpl.core.services.datasource.DataSourceService#writeBack(org.simpl.core.services
   * .datasource.DataSource, commonj.sdo.DataObject)
   */
  @Override
  public synchronized boolean writeBack(DataSource dataSource, DataObject data)
      throws ConnectionException {
    boolean success = false;

    DataSourceService<Object, Object> dataSourceService = null;
    Object writeData = null;

    try {
      if (this.isDataSourceComplete(dataSource)) {
        // get data source service instance
        dataSourceService = DataSourceServiceProvider.getInstance(dataSource.getType(),
            dataSource.getSubType());

        writeData = DataFormatProvider.getInstance(
            dataSource.getConnector().getConverterDataFormat().getName()).fromSDO(data);

        // write data
        if (writeData != null) {
          success = dataSourceService.writeBack(dataSource, writeData);
        }
      }
    } catch (Exception e) {
      throw new ConnectionException(e.getCause());
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
  public synchronized boolean writeData(DataSource dataSource, DataObject data,
      String target) throws ConnectionException {
    boolean success = false;

    DataSourceService<Object, Object> dataSourceService = null;
    Object writeData = null;

    try {
      if (this.isDataSourceComplete(dataSource)) {
        // get data source service instance
        dataSourceService = DataSourceServiceProvider.getInstance(dataSource.getType(),
            dataSource.getSubType());

        // format data
        writeData = formatWriteDataAndCreateTarget(dataSourceService, data, dataSource,
            target);

        if (writeData != null) {
          success = dataSourceService.writeData(dataSource, writeData, target);
        }
      }
    } catch (Exception e) {
      throw new ConnectionException(e.getCause());
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

    try {
      if (this.isDataSourceComplete(dataSource)) {
        // get data source service instance
        dataSourceService = DataSourceServiceProvider.getInstance(dataSource.getType(),
            dataSource.getSubType());

        // get meta data
        data = dataSourceService.getMetaData(dataSource, filter);
      }
    } catch (Exception e) {
      throw new ConnectionException(e.getCause());
    }

    return data;
  }

  /*
   * (non-Javadoc)
   * @see
   * org.simpl.core.services.datasource.DataSourceService#createTarget(org.simpl.core.
   * services.datasource.DataSource, commonj.sdo.DataObject, java.lang.String)
   */
  @Override
  public boolean createTarget(DataSource dataSource, DataObject dataObject, String target)
      throws ConnectionException {
    boolean success = false;
    DataSourceService<Object, Object> dataSourceService = null;

    try {
      // get data source service instance
      dataSourceService = DataSourceServiceProvider.getInstance(dataSource.getType(),
          dataSource.getSubType());

      // create target
      success = dataSourceService.createTarget(dataSource, dataObject, target);
    } catch (Exception e) {
      throw new ConnectionException(e.getCause());
    }

    return success;
  }

  /**
   * Returns the data source's meta data schema file as InputStream.
   * 
   * @param dataSource
   * @return
   */
  @SuppressWarnings({ "rawtypes" })
  public InputStream getMetaDataSchemaFile(DataSource dataSource) {
    InputStream inputStream = ((DataSourceServicePlugin) DataSourceServiceProvider
        .getInstance(dataSource.getType(), dataSource.getSubType()))
        .getMetaDataSchemaFile();

    return inputStream;
  }

  /**
   * Formats SDO data to a format that can be understand and is writeable by the given
   * data source service and its underlying data format. If the data format is not
   * supported by the data source service, it is looked for a data format converter that
   * can be used to convert the given data format to the data source service supported
   * data format and thus be able to eventually write the data even though the incoming
   * data format is not supported. Also creates a target on the data source to be able to
   * write the data.
   * 
   * @param dataSourceService
   * @param data
   * @param dataSource
   * @param target
   * @return
   * @throws ConnectionException
   */
  @SuppressWarnings({ "unchecked", "rawtypes" })
  private Object formatWriteDataAndCreateTarget(DataSourceService dataSourceService,
      DataObject data, DataSource dataSource, String target) throws ConnectionException {
    Object writeData = null;

    boolean createdTarget;
    DataObject convertedData = null;
    String dataFormatType = data.getString("dataFormatType");
    List<String> supportedDataFormatTypes = DataFormatProvider
        .getSupportedDataFormatTypes(dataSourceService);
    List<String> supportedConvertDataFormatTypes = null;

    // check if data format type is supported by the data source service
    if (supportedDataFormatTypes.contains(dataFormatType)) {
      // turn the SDO to the data type of the data source
      writeData = DataFormatProvider.getInstance(dataFormatType).fromSDO(data);
    } else {
      supportedConvertDataFormatTypes = DataFormatConverterProvider
          .getSupportedConvertDataFormatTypes(dataSourceService, dataFormatType);

      // cycle through the types that the data format can be converted to
      for (String supportedConvertDataFormatType : supportedConvertDataFormatTypes) {
        // check if one of the types is supported by the data source service
        if (DataFormatProvider.getSupportedDataFormatTypes(dataSourceService).contains(
            supportedConvertDataFormatType)) {
          // convert from the given data format to the supported data format
          convertedData = DataFormatConverterProvider.getInstance(dataFormatType,
              supportedConvertDataFormatType).convert(data, dataSourceService);

          // turn the converted SDO to the data type of the data source
          writeData = DataFormatProvider.getInstance(supportedConvertDataFormatType)
              .fromSDO(convertedData);

          break;
        }
      }
    }

    // create target
    if (writeData != null) {
      if (convertedData != null) {
        createdTarget = dataSourceService.createTarget(dataSource, convertedData, target);
      } else {
        createdTarget = dataSourceService.createTarget(dataSource, data, target);
      }

      // write data
      if (!createdTarget) {
        writeData = null;
      }
    }

    return writeData;
  }

  /**
   * Turns the retrieved data into SDO using a data format that is supported by the data
   * source service.
   * 
   * @param dataSourceService
   * @param data
   * @param dataormatType
   * @return
   */
  @SuppressWarnings({ "rawtypes" })
  private DataObject formatRetrievedData(DataSourceService dataSourceService,
      Object data, String dataFormatType) {
    DataObject retrieveDataSDO = null;
    List<String> supportedDataFormatTypes = null;

    supportedDataFormatTypes = DataFormatProvider
        .getSupportedDataFormatTypes(dataSourceService);

    for (String supportedDataFormatType : supportedDataFormatTypes) {
      if (supportedDataFormatType.equals(dataFormatType)) {
        retrieveDataSDO = DataFormatProvider.getInstance(supportedDataFormatType).toSDO(
            data);
      }
    }

    return retrieveDataSDO;
  }

  /**
   * @param dataSource
   * @return <i>true</i> if data source contains all necessary information to execute an
   *         operation, <i>false</i> otherwise
   */
  private boolean isDataSourceComplete(DataSource dataSource) {
    boolean complete = false;

    complete = dataSource != null && dataSource.getAddress() != null
        && dataSource.getType() != null && dataSource.getSubType() != null
        && dataSource.getConnector().getConverterDataFormat().getName() != null;

    return complete;
  }
}
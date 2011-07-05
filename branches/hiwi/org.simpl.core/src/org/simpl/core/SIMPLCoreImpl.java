package org.simpl.core;

import java.io.InputStream;
import java.util.List;

import org.simpl.core.connector.Connector;
import org.simpl.core.connector.ConnectorProvider;
import org.simpl.core.dataconverter.DataConverterProvider;
import org.simpl.core.datatransformation.DataTransformationServiceProvider;
import org.simpl.core.exceptions.ConnectionException;
import org.simpl.core.plugins.connector.ConnectorPlugin;
import org.simpl.resource.management.data.DataSource;

import commonj.sdo.DataObject;

/**
 * <b>Purpose:</b>Implementation of the SIMPL Core that unites all connector plug-ins.<br>
 * <b>Description:</b>Receives all service requests and forwards them to the connector
 * instances that are provided by the connector plug-ins.<br>
 * The given data on write back is tested to match the given connector's data format and
 * if necessary converts the data with an appropriate converter.<br>
 * <b>Copyright:</b>Licensed under the Apache License, Version 2.0.
 * http://www.apache.org/licenses/LICENSE-2.0<br>
 * <b>Company:</b>SIMPL<br>
 * 
 * @author schneimi<br>
 * @version $Id$<br>
 * @link http://code.google.com/p/simpl09/
 */
public class SIMPLCoreImpl implements Connector<DataObject, DataObject> {
  /*
   * (non-Javadoc)
   * @see
   * org.simpl.core.connector.Connector#depositData(org.simpl.resource.management.client
   * .DataSource, java.lang.String, java.lang.String)
   */
  @Override
  public synchronized boolean queryData(DataSource dataSource, String statement,
      String target) throws ConnectionException {
    boolean success = false;
    Connector<Object, Object> connector;

    try {
      if (this.isDataSourceComplete(dataSource)) {
        connector = ConnectorProvider.getInstance(dataSource.getType(),
            dataSource.getSubType());

        success = connector.queryData(dataSource, statement, target);
      }
    } catch (Exception e) {
      throw new ConnectionException(e.getCause());
    }

    return success;
  }

  /*
   * (non-Javadoc)
   * @see
   * org.simpl.core.connector.Connector#executeStatement(org.simpl.resource.management
   * .client.DataSource, java.lang.String)
   */
  @Override
  public synchronized boolean issueCommand(DataSource dataSource, String statement)
      throws ConnectionException {
    boolean success = false;

    Connector<Object, Object> connector = null;

    try {
      // execute statement
      if (this.isDataSourceComplete(dataSource)) {
        // get data source service instance
        connector = ConnectorProvider.getInstance(dataSource.getType(),
            dataSource.getSubType());

        // execute statement
        success = connector.issueCommand(dataSource, statement);
      }
    } catch (Exception e) {
      throw new ConnectionException(e.getCause());
    }

    return success;
  }

  /*
   * (non-Javadoc)
   * @see
   * org.simpl.core.connector.Connector#retrieveData(org.simpl.resource.management.client
   * .DataSource, java.lang.String)
   */
  @Override
  public synchronized DataObject retrieveData(DataSource dataSource, String statement)
      throws ConnectionException {
    DataObject retrievedData = null;
    Object data = null;
    Connector<Object, Object> connector = null;
    
    try {
      // retrieve data
      if (this.isDataSourceComplete(dataSource)) {
        // get connector instance
        connector = ConnectorProvider.getInstance(dataSource.getType(),
            dataSource.getSubType());
        
        data = connector.retrieveData(dataSource, statement);
      }

      // format data to SDO
      if (data != null) {
        retrievedData = formatRetrievedData(connector, data, dataSource.getConnector()
            .getDataConverter().getDataFormat());
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
   * org.simpl.core.connector.Connector#writeDataBack(org.simpl.resource.management.client
   * .DataSource, commonj.sdo.DataObject, java.lang.String)
   */
  @Override
  public synchronized boolean writeDataBack(DataSource dataSource, DataObject data,
      String target) throws ConnectionException {
    boolean success = false;

    Connector<Object, Object> connector = null;
    Object writeData = null;

    try {
      if (this.isDataSourceComplete(dataSource)) {
        // get connector instance
        connector = ConnectorProvider.getInstance(dataSource.getType(),
            dataSource.getSubType());

        if (target != null && !target.equals("")) {
          // format and write back data
          writeData = formatWriteDataAndCreateTarget(connector, data, dataSource,
              target);
        } else {
          // write back data
          writeData = DataConverterProvider.getInstance(
              dataSource.getConnector().getDataConverter().getDataFormat()).fromSDO(data);
        }

        if (writeData != null) {
          success = connector.writeDataBack(dataSource, writeData, target);
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
   * org.simpl.core.connector.Connector#getMetaData(org.simpl.resource.management.client
   * .DataSource, java.lang.String)
   */
  @Override
  public DataObject getMetaData(DataSource dataSource, String filter)
      throws ConnectionException {
    DataObject data = null;
    Connector<Object, Object> connector;

    try {
      if (this.isDataSourceComplete(dataSource)) {
        // get connector instance
        connector = ConnectorProvider.getInstance(dataSource.getType(),
            dataSource.getSubType());

        // get meta data
        data = connector.getMetaData(dataSource, filter);
      }
    } catch (Exception e) {
      throw new ConnectionException(e.getCause());
    }

    return data;
  }

  /*
   * (non-Javadoc)
   * @see
   * org.simpl.core.connector.Connector#createTarget(org.simpl.resource.management.client
   * .DataSource, commonj.sdo.DataObject, java.lang.String)
   */
  @Override
  public boolean createTarget(DataSource dataSource, DataObject dataObject, String target)
      throws ConnectionException {
    boolean success = false;
    Connector<Object, Object> connector = null;

    try {
      // get data source service instance
      connector = ConnectorProvider.getInstance(dataSource.getType(),
          dataSource.getSubType());

      // create target
      success = connector.createTarget(dataSource, dataObject, target);
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
    InputStream inputStream = ((ConnectorPlugin) ConnectorProvider.getInstance(
        dataSource.getType(), dataSource.getSubType())).getMetaDataSchemaFile();

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
   * @param connector
   * @param data
   * @param dataSource
   * @param target
   * @return
   * @throws ConnectionException
   */
  @SuppressWarnings({ "unchecked", "rawtypes" })
  private Object formatWriteDataAndCreateTarget(Connector connector, DataObject data,
      DataSource dataSource, String target) throws ConnectionException {
    Object writeData = null;

    boolean createdTarget;
    DataObject convertedData = null;
    String dataFormat = data.getString("dataFormat");
    List<String> supportedDataFormats = DataConverterProvider
        .getSupportedDataFormats(connector);
    List<String> supportedConvertDataFormats = null;

    // check if data format is supported by the data source service
    if (supportedDataFormats.contains(dataFormat)) {
      // turn the SDO to the data type of the data source
      writeData = DataConverterProvider.getInstance(dataFormat).fromSDO(data);
    } else {
      supportedConvertDataFormats = DataTransformationServiceProvider
          .getSupportedConvertDataFormats(connector, dataFormat);

      // cycle through the data formats that can be converted to
      for (String supportedConvertDataFormat : supportedConvertDataFormats) {
        // check if one of the data formats is supported by the data source service
        if (DataConverterProvider.getSupportedDataFormats(connector).contains(
            supportedConvertDataFormat)) {
          // convert from the given data format to the supported data format
          convertedData = DataTransformationServiceProvider.getInstance(dataFormat,
              supportedConvertDataFormat).convert(data, connector);

          // turn the converted SDO to the data type of the data source
          writeData = DataConverterProvider.getInstance(supportedConvertDataFormat)
              .fromSDO(convertedData);

          break;
        }
      }
    }

    // create target
    if (writeData != null) {
      if (convertedData != null) {
        createdTarget = connector.createTarget(dataSource, convertedData, target);
      } else {
        createdTarget = connector.createTarget(dataSource, data, target);
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
   * @param connector
   * @param data
   * @param dataFormat
   * @return
   */
  @SuppressWarnings({ "rawtypes" })
  private DataObject formatRetrievedData(Connector connector, Object data,
      String dataFormat) {
    DataObject retrieveDataSDO = null;
    List<String> supportedDataFormats = null;

    supportedDataFormats = DataConverterProvider.getSupportedDataFormats(connector);

    for (String supportedDataFormat : supportedDataFormats) {
      if (supportedDataFormat.equals(dataFormat)) {
        retrieveDataSDO = DataConverterProvider.getInstance(supportedDataFormat).toSDO(
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
        && dataSource.getConnector().getDataConverter().getDataFormat() != null;

    return complete;
  }
}
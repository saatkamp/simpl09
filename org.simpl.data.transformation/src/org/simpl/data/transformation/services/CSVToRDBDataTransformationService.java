package org.simpl.data.transformation.services;

import java.util.List;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.simpl.data.transformation.DataTransformationService;

import commonj.sdo.DataObject;

/**
 * <b>Purpose: Converts the CSV data format to the RDB data format and vice versa.</b> <br>
 * <b>Description:</b>The CSV data format does not contain information about primary keys
 * and column types that is required by the RDB data format. The column types are
 * specified by analyzing the string values. A new primary key is set as default.<br>
 * <b>Copyright:</b>Licensed under the Apache License, Version 2.0.
 * http://www.apache.org/licenses/LICENSE-2.0<br>
 * <b>Company:</b>SIMPL<br>
 * 
 * @author schneimi<br>
 * @version $Id$<br>
 * @link http://code.google.com/p/simpl09/
 */
public class CSVToRDBDataTransformationService extends DataTransformationService {
  static Logger logger = Logger.getLogger(CSVToRDBDataTransformationService.class);

  /**
   * Initialize the service.
   */
  public CSVToRDBDataTransformationService() {
    this.setFromDataFormat("CSVDataFormat");
    this.setToDataFormat("RDBDataFormat");

    // Set up a simple configuration that logs on the console.
    PropertyConfigurator.configure("log4j.properties");
  }

  /*
   * (non-Javadoc)
   * @see
   * org.simpl.core.services.dataformat.converter.DataFormatConverter#convertFrom(commonj
   * .sdo.DataObject)
   */
  @SuppressWarnings({ "unchecked" })
  @Override
  public DataObject convertTo(DataObject csvSDO, String connectorImpl) {
    if (CSVToRDBDataTransformationService.logger.isDebugEnabled()) {
      CSVToRDBDataTransformationService.logger
          .debug("Convert 'DataObject' data format from "
              + this.getFromDataConverter().getDataFormat() + " to "
              + this.getToDataConverter().getDataFormat());
    }

    // use csv SDO and replace csv table meta data with rdb table meta data
    DataObject rdbSDO = csvSDO;
    rdbSDO.setString("dataFormat", "RDBDataFormat");

    DataObject csvTableMetaDataObject = null;
    DataObject rdbTableMetaDataObject = null;
    DataObject rdbTableMetaDataColumnTypeObject = null;
    List<DataObject> rdbTables = rdbSDO.getList("table");
    List<DataObject> rdbRows = null;
    List<DataObject> rdbColumns = null;
    DataObject rdbColumn = null;

    for (DataObject rdbTable : rdbTables) {
      rdbRows = rdbTable.getList("row");
      rdbColumns = rdbRows.get(0).getList("column");

      // remove csv table meta data
      csvTableMetaDataObject = rdbTable.getDataObject("csvTableMetaData");
      csvTableMetaDataObject.detach();

      // add rdb table meta data
      rdbTableMetaDataObject = rdbTable.createDataObject("rdbTableMetaData");
      rdbTableMetaDataObject.setString("name",
          csvTableMetaDataObject.getString("filename").toUpperCase().replace(".", "_"));
      rdbTableMetaDataObject.setString("schema", "");
      rdbTableMetaDataObject.setString("catalog", "");

      for (int k = 0; k < rdbColumns.size(); k++) {
        rdbTableMetaDataColumnTypeObject = rdbTableMetaDataObject
            .createDataObject("columnType");
        rdbTableMetaDataColumnTypeObject.setString("columnName", rdbColumns.get(k)
            .getString("name"));
        rdbTableMetaDataColumnTypeObject.set(0,
            this.getColumnType(rdbColumns.get(k).getString(0), connectorImpl));
      }

      // create default primary key
      rdbTableMetaDataColumnTypeObject = rdbTableMetaDataObject
          .createDataObject("columnType");
      rdbTableMetaDataColumnTypeObject.setString("columnName", "PK_ID");
      rdbTableMetaDataColumnTypeObject.setBoolean("isPrimaryKey", true);
      rdbTableMetaDataColumnTypeObject.set(0, this.getColumnType("123", connectorImpl));

      // add primary key column to rows
      for (int k = 0; k < rdbRows.size(); k++) {
        rdbColumn = rdbRows.get(k).createDataObject("column");
        rdbColumn.set("name", "PK_ID");
        rdbColumn.set(0, String.valueOf(k));
      }
    }
    // if (CSVtoRDBDataFormatConverter.logger.isDebugEnabled()) {
    // ByteArrayOutputStream baos = new ByteArrayOutputStream();
    //
    // try {
    // XMLHelper.INSTANCE.save(rdbSDO, "commonj.sdo", "dataObject", baos);
    // } catch (IOException e) {
    // // TODO Auto-generated catch block
    // e.printStackTrace();
    // }
    //
    // CSVtoRDBDataFormatConverter.logger.debug("CSV DataObject: "
    // + new String(baos.toByteArray()));
    // }

    return rdbSDO;
  }

  /*
   * (non-Javadoc)
   * @see
   * org.simpl.core.services.dataformat.converter.DataFormatConverter#convertTo(commonj
   * .sdo.DataObject)
   */
  @SuppressWarnings({ "unchecked" })
  @Override
  public DataObject convertFrom(DataObject rdbSDO, String connectorImpl) {
    if (CSVToRDBDataTransformationService.logger.isDebugEnabled()) {
      CSVToRDBDataTransformationService.logger
          .debug("Convert 'DataObject' data format from "
              + this.getToDataConverter().getDataFormat() + " to "
              + this.getFromDataConverter().getDataFormat());
    }

    // use rdb SDO and add csv table meta data
    DataObject csvSDO = rdbSDO;
    csvSDO.setString("dataFormat", "CSVDataFormat");

    DataObject csvTableMetaDataObject = null;
    DataObject rdbTableMetaDataObject = null;
    List<DataObject> csvTables = csvSDO.getList("table");
    DataObject csvTable = null;

    // add csv table meta data
    for (int i = 0; i < csvTables.size(); i++) {
      csvTable = csvTables.get(i);
      csvTableMetaDataObject = csvTable.createDataObject("csvTableMetaData");
      csvTableMetaDataObject.setString("filename", "");

      // remove csv table meta data
      rdbTableMetaDataObject = csvTable.createDataObject("rdbTableMetaData");
      rdbTableMetaDataObject.detach();
    }

    // if (CSVtoRDBDataFormatConverter.logger.isDebugEnabled()) {
    // ByteArrayOutputStream baos = new ByteArrayOutputStream();
    //
    // try {
    // XMLHelper.INSTANCE.save(csvSDO, "commonj.sdo", "dataObject", baos);
    // } catch (IOException e) {
    // // TODO Auto-generated catch block
    // e.printStackTrace();
    // }
    //
    // CSVtoRDBDataFormatConverter.logger.debug("CSV DataObject: "
    // + new String(baos.toByteArray()));
    // }

    return csvSDO;
  }

  /**
   * Returns a SQL data type declaration depending on the string content.
   * 
   * TODO: support more sql data types like date, decimal, ...
   * 
   * @param type
   * @param connector
   * @return
   */
  private String getColumnType(String type, String connectorImpl) {
    String sqlType = "VARCHAR(255)";

    try {
      if (Integer.valueOf(type) != null) {
        if (connectorImpl.equals("org.simpl.core.plugins.connector.rdb.DB2RDBConnector")) {
          return sqlType = "INTEGER";
        } else if (connectorImpl
            .equals("org.simpl.core.plugins.connector.rdb.EmbDerbyRDBConnector")) {
          return sqlType = "INT";
        } else if (connectorImpl
            .equals("org.simpl.core.plugins.connector.rdb.DerbyRDBConnector")) {
          return sqlType = "INT";
        } else if (connectorImpl
            .equals("org.simpl.core.plugins.connector.rdb.MySQLRDBConnector")) {
          return sqlType = "INT(11)";
        } else {
          return sqlType = "INT";
        }
      }
    } catch (NumberFormatException e) {
      // no integer
    }

    try {
      if (Boolean.valueOf(type)) {
        if (connectorImpl.equals("org.simpl.core.plugins.connector.rdb.DB2RDBConnector")) {
          return sqlType = "SMALLINT";
        } else if (connectorImpl
            .equals("org.simpl.core.plugins.connector.rdb.EmbDerbyRDBConnector")) {
          return sqlType = "SMALLINT";
        } else if (connectorImpl
            .equals("org.simpl.core.plugins.connector.rdb.DerbyRDBConnector")) {
          return sqlType = "SMALLINT";
        } else if (connectorImpl
            .equals("org.simpl.core.plugins.connector.rdb.MySQLRDBConnector")) {
          return sqlType = "TINYINT(1)";
        } else {
          return sqlType = "SMALLINT";
        }
      }
    } catch (NumberFormatException e) {
      // no boolean
    }

    try {
      if (Float.valueOf(type) != null) {
        if (connectorImpl.equals("org.simpl.core.plugins.connector.rdb.DB2RDBConnector")) {
          return sqlType = "FLOAT(23)";
        } else if (connectorImpl
            .equals("org.simpl.core.plugins.connector.rdb.EmbDerbyRDBConnector")) {
          return sqlType = "FLOAT";
        } else if (connectorImpl
            .equals("org.simpl.core.plugins.connector.rdb.DerbyRDBConnector")) {
          return sqlType = "FLOAT";
        } else if (connectorImpl
            .equals("org.simpl.core.plugins.connector.rdb.MySQLRDBConnector")) {
          return sqlType = "FLOAT";
        } else {
          return sqlType = "FLOAT";
        }
      }
    } catch (NumberFormatException e) {
      // no float
    }

    try {
      if (Double.valueOf(type) != null) {
        if (connectorImpl.equals("org.simpl.core.plugins.connector.rdb.DB2RDBConnector")) {
          return sqlType = "DOUBLE";
        } else if (connectorImpl
            .equals("org.simpl.core.plugins.connector.rdb.EmbDerbyRDBConnector")) {
          return sqlType = "DOUBLE";
        } else if (connectorImpl
            .equals("org.simpl.core.plugins.connector.rdb.DerbyRDBConnector")) {
          return sqlType = "DOUBLE";
        } else if (connectorImpl
            .equals("org.simpl.core.plugins.connector.rdb.MySQLRDBConnector")) {
          return sqlType = "DOUBLE";
        } else {
          return sqlType = "DOUBLE";
        }
      }
    } catch (NumberFormatException e) {
      // no double
    }

    return sqlType;
  }
}
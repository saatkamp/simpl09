package org.simpl.core.plugins.converter;

import java.util.List;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.simpl.core.connector.Connector;
import org.simpl.core.plugins.connector.rdb.DB2RDBConnector;
import org.simpl.core.plugins.connector.rdb.DerbyRDBConnector;
import org.simpl.core.plugins.connector.rdb.EmbDerbyRDBConnector;
import org.simpl.core.plugins.connector.rdb.MySQLRDBConnector;
import org.simpl.core.plugins.converter.ConverterPlugin;

import commonj.sdo.DataObject;

/**
 * <b>Purpose: Converts the CSV data format to the RDB data format and vice versa.</b> <br>
 * <b>Description:</b>The CSV data format does not contain information about primary keys
 * and column types that is required by the RDB data format. The column types are
 * specified by analyzing the string values, a new primary key is set as default.<br>
 * <b>Copyright:</b>Licensed under the Apache License, Version 2.0.
 * http://www.apache.org/licenses/LICENSE-2.0<br>
 * <b>Company:</b>SIMPL<br>
 * 
 * @author schneimi<br>
 * @version $Id: RDBCSVDataFormatConverter.java 1329 2010-05-07 18:06:21Z
 *          michael.schneidt@arcor.de $<br>
 * @link http://code.google.com/p/simpl09/
 */
public class CSVtoRDBConverter extends ConverterPlugin {
  static Logger logger = Logger.getLogger(DB2RDBConnector.class);

  /**
   * Initialize the plug-in.
   */
  public CSVtoRDBConverter() {
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
  @SuppressWarnings({ "unchecked", "rawtypes" })
  @Override
  public DataObject convertTo(DataObject csvSDO, Connector connector) {
    if (CSVtoRDBConverter.logger.isDebugEnabled()) {
      CSVtoRDBConverter.logger.debug("Convert 'DataObject' data format from "
          + this.getFromDataFormat().getType() + " to "
          + this.getToDataFormat().getType());
    }

    // use csv SDO and replace csv table meta data with rdb table meta data
    DataObject rdbSDO = csvSDO;
    rdbSDO.setString("dataFormatType", "RDBDataFormat");

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
            this.getColumnType(rdbColumns.get(k).getString(0), connector));
      }

      // create default primary key
      rdbTableMetaDataColumnTypeObject = rdbTableMetaDataObject
          .createDataObject("columnType");
      rdbTableMetaDataColumnTypeObject.setString("columnName", "PK_ID");
      rdbTableMetaDataColumnTypeObject.setBoolean("isPrimaryKey", true);
      rdbTableMetaDataColumnTypeObject.set(0, this.getColumnType("123", connector));

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
  @SuppressWarnings({ "unchecked", "rawtypes" })
  @Override
  public DataObject convertFrom(DataObject rdbSDO, Connector connector) {
    if (CSVtoRDBConverter.logger.isDebugEnabled()) {
      CSVtoRDBConverter.logger.debug("Convert 'DataObject' data format from "
          + this.getToDataFormat().getType() + " to "
          + this.getFromDataFormat().getType());
    }

    // use rdb SDO and add csv table meta data
    DataObject csvSDO = rdbSDO;
    csvSDO.setString("dataFormatType", "CSVDataFormat");

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
  @SuppressWarnings({ "rawtypes" })
  private String getColumnType(String type, Connector connector) {
    String sqlType = "VARCHAR(255)";

    try {
      if (Integer.valueOf(type) != null) {
        if (connector instanceof DB2RDBConnector) {
          return sqlType = "INTEGER";
        } else if (connector instanceof EmbDerbyRDBConnector) {
          return sqlType = "INT";
        } else if (connector instanceof DerbyRDBConnector) {
          return sqlType = "INT";
        } else if (connector instanceof MySQLRDBConnector) {
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
        if (connector instanceof DB2RDBConnector) {
          return sqlType = "SMALLINT";
        } else if (connector instanceof EmbDerbyRDBConnector) {
          return sqlType = "SMALLINT";
        } else if (connector instanceof DerbyRDBConnector) {
          return sqlType = "SMALLINT";
        } else if (connector instanceof MySQLRDBConnector) {
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
        if (connector instanceof DB2RDBConnector) {
          return sqlType = "FLOAT(23)";
        } else if (connector instanceof EmbDerbyRDBConnector) {
          return sqlType = "FLOAT";
        } else if (connector instanceof DerbyRDBConnector) {
          return sqlType = "FLOAT";
        } else if (connector instanceof MySQLRDBConnector) {
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
        if (connector instanceof DB2RDBConnector) {
          return sqlType = "DOUBLE";
        } else if (connector instanceof EmbDerbyRDBConnector) {
          return sqlType = "DOUBLE";
        } else if (connector instanceof DerbyRDBConnector) {
          return sqlType = "DOUBLE";
        } else if (connector instanceof MySQLRDBConnector) {
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
package org.simpl.core.plugins.converter;

import java.util.List;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.simpl.core.connector.Connector;
import org.simpl.core.plugins.connector.rdb.DB2RDBConnector;
import org.simpl.core.plugins.connector.rdb.DerbyRDBConnector;
import org.simpl.core.plugins.connector.rdb.EmbDerbyRDBConnector;
import org.simpl.core.plugins.connector.rdb.MySQLRDBConnector;
import org.simpl.core.plugins.connector.rdb.PostgreSQLRDBConnector;

import commonj.sdo.DataObject;
import commonj.sdo.helper.XMLHelper;

/**
 * <b>Purpose: Converts the RandomFile data format to the RDB data format and vice
 * versa.</b> <br>
 * <b>Description:</b>The RandomFile format will be used to store the name of a file and
 * its content (as text) in a database table.<br>
 * <b>Copyright:</b>Licensed under the Apache License, Version 2.0.
 * http://www.apache.org/licenses/LICENSE-2.0<br>
 * <b>Company:</b>SIMPL<br>
 * 
 * @author hahnml<br>
 * @version $Id$<br>
 * @link http://code.google.com/p/simpl09/
 */
public class RandomFiletoRDBConverter extends ConverterPlugin {
  static Logger logger = Logger.getLogger(RandomFiletoRDBConverter.class);

  /**
   * Initialize the plug-in.
   */
  public RandomFiletoRDBConverter() {
    this.setFromDataFormat("RandomFileDataFormat");
    this.setToDataFormat("RDBDataFormat");

    // Set up a simple configuration that logs on the console.
    PropertyConfigurator.configure("log4j.properties");
  }

  /*
   * (non-Javadoc)
   * @see org.simpl.core.services.dataformat.converter.DataFormatConverter#convertFrom
   * (commonj .sdo.DataObject)
   */
  @SuppressWarnings({ "unchecked", "rawtypes" })
  @Override
  public DataObject convertTo(DataObject fileSDO, Connector connector) {
    if (RandomFiletoRDBConverter.logger.isDebugEnabled()) {
      RandomFiletoRDBConverter.logger.debug("Convert 'DataObject' data format from "
          + this.getFromDataFormat().getType() + " to "
          + this.getToDataFormat().getType());
    }

    // RDB SDO data holder
    DataObject rdbDataObject = this.getToDataFormat().getSDO();
    DataObject tableObject = rdbDataObject.createDataObject("table");
    DataObject rdbTableMetaDataObject = tableObject.createDataObject("rdbTableMetaData");
    DataObject columnTypeObject = null;
    DataObject rowObject = null;
    DataObject columnObject = null;

    // read data from RandomFile SDO
    List<DataObject> fileObjects = fileSDO.getList("file");

    // fill the rdb table meta data object
    rdbTableMetaDataObject.set("name", fileSDO.getString("folder").replace(":", "").replace("\\", "_").toUpperCase());
    rdbTableMetaDataObject.set("schema", "");
    rdbTableMetaDataObject.set("catalog", "");

    columnTypeObject = rdbTableMetaDataObject.createDataObject("columnType");
    columnTypeObject.set("columnName", "PK_ID");
    columnTypeObject.set("isPrimaryKey", true);
    columnTypeObject.set(0, getColumnType(0, connector));
    columnTypeObject = rdbTableMetaDataObject.createDataObject("columnType");
    columnTypeObject.set("columnName", "NAME");
    columnTypeObject.set(0, getColumnType(1, connector));
    columnTypeObject = rdbTableMetaDataObject.createDataObject("columnType");
    columnTypeObject.set("columnName", "CONTENT");
    columnTypeObject.set(0, getColumnType(2, connector));

    // fill the rdb table data object
    for (int i = 0; i < fileObjects.size(); i++) {
      rowObject = tableObject.createDataObject("row");

      columnObject = rowObject.createDataObject("column");
      columnObject.set("name", "PK_ID");
      columnObject.set(0, String.valueOf(i));

      columnObject = rowObject.createDataObject("column");
      columnObject.set("name", "NAME");
      columnObject.set(0, fileObjects.get(i).get("name"));

      columnObject = rowObject.createDataObject("column");
      columnObject.set("name", "CONTENT");
      columnObject.set(0, fileObjects.get(i).get("content"));
    }

    if (RandomFiletoRDBConverter.logger.isDebugEnabled()) {
      String result = XMLHelper.INSTANCE.save(rdbDataObject, "commonj.sdo", "dataObject");
      RandomFiletoRDBConverter.logger.debug("RandomFile DataObject: " + result);
    }

    return rdbDataObject;
  }

  /*
   * (non-Javadoc)
   * @see org.simpl.core.services.dataformat.converter.DataFormatConverter#convertTo
   * (commonj .sdo.DataObject)
   */
  @SuppressWarnings({ "unchecked", "rawtypes" })
  @Override
  public DataObject convertFrom(DataObject rdbSDO, Connector connector) {
    if (RandomFiletoRDBConverter.logger.isDebugEnabled()) {
      RandomFiletoRDBConverter.logger.debug("Convert 'DataObject' data format from "
          + this.getToDataFormat().getType() + " to "
          + this.getFromDataFormat().getType());
    }

    // RandomFile SDO data holder
    DataObject folderSDO = this.getFromDataFormat().getSDO();
    DataObject fileSDO = null;

    // read data from RDB SDO
    List<DataObject> rdbTables = rdbSDO.getList("table");
    List<DataObject> rdbRows = null;
    List<DataObject> rdbColumns = null;

    DataObject rdbTable = rdbTables.get(0);
    rdbRows = rdbTable.getList("row");

    folderSDO.setString("folder", rdbTables.get(0).getDataObject("rdbTableMetaData")
        .getString("name"));
    
    for (DataObject rdbRow : rdbRows) {
      fileSDO = folderSDO.createDataObject("file");
      rdbColumns = rdbRow.getList("column");

      folderSDO.setString("folder", rdbTables.get(0).getDataObject("rdbTableMetaData")
          .getString("name"));

      for (DataObject rdbColumn : rdbColumns) {
        if (rdbColumn.getString("name").toLowerCase().equals("name")) {
          fileSDO.set("name", rdbColumn.getString(0));
        } else if (rdbColumn.getString("name").toLowerCase().equals("content")) {
          fileSDO.set("content", rdbColumn.getString(0));
        }
      }
    }

    if (RandomFiletoRDBConverter.logger.isDebugEnabled()) {
      String result = XMLHelper.INSTANCE.save(folderSDO, "commonj.sdo", "dataObject");
      RandomFiletoRDBConverter.logger.debug("RandomFile DataObject: " + result);
    }

    return folderSDO;
  }

  /**
   * Returns a SQL data type declaration depending on column index.
   * 
   * @param index
   *          of column
   * @param connector
   * @return For index=1 VARCHAR(511) and for index=2 a database specific type like
   *         LONGTEXT or BLOB to save the file content.
   */

  private String getColumnType(int index,
      @SuppressWarnings("rawtypes") Connector connector) {
    String sqlType = "VARCHAR(511)";

    if (index == 0) {
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
    } else if (index == 2) {
      if (connector instanceof MySQLRDBConnector) {
        return sqlType = "LONGTEXT";
      } else if (connector instanceof DB2RDBConnector) {
        return sqlType = "CLOB";
      } else if (connector instanceof PostgreSQLRDBConnector) {
        return sqlType = "BYTEA";
      } else {
        return sqlType = "BLOB";
      }
    }

    return sqlType;
  }
}
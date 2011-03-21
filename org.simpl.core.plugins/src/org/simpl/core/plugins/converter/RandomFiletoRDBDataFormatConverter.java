package org.simpl.core.plugins.converter;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.simpl.core.connector.Connector;
import org.simpl.core.plugins.connector.rdb.DB2RDBConnector;
import org.simpl.core.plugins.connector.rdb.DerbyRDBConnector;
import org.simpl.core.plugins.connector.rdb.EmbDerbyRDBConnector;
import org.simpl.core.plugins.connector.rdb.MySQLRDBConnector;

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
 * @version $Id: $<br>
 * @link http://code.google.com/p/simpl09/
 */
public class RandomFiletoRDBDataFormatConverter extends ConverterPlugin {
  static Logger logger = Logger.getLogger(RandomFiletoRDBDataFormatConverter.class);

  private final int RDB_TO_RANDOMFILE = 0;
  private final int RANDOMFILE_TO_RDB = 1;

  /**
   * Initialize the plug-in.
   */
  public RandomFiletoRDBDataFormatConverter() {
    this.setFromDataFormat("RandomFile");
    this.setToDataFormat("RDB");

    // Set up a simple configuration that logs on the console.
    PropertyConfigurator.configure("log4j.properties");
  }

  /*
   * (non-Javadoc)
   * @see org.simpl.core.services.dataformat.converter.DataFormatConverter#convertFrom
   * (commonj .sdo.DataObject)
   */
  @SuppressWarnings("unchecked")
  @Override
  public DataObject convertTo(DataObject folderSDO,
      @SuppressWarnings("rawtypes") Connector connector) {
    if (RandomFiletoRDBDataFormatConverter.logger.isDebugEnabled()) {
      RandomFiletoRDBDataFormatConverter.logger
          .debug("Convert 'DataObject' data format from "
              + this.getFromDataFormat().getType() + " to "
              + this.getToDataFormat().getType());
    }

    // RDB SDO data holder
    DataObject rdbSDO = this.getToDataFormat().getSDO();
    DataObject rdbTableObject = null;
    DataObject rdbColumnObject = null;
    List<String> rdbPrimaryKeys = new ArrayList<String>();

    DataObject fileObject = null;

    // read data from RandomFile SDO
    List<DataObject> fileObjects = folderSDO.getList("file");

    // write data to RDB SDO
    for (int i = 0; i < fileObjects.size(); i++) {
      fileObject = fileObjects.get(i);

      String result = "";

      // result = this
      // .convertStringFormat(fileObject.getString("content"),
      // RDB_TO_RANDOMFILE, dataSourceService);

      try {
        result = URLEncoder.encode(fileObject.getString("content"), "UTF-8");
      } catch (UnsupportedEncodingException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }

      // add table
      rdbTableObject = rdbSDO.createDataObject("table");
      rdbTableObject.set("name",
          folderSDO.getString("folder").toUpperCase().replace(".", "_"));
      rdbTableObject.set("catalog", "");
      rdbTableObject.set("schema", "");

      // add primary key
      rdbColumnObject = rdbTableObject.createDataObject("column");
      rdbColumnObject.set("name", "PK_ID");
      rdbColumnObject.set("type", this.getColumnType(0, connector));
      rdbColumnObject.set("value", String.valueOf(i));
      rdbPrimaryKeys.add("PK_ID");
      rdbTableObject.set("primaryKey", rdbPrimaryKeys);
      rdbPrimaryKeys.clear();

      // add columns
      rdbColumnObject = rdbTableObject.createDataObject("column");
      rdbColumnObject.set("name", "FILENAME");
      rdbColumnObject.set("type", this.getColumnType(1, connector));
      rdbColumnObject.set("value", fileObject.getString("name"));

      rdbColumnObject = rdbTableObject.createDataObject("column");
      rdbColumnObject.set("name", "FILECONTENT");
      rdbColumnObject.set("type", this.getColumnType(2, connector));

      // TODO: For LONGTEXT there should be also "'"...
      rdbColumnObject.set("value", "\'" + result + "\'");

    }

    if (RandomFiletoRDBDataFormatConverter.logger.isDebugEnabled()) {

      String result = XMLHelper.INSTANCE.save(rdbSDO, "commonj.sdo", "dataObject");

      RandomFiletoRDBDataFormatConverter.logger.debug("RandomFile DataObject: " + result);
    }

    return rdbSDO;
  }

  /*
   * (non-Javadoc)
   * @see org.simpl.core.services.dataformat.converter.DataFormatConverter#convertTo
   * (commonj .sdo.DataObject)
   */
  @SuppressWarnings("unchecked")
  @Override
  public DataObject convertFrom(DataObject rdbSDO,
      @SuppressWarnings("rawtypes") Connector connector) {
    if (RandomFiletoRDBDataFormatConverter.logger.isDebugEnabled()) {
      RandomFiletoRDBDataFormatConverter.logger
          .debug("Convert 'DataObject' data format from "
              + this.getToDataFormat().getType() + " to "
              + this.getFromDataFormat().getType());
    }

    // RandomFile SDO data holder
    DataObject folderSDO = this.getFromDataFormat().getSDO();
    DataObject fileSDO = null;

    // read data from RDB SDO
    List<DataObject> rdbTables = rdbSDO.getList("table");
    List<DataObject> rdbColumns = null;

    folderSDO.setString("folder", rdbTables.get(0).getString("name"));

    for (DataObject rdbTable : rdbTables) {
      fileSDO = folderSDO.createDataObject("file");

      rdbColumns = rdbTable.getList("column");

      for (DataObject rdbColumn : rdbColumns) {

        String result = "";

        // result = this.convertStringFormat(rdbColumn
        // .getString("value"), RDB_TO_RANDOMFILE,
        // dataSourceService);

        try {
          result = URLDecoder.decode(rdbColumn.getString("value"), "UTF-8");
          // Remove the "'" from beginning and end of the string
          result = result.replaceFirst("\'", "");
          result = result.substring(0, result.length());
        } catch (UnsupportedEncodingException e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
        }

        if (rdbColumn.getString("name").equals("FILENAME")) {
          fileSDO.set("name", rdbColumn.get("value"));
        } else {
          fileSDO.set("content", result);
        }
      }
    }

    if (RandomFiletoRDBDataFormatConverter.logger.isDebugEnabled()) {

      String result = XMLHelper.INSTANCE.save(folderSDO, "commonj.sdo", "dataObject");

      RandomFiletoRDBDataFormatConverter.logger.debug("RandomFile DataObject: " + result);
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
      } else {
        return sqlType = "BLOB";
      }
    }

    return sqlType;
  }

  @SuppressWarnings("unused")
  private String convertStringFormat(String input, int mode,
      @SuppressWarnings("rawtypes") Connector connector) {
    String result = input;

    if (mode == RDB_TO_RANDOMFILE) {
      if (connector instanceof MySQLRDBConnector) {
        result = result.replace("\\\\", "\\");
        result = result.replace("\\'", "'");
        result = result.replace("\\\"", "\"");
        result = result.replace("\\%", "%");
        result = result.replace("\\_", "_");
        result = result.replace("\\b", "\b");
        result = result.replace("\\n", "\n");
        result = result.replace("\\r", "\r");
        result = result.replace("\\t", "\t");
      }
    } else if (mode == RANDOMFILE_TO_RDB) {
      if (connector instanceof MySQLRDBConnector) {
        result = result.replace("\\", "\\\\");
        result = result.replace("'", "\\'");
        result = result.replace("\"", "\\\"");
        result = result.replace("%", "\\%");
        result = result.replace("_", "\\_");
        result = result.replace("\b", "\\b");
        result = result.replace("\n", "\\n");
        result = result.replace("\r", "\\r");
        result = result.replace("\t", "\\t");
      }
    }

    return result;
  }
}
package org.simpl.core.plugins.dataformat.converter;

import java.util.List;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.simpl.core.plugins.datasource.rdb.DB2RDBDataSourceService;
import org.simpl.core.plugins.datasource.rdb.DerbyRDBDataSourceService;
import org.simpl.core.plugins.datasource.rdb.EmbDerbyRDBDataSourceService;
import org.simpl.core.plugins.datasource.rdb.MySQLRDBDataSourceService;
import org.simpl.core.services.datasource.DataSourceService;

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
public class CSVtoRDBDataFormatConverter extends DataFormatConverterPlugin {
  static Logger logger = Logger.getLogger(DB2RDBDataSourceService.class);

  /**
   * Initialize the plug-in.
   */
  public CSVtoRDBDataFormatConverter() {
    this.setFromDataFormat("CSV");
    this.setToDataFormat("RDB");

    // Set up a simple configuration that logs on the console.
    PropertyConfigurator.configure("log4j.properties");
  }

  /*
   * (non-Javadoc)
   * @see
   * org.simpl.core.services.dataformat.converter.DataFormatConverter#convertFrom(commonj
   * .sdo.DataObject)
   */
  @SuppressWarnings("unchecked")
  @Override
  public DataObject convertTo(DataObject csvSDO, DataSourceService dataSourceService) {
    if (CSVtoRDBDataFormatConverter.logger.isDebugEnabled()) {
      CSVtoRDBDataFormatConverter.logger.debug("Convert 'DataObject' data format from "
          + this.getFromDataFormat().getType() + " to "
          + this.getToDataFormat().getType());
    }

    // use csv SDO and add rdb table meta data
    DataObject rdbSDO = csvSDO;
    rdbSDO.setString("dataFormatType", "RDB");

    DataObject csvTableMetaDataObject = null;
    DataObject rdbTableMetaDataObject = null;
    DataObject rdbTableMetaDataColumnTypeObject = null;
    DataObject rdbTableObject = null;
    List<DataObject> rdbTables = rdbSDO.getList("table");
    List<DataObject> rdbRows = null;
    List<DataObject> rdbColumns = null;

    // add rdb table meta data
    for (int i = 0; i < rdbTables.size(); i++) {
      csvTableMetaDataObject = rdbTables.get(i).getDataObject("csvTableMetaData");
      rdbTableObject = rdbTables.get(i);
      rdbRows = rdbTables.get(i).getList("row");
      rdbColumns = rdbRows.get(0).getList("column");

      // remove csv table meta data
      csvTableMetaDataObject.detach();

      rdbTableMetaDataObject = rdbTableObject.createDataObject("rdbTableMetaData");
      rdbTableMetaDataObject.setString("name", csvTableMetaDataObject.getString(
          "filename").toUpperCase().replace(".", "_"));
      rdbTableMetaDataObject.setString("schema", "");
      rdbTableMetaDataObject.setString("catalog", "");

      for (int k = 0; k < rdbColumns.size(); k++) {
        rdbTableMetaDataColumnTypeObject = rdbTableMetaDataObject
            .createDataObject("columnType");
        rdbTableMetaDataColumnTypeObject.setString("columnName", rdbColumns.get(k)
            .getString("name"));
        rdbTableMetaDataColumnTypeObject.set(0, this.getColumnType(rdbColumns.get(k)
            .getString(0), dataSourceService));
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
  @SuppressWarnings("unchecked")
  @Override
  public DataObject convertFrom(DataObject rdbSDO, DataSourceService dataSourceService) {
    if (CSVtoRDBDataFormatConverter.logger.isDebugEnabled()) {
      CSVtoRDBDataFormatConverter.logger.debug("Convert 'DataObject' data format from "
          + this.getToDataFormat().getType() + " to "
          + this.getFromDataFormat().getType());
    }

    // use rdb SDO and add csv table meta data
    DataObject csvSDO = rdbSDO;
    csvSDO.setString("dataFormatType", "CSV");

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
   * @param dataSourceService
   * @return
   */
  @SuppressWarnings("unchecked")
  private String getColumnType(String type, DataSourceService dataSourceService) {
    String sqlType = "VARCHAR(255)";

    try {
      if (Integer.valueOf(type) != null) {
        if (dataSourceService instanceof DB2RDBDataSourceService) {
          return sqlType = "INTEGER";
        } else if (dataSourceService instanceof EmbDerbyRDBDataSourceService) {
          return sqlType = "INT";
        } else if (dataSourceService instanceof DerbyRDBDataSourceService) {
          return sqlType = "INT";
        } else if (dataSourceService instanceof MySQLRDBDataSourceService) {
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
        if (dataSourceService instanceof DB2RDBDataSourceService) {
          return sqlType = "SMALLINT";
        } else if (dataSourceService instanceof EmbDerbyRDBDataSourceService) {
          return sqlType = "SMALLINT";
        } else if (dataSourceService instanceof DerbyRDBDataSourceService) {
          return sqlType = "SMALLINT";
        } else if (dataSourceService instanceof MySQLRDBDataSourceService) {
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
        if (dataSourceService instanceof DB2RDBDataSourceService) {
          return sqlType = "FLOAT(23)";
        } else if (dataSourceService instanceof EmbDerbyRDBDataSourceService) {
          return sqlType = "FLOAT";
        } else if (dataSourceService instanceof DerbyRDBDataSourceService) {
          return sqlType = "FLOAT";
        } else if (dataSourceService instanceof MySQLRDBDataSourceService) {
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
        if (dataSourceService instanceof DB2RDBDataSourceService) {
          return sqlType = "DOUBLE";
        } else if (dataSourceService instanceof EmbDerbyRDBDataSourceService) {
          return sqlType = "DOUBLE";
        } else if (dataSourceService instanceof DerbyRDBDataSourceService) {
          return sqlType = "DOUBLE";
        } else if (dataSourceService instanceof MySQLRDBDataSourceService) {
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
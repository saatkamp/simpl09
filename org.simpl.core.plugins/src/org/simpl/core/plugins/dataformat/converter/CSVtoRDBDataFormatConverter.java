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

    // data from CSV SDO
    List<DataObject> csvTables = csvSDO.getList("table");
    List<DataObject> csvRows = null;
    List<DataObject> csvColumns = null;

    // data of RDB SDO
    DataObject rdbSDO = this.getToDataFormat().getSDO();
    DataObject rdbTableObject = null;
    DataObject rdbRowObject = null;
    DataObject rdbColumnObject = null;

    // write data to RDB SDO
    for (int i = 0; i < csvTables.size(); i++) {
      // add table
      rdbTableObject = rdbSDO.createDataObject("table");
      rdbTableObject.set("name", csvSDO.getString("filename").toUpperCase().replace(".",
          "_"));
      rdbTableObject.set("catalog", "");
      rdbTableObject.set("schema", "");

      csvRows = csvTables.get(i).getList("row");

      // add rows
      for (int j = 0; j < csvRows.size(); j++) {
        rdbRowObject = rdbTableObject.createDataObject("row");

        csvColumns = csvRows.get(j).getList("column");

        // add primary key column
        rdbColumnObject = rdbRowObject.createDataObject("column");
        rdbColumnObject.set("name", "PK_ID");
        rdbColumnObject.set("type", this.getColumnType("123", dataSourceService));
        rdbColumnObject.set("value", String.valueOf(j));
        rdbColumnObject.set("pk", true);

        // add columns
        for (DataObject csvColumn : csvColumns) {
          rdbColumnObject = rdbRowObject.createDataObject("column");
          rdbColumnObject.set("name", csvColumn.getString("name"));
          rdbColumnObject.set("type", this.getColumnType(csvColumn.getString("value"),
              dataSourceService));
          rdbColumnObject.set("value", csvColumn.getString("value"));
        }
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

    // data from RDB SDO
    List<DataObject> rdbTables = rdbSDO.getList("table");
    List<DataObject> rdbRows = null;
    List<DataObject> rdbColumns = null;

    // data of CSV SDO
    DataObject csvSDO = this.getFromDataFormat().getSDO();
    DataObject csvTableObject = null;
    DataObject csvRowObject = null;
    DataObject csvColumnObject = null;

    csvSDO.set("filename", "");

    // write data to RDB SDO
    for (int i = 0; i < rdbTables.size(); i++) {
      // add table
      csvTableObject = csvSDO.createDataObject("table");

      rdbRows = rdbTables.get(i).getList("row");

      // add rows
      for (int j = 0; j < rdbRows.size(); j++) {
        csvRowObject = csvTableObject.createDataObject("row");
        rdbColumns = rdbRows.get(j).getList("column");

        // add columns
        for (DataObject rdbColumn : rdbColumns) {
          csvColumnObject = csvRowObject.createDataObject("column");
          csvColumnObject.set("name", rdbColumn.getString("name"));
          csvColumnObject.set("value", rdbColumn.getString("value"));
        }
      }
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
   * TODO: support more sql types like date, decimal, ...
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
package org.simpl.core.plugins.dataformat.converter;

import java.util.ArrayList;
import java.util.List;

import commonj.sdo.DataObject;

/**
 * <b>Purpose: Converts the CSV data format to the RDB data format and vice versa.</b> <br>
 * <b>Description:</b><br>
 * <b>Copyright:</b> <br>
 * <b>Company:</b> SIMPL<br>
 * 
 * @author schneimi<br>
 * @version $Id$<br>
 * @link http://code.google.com/p/simpl09/
 */
public class RDBCSVDataFormatConverter extends DataFormatConverterPlugin {
  /**
   * Initialize the plug-in.
   */
  public RDBCSVDataFormatConverter() {
    this.setFromDataFormat("CSV");
    this.setToDataFormat("RDB");
  }

  /*
   * (non-Javadoc)
   * @see
   * org.simpl.core.services.dataformat.converter.DataFormatConverter#convertFrom(commonj
   * .sdo.DataObject)
   */
  @SuppressWarnings("unchecked")
  @Override
  public DataObject convertTo(DataObject csvSDO) {
    // RDB SDO data
    DataObject rdbSDO = this.getToDataFormat().getSDO();
    DataObject rdbTableObject = null;
    DataObject rdbColumnObject = null;
  
    // read data from CSV SDO
    List<DataObject> csvDataRows = csvSDO.getList("dataset");
  
    // write data to RDB SDO
    for (int i = 0; i < csvDataRows.size(); i++) {
      rdbTableObject = rdbSDO.createDataObject("table");
      rdbTableObject.set("name", csvSDO.getString("filename").toUpperCase().replace(".",
          "_"));
      rdbTableObject.set("catalog", "");
      rdbTableObject.set("schema", "");
  
      List<DataObject> csvColumns = csvDataRows.get(i).getList("column");
  
      for (DataObject csvColumn : csvColumns) {
        rdbColumnObject = rdbTableObject.createDataObject("column");
        rdbColumnObject.set("name", csvColumn.getString("name"));
        rdbColumnObject.set("type", this.getColumnType(csvColumn.getString("value")));
        rdbColumnObject.set("value", csvColumn.getString("value"));
      }
    }
  
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
  public DataObject convertFrom(DataObject rdbSDO) {
    // CSV SDO data
    DataObject csvSDO = this.getFromDataFormat().getSDO();
    DataObject csvHeaderObject = null;
    DataObject csvDatasetObject = null;
    DataObject csvColumnObject = null;
    List<String> csvHeaderNames = new ArrayList<String>();
    
    // read data from RDB SDO
    List<DataObject> rdbTables = rdbSDO.getList("table");
    List<DataObject> rdbColumns = null;
    
    csvHeaderObject = csvSDO.createDataObject("header");

    for (DataObject rdbTable : rdbTables) {
      csvDatasetObject = csvSDO.createDataObject("dataset");
      rdbColumns = rdbTable.getList("column");
      
      for (DataObject rdbColumn : rdbColumns) {
        csvColumnObject = csvDatasetObject.createDataObject("column");
        csvColumnObject.set("name", rdbColumn.get("name"));
        csvColumnObject.set("value", rdbColumn.get("value"));
        
        if (!csvHeaderNames.contains(rdbColumn.get("name"))) {
          csvHeaderNames.add(rdbColumn.getString("name"));
        }
      }
    }
    
    csvHeaderObject.setList("column", csvHeaderNames);
    
    return csvSDO;
  }

  /**
   * Returns a SQL data type declaration depending on string content.
   * 
   * TODO: support more sql types like date, decimal, ...
   * 
   * @param type
   * @return
   */
  private String getColumnType(String type) {
    String sqlType = null;

    try {
      if (Integer.valueOf(type) != null) {
        sqlType = "INTEGER(10)";
      }
    } catch (NumberFormatException e) {
      // type is not integer
    }

    try {
      if (Boolean.valueOf(type) != null) {
        sqlType = "TINYINT(1)";
      }
    } catch (NumberFormatException e) {
      // type is not boolean
    }

    try {
      if (Float.valueOf(type) != null) {
        sqlType = "FLOAT(8)";
      }
    } catch (NumberFormatException e) {
      // type is not float
    }

    try {
      if (Double.valueOf(type) != null) {
        sqlType = "DOUBLE(8)";
      }
    } catch (NumberFormatException e) {
      // type is not double
    }

    // take VARCHAR as default
    if (sqlType == null) {
      sqlType = "VARCHAR(255)";
    }

    return sqlType;
  }
}
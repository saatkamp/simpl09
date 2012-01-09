package org.simpl.core.plugins.dataconverter.sensor.network;

import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import net.tinyos.tinydb.QueryResult;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.simpl.core.plugins.dataconverter.DataConverterPlugin;

import commonj.sdo.DataObject;

/**
 * <b>Purpose:</b>Used to create a SDO from TinyDBResult.<br>
 * <b>Description:</b>Converts the data from a list of QueryResults to a
 * DataObject. A conversion in the other direction is not supported<br>
 * <b>Copyright:</b>Licensed under the Apache License, Version 2.0.
 * http://www.apache.org/licenses/LICENSE-2.0<br>
 * <b>Company:</b>SIMPL<br>
 * 
 * @author Henrik Pietranek <henrik.pietranek@googlemail.com><br>
 * @link http://code.google.com/p/simpl09/
 */
public class TinyDBResultConverter extends
    DataConverterPlugin<TinyDBResult, String> {

  static Logger logger = Logger.getLogger(TinyDBResultConverter.class);

  public TinyDBResultConverter() {
    this.setDataFormat("TinyDBDataFormat");
    this.setSchemaType("tTinyDBDataFormat");

    // Set up a simple configuration that logs on the console.
    PropertyConfigurator.configure("log4j.properties");
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * org.simpl.core.services.dataformat.DataFormatService#getSDO(java.lang.Object
   * )
   */
  @SuppressWarnings("rawtypes")
  @Override
  public DataObject toSDO(TinyDBResult tinyDBResult) {
    DataObject tinyDBDataObject = this.getSDO();
    DataObject dataFormatMetaDataObject = tinyDBDataObject
        .createDataObject("dataFormatMetaData");
    DataObject tableObject = tinyDBDataObject.createDataObject("table");
    Iterator<QueryResult> iterator = null;
    DataObject rowObject = null;
    DataObject columnObject = null;

    if (TinyDBResultConverter.logger.isDebugEnabled()) {
      TinyDBResultConverter.logger
          .debug("Convert data from 'TinyDBResult' to 'DataObject'.");
    }

    List<QueryResult> queryResults = tinyDBResult.getQueryResults();

    // add data format meta data
    dataFormatMetaDataObject.set("dataSource", tinyDBResult.getDataSource()
        .getName());

    // add results
    iterator = queryResults.iterator();
    while (iterator.hasNext()) {
      rowObject = tableObject.createDataObject("row");
      Vector columnHeadings = tinyDBResult.getColumnHeadings();
      Vector vector = iterator.next().resultVector();
      for (int i = 0; i < vector.size(); i++) {
        columnObject = rowObject.createDataObject("column");
        columnObject.set("name", columnHeadings.elementAt(i));
        columnObject.set(0, vector.elementAt(i));
      }
    }
    return tinyDBDataObject;
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * org.simpl.core.services.dataformat.DataFormatService#fromSDO(commonj.sdo
   * .DataObject)
   */
  @Override
  public String fromSDO(DataObject data) {
    // not supported
    return null;
  }

}

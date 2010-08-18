package org.simpl.core.plugins.dataformat.fs;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.simpl.core.plugins.dataformat.DataFormatPlugin;
import org.simpl.core.plugins.datasource.rdb.DB2RDBDataSourceService;

import au.com.bytecode.opencsv.CSVReader;
import au.com.bytecode.opencsv.CSVWriter;

import commonj.sdo.DataObject;

/**
 * <b>Purpose:</b>Used to create a SDO from standard CSV file and vice versa.<br>
 * <b>Description:</b>Uses OpenCSV to read and write the CSV data. The data is written to
 * a temporary file, to be able to return it as java.io.File.<br>
 * <b>Copyright:</b>Licensed under the Apache License, Version 2.0.
 * http://www.apache.org/licenses/LICENSE-2.0<br>
 * <b>Company:</b>SIMPL<br>
 * 
 * @author schneimi<br>
 * @version $Id$<br>
 * @link http://code.google.com/p/simpl09/
 */
public class CSVDataFormat extends DataFormatPlugin<File, File> {
  static Logger logger = Logger.getLogger(DB2RDBDataSourceService.class);

  /**
   * Initialize the plug-in.
   */
  public CSVDataFormat() {
    this.setType("CSV");
    this.setSchemaFile("CSVDataFormat.xsd");
    this.setSchemaType("tCSVDataFormat");

    // Set up a simple configuration that logs on the console.
    PropertyConfigurator.configure("log4j.properties");
  }

  /*
   * (non-Javadoc)
   * @see org.simpl.core.services.dataformat.DataFormatService#getSDO(java.lang.Object )
   */
  public DataObject toSDO(File file) {
    CSVReader csvReader;
    String[] headLine;
    String[] nextLine;
    DataObject sdo = this.getSDO();
    DataObject tableObject = sdo.createDataObject("table");
    DataObject rowObject;
    DataObject columnObject;

    if (CSVDataFormat.logger.isDebugEnabled()) {
      CSVDataFormat.logger.debug("Convert data from 'File' to 'DataObject'.");
    }

    try {
      csvReader = new CSVReader(new FileReader(file));

      // take first line as headline
      headLine = csvReader.readNext();

      sdo.setString("filename", file.getName());

      // uncommented because CSV properties cannot be recognized and used by OpenCSV
      // sdo.setChar("separator", ',');
      // sdo.setChar("quoteChar", '\0');
      // sdo.setChar("escapeChar", '\\');
      // sdo.setBoolean("strictQuotes", false);

      while ((nextLine = csvReader.readNext()) != null) {
        rowObject = tableObject.createDataObject("row");

        for (int i = 0; i < nextLine.length; i++) {
          columnObject = rowObject.createDataObject("column");
          columnObject.setString("name", headLine[i]);
          columnObject.setString("value", nextLine[i]);
        }
      }
    } catch (FileNotFoundException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    return sdo;
  }

  /*
   * (non-Javadoc)
   * @see org.simpl.core.services.dataformat.DataFormatService#fromSDO(commonj.sdo
   * .DataObject)
   */
  @SuppressWarnings("unchecked")
  @Override
  public File fromSDO(DataObject data) {
    File file = null;
    FileWriter fileWriter;
    CSVWriter csvWriter;

    String[] row = null;
    List<String> headerRow = new ArrayList<String>();
    List<String[]> dataRows = new ArrayList<String[]>();
    List<String[]> csvLines = new ArrayList<String[]>();
    List<DataObject> tableObjects = data.getList("table");
    List<DataObject> rowObjects = null;

    boolean headerComplete = false;
    // uncommented because CSV properties cannot be recognized and used by OpenCSV
    // char separator = data.getChar("separator");
    // char quoteChar = data.getChar("quoteChar");
    // char escapeChar = data.getChar("escapeChar");
    // boolean strictQuotes = data.getBoolean("strictQuotes");

    if (CSVDataFormat.logger.isDebugEnabled()) {
      CSVDataFormat.logger.debug("Convert data from 'DataObject' to 'File'.");
    }

    try {
      file = File.createTempFile("CSVDataFormatFile", ".tmp");
      file.deleteOnExit();

      fileWriter = new FileWriter(file);
      csvWriter = new CSVWriter(fileWriter);

      // get rows
      rowObjects = tableObjects.get(0).getList("row");

      // gather rows
      for (DataObject rowObject : rowObjects) {
        List<DataObject> columns = rowObject.getList("column");
        List<String> values = new ArrayList<String>();

        for (DataObject column : columns) {
          values.add(column.getString("value"));

          if (!headerComplete) {
            headerRow.add(column.getString("name"));
          }
        }

        headerComplete = true;

        row = Arrays.copyOf(values.toArray(), values.size(), String[].class);

        // add row
        dataRows.add(row);
      }

      csvLines.add(headerRow.toArray(new String[headerRow.size()]));
      csvLines.addAll(dataRows);

      // write lines to temporary file
      csvWriter.writeAll(csvLines);
      csvWriter.close();
      fileWriter.close();
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    return file;
  }
}
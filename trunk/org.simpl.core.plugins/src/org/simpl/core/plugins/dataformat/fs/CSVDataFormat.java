package org.simpl.core.plugins.dataformat.fs;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.simpl.core.plugins.dataformat.DataFormatPlugin;

import au.com.bytecode.opencsv.CSVReader;
import au.com.bytecode.opencsv.CSVWriter;

import commonj.sdo.DataObject;

/**
 * <b>Purpose:</b>Used to create a SDO from standard CSV file and vice versa.<br>
 * <b>Description:</b>Uses OpenCSV to read and write the CSV data. The data is
 * written to a temporary file, to be able to return it as java.io.File.<br>
 * <b>Copyright:</b> <br>
 * <b>Company:</b> SIMPL<br>
 * 
 * TODO: rename to CSVFileDataFormat
 * 
 * @author schneimi<br>
 * @version $Id: CSVDataFormat.java 1130 2010-04-19 13:54:30Z
 *          michael.schneidt@arcor.de $<br>
 * @link http://code.google.com/p/simpl09/
 */
public class CSVDataFormat extends DataFormatPlugin<File, File> {
  /**
   * Initialize the plug-in.
   */
  public CSVDataFormat() {
    this.setType("CSV");
    this.setSchemaFile("CSVDataFormat.xsd");
    this.setSchemaType("tCSVDataFormat");
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * org.simpl.core.services.dataformat.DataFormatService#getSDO(java.lang.Object
   * )
   */
  public DataObject toSDO(File file) {
    CSVReader csvReader;
    String[] headLine;
    String[] nextLine;
    DataObject sdo = this.getSDO();
    DataObject headerObject = sdo.createDataObject("header");
    DataObject rowObject;
    DataObject columnObject;

    try {
      csvReader = new CSVReader(new FileReader(file));
      headLine = csvReader.readNext();
      headerObject.setList("column", Arrays.asList(headLine));

      sdo.setString("filename", file.getName());
      // sdo.setChar("separator", ',');
      // sdo.setChar("quoteChar", '\0');
      // sdo.setChar("escapeChar", '\\');
      // sdo.setBoolean("strictQuotes", false);

      while ((nextLine = csvReader.readNext()) != null) {
        rowObject = sdo.createDataObject("row");

        for (String value : nextLine) {
          columnObject = rowObject.createDataObject("column");
          columnObject.setString("value", value);
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
   * 
   * @see
   * org.simpl.core.services.dataformat.DataFormatService#fromSDO(commonj.sdo
   * .DataObject)
   */
  @SuppressWarnings("unchecked")
  @Override
  public File fromSDO(DataObject data) {
    File file = null;
    FileWriter fileWriter;
    CSVWriter csvWriter;
    String[] row = null;
    List<String[]> lines = new ArrayList<String[]>();
    // char separator = data.getChar("separator");
    // char quoteChar = data.getChar("quoteChar");
    // char escapeChar = data.getChar("escapeChar");
    // boolean strictQuotes = data.getBoolean("strictQuotes");

    try {
      file = File.createTempFile("WindowsLocalFSDataSource", ".tmp");
      file.deleteOnExit();

      fileWriter = new FileWriter(file);
      csvWriter = new CSVWriter(fileWriter);

      // read header
      row = (String[]) data.getDataObject("header").getList("column").toArray();

      // add header
      lines.add(row);

      // read rows
      List<DataObject> rows = data.getList("row");

      for (DataObject rowObject : rows) {
        List<DataObject> columns = rowObject.getList("column");
        List<String> values = new ArrayList<String>();

        for (DataObject column : columns) {
          values.add((String) column.getString("value"));
        }

        row = Arrays.copyOf(values.toArray(), values.size(), String[].class);

        // add row
        lines.add(row);
      }

      // write lines to temporary file
      csvWriter.writeAll(lines);
      csvWriter.close();
      fileWriter.close();
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    return file;
  }

  /* (non-Javadoc)
   * @see org.simpl.core.services.dataformat.DataFormat#createTarget(java.lang.Object, commonj.sdo.DataObject, java.lang.String)
   */
  @Override
  public List<String> getCreateTargetStatements(DataObject data, String target) {
    // this function is not used on local filesystems
    return null;
  }
}
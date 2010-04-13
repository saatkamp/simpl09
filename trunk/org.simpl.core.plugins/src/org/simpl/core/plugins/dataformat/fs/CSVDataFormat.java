package org.simpl.core.plugins.dataformat.fs;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

import org.simpl.core.plugins.dataformat.DataFormatPlugin;

import au.com.bytecode.opencsv.CSVReader;

import commonj.sdo.DataObject;

/**
 * <b>Purpose:</b> <br>
 * <b>Description:</b> <br>
 * <b>Copyright:</b> <br>
 * <b>Company:</b> SIMPL<br>
 * 
 * @author Michael Schneidt <michael.schneidt@arcor.de><br>
 * @version $Id$<br>
 * @link http://code.google.com/p/simpl09/
 */
public class CSVDataFormat extends DataFormatPlugin {
  public CSVDataFormat() {
    this.setType("CSV");
    this.addSubtype("Headline");
    this.setSchemaFile("CSVDataFormat.xsd");
    this.setSchemaType("tTable");
    this.init();
  }

  /* TODO Generic File soll java.io.File werden
   * (non-Javadoc)
   * @see org.simpl.core.services.dataformat.DataFormatService#getSDO(java.lang.Object)
   */
  public <File> DataObject toSDO(File file) {
    CSVReader reader;
    String[] headLine;
    String[] nextLine;
    DataObject sdo = this.getDataObjectFromSchema();
    DataObject headerObject = sdo.createDataObject("header");
    DataObject rowObject;
    DataObject columnObject;
    
    try {
      reader = new CSVReader(new FileReader((java.io.File) file));
      headLine = reader.readNext();
      headerObject.setList("column", Arrays.asList(headLine));
      
      while ((nextLine = reader.readNext()) != null) {
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
}
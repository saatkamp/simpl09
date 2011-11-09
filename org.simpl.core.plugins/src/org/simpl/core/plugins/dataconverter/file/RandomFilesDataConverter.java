package org.simpl.core.plugins.dataconverter.file;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.simpl.core.plugins.dataconverter.DataConverterPlugin;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import commonj.sdo.DataObject;

/**
 * <b>Purpose:</b>Used to create a SDO from one or more files from a folder (also
 * unstructured once) and vice versa.<br>
 * <b>Description:</b>This is just a very simple implementation which handles the data of
 * a file as a BASE64 encoded string.<br>
 * <b>Copyright:</b>Licensed under the Apache License, Version 2.0.
 * http://www.apache.org/licenses/LICENSE-2.0<br>
 * <b>Company:</b>SIMPL<br>
 * 
 * @author hahnml<br>
 * @version $Id: RandomFilesDataConverter.java 1815 2011-07-05 12:33:12Z
 *          michael.schneidt@arcor.de $<br>
 * @link http://code.google.com/p/simpl09/
 */
public class RandomFilesDataConverter extends DataConverterPlugin<RandomFiles, File> {
  static Logger logger = Logger.getLogger(RandomFilesDataConverter.class);

  /**
   * Initialize the plug-in.
   */
  public RandomFilesDataConverter() {
    this.setDataFormat("RandomFilesDataFormat");
    this.setSchemaFile("RandomFilesDataFormat.xsd");
    this.setSchemaType("tRandomFilesDataFormat");

    // Set up a simple configuration that logs on the console.
    PropertyConfigurator.configure("log4j.properties");
  }

  /*
   * (non-Javadoc)
   * @see org.simpl.core.services.dataformat.DataFormatService#getSDO(java.lang .Object )
   */
  public DataObject toSDO(RandomFiles result) {
    DataObject sdo = this.getSDO();
    File[] files = result.getFiles();

    if (RandomFilesDataConverter.logger.isDebugEnabled()) {
      RandomFilesDataConverter.logger.debug("Convert data from 'File' to 'DataObject'.");
    }
   
    try {
      // extract folder location from first file
      if (files.length > 0) {
        File file1 = files[0];
        sdo.setString("folder", file1.getAbsolutePath().replace("\\" + file1.getName(), ""));
      }
      
      for (File current : files) {
        DataObject fileSDO = sdo.createDataObject("file");

        fileSDO.setString("name", current.getName());

        // Read the entire content of the file into a string and BASE64 encode it
        String content = FileUtils.readFileToString(current);
        BASE64Encoder encoder = new BASE64Encoder();

        fileSDO.setString("content", encoder.encode(content.getBytes()));

        if (logger.isDebugEnabled()) {
          logger.debug("Filename: " + fileSDO.getString("name"));
        }
      }
    } catch (IOException e) {
      logger.error("Exception during transformation from file to data object", e);
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
    // TODO: note that the temporary data is not deleted.
    File tmpDir = null;

    if (!data.getString("folder").equals("")) {
      try {
        tmpDir = File.createTempFile("simpl", "");
        tmpDir.delete();
        tmpDir.mkdir();
        tmpDir.deleteOnExit();
      } catch (IOException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
    }

    if (RandomFilesDataConverter.logger.isDebugEnabled()) {
      RandomFilesDataConverter.logger.debug("Convert data from 'DataObject' to 'File'.");
      RandomFilesDataConverter.logger.debug("Created temporary folder: " + tmpDir);
    }

    try {
      List<DataObject> files = data.getList("file");

      for (DataObject fileSDO : files) {
        File file = new File(tmpDir, fileSDO.getString("name"));
        BASE64Decoder decoder = new BASE64Decoder();
        String content = new String(decoder.decodeBuffer(fileSDO.getString("content")));
        FileUtils.writeStringToFile(file, content);
      }
    } catch (IOException e) {
      logger.error("Exception during transformation from data object to file", e);
    }

    return tmpDir;
  }
}
package org.simpl.core.plugins.datasource.fs;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.log4j.PropertyConfigurator;
import org.simpl.core.SIMPLCore;
import org.simpl.core.plugins.datasource.DataSourcePlugin;
import org.simpl.core.services.dataformat.DataFormatService;
import org.simpl.core.services.datasource.exceptions.ConnectionException;

import commonj.sdo.DataObject;

/**
 * <b>Purpose:</b> <br>
 * <b>Description:</b> <br>
 * <b>Copyright:</b> <br>
 * <b>Company:</b> SIMPL<br>
 * 
 * @author Michael Schneidt <michael.schneidt@arcor.de><br>
 * @version $Id: WindowsLocalFSDataSource.java 1014 2010-03-29 09:16:08Z
 *          michael.schneidt@arcor.de $<br>
 * @link http://code.google.com/p/simpl09/
 */
public class WindowsLocalFSDataSource extends DataSourcePlugin {
  Runtime runtime = Runtime.getRuntime();
  
  public WindowsLocalFSDataSource() {
    this.setType("Local Filesystem");
    this.setMetaDataType("tFilesystemMetaData");
    this.addSubtype("Windows");
    this.addLanguage("Windows", "Shell Command");
    
    // Set up a simple configuration that logs on the console.
    PropertyConfigurator.configure("log4j.properties");
  }

  /*
   * (non-Javadoc)
   * @see org.simpl.core.datasource.DatasourceService#defineData(java.lang.String,
   * java.lang.String)
   */
  @Override
  public boolean executeStatement(String dir, String statement) throws ConnectionException {
    Process process;
    BufferedReader processReader;
    String line;
    File workingDir = new File(dir);
    
    try {
      if (!workingDir.isDirectory()) {
        workingDir = null;
      }
      
      process = runtime.exec(statement, new String[0], workingDir);
      processReader = new BufferedReader(new InputStreamReader(process.getInputStream(), "cp850"));
      
      while ((line = processReader.readLine()) != null) {
        // do nothing
        System.out.println(line);
      }
      
      process.destroy();
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
      
      return false;
    }
    return true;
  }

  /*
   * (non-Javadoc)
   * @see org.simpl.core.datasource.DatasourceService#depositData(java.lang.String,
   * java.lang.String, java.lang.String)
   */
  @Override
  public boolean depositData(String arg0, String arg1, String arg2)
      throws ConnectionException {
    // TODO Auto-generated method stub
    return false;
  }

  /*
   * (non-Javadoc)
   * @see org.simpl.core.datasource.DatasourceService#getMetaData(java.lang.String)
   */
  @Override
  public DataObject getMetaData(String arg0) throws ConnectionException {
    // TODO Auto-generated method stub
    return null;
  }

  /*
   * (non-Javadoc)
   * @see org.simpl.core.datasource.DatasourceService#writeBack(java.lang.String,
   * commonj.sdo.DataObject)
   */
  @Override
  public boolean writeBack(String dir, DataObject data) throws ConnectionException {
    boolean writeBack = false;
    DataFormatService<Object> dataFormatService = SIMPLCore.getInstance().dataFormatService("CSV", "Headline");
    File tempFile = (File) dataFormatService.fromSDO(data);
    
    // move temp file to given dir
    writeBack = tempFile.renameTo(new File(dir + File.separator + data.getString("name")));
    
    return writeBack;
  }

  /*
   * (non-Javadoc)
   * @see org.simpl.core.datasource.DatasourceService#retrieveData(java.lang.String,
   * java.lang.String)
   */
  @Override
  public DataObject retrieveData(String dir, String statement) throws ConnectionException {
    DataFormatService<Object> dataFormatService = SIMPLCore.getInstance().dataFormatService("CSV", "Headline");
    DataObject dataObject = dataFormatService.toSDO(new File(dir + File.separator + statement));
    
    return dataObject;
  }
}
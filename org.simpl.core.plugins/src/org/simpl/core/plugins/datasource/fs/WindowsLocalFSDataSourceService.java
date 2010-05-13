package org.simpl.core.plugins.datasource.fs;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.simpl.core.plugins.datasource.DataSourceServicePlugin;
import org.simpl.core.plugins.datasource.rdb.DB2RDBDataSourceService;
import org.simpl.core.services.datasource.DataSource;
import org.simpl.core.services.datasource.exceptions.ConnectionException;

import commonj.sdo.DataObject;

/**
 * <b>Purpose:</b>Provides access to a windows file system data source.<br>
 * <b>Description:</b>Statements (commands) are executed on the windows command shell and
 * existing commands used to realize the data source service functions.<br>
 * <b>Copyright:</b> <br>
 * <b>Company:</b> SIMPL<br>
 * 
 * @author schneimi<br>
 * @version $Id: WindowsLocalFSDataSource.java 1014 2010-03-29 09:16:08Z
 *          michael.schneidt@arcor.de $<br>
 * @link http://code.google.com/p/simpl09/
 */
public class WindowsLocalFSDataSourceService extends DataSourceServicePlugin<File, File> {
  static Logger logger = Logger.getLogger(DB2RDBDataSourceService.class);
  
  /**
   * Runtime to execute commands on the file system.
   */
  Runtime runtime = Runtime.getRuntime();

  /**
   * Initialize the plug-in.
   */
  public WindowsLocalFSDataSourceService() {
    this.setType("Filesystem");
    this.setMetaDataSchemaType("tFilesystemMetaData");
    this.addSubtype("Windows Local");
    this.addLanguage("Windows Local", "Shell");

    // Set up a simple configuration that logs on the console.
    PropertyConfigurator.configure("log4j.properties");
  }

  /*
   * (non-Javadoc)
   * @see org.simpl.core.datasource.DatasourceService#defineData(java.lang.String,
   * java.lang.String)
   */
  @Override
  public boolean executeStatement(DataSource dataSource, String statement)
      throws ConnectionException {
    
    if (logger.isDebugEnabled()) {
      logger.debug("boolean executeStatement(" + dataSource.getAddress() + ", "
          + statement + ") executed.");
    }
    
    try {
      this.execute(statement, dataSource.getAddress(), null);
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();

      return false;
    }

    // execution is successful if no error occurs
    return true;
  }

  /*
   * (non-Javadoc)
   * @see org.simpl.core.datasource.DatasourceService#retrieveData(java.lang.String,
   * java.lang.String)
   */
  @Override
  public File retrieveData(DataSource dataSource, String file)
      throws ConnectionException {
    if (logger.isDebugEnabled()) {
      logger.debug("DataObject retrieveData(" + dataSource.getAddress() + ", "
          + file + ") executed.");
    }
    
    return new File(file);
  }

  /*
   * (non-Javadoc)
   * @see org.simpl.core.datasource.DatasourceService#writeBack(java.lang.String,
   * commonj.sdo.DataObject)
   */
  @Override
  public boolean writeBack(DataSource dataSource, File file)
      throws ConnectionException {
    if (logger.isDebugEnabled()) {
      logger.debug("boolean writeBack(" + dataSource.getAddress()
          + ", " + file + ") executed.");
    }
    
    return file.exists();
  }

  /*
   * (non-Javadoc)
   * @see
   * org.simpl.core.services.datasource.DataSourceService#writeData(org.simpl.core.services
   * .datasource.DataSource, commonj.sdo.DataObject, java.lang.String)
   */
  @Override
  public boolean writeData(DataSource dataSource, File dataFile, String target)
      throws ConnectionException {
    File targetFile = null;
    String dir = null;
    
    if (logger.isDebugEnabled()) {
      logger.debug("boolean writeData(" + dataSource.getAddress()
          + ", " + dataFile.getName() + ", " + target + ") executed.");
    }
    
    if (!dataSource.getAddress().equals("")) {
      dir = dataSource.getAddress() + File.separator;
    }
    
    targetFile = new File(dir + target);
    dataFile.renameTo(targetFile);

    return targetFile.exists();
  }

  /*
   * (non-Javadoc)
   * @see org.simpl.core.datasource.DatasourceService#depositData(java.lang.String,
   * java.lang.String, java.lang.String)
   */
  @Override
  public boolean depositData(DataSource dataSource, String file, String targetFile)
      throws ConnectionException {
    String[] envp = new String[] { "cmd", "/c", "start", "copy" };

    if (logger.isDebugEnabled()) {
      logger.debug("DataObject depositData(" + dataSource.getAddress() + ", " + file
          + "," + targetFile + ") executed.");
    }
    
    try {
      this.execute("copy " + file + " " + targetFile, dataSource.getAddress(), envp);
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();

      return false;
    }

    return true;
  }

  /*
   * (non-Javadoc)
   * @see org.simpl.core.datasource.DatasourceService#getMetaData(java.lang.String)
   */
  @Override
  public DataObject getMetaData(DataSource source, String filter)
      throws ConnectionException {
    DataObject metaDataObject = this.getMetaDataSDO();
    DataObject driveObject = null;
    DataObject commandObject = null;
    DataObject folderObject = null;
    DataObject fileObject = null;

    File[] driveList = File.listRoots();
    File[] fileList = null;
    File filterPath = null;

    // commands
    String[] commands = null;
    String commandName = null;
    String commandDescription = null;

    try {
      commands = this.execute("help", "/", null);

      // append line wrapped description to preceding command description
      for (int i = 3; i < commands.length; i++) {
        if (commands[i].indexOf(" ") > -1) {
          commandName = commands[i].substring(0, commands[i].indexOf(" "));
          commandDescription = commands[i].substring(commands[i].indexOf(" ")).trim();

          if (commandName.equals("")) {
            commands[i - 1] += " " + commandDescription;
            commands[i] = null;
          }
        }
      }

      // add commands to meta data object
      for (int i = 3; i < commands.length; i++) {
        if (commands[i] != null && commands[i].matches("[A-Z]{2,}.*")) {
          commandName = commands[i].substring(0, commands[i].indexOf(" "));
          commandDescription = commands[i].substring(commands[i].indexOf(" ")).trim();
          commandObject = metaDataObject.createDataObject("command");
          commandObject.set("name", commandName);
          commandObject.set("description", commandDescription);

        }
      }
    } catch (IOException e) {
      // no command meta data could be retrieved
    }

    // drives
    for (File drive : driveList) {
      driveObject = metaDataObject.createDataObject("drive");

      // TODO: Crashes on Windows7
      // driveObject.setString("name",
      // FileSystemView.getFileSystemView().getSystemDisplayName(drive));

      driveObject.setString("letter", (drive.toString()).substring(0, 1));
      driveObject.setLong("totalSpace", drive.getTotalSpace());
      driveObject.setLong("freeSpace", drive.getFreeSpace());
      driveObject.setLong("useableSpace", drive.getUsableSpace());
      driveObject.setBoolean("writable", drive.canWrite());
      driveObject.setBoolean("readable", drive.canRead());
      driveObject.setBoolean("executable", drive.canExecute());

      // TODO: Causes exception on Windows7
      // driveObject.setString("type",
      // FileSystemView.getFileSystemView().getSystemTypeDescription(drive));
    }

    // files and folders
    if (source.getAddress() != null) {
      filterPath = new File(source.getAddress());

      if (filterPath.isDirectory()) {
        fileList = new File(source.getAddress()).listFiles();

        if (fileList.length > 0) {
          for (File file : fileList) {
            if (file.isDirectory()) {
              folderObject = metaDataObject.createDataObject("folder");
              folderObject.setString("name", file.getName());
            } else {
              fileObject = metaDataObject.createDataObject("file");
              fileObject.setString("name", file.getName());
            }
          }
        }
      }
    }

    return metaDataObject;
  }

  /* (non-Javadoc)
   * @see org.simpl.core.services.datasource.DataSourceService#createTarget(org.simpl.core.services.datasource.DataSource, commonj.sdo.DataObject, java.lang.String)
   */
  @Override
  public boolean createTarget(DataSource dataSource, DataObject dataObject, String target)
      throws ConnectionException {
    // no need to create a target on local filesystem
    return true;
  }

  /**
   * Executes a shell command in the given working directory and returns the shell output.
   * 
   * @param command
   * @return
   */
  private String[] execute(String command, String dir, String[] envp) throws IOException {
    Process process = null;
    BufferedReader processReader;
    String line;
    File workingDir = new File(dir);
    List<String> output = new ArrayList<String>();

    process = runtime.exec("cmd /c " + command, envp, workingDir);
    processReader = new BufferedReader(new InputStreamReader(process.getInputStream(),
        "cp850"));

    while ((line = processReader.readLine()) != null) {
      output.add(line);
      // System.out.println(line);
    }

    try {
      process.waitFor();
    } catch (InterruptedException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    process.destroy();

    return Arrays.copyOf(output.toArray(), output.size(), String[].class);
  }
}
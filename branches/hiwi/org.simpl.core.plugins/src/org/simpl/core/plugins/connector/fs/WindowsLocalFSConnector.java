package org.simpl.core.plugins.connector.fs;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.simpl.core.exceptions.ConnectionException;
import org.simpl.core.plugins.connector.ConnectorPlugin;
import org.simpl.core.plugins.dataconverter.file.RandomFile;
import org.simpl.resource.management.data.DataSource;

import commonj.sdo.DataObject;

/**
 * <b>Purpose:</b>Provides access to a windows file system data source.<br>
 * <b>Description:</b>Statements (commands) are executed on the windows command shell and
 * existing commands are used to realize the data source service functions.<br>
 * <b>Copyright:</b>Licensed under the Apache License, Version 2.0.
 * http://www.apache.org/licenses/LICENSE-2.0<br>
 * <b>Company:</b>SIMPL<br>
 * 
 * @author schneimi<br>
 * @version $Id: WindowsLocalFSDataSource.java 1014 2010-03-29 09:16:08Z
 *          michael.schneidt@arcor.de $<br>
 * @link http://code.google.com/p/simpl09/
 */
public class WindowsLocalFSConnector extends ConnectorPlugin<File, RandomFile> {
  static Logger logger = Logger.getLogger(WindowsLocalFSConnector.class);

  /**
   * Runtime to execute commands on the file system.
   */
  Runtime runtime = Runtime.getRuntime();

  /**
   * Initialize the plug-in.
   */
  public WindowsLocalFSConnector() {
    this.setType("Filesystem");
    this.setMetaDataSchemaType("tFilesystemMetaData");
    this.addSubtype("Windows Local");
    this.addLanguage("Windows Local", "Shell");

    // Set up a simple configuration that logs on the console.
    PropertyConfigurator.configure("log4j.properties");
  }

  @Override
  public boolean issueCommand(DataSource dataSource, String statement)
      throws ConnectionException {

    if (WindowsLocalFSConnector.logger.isDebugEnabled()) {
      WindowsLocalFSConnector.logger.debug("boolean executeStatement("
          + dataSource.getAddress() + ", " + statement + ") executed.");
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

  @Override
  public RandomFile retrieveData(DataSource dataSource, String file)
      throws ConnectionException {
    RandomFile result = new RandomFile();
    String dir = "";

    if (WindowsLocalFSConnector.logger.isDebugEnabled()) {
      WindowsLocalFSConnector.logger.debug("DataObject retrieveData("
          + dataSource.getAddress() + ", " + file + ") executed.");
    }

    if (!dataSource.getAddress().equals("")) {
      dir = dataSource.getAddress() + File.separator;
    }

    result.setDataSource(dataSource);
    result.setFile(new File(dir + file));

    return result;
  }

  @Override
  public boolean writeDataBack(DataSource dataSource, File dataFile, String target)
      throws ConnectionException {
    boolean successful = false;
    File targetFile = null;
    String dir = "";

    if (WindowsLocalFSConnector.logger.isDebugEnabled()) {
      WindowsLocalFSConnector.logger.debug("boolean writeDataBack("
          + dataSource.getAddress() + ", " + dataFile.getName() + ", " + target
          + ") executed.");
    }

    if (!dataSource.getAddress().equals("")) {
      dir = dataSource.getAddress() + File.separator;
    }

    if (target != null && !target.equals("")) {
      targetFile = new File(dir + target);

      // file -> file
      if (dataFile.isFile() && targetFile.isFile()) {
        // check if target file exists
        if (!targetFile.exists()) {
          // move file to the target
          successful = dataFile.renameTo(targetFile);
        }
      }

      // file -> directory
      if (dataFile.isFile() && targetFile.isDirectory()) {

        // create target directory if it doesn't exist
        if (!targetFile.exists()) {
          targetFile.mkdir();
        }

        File checkFile = new File(targetFile.getAbsolutePath(), dataFile.getName());

        // check if file exists in target directory
        if (!checkFile.exists()) {
          // move file to the target
          successful = dataFile.renameTo(checkFile);
        }
      }

      // directory -> directory
      if (dataFile.isDirectory() && targetFile.isDirectory()) {
        boolean filesExistOnTarget = false;

        // check if directory files exist in target directory
        for (File file : dataFile.listFiles()) {
          if (!file.isDirectory()) {
            File checkFile = new File(targetFile.getAbsolutePath(), file.getName());

            if (!checkFile.isDirectory() && checkFile.exists()) {
              filesExistOnTarget = true;
            }
          }
        }

        if (!filesExistOnTarget) {
          successful = true;

          // create target directory if it doesn't exist
          if (!targetFile.exists()) {
            targetFile.mkdir();
          }

          for (File file : dataFile.listFiles()) {
            if (!file.isDirectory()) {
              File checkFile = new File(targetFile.getAbsolutePath(), file.getName());

              // move file to the target
              successful = successful && file.renameTo(checkFile);
            }
          }
        }
      }
    }

    return successful;
  }

  @Override
  public boolean queryData(DataSource dataSource, String file, String targetFile)
      throws ConnectionException {
    String[] envp = new String[] { "cmd", "/c", "start", "copy" };

    if (WindowsLocalFSConnector.logger.isDebugEnabled()) {
      WindowsLocalFSConnector.logger.debug("DataObject queryData("
          + dataSource.getAddress() + ", " + file + "," + targetFile + ") executed.");
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
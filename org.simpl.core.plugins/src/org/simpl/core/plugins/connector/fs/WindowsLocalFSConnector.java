package org.simpl.core.plugins.connector.fs;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.simpl.core.exceptions.ConnectionException;
import org.simpl.core.plugins.connector.ConnectorPlugin;
import org.simpl.core.plugins.dataconverter.file.RandomFiles;
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
public class WindowsLocalFSConnector extends ConnectorPlugin<File, RandomFiles> {
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
  public RandomFiles retrieveData(DataSource dataSource, String file)
      throws ConnectionException {
    RandomFiles result = new RandomFiles();
    String dir = "";

    if (WindowsLocalFSConnector.logger.isDebugEnabled()) {
      WindowsLocalFSConnector.logger.debug("DataObject retrieveData("
          + dataSource.getAddress() + ", " + file + ") executed.");
    }

    if (!dataSource.getAddress().equals("")) {
      dir = dataSource.getAddress() + File.separator;
    }

    File f = new File(dir + file);
    File[] files = null;

    if (f.isDirectory()) {
      files = f.listFiles(new FileFilter() {
        @Override
        public boolean accept(File f) {
          return f.isFile();
        }
      });
    } else {
      files = new File[1];
      files[0] = f;
    }

    result.setDataSource(dataSource);
    result.setFiles(files);

    return result;
  }

  @Override
  public boolean writeDataBack(DataSource dataSource, File source, String target)
      throws ConnectionException {
    boolean successful = false;
    File targetDir = null;
    String dir = "";

    if (WindowsLocalFSConnector.logger.isDebugEnabled()) {
      WindowsLocalFSConnector.logger.debug("boolean writeDataBack("
          + dataSource.getAddress() + ", " + source.getName() + ", " + target
          + ") executed.");
    }

    if (!dataSource.getAddress().equals("")) {
      dir = dataSource.getAddress() + File.separator;
      
      // Workaround: sometimes a line break manages into the address!? (at least when
      // using the web service with soapUI)
      dir = dir.replace("\n", "");
    }

    if (target != null && !target.equals("")) {
      targetDir = new File(dir + target);
      
      // source is always a directory (see RandomFilesDataConverter)
      // target must be a directory

      WindowsLocalFSConnector.logger.debug("Source: " + source);
      WindowsLocalFSConnector.logger.debug("Target: " + targetDir);

      // create target directory
      if (!targetDir.exists()) {
        boolean createdTarget = targetDir.mkdir();
        WindowsLocalFSConnector.logger.debug("Created target: " + createdTarget);
      }
      
      boolean filesExistOnTarget = false;

      // check if files already exist in target directory
      for (File f : source.listFiles()) {
        if (!f.isDirectory()) {
          File targetFile = new File(targetDir, f.getName());

          WindowsLocalFSConnector.logger.debug("Check if file exists on target: "
              + targetFile);

          if (!targetFile.isDirectory() && targetFile.exists()) {
            filesExistOnTarget = true;
          }
        }
      }

      WindowsLocalFSConnector.logger.debug("One or more target files already exist: "
          + filesExistOnTarget);

      if (!filesExistOnTarget) {
        successful = true;

        for (File sourceFile : source.listFiles()) {
          if (sourceFile.isFile()) {
            File targetFile = new File(targetDir, sourceFile.getName());

            WindowsLocalFSConnector.logger
                .debug("Write file: " + sourceFile + " -> " + targetFile);

            // move file to the target
            successful = successful && sourceFile.renameTo(targetFile);

            WindowsLocalFSConnector.logger.debug("Write successful: " + successful);
          }
        }
      }
    } else {
      WindowsLocalFSConnector.logger.debug("Abort because no target is defined.");
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
    // no need to create a target
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
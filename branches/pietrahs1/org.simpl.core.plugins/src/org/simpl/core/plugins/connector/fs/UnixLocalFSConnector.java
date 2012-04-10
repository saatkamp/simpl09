package org.simpl.core.plugins.connector.fs;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.filechooser.FileSystemView;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.simpl.core.exceptions.ConnectionException;
import org.simpl.core.plugins.connector.ConnectorPlugin;
import org.simpl.core.plugins.dataconverter.file.RandomFiles;
import org.simpl.resource.management.data.DataSource;

import commonj.sdo.DataObject;

/**
 * <b>Purpose:</b>Provides access to a Unix file system data source.<br>
 * <b>Description:</b>Statements (commands) are executed on the command shell
 * and existing commands are used to realize the data source service functions.
 * NOTE: tested on Ubuntu 10.04 and 11.10<br>
 * <b>Copyright:</b>Licensed under the Apache License, Version 2.0.
 * http://www.apache.org/licenses/LICENSE-2.0<br>
 * <b>Company:</b>SIMPL<br>
 * 
 * @author Henrik Pietranek <henrik.pietranek@googlemail.com><br>
 * @link http://code.google.com/p/simpl09/
 */
public class UnixLocalFSConnector extends ConnectorPlugin<File, RandomFiles> {
  static Logger logger = Logger.getLogger(UnixLocalFSConnector.class);

  /**
   * Runtime to execute commands on the file system.
   */
  Runtime runtime = Runtime.getRuntime();

  /**
   * Initialize the plug-in.
   */
  public UnixLocalFSConnector() {
    this.setType("Filesystem");
    this.setMetaDataSchemaType("tFilesystemMetaData");
    this.addSubtype("Unix Local");
    this.addLanguage("Unix Local", "Shell");

    // Set up a simple configuration that logs on the console.
    PropertyConfigurator.configure("log4j.properties");
  }

  @Override
  public boolean issueCommand(DataSource dataSource, String statement)
      throws ConnectionException {

    if (UnixLocalFSConnector.logger.isDebugEnabled()) {
      UnixLocalFSConnector.logger.debug("boolean executeStatement("
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
    String fullPath = "";

    if (UnixLocalFSConnector.logger.isDebugEnabled()) {
      UnixLocalFSConnector.logger.debug("DataObject retrieveData("
          + dataSource.getAddress() + ", " + file + ") executed.");
    }

    if (!dataSource.getAddress().equals("")) {
      dir = dataSource.getAddress() + File.separator;
    } else {
      dir = File.separator;
    }
    // if the user input in incorrect -> replace
    fullPath = (dir + file).replace("//", "/");

    File f = new File(fullPath);
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
    String fullPath = "";

    if (UnixLocalFSConnector.logger.isDebugEnabled()) {
      UnixLocalFSConnector.logger.debug("boolean writeDataBack("
          + dataSource.getAddress() + ", " + source.getName() + ", " + target
          + ") executed.");
    }

    if (!dataSource.getAddress().equals("")) {
      dir = dataSource.getAddress() + File.separator;
      // Workaround: sometimes a line break manages into the address!? (at least
      // when using the web service with soapUI)
      dir = dir.replace("\n", "");
    } else {
      dir = File.separator;
    }
    // if the user input in incorrect -> replace
    fullPath = (dir + target).replace("//", "/");

    if (target != null && !target.equals("")) {
      targetDir = new File(fullPath);

      // source is always a directory (see RandomFilesDataConverter)
      // target must be a directory
      UnixLocalFSConnector.logger.debug("Source: " + source);
      UnixLocalFSConnector.logger.debug("Target: " + targetDir);

      // create target directory
      if (!targetDir.exists()) {
        boolean createdTarget = targetDir.mkdir();
        UnixLocalFSConnector.logger.debug("Created target: " + createdTarget);
      }

      boolean filesExistOnTarget = false;

      // check if files already exist in target directory
      for (File f : source.listFiles()) {
        if (!f.isDirectory()) {
          File targetFile = new File(targetDir, f.getName());

          UnixLocalFSConnector.logger.debug("Check if file exists on target: "
              + targetFile);

          if (!targetFile.isDirectory() && targetFile.exists()) {
            filesExistOnTarget = true;
          }
        }
      }

      UnixLocalFSConnector.logger
          .debug("One or more target files already exist: "
              + filesExistOnTarget);

      if (!filesExistOnTarget) {
        successful = true;

        for (File sourceFile : source.listFiles()) {
          if (sourceFile.isFile()) {
            File targetFile = new File(targetDir, sourceFile.getName());

            UnixLocalFSConnector.logger.debug("Write file: " + sourceFile
                + " -> " + targetFile);

            // move file to the target
            successful = successful && sourceFile.renameTo(targetFile);

            UnixLocalFSConnector.logger
                .debug("Write successful: " + successful);
          }
        }
      }
    } else {
      UnixLocalFSConnector.logger.debug("Abort because no target is defined.");
    }

    return successful;
  }

  @Override
  public boolean queryData(DataSource dataSource, String file, String targetFile)
      throws ConnectionException {
    String dir = "";
    String fullPathSource = "";
    String fullPathTarget = "";

    if (UnixLocalFSConnector.logger.isDebugEnabled()) {
      UnixLocalFSConnector.logger.debug("DataObject queryData("
          + dataSource.getAddress() + ", " + file + "," + targetFile
          + ") executed.");
    }

    if (!dataSource.getAddress().equals("")) {
      dir = dataSource.getAddress() + File.separator;
      // Workaround: sometimes a line break manages into the address!? (at least
      // when using the web service with soapUI)
      dir = dir.replace("\n", "");
    } else {
      dir = File.separator;
    }

    // if the user input in incorrect -> replace
    fullPathSource = (dir + file).replace("//", "/");
    fullPathTarget = (dir + targetFile).replace("//", "/");

    File source = new File(fullPathSource);
    File target = new File(fullPathTarget);

    try {
      if (source.isDirectory()) {
        FileUtils.copyDirectory(source, target);
      } else {
        FileUtils.copyFile(source, target);
      }
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();

      return false;
    }

    return true;
  }

  @Override
  public boolean transferData(DataSource dataSource, DataSource dataSink,
      String statement, String target) throws ConnectionException {
    // currently not used
    // instead retrieveData and writeDataBack are used
    return false;
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
    String commands = null;
    String commandName = null;

    // extract the command names
    // e.g. 'cd [-L|-P] [dir]'
    String regex = "\\w+\\s(\\[)";
    Pattern pattern = null;
    Matcher matcher = null;

    try {
      commands = this.execute("help");
      pattern = Pattern.compile(regex);
      matcher = pattern.matcher(commands);
      while (matcher.find()) {
        commandName = commands.substring(matcher.start(), matcher.end() - 2);
        if (!Character.isUpperCase(commandName.charAt(0))) {
          commandObject = metaDataObject.createDataObject("command");
          commandObject.set("name", commandName);
          commandObject.set("description", this.execute("help " + commandName));
        }
      }
    } catch (IOException e) {
      // no command meta data could be retrieved
    }

    // drives
    for (File drive : driveList) {
      driveObject = metaDataObject.createDataObject("drive");

      driveObject.setString("name", FileSystemView.getFileSystemView()
          .getSystemDisplayName(drive));
      driveObject.setString("letter", (drive.toString()).substring(0, 1));
      driveObject.setLong("totalSpace", drive.getTotalSpace());
      driveObject.setLong("freeSpace", drive.getFreeSpace());
      driveObject.setLong("useableSpace", drive.getUsableSpace());
      driveObject.setBoolean("writable", drive.canWrite());
      driveObject.setBoolean("readable", drive.canRead());
      driveObject.setBoolean("executable", drive.canExecute());
      driveObject.setString("type", FileSystemView.getFileSystemView()
          .getSystemTypeDescription(drive));
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

  /**
   * Executes a shell command in the given working directory and returns the
   * shell output.
   * 
   * @param command
   * @return
   */
  private String[] execute(String command, String dir, String[] envp)
      throws IOException {
    Process process = null;
    BufferedReader processReader;
    String line;
    File workingDir = new File(dir);
    List<String> output = new ArrayList<String>();

    process = runtime.exec(command, envp, workingDir);
    processReader = new BufferedReader(new InputStreamReader(
        process.getInputStream(), "UTF8"));

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

  /**
   * Executes a given command with bash and returns the bash output.
   * 
   * @param command
   * @return
   * @throws IOException
   */
  private String execute(String command) throws IOException {
    Process process = null;
    File directory = new File("/bin");

    process = runtime.exec("/bin/bash", null, directory);

    if (process != null) {
      BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(
          process.getInputStream()));
      PrintWriter printWriter = new PrintWriter(new BufferedWriter(
          new OutputStreamWriter(process.getOutputStream())), true);
      printWriter.println(command);
      // now execute the specified command
      printWriter.println("exit");

      StringBuffer stringBuffer = new StringBuffer();
      String line;
      while ((line = bufferedReader.readLine()) != null) {
        stringBuffer.append(line);
      }
      try {
        process.waitFor();
      } catch (InterruptedException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
      bufferedReader.close();
      printWriter.close();
      process.destroy();

      return stringBuffer.toString();
    }
    return "";
  }

}

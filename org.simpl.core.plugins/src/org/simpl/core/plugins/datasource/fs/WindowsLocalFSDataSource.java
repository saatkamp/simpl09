package org.simpl.core.plugins.datasource.fs;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.filechooser.FileSystemView;

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
    this.setType("Filesystem");
    this.setMetaDataType("tFilesystemMetaData");
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
  public boolean executeStatement(String dir, String statement)
      throws ConnectionException {
    try {
      this.execute(statement, dir, null);
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
  public boolean depositData(String dir, String file, String targetFile)
      throws ConnectionException {
    String[] envp = new String[] { "cmd", "/c", "start", "copy"};

    try {
      this.execute("copy " + file + " " + targetFile, dir, envp);
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
  public DataObject getMetaData(String dir, String filter)
      throws ConnectionException {
    DataObject metaDataObject = this.createMetaDataObject().getRootObject();
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

      // append line breaked description to preceding command description
      for (int i = 3; i < commands.length; i++) {
        commandName = commands[i].substring(0, commands[i].indexOf(" "));
        commandDescription = commands[i].substring(commands[i].indexOf(" ")).trim();

        if (commandName.equals("")) {
          commands[i - 1] += " " + commandDescription;
          commands[i] = null;
        }
      }

      // add commands to meta data object
      for (int i = 3; i < commands.length; i++) {
        if (commands[i] != null) {
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

      filterPath = new File(dir);

      // files and folders
      if (dir != null && !dir.equals("") && filterPath.isDirectory()) {
        fileList = new File(dir).listFiles();

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

  /*
   * (non-Javadoc)
   * @see org.simpl.core.datasource.DatasourceService#writeBack(java.lang.String,
   * commonj.sdo.DataObject)
   */
  @Override
  public boolean writeBack(String dir, DataObject data) throws ConnectionException {
    boolean writeBack = false;
    DataFormatService<Object> dataFormatService = SIMPLCore.getInstance()
        .dataFormatService("CSV", "Headline");
    File tempFile = (File) dataFormatService.fromSDO(data);

    // move temp file to given dir
    writeBack = tempFile
        .renameTo(new File(dir + File.separator + data.getString("name")));

    return writeBack;
  }

  /*
   * (non-Javadoc)
   * @see org.simpl.core.datasource.DatasourceService#retrieveData(java.lang.String,
   * java.lang.String)
   */
  @Override
  public DataObject retrieveData(String dir, String file)
      throws ConnectionException {
    DataFormatService<Object> dataFormatService = SIMPLCore.getInstance()
        .dataFormatService("CSV", "Headline");
    DataObject dataObject = dataFormatService.toSDO(new File(dir + File.separator + file));

    return dataObject;
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
      //System.out.println(line);
    }
    
    process.destroy();

    return Arrays.copyOf(output.toArray(), output.size(), String[].class);
  }
}
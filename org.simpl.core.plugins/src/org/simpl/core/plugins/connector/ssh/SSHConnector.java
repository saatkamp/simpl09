package org.simpl.core.plugins.connector.ssh;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.simpl.core.exceptions.ConnectionException;
import org.simpl.core.plugins.connector.ConnectorPlugin;
import org.simpl.core.plugins.dataconverter.file.RandomFiles;
import org.simpl.resource.management.data.DataSource;

import com.sshtools.j2ssh.ScpClient;
import com.sshtools.j2ssh.SshClient;
import com.sshtools.j2ssh.authentication.AuthenticationProtocolState;
import com.sshtools.j2ssh.authentication.PasswordAuthenticationClient;
import com.sshtools.j2ssh.session.SessionChannelClient;
import com.sshtools.j2ssh.transport.IgnoreHostKeyVerification;
import commonj.sdo.DataObject;

/**
 * <b>Purpose:</b>Provides access to a SSH server.<br>
 * <b>Description:</b>Uses the j2ssh library.<br>
 * <b>Copyright:</b>Licensed under the Apache License, Version 2.0.
 * http://www.apache.org/licenses/LICENSE-2.0<br>
 * <b>Company:</b>SIMPL<br>
 * 
 * @author schneimi<br>
 * @version $Id$<br>
 * @link http://code.google.com/p/simpl09/
 */
public class SSHConnector extends ConnectorPlugin<File, RandomFiles> {
  static Logger logger = Logger.getLogger(SSHConnector.class);
  
  /**
   * Sleep time between SSH operations that need time to be executed.
   */
  static int WORK_AROUND_SLEEP_TIME = 500;
  
  /**
   * SSH client to connect to a SSH server.
   */
  SshClient ssh = new SshClient();

  /**
   * SCP client to secure copy files from and to a SSH server.
   */
  ScpClient scp = new ScpClient(ssh, false, null);

  /**
   * Initialize the plug-in.
   */
  public SSHConnector() {
    this.setType("Filesystem");
    this.setMetaDataSchemaType("tFilesystemMetaData");
    this.addSubtype("SSH Server");
    this.addLanguage("SSH Server", "Shell");

    // Set up a simple configuration that logs on the console.
    PropertyConfigurator.configure("log4j.properties");
  }

  @Override
  public boolean issueCommand(DataSource dataSource, String statement)
      throws ConnectionException {

    if (SSHConnector.logger.isDebugEnabled()) {
      SSHConnector.logger.debug("boolean executeStatement(" + dataSource.getAddress()
          + ", " + statement + ") executed.");
    }

    try {
      this.execute(statement, null, dataSource);
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    // execution is successful if no error occurs
    return true;
  }

  @Override
  public RandomFiles retrieveData(DataSource dataSource, String file)
      throws ConnectionException {
    RandomFiles result = new RandomFiles();
    File tmpDir = null;
    File[] files = null;
    
    if (SSHConnector.logger.isDebugEnabled()) {
      SSHConnector.logger.debug("DataObject retrieveData(" + dataSource.getAddress()
          + ", " + file + ") executed.");
    }
    
    try {
      if (this.authenticate(dataSource)) {
        // create temporary directory
        tmpDir = File.createTempFile("simpl", "");
        tmpDir.delete();
        tmpDir.mkdir();
        tmpDir.deleteOnExit();
        
        SSHConnector.logger.debug("SCP: " + file + " -> " + tmpDir);
        scp.get(tmpDir.getAbsolutePath(), file, true);        
      } else {
        SSHConnector.logger.debug("SSH authentication failed.");
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
    
    // if a directory has been retrieved, take the files of this directory
    if (tmpDir.listFiles().length == 1 && tmpDir.listFiles()[0].isDirectory()) {
      files = tmpDir.listFiles()[0].listFiles(new FileFilter() {
        @Override
        public boolean accept(File f) {
          return f.isFile();
        }
      });
    } else {
      files = tmpDir.listFiles(new FileFilter() {
        @Override
        public boolean accept(File f) {
          return f.isFile();
        }
      });
    }
    
    result.setDataSource(dataSource);
    result.setFiles(files);

    return result;
  }

  @Override
  public boolean writeDataBack(DataSource dataSource, File source, String target)
      throws ConnectionException {
    boolean successful = false;

    if (SSHConnector.logger.isDebugEnabled()) {
      SSHConnector.logger.debug("boolean writeDataBack("
          + dataSource.getAddress() + ", " + source + ", " + target
          + ") executed.");
    }

    // source is always a directory (see RandomFilesDataConverter)
    // target must be an existing directory on the SSH server
    
    if (target == null || target.equals("")) {
      SSHConnector.logger.debug("Abort because no target is defined.");
      return false;
    }  
      
    if (this.authenticate(dataSource)) {
      try {
        for (File file : source.listFiles()) {
          if (file.isFile()) {
            SSHConnector.logger.debug("SCP: " + file + " -> " + target);
            scp.put(file.getAbsolutePath(), target, true);   
          }
        }
        
        successful = true;
      } catch (IOException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }      
    } else {
      SSHConnector.logger.debug("SSH authentication failed.");
    }

    return successful;
  }

  @Override
  public boolean queryData(DataSource dataSource, String file, String targetFile)
      throws ConnectionException {
    if (SSHConnector.logger.isDebugEnabled()) {
      SSHConnector.logger.debug("DataObject queryData(" + dataSource.getAddress() + ", "
          + file + "," + targetFile + ") executed.");
    }

    try {
      this.execute("cp -R " + file + " " + targetFile, null, dataSource);
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    // query is successful if no error occurs
    return true;
  }

  @Override
  public DataObject getMetaData(DataSource dataSource, String filter)
      throws ConnectionException {
    DataObject metaDataObject = this.getMetaDataSDO();
    DataObject commandObject = null;
    DataObject folderObject = null;
    DataObject fileObject = null;

    String[] fileList = null;

    // commands
    String[] commands = null;

    try {
      commands = this.execute("ls", "/bin", dataSource);

      // add commands to meta data object
      for (int i = 0; i < commands.length; i++) {
        if (commands[i] != null) {
          commandObject = metaDataObject.createDataObject("command");
          commandObject.set("name", commands[i]);
        }
      }

      // files and folders
      if (dataSource.getAddress() != null) {
        fileList = this.execute("ls", null, dataSource);

        if (fileList.length > 0) {
          for (String file : fileList) {
            if (file.indexOf(".") <= 0) {
              folderObject = metaDataObject.createDataObject("folder");
              folderObject.setString("name", file);
            } else {
              fileObject = metaDataObject.createDataObject("file");
              fileObject.setString("name", file);
            }
          }
        }
      }
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
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
   * Executes a shell command on the SSH server.
   * 
   * @param command
   * @param dir
   * @param auth
   * @return
   */
  private String[] execute(String command, String dir, DataSource dataSource)
      throws IOException {
    SessionChannelClient session = null;
    OutputStream out = null;
    BufferedReader in = null;
    String line;
    List<String> output = new ArrayList<String>();

    if (this.authenticate(dataSource)) {
      SSHConnector.logger.debug("The authentication is complete");
      session = ssh.openSessionChannel();

      // execute command
      if (session.startShell()) {
        out = session.getOutputStream();
        if (dir != null && !dir.equals("")) {
          // change directory
          SSHConnector.logger.debug("SSH Console: " + "cd " + dir);
          out.write(("cd " + dir + "\n").getBytes());
        }
        SSHConnector.logger.debug("SSH Console: " + command);
        out.write((command + "\n").getBytes());

        // Workaround: the command needs some time to execute
        try {
          Thread.sleep(WORK_AROUND_SLEEP_TIME);
        } catch (InterruptedException e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
        }
        
        // read result
        in = new BufferedReader(new InputStreamReader(session.getInputStream()));
        while (in.ready() && (line = in.readLine()) != null) {
          if (!line.equals("Warning: no access to tty (Bad file descriptor).")
              && !line.equals("Thus no job control in this shell.")) {
            output.add(line);
          }
        }

        in.close();
        out.close();
        SSHConnector.logger.debug("Close session");
        session.close();
      }
    }

    return Arrays.copyOf(output.toArray(), output.size(), String[].class);
  }

  /**
   * Authenticates the user on the SSH server if the user is not already authenticated.
   * 
   * @param dataSource
   * @return
   */
  private boolean authenticate(DataSource dataSource) {
    boolean authed = ssh.isAuthenticated();
    PasswordAuthenticationClient pwd = new PasswordAuthenticationClient();
    int authResult = 0;

    // authenticate user
    pwd.setUsername(dataSource.getAuthentication().getUser());
    pwd.setPassword(dataSource.getAuthentication().getPassword());

    try {
      if (!authed) {
        ssh.connect(dataSource.getAddress(), new IgnoreHostKeyVerification());
        authResult = ssh.authenticate(pwd);

        if (authResult == AuthenticationProtocolState.FAILED) {
          SSHConnector.logger.debug("The authentication failed");
        } else if (authResult == AuthenticationProtocolState.PARTIAL) {
          SSHConnector.logger.debug("The authentication succeeded but another"
              + "authentication is required");
        } else if (authResult == AuthenticationProtocolState.COMPLETE) {
          SSHConnector.logger.debug("The authentication is complete");
          authed = true;
        }

        // Workaround: the authentication requires some time
        Thread.sleep(WORK_AROUND_SLEEP_TIME);
      } else {
        authed = true;
      }
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (InterruptedException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    return authed;
  }
}
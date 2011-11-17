package org.eclipse.bpel.simpl.ui.properties.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.bpel.model.Process;
import org.eclipse.bpel.ui.IBPELUIConstants;
import org.eclipse.bpel.ui.util.BPELUtil;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.simpl.communication.ResourceManagementCommunication;
import org.simpl.resource.management.data.DataSource;

/**
 * <b>Purpose:</b> <br>
 * <b>Description:</b> <br>
 * <b>Copyright:</b> Licensed under the Apache License, Version 2.0.
 * http://www.apache.org/licenses/LICENSE-2.0<br>
 * <b>Company:</b> SIMPL<br>
 * 
 * @author Michael Hahn <hahnml@studi.informatik.uni-stuttgart.de> <br>
 * @version $Id$ <br>
 * @link http://code.google.com/p/simpl09/
 * 
 */
public class PropertySectionUtils {
  /**
   * Die BPEL Datei des Prozesses
   */
  private static IFile bpelFile = null;

  /**
   * Der Pfad zur BPEL Datei des Prozesses
   */
  private static IPath bpelPath = null;

  /**
   * Pfad zum Projektordner = Pfad zur BPEL Datei ohne Dateiendung
   */
  private static IPath projectPath = null;

  /**
   * Absoluter Workspace-Pfad. Pfad in Format "OSString" umwandeln, so dass man mit
   * java.io arbeiten kann.
   */
  private static String absolutWorkspacePath = "";

  private static final String RM_PREFIX = "rm";

  public static String[] getAllDataSourceIdentifiers(Process process) {
    List<String> datasources = new ArrayList<String>();

    // TODO: Uncommented because rm: identifiers are not supported anymore
    //datasources.addAll(getRMDatasourceNames());
    datasources.addAll(VariableUtils.getUseableVariables(process, VariableUtils.DESCRIPTOR_VAR));

    return datasources.toArray(new String[0]);
  }

  @SuppressWarnings("unused")
  private static List<String> getRMDatasourceNames() {
    List<String> dataSourceNames = new ArrayList<String>();

    List<DataSource> dataSources = ResourceManagementCommunication.getInstance().getDataSources();

    if (dataSources != null) {
      for (DataSource dat : dataSources) {
        dataSourceNames.add(RM_PREFIX + ":" + dat.getName());
      }
    }

    return dataSourceNames;
  }
  
  public static DataSource findDataSourceByIdentifier(Process process, String nameWithPrefix) {
    DataSource dataSource = null;

    String dataSourceName = VariableUtils.getDataSourceReferenceElementValue(process, nameWithPrefix, "name");
    
    if (ResourceManagementCommunication.getInstance().isAvailable() && dataSourceName != null) {
      dataSource = ResourceManagementCommunication.getInstance().findDataSourceByName(dataSourceName);

      if (dataSource != null && dataSource.getName() == null) {
        dataSource = null;
      }
    }

    return dataSource;
  }

  public static void downloadSchema(DataSource dataSource, Process process) {
    if (ResourceManagementCommunication.getInstance().isAvailable() && dataSource != null) {
      bpelFile = BPELUtil.getBPELFile(process);

      bpelPath = bpelFile.getFullPath();

      projectPath = bpelPath.removeFileExtension().removeLastSegments(1);

      absolutWorkspacePath = ResourcesPlugin.getWorkspace().getRoot().getLocation()
          .toOSString();

      if (dataSource.getConnector().getDataConverter().getWorkflowDataFormat() != null) {

        IPath xsdPath = projectPath.append(
            dataSource.getConnector().getDataConverter().getWorkflowDataFormat())
            .addFileExtension(IBPELUIConstants.EXTENSION_XSD);

        File xsdFileSIMPL = new File(absolutWorkspacePath + xsdPath.toOSString());

        if (!xsdFileSIMPL.exists()) {
          try {
            String stream = null;
            try {
              stream = ResourceManagementCommunication.getInstance().getService().getDataFormatSchema(
                  dataSource.getConnector().getDataConverter().getWorkflowDataFormat());
            } catch (Exception e) {
            }
            if (stream != null && xsdFileSIMPL.createNewFile()) {

              OutputStreamWriter out = new OutputStreamWriter(new FileOutputStream(
                  xsdFileSIMPL.getAbsolutePath()), "UTF-8");

              out.write(stream.toCharArray());
              out.close();

            }
          } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
          } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
          } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
          }

        }
      }
    }
  }
}

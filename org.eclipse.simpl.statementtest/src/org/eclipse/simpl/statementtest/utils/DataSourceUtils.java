package org.eclipse.simpl.statementtest.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.simpl.communication.client.Authentication;
import org.eclipse.simpl.communication.client.DataSource;
import org.eclipse.simpl.uddi.model.ModelProvider;
import org.eclipse.ui.PlatformUI;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.Namespace;
import org.jdom.input.SAXBuilder;

/**
 * <b>Purpose:</b>Util functions for data source retrieval.<br>
 * <b>Description:</b> <br>
 * <b>Copyright:</b>Licensed under the Apache License, Version 2.0.
 * http://www.apache.org/licenses/LICENSE-2.0<br>
 * <b>Company:</b><br>
 * 
 * @author Michael Hahn <hahnml@studi.informatik.uni-stuttgart.de> <br>
 * @version $Id: DataSourceUtils.java 51 2010-06-29 18:21:35Z hiwi $ <br>
 * @link http://code.google.com/p/simpl09/
 * 
 */
public class DataSourceUtils {

  private static final String EL_PROCESS = "process";
  private static final String EL_DATASOURCE = "datasources";

  private static final String AT_NAME = "name";
  private static final String AT_DATA_SOURCE_NAME = "dataSourceName";
  private static final String AT_DATA_SOURCE_ADDRESS = "address";
  private static final String AT_DATA_SOURCE_TYPE = "type";
  private static final String AT_DATA_SOURCE_SUBTYPE = "subtype";
  private static final String AT_DATA_SOURCE_LANG = "language";
  private static final String AT_DATA_SOURCE_FORMAT = "format";
  private static final String AT_DATA_SOURCE_USERNAME = "userName";
  private static final String AT_DATA_SOURCE_PASSWORD = "password";

  private final static Namespace DD_NAMESPACE = Namespace
      .getNamespace("http://www.apache.org/ode/schemas/dd/2007/03");

  private static final String UDDI_PREFIX = "uddi";

  private static final String DD_PREFIX = "dd";

  @SuppressWarnings("unchecked")
  private static DataSource findDeploymentDescriptorDatasourceByName(String processName,
      String dsName) {
    DataSource datasource = null;
    IFile file = (IFile) PlatformUI.getWorkbench().getActiveWorkbenchWindow()
        .getActivePage().getActivePart().getSite().getPage().getActiveEditor()
        .getEditorInput().getAdapter(IFile.class);
    String processFile = file.getLocation().toOSString();

    File deploy = new File(processFile.substring(0, processFile.lastIndexOf("\\"))
        + File.separator + "deploy.xml");

    if (deploy.exists()) {
      SAXBuilder builder = new SAXBuilder();
      Document doc;
      try {
        InputStream inputStream = new FileInputStream(deploy);

        doc = builder.build(inputStream);

        Element root = doc.getRootElement();

        List processes = root.getChildren(DataSourceUtils.EL_PROCESS,
            DataSourceUtils.DD_NAMESPACE);

        for (Object processObj : processes) {
          Element processElement = (Element) processObj;

          if (processElement.getAttributeValue(DataSourceUtils.AT_NAME).contains(
              processName)) {
            List datasourceElements = processElement.getChildren(
                DataSourceUtils.EL_DATASOURCE, DataSourceUtils.DD_NAMESPACE);

            for (Object data : datasourceElements) {
              String name = ((Element) data)
                  .getAttributeValue(DataSourceUtils.AT_DATA_SOURCE_NAME);

              if (name.equals(dsName)) {
                datasource = new DataSource();
                datasource.setName(name);
                datasource.setAddress(((Element) data)
                    .getAttributeValue(DataSourceUtils.AT_DATA_SOURCE_ADDRESS));
                datasource.setType(((Element) data)
                    .getAttributeValue(DataSourceUtils.AT_DATA_SOURCE_TYPE));
                datasource.setSubType(((Element) data)
                    .getAttributeValue(DataSourceUtils.AT_DATA_SOURCE_SUBTYPE));
                datasource.setLanguage(((Element) data)
                    .getAttributeValue(DataSourceUtils.AT_DATA_SOURCE_LANG));
                datasource.setDataFormat(((Element) data)
                    .getAttributeValue(DataSourceUtils.AT_DATA_SOURCE_FORMAT));
                Authentication authent = new Authentication();
                authent.setUser(((Element) data)
                    .getAttributeValue(DataSourceUtils.AT_DATA_SOURCE_USERNAME));
                authent.setPassword(((Element) data)
                    .getAttributeValue(DataSourceUtils.AT_DATA_SOURCE_PASSWORD));
                datasource.setAuthentication(authent);
              }
            }
          }
        }
      } catch (JDOMException e) {
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

    return datasource;
  }

  @SuppressWarnings("unchecked")
  private static List<String> getDeploymentDescriptorDatasourceNames(String processName) {
    List<String> datasources = new ArrayList<String>();

    IFile file = (IFile) PlatformUI.getWorkbench().getActiveWorkbenchWindow()
        .getActivePage().getActivePart().getSite().getPage().getActiveEditor()
        .getEditorInput().getAdapter(IFile.class);
    String processFile = file.getLocation().toOSString();

    File deploy = new File(processFile.substring(0, processFile.lastIndexOf("\\"))
        + File.separator + "deploy.xml");

    if (deploy.exists()) {
      SAXBuilder builder = new SAXBuilder();
      Document doc;

      try {
        InputStream inputStream = new FileInputStream(deploy);

        doc = builder.build(inputStream);

        Element root = doc.getRootElement();

        List processes = root.getChildren(DataSourceUtils.EL_PROCESS,
            DataSourceUtils.DD_NAMESPACE);

        for (Object processObj : processes) {
          Element processElement = (Element) processObj;

          if (processElement.getAttributeValue(DataSourceUtils.AT_NAME).contains(
              processName)) {
            List datasourceElements = processElement.getChildren(
                DataSourceUtils.EL_DATASOURCE, DataSourceUtils.DD_NAMESPACE);
            for (Object data : datasourceElements) {
              String name = ((Element) data)
                  .getAttributeValue(DataSourceUtils.AT_DATA_SOURCE_NAME);
              datasources.add(DataSourceUtils.DD_PREFIX + ":" + name);
            }
          }
        }
      } catch (JDOMException e) {
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

    return datasources;
  }

  private static List<String> getUDDIDatasourceNames() {
    List<String> dataSourceNames = new ArrayList<String>();

    List<DataSource> dataSources = ModelProvider.getInstance().getDataSources();

    for (DataSource dat : dataSources) {
      dataSourceNames.add(DataSourceUtils.UDDI_PREFIX + ":" + dat.getName());
    }

    return dataSourceNames;
  }

  public static String[] getAllDataSourceNames(String processName) {
    List<String> datasources = new ArrayList<String>();

    datasources.addAll(DataSourceUtils
        .getDeploymentDescriptorDatasourceNames(processName));
    datasources.addAll(DataSourceUtils.getUDDIDatasourceNames());

    return datasources.toArray(new String[0]);
  }

  public static DataSource findDataSourceByName(String processName, String nameWithPrefix) {
    DataSource data = null;

    String[] name = nameWithPrefix.split(":");

    if (name[0].equals(DataSourceUtils.DD_PREFIX)) {
      data = DataSourceUtils.findDeploymentDescriptorDatasourceByName(processName,
          name[1]);
    } else {
      if (name[0].equals(DataSourceUtils.UDDI_PREFIX)) {
        data = ModelProvider.getInstance().findDataSourceByName(name[1]);
      }
    }
    return data;
  }
}
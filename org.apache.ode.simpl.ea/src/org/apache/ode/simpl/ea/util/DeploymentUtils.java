package org.apache.ode.simpl.ea.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.ode.simpl.ea.DataManagementActivity;
import org.apache.ode.simpl.events.listener.AuditingParameters;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.Namespace;
import org.jdom.input.SAXBuilder;
import org.simpl.core.SIMPLResourceManagement;
import org.simpl.resource.management.client.Authentication;
import org.simpl.resource.management.client.DataSource;
import org.simpl.resource.management.client.LateBinding;
import org.simpl.resource.management.client.Strategy;

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
public class DeploymentUtils {

  static Logger logger = Logger.getLogger(DeploymentUtils.class);

  private static DeploymentUtils instance = null;

  private static final String EL_PROCESS = "process";

  private static final String AT_NAME = "name";

  private static final String EL_AUDITING_MODE = "auditing";

  private static final String EL_DATASOURCE = "datasources";
  private static final String EL_MAPPING = "activityMappings";
  private static final String EL_POLICY = "policy";

  private static final String AT_DATA_SOURCE_NAME = "dataSourceName";
  private static final String AT_DATA_SOURCE_ADDRESS = "address";
  private static final String AT_DATA_SOURCE_TYPE = "type";
  private static final String AT_DATA_SOURCE_SUBTYPE = "subtype";
  private static final String AT_DATA_SOURCE_USER = "userName";
  private static final String AT_DATA_SOURCE_PASSW = "password";
  private static final String AT_DATA_SOURCE_FORMAT = "format";

  private static final String AT_MAPPING_ACTIVITY = "activity";
  private static final String AT_MAPPING_POLICY_DATA = "policyData";
  private static final String AT_MAPPING_STRATEGY = "strategy";

  private final Namespace DD_NAMESPACE = Namespace
      .getNamespace("http://www.apache.org/ode/schemas/dd/2007/03");

  /**
   * This list holds all data source information of the deployment descriptor.
   */
  private List<DataSource> ddDataSources = new ArrayList<DataSource>();

  /**
   * This map holds all yet queried data sources and their names (with "rm:" prefix) of
   * the resource management. So we can use them several times without querying them again.
   */
  private HashMap<String, DataSource> rmDataSourceElements = new HashMap<String, DataSource>();

  /**
   * This map holds all activity-policy mappings for the late binding of data source which
   * are specified in the deployment descriptor.
   */
  private Map<String, DataSource> activityMappings = new HashMap<String, DataSource>();

  /**
   * This maps holds all late binding mappings for the activities.
   */
  private Map<String, LateBinding> lateBindingMappings = new HashMap<String, LateBinding>();
  
  private Boolean AUDITING_MODE = false;

  private static String lastProcess = "";

  public static DeploymentUtils getInstance() {
    if (instance == null) {
      instance = new DeploymentUtils();
      // Set up a simple configuration that logs on the console.
      PropertyConfigurator.configure("log4j.properties");

      // Initialize the created instance
      instance.init(DataManagementActivity.getDeployDir(),
          DataManagementActivity.getProcessName());

      lastProcess = DataManagementActivity.getProcessName();
    }

    if (!lastProcess.equals(DataManagementActivity.getProcessName())) {
      // Initialize the created instance
      instance.reInit(DataManagementActivity.getDeployDir(),
          DataManagementActivity.getProcessName());
    }

    return instance;
  }

  /**
   * If the process changed the new deployment descriptor has to be read
   * 
   * @param deployDir
   * @param processName
   */
  private void reInit(String deployDir, String processName) {
    ddDataSources.clear();
    activityMappings.clear();

    init(deployDir, processName);
  }

  private void init(String path, String process) {
    readDeploymentDescriptor(path, process);

    // Set the auditing mode in the SIMPL BPEL Event Listener
    AuditingParameters.getInstance().setMode(AUDITING_MODE);
    logger.debug("Set auditing mode to: " + AUDITING_MODE);
  }

  /**
   * Read the deployment descriptor at the given path and fill all maps with the
   * corresponding data.
   * 
   * @param path
   *          to the deployment descriptor file
   */
  @SuppressWarnings({ "rawtypes" })
  private void readDeploymentDescriptor(String path, String process) {
    File deploy = new File(path);
  
    if (logger.isDebugEnabled()) {
      logger.debug("Path to the DD: " + path);
    }
  
    if (deploy.exists()) {
      SAXBuilder builder = new SAXBuilder();
      Document doc;
      try {
        InputStream inputStream = new FileInputStream(deploy);
  
        doc = builder.build(inputStream);
  
        Element root = doc.getRootElement();
  
        List processes = root.getChildren(EL_PROCESS, DD_NAMESPACE);
  
        for (Object processObj : processes) {
          Element processElement = (Element) processObj;
          if (processElement.getAttributeValue(AT_NAME).contains(process)) {
  
            // TODO: Read the attached Resource Management address
            //PROCESS_RM_ADDRESS = ((Element) processElement)
            //    .getAttributeValue(AT_ATTACHED_RM_ADDRESS);
  
            // Read the auditing mode to set to the SIMPL Event Listener
            if (((Element) processElement).getChild(EL_AUDITING_MODE, DD_NAMESPACE) != null) {
              AUDITING_MODE = Boolean.valueOf(((Element) processElement).getChild(
                  EL_AUDITING_MODE, DD_NAMESPACE).getText());
            } else {
              AUDITING_MODE = false;
            }
  
            List datasourceElements = processElement.getChildren(EL_DATASOURCE,
                DD_NAMESPACE);
            for (Object data : datasourceElements) {
              // Now we query all the required information
              // and create a new DataSource object out of them.
              String name = "dd:"
                  + ((Element) data).getAttributeValue(AT_DATA_SOURCE_NAME);
              if (logger.isDebugEnabled()) {
                logger.debug("Name of ds: " + name);
              }
              String address = ((Element) data).getAttributeValue(AT_DATA_SOURCE_ADDRESS);
              if (logger.isDebugEnabled()) {
                logger.debug("Address of ds: " + address);
              }
              String type = ((Element) data).getAttributeValue(AT_DATA_SOURCE_TYPE);
              if (logger.isDebugEnabled()) {
                logger.debug("Type of ds: " + type);
              }
              String subtype = ((Element) data).getAttributeValue(AT_DATA_SOURCE_SUBTYPE);
              if (logger.isDebugEnabled()) {
                logger.debug("Subtype of ds: " + subtype);
              }
              String username = ((Element) data).getAttributeValue(AT_DATA_SOURCE_USER);
              if (logger.isDebugEnabled()) {
                logger.debug("UserName of ds: " + username);
              }
              String password = ((Element) data).getAttributeValue(AT_DATA_SOURCE_PASSW);
              if (logger.isDebugEnabled()) {
                logger.debug("Password of ds: " + password);
              }
              String format = ((Element) data).getAttributeValue(AT_DATA_SOURCE_FORMAT);
              if (logger.isDebugEnabled()) {
                logger.debug("Format of ds: " + format);
              }
  
              DataSource dataSource = new DataSource();
              
              dataSource.setName(name);
              dataSource.setAddress(address);
              dataSource.setType(type);
              dataSource.setSubType(subtype);
              Authentication auth = new Authentication();
              auth.setUser(username);
              auth.setPassword(password);
              dataSource.setAuthentication(auth);
              dataSource.getConnector().getConverterDataFormat().setName(format);
              
              ddDataSources.add(dataSource);
            }
  
            List mappingElements = processElement.getChildren(EL_MAPPING, DD_NAMESPACE);
            for (Object map : mappingElements) {
              // Now we query all the required late binding
              // information and save them in new DataSource
              // objects.
              String activity = ((Element) map).getAttributeValue(AT_MAPPING_ACTIVITY);
              String strat = ((Element) map).getAttributeValue(AT_MAPPING_STRATEGY);
              Strategy strategy = null;
              if (!strat.isEmpty()) {
                strategy = Strategy.valueOf(strat);
              }
  
              Element policy = ((Element) map).getChild(EL_POLICY, DD_NAMESPACE);
              String policyData = ((Element) policy)
                  .getAttributeValue(AT_MAPPING_POLICY_DATA);
  
              if (logger.isDebugEnabled()) {
                logger.debug("Policy of ds: " + policyData);
              }
  
              LateBinding lateBinding = new LateBinding();
              lateBinding.setPolicy(policyData);
              lateBinding.setStrategy(strategy);
              
              lateBindingMappings.put(activity, lateBinding);
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
  }

  /**
   * Queries a data source from the resource management just on demand. That means that the
   * data source is queried just before it is used from the activity. So we will save a
   * lot of time and performance.
   */
  public DataSource getResourceManagementDataSourceByName(String dataSourceName) {
    DataSource resultDataSource = null;

    if (rmDataSourceElements.containsKey(dataSourceName)) {
      resultDataSource = rmDataSourceElements.get(dataSourceName);
    } else {
      String nameWOprefix = dataSourceName.split(":")[1];

      resultDataSource = SIMPLResourceManagement.getInstance().getDataSourceByName(nameWOprefix);
      resultDataSource.setName(dataSourceName); // set name with prefix

      // Add the queried data source to the map
      rmDataSourceElements.put(dataSourceName, resultDataSource);

      if (logger.isDebugEnabled()) {
        logger.debug("Name of ds: " + resultDataSource.getName());
      }
      if (logger.isDebugEnabled()) {
        logger.debug("Address of ds: " + resultDataSource.getAddress());
      }
      if (logger.isDebugEnabled()) {
        logger.debug("Type of ds: " + resultDataSource.getType());
      }
      if (logger.isDebugEnabled()) {
        logger.debug("Subtype of ds: " + resultDataSource.getSubType());
      }
      if (logger.isDebugEnabled()) {
        logger.debug("UserName of ds: " + resultDataSource.getAuthentication().getUser());
      }
      if (logger.isDebugEnabled()) {
        logger.debug("Password of ds: "
            + resultDataSource.getAuthentication().getPassword());
      }
      if (logger.isDebugEnabled()) {
        logger.debug("Format of ds: " + resultDataSource.getConnector().getConverterDataFormat().getName());

      }
    }

    return resultDataSource;
  }

  /**
   * Returns the late binding information of an activity.
   * 
   * @param activityName
   * @return
   */
  public LateBinding getLateBindingOfActivity(String activityName) {
    return lateBindingMappings.get(activityName);
  }

  public DataSource getDataSourceByName(String name) {
    boolean found = false;

    Iterator<DataSource> iterator = this.ddDataSources.iterator();
    DataSource data = null;

    while (!found && iterator.hasNext()) {
      data = iterator.next();
      if (data.getName().equals(name)) {
        found = true;
      } else {
        data = null;
      }
    }

    return data;
  }
}
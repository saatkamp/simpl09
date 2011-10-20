package org.apache.ode.simpl.ea.util;

import java.util.HashMap;

import org.apache.log4j.Logger;
import org.apache.ode.bpel.rtrep.common.extension.ExtensionContext;
import org.simpl.core.clients.RMClient;
import org.simpl.resource.management.data.DataSource;
import org.simpl.resource.management.data.LateBinding;
import org.simpl.resource.management.data.Strategy;

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
public class DataSourceUtils {
  static Logger logger = Logger.getLogger(DataSourceUtils.class);

  /**
   * This map holds all yet queried data sources and their names (with "rm:" prefix) of
   * the resource management. So we can use them several times without querying them
   * again.
   */
  private static HashMap<String, DataSource> rmDataSourceElements = new HashMap<String, DataSource>();

  /**
   * Returns the data source from an identifier.
   * 
   * @param context
   * @param dataSourceIdentifier
   *          of the data source
   * @return The data source object.
   */
  public static DataSource getDataSource(ExtensionContext context,
      String dataSourceIdentifier) {
    DataSource dataSource = new DataSource();

    // TODO: Uncommented because rm: identifiers are not supported anymore
    // if (dataSourceIdentifier.contains("rm:")) {
    // // Query the data source from resource management (on demand)
    // dataSource = getResourceManagementDataSourceByName(dataSourceIdentifier);
    // } else {
    String name = VariableUtils.getDataSourceReferenceValue(context, dataSourceIdentifier,
        "name");

    if (name != null) {
      dataSource = getResourceManagementDataSourceByName(name);
    }
    // }

    if (logger.isDebugEnabled()) {
      logger.debug("Name of ds: " + dataSource.getName());
      logger.debug("Address of ds: " + dataSource.getAddress());
      logger.debug("Type of ds: " + dataSource.getType());
      logger.debug("Subtype of ds: " + dataSource.getSubType());
      logger.debug("UserName of ds: " + dataSource.getAuthentication().getUser());
      logger.debug("Password of ds: " + dataSource.getAuthentication().getPassword());
      logger.debug("Format of ds: "
          + dataSource.getConnector().getDataConverter().getWorkflowDataFormat());
    }

    return dataSource;
  }

  /**
   * Returns the data source name.
   * 
   * @param context
   *          of the activity.
   * @param dataSourceIdentifier
   *          of the data source.
   * @return The data source name.
   */
  public static String getName(ExtensionContext context, String dataSourceIdentifier) {
    String dataSourceName = null;

    // TODO: Uncommented because rm: identifiers are not supported anymore
    // if (dataSourceIdentifier.startsWith("rm:")) {
    // dataSourceName = dataSourceIdentifier.substring(3);
    // } else {
    dataSourceName = VariableUtils.getDataSourceReferenceValue(context,
        dataSourceIdentifier, "name");
    // }

    return dataSourceName;
  }

  /**
   * Returns the data source late binding.
   * 
   * @param context
   *          of the activity.
   * @param descriptorVariableName
   *          of the data source.
   * @return The data source late binding.
   */
  public static LateBinding getLateBinding(ExtensionContext context,
      String descriptorVariableName) {
    LateBinding lateBinding = new LateBinding();
    String requirements = VariableUtils.getDataSourceReferenceValue(context,
        descriptorVariableName, "requirements");

    lateBinding.setPolicy(requirements);
    lateBinding.setStrategy(Strategy.FIRST_FIND);

    return lateBinding;
  }

  /**
   * Queries a data source from the resource management just on demand. That means that
   * the data source is queried just before it is used from the activity. So we will save
   * a lot of time and performance.
   */
  private static DataSource getResourceManagementDataSourceByName(String dataSourceName) {
    DataSource resultDataSource = null;

    if (rmDataSourceElements.containsKey(dataSourceName)) {
      resultDataSource = rmDataSourceElements.get(dataSourceName);
    } else {
      // TODO: Uncommented because rm: identifiers are not supported anymore
      // String nameWOprefix = null;
      //
      // if (dataSourceName.startsWith("rm:")) {
      // nameWOprefix = dataSourceName.split(":")[1];
      // } else {
      // nameWOprefix = dataSourceName;
      // }

      resultDataSource = RMClient.getInstance().getDataSourceByName(dataSourceName);
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
        logger.debug("Format of ds: "
            + resultDataSource.getConnector().getDataConverter().getWorkflowDataFormat());
      }
    }

    return resultDataSource;
  }
}
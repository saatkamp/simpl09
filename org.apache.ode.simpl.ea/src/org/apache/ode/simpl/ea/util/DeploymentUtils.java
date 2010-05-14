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
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.Namespace;
import org.jdom.input.SAXBuilder;
import org.simpl.core.services.datasource.Authentication;
import org.simpl.core.services.datasource.DataSource;
import org.simpl.core.services.datasource.LateBinding;
import org.simpl.core.services.strategy.Strategy;

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

	private static final String EL_DATASOURCE = "datasources";
	private static final String EL_MAPPING = "activityMappings";
	private static final String EL_POLICY = "policy";

	private static final String AT_DATA_SOURCE_NAME = "dataSourceName";
	private static final String AT_DATA_SOURCE_ADDRESS = "address";
	private static final String AT_DATA_SOURCE_TYPE = "type";
	private static final String AT_DATA_SOURCE_SUBTYPE = "subtype";
	private static final String AT_DATA_SOURCE_USER = "userName";
	private static final String AT_DATA_SOURCE_PASSW = "password";

	private static final String AT_MAPPING_ACTIVITY = "activity";
	private static final String AT_MAPPING_POLICY_DATA = "policyData";
	private static final String AT_MAPPING_STRATEGY = "strategy";
	private static final String AT_MAPPING_UDDI_ADDRESS = "uddiAddress";

	private final Namespace DD_NAMESPACE = Namespace
			.getNamespace("http://www.apache.org/ode/schemas/dd/2007/03");

	/**
	 * This list holds all data source information of the deployment descriptor.
	 */
	private List<DataSource> dataSourceElements = new ArrayList<DataSource>();

	/**
	 * This map holds all activity-policy mappings for the late binding of data
	 * source which are specified in the deployment descriptor.
	 */
	private Map<String, DataSource> activityMappings = new HashMap<String, DataSource>();

	public static DeploymentUtils getInstance() {
		if (instance == null) {
			instance = new DeploymentUtils();
			// Set up a simple configuration that logs on the console.
			PropertyConfigurator.configure("log4j.properties");
		}
		return instance;
	}

	/**
	 * Read the deployment descriptor at the given path and fill all maps with
	 * the corresponding data.
	 * 
	 * @param path
	 *            to the deployment descriptor file
	 */
	public void readDeploymentDescriptor(String path, String process) {
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
					if (processElement.getAttributeValue(AT_NAME).contains(
							process)) {
						List datasourceElements = processElement.getChildren(
								EL_DATASOURCE, DD_NAMESPACE);
						for (Object data : datasourceElements) {
							// Now we query all the required information
							// and create a new DataSource object out of them.
							String name = "dd:"
									+ ((Element) data)
											.getAttributeValue(AT_DATA_SOURCE_NAME);
							if (logger.isDebugEnabled()) {
								logger.debug("Name of ds: " + name);
							}
							String address = ((Element) data)
									.getAttributeValue(AT_DATA_SOURCE_ADDRESS);
							if (logger.isDebugEnabled()) {
								logger.debug("Address of ds: " + address);
							}
							String type = ((Element) data)
									.getAttributeValue(AT_DATA_SOURCE_TYPE);
							if (logger.isDebugEnabled()) {
								logger.debug("Type of ds: " + type);
							}
							String subtype = ((Element) data)
									.getAttributeValue(AT_DATA_SOURCE_SUBTYPE);
							if (logger.isDebugEnabled()) {
								logger.debug("Subtype of ds: " + subtype);
							}
							String username = ((Element) data)
									.getAttributeValue(AT_DATA_SOURCE_USER);
							if (logger.isDebugEnabled()) {
								logger.debug("UserName of ds: " + username);
							}
							String password = ((Element) data)
									.getAttributeValue(AT_DATA_SOURCE_PASSW);
							if (logger.isDebugEnabled()) {
								logger.debug("Password of ds: " + password);
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
							
							//FIXME: This is just for debugging and should be removed
							dataSource.setDataFormat("RDB");

							dataSourceElements.add(dataSource);
						}

						List mappingElements = processElement.getChildren(
								EL_MAPPING, DD_NAMESPACE);
						for (Object map : mappingElements) {
							// Now we query all the required late binding
							// information and save them in new DataSource
							// objects.
							String activity = ((Element) map)
									.getAttributeValue(AT_MAPPING_ACTIVITY);
							String strat = ((Element) map)
									.getAttributeValue(AT_MAPPING_STRATEGY);
							Strategy strategy = null;
							if (!strat.isEmpty()) {
								strategy = Strategy.valueOf(strat);
							}

							Element policy = ((Element) map).getChild(
									EL_POLICY, DD_NAMESPACE);
							String policyData = ((Element) policy)
									.getAttributeValue(AT_MAPPING_POLICY_DATA);

							String uddiAddress = ((Element) map)
									.getAttributeValue(AT_MAPPING_UDDI_ADDRESS);

							DataSource newDs = new DataSource();
							LateBinding lateBinding = new LateBinding();
							lateBinding.setPolicy(policyData);
							lateBinding.setStrategy(strategy);
							lateBinding.setUddiAddress(uddiAddress);
							newDs.setLateBinding(lateBinding);
							activityMappings.put(activity, newDs);
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
		//		
		// DataSource dataSource = new DataSource();
		// dataSource.setName("dd:myDB");
		// dataSource.setAddress("http://localhost:3306/test");
		// dataSource.setType("Database");
		// dataSource.setSubType("MySQL");
		// Authentication auth = new Authentication();
		// auth.setUser("test");
		// auth.setPassword("test");
		// dataSource.setAuthentication(auth);
		//
		// dataSourceElements.add(dataSource);
	}

	/**
	 * Returns the DataSource which is mapped to the given activity name.
	 * 
	 * @param activityName
	 * @return
	 */
	public DataSource getDataSourceOfActivity(String activityName) {
		DataSource data = null;

		data = this.activityMappings.get(activityName);

		return data;
	}

	public DataSource getDataSourceByName(String name) {
		boolean found = false;

		Iterator<DataSource> iterator = this.dataSourceElements.iterator();
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

	/**
	 * This method merges a late binding data source and a static data source. A
	 * late binding data source has only a policy and a strategy. The static
	 * data source has a name, address, type, subtype, language and
	 * authentication information for a static binding of the data source.
	 * 
	 * @param lateBindingDs
	 * @param staticDs
	 * @return
	 */
	public DataSource merge(DataSource lateBindingDs, DataSource staticDs) {
		DataSource result = new DataSource();

		result.setName(staticDs.getName());
		result.setAddress(staticDs.getAddress());
		result.setType(staticDs.getType());
		result.setSubType(staticDs.getSubType());
		result.setAuthentication(staticDs.getAuthentication());
		result.setLateBinding(lateBindingDs.getLateBinding());

		return result;
	}
}

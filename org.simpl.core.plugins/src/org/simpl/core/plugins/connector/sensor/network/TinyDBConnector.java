package org.simpl.core.plugins.connector.sensor.network;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import net.tinyos.tinydb.parser.ParseException;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.simpl.core.exceptions.ConnectionException;
import org.simpl.core.plugins.connector.ConnectorPlugin;
import org.simpl.core.plugins.dataconverter.sensor.network.TinyDBResult;

import org.simpl.resource.management.data.Connector;
import org.simpl.resource.management.data.DataSource;

import commonj.sdo.DataObject;
import commonj.sdo.helper.HelperContext;
import commonj.sdo.helper.XMLDocument;

import commonj.sdo.impl.HelperProvider;

/**
 * <b>Purpose:</b>Implements all methods of the {@link Connector} interface for
 * supporting TinyDB, a declarative database for sensor networks.<br>
 * <b>Description:</b>Two files are necessary: 'catalog.xml' and 'tinydb.conf'.
 * They must be stored at the following location:
 * 'webapps/ode/WEB-INF/conf/tinydb_conf/'. The methods: 'issueCommand',
 * 'writeDataBack' and 'queryData' are not supported.<br>
 * <b>Copyright:</b>Licensed under the Apache License, Version 2.0.
 * http://www.apache.org/licenses/LICENSE-2.0<br>
 * <b>Company:</b>SIMPL<br>
 * 
 * @author Henrik Pietranek <henrik.pietranek@googlemail.com><br>
 * @link http://code.google.com/p/simpl09/
 */
public class TinyDBConnector extends
    ConnectorPlugin<List<String>, TinyDBResult> {

  // time to wait for results
  static int WORK_AROUND_SLEEP_TIME = 15000;

  static Logger logger = Logger.getLogger(TinyDBConnector.class);

  /**
   * Initialize the plug-in.
   */
  public TinyDBConnector() {
    this.setType("Database");
    this.setMetaDataSchemaType("tTinyDBMetaData");
    this.addSubtype("TinyDB");
    this.addLanguage("TinyDB", "TinySQL");

    // Set up a simple configuration that logs on the console.
    PropertyConfigurator.configure("log4j.properties");
  }

  @Override
  public boolean issueCommand(DataSource dataSource, String statement)
      throws ConnectionException {
    // not supported
    return false;
  }

  @Override
  public TinyDBResult retrieveData(DataSource dataSource, String statement)
      throws ConnectionException {
    TinyDBAccessHelper tinyDBAccessHelper = null;

    // create new helper
    tinyDBAccessHelper = new TinyDBAccessHelper(dataSource);

    try {
      // inject query
      tinyDBAccessHelper.injectQuery(statement);

      // wait for results
      Thread.sleep(WORK_AROUND_SLEEP_TIME);

      // stop query
      tinyDBAccessHelper.abortQuery();
    } catch (IOException e1) {
      // TODO Auto-generated catch block
      e1.printStackTrace();
    } catch (ParseException e1) {
      // TODO Auto-generated catch block
      e1.printStackTrace();
    } catch (InterruptedException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    // get results
    return tinyDBAccessHelper.getResult();
  }

  @Override
  public boolean writeDataBack(DataSource dataSource, List<String> data,
      String target) throws ConnectionException {
    // not supported
    return false;
  }

  @Override
  public boolean queryData(DataSource dataSource, String statement,
      String target) throws ConnectionException {
    // not supported
    return false;
  }
  
  @Override
  public boolean transferData(DataSource dataSource, DataSource dataSink,
      String statement, String target) throws ConnectionException {
    // not supported
    return false;
  }

  @Override
  public DataObject getMetaData(DataSource dataSource, String filter)
      throws ConnectionException {
    TinyDBConnector.logger.debug("zzz1");
    DataObject metaDataObject = this.getMetaDataSDO();
    TinyDBConnector.logger.debug("zzz2");
    InputStream in = null;
    HelperContext ctx = null;
    XMLDocument doc = null;

    // try to read the catalog.xml
    String FILE_NAME = "catalog.xml";
    String FILE_LOCATION_1 = System.getProperty("user.dir")
        + "/webapps/ode/WEB-INF/conf/tinydb_conf/" + FILE_NAME;
    String FILE_LOCATION_2 = System.getProperty("user.dir")
        + "/../webapps/ode/WEB-INF/conf/tinydb_conf" + FILE_NAME;

    try {
      in = new FileInputStream(FILE_LOCATION_1);
    } catch (FileNotFoundException e) {
      try {
        in = new FileInputStream(FILE_LOCATION_2);
      } catch (FileNotFoundException e1) {
        try {
          in = new FileInputStream(FILE_NAME);
        } catch (FileNotFoundException e2) {
          // TODO Auto-generated catch block
          e2.printStackTrace();
        }
      }
    }
    if (in != null) {
      ctx = HelperProvider.getDefaultContext();
      try {
        doc = ctx.getXMLHelper().load(in);
      } catch (IOException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }

      if (doc != null) {
        metaDataObject.setDataObject("catalog", doc.getRootObject());

      }
    }

    return metaDataObject;
  }

}

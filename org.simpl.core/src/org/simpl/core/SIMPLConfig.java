package org.simpl.core;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

/**
 * <b>Purpose:</b> Reads the SIMPLCore configuration from the config file and provides
 * functions to access the configuration settings. This includes information about all
 * registered plugins.<br>
 * <b>Description:</b> Searches for the config file in the current work directory and in
 * the Apache ODE <i>conf</i> directory whose relative location depends on the execution
 * path of Apache Tomcat.<br>
 * <b>Copyright:</b> <br>
 * <b>Company:</b> SIMPL<br>
 * 
 * @author schneimi<br>
 * @version $Id$<br>
 * @link http://code.google.com/p/simpl09/
 */
public class SIMPLConfig {
  private static final String CONFIG_FILE_NAME = "simpl-core-config.xml";
  private static final String CONFIG_FILE_1 = System.getProperty("user.dir")
      + "\\webapps\\ode\\WEB-INF\\conf\\" + CONFIG_FILE_NAME;
  private static final String CONFIG_FILE_2 = System.getProperty("user.dir")
      + "\\..\\webapps\\ode\\WEB-INF\\conf\\" + CONFIG_FILE_NAME;
  List<String> dataSourcePlugins = new ArrayList<String>();
  List<String> dataFormatPlugins = new ArrayList<String>();

  public SIMPLConfig() {
    InputStream in = null;
    XMLInputFactory factory = XMLInputFactory.newInstance();
    XMLStreamReader parser = null;

    try {
      in = new FileInputStream(CONFIG_FILE_1);
    } catch (FileNotFoundException e) {
      try {
        in = new FileInputStream(CONFIG_FILE_2);
      } catch (FileNotFoundException e1) {
        try {
          in = new FileInputStream(CONFIG_FILE_NAME);
        } catch (FileNotFoundException e2) {
          // TODO Auto-generated catch block
          e2.printStackTrace();
        }
      }
    }

    try {
      parser = factory.createXMLStreamReader(in);

      while (parser.hasNext()) {
        int event = parser.next();

        switch (event) {
        case XMLStreamConstants.END_DOCUMENT:
          parser.close();

          break;
        case XMLStreamConstants.START_ELEMENT:
          if (parser.getLocalName().equals("DataSourcePlugin")) {
            for (int i = 0; i < parser.getAttributeCount(); i++) {
              if (parser.getAttributeLocalName(i).equals("name")) {
                dataSourcePlugins.add(parser.getAttributeValue(i));
              }
            }
          }

          if (parser.getLocalName().equals("DataFormatPlugin")) {
            for (int i = 0; i < parser.getAttributeCount(); i++) {
              if (parser.getAttributeLocalName(i).equals("name")) {
                dataFormatPlugins.add(parser.getAttributeValue(i));
              }
            }
          }

          break;
        case XMLStreamConstants.CHARACTERS:
          break;
        case XMLStreamConstants.END_ELEMENT:
          break;
        default:
          break;
        }
      }
    } catch (XMLStreamException e1) {
      // TODO Auto-generated catch block
      e1.printStackTrace();
    }
  }

  /**
   * Returns a list of registered DataSourcePlugins. The list contains full qualified
   * names of DataSourcePlugin classes.
   * 
   * @return List of DataSourcePlugins
   */
  public List<String> getDataSourcePlugins() {
    return dataSourcePlugins;
  }

  /**
   * Returns a list of registered DataFormatPlugins. The list contains full qualified
   * names of DataFormatPlugin classes.
   * 
   * @return List of DataFormatPlugins
   */
  public List<String> getDataFormatPlugins() {
    return dataFormatPlugins;
  }
}
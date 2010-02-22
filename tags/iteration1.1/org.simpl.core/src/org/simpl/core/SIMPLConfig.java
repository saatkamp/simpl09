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
 * <b>Purpose:</b> <br>
 * <b>Description:</b> <br>
 * <b>Copyright:</b> <br>
 * <b>Company:</b> SIMPL<br>
 * 
 * TODO: DTD erstellen und überprüfen ob das Format stimmt
 * 
 * @author schneimi<br>
 * @version $Id$<br>
 * @link http://code.google.com/p/simpl09/
 */
public class SIMPLConfig {
  private static final String CONFIG_FILE1 = System.getProperty("user.dir")
      + "\\webapps\\ode\\WEB-INF\\conf\\simpl-core-config.xml";
  private static final String CONFIG_FILE2 = System.getProperty("user.dir")
      + "\\..\\webapps\\ode\\WEB-INF\\conf\\simpl-core-config.xml";
  List<String> datasourceServicePlugins = new ArrayList<String>();

  public SIMPLConfig() {
    InputStream in = null;
    XMLInputFactory factory = XMLInputFactory.newInstance();
    XMLStreamReader parser = null;

    try {
      in = new FileInputStream(CONFIG_FILE1);
    } catch (FileNotFoundException e) {
      try {
        in = new FileInputStream(CONFIG_FILE2);
      } catch (FileNotFoundException e1) {
        try {
          in = new FileInputStream("simpl-core-config.xml");
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
          if (parser.getLocalName().equals("datasourceServicePlugin")) {
            for (int i = 0; i < parser.getAttributeCount(); i++) {
              if (parser.getAttributeLocalName(i).equals("name")) {
                datasourceServicePlugins.add(parser.getAttributeValue(i));
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
   * Returns a list of registered DatasourceServicePlugins. The list contains full
   * qualified names of DataSourceServicePlugin classes.
   * 
   * @return List of DatasourceServicePlugins
   */
  public List<String> getDataSourceServicePlugins() {
    return datasourceServicePlugins;
  }
}
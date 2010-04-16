package org.simpl.rrs;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

public class RRSConfig {

	private static final String CONFIG_FILE_NAME = "rrs-config.xml";
	private static final String CONFIG_FILE_1 = System.getProperty("user.dir")
    + "\\webapps\\ode\\WEB-INF\\conf\\" + CONFIG_FILE_NAME;
    private static final String CONFIG_FILE_2 = System.getProperty("user.dir")
    + "\\..\\webapps\\ode\\WEB-INF\\conf\\" + CONFIG_FILE_NAME;
	List<String> DSAdapterPlugins = new ArrayList<String>();
	
	public RRSConfig() {
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
	          if (parser.getLocalName().equals("RRSDQAdapterPlugin")) {
	            for (int i = 0; i < parser.getAttributeCount(); i++) {
	              if (parser.getAttributeLocalName(i).equals("name")) {
	            	  DSAdapterPlugins.add(parser.getAttributeValue(i));
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
	
	public List<String> getDSAdapterPlugins() {
		return DSAdapterPlugins;
	}
	}

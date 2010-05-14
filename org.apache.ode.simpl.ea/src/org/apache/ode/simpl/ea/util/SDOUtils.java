package org.apache.ode.simpl.ea.util;

import java.io.IOException;
import java.io.StringReader;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import commonj.sdo.DataObject;
import commonj.sdo.helper.XMLHelper;

/**
 * <b>Purpose:</b> <br>
 * <b>Description:</b> <br>
 * <b>Copyright:</b>  Licensed under the Apache License, Version 2.0. http://www.apache.org/licenses/LICENSE-2.0<br>
 * <b>Company:</b> SIMPL<br>
 * 
 * @author Michael Hahn <hahnml@studi.informatik.uni-stuttgart.de> <br>
 * @version $Id$ <br>
 * @link http://code.google.com/p/simpl09/
 *
 */
public class SDOUtils {

	static Logger logger = Logger.getLogger(SDOUtils.class);
	
	public static Node createNodeOfSDO(DataObject data, String namespaceURI){
		String doc = XMLHelper.INSTANCE.save(data, namespaceURI, "data");
		
		if (logger.isDebugEnabled()) {
			logger.debug("DataObject XML: " + doc);
		}
		
		DocumentBuilderFactory f = DocumentBuilderFactory.newInstance();
		Document document;
		try {
			document = f.newDocumentBuilder().parse(new InputSource(new StringReader(doc)));
			
			if (logger.isDebugEnabled()) {
				logger.debug("Document XML: " + document.toString());
			}
			
			return document;
		} catch (SAXException e) {
			if (logger.isDebugEnabled()) {
				logger.debug("SAXException: ", e);
			}
		} catch (IOException e) {
			if (logger.isDebugEnabled()) {
				logger.debug("IOException: ", e);
			}
		} catch (ParserConfigurationException e) {
			if (logger.isDebugEnabled()) {
				logger.debug("ParserConfigurationException: ", e);
			}
		}
		
		return null;
	}
}

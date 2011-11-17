package org.apache.ode.simpl.ea.util;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.apache.ode.utils.DOMUtils;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
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
	
	public static Node createNodeOfSDO(DataObject data){
		String doc = XMLHelper.INSTANCE.save(data, "commonj.sdo", "dataObject");
		
		if (logger.isDebugEnabled()) {
			logger.debug("DataObject XML: " + doc);
		}
		
        Element node = null;
        
        try {
			node = DOMUtils.stringToDOM(doc);
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        return node;
	}
}

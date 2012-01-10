package org.simpl.core.plugins.dataconverter.xml;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.io.Writer;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.jdom.Document;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;
import org.simpl.core.plugins.dataconverter.DataConverterPlugin;

import commonj.sdo.DataObject;
import commonj.sdo.helper.HelperContext;
import commonj.sdo.helper.XMLDocument;
import commonj.sdo.helper.XMLHelper;
import commonj.sdo.impl.HelperProvider;

/**
 * <b>Purpose:</b>Used to create a SDO from XMLResult and vice versa.<br>
 * <b>Description:</b>Converts a xml-document or xml-fragment (which is saved as
 * StringBuffer) to a DataObject. When converting back from a DataObject, a file
 * is created. This file contains the xml data.<br>
 * <b>Copyright:</b>Licensed under the Apache License, Version 2.0.
 * http://www.apache.org/licenses/LICENSE-2.0<br>
 * <b>Company:</b>SIMPL<br>
 * 
 * @author Henrik Pietranek <henrik.pietranek@googlemail.com><br>
 * @link http://code.google.com/p/simpl09/
 */
public class XMLResultDataConverter extends
    DataConverterPlugin<XMLResult, File> {

  static Logger logger = Logger.getLogger(XMLResultDataConverter.class); // Logger

  public XMLResultDataConverter() {
    this.setDataFormat("XMLDataFormat");
    this.setSchemaType("tXMLDataFormat");

    // Set up a simple configuration that logs on the console.
    PropertyConfigurator.configure("log4j.properties");
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * org.simpl.core.services.dataformat.DataFormatService#getSDO(java.lang.Object
   * )
   */
  @Override
  public DataObject toSDO(XMLResult xmlDatabaseResult) {
    if (XMLResultDataConverter.logger.isDebugEnabled()) {
      XMLResultDataConverter.logger
          .debug("Convert data from 'XMLResult' to 'DataObject'.");
    }
    HelperContext ctx = null;
    XMLDocument doc = null;
    StringBuffer document = null;
    String headerLine = null;

    DataObject sdo = this.getSDO();

    DataObject dataFormatMetaDataObject = sdo
        .createDataObject("dataFormatMetaData");
    dataFormatMetaDataObject.set("dataSource", xmlDatabaseResult
        .getDataSource().getName());

    // retrieve document or fragment
    document = xmlDatabaseResult.getDocument();

    if (document != null) {

      // remove the header line and save separately
      if (document.indexOf("<?") != -1) {
        headerLine = document.substring(document.indexOf("<?"),
            document.lastIndexOf("?>") + 2);
        document.delete(document.indexOf("<?"), document.lastIndexOf("?>") + 2);
        sdo.setString("documentHeader", headerLine);
      }

      // create a virtual root element
      // this is required for xml fragments without root element
      document.insert(0, "<documentContent>");
      document.append("</documentContent>");

      // create xml representation
      ctx = HelperProvider.getDefaultContext();
      doc = ctx.getXMLHelper().load(document.toString());

      // insert into sdo
      if (doc != null) {
        sdo.setDataObject("documentContent", doc.getRootObject());
      }
    }

    return sdo;
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * org.simpl.core.services.dataformat.DataFormatService#fromSDO(commonj.sdo
   * .DataObject)
   */
  @Override
  public File fromSDO(DataObject sdo) {
    if (XMLResultDataConverter.logger.isDebugEnabled()) {
      XMLResultDataConverter.logger
          .debug("Convert data from 'DataObject' to 'File'.");
    }
    SAXBuilder saxBuilder = null;
    Document document = null;
    XMLOutputter xmlOutputter = null;
    String headerLine = null;
    StringBuffer stringBuffer = null;
    Writer fileWriter = null;
    File file = null;

    // exract headerline
    try {
      headerLine = sdo.getString("documentHeader");
    } catch (Exception e) {
      e.printStackTrace();
    }

    // convert sdo to string
    String sdoStringRepresentation = XMLHelper.INSTANCE.save(
        sdo.getRootObject(), "commonj.sdo", "dataObject");

    // build file content
    if (sdoStringRepresentation.length() > 0) {
      try {
        saxBuilder = new SAXBuilder();
        document = saxBuilder.build(new StringReader(sdoStringRepresentation));
        xmlOutputter = new XMLOutputter(Format.getPrettyFormat());
        stringBuffer = new StringBuffer();

        // append header line
        if (headerLine != null) {
          stringBuffer.append(headerLine);
        }

        // extract encoding
        // this program use UTF-8 when writing a file
        if (stringBuffer.indexOf("encoding=\"") != -1) {
          int startEncodingPos = stringBuffer.indexOf("encoding=\"") + 10;
          int endEncodingPos = stringBuffer.indexOf("\"", startEncodingPos);
          stringBuffer.replace(startEncodingPos, endEncodingPos, "UTF-8");
        }

        // append xml structure and delete virtual root element
        stringBuffer.append(xmlOutputter.outputString(document.getRootElement()
            .getChild("documentContent").getChildren()));
      } catch (JDOMException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      } catch (IOException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
    }

    // create file
    try {
      // create file with random number
      file = new File(String.valueOf((int) (Math.random() * 10000)) + ".xml");
      fileWriter = new OutputStreamWriter(new FileOutputStream(file), "UTF-8");
      fileWriter.write(stringBuffer.toString());
    } catch (UnsupportedEncodingException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (FileNotFoundException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } finally {
      if (fileWriter != null) {
        try {
          fileWriter.close();
        } catch (IOException e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
        }
      }
    }
    file.deleteOnExit();
    return file;
  }
}

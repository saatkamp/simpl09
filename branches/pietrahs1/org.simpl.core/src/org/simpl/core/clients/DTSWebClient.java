package org.simpl.core.clients;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedHashMap;

import org.simpl.core.datatransformation.DataTransformationServiceProvider;
import org.simpl.core.services.SIMPLCoreAdministrationService;
import org.simpl.data.transformation.webservice.client.DataTransformation;
import org.simpl.data.transformation.webservice.client.DataTransformationClient;
import org.simpl.resource.management.ResourceManagement;

import commonj.sdo.DataObject;
import commonj.sdo.helper.XMLDocument;
import commonj.sdo.helper.XMLHelper;
import commonj.sdo.helper.XSDHelper;

/**
 * <b>Purpose:</b>Provides access to the Data Transformation web service.<br>
 * <b>Description:</b>Uses the DataTransformationClient. The address of the Data
 * Transformation web service is retrieved from the SIMPLCore embedded Derby database.<br>
 * <b>Copyright:</b>Licensed under the Apache License, Version 2.0.
 * http://www.apache.org/licenses/LICENSE-2.0<br>
 * <b>Company:</b>SIMPL<br>
 * 
 * @author hiwi<br>
 * @version $Id: SIMPLResourceManagement.java 1718 2010-11-17 18:25:37Z
 *          michael.schneidt@arcor.de $<br>
 * @link http://code.google.com/p/simpl09/
 */
public class DTSWebClient {
  /**
   * The resource discovery web service.
   */
  private DataTransformation dataTransformation = null;

  /**
   * DTSWebClient singleton instance.
   */
  private static final DTSWebClient instance = new DTSWebClient();

  /**
   * Private singleton constructor.
   */
  private DTSWebClient() {
    // retrieve the resource discovery address from the internal embedded derby simplDB
    LinkedHashMap<String, String> settings = SIMPLCoreAdministrationService.getInstance()
        .getService().loadSettings("DATATRANSFORMATIONSERVICE", "SETTINGS", "lastSaved");
    String dataTransformationAddress = settings.get("ADDRESS");

    if (dataTransformationAddress != null) {
      dataTransformation = DataTransformationClient.getService(dataTransformationAddress);
    }
  }

  /**
   * Returns the {@link DTSWebClient} singleton instance.
   * 
   * @return
   */
  public static DTSWebClient getInstance() {
    return DTSWebClient.instance;
  }

  public DataObject convert(String fromDataFormat, String toDataFormat, DataObject data, String connectorImpl) {
    String serviceImpl = DataTransformationServiceProvider.getInstance(fromDataFormat, toDataFormat).getClass().getCanonicalName();
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    String dataString = null;
    DataObject convertedData = null;
    String convertedDataString = null;
    ResourceManagement resourceManagement = new ResourceManagement();
    String schema = null;
    
    if (data != null) {
      try {
        XMLDocument xmlDocument = XMLHelper.INSTANCE.createDocument(data, "commonj.sdo", "dataObject");
        xmlDocument.setEncoding("UTF-8");
        XMLHelper.INSTANCE.save(xmlDocument, outputStream, null);
      } catch (IOException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
    }
    
    dataString = new String(outputStream.toByteArray());
    convertedDataString = dataTransformation.convert(serviceImpl, dataString, connectorImpl);
    
    try {
      schema = resourceManagement.getDataFormatSchema(toDataFormat);
      InputStream schemaInputStream = new ByteArrayInputStream(schema.getBytes());
      XSDHelper.INSTANCE.define(schemaInputStream, null);
  
      // convert xml string to SDO
      XMLDocument xmlDoc = XMLHelper.INSTANCE.load(convertedDataString);
      convertedData = xmlDoc.getRootObject();
    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    
    return convertedData;
  }
}
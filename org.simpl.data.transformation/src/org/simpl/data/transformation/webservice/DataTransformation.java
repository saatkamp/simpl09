package org.simpl.data.transformation.webservice;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

import org.simpl.data.transformation.DataTransformationService;
import org.simpl.resource.management.ResourceManagement;

import commonj.sdo.DataObject;
import commonj.sdo.helper.XMLDocument;
import commonj.sdo.helper.XMLHelper;
import commonj.sdo.helper.XSDHelper;

/**
 * <b>Purpose: Provide the data transformation services as web service.</b> <br>
 * <b>Description:</b>This class can be deployed as JAX-WS web service, the concrete
 * service implementation to use is passed as parameter in the functions.<br>
 * <b>Copyright:</b>Licensed under the Apache License, Version 2.0.
 * http://www.apache.org/licenses/LICENSE-2.0<br>
 * <b>Company:</b>SIMPL<br>
 * 
 * @author schneimi<br>
 * @version $Id$<br>
 * @link http://code.google.com/p/simpl09/
 */
@WebService(name = "DataTransformation")
@SOAPBinding(style = SOAPBinding.Style.RPC)
public class DataTransformation {
  /**
   * @see DataTransformation
   * @param serviceImpl
   *          The data transformation service implementation
   * @param data
   *          The data to convert
   * @param connectorImpl
   *          The connector implementation that is used with the data after conversion
   * @return
   */
  @WebMethod(action = "convertTo")
  public String convertTo(@WebParam(name = "serviceImpl") String serviceImpl,
      @WebParam(name = "data") String data,
      @WebParam(name = "connectorImpl") String connectorImpl) {
    DataObject convertedDataObject = null;
    String convertedData = null;

    try {
      DataObject dataObject = (DataObject) this.deserializeData(data);
      DataTransformationService dataTransformationService = (DataTransformationService) Class
          .forName(serviceImpl).newInstance();

      convertedDataObject = dataTransformationService
          .convertTo(dataObject, connectorImpl);
      convertedData = this.serializeData(convertedDataObject);
    } catch (InstantiationException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (IllegalAccessException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (ClassNotFoundException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    return convertedData;
  }

  /**
   * @see DataTransformation
   * @param serviceImpl
   *          The data transformation service implementation
   * @param data
   *          The data to convert
   * @param connectorImpl
   *          The connector implementation that is used with the data after conversion
   * @return
   */
  @WebMethod(action = "convertFrom")
  public String convertFrom(@WebParam(name = "serviceImpl") String serviceImpl,
      @WebParam(name = "data") String data,
      @WebParam(name = "connectorImpl") String connectorImpl) {
    DataObject convertedDataObject = null;
    String convertedData = null;

    try {
      DataObject dataObject = (DataObject) this.deserializeData(data);
      DataTransformationService dataTransformationService = (DataTransformationService) Class
          .forName(serviceImpl).newInstance();

      convertedDataObject = dataTransformationService.convertFrom(dataObject,
          connectorImpl);
      convertedData = this.serializeData(convertedDataObject);
    } catch (InstantiationException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (IllegalAccessException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (ClassNotFoundException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    return convertedData;
  }

  /**
   * @see DataTransformation
   * @param serviceImpl
   *          The data transformation service implementation
   * @param data
   *          The data to convert
   * @param connectorImpl
   *          The connector implementation that is used with the data after conversion
   * @return
   */
  @WebMethod(action = "convert")
  public String convert(@WebParam(name = "serviceImpl") String serviceImpl,
      @WebParam(name = "data") String data,
      @WebParam(name = "connectorImpl") String connectorImpl) {
    DataObject convertedDataObject = null;
    String convertedData = null;

    try {
      DataTransformationService dataTransformationService = (DataTransformationService) Class
          .forName(serviceImpl).newInstance();

      DataObject dataObject = this.deserializeData(data);

      convertedDataObject = dataTransformationService.convert(dataObject, connectorImpl);
      convertedData = this.serializeData(convertedDataObject);
    } catch (InstantiationException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (IllegalAccessException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (ClassNotFoundException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    return convertedData;
  }

  /**
   * Serializes a data object to a XML encoded object string.
   * 
   * @param data
   * @return serialized object string
   */
  private String serializeData(DataObject data) {
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    String serializedData = null;

    if (data != null) {
      try {
        XMLDocument xmlDocument = XMLHelper.INSTANCE.createDocument(data, "commonj.sdo",
            "dataObject");
        xmlDocument.setEncoding("UTF-8");
        XMLHelper.INSTANCE.save(xmlDocument, outputStream, null);
      } catch (IOException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
    }

    serializedData = new String(outputStream.toByteArray());

    return serializedData;
  }

  /**
   * Deserializes a XML encoded data object string to a java object.
   * 
   * @param data
   * @return deserialized java object
   */
  private DataObject deserializeData(String data) {
    DataObject deserializedData = null;
    ResourceManagement resourceManagement = new ResourceManagement();
    String schema = null;
    String dataFormat = null;
    Pattern dataFormatPattern = Pattern.compile("dataFormat=\"(.*?)\"");
    Matcher matcher = dataFormatPattern.matcher(data);

    // retrieve the data format from the SDO xml data string
    if (matcher.find()) {
      dataFormat = matcher.group(1);
    }

    if (dataFormat != null) {
      try {
        schema = resourceManagement.getDataFormatSchema(dataFormat);
        InputStream schemaInputStream = new ByteArrayInputStream(schema.getBytes());
        XSDHelper.INSTANCE.define(schemaInputStream, null);

        // convert xml string to SDO
        XMLDocument xmlDoc = XMLHelper.INSTANCE.load(data);
        deserializedData = xmlDoc.getRootObject();
      } catch (Exception e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
    }

    return deserializedData;
  }
}
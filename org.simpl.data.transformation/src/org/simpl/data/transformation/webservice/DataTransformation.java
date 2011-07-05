package org.simpl.data.transformation.webservice;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

import org.simpl.core.connector.Connector;
import org.simpl.data.transformation.DataTransformationService;

import commonj.sdo.DataObject;

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
  @SuppressWarnings({ "rawtypes", "unchecked" })
  public String convertTo(@WebParam(name = "serviceImpl") String serviceImpl,
      @WebParam(name = "data") String data,
      @WebParam(name = "connectorImpl") String connectorImpl) {
    DataObject convertedDataObject = null;
    String convertedData = null;

    try {
      DataObject dataObject = (DataObject) this.deserializeData(data);
      Connector connector = (Connector) Class.forName(connectorImpl).newInstance();
      DataTransformationService dataTransformationService = (DataTransformationService) Class
          .forName(serviceImpl).newInstance();

      convertedDataObject = dataTransformationService.convertTo(dataObject, connector);
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
  @SuppressWarnings({ "rawtypes", "unchecked" })
  public String convertFrom(@WebParam(name = "serviceImpl") String serviceImpl,
      @WebParam(name = "data") String data,
      @WebParam(name = "connectorImpl") String connectorImpl) {
    DataObject convertedDataObject = null;
    String convertedData = null;

    try {
      DataObject dataObject = (DataObject) this.deserializeData(data);
      Connector connector = (Connector) Class.forName(connectorImpl).newInstance();
      DataTransformationService dataTransformationService = (DataTransformationService) Class
          .forName(serviceImpl).newInstance();

      convertedDataObject = dataTransformationService.convertFrom(dataObject, connector);
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
  @SuppressWarnings({ "rawtypes", "unchecked" })
  public String convert(@WebParam(name = "serviceImpl") String serviceImpl,
      @WebParam(name = "data") String data,
      @WebParam(name = "connectorImpl") String connectorImpl) {
    DataObject convertedDataObject = null;
    String convertedData = null;

    try {
      DataObject dataObject = (DataObject) this.deserializeData(data);
      Connector connector = (Connector) Class.forName(connectorImpl).newInstance();
      DataTransformationService dataTransformationService = (DataTransformationService) Class
          .forName(serviceImpl).newInstance();

      convertedDataObject = dataTransformationService.convert(dataObject, connector);
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
   * Serializes a java object to a XML encoded object string.
   * 
   * @param object
   * @return serialized object string
   */
  private String serializeData(Object object) {
    ByteArrayOutputStream byteArrayOuputStream = new ByteArrayOutputStream();
    String serializedData = null;

    XMLEncoder encoder = new XMLEncoder(byteArrayOuputStream);
    encoder.writeObject(object);
    encoder.close();

    serializedData = new String(byteArrayOuputStream.toByteArray());

    return serializedData;
  }

  /**
   * Deserializes a XML encoded object string to a java object.
   * 
   * @param data
   * @return deserialized java object
   */
  private Object deserializeData(String data) {
    ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(data.getBytes());
    Object deserializedData = null;

    XMLDecoder decoder = new XMLDecoder(byteArrayInputStream);
    deserializedData = decoder.readObject();
    decoder.close();

    return deserializedData;
  }
}
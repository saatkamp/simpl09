package org.simpl.core.webservices;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

import org.apache.commons.io.IOUtils;
import org.simpl.core.SIMPLCore;
import org.simpl.core.helpers.Parameter;
import org.simpl.core.plugins.datasource.DataSourcePlugin;
import org.simpl.core.services.datasource.DataSource;
import org.simpl.core.services.datasource.auth.Authentication;
import org.simpl.core.services.datasource.exceptions.ConnectionException;

import commonj.sdo.DataObject;
import commonj.sdo.helper.XMLHelper;

/**
 * Web Service to access the data source service.
 * 
 * @author Michael Schneidt <michael.schneidt@arcor.de>
 * @version $Id: Datasource.java 1140 2010-04-19 14:38:52Z
 *          michael.schneidt@arcor.de $<br>
 * @link http://code.google.com/p/simpl09/
 */
@WebService(name = "DatasourceService")
@SOAPBinding(style = SOAPBinding.Style.RPC)
public class Datasource {
  // Output stream to write SDO to XML string
  ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

  @WebMethod(action = "retrieveData")
  public String retrieveData(
      @WebParam(name = "dataSource") DataSource dataSource,
      @WebParam(name = "authentication") Authentication auth,
      @WebParam(name = "statement") String statement)
      throws ConnectionException {
    DataObject dataObject = null;
    String data = null;

    dataObject = SIMPLCore.getInstance().dataSourceService(
        dataSource.getType(), dataSource.getSubType()).retrieveData(dataSource,
        auth, statement);

    try {
      XMLHelper.INSTANCE.save(dataObject, "commonj.sdo", "dataObject",
          outputStream);
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    data = new String(outputStream.toByteArray());

    return data;
  }

  @WebMethod(action = "depositData")
  public boolean depositData(
      @WebParam(name = "dataSource") DataSource dataSource,
      @WebParam(name = "authentication") Authentication auth,
      @WebParam(name = "statement") String statement,
      @WebParam(name = "target") String target) throws ConnectionException {
    boolean success = false;

    success = SIMPLCore.getInstance().dataSourceService(dataSource.getType(),
        dataSource.getSubType()).depositData(dataSource, auth, statement,
        target);

    return success;
  }

  @WebMethod(action = "executeStatement")
  public boolean executeStatement(
      @WebParam(name = "dataSource") DataSource dataSource,
      @WebParam(name = "authentication") Authentication auth,
      @WebParam(name = "statement") String statement)
      throws ConnectionException {
    boolean success = false;

    success = SIMPLCore.getInstance().dataSourceService(dataSource.getType(),
        dataSource.getSubType()).executeStatement(dataSource, auth, statement);

    return success;
  }

  @WebMethod(action = "writeBack")
  public boolean writeBack(
      @WebParam(name = "dataSource") DataSource dataSource,
      @WebParam(name = "authentication") Authentication auth,
      @WebParam(name = "data") String data) throws ConnectionException {
    boolean success = false;
    DataObject dataObject = null;

    dataObject = (DataObject) Parameter.deserialize(data);
    success = SIMPLCore.getInstance().dataSourceService(dataSource.getType(),
        dataSource.getSubType()).writeBack(dataSource, auth, dataObject);

    return success;
  }

  @WebMethod(action = "getMetaData")
  public String getMetaData(@WebParam(name = "datasource") DataSource source,
      @WebParam(name = "authentication") Authentication auth,
      @WebParam(name = "filter") String filter) throws ConnectionException {
    DataObject dataObject = null;
    String metaData = null;

    dataObject = SIMPLCore.getInstance().dataSourceService(source.getType(),
        source.getSubType()).getMetaData(source, auth, filter);

    try {
      XMLHelper.INSTANCE.save(dataObject, "commonj.sdo", "dataObject",
          outputStream);
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    metaData = new String(outputStream.toByteArray());

    return metaData;
  }

  @WebMethod(action = "getMetaDataSchema")
  public String getMetaDataSchema(
      @WebParam(name = "dataSource") DataSource dataSource)
      throws ConnectionException {
    InputStream inputStream = null;
    String metaDataSchema = "";
    StringWriter writer = new StringWriter();

    inputStream = ((DataSourcePlugin) SIMPLCore.getInstance()
        .dataSourceService(dataSource.getType(), dataSource.getSubType()))
        .getMetaDataSchema();

    // convert inputStream to String
    try {
      IOUtils.copy(inputStream, writer);
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    metaDataSchema = writer.toString();

    return metaDataSchema;
  }

  @WebMethod(action = "getDataSourceTypes")
  public String getDataSourceTypes() {
    return Parameter.serialize(SIMPLCore.getInstance().getDataSourceTypes());
  }

  @WebMethod(action = "getDataSourceSubtypes")
  public String getDataSourceSubtypes(String dsType) {
    return Parameter.serialize(SIMPLCore.getInstance().getDataSourceSubtypes(
        dsType));
  }

  @WebMethod(action = "getDataSourceLanguages")
  public String getDataSourceLanguages(String dsSubtype) {
    return Parameter.serialize(SIMPLCore.getInstance().getDataSourceLanguages(
        dsSubtype));
  }

  @WebMethod(action = "getDataFormatTypes")
  public String getDataFormatTypes() {
    return Parameter.serialize(SIMPLCore.getInstance().getDataFormatTypes());
  }
}
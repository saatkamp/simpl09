package org.simpl.core.plugins.connector;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.simpl.core.connector.Connector;

import commonj.sdo.DataObject;
import commonj.sdo.helper.DataFactory;
import commonj.sdo.helper.XSDHelper;

/**
 * <b>Purpose:</b>This abstract class is used to create a connector plug-in for the SIMPL
 * Core, to realize the access to a specific type of data source.<br>
 * <b>Description:</b>A connector plug-in supports just one type of data source (e.g.
 * database, file system), but can support several sub types (e.g. MySQL, DB2) and
 * languages (e.g. SQL, XQuery).<br>
 * The meta data of a data source is made available as a service data object (SDO). The
 * SDO is created from of a XML schema file, that defines the meta data structure in a
 * schema element type and can be set for each plug-in individually. If no schema file is
 * set, the default meta data schema file is used that is defined in the SIMPL Core.
 * Type, sub types, languages, meta data schema and schema element type must be set in the
 * plug-in's constructor.<br>
 * <b>Copyright:</b>Licensed under the Apache License, Version 2.0.
 * http://www.apache.org/licenses/LICENSE-2.0<br>
 * <b>Company:</b>SIMPL<br>
 * 
 * @author schneimi
 * @version $Id: ConnectorPlugin.java 1788 2011-04-10 12:30:51Z michael.schneidt@arcor.de $<br>
 * @link http://code.google.com/p/simpl09/
 */
public abstract class ConnectorPlugin<S, T> implements Connector<S, T> {
  /**
   * Type of the supported data source (database, file system, ...).
   */
  private String type = "Database";

  /**
   * Sub types of the supported data source (DB2, MySQL, ...).
   */
  private List<String> subtypes = new ArrayList<String>();

  /**
   * Languages supported by the data source sub types (SQL, XQuery, ...).
   */
  private HashMap<String, List<String>> languages = new HashMap<String, List<String>>();

  /**
   * Sets the name and location of the data format schema file.
   */
  private String metaDataSchemaFile = "DataSourceMetaData.xsd";

  /**
   * XML schema type of the data source meta data (declared in DEFAULT_META_DATA_SCHEMA).
   */
  private String metaDataSchemaType = "tDatabaseMetaData";

  /**
   * @return The supported data source type.
   */
  public String getType() {
    return this.type;
  }

  /**
   * Sets the supported data source type.
   * 
   * @param dsType
   */
  public void setType(String dsType) {
    this.type = dsType;
  }

  /**
   * @return List of the supported data source sub types.
   */
  public List<String> getSubtypes() {
    return this.subtypes;
  }

  /**
   * Adds a supported data source sub type.
   * 
   * @param dsSubtype
   */
  public void addSubtype(String dsSubtype) {
    if (!this.subtypes.contains(dsSubtype)) {
      this.subtypes.add(dsSubtype);
    }
  }

  /**
   * @return HashMap of the supported languages of sub types.
   */
  public HashMap<String, List<String>> getLanguages() {
    return this.languages;
  }

  /**
   * Adds a supported language of a sub type.
   * 
   * @param dsSubtype
   * @param dsLanguage
   */
  public void addLanguage(String dsSubtype, String dsLanguage) {
    List<String> languageList = null;

    if (languages.containsKey(dsSubtype)) {
      languageList = languages.get(dsSubtype);
    } else {
      languageList = new ArrayList<String>();
    }

    if (!languageList.contains(dsLanguage)) {
      languageList.add(dsLanguage);
      this.languages.put(dsSubtype, languageList);
    }
  }

  /**
   * Sets the name/location of the data format schema file.
   * 
   * @param dfSchemaFile
   */
  public void setMetaDataSchemaFile(String dfSchemaFile) {
    this.metaDataSchemaFile = dfSchemaFile;
  }

  /**
   * @return The meta data schema file as InputStream.
   */
  public InputStream getMetaDataSchemaFile() {
    InputStream inputStream = null;

    // Load the schema from jar file
    inputStream = getClass().getResourceAsStream(metaDataSchemaFile);

    // Load the local schema file
    if (inputStream == null) {
      inputStream = getClass().getResourceAsStream(
          "/org/simpl/core/services/datasource/metadata/" + metaDataSchemaFile);
    }

    if (inputStream == null) {
      System.out.println("The file '" + metaDataSchemaFile + "' could not be found.");
    }

    return inputStream;
  }

  /**
   * Sets the element type for the meta data being used to create the SDO. The types are
   * defined in the data source meta data schema file.
   * 
   * Default available types are tDatabaseMetaData and tFilesystemMetaData.
   * 
   * @param dsMetaDataType
   */
  public void setMetaDataSchemaType(String dsMetaDataType) {
    this.metaDataSchemaType = dsMetaDataType;
  }

  /**
   * @return Empty meta data SDO created from the meta data schema.
   */
  public DataObject getMetaDataSDO() {
    DataObject metaDataObject = null;
    InputStream inputStream = null;

    // Load the local schema file
    inputStream = getClass().getResourceAsStream(metaDataSchemaFile);

    // Load the default schema file
    if (inputStream == null) {
      inputStream = getClass().getResourceAsStream(
          "/org/simpl/core/metadata/" + metaDataSchemaFile);
    }

    if (inputStream == null) {
      System.out.println("The file '" + metaDataSchemaFile + "' could not be found.");
    }

    XSDHelper.INSTANCE.define(inputStream, null);

    try {
      inputStream.close();
    } catch (IOException e) {
      e.printStackTrace();
    }

    metaDataObject = DataFactory.INSTANCE.create(
        "http://org.simpl.core/metadata/DataSourceMetaData",
        this.metaDataSchemaType);

    return metaDataObject;
  }
}
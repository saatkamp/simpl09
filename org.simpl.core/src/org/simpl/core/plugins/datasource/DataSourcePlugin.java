package org.simpl.core.plugins.datasource;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.simpl.core.services.datasource.DataSourceService;

import commonj.sdo.DataObject;
import commonj.sdo.helper.DataFactory;
import commonj.sdo.helper.XSDHelper;

/**
 * <b>Purpose:</b>This abstract class is used to create a data source plugin for
 * the SIMPL Core, to realize the access to a specific type of data source.<br>
 * <b>Description:</b>A data source service plugin supports just one type of
 * data source (e.g. database or file system), but several subtypes (MySQL, DB2)
 * and languages.<br>
 * The meta data of a data source is made available as a service data object
 * (SDO) to offer an independent and extensible data format for exchange. The
 * structure of the SDO is created from of a XML schema file, that defines all
 * data types and can be created for each plugin individually. If no schema file
 * is set, the standard schema file defined in the SIMPL Core is used as
 * default.<br>
 * <b>Copyright:</b> <br>
 * <b>Company:</b>SIMPL<br>
 * 
 * @author hahnml
 * @version $Id: DatasourceServicePlugin.java 904 2010-02-22 11:23:43Z
 *          michael.schneidt@arcor.de $<br>
 * @link http://code.google.com/p/simpl09/
 */
public abstract class DataSourcePlugin implements DataSourceService {
  /**
   * Type of the supporting data source (database, filesystem, ...).
   */
  private String type = "Database";

  /**
   * Subtypes of the supporting data source (DB2, MySQL, ...).
   */
  private List<String> subtypes = new ArrayList<String>();

  /**
   * Languages supporting by the data source subtypes (SQL, XQuery, ...).
   */
  private HashMap<String, List<String>> languages = new HashMap<String, List<String>>();

  /**
   * Sets the name and location of the data format schema file.
   */
  private String metaDataSchemaFile = "DataSourceMetaData.xsd";

  /**
   * XML schema type of the data source meta data (declared in
   * DEFAULT_META_DATA_SCHEMA).
   */
  private String metaDataType = "tDatabaseMetaData";

  /**
   * @return The supporting data source type.
   */
  public String getType() {
    return this.type;
  }

  /**
   * @return List of the supporting data source subtypes.
   */
  public List<String> getSubtypes() {
    return this.subtypes;
  }

  /**
   * @return HashMap of the supporting languages of subtypes.
   */
  public HashMap<String, List<String>> getLanguages() {
    return this.languages;
  }

  /**
   * Sets the supporting data source type.
   * 
   * @param dsType
   */
  public void setType(String dsType) {
    this.type = dsType;
  }

  /**
   * Adds a supported data source subtype.
   * 
   * @param dsSubtype
   */
  public void addSubtype(String dsSubtype) {
    if (!this.subtypes.contains(dsSubtype)) {
      this.subtypes.add(dsSubtype);
    }
  }

  /**
   * Adds a supported language of a subtype.
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
   * Sets the element type for the meta data being used by the plugin. The types
   * are defined in the data source meta data schema file.
   * 
   * @param dsMetaDataType
   */
  public void setMetaDataType(String dsMetaDataType) {
    this.metaDataType = dsMetaDataType;
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
          "/org/simpl/core/services/datasource/metadata/" + metaDataSchemaFile);
    }

    if (inputStream == null) {
      System.out.println("The file '" + metaDataSchemaFile
          + "' could not be found.");
    }

    XSDHelper.INSTANCE.define(inputStream, null);

    try {
      inputStream.close();
    } catch (IOException e) {
      e.printStackTrace();
    }

    metaDataObject = DataFactory.INSTANCE
        .create(
            "http://org.simpl.core/services/datasource/metadata/DataSourceMetaData",
            this.metaDataType);

    return metaDataObject;
  }

  /**
   * @return The meta data schema file as InputStream.
   */
  public InputStream getMetaDataSchema() {
    InputStream inputStream = null;

    // Load the schema from jar file
    inputStream = getClass().getResourceAsStream(metaDataSchemaFile);

    // Load the local schema file
    if (inputStream == null) {
      inputStream = getClass().getResourceAsStream(
          "/org/simpl/core/services/datasource/metadata/" + metaDataSchemaFile);
    }

    if (inputStream == null) {
      System.out.println("The file '" + metaDataSchemaFile
          + "' could not be found.");
    }

    return inputStream;
  }
}
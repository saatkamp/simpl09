package org.simpl.core.datasource.plugins;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.simpl.core.datasource.DatasourceService;

import commonj.sdo.DataObject;
import commonj.sdo.helper.DataFactory;
import commonj.sdo.helper.XSDHelper;

/**
 * <b>Purpose:</b> This abstract class is used to realize datasource service plugins and
 * thus be able to support various datasources. <br>
 * <b>Description:</b> A datasource plugin can support just one type of datasource but
 * several subtypes and languages that must be defined in the plugin's constructor with
 * the available set, get and add methods.<br>
 * The meta data structure of a datasource is made available as a service data object
 * (SDO) to offer an independant and extensible data format for exchange. The structure of
 * the SDO is created out of a XML schema file that defines the data types and can be
 * created for each plugin. If no schema file is created, the standard schema file is used
 * as default.<br>
 * <b>Copyright:</b> <br>
 * <b>Company:</b> SIMPL<br>
 * 
 * @author hahnml
 * @version $Id: DatasourceServicePlugin.java 904 2010-02-22 11:23:43Z
 *          michael.schneidt@arcor.de $<br>
 * @link http://code.google.com/p/simpl09/
 */
public abstract class DatasourceServicePlugin implements DatasourceService {
  /**
   * Name of the meta data schema file.
   */
  private static final String DATASOURCE_META_DATA_SCHEMA_FILE = "DatasourceMetaData.xsd";

  /**
   * Type of the supported datasource (database, filesystem, ...)
   */
  private String datasourceType = "database";

  /**
   * XML schema type of the datasource meta data. (declared in DEFAULT_META_DATA_SCHEMA)
   */
  private String datasourceMetaDataType = "tDatabaseMetaData";

  /**
   * Subtypes of the supported datasource. (DB2, MySQL, ...)
   */
  private List<String> datasourceSubtypes = new ArrayList<String>();

  /**
   * Languages supported by the datasource subtypes. (SQL, XQuery, ...)
   */
  private HashMap<String, List<String>> datasourceLanguages = new HashMap<String, List<String>>();

  /**
   * Returns the supported datasource type.
   * 
   * @return
   */
  public String getDatasourceType() {
    return this.datasourceType;
  }

  /**
   * Returns the supported datasource subtypes.
   * 
   * @return
   */
  public List<String> getDatasourceSubtypes() {
    return this.datasourceSubtypes;
  }

  /**
   * Returns the supported languages of the subtypes.
   * 
   * @return
   */
  public HashMap<String, List<String>> getDatasourceLanguages() {
    return this.datasourceLanguages;
  }

  /**
   * Sets the supported datasource type.
   * 
   * @param dsType
   */
  public void setDatasourceType(String dsType) {
    this.datasourceType = dsType;
  }

  /**
   * Sets the element type for the meta data beeing used by the plugin. The types are
   * defined in the datasource meta data schema file.
   * 
   * @param dsMetaDataType
   */
  public void setDatasourceMetaDataType(String dsMetaDataType) {
    this.datasourceMetaDataType = dsMetaDataType;
  }

  /**
   * Sets the supported datasource subtypes.
   * 
   * @param dsSubtype
   */
  public void addDatasourceSubtype(String dsSubtype) {
    if (!this.datasourceSubtypes.contains(dsSubtype)) {
      this.datasourceSubtypes.add(dsSubtype);
    }
  }

  /**
   * Sets the supported language of a subtype.
   * 
   * @param dsSubtype
   * @param dsLanguage
   */
  public void addDatasourceLanguage(String dsSubtype, String dsLanguage) {
    List<String> languages = null;

    if (datasourceLanguages.containsKey(dsSubtype)) {
      languages = datasourceLanguages.get(dsSubtype);
    } else {
      languages = new ArrayList<String>();
    }

    if (!languages.contains(dsLanguage)) {
      languages.add(dsLanguage);
      this.datasourceLanguages.put(dsSubtype, languages);
    }
  }

  /**
   * Creates an empty meta data object (SDO) from the meta data schema.
   * 
   * @return
   */
  public DataObject createDatasourceMetaDataObject() {
    DataObject metaDataObject = null;
    InputStream inputStream = null;

    // Load the local schema file
    inputStream = getClass().getResourceAsStream(DATASOURCE_META_DATA_SCHEMA_FILE);

    // Load the default schema file
    if (inputStream == null) {
      inputStream = getClass().getResourceAsStream(
          "/org/simpl/core/datasource/metadata/" + DATASOURCE_META_DATA_SCHEMA_FILE);
    }

    if (inputStream == null) {
      System.out.println("The file '" + DATASOURCE_META_DATA_SCHEMA_FILE
          + "' could not be found.");
    }


    XSDHelper.INSTANCE.define(inputStream, null);

    try {
      inputStream.close();
    } catch (IOException e) {
      e.printStackTrace();
    }

    metaDataObject = DataFactory.INSTANCE.create(
        "http://org.simpl.core/datasource/metadata/DatasourceMetaData",
        this.datasourceMetaDataType);

    return metaDataObject;
  }
  
  /**
   * Returns the meta data schema file as InputStream.
   *  
   * @return
   */
  public InputStream getDatasourceMetaDataSchema() {
    InputStream inputStream = null;

    // Load the schema from jar file
    inputStream = getClass().getResourceAsStream(DATASOURCE_META_DATA_SCHEMA_FILE);

    // Load the local schema file
    if (inputStream == null) {
      inputStream = getClass().getResourceAsStream(
          "/org/simpl/core/datasource/metadata/" + DATASOURCE_META_DATA_SCHEMA_FILE);
    }

    if (inputStream == null) {
      System.out.println("The file '" + DATASOURCE_META_DATA_SCHEMA_FILE
          + "' could not be found.");
    }
    
    return inputStream;
  }
}
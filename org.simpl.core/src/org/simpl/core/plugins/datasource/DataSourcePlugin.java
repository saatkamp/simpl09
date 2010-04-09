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
 * <b>Purpose:</b> This abstract class is used to realize data source service plugins and
 * thus be able to support various data sources. <br>
 * <b>Description:</b> A data source plugin can support just one type of data source but
 * several subtypes and languages that must be defined in the plugin's constructor with
 * the available set, get and add methods.<br>
 * The meta data structure of a data source is made available as a service data object
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
public abstract class DataSourcePlugin implements DataSourceService {
  /**
   * Name of the meta data schema file.
   */
  private static final String DATASOURCE_META_DATA_SCHEMA_FILE = "DataSourceMetaData.xsd";

  /**
   * Type of the supporting data source (database, filesystem, ...).
   */
  private String dataSourceType = "Database";

  /**
   * XML schema type of the data source meta data (declared in DEFAULT_META_DATA_SCHEMA).
   */
  private String dataSourceMetaDataType = "tDatabaseMetaData";

  /**
   * Subtypes of the supporting data source (DB2, MySQL, ...).
   */
  private List<String> dataSourceSubtypes = new ArrayList<String>();

  /**
   * Languages supporting by the data source subtypes (SQL, XQuery, ...).
   */
  private HashMap<String, List<String>> dataSourceLanguages = new HashMap<String, List<String>>();

  /**
   * Returns the supporting data source type.
   * 
   * @return
   */
  public String getType() {
    return this.dataSourceType;
  }

  /**
   * Returns the supporting data source subtypes.
   * 
   * @return
   */
  public List<String> getSubtypes() {
    return this.dataSourceSubtypes;
  }

  /**
   * Returns the supporting languages of the subtypes.
   * 
   * @return
   */
  public HashMap<String, List<String>> getLanguages() {
    return this.dataSourceLanguages;
  }

  /**
   * Sets the supporting data source type.
   * 
   * @param dsType
   */
  public void setType(String dsType) {
    this.dataSourceType = dsType;
  }

  /**
   * Sets the element type for the meta data beeing used by the plugin. The types are
   * defined in the data source meta data schema file.
   * 
   * @param dsMetaDataType
   */
  public void setMetaDataType(String dsMetaDataType) {
    this.dataSourceMetaDataType = dsMetaDataType;
  }

  /**
   * Adds a supported data source subtype.
   * 
   * @param dsSubtype
   */
  public void addSubtype(String dsSubtype) {
    if (!this.dataSourceSubtypes.contains(dsSubtype)) {
      this.dataSourceSubtypes.add(dsSubtype);
    }
  }

  /**
   * Adds a supported language of a subtype.
   * 
   * @param dsSubtype
   * @param dsLanguage
   */
  public void addLanguage(String dsSubtype, String dsLanguage) {
    List<String> languages = null;

    if (dataSourceLanguages.containsKey(dsSubtype)) {
      languages = dataSourceLanguages.get(dsSubtype);
    } else {
      languages = new ArrayList<String>();
    }

    if (!languages.contains(dsLanguage)) {
      languages.add(dsLanguage);
      this.dataSourceLanguages.put(dsSubtype, languages);
    }
  }

  /**
   * Creates an empty meta data object (SDO) from the meta data schema.
   * 
   * @return
   */
  public DataObject createMetaDataObject() {
    DataObject metaDataObject = null;
    InputStream inputStream = null;

    // Load the local schema file
    inputStream = getClass().getResourceAsStream(DATASOURCE_META_DATA_SCHEMA_FILE);

    // Load the default schema file
    if (inputStream == null) {
      inputStream = getClass().getResourceAsStream(
          "/org/simpl/core/services/datasource/metadata/"
              + DATASOURCE_META_DATA_SCHEMA_FILE);
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
        "http://org.simpl.core/services/datasource/metadata/DataSourceMetaData",
        this.dataSourceMetaDataType);

    return metaDataObject;
  }

  /**
   * Returns the meta data schema file as InputStream.
   * 
   * @return
   */
  public InputStream getMetaDataSchema() {
    InputStream inputStream = null;

    // Load the schema from jar file
    inputStream = getClass().getResourceAsStream(DATASOURCE_META_DATA_SCHEMA_FILE);

    // Load the local schema file
    if (inputStream == null) {
      inputStream = getClass().getResourceAsStream(
          "/org/simpl/core/services/datasource/metadata/"
              + DATASOURCE_META_DATA_SCHEMA_FILE);
    }

    if (inputStream == null) {
      System.out.println("The file '" + DATASOURCE_META_DATA_SCHEMA_FILE
          + "' could not be found.");
    }

    return inputStream;
  }
}
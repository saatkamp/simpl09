package org.simpl.core.plugins.dataformat.converter;

import java.util.ArrayList;
import java.util.List;

import org.simpl.core.services.dataformat.converter.DataFormatConverterService;

/**
 * <b>Purpose:</b>This abstract class is used to create data format converter
 * plug-ins that can be created for data sources to also understand data formats
 * from other data sources.<br>
 * <b>Description:</b><br>
 * <b>Copyright:</b> <br>
 * <b>Company:</b> SIMPL<br>
 * 
 * @author schneimi<br>
 * @version $Id$<br>
 * @link http://code.google.com/p/simpl09/
 */
public abstract class DataFormatConverterPlugin implements
    DataFormatConverterService {
  /**
   * Type of the supported data source.
   */
  private String type = "";

  /**
   * Subtypes of the supported data source.
   */
  private List<String> subtypes = new ArrayList<String>();

  /**
   * Sets the supported data source type.
   * 
   * @param dsType
   */
  public void setType(String dsType) {
    this.type = dsType;
  }

  /**
   * Adds a supported subtype.
   * 
   * @param dsSubtype
   */
  public void addSubtype(String dsSubtype) {
    if (!this.subtypes.contains(dsSubtype)) {
      this.subtypes.add(dsSubtype);
    }
  }

  /**
   * @return The supported data source type.
   */
  public String getType() {
    return this.type;
  }

  /**
   * @return List of the supported data source subtypes.
   */
  public List<String> getSubtypes() {
    return this.subtypes;
  }
}

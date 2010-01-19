package org.simpl.core.datasource;

import java.util.List;

/**
 * <b>Purpose:</b>
 * <br>
 * <b>Description:</b>
 * <br>
 * <b>Copyright:</b>     <br>
 * <b>Company:</b>       SIMPL<br>
 *
 * @author      Michael Schneidt <michael.schneidt@arcor.de><br>
 * @version     $Id$<br>
 * @link http://code.google.com/p/simpl09/
 */
public interface DatasourceInfo {
  /**
   * Returns the available datasource types.
   * 
   * @return
   */
  public List<String> getDatasourceTypes();

  /**
   * Returns the subtypes for a given datasource type.
   * 
   * @param dsType
   * @return
   */
  public List<String> getDatasourceSubtypes(String dsType);

  /**
   * Returns the languages for a given subtype.
   * 
   * @param dsSubtype
   * @return
   */
  public List<String> getDatasourceLanguages(String dsSubtype);
}

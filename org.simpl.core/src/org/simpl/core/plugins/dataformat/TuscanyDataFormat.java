package org.simpl.core.plugins.dataformat;

import commonj.sdo.DataObject;

/**
 * <b>Purpose:</b>Used to create a SIMPL core data format compatible SDO from a SDO
 * provided by the Tuscany DAS and vice versa.<br>
 * <b>Description:</b>Basically the Tuscany DAS SDO is extended with a data format type
 * attribute, in order to be recognized and to work with the SIMPL core data format and
 * converter system.<br>
 * <b>Copyright:</b> <br>
 * <b>Company:</b> SIMPL<br>
 * 
 * @author schneimi<br>
 * @version $Id$<br>
 * @link http://code.google.com/p/simpl09/
 */
public class TuscanyDataFormat extends DataFormatPlugin<DataObject> {
  public TuscanyDataFormat() {
    this.setType("Tuscany");
    this.setSchemaFile("TuscanyDataFormat.xsd");
    this.setSchemaType("tTuscanyDataFormat");
  }

  /*
   * (non-Javadoc)
   * @see org.simpl.core.services.dataformat.DataFormatService#getSDO(java.lang.Object )
   */
  public DataObject toSDO(DataObject file) {
    DataObject sdo = this.getSDO();

    return sdo;
  }

  /*
   * (non-Javadoc)
   * @see org.simpl.core.services.dataformat.DataFormatService#fromSDO(commonj.sdo
   * .DataObject)
   */
  @Override
  public DataObject fromSDO(DataObject data) {
    return null;
  }
}
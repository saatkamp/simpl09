package org.simpl.core.plugins.dataformat;

import commonj.sdo.DataObject;

/**
 * <b>Purpose:</b>Used to convert between a SIMPL core data format compatible SDO and a
 * SDO provided by the Tuscany DAS.<br>
 * <b>Description:</b>The SDO that returns from the Tuscany DAS needs to be extended by a
 * data format type attribute, in order to be recognized and to work with the SIMPL core
 * data format converter system. The schema defined in the <i>TuscanyDataFormat.xsd</i>
 * declares a <i>formatType</i> attribute and a <i>dataObject</i> element where the
 * original Tuscany SDO gets embedded.<br>
 * <b>Copyright:</b> <br>
 * <b>Company:</b> SIMPL<br>
 * 
 * @author schneimi<br>
 * @version $Id: TuscanyDataFormat.java 1224 2010-04-28 14:17:34Z
 *          michael.schneidt@arcor.de $<br>
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
  public DataObject toSDO(DataObject data) {
    DataObject dataFormatSDO = this.getSDO();

    dataFormatSDO.setDataObject("dataObject", data);

    return dataFormatSDO;
  }

  /*
   * (non-Javadoc)
   * @see org.simpl.core.services.dataformat.DataFormatService#fromSDO(commonj.sdo
   * .DataObject)
   */
  @Override
  public DataObject fromSDO(DataObject data) {
    DataObject tuscanySDO = data.getDataObject("dataObject");

    return tuscanySDO;
  }
}
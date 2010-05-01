package org.simpl.core.plugins.dataformat;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import commonj.sdo.DataObject;
import commonj.sdo.helper.XMLHelper;

/**
 * <b>Purpose:</b>Used to convert between a SIMPL core data format compatible SDO and a
 * SDO provided by the Tuscany DAS.<br>
 * <b>Description:</b>Basically the Tuscany DAS SDO is extended with a data format type
 * attribute, in order to be recognized and to work with the SIMPL core data format and
 * converter system.<br>
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

    // extract the data object content from the xml representation
    String dataXML = XMLHelper.INSTANCE.save(data, "commonj.sdo", "dataObject");
    Pattern MY_PATTERN = Pattern.compile("<sdo:dataObject.*?>(.*?)</sdo:dataObject>",
        Pattern.DOTALL);
    Matcher m = MY_PATTERN.matcher(dataXML);

    if (m.find()) {
      dataXML = m.group(1);
    }

    // add the xml to the data format data object
    dataFormatSDO.setString("dataObject", dataXML);

    return dataFormatSDO;
  }

  /*
   * (non-Javadoc)
   * @see org.simpl.core.services.dataformat.DataFormatService#fromSDO(commonj.sdo
   * .DataObject)
   */
  @Override
  public DataObject fromSDO(DataObject data) {
    DataObject tuscanySDO = null;
    String dataXML = data.getString("dataObject");

    try {
      tuscanySDO = XMLHelper.INSTANCE.load(
          new ByteArrayInputStream(("<dataObject>" + dataXML + "</dataObject>")
              .getBytes())).getRootObject();
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    return tuscanySDO;
  }
}
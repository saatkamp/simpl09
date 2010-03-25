package org.simpl.core.helpers;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

/**
 * <b>Purpose:</b> Helper for the transmission of complex objects as web service
 * parameters.<br>
 * <b>Description:</b> Offers functions to serialize and deserialize objects to and from
 * xml. <br>
 * <b>Copyright:</b> <br>
 * <b>Company:</b> SIMPL<br>
 * 
 * @author schneimi<br>
 * @version $Id$<br>
 * @link http://code.google.com/p/simpl09/
 */
public class Parameter {
  /**
   * Serializes a java object to a BASE64 encoded object string.
   * 
   * @param object
   * @return serialized object string
   */
  public static String serialize(Object object) {
    ByteArrayOutputStream byteArrayOuputStream = new ByteArrayOutputStream();
    String serialized = null;

    XMLEncoder encoder = new XMLEncoder(byteArrayOuputStream);
    encoder.writeObject(object);
    encoder.close();

    serialized = new String(byteArrayOuputStream.toByteArray());

    return serialized;
  }

  /**
   * Deserializes a BASE64 encoded object string to a java object.
   * 
   * @param data
   * @return deserialized java object
   */
  public static Object deserialize(String data) {
    ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(data.getBytes());
    Object deserialized = null;

    XMLDecoder decoder = new XMLDecoder(byteArrayInputStream);
    deserialized = decoder.readObject();
    decoder.close();

    return deserialized;
  }
}
package org.simpl.core.helpers;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

/**
 * <b>Purpose:</b>Helper for the transmission of complex objects as web service
 * parameters.<br>
 * <b>Description:</b>Offers functions to serialize objects to XML and deserialize
 * objects from XML.<br>
 * <b>Copyright:</b>Licensed under the Apache License, Version 2.0.
 * http://www.apache.org/licenses/LICENSE-2.0<br>
 * <b>Company:</b>SIMPL<br>
 * 
 * @author schneimi<br>
 * @version $Id$<br>
 * @link http://code.google.com/p/simpl09/
 */
public class Parameter {
  /**
   * Serializes a java object to a XML encoded object string.
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
   * Deserializes a XML encoded object string to a java object.
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
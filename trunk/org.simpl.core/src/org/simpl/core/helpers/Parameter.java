package org.simpl.core.helpers;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * <b>Purpose:</b> Helper for the transmission of complex objects as web service
 * parameters.<br>
 * <b>Description:</b> Offers functions to serialize and deserialize objects. <br>
 * <b>Copyright:</b> <br>
 * <b>Company:</b> SIMPL<br>
 * 
 * @author Michael Schneidt <michael.schneidt@arcor.de><br>
 * @version $Id$<br>
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
    ObjectOutputStream objectOutputStream = null;
    String serialized = null;

    try {
      objectOutputStream = new ObjectOutputStream(new BufferedOutputStream(
          byteArrayOuputStream));

      objectOutputStream.writeObject(object);
      objectOutputStream.close();

      serialized = new BASE64Encoder().encode(byteArrayOuputStream.toByteArray());
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    return serialized;
  }

  /**
   * Deserializes a BASE64 encoded object string to a java object.
   * 
   * @param data
   * @return deserialized java object
   */
  public static Object deserialize(String data) {
    ByteArrayInputStream byteArrayInputStream = null;
    ObjectInputStream objectInputStream = null;
    Object deserialized = null;

    try {
      byteArrayInputStream = new ByteArrayInputStream(new BASE64Decoder()
          .decodeBuffer(data));
      objectInputStream = new ObjectInputStream(new BufferedInputStream(
          byteArrayInputStream));
      deserialized = objectInputStream.readObject();
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (ClassNotFoundException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    return deserialized;
  }
}

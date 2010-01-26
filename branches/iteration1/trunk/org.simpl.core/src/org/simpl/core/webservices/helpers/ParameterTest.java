package org.simpl.core.webservices.helpers;

import java.util.ArrayList;
import java.util.List;

/**
 * <b>Purpose:</b> <br>
 * <b>Description:</b> <br>
 * <b>Copyright:</b> <br>
 * <b>Company:</b> SIMPL<br>
 * 
 * @author Michael Schneidt <michael.schneidt@arcor.de><br>
 * @version $Id$<br>
 *          Date of creation: 16.01.2010<br>
 *          File: $Source$<br>
 *          Modifier: $Author$<br>
 *          Revision: $Revision$<br>
 *          State: $State$<br>
 */
public class ParameterTest {
  public static void main(String[] args) {
    String serialized = null;
    Object deserialized = null;

    // Object to serialize
    List<String> list = new ArrayList<String>();
    list.add(0, "erster Eintrag");
    list.add(1, "zweiter Eintrag");
    list.add(2, "dritter Eintrag");
    System.out.println(list);

    // Serialization
    serialized = Parameter.serialize(list);
    System.out.println(serialized);

    // Deserialization
    deserialized = Parameter.deserialize(serialized);
    System.out.println(deserialized);
  }
}
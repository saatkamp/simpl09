package org.eclipse.simpl.statementtest.utils;

import java.util.List;

/** TODO
 * <b>Purpose:</b><br>
 * <b>Description:</b><br>
 * <b>Copyright:</b>Licensed under the Apache License, Version 2.0.
 * http://www.apache.org/licenses/LICENSE-2.0<br>
 * <b>Company:</b>SIMPL<br>
 *
 * @author Michael Schneidt <michael.schneidt@arcor.de><br>
 * @version $Id$<br>
 * @link http://code.google.com/p/simpl09/
 */
public class StringUtils {
  /**
   * Concatenates a list of strings with a delimiter.
   * 
   * @param strings
   * @param delimiter
   * @return
   */
  public static String implode(List<String> strings, String delimiter) {
    String implodeString = "";
    String[] array = strings.toArray(new String[strings.size()]);

    for (int i = 0; i < array.length; i++) {
      if (i != 0) {
        implodeString += delimiter;
      }

      implodeString += array[i];
    }

    return implodeString;
  }
}
package org.simpl.resource.management.data;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;

/**
 * <b>Purpose:</b> Provides the different types of strategies for the discovery service.<br>
 * <b>Description:</b> <br>
 * <b>Copyright:</b>Licensed under the Apache License, Version 2.0.
 * http://www.apache.org/licenses/LICENSE-2.0<br>
 * <b>Company:</b> SIMPL<br>
 * 
 * @author schneimi<br>
 * @version $Id: Strategy.java 1488 2010-05-29 10:05:40Z michael.schneidt@arcor.de $<br>
 * @link http://code.google.com/p/simpl09/
 */
@XmlType(name = "strategy")
@XmlEnum(String.class)
public enum Strategy {
  FIRST_FIND;

  public String value() {
    return name();
  }

  public static Strategy fromValue(String v) {
    return Strategy.valueOf(v);
  }
}
package org.simpl.core.services.strategy;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;

/**
 * <b>Purpose:</b> Provides the different types of strategies for the strategy
 * service.<br>
 * <b>Description:</b> <br>
 * <b>Copyright:</b> <br>
 * <b>Company:</b> SIMPL<br>
 * 
 * @author Michael Schneidt <michael.schneidt@arcor.de><br>
 * @version $Id: Strategy.java 1159 2010-04-20 18:19:11Z
 *          michael.schneidt@arcor.de $<br>
 * @link http://code.google.com/p/simpl09/
 */
@XmlType(name = "strategy")
@XmlEnum
public enum Strategy {
  FIRST_FIND;

  public String value() {
    return name();
  }

  public static Strategy fromValue(String v) {
    return valueOf(v);
  }
}
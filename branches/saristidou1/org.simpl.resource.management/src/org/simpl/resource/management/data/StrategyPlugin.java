package org.simpl.resource.management.data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

/**
 * <b>Purpose:</b>Data type for Resource Discovery Strategy Plug-Ins.<br>
 * <b>Description:</b><br>
 * <b>Copyright:</b>Licensed under the Apache License, Version 2.0.
 * http://www.apache.org/licenses/LICENSE-2.0<br>
 * <b>Company:</b>SIMPL<br>
 * 
 * @author hiwi<br>
 * @version $Id$<br>
 * @link http://code.google.com/p/simpl09/
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "strategyPlugin", propOrder = { "id", "name", "implementation" })
public class StrategyPlugin {
  String id;
  String name;
  String implementation;

  /**
   * @return the id
   */
  public String getId() {
    return id;
  }

  /**
   * @param id
   *          the id to set
   */
  public void setId(String id) {
    this.id = id;
  }

  /**
   * @return the name
   */
  public String getName() {
    return name;
  }

  /**
   * @param name
   *          the name to set
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * @return the implementation
   */
  public String getImplementation() {
    return implementation;
  }

  /**
   * @param implementation
   *          the implementation to set
   */
  public void setImplementation(String implementation) {
    this.implementation = implementation;
  }
}
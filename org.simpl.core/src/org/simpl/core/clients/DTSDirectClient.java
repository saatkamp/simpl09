package org.simpl.core.clients;

import org.simpl.core.datatransformation.DataTransformationServiceProvider;

import commonj.sdo.DataObject;

/**
 * <b>Purpose:</b>Provides access to the Data Transformation Services<br>
 * <b>Description:</b>The Data Transformation Services are accessed directly in the class path.<br>
 * <b>Copyright:</b>Licensed under the Apache License, Version 2.0.
 * http://www.apache.org/licenses/LICENSE-2.0<br>
 * <b>Company:</b>SIMPL<br>
 * 
 * @author hiwi<br>
 * @version $Id: SIMPLResourceManagement.java 1718 2010-11-17 18:25:37Z
 *          michael.schneidt@arcor.de $<br>
 * @link http://code.google.com/p/simpl09/
 */
public class DTSDirectClient {
  /**
   * DTSDirectClient singleton instance.
   */
  private static final DTSDirectClient instance = new DTSDirectClient();

  /**
   * Singleton constructor.
   */
  private DTSDirectClient() {

  }

  /**
   * Returns the {@link RDClient} singleton instance.
   * 
   * @return
   */
  public static DTSDirectClient getInstance() {
    return DTSDirectClient.instance;
  }

  public DataObject convert(String fromDataFormat, String toDataFormat, DataObject data, String connectorImpl) {
    return DataTransformationServiceProvider.getInstance(fromDataFormat, toDataFormat).convert(data, connectorImpl);
  }
}
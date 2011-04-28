package org.simpl.core.clients;

import org.simpl.resource.discovery.ResourceDiscovery;
import org.simpl.resource.management.data.DataSource;
import org.simpl.resource.management.data.LateBinding;

/**
 * <b>Purpose:</b>Provides access to the SIMPL Resource Discovery.<br>
 * <b>Description:</b>The Resource Discovery is accessed directly in the class path.<br>
 * <b>Copyright:</b>Licensed under the Apache License, Version 2.0.
 * http://www.apache.org/licenses/LICENSE-2.0<br>
 * <b>Company:</b>SIMPL<br>
 * 
 * @author hiwi<br>
 * @version $Id: SIMPLResourceManagement.java 1718 2010-11-17 18:25:37Z
 *          michael.schneidt@arcor.de $<br>
 * @link http://code.google.com/p/simpl09/
 */
public class RDDirectClient {
  /**
   * The resource discovery web service.
   */
  private ResourceDiscovery resourceDiscovery = new ResourceDiscovery();

  /**
   * RDDirectClient singleton instance.
   */
  private static final RDDirectClient instance = new RDDirectClient();

  /**
   * Singleton constructor.
   */
  private RDDirectClient() {

  }

  /**
   * Returns the {@link RDClient} singleton instance.
   * 
   * @return
   */
  public static RDDirectClient getInstance() {
    return RDDirectClient.instance;
  }

  /**
   * Finds a {@link DataSource} with the given late binding informations.
   * 
   * @param lateBinding
   * @return
   */
  public DataSource findDataSource(LateBinding lateBinding) {
    return resourceDiscovery.findDataSource(lateBinding);
  }
}
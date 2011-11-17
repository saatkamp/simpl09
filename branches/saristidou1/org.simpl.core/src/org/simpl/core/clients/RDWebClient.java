package org.simpl.core.clients;

import java.util.LinkedHashMap;

import org.simpl.core.services.SIMPLCoreAdministrationService;
import org.simpl.resource.discovery.client.ResourceDiscovery;
import org.simpl.resource.discovery.client.ResourceDiscoveryClient;
import org.simpl.resource.management.data.DataSource;
import org.simpl.resource.management.data.LateBinding;

/**
 * <b>Purpose:</b>Provides access to the SIMPL Resource Discovery.<br>
 * <b>Description:</b>Uses the ResourceDiscoveryClient to talk to the Resource Discovery
 * web service interface. The web service address of the Resource Discovery is retrieved
 * from the SIMPLCore embedded Derby database.<br>
 * <b>Copyright:</b>Licensed under the Apache License, Version 2.0.
 * http://www.apache.org/licenses/LICENSE-2.0<br>
 * <b>Company:</b>SIMPL<br>
 * 
 * @author hiwi<br>
 * @version $Id: SIMPLResourceManagement.java 1718 2010-11-17 18:25:37Z
 *          michael.schneidt@arcor.de $<br>
 * @link http://code.google.com/p/simpl09/
 */
public class RDWebClient {
  /**
   * The resource discovery web service.
   */
  private ResourceDiscovery resourceDiscovery = null;

  /**
   * RDWebClient singleton instance.
   */
  private static final RDWebClient instance = new RDWebClient();

  /**
   * Private singleton constructor.
   */
  private RDWebClient() {
    // retrieve the resource discovery address from the internal embedded derby simplDB
    LinkedHashMap<String, String> settings = SIMPLCoreAdministrationService.getInstance()
        .getService().loadSettings("RESOURCEDISCOVERY", "SETTINGS", "lastSaved");
    String resourceDiscoveryAddress = settings.get("ADDRESS");

    if (resourceDiscoveryAddress != null) {
      resourceDiscovery = ResourceDiscoveryClient.getService(resourceDiscoveryAddress);
    }
  }

  /**
   * Returns the {@link RDWebClient} singleton instance.
   * 
   * @return
   */
  public static RDWebClient getInstance() {
    return RDWebClient.instance;
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
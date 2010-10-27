package org.simpl.resource.framework.client;

import org.simpl.resource.framework.client.ResourceFramework;
import org.simpl.resource.framework.client.ResourceFrameworkService;

/**
 * <b>Purpose:</b><br>
 * <b>Description:</b><br>
 * <b>Copyright:</b>Licensed under the Apache License, Version 2.0.
 * http://www.apache.org/licenses/LICENSE-2.0<br>
 * <b>Company:</b>SIMPL<br>
 *
 * @author hiwi<br>
 * @version $Id$<br>
 * @link http://code.google.com/p/simpl09/
 */
public class ResourceFrameworkClient {
  private static ResourceFramework resourceFramework = new ResourceFrameworkService().getResourceFrameworkPort();
  
  private ResourceFrameworkClient() {
    
  }
  
  public static ResourceFramework getInstance() {
    return resourceFramework;    
  }
  
  public static ResourceFramework getInstance(String address) {
    ResourceFrameworkService resourceFrameworkService = new ResourceFrameworkService();
    resourceFrameworkService.setAddress(address);
    
    return resourceFrameworkService.getResourceFrameworkPort();
  }
}
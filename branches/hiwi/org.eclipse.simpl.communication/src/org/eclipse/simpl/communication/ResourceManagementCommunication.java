package org.eclipse.simpl.communication;

/**
 * ResourceManagementService singleton access point to communicate.
 * 
 * @author Michael Schneidt
 */
public class ResourceManagementCommunication {
  private static ResourceManagementService resourceManagement = null;

  public static ResourceManagementService getInstance() {
    resourceManagement = new ResourceManagementService();
    
    return resourceManagement;
  }
}
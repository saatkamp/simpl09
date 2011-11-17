package org.eclipse.simpl.communication;

/**
 * SIMPLCoreService singleton access point to communicate.
 * 
 * @author Michael Hahn <hahnml@studi.informatik.uni-stuttgart.de>
 */
public class SIMPLCoreCommunication {
  private static SIMPLCoreWebService simplCore = null;

  public static SIMPLCoreWebService getInstance() {
    if (simplCore == null) {
      simplCore = new SIMPLCoreWebService();
    }
    return simplCore;
  }
}
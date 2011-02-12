package org.eclipse.simpl.communication;


/**
 * SIMPLCoreService singleton access point to communicate. 
 *
 * @author Michael Hahn <hahnml@studi.informatik.uni-stuttgart.de>
 */
public class SIMPLCoreCommunication {
	
	private static SIMPLCoreService simplCore = null;
	
	public static SIMPLCoreService getInstance(){
		if (simplCore == null){
			simplCore = new SIMPLCoreService();
		}
		return simplCore;
	}
}

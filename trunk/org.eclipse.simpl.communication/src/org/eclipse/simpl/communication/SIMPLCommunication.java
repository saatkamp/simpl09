package org.eclipse.simpl.communication;


/**
 *
 * @author Michael Hahn <hahnml@studi.informatik.uni-stuttgart.de>
 *
 */
public class SIMPLCommunication {
	
	private static SIMPLCore simplCore = null;
	
	public static SIMPLCore getConnection(){
		if (simplCore == null){
			simplCore = new SIMPLCore();
		}
		return simplCore;
	}
}

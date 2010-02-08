package org.eclipse.simpl.communication;


/**
 * Die ganze Klasse ist nur ein Stub f�r die echte Kommunikationsschnittstelle.
 * D.h. diese Klasse simuliert die Funktionalit�t, die zur Kommunikation mit dem
 * SIMPL Core ben�tigt wird.
 * Die hier implementierte Funktionalit�t befindet sich sp�ter als Library auf Eclipse-Seite.
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

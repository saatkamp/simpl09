package org.eclipse.simpl.communication;


/**
 * Die ganze Klasse ist nur ein Stub für die echte Kommunikationsschnittstelle.
 * D.h. diese Klasse simuliert die Funktionalität, die zur Kommunikation mit dem
 * SIMPL Core benötigt wird.
 * Die hier implementierte Funktionalität befindet sich später als Library auf Eclipse-Seite.
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

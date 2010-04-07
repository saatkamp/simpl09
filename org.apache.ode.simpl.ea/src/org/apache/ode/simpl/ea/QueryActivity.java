package org.apache.ode.simpl.ea;

import org.apache.ode.bpel.common.FaultException;
import org.apache.ode.bpel.evt.ScopeEvent;
import org.apache.ode.bpel.rtrep.common.extension.ExtensionContext;
import org.apache.ode.simpl.events.DMEnd;
import org.apache.ode.simpl.events.DMFailure;
import org.apache.ode.simpl.events.DMStarted;
import org.simpl.core.SIMPLCore;
import org.simpl.core.services.datasource.DataSourceService;
import org.simpl.core.services.datasource.exceptions.ConnectionException;
import org.w3c.dom.Element;

public class QueryActivity extends DataManagementActivity {

	@Override
	protected void runSync(ExtensionContext context, Element element)
			throws FaultException {
		// TODO Auto-generated method stub

		ScopeEvent DMStarted = new DMStarted();
		context.getInternalInstance().sendEvent(DMStarted);

		// Laden alle Attributwerte aus der Aktivit�t.
		loadSIMPLAttributes(context, element);

		// Laden das Query-spezifische Attribut "queryTarget"
		String queryTarget = element.getAttribute("queryTarget").toString();

		DataSourceService datasourceService = SIMPLCore.getInstance()
				.dataSourceService(getDsType(), getDsSubType());

		try {
			this.successfullExecution = datasourceService.depositData(getDsAddress(),
					getDsStatement(), queryTarget);
			
			if (this.successfullExecution) {
				ScopeEvent DMEnd = new DMEnd();
				context.getInternalInstance().sendEvent(DMEnd);
			} else {
				//Hier werden alle vorhandenen "wichtigen" Daten dem Event �bergeben
				ScopeEvent DMFailure = new DMFailure(getActivityName() ,getDsAddress(), getDsStatement(), "UNKNOWN");
				context.getInternalInstance().sendEvent(DMFailure);
			}
			
		} catch (ConnectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
//		/*
//		 * Hier werden alle Daten f�r das Auditing in ein SDO gepackt und abgeschickt.
//		 * 
//		 * Mit diesem Vorgehen k�nnen wir ohne Probleme alle f�r SIMPL relevanten Daten
//		 * auslesen und so ein SIMPL Auditing erstellen. Die restlichen Daten, die bei
//		 * der Ausf�hrung von nicht DM-Aktivit�ten anfallen, werden dadurch allerdings
//		 * vorerst nicht ber�cksichtigt. 
//		 */
//		DataObject queryObject = null;
//		//queryObject erzeugen und mit Werten f�llen
//		queryObject = DataFactory.INSTANCE.create(SIMPL_SDO_NS, "tQueryActivitySDO");
//		//Standardattribute die alle DM-SDOs haben
//		queryObject.setString("activityName", getActivityName());
//		queryObject.setString("dsType", getDsType());
//		queryObject.setString("dsKind", getDsSubType());
//		queryObject.setString("dsStatement", getDsStatement());
//		queryObject.setString("dsLanguage", getDsLanguage());
//		queryObject.setString("dsAddress", getDsAddress());
//		//Spezialattribut das nur das QuerySDO hat
//		queryObject.setString("queryTarget", queryTarget);
//		
//		//Hier hinterlegen wir noch, ob die Ausf�hrung der Aktivit�t erfolgreich war
//		queryObject.setBoolean("successfullExecution", this.successfullExecution);
//		
//		//Hier wird ein Containment-SDO erzeugt, dass heisst ein SDO das in das QueryActivitySDO eingebunden ist
//		/* TODO:
//		 * Dies muss noch sowohl im XSD als auch hier in der Implementierung verfeinert und
//		 * um die fehlenden Teile (Scope, Events) erg�nzt werden.
//		 * Auch die Plausibilit�t und Verwendbarkeit der bereits vorhandenen Elemente sollte nochmal �berpr�ft werden
//		 */
//		DataObject processIObject = queryObject.createDataObject("ProcessInstance");
//		processIObject.setLong("processID", context.getProcessId());
//		
//		
//		//SDO "abschicken", d.h. in/auf einer Datenquelle speichern
//		/* TODO:
//		 * Vorerst wird es nur in eine Datei geschrieben. Die komplette Funktionalit�t
//		 * sollte daf�r aber in der SDOSender-Klasse liegen
//		 */
//		SDOSender.getInstance().sendSDO(queryObject, getActivityName());
	}

}

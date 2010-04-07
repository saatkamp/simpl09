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

		// Laden alle Attributwerte aus der Aktivität.
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
				//Hier werden alle vorhandenen "wichtigen" Daten dem Event übergeben
				ScopeEvent DMFailure = new DMFailure(getActivityName() ,getDsAddress(), getDsStatement(), "UNKNOWN");
				context.getInternalInstance().sendEvent(DMFailure);
			}
			
		} catch (ConnectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
//		/*
//		 * Hier werden alle Daten für das Auditing in ein SDO gepackt und abgeschickt.
//		 * 
//		 * Mit diesem Vorgehen können wir ohne Probleme alle für SIMPL relevanten Daten
//		 * auslesen und so ein SIMPL Auditing erstellen. Die restlichen Daten, die bei
//		 * der Ausführung von nicht DM-Aktivitäten anfallen, werden dadurch allerdings
//		 * vorerst nicht berücksichtigt. 
//		 */
//		DataObject queryObject = null;
//		//queryObject erzeugen und mit Werten füllen
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
//		//Hier hinterlegen wir noch, ob die Ausführung der Aktivität erfolgreich war
//		queryObject.setBoolean("successfullExecution", this.successfullExecution);
//		
//		//Hier wird ein Containment-SDO erzeugt, dass heisst ein SDO das in das QueryActivitySDO eingebunden ist
//		/* TODO:
//		 * Dies muss noch sowohl im XSD als auch hier in der Implementierung verfeinert und
//		 * um die fehlenden Teile (Scope, Events) ergänzt werden.
//		 * Auch die Plausibilität und Verwendbarkeit der bereits vorhandenen Elemente sollte nochmal überprüft werden
//		 */
//		DataObject processIObject = queryObject.createDataObject("ProcessInstance");
//		processIObject.setLong("processID", context.getProcessId());
//		
//		
//		//SDO "abschicken", d.h. in/auf einer Datenquelle speichern
//		/* TODO:
//		 * Vorerst wird es nur in eine Datei geschrieben. Die komplette Funktionalität
//		 * sollte dafür aber in der SDOSender-Klasse liegen
//		 */
//		SDOSender.getInstance().sendSDO(queryObject, getActivityName());
	}

}

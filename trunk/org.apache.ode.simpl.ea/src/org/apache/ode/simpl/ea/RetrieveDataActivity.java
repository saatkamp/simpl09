package org.apache.ode.simpl.ea;

import org.apache.ode.bpel.common.FaultException;
import org.apache.ode.bpel.evt.ScopeEvent;
import org.apache.ode.bpel.rtrep.common.extension.ExtensionContext;
import org.apache.ode.bpel.rtrep.v2.OScope.Variable;
import org.apache.ode.simpl.events.DMEnd;
import org.apache.ode.simpl.events.DMFailure;
import org.apache.ode.simpl.events.DMStarted;
import org.simpl.core.SIMPLCore;
import org.simpl.core.datasource.DatasourceService;
import org.simpl.core.datasource.exceptions.ConnectionException;
import org.w3c.dom.Attr;
import org.w3c.dom.Element;

import commonj.sdo.DataObject;

public class RetrieveDataActivity extends DataManagementActivity {

	@Override
	protected void runSync(ExtensionContext context, Element element)
			throws FaultException {
		// TODO Auto-generated method stub

		ScopeEvent DMStarted = new DMStarted();
		context.getInternalInstance().sendEvent(DMStarted);

		// Laden alle Attributwerte aus der Aktivit�t.
		loadSIMPLAttributes(context, element);

		// Laden das RetrieveData-spezifische Attribut "dataVariable"
		Attr dataVarAttr = element.getAttributeNode("dataVariable");
		String dataVariableName = dataVarAttr.getValue();
		
		//TODO: Hier m�ssen wir irgendwie schreibenden Zugriff auf die Variable
		//erhalten und dann die Daten des Queries darin ablegen!
		Variable variable = context.getVisibleVariables().get(dataVariableName);
		
		DatasourceService datasourceService = SIMPLCore.getInstance()
				.datasourceService(getDsType(), getDsSubType());

		try {
			DataObject dataObject = datasourceService.queryData(getDsAddress(), getDsStatement());
			
			if (dataObject == null) {
				ScopeEvent DMFailure = new DMFailure("Wollo is doff");
				context.getInternalInstance().sendEvent(DMFailure);
			} else {
				ScopeEvent DMEnd = new DMEnd();
				context.getInternalInstance().sendEvent(DMEnd);
			}
			
		} catch (ConnectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
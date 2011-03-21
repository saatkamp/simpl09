package org.apache.ode.simpl.ea;

import org.apache.ode.bpel.common.FaultException;
import org.apache.ode.bpel.rtrep.common.extension.ExtensionContext;
import org.simpl.core.SIMPLCore;
import org.simpl.core.services.datasource.DataSource;
import org.simpl.core.services.datasource.DataSourceService;
import org.simpl.core.services.datasource.exceptions.ConnectionException;
import org.w3c.dom.Attr;
import org.w3c.dom.Element;

import commonj.sdo.DataObject;

/**
 * <b>Purpose:</b> <br>
 * <b>Description:</b> <br>
 * <b>Copyright:</b>  Licensed under the Apache License, Version 2.0. http://www.apache.org/licenses/LICENSE-2.0<br>
 * <b>Company:</b> SIMPL<br>
 * 
 * @author Michael Hahn <hahnml@studi.informatik.uni-stuttgart.de> <br>
 * @version $Id$ <br>
 * @link http://code.google.com/p/simpl09/
 *
 */
public class TransferActivity extends DataManagementActivity {

	@Override
	protected void runSync(ExtensionContext context, Element element)
			throws FaultException {
		// TODO Auto-generated method stub

//		ScopeEvent DMStarted = new DMStarted();
//		context.getInternalInstance().sendEvent(DMStarted);

		// Laden alle Attributwerte aus der Aktivit�t.
		loadSIMPLAttributes(context, element);

		// Laden der Transfer-spezifischen Attribute
		Attr targetDsAddressAttr = element.getAttributeNode("targetDsAddress");
		String targetDsAddress = targetDsAddressAttr.getValue();
		
		Attr targetDsContainerAttr = element.getAttributeNode("targetDsContainer");
		String targetDsContainer = targetDsContainerAttr.getValue();

		DataSource dsFrom = getDataSource(getActivityName(), getDsAddress());
		DataSource dsTo = getDataSource(getActivityName(), targetDsAddress);
		
		DataSourceService<DataObject, DataObject> datasourceService = SIMPLCore
				.getInstance().dataSourceService();

		try {
			DataObject dataObject = datasourceService.retrieveData(dsFrom,
					getDsStatement(context));

			if (dataObject == null) {
//				ScopeEvent DMFailure = new DMFailure(
//						"The result of the query is null");
//				context.getInternalInstance().sendEvent(DMFailure);
			} else {
				datasourceService.writeData(dsTo, dataObject, targetDsContainer);

//				ScopeEvent DMEnd = new DMEnd();
//				context.getInternalInstance().sendEvent(DMEnd);
			}

		} catch (ConnectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
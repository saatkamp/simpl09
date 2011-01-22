package org.apache.ode.simpl.ea;

import java.io.StringWriter;

import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.ode.bpel.common.FaultException;
import org.apache.ode.bpel.evt.ActivityFailureEvent;
import org.apache.ode.bpel.rtrep.common.extension.ExtensionContext;
import org.apache.ode.bpel.rtrep.v2.OScope.Variable;
import org.simpl.core.SIMPLCore;
import org.simpl.core.services.datasource.DataSource;
import org.simpl.core.services.datasource.DataSourceService;
import org.w3c.dom.Attr;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import commonj.sdo.DataObject;
import commonj.sdo.helper.XMLDocument;
import commonj.sdo.helper.XMLHelper;
import commonj.sdo.helper.XSDHelper;

public class WriteDataBackActivity extends DataManagementActivity {
  @Override
  protected void runSync(ExtensionContext context, Element element) throws FaultException {
    // ScopeEvent DMStarted = new DMStarted();
    // context.getInternalInstance().sendEvent(DMStarted);

    // Load all attribute values from the activity.
    loadSIMPLAttributes(context, element);

    // Load all specific attribute values from the WriteDataBackActivity.
    String writeTarget = element.getAttribute("writeTarget");
    Attr dataVarAttr = element.getAttributeNode("dataVariable");
    String dataVariableName = dataVarAttr.getValue();
    
    DataSource ds = getDataSource(getActivityName(), getDsAddress());

    DataSourceService<DataObject, DataObject> datasourceService = SIMPLCore.getInstance()
        .dataSourceService();

    try {
      Node value = null;
      Variable variable = context.getVisibleVariables().get(dataVariableName);

      if (variable != null) {
        value = context.readVariable(variable);
      }

      // get variable content as xml string
      Source source = new DOMSource(value);
      StringWriter stringWriter = new StringWriter();
      Result result = new StreamResult(stringWriter);
      TransformerFactory factory = TransformerFactory.newInstance();
      Transformer transformer = factory.newTransformer();
      transformer.transform(source, result);
      String xmlValue = stringWriter.getBuffer().toString();

      // retrieve the schema file
      XSDHelper.INSTANCE.define(SIMPLCore.getInstance().dataSourceService()
          .getDataFormatSchemaFile(ds), null);

      // convert xml string to SDO
      XMLDocument xmlDoc = XMLHelper.INSTANCE.load(xmlValue);
      DataObject sdo = xmlDoc.getRootObject();

      // write data back
      if (writeTarget != null) {
        this.successfullExecution = datasourceService.writeData(ds, sdo, writeTarget);
      } else {
        this.successfullExecution = datasourceService.writeBack(ds, sdo);
      }

      if (!this.successfullExecution) {
        ActivityFailureEvent event = new ActivityFailureEvent();
        event.setActivityName(context.getActivityName());
        event.setActivityId(context.getOActivity().getId());
        event.setActivityType("WriteDataBackActivity");
        event.setScopeName(context.getOActivity().getParent().name);
        event.setScopeId(0L);
        event.setScopeDeclerationId(context.getOActivity().getParent()
            .getId());
        context.getInternalInstance().sendEvent(event);
        context.completeWithFault(new Throwable("SIMPL Exception"));
      }
    } catch (Exception e) {
      ActivityFailureEvent event = new ActivityFailureEvent(e.toString());
      event.setActivityName(context.getActivityName());
      event.setActivityId(context.getOActivity().getId());
      event.setActivityType("WriteDataBackActivity");
      event.setScopeName(context.getOActivity().getParent().name);
      event.setScopeId(0L);
      event.setScopeDeclerationId(context.getOActivity().getParent().getId());

      context.getInternalInstance().sendEvent(event);
    }
  }

}

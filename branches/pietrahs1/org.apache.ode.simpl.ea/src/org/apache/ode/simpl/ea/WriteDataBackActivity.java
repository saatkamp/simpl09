package org.apache.ode.simpl.ea;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
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
import org.apache.ode.simpl.ea.util.DataSourceUtils;
import org.apache.ode.simpl.ea.util.StatementUtils;
import org.simpl.core.SIMPLCoreInterface;
import org.simpl.core.services.SIMPLCoreService;
import org.simpl.resource.management.ResourceManagement;
import org.simpl.resource.management.data.DataSource;
import org.simpl.resource.management.data.LateBinding;
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
    String targetContainer = StatementUtils.processStatement(context, element.getAttribute("targetContainer"));
    Attr dataVarAttr = element.getAttributeNode("dataVariable");
    String dataVariableName = dataVarAttr.getValue();

    DataSource ds = DataSourceUtils.getDataSource(context, getDataResource());
    LateBinding lb = DataSourceUtils.getLateBinding(context, getDataResource());

    SIMPLCoreInterface simplCoreService = SIMPLCoreService.getInstance().getService();

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

      // retrieve workflow data format of the data  
      String workflowDataFormat = xmlValue.substring(xmlValue.indexOf("dataFormat=\"") + 12, xmlValue.indexOf("\"", xmlValue.indexOf("dataFormat=\"") + 12));

      // retrieve the schema file and define it for SDO
      ResourceManagement rm = new ResourceManagement();
      String schema = rm.getDataFormatSchema(workflowDataFormat);
      InputStream schemaInputStream = new ByteArrayInputStream(schema.getBytes());
      XSDHelper.INSTANCE.define(schemaInputStream, null);

      // convert xml string to SDO
      XMLDocument xmlDoc = XMLHelper.INSTANCE.load(xmlValue);
      DataObject sdo = xmlDoc.getRootObject();

      // write data back
      this.successfulExecution = simplCoreService.writeDataBack(ds, sdo, targetContainer, lb);

      if (!this.successfulExecution) {
        ActivityFailureEvent event = new ActivityFailureEvent();
        event.setActivityName(context.getActivityName());
        event.setActivityId(context.getOActivity().getId());
        event.setActivityType("WriteDataBackActivity");
        event.setScopeName(context.getOActivity().getParent().name);
        event.setScopeId(0L);
        event.setScopeDeclerationId(context.getOActivity().getParent().getId());
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

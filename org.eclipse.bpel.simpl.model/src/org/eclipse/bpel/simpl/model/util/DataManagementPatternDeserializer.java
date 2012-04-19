/**
 * <b>Purpose:</b> This class implements the {@link BPELActivityDeserializer} interface for the deserialization
 * of all SIMPL Patterm. <br>
 * <b>Description:</b> Provides the deserialization of all DataManagement patterns.<br>
 * <b>Copyright:</b> Licensed under the Apache License, Version 2.0. http://www.apache.org/licenses/LICENSE-2.0<br>
 * <b>Company:</b> SIMPL<br>
 *
 */
package org.eclipse.bpel.simpl.model.util;

import java.util.Map;

import javax.wsdl.extensions.ExtensionRegistry;
import javax.xml.namespace.QName;

import org.eclipse.bpel.model.Activity;
import org.eclipse.bpel.model.Process;
import org.eclipse.bpel.model.extensions.BPELActivityDeserializer;
import org.eclipse.bpel.model.resource.BPELReader;
import org.eclipse.bpel.simpl.model.ContainerToContainerPattern;
import org.eclipse.bpel.simpl.model.DataFormatConversionPattern;
import org.eclipse.bpel.simpl.model.DataIterationPattern;
import org.eclipse.bpel.simpl.model.ModelFactory;
import org.eclipse.bpel.simpl.model.ModelPackage;
import org.eclipse.emf.common.util.URI;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * The Class DataManagementPattermDeserializer.
 * 
 */
public class DataManagementPatternDeserializer implements
    BPELActivityDeserializer {

  /*
   * (non-Javadoc)
   * 
   * @see org.eclipse.bpel.model.extensions.BPELActivityDeserializer#unmarshall
   * (javax.xml.namespace.QName, org.w3c.dom.Node,
   * org.eclipse.bpel.model.Process, java.util.Map,
   * javax.wsdl.extensions.ExtensionRegistry, org.eclipse.emf.common.util.URI,
   * org.eclipse.bpel.model.resource.BPELReader)
   */
  @SuppressWarnings("rawtypes")
  @Override
  public Activity unmarshall(QName elementType, Node node, Process process,
      Map nsMap, ExtensionRegistry extReg, URI uri, BPELReader bpelReader) {

    /*
     * ContainerToContainerPattern
     */
    if (DataManagementConstants.ND_CONTAINER_TO_CONTAINER_PATTERN
        .equals(elementType.getLocalPart())) {
      Element containerToContainerPatternElement = (Element) node;

      // create a ContainerToContainerPattern model object
      ContainerToContainerPattern pattern = ModelFactory.eINSTANCE
          .createContainerToContainerPattern();

      // attach the DOM node to our new pattern
      pattern.setElement(containerToContainerPatternElement);

      // handle the ContainerToContainerPattern attributes
      String attSourceContainer = ModelPackage.eINSTANCE
          .getContainerToContainerPattern_SourceContainer().getName();
      String attTargetContainer = ModelPackage.eINSTANCE
          .getContainerToContainerPattern_TargetContainer().getName();

      if (((Element) node).getAttribute(attSourceContainer) != null) {
        pattern.setSourceContainer(((Element) node)
            .getAttribute(attSourceContainer));
      }
      if (((Element) node).getAttribute(attTargetContainer) != null) {
        pattern.setTargetContainer(((Element) node)
            .getAttribute(attTargetContainer));
      }

      return pattern;
    }
    /*
     * DataFormatConversionPattern
     */
    if (DataManagementConstants.ND_DATA_FORMAT_CONVERSION_PATTERN
        .equals(elementType.getLocalPart())) {
      Element dataFormatConversionPatternElement = (Element) node;

      // create a ContainerToContainerPattern model object
      DataFormatConversionPattern pattern = ModelFactory.eINSTANCE
          .createDataFormatConversionPattern();

      // attach the DOM node to our new pattern
      pattern.setElement(dataFormatConversionPatternElement);

      // handle the ContainerToContainerPattern attributes
      String attSourceContainer = ModelPackage.eINSTANCE
          .getDataFormatConversionPattern_SourceContainer().getName();
      String attTargetContainer = ModelPackage.eINSTANCE
          .getDataFormatConversionPattern_TargetContainer().getName();

      if (((Element) node).getAttribute(attSourceContainer) != null) {
        pattern.setSourceContainer(((Element) node)
            .getAttribute(attSourceContainer));
      }
      if (((Element) node).getAttribute(attTargetContainer) != null) {
        pattern.setTargetContainer(((Element) node)
            .getAttribute(attTargetContainer));
      }

      return pattern;
    }
    /*
     * DataIterationPattern
     */
    if (DataManagementConstants.ND_DATA_ITERATION_PATTERN.equals(elementType
        .getLocalPart())) {
      Element dataIterationPatternElement = (Element) node;

      // create a ContainerToContainerPattern model object
      DataIterationPattern pattern = ModelFactory.eINSTANCE
          .createDataIterationPattern();

      // attach the DOM node to our new pattern
      pattern.setElement(dataIterationPatternElement);

      // handle the ContainerToContainerPattern attributes
      String attContainerReferenceList = ModelPackage.eINSTANCE
          .getDataIterationPattern_ContainerReferenceList().getName();
      String attCurrentContainer = ModelPackage.eINSTANCE
          .getDataIterationPattern_CurrentContainer().getName();

      if (((Element) node).getAttribute(attContainerReferenceList) != null) {
        pattern.setContainerReferenceList(((Element) node)
            .getAttribute(attContainerReferenceList));
      }
      if (((Element) node).getAttribute(attCurrentContainer) != null) {
        pattern.setCurrentContainer(((Element) node)
            .getAttribute(attCurrentContainer));
      }

      NodeList childElements = dataIterationPatternElement.getChildNodes();
      Element activityElement = null;
      if (childElements != null && childElements.getLength() > 0) {
        for (int i = 0; i < childElements.getLength(); i++) {

          // the only element node is the child activity
          if ((childElements.item(i).getNodeType() == Node.ELEMENT_NODE)) {
            activityElement = (Element) childElements.item(i);
            Activity child = bpelReader.xml2Activity(activityElement);
            if (child != null) {
              pattern.setActivity(child);
            }
          }
        }
      }

      return pattern;
    }

    System.out.println("Cannot handle this kind of element");
    return null;
  }
}

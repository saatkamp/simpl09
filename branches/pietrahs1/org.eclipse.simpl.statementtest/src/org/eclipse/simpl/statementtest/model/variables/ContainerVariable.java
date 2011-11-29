package org.eclipse.simpl.statementtest.model.variables;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import org.eclipse.bpel.model.Variable;
import org.eclipse.xsd.XSDElementDeclaration;
import org.eclipse.xsd.XSDTypeDefinition;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.NodeList;

/**
 * <b>Purpose: Model of a SIMPL container variable (simpl:containerReference).</b> <br>
 * <b>Description:</b>Container variables consist of a sequence of elements and a string
 * pattern attribute that describes how the elements are concatenated for the use in a
 * statement.<br>
 * <b>Copyright:</b>Licensed under the Apache License, Version 2.0.
 * http://www.apache.org/licenses/LICENSE-2.0<br>
 * <b>Company:</b><br>
 * 
 * @author Michael Schneidt <michael.schneidt@arcor.de><br>
 * @version $Id: ContainerVariable.java 72 2010-07-23 20:02:14Z hiwi $<br>
 * @link http://code.google.com/p/simpl09/
 */
public class ContainerVariable extends AbstractVariable {
  /**
   * List of container variable elements.
   */
  LinkedList<ContainerVariableElement> elements = new LinkedList<ContainerVariableElement>();

  /**
   * Concatenation string pattern.
   */
  String stringPattern = "schema.table";

  /**
   * The constructor reads the elements and the string pattern from the variable.
   */
  public ContainerVariable(Variable variable) {
    super(variable);

    XSDElementDeclaration xsdElement = variable.getXSDElement();
    XSDTypeDefinition xsdType = variable.getType();
    
    NodeList typeNodes = null;

    // use element or type
    if (xsdElement != null) {
      this.type = xsdElement.getQName();
      xsdType = xsdElement.getTypeDefinition();
      typeNodes = xsdType.getElement().getChildNodes();
    } else if (xsdType != null) { 
      this.type = xsdType.getQName(); 
      typeNodes = xsdType.getElement().getChildNodes();
    } else {
      System.out.println("Container variable XML schema (XSD) not found.");
      return;
    }

    // read string pattern attribute
    for (int i = 0; i < typeNodes.getLength(); i++) {
      if (typeNodes.item(i).getLocalName() != null
          && typeNodes.item(i).getLocalName().equals("attribute")) {
        NamedNodeMap attributes = typeNodes.item(i).getAttributes();

        if (attributes.getNamedItem("name").getNodeValue().equals("stringPattern")) {
          this.stringPattern = attributes.getNamedItem("fixed").getNodeValue();
        }

        break;
      }
    }

    NodeList variableSchemaNodes = xsdType.getComplexType().getElement().getChildNodes();
    NamedNodeMap attributes = null;
    ContainerVariableElement element = null;

    // read container elements
    for (int i = 0; i < variableSchemaNodes.getLength(); i++) {
      element = new ContainerVariableElement();

      if (variableSchemaNodes.item(i).hasAttributes()) {
        attributes = variableSchemaNodes.item(i).getAttributes();

        element.setName(attributes.getNamedItem("name").getNodeValue());
        element.setType(attributes.getNamedItem("type").getNodeValue()
            .replace("xsd:", ""));

        if (attributes.getNamedItem("minOccurs").getNodeValue().equals("1")) {
          element.setRequired(true);
        } else {
          element.setRequired(false);
        }

        elements.add(element);
      }
    }
  }

  /**
   * @return List of container variable elements.
   */
  public List<ContainerVariableElement> getElements() {
    return this.elements;
  }

  /**
   * String representation described by the pattern.
   * 
   * Because the pattern elements are replaced or removed gradually, the positions of the
   * remaining elements are updated with each step.
   */
  @Override
  public String toString() {
    String pattern = this.stringPattern;
    HashMap<Integer, ContainerVariableElement> positionedElements = new HashMap<Integer, ContainerVariableElement>();
    ContainerVariableElement positionedElement = null;
    ContainerVariableElement positionedElementPreceding = null;
    List<Integer> positions = new ArrayList<Integer>();
    boolean emptyValue = false;

    // find the positions of the elements in the pattern
    positions = this.findPositions(positionedElements, pattern);

    for (int i = positions.size() - 1; i >= 0; i--) {
      positionedElement = positionedElements.get(positions.get(i));
      emptyValue = positionedElement.getValue() == null
          || positionedElement.getValue().equals("");

      // remove optional elements that are empty from the pattern
      if (emptyValue) {
        if (i == 0) {
          // if first element, remove name including right delimiter
          pattern = pattern.substring(positions.get(i + 1), pattern.length());
        } else {
          positionedElementPreceding = positionedElements.get(positions.get(i - 1));

          // remove element including left delimiter
          String remove = pattern.substring(
              (positions.get(i - 1) + positionedElementPreceding.getName().length()),
              (positions.get(i) + positionedElement.getName().length()));

          pattern = pattern.replace(remove, "");
        }
      }
    }

    // replace element names with their values
    for (Integer position : positionedElements.keySet()) {
      pattern = pattern.replaceAll(positionedElements.get(position).getName(),
          positionedElements.get(position).getValue());
    }

    return pattern;
  }

  /**
   * Cycles through the elements of a variable and maps the elements to their positions.
   * 
   * @param positionedElements
   * @param pattern
   * @return
   */
  private List<Integer> findPositions(
      HashMap<Integer, ContainerVariableElement> positionedElements, String pattern) {
    List<Integer> positions = new ArrayList<Integer>();

    // clear previous positioned elements
    positionedElements.clear();

    // find the positions of the elements in the pattern
    for (ContainerVariableElement element : this.elements) {
      if (pattern.indexOf(element.getName()) > -1) {
        positionedElements.put(pattern.indexOf(element.getName()), element);
        positions.add(pattern.indexOf(element.getName()));
      }
    }

    // sort the positions
    Collections.sort(positions);

    return positions;
  }
}
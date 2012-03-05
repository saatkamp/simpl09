package org.simpl.core.resolver;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.simpl.core.clients.RMClient;
import org.simpl.resource.management.data.DataContainer;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

/**
 * <b>Purpose:</b>A resolver to solve data container references.<br>
 * <b>Description:</b>Data container references consists of a sequence of
 * elements and a string pattern attribute that describes how the elements are
 * concatenated for the use in a statement.<br>
 * <b>Copyright:</b>Licensed under the Apache License, Version 2.0.
 * http://www.apache.org/licenses/LICENSE-2.0<br>
 * <b>Company:</b>SIMPL<br>
 * 
 * @link http://code.google.com/p/simpl09/
 */
public class DataContainerReferencesResolver {
  /**
   * Concatenation string pattern.
   */
  private String stringPattern;
  /**
   * List of the elements.
   */
  private LinkedList<SchemaElement> elements = new LinkedList<SchemaElement>();

  /**
   * The constructor reads the elements and the string pattern from the
   * reference type.
   */
  public DataContainerReferencesResolver(String schema) {
    DocumentBuilder documentBuilder = null;
    Document document = null;
    NodeList typeNodes = null;

    DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory
        .newInstance();

    try {
      documentBuilder = docBuilderFactory.newDocumentBuilder();
      document = documentBuilder.parse(new InputSource(new StringReader(schema
          .replaceAll(">\\s*<", "><"))));
      typeNodes = document.getDocumentElement().getChildNodes();
    } catch (ParserConfigurationException e) {
      e.printStackTrace();
    } catch (SAXException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }

    rekursiveNodeList(typeNodes);
  }

  /**
   * Check to see, if there are references and to solve them if necessary. 
   * 
   * @param statement
   * @return
   */
  public String parseStatement(String statement) {
    String resultStatement = statement;

    // Solve all references
    Pattern pattern = Pattern.compile("\\[[^\\[\\]#]+\\]");
    Matcher matcher = pattern.matcher(statement);
    while (matcher.find()) {
      String match = matcher.group();
      resultStatement = resultStatement.replace(match,
          resolveReference(match.replaceAll("[\\[\\]]", "")));
    }

    return resultStatement;
  }

  /**
   * Solves references.
   * 
   * @param match
   * @return
   */
  public String resolveReference(String match) {
    // check to see, if there is a reference via a logical name
    if (match.contains("logicalIdentifier")) {
      String elementName = "logicalIdentifier";
      int valueStart = match.indexOf("<" + elementName + ">")
          + elementName.length() + 2;
      int valueStop = match.indexOf("</" + elementName + ">", valueStart);
      if (valueStart > -1 && valueStop > -1) {
        String name = match.substring(valueStart, valueStop);
        DataContainer dataContainer = RMClient.getInstance()
            .getDataContainerByName(name);
        if (dataContainer != null) {
          match = dataContainer.getLocalIdentifier();
        }
      }
    }
    for (SchemaElement variableElement : elements) {
      // extract and set the value
      int valueStart = match.indexOf("<" + variableElement.getName() + ">")
          + variableElement.getName().length() + 2;
      int valueStop = match.indexOf("</" + variableElement.getName() + ">",
          valueStart);
      if (valueStart > -1 && valueStop > -1) {
        String value = match.substring(valueStart, valueStop);
        variableElement.setValue(value);
      } else {
        // reset
        variableElement.setValue("");
      }
    }
    return toString(stringPattern);
  }

  /**
   * String representation described by the pattern.
   * 
   * Because the pattern elements are replaced or removed gradually, the
   * positions of the remaining elements are updated with each step.
   */
  public String toString(String stringPattern) {
    String pattern = stringPattern;
    HashMap<Integer, SchemaElement> positionedElements = new HashMap<Integer, SchemaElement>();
    SchemaElement positionedElement = null;
    SchemaElement positionedElementPreceding = null;
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
          positionedElementPreceding = positionedElements.get(positions
              .get(i - 1));

          // remove element including left delimiter
          String remove = pattern.substring(
              (positions.get(i - 1) + positionedElementPreceding.getName()
                  .length()), (positions.get(i) + positionedElement.getName()
                  .length()));

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
   * Cycles through the elements of a variable and maps the elements to their
   * positions.
   * 
   * @param positionedElements
   * @param pattern
   * @return
   */
  private List<Integer> findPositions(
      HashMap<Integer, SchemaElement> positionedElements, String pattern) {
    List<Integer> positions = new ArrayList<Integer>();

    // clear previous positioned elements
    positionedElements.clear();

    // find the positions of the elements in the pattern
    for (SchemaElement element : this.elements) {
      if (pattern.indexOf(element.getName()) > -1) {
        positionedElements.put(pattern.indexOf(element.getName()), element);
        positions.add(pattern.indexOf(element.getName()));
      }
    }

    // sort the positions
    Collections.sort(positions);

    return positions;
  }

  /**
   * Cycles through the elements of the type definition and puts the elements
   * into a list.
   * 
   * @param nodeList
   */
  private void rekursiveNodeList(NodeList nodeList) {
    for (int i = 0; i < nodeList.getLength(); i++) {
      if (nodeList.item(i).hasChildNodes()) {
        rekursiveNodeList(nodeList.item(i).getChildNodes());
      } else {
        NamedNodeMap attributes = nodeList.item(i).getAttributes();
        if (attributes != null && attributes.getNamedItem("name") != null) {
          if (attributes.getNamedItem("name").getNodeValue()
              .equals("stringPattern")) {
            stringPattern = attributes.getNamedItem("fixed").getNodeValue();
          } else if (attributes.getNamedItem("type") != null) {
            SchemaElement element = new SchemaElement();
            element.setName(attributes.getNamedItem("name").getNodeValue());
            element.setType(attributes.getNamedItem("type").getNodeValue()
                .replace("xsd:", ""));
            if (attributes.getNamedItem("minOccurs") != null
                && attributes.getNamedItem("minOccurs").getNodeValue()
                    .equals("1")) {
              element.setRequired(true);
            } else {
              element.setRequired(false);
            }
            elements.add(element);
          }
        }
      }
    }
  }
}

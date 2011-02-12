package org.eclipse.simpl.statementtest.utils;

import java.io.IOException;
import java.io.StringReader;
import java.util.HashMap;
import java.util.List;

import org.eclipse.simpl.communication.ResourceManagementCommunication;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.simpl.resource.management.client.Exception_Exception;
import org.simpl.resource.management.client.ResourceManagement;
import org.simpl.resource.management.client.StringList;
import org.xml.sax.InputSource;

/**
 * <b>Purpose:</b>Helps to differentiate the different issues of the statements in an
 * IssueActivity.<br>
 * <b>Description:</b>Retrieves the languages statement descriptions from the Resource
 * Management and offers them in a HashMap. The HashMap contains issues along with regex
 * predicates that are used to identify the issue of a statement.<br>
 * <b>Copyright:</b>Licensed under the Apache License, Version 2.0.
 * http://www.apache.org/licenses/LICENSE-2.0<br>
 * <b>Company:</b>SIMPL<br>
 * 
 * @author hiwi<br>
 * @version $Id$<br>
 * @link http://code.google.com/p/simpl09/
 */
public class IssueRecognition {
  // Singleton instance
  private static IssueRecognition INSTANCE = null;

  // HashMap <language, <issue, predicate>>
  private HashMap<String, HashMap<String, String>> languagesStatementDescription = new HashMap<String, HashMap<String, String>>();

  @SuppressWarnings("unchecked")
  private IssueRecognition() {
    ResourceManagement resourceManagement = ResourceManagementCommunication.getInstance().getService();
    StringList languages = null;
    String statementDescription = null;

    Document statementDescriptionXML = null;
    Element root = null;
    List<Element> statements = null;
    String issue = null;
    String predicate = null;
    HashMap<String, String> issuePredicateMap = null;
    SAXBuilder saxBuilder = new SAXBuilder();

    try {
      languages = resourceManagement.getAllLanguages();

      for (String language : languages.getItems()) {
        issuePredicateMap = new HashMap<String, String>();

        statementDescription = resourceManagement
            .getLanguageStatementDescription(language);

        // parse the statement description (XML)
        statementDescriptionXML = saxBuilder.build(new InputSource(new StringReader(
            statementDescription)));

        root = statementDescriptionXML.getRootElement();
        statements = root.getChildren("statement", root.getNamespace());

        for (Element statement : statements) {
          issue = statement.getAttribute("name").getValue();
          predicate = statement.getChild("predicate", statement.getNamespace())
              .getValue();

          issuePredicateMap.put(issue, predicate);
        }

        languagesStatementDescription.put(language, issuePredicateMap);
      }
    } catch (Exception_Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (JDOMException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

  public static IssueRecognition getInstance() {
    if (INSTANCE == null) {
      INSTANCE = new IssueRecognition();
    }

    return INSTANCE;
  }

  /**
   * Returns the recognized issue for a given language and statement.
   * 
   * @param language
   * @param statement
   * @return
   */
  public String recognizeIssue(String language, String statement) {
    String issue = null;
    String predicate = null;
    HashMap<String, String> issues = languagesStatementDescription.get(language);

    if (languagesStatementDescription.containsKey(language)) {
      for (String tmpIssue : issues.keySet()) {
        predicate = issues.get(tmpIssue);

        // try to match statement against regex predicate
        if (statement.matches(predicate)) {
          issue = tmpIssue;
          break;
        }
      }
    }

    return issue;
  }
}
package org.apache.ode.simpl.ea.util;

import java.io.StringWriter;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.log4j.Logger;
import org.apache.ode.bpel.common.FaultException;
import org.apache.ode.bpel.rtrep.common.extension.ExtensionContext;
import org.apache.ode.bpel.rtrep.v2.OScope.Variable;
import org.w3c.dom.Node;

/**
 * <b>Purpose:</b> <br>
 * <b>Description:</b> <br>
 * <b>Copyright:</b> Licensed under the Apache License, Version 2.0.
 * http://www.apache.org/licenses/LICENSE-2.0<br>
 * <b>Company:</b> SIMPL<br>
 * 
 * @author Michael Hahn <hahnml@studi.informatik.uni-stuttgart.de> <br>
 * @version $Id$ <br>
 * @link http://code.google.com/p/simpl09/
 * 
 */
public class StatementUtils {

	static Logger logger = Logger.getLogger(StatementUtils.class);

	/**
	 * Processes a given statement and resolves the inserted BPEL variables.
	 * 
	 * @param context
	 * @param statement
	 * @return The statement with the values of the inserted BPEL variables.
	 */
	public static String processStatement(ExtensionContext context,
			String statement) {
		String resultStatement = "";

		if (statement.contains("[") || statement.contains("#")) {
			resultStatement = parseStatement(context, statement);
			if (logger.isDebugEnabled()) {
				logger.debug("Incoming statement: " + statement);
			}
		} else {
			resultStatement = statement;
		}

		if (logger.isDebugEnabled()) {
			logger.debug("Result statement: " + resultStatement);
		}
		return resultStatement;
	}

	/**
	 * @param context
	 * @param statement
	 * @return
	 */
	private static String parseStatement(ExtensionContext context,
			String statement) {
		String resultStatement = statement;

		Map<String, Variable> variables = null;
		try {
			variables = context.getVisibleVariables();
		} catch (FaultException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (variables != null) {

			// Solve all complex variable elements
			Pattern pattern = Pattern.compile("\\[[^\\[\\]#]+\\]");
			Matcher matcher = pattern.matcher(statement);
			while (matcher.find()) {
				String match = matcher.group();
				resultStatement = resultStatement.replace(match,
						resolveVariable(context, variables, match));
			}

			// Solve all simple variable elements
			Pattern pattern2 = Pattern.compile("#[^\\[\\]#]+#");
			Matcher matcher2 = pattern2.matcher(statement);
			while (matcher2.find()) {
				String match = matcher2.group();

				resultStatement = resultStatement.replace(match,
						resolveVariable(context, variables, match));
			}

		}

		return resultStatement;
	}

	/**
	 * @param context
	 * @param variables
	 * @param match
	 * @return
	 */
  public static CharSequence resolveVariable(ExtensionContext context,
      Map<String, Variable> variables, String match) {
    String varName = "";
    if (match.contains("[")) {
      // Statement contains complex type
      // (simpl:DataContainerReferenceType)
      // BPEL variables
      /*
       * <?xml version="1.0" encoding="UTF-8"?> <containerReference
       * xmlns="http://www.example.org/simpl"> <simpl:schema
       * xmlns:simpl="http://www.example.org/simpl">test</simpl:schema>
       * <simpl:table
       * xmlns:simpl="http://www.example.org/simpl">projects</simpl:table>
       * </containerReference>
       */
      varName = match.replaceAll("[\\[\\]]", "");
      Variable var = variables.get(varName);
      if (var != null) {
        // replaces the data container name by the belonging xml fragment
        // the SIMPL Core resolves these references
        try {
          Node varContent = context.readVariable(var);
          String identifier = "";
          Source source = new DOMSource(varContent);
          StringWriter stringWriter = new StringWriter();
          Result result = new StreamResult(stringWriter);
          TransformerFactory factory = TransformerFactory.newInstance();
          Transformer transformer;
          try {
            transformer = factory.newTransformer();
            transformer.transform(source, result);
            identifier = "[" + stringWriter.getBuffer().toString() + "]";
          } catch (TransformerConfigurationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
          } catch (TransformerException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
          }
          return identifier;
        } catch (FaultException e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
        }
      }
    } else {
      if (match.contains("#")) {
        // Statement contains simple type BPEL variables
        /*
         * <?xml version="1.0" encoding="UTF-8"?> <temporary-simple-type-
         * wrapper>HALLO</temporary-simple-type-wrapper>
         */
        varName = match.replaceAll("#", "");
        Variable var = variables.get(varName);

        if (var != null) {

          try {
            Node varContent = context.readVariable(var);
            return varContent.getFirstChild().getTextContent();
          } catch (FaultException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
          }
        }
      }
    }

    return "";
  }
}

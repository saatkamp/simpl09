package org.eclipse.bpel.simpl.ui;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.bpel.simpl.ui.Application;
import org.eclipse.bpel.simpl.ui.extensions.IStatementEditor;
import org.eclipse.bpel.simpl.ui.extensions.AStatementEditor;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.Platform;

/**
 * This class is used to capture the management of extension contributions to
 * the extension point.
 * 
 * @author hahnml
 * 
 * 
 */
public class Application {

	/**
	 * The single instance of this class.
	 */
	private static Application instance = null;

	/**
	 * A list of all extensions and their child elements.
	 */
	private List<IConfigurationElement> languageExtensions = new ArrayList<IConfigurationElement>();

	/**
	 * Holds the id of the extension point which have to be managed.
	 */
	private static final String QUERY_LANGUAGE_ID = "org.eclipse.bpel.simpl.ui.queryLanguage";

	// private static final String[] fileSystemStatements = new String[]{"GET",
	// "PUT", "RM", "MKDIR", "MKFILE"};
	// private static final String[] dataSourceStatements = new
	// String[]{"SELECT", "INSERT", "UPDATE", "DELETE", "CREATE", "CALL"};
	// private static final String[] sensorNetStatements = new
	// String[]{"SELECT", "CREATE BUFFER", "DROP ALL"};

	/**
	 * Provides the single instance of the Application.
	 * 
	 * @return the instance of this class.
	 */
	public static Application getInstance() {
		if (instance == null) {
			instance = new Application();
		}
		return instance;
	}

	/**
	 * Initializes the Application, which means that the list of
	 * languageExtensions will be initialized.
	 */
	public void initApplication() {
		IConfigurationElement[] config = Platform.getExtensionRegistry()
				.getConfigurationElementsFor(QUERY_LANGUAGE_ID);
		for (IConfigurationElement e : config) {
			languageExtensions.add(e);
			System.out.println("Element: " + e.getName());
		}
	}

	/**
	 * Returns the class of a {@link AStatementEditor} implementation which is
	 * accessible over the extension point.
	 * 
	 * @param language
	 *            of the editor.
	 * @param activity
	 *            on which the editor is based on.
	 * @return The corresponding statement editor class.
	 */
	public IStatementEditor getEditorClass(String language, String activity) {
		IConfigurationElement element = null;
		IStatementEditor editorClass = null;
		try {
			for (IConfigurationElement e : languageExtensions) {
				if (e.getAttribute("language").contains(language)) {
					for (IConfigurationElement sub : e.getChildren()) {
						// TODO: Besser lösen!!! So darf das nicht bleiben!
						if ((sub.getChildren("activity"))[0].getAttribute("type").contains(activity)){
//						(activity.contains((sub.getChildren("activity"))[0]
//								.getAttribute("type").substring(
//										(sub.getChildren("activity"))[0]
//												.getAttribute("type")
//												.lastIndexOf(".")))) {
							element = sub;
							break;
						}
					}
				}
			}

			final Object o = element.createExecutableExtension("class");
			if (o instanceof IStatementEditor) {
				editorClass = (((IStatementEditor) o));
				System.out.println("Target: " + element.getAttribute("class"));
				;
			}
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		return editorClass;
	}

//	// TODO: Testen, ob so alle Fälle abgedeckt sind.
//	/**
//	 * This method is used to serialize a statement from a HashMap to a String
//	 * representation.
//	 * 
//	 * @param statement
//	 *            as HashMap to serialize.
//	 * @return The serialized String representation of the statement HashMap.
//	 */
//	public String serializeStatement(LinkedHashMap<String, String> statement) {
//		StringBuilder statem = new StringBuilder();
//		for (String part : statement.keySet()) {
//			statem.append(part);
//			statem.append(" ");
//			statem.append(statement.get(part));
//			statem.append(" ");
//		}
//		statem.deleteCharAt(statem.length() - 1);
//		return statem.toString();
//	}

	// TODO: Überarbeiten, wird so nicht funktionieren!
	// Hier muss wahrscheinlich mit der Syntax der Sprache gearbeitet werden
	// bzw.
	// mit der Erkennung von Schlüsselwörtern.
	/**
	 * This method is used to deserialize a statement from a String to a HashMap
	 * representation.
	 * 
	 * @param statement
	 *            as String to deserialize.
	 * @return The deserialized HashMap representation of the statement String.
	 */
	public StatementHashMap deserializeStatement(String statement) {
		StatementHashMap statem = new StatementHashMap();
		String[] parts = statement.split(" ");
		if (parts.length > 1) {
			int i = 0;
			while (i < parts.length) {
				statem.put(parts[i], parts[i + 1]);
				i = i + 2;
			}
		}
		return statem;
	}
}

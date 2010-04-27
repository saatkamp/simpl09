/**
 * <b>Purpose:</b> This class is used to capture the management of extension contributions to
 * the extension point.<br>
 * <b>Description:</b> <br>
 * <b>Copyright:</b>  Licensed under the Apache License, Version 2.0. http://www.apache.org/licenses/LICENSE-2.0<br>
 * <b>Company:</b> SIMPL<br>
 * 
 * @author Michael Hahn <hahnml@studi.informatik.uni-stuttgart.de> <br>
 * @version $Id$ <br>
 * @link http://code.google.com/p/simpl09/
 *
 */
package org.eclipse.bpel.simpl.ui;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.bpel.simpl.ui.extensions.AStatementEditor;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.Platform;


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
		
		org.eclipse.bpel.simpl.ui.properties.util.SIMPLCoreMetaData.init();
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
	public AStatementEditor getEditorClass(String language, String activity) {
		IConfigurationElement element = null;
		AStatementEditor editorClass = null;
		try {
			for (IConfigurationElement e : languageExtensions) {
				if (e.getAttribute("language").contains(language)) {
					for (IConfigurationElement sub : e.getChildren()) {
						if ((sub.getChildren("activity"))[0].getAttribute("type").contains(activity)){
							element = sub;
							break;
						}
					}
				}
			}

			final Object o = element.createExecutableExtension("class");
			if (o instanceof AStatementEditor) {
				editorClass = (((AStatementEditor) o));
				System.out.println("Target: " + element.getAttribute("class"));
				;
			}
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		return editorClass;
	}
}

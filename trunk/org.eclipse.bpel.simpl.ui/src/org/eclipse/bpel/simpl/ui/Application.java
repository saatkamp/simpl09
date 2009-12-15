package org.eclipse.bpel.simpl.ui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.eclipse.bpel.simpl.ui.Application;
import org.eclipse.bpel.simpl.ui.extensions.IStatementEditor;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.Platform;

public class Application {

	private static Application instance = null;
	
	private List<IConfigurationElement> languageExtensions = new ArrayList<IConfigurationElement>();
	
	// This must be the ID from your extension point
	private static final String QUERY_LANGUAGE_ID = "org.eclipse.bpel.simpl.ui.queryLanguage";
	
//	private static final String[] fileSystemStatements = new String[]{"GET", "PUT", "RM", "MKDIR", "MKFILE"}; 
//	private static final String[] dataSourceStatements = new String[]{"SELECT", "INSERT", "UPDATE", "DELETE", "CREATE", "CALL"};
//	private static final String[] sensorNetStatements = new String[]{"SELECT", "CREATE BUFFER", "DROP ALL"};

	public static Application getInstance() {
		if (instance == null) {
			instance = new Application();
		}
		return instance;
	}
	
	public void initApplication() {
		IConfigurationElement[] config = Platform.getExtensionRegistry()
				.getConfigurationElementsFor(QUERY_LANGUAGE_ID);
		for (IConfigurationElement e : config) {
			languageExtensions.add(e);
			System.out.println("Element: " + e.getName());
		}
	}
	
//	public static String[] getDataSourceTypes(){
//		return dataSourceTypes;
//	}
	
	public IStatementEditor getEditorClass(String language, String activity) {
		IConfigurationElement element = null;
		IStatementEditor editorClass = null;
		try {
			for (IConfigurationElement e : languageExtensions) {
				if (e.getAttribute("language").contains(language)) {
					for (IConfigurationElement sub : e.getChildren()) {
						//TODO: Besser lösen!!! So darf das nicht bleiben!
						if (activity.contains((sub.getChildren("activity"))[0].getAttribute("type").substring((sub.getChildren("activity"))[0].getAttribute("type").lastIndexOf(".")))){
							//(sub.getChildren("activity"))[0].getAttribute("type").contains(activity)){
							element = sub;
							break;
						}
					}
				}
			}
			
			final Object o = element.createExecutableExtension("class");
			if (o instanceof IStatementEditor) {
				editorClass = (((IStatementEditor) o));
				System.out.println("Target: "
						+ element.getAttribute("class"));
				;
			}
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		return editorClass;
	}
	
	//TODO: Testen, ob so alle Fälle abgedeckt sind.
	public String serializeStatement(HashMap<String, String> statement){
		StringBuilder statem = new StringBuilder();
		for (String part : statement.keySet()){
			statem.append(part);
			statem.append(" ");
			statem.append(statement.get(part));
			statem.append(" ");
		}
		statem.deleteCharAt(statem.length()-1);
		return statem.toString();
	}
	
	//TODO: Überarbeiten, wird so nicht funktionieren!
	//		Hier muss wahrscheinlich mit der Syntax der Sprache gearbeitet werden bzw. 
	//		mit der Erkennung von Schlüsselwörtern.
	public HashMap<String, String> deserializeStatement(String statement){
		HashMap<String, String> statem = new HashMap<String, String>();
		String[] parts = statement.split(" ");
		int i = 0;
		while (i < parts.length){
			statem.put(parts[i], parts[i+1]);
			i=i+2;
		}
		return statem;
	}
}

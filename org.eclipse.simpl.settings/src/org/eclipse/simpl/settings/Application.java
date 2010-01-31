package org.eclipse.simpl.settings;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.Platform;
import org.eclipse.simpl.settings.extensions.ASettingsComposite;

public class Application {

	private static Application instance = null;

	private List<IConfigurationElement> nodes = new ArrayList<IConfigurationElement>();
	private LinkedHashMap<String, ASettingsComposite> compositeClasses = new LinkedHashMap<String, ASettingsComposite>();

	// This must be the ID from your extension point
	private static final String SETTINGS_ITEM_ID = "org.eclipse.simpl.settings.settingsItem";

	public static Application getInstance() {
		if (instance == null) {
			instance = new Application();
		}
		return instance;
	}

	public void initApplication() {
		IConfigurationElement[] config = Platform.getExtensionRegistry()
				.getConfigurationElementsFor(SETTINGS_ITEM_ID);

		List<IConfigurationElement> iter = new ArrayList<IConfigurationElement>();
		List<IConfigurationElement> childs = new ArrayList<IConfigurationElement>();

		// Initialisieren die Liste nodes.
		for (IConfigurationElement e : config) {
			nodes.add(e);
		}

		iter = nodes;

		// Suchen alle TreeItem-Objekte in der PlugIn.xml und legen diese in die
		// Liste nodes.
		while (!iter.isEmpty()) {
			for (IConfigurationElement e : iter) {
				for (IConfigurationElement sub : e
						.getChildren("settingsItem")) {
					childs.add(sub);
				}
			}
			iter = childs;
			nodes.addAll(childs);
			childs.clear();
		}

		// Einmaliges Laden aller zur Verfügung stehenden CompositeClasses
		// Alle Admin-Konsolen Punkte laden
		// TODO Hier am besten gleich Einstellungen initial laden und so in den
		// Klassen hinterlegen
		LinkedHashMap<String, ASettingsComposite> classes = new LinkedHashMap<String, ASettingsComposite>();
		List<Tuple> treeItems = Application.getInstance().getTreeItems();
		List<String> treeSubItems = null;
		ASettingsComposite compClass = null;
		for (Tuple treeIt : treeItems) {
			// Alle Admin-Konsolen Unterpunkte eines Punktes laden
			treeSubItems = Application.getInstance().getTreeSubItems(
					treeIt.getName());
			for (String subItem : treeSubItems) {
				// Alle Composite-Klassen, die zur Verfügung stehen werden zu
				// Beginn instanziert.
				compClass = getCompClass(subItem);
				// Initiales Laden aller gespeicherten Werte
				compClass.loadSettings(treeIt.getName(), subItem);
				classes.put(subItem, compClass);
			}
		}
		this.compositeClasses = classes;
	}

	public List<Tuple> getTreeItems() {
		List<Tuple> treeItems = new ArrayList<Tuple>();
		try {
			IConfigurationElement[] config = Platform.getExtensionRegistry()
					.getConfigurationElementsFor(SETTINGS_ITEM_ID);
			for (IConfigurationElement e : config) {
				final String name = e.getAttribute("label");
				final String position = e.getAttribute("position");
				if (position != null) {
					int pos = Integer.parseInt(position) - 1;
					treeItems.add(new Tuple(name, pos));
				} else {
					treeItems.add(new Tuple(name));
				}
			}
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}

		// Sortieren die Liste nach Positionsangaben aufsteigend

		return sortTupleList(treeItems);
	}

	public List<String> getTreeSubItems(String treeItem) {
		List<String> treeSubItems = new ArrayList<String>();
		try {
			IConfigurationElement[] config = Platform.getExtensionRegistry()
					.getConfigurationElementsFor(SETTINGS_ITEM_ID);
			for (IConfigurationElement e : config) {
				if (e.getAttribute("label").contains(treeItem)) {
					for (IConfigurationElement sub : e.getChildren()) {
						treeSubItems.add(sub.getAttribute("label"));
					}
				}
			}
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		return treeSubItems;
	}

	private ASettingsComposite getCompClass(String treeItem) {
		ASettingsComposite compositeClass = null;

		for (IConfigurationElement e : nodes) {
			// 
			if (e.getAttribute("composite") != null
					&& e.getAttribute("label").contains(treeItem)) {
				System.out.println("Knoten_Name: " + e.getName());
				System.out.println("Attribut_Composite: "
						+ e.getAttribute("composite"));
				try {
					final Object o = e.createExecutableExtension("composite");
					if (o instanceof ASettingsComposite) {
						compositeClass = (((ASettingsComposite) o));
						System.out.println("Target: "
								+ e.getAttribute("composite"));
						;
					}
				} catch (Exception ex) {
					System.out.println(ex.getMessage());
				}
			}

		}
		return compositeClass;
	}
	
	public boolean haveSettingsChanged(){
		boolean changed = false;
		for (ASettingsComposite compositeClass : this.compositeClasses.values()){
			if (compositeClass.haveSettingsChanged()){
				changed = true;
			}
		}
		return changed;
	}
	
	public void resetCompositeValues(){
		for (ASettingsComposite compositeClass : this.compositeClasses.values()){
			compositeClass.loadSettingsFromBuffer("lastSaved");
		}
	}
	
	public void defaultCompositeValues(){
		for (ASettingsComposite compositeClass : this.compositeClasses.values()){
			compositeClass.loadSettingsFromBuffer("default");
		}
	}

	public ASettingsComposite getCompositeClass(String treeItem) {
		return this.compositeClasses.get(treeItem);
	}

	public LinkedHashMap<String, ASettingsComposite> getCompositeClasses() {
		return this.compositeClasses;
	}

	public List<Tuple> sortTupleList(List<Tuple> list) {
		boolean unsorted = true;
		List<Tuple> tupleList = list;
		Tuple temp;

		while (unsorted) {
			unsorted = false;
			for (int i = 0; i < tupleList.size() - 1; i++)
				if (tupleList.get(i).getValue() > tupleList.get(i + 1)
						.getValue()) {
					temp = tupleList.get(i);
					tupleList.set(i, tupleList.get(i + 1));
					tupleList.set(i + 1, temp);
					unsorted = true;
				}
		}
		return tupleList;
	}

}

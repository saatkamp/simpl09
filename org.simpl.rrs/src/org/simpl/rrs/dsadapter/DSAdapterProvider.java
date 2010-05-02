package org.simpl.rrs.dsadapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.simpl.rrs.dsadapter.plugins.DSAdapterPlugin;
import org.simpl.rrs.RRS;

public class DSAdapterProvider {

	private HashMap<String, List<DSAdapterPlugin>> dsAdapters = new HashMap<String, List<DSAdapterPlugin>>();
	
	public DSAdapterProvider() {
		loadPlugins();
	}
	
	public DSAdapter getInstance(String dsType, String dsSubtype) {
		DSAdapterPlugin DSAdapterInstance = null;
		List<DSAdapterPlugin> DSAdapterPlugins = this.dsAdapters.get(dsType);
		List<String> DSAdapterSubtypes = null;
		
		for (DSAdapterPlugin DSAdapterPluginInstance : DSAdapterPlugins) {
			DSAdapterSubtypes = DSAdapterPluginInstance.getSubtypes();
			
			for (String subtype : DSAdapterSubtypes) {
				if (subtype.equals(dsSubtype)) {
					DSAdapterInstance = DSAdapterPluginInstance;
				}
				
			}
		}
		return DSAdapterInstance;
		
	}
	
	private void loadPlugins() {
		List<String> plugins = RRS.getInstance().config().getDSAdapterPlugins();
		Iterator<String> pluginIterator = plugins.iterator();
		DSAdapterPlugin DSAdapterInstance;
		String DSAdapterType = null;
		//System.out.println(plugins.toString());
		
		while (pluginIterator.hasNext()) {
			try {
				DSAdapterInstance = (DSAdapterPlugin) Class.forName((String) pluginIterator.next()).newInstance();
				
				DSAdapterType = DSAdapterInstance.getType();
				if (dsAdapters.containsKey(DSAdapterType)) {
					dsAdapters.get(DSAdapterType).add(DSAdapterInstance);
				} else {
					List<DSAdapterPlugin> DSAdapterPluginList = new ArrayList<DSAdapterPlugin>();
					DSAdapterPluginList.add(DSAdapterInstance);
					dsAdapters.put(DSAdapterType, DSAdapterPluginList);
				}
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
	}
	
	public List<String> getTypes() {
		return new ArrayList<String>(dsAdapters.keySet()); 
	}
	
	public List<String> getSubtypes(String dsType) {
		List<String> DSAdapterSubtypes = new ArrayList<String>();
		
		for (DSAdapterPlugin dsAdapterPlugin : dsAdapters.get(dsType)) {
			for (String DSAdapterSubtype : dsAdapterPlugin.getSubtypes()) {
				if(!DSAdapterSubtypes.contains(DSAdapterSubtype)) {
					DSAdapterSubtypes.add(DSAdapterSubtype);
				}
			}
		}
		
		return DSAdapterSubtypes;
	}
	
	public List<String> getLanguages(String dsSubtype) {
		List<String> DSAdapterLanguages = new ArrayList<String>();
		
		for (String DSAdapterType : dsAdapters.keySet()) {
			for (DSAdapterPlugin dsAdapterPlugin : dsAdapters.get(DSAdapterType)) {
				if (dsAdapterPlugin.getLanguages().containsKey(dsSubtype)) {
					for (String language : dsAdapterPlugin.getLanguages().get(dsSubtype)) {
						if (!DSAdapterLanguages.contains(language)) {
							DSAdapterLanguages.add(language);
						}
					}
				}
			}
		}
		
		return DSAdapterLanguages;
	}
}
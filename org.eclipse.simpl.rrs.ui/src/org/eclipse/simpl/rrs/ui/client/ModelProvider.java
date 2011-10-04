package org.eclipse.simpl.rrs.ui.client;

import java.util.ArrayList;
import java.util.List;

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
public class ModelProvider {

	private static ModelProvider content;
	private List<EPR> references;

	private ModelProvider() {
		references = new ArrayList<EPR>();
		
		if (loadAllReferences() != null){
			references = loadAllReferences();
		}
	}

	public static synchronized ModelProvider getInstance() {
		if (content == null) {
			content = new ModelProvider();
		}
		return content;
	}

	public EPR find(String id) {
		EPR found = null;
		for (EPR ref : this.references) {
			if (ref.toString().equals(id)) {
				found = ref;
				continue;
			}
		}
		return found;
	}

	public List<EPR> getReferences() {
		return this.references;
	}

	public boolean insertReference(EPR ref) {
		return RRSClient.getClient().insertEPR(ref);
	}

	public boolean updateReference(EPR ref) {
		return RRSClient.getClient().updateEPR(ref);
	}

	public boolean deleteReference(EPR ref) {
		return RRSClient.getClient().deleteEPR(ref);
	}

	private List<EPR> loadAllReferences() {
		return RRSClient.getClient().getAllEPRs();
	}

	/**
     * 
     */
	public void refresh() {
		references.clear();
		references.addAll(loadAllReferences());
	}

}

package org.eclipse.simpl.rrs.ui.preferences;

import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.jface.preference.StringFieldEditor;
import org.eclipse.simpl.rrs.ui.RRSUIPlugIn;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

public class RRSPreferencePage extends FieldEditorPreferencePage implements
		IWorkbenchPreferencePage {

	public RRSPreferencePage() {
		super(GRID);

	}

	public void createFieldEditors() {
		addField(new StringFieldEditor("RRS_RET_ADDRESS", "RRS Retrieval Service address:",
				getFieldEditorParent()));
		addField(new StringFieldEditor("RRS_MG_ADDRESS", "RRS Management Service address:",
				getFieldEditorParent()));
		addField(new StringFieldEditor("RRS_MD_ADDRESS", "RRS MetaData Service address:",
				getFieldEditorParent()));
	}

	@Override
	public void init(IWorkbench workbench) {
		setPreferenceStore(RRSUIPlugIn.getDefault().getPreferenceStore());
	}

}

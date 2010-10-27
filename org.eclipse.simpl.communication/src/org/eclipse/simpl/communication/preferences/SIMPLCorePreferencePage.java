package org.eclipse.simpl.communication.preferences;

import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.jface.preference.StringFieldEditor;
import org.eclipse.simpl.communication.CommunicationPlugIn;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

public class SIMPLCorePreferencePage extends FieldEditorPreferencePage implements
		IWorkbenchPreferencePage {

	public SIMPLCorePreferencePage() {
		super(GRID);

	}

	public void createFieldEditors() {
		addField(new StringFieldEditor("SIMPL_CORE_DSS_ADDRESS", "SIMPL Datasource Service address:",
				getFieldEditorParent()));
		addField(new StringFieldEditor("SIMPL_CORE_AS_ADDRESS", "SIMPL Administration Service address:",
				getFieldEditorParent()));
    addField(new StringFieldEditor("SIMPL_RESOURCE_FRAMEWORK_ADDRESS", "SIMPL Resource Framework address:",
        getFieldEditorParent()));
	}

	@Override
	public void init(IWorkbench workbench) {
		setPreferenceStore(CommunicationPlugIn.getDefault().getPreferenceStore());
	}
}

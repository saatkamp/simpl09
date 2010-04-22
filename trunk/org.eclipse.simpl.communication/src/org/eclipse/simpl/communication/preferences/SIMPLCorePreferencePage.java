package org.eclipse.simpl.communication.preferences;

import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.jface.preference.StringFieldEditor;
import org.eclipse.simpl.communication.Activator;
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
	}

	@Override
	public void init(IWorkbench workbench) {
		setPreferenceStore(Activator.getDefault().getPreferenceStore());
	}
}

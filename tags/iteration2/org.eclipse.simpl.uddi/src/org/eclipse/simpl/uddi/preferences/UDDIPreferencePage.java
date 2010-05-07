package org.eclipse.simpl.uddi.preferences;

import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.jface.preference.StringFieldEditor;
import org.eclipse.simpl.uddi.UDDIPlugIn;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

public class UDDIPreferencePage extends FieldEditorPreferencePage implements
		IWorkbenchPreferencePage {

	public UDDIPreferencePage() {
		super(GRID);

	}

	public void createFieldEditors() {
		addField(new StringFieldEditor("UDDI_ADDRESS", "UDDI address:",
				getFieldEditorParent()));
	}

	@Override
	public void init(IWorkbench workbench) {
		setPreferenceStore(UDDIPlugIn.getDefault().getPreferenceStore());
	}

}

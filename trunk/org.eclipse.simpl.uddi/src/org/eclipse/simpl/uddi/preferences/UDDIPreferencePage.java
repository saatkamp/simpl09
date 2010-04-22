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
		addField(new StringFieldEditor("UDDI_INQ_ADDRESS", "UDDI Inquiry Service address:",
				getFieldEditorParent()));
		addField(new StringFieldEditor("UDDI_PUB_ADDRESS", "UDDI Publish Service address:",
				getFieldEditorParent()));
		addField(new StringFieldEditor("UDDI_SEC_ADDRESS", "UDDI Security Service address:",
				getFieldEditorParent()));
	}

	@Override
	public void init(IWorkbench workbench) {
		setPreferenceStore(UDDIPlugIn.getDefault().getPreferenceStore());
	}

}

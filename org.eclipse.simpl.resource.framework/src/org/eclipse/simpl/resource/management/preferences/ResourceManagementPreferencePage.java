package org.eclipse.simpl.resource.management.preferences;

import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.jface.preference.StringFieldEditor;
import org.eclipse.simpl.resource.management.ResourceManagementPlugIn;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

public class ResourceManagementPreferencePage extends FieldEditorPreferencePage implements
		IWorkbenchPreferencePage {

	public ResourceManagementPreferencePage() {
		super(GRID);

	}

	public void createFieldEditors() {
		addField(new StringFieldEditor("RESOURCE_MANAGEMENT_ADDRESS", "Resource Management Address:",
				getFieldEditorParent()));
	}

	@Override
	public void init(IWorkbench workbench) {
		setPreferenceStore(ResourceManagementPlugIn.getDefault().getPreferenceStore());
	}

}

package org.eclipse.simpl.resource.framework.preferences;

import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.jface.preference.StringFieldEditor;
import org.eclipse.simpl.resource.framework.ResourceFrameworkPlugIn;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

public class ResourceFrameworkPreferencePage extends FieldEditorPreferencePage implements
		IWorkbenchPreferencePage {

	public ResourceFrameworkPreferencePage() {
		super(GRID);

	}

	public void createFieldEditors() {
		addField(new StringFieldEditor("RESOURCE_FRAMEWORK_ADDRESS", "Resource Framework Address:",
				getFieldEditorParent()));
	}

	@Override
	public void init(IWorkbench workbench) {
		setPreferenceStore(ResourceFrameworkPlugIn.getDefault().getPreferenceStore());
	}

}

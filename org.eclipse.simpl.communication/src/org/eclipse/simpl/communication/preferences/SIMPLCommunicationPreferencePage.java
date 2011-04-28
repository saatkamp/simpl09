package org.eclipse.simpl.communication.preferences;

import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.jface.preference.StringFieldEditor;
import org.eclipse.simpl.communication.CommunicationPlugIn;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

public class SIMPLCommunicationPreferencePage extends FieldEditorPreferencePage implements
    IWorkbenchPreferencePage {

  public SIMPLCommunicationPreferencePage() {
    super(GRID);
  }

  public void createFieldEditors() {
    addField(new StringFieldEditor("SIMPL_CORE_DSS_ADDRESS",
        "SIMPL Core Datasource Web Service address:", getFieldEditorParent()));
    addField(new StringFieldEditor("SIMPL_CORE_AS_ADDRESS",
        "SIMPL Core Administration Web Service address:", getFieldEditorParent()));
    addField(new StringFieldEditor("SIMPL_RESOURCE_MANAGEMENT_ADDRESS",
        "SIMPL Resource Management Web Service address:", getFieldEditorParent()));
  }

  @Override
  public void init(IWorkbench workbench) {
    setPreferenceStore(CommunicationPlugIn.getDefault().getPreferenceStore());
  }
}
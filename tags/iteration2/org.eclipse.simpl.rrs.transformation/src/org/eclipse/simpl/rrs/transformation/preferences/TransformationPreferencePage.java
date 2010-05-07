package org.eclipse.simpl.rrs.transformation.preferences;

import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.jface.preference.StringFieldEditor;
import org.eclipse.simpl.rrs.transformation.TransformerPlugIn;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

/**
 * <b>Purpose:</b> <br>
 * <b>Description:</b> <br>
 * <b>Copyright:</b>  Licensed under the Apache License, Version 2.0. http://www.apache.org/licenses/LICENSE-2.0<br>
 * <b>Company:</b> SIMPL<br>
 * 
 * @author Michael Hahn <hahnml@studi.informatik.uni-stuttgart.de> <br>
 * @version $Id$ <br>
 * @link http://code.google.com/p/simpl09/
 *
 */
public class TransformationPreferencePage extends FieldEditorPreferencePage implements
		IWorkbenchPreferencePage {

	public TransformationPreferencePage() {
		super(GRID);

	}

	public void createFieldEditors() {
		addField(new StringFieldEditor("TRANSFORMER_ADDRESS", "Transformation Service address:",
				getFieldEditorParent()));
	}

	@Override
	public void init(IWorkbench workbench) {
		setPreferenceStore(TransformerPlugIn.getDefault().getPreferenceStore());
	}

}

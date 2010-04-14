package org.eclipse.simpl.rrs.transformation.commands;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.ui.handlers.HandlerUtil;

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
public class TransformationHandler extends AbstractHandler {

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		// TODO: Hier wird die eigentlich Transformation angestoßen, d.h. es werden alle
		// selektierten (mehrfachauswahl möglich) bzw. im Editor geöffnetete (einfachauswahl) BPEL-Dateien
		// an den Transformator geschickt und die transformierten Dateien müssen
		// auch von diesem Plug-In lokal im Workspace in einem Unterordner "transformed" gespeichert werden
		MessageDialog.openInformation(HandlerUtil.getActiveWorkbenchWindow(
				event).getShell(), "Transformation", "Transformation started.");
		return null;
	}

}

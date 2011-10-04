package org.eclipse.simpl.rrs.transformation.commands;

import org.eclipse.bpel.model.Process;
import org.eclipse.bpel.ui.util.BPELUtil;
import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.simpl.rrs.transformation.client.TransformationClient;
import org.eclipse.swt.widgets.Display;
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
	/**
	 * Die BPEL Datei des Prozesses
	 */
	IFile bpelFile = null;

	/**
	 * Der Pfad zur BPEL Datei des Prozesses
	 */
	IPath bpelPath = null;

	/**
	 * Pfad zum Projektordner = Pfad zur BPEL Datei ohne Dateiendung
	 */
	IPath projectPath = null;

	/**
	 * Absoluter Workspace-Pfad. Pfad in Format "OSString" umwandeln, so dass
	 * man mit java.io arbeiten kann.
	 */
	String absolutWorkspacePath = "";

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		if (TransformationClient.getClient().isTransformerAvailable()) {
			ISelection selection = HandlerUtil.getActiveWorkbenchWindow(event)
					.getActivePage().getSelection();
			if (selection != null & selection instanceof IStructuredSelection) {
				IStructuredSelection strucSelection = (IStructuredSelection) selection;
				if (strucSelection.getFirstElement() instanceof Process) {
					Process process = (Process) strucSelection
							.getFirstElement();

					bpelFile = BPELUtil.getBPELFile(process);

					bpelPath = bpelFile.getFullPath();

					projectPath = bpelPath.removeFileExtension()
							.removeLastSegments(1);

					absolutWorkspacePath = ResourcesPlugin.getWorkspace()
							.getRoot().getLocation().toOSString();

					System.out.println("PROJEKT-PFAD: " + absolutWorkspacePath
							+ projectPath.toOSString());
					System.out.println("Datei-PFAD: " + absolutWorkspacePath
							+ bpelPath.toOSString());

					// Hier wird die eigentlich Transformation angestoßen,
					// d.h. es wird die im Editor geöffnetete BPEL-Datei
					// an den Transformator geschickt und die transformierte
					// Datei wird auch von diesem Plug-In lokal im Workspace in
					// einem
					// Unterordner "Prozessname_transformed" gespeichert.
					TransformationCmdHelper.doTheWork(absolutWorkspacePath,
							projectPath, bpelPath);

				} else {
					MessageDialog
							.openError(
									Display.getCurrent().getActiveShell(),
									"SIMPL TransformationService: Unknown source",
									"You have to focus the BPEL Editor to transform the process. "
											+ "Alternatively you can choose a process file (*.bpel) in the "
											+ "Package Explorer and use the context menue to transform.");
				}
			}
		} else {
			MessageDialog
			.openError(
					Display.getCurrent().getActiveShell(),
					"SIMPL TransformationService: Connection Exception",
					"The Transformation Service is not responding. Please check the connection and try again.");
		}
		return null;
	}

}
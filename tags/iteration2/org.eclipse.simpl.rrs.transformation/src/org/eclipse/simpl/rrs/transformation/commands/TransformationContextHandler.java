package org.eclipse.simpl.rrs.transformation.commands;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Iterator;
import java.util.List;

import org.eclipse.bpel.model.Process;
import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.simpl.rrs.transformation.TransformerUtil;
import org.eclipse.simpl.rrs.transformation.client.TransformationClient;
import org.eclipse.simpl.rrs.transformation.jet.TemplateRrsXSD;
import org.eclipse.simpl.rrs.ui.RRSUIPlugIn;
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
public class TransformationContextHandler extends AbstractHandler {

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		ISelection selection = HandlerUtil.getActiveWorkbenchWindow(event)
				.getActivePage().getSelection();
		if (selection != null & selection instanceof IStructuredSelection) {
			IStructuredSelection strucSelection = (IStructuredSelection) selection;

			if (strucSelection.getFirstElement() instanceof IFile) {
				IFile bpelFile = (IFile) strucSelection.getFirstElement();

				IPath bpelPath = bpelFile.getFullPath();

				IPath projectPath = bpelPath.removeFileExtension()
						.removeLastSegments(1);

				String absolutWorkspacePath = ResourcesPlugin.getWorkspace()
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
			}
		}

		return null;
	}
}

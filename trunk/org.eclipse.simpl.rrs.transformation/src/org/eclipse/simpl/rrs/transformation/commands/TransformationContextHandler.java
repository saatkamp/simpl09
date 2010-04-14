package org.eclipse.simpl.rrs.transformation.commands;

import java.util.Iterator;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
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

			// Hier wird die eigentlich Transformation angestoﬂen,
			// d.h. es werden alle im Package Explorer selektierten BPEL-Dateien
			// an den Transformator geschickt und die transformierten
			// Dateien werden auch von diesem Plug-In lokal im Workspace in einem
			// Unterordner "Prozessname_transformed" gespeichert.
			for (Iterator<Object> iterator = strucSelection.iterator(); iterator
					.hasNext();) {
				Object element = iterator.next();

				if (element instanceof IFile) {
					IFile bpelFile = (IFile) element;

					IPath bpelPath = bpelFile.getFullPath();

					IPath projectPath = bpelPath.removeFileExtension()
							.removeLastSegments(1);

					String absolutWorkspacePath = ResourcesPlugin
							.getWorkspace().getRoot().getLocation()
							.toOSString();

					System.out.println("PROJEKT-PFAD: " + absolutWorkspacePath
							+ projectPath.toOSString());
					System.out.println("Datei-PFAD: " + absolutWorkspacePath
							+ bpelPath.toOSString());

					// Check if process has reference variables
//					TransformationClient.getClient().transform(
//							absolutWorkspacePath + projectPath.toOSString(),
//							absolutWorkspacePath + bpelPath.toOSString());
				}
			}
		}

		return null;
	}
}

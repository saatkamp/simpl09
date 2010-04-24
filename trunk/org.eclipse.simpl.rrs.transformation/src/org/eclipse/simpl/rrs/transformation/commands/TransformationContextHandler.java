package org.eclipse.simpl.rrs.transformation.commands;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Iterator;
import java.util.List;

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
@SuppressWarnings("unchecked")
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
			// Dateien werden auch von diesem Plug-In lokal im Workspace in
			// einem
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

					List references = TransformerUtil
							.getReferenceVariables(absolutWorkspacePath
									+ bpelPath.toOSString());

					if (!references.isEmpty()) {
						if (TransformerUtil
								.areAllRefVarsFullSpecified(absolutWorkspacePath
										+ bpelPath.toOSString())) {
							String bpelFilePath = absolutWorkspacePath
									+ bpelPath.toOSString();

							String bpelFileName = bpelFilePath
									.substring(
											bpelFilePath
													.lastIndexOf(System
															.getProperty("file.separator")) + 1,
											bpelFilePath.lastIndexOf("."));

							// Download the rrs.wsdl file from the RRS, if
							// necassary
							// TODO: If more than one RRS is used, all rrs.wsdl
							// files have to be downloaded and stored
							TransformerUtil.downloadWSDL(RRSUIPlugIn
									.getDefault().getPreferenceStore()
									.getString("RRS_RET_ADDRESS"),
									absolutWorkspacePath
											+ projectPath.toOSString(),
									bpelFileName);
							
							//Get the rrs wsdl namespace uri
							String wsdlNamespace = TransformerUtil.getRRSwsdlNamespace(absolutWorkspacePath
									+ projectPath.toOSString(),
									bpelFileName);
							
							// Transform the process
							TransformationClient.getClient().transform(
									absolutWorkspacePath
											+ projectPath.toOSString(),
									absolutWorkspacePath
											+ bpelPath.toOSString(),
											wsdlNamespace);

							File xsdFileRRS = new File(absolutWorkspacePath
									+ projectPath.toOSString()
									+ System.getProperty("file.separator")
									+ bpelFileName + "_transformed"
									+ System.getProperty("file.separator")
									+ "rrs.xsd");

							// Create the rrs.xsd file, if necassary
							if (!xsdFileRRS.exists()) {
								try {

									if (xsdFileRRS.createNewFile()) {

										OutputStreamWriter out = new OutputStreamWriter(
												new FileOutputStream(xsdFileRRS
														.getAbsolutePath()),
												"UTF-8");

										TemplateRrsXSD myTemplate = new TemplateRrsXSD();

										out.write(myTemplate.generate(null));
										out.close();
									}
								} catch (IOException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							}
							
							//Copy and modify the process wsdl to the transformed directory
							TransformerUtil.copyAndEditProcessWSDL(absolutWorkspacePath
									+ projectPath.toOSString(),
									bpelFileName);

						} else {
							MessageDialog
									.openInformation(
											Display.getCurrent()
													.getActiveShell(),
											"SIMPL TransformationService: Process has reference variables with unspecified attributes",
											"The opened process has Reference Variables which are not fully specified. "
													+ "Please check that in every Reference Variable the name, referenceType and valueType attribute is set to a value.");
						}

						// TODO: RRS.wsdl und rrs.xsd noch in den Projektordner
						// kopieren!
						// transform("C:/runtime-EclipseApplication/Test",
						// "C:/runtime-EclipseApplication/Test/asd.bpel");
					} else {
						MessageDialog
								.openInformation(
										Display.getCurrent().getActiveShell(),
										"SIMPL TransformationService: Process has no reference variables",
										"The opened process has no Reference Variables, so a transformation"
												+ " is not necassary.");
					}
				}
			}
		}

		return null;
	}
}

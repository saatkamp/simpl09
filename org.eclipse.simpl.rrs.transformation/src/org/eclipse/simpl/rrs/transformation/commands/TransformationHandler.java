package org.eclipse.simpl.rrs.transformation.commands;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.List;

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

	@SuppressWarnings("unchecked")
	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		ISelection selection = HandlerUtil.getActiveWorkbenchWindow(event)
				.getActivePage().getSelection();
		if (selection != null & selection instanceof IStructuredSelection) {
			IStructuredSelection strucSelection = (IStructuredSelection) selection;
			if (strucSelection.getFirstElement() instanceof Process) {
				Process process = (Process) strucSelection.getFirstElement();

				bpelFile = BPELUtil.getBPELFile(process);

				bpelPath = bpelFile.getFullPath();

				projectPath = bpelPath.removeFileExtension()
						.removeLastSegments(1);

				absolutWorkspacePath = ResourcesPlugin.getWorkspace().getRoot()
						.getLocation().toOSString();

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

				List references = TransformerUtil
						.getReferenceVariables(absolutWorkspacePath
								+ bpelPath.toOSString());

				if (!references.isEmpty()) {
					if (TransformerUtil
							.areAllRefVarsFullSpecified(absolutWorkspacePath
									+ bpelPath.toOSString())) {
						String bpelFilePath = absolutWorkspacePath
								+ bpelPath.toOSString();

						String bpelFileName = bpelFilePath.substring(
								bpelFilePath.lastIndexOf(System
										.getProperty("file.separator")) + 1,
								bpelFilePath.lastIndexOf("."));

						// Download the rrs.wsdl file from the RRS, if
						// necassary
						// TODO: If more than one RRS is used, all rrs.wsdl
						// files have to be downloaded and stored
						TransformerUtil
								.downloadWSDL(RRSUIPlugIn.getDefault()
										.getPreferenceStore().getString(
												"RRS_RET_ADDRESS"),
										absolutWorkspacePath
												+ projectPath.toOSString(),
										bpelFileName);
						
						//Get the rrs wsdl namespace uri
						String wsdlNamespace = TransformerUtil.getRRSwsdlNamespace(absolutWorkspacePath
								+ projectPath.toOSString(),
								bpelFileName);
						
						// Transform the process
						TransformationClient.getClient()
								.transform(
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
						
						System.out.println(xsdFileRRS);

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
										Display.getCurrent().getActiveShell(),
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

		return null;
	}

}
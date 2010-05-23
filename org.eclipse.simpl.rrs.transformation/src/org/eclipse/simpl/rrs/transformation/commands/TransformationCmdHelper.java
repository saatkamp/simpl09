package org.eclipse.simpl.rrs.transformation.commands;

import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.simpl.rrs.transformation.TransformerUtil;
import org.eclipse.simpl.rrs.transformation.client.TransformationClient;
import org.eclipse.simpl.rrs.ui.RRSUIPlugIn;
import org.eclipse.swt.widgets.Display;

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
public class TransformationCmdHelper {

	public static void doTheWork(String absolutWorkspacePath,
			IPath projectPath, IPath bpelPath) {

		// Create a new project, if it doesn't exist
		IWorkspace workspace = ResourcesPlugin.getWorkspace();
		IWorkspaceRoot root = workspace.getRoot();
		IProject project = root.getProject(projectPath.lastSegment() + "_TF");
		
		List references = TransformerUtil
				.getReferenceVariables(absolutWorkspacePath
						+ bpelPath.toOSString());

		if (!references.isEmpty()) {
			if (TransformerUtil.areAllRefVarsFullSpecified(absolutWorkspacePath
					+ bpelPath.toOSString())) {
				
				// at this point, no resources have been created
				try {
					// project.copy(new Path(projectPath.lastSegment()+"_TF"), true,
					// null);
					if (!project.exists()) {
						//Get the description of the source project
						IProjectDescription desc = root.getProject(projectPath.lastSegment()).getDescription();
						//Create the new project and add the original description
						project.create(desc, IProject.BACKGROUND_REFRESH, null);
						project.open(null);
					}
				} catch (CoreException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				String bpelFilePath = absolutWorkspacePath
						+ bpelPath.toOSString();

				String bpelFileName = bpelFilePath.substring(bpelFilePath
						.lastIndexOf(System.getProperty("file.separator")) + 1,
						bpelFilePath.lastIndexOf("."));

				// Download the rrsRetrieval.wsdl and rrsMetaData.wsdl file from
				// the RRS, if necassary
				// TODO: If in the future more than one RRS is used, all
				// rrs***.wsdl
				// files have to be downloaded and stored
				TransformerUtil.downloadFile(RRSUIPlugIn.getDefault()
						.getPreferenceStore().getString("RRS_RET_ADDRESS"),
						project.getLocation().toOSString(),
						TransformerUtil.RRS_RETRIEVAL_FILE);

				TransformerUtil.downloadFile(RRSUIPlugIn.getDefault()
						.getPreferenceStore().getString("RRS_MD_ADDRESS"),
						project.getLocation().toOSString(),
						TransformerUtil.RRS_META_DATA_FILE);
				
				// ../axis2/services/RRSRetrievalService?xsd=RRSService_schema1.xsd
				String rrsXSDUri = RRSUIPlugIn.getDefault()
				.getPreferenceStore().getString("RRS_RET_ADDRESS");

				rrsXSDUri = rrsXSDUri.substring(0, rrsXSDUri.lastIndexOf("?"));
				rrsXSDUri += "?xsd=RRSService_schema1.xsd";
				
				TransformerUtil.downloadFile(rrsXSDUri,
						project.getLocation().toOSString(),
						TransformerUtil.RRS_XSD_FILE);

				// Get the rrsRetrieval.wsdl namespace uri
				String rrsNamespace = TransformerUtil
						.getRRSwsdlNamespace(
								project.getLocation().toOSString(),
								TransformerUtil.RRS_RETRIEVAL_FILE);

				// Transform the process
				TransformationClient.getClient().transform(
						absolutWorkspacePath + bpelPath.toOSString(),
						bpelFileName, project.getLocation().toOSString(),
						rrsNamespace);

				// Copy and modify the process wsdl to the transformed
				// directory
				TransformerUtil.copyAndEditProcessWSDL(absolutWorkspacePath
						+ projectPath.toOSString(), bpelFileName, project
						.getLocation().toOSString());

				TransformerUtil.copyAllNotTransfContent(absolutWorkspacePath
						+ projectPath.toOSString(), project.getLocation().toOSString());
			} else {
				MessageDialog
						.openInformation(
								Display.getCurrent().getActiveShell(),
								"SIMPL TransformationService: Process has reference variables with unspecified attributes",
								"The opened process has Reference Variables which are not fully specified. "
										+ "Please check that in every Reference Variable the name, referenceType and valueType attribute is set to a value.");
			}
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

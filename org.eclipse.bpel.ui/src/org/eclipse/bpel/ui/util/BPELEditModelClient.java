/*******************************************************************************
 * Copyright (c) 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.bpel.ui.util;

import java.util.Map;

import javax.xml.namespace.QName;

import org.eclipse.bpel.common.extension.model.ExtensionMap;
import org.eclipse.bpel.common.extension.model.ExtensionmodelFactory;
import org.eclipse.bpel.common.ui.editmodel.EditModel;
import org.eclipse.bpel.common.ui.editmodel.EditModelClient;
import org.eclipse.bpel.common.ui.editmodel.EditModelCommandStack;
import org.eclipse.bpel.common.ui.editmodel.IEditModelListener;
import org.eclipse.bpel.common.ui.editmodel.ResourceInfo;
import org.eclipse.bpel.model.Process;
import org.eclipse.bpel.ui.IBPELUIConstants;
import org.eclipse.core.resources.IFile;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.ui.IEditorPart;

import org.eclipse.wst.wsdl.Definition;
import org.eclipse.wst.wsdl.WSDLFactory;

/**
 * EditModelClient customized for use with the BPELEditor.
 */
public class BPELEditModelClient extends EditModelClient {

	private ResourceInfo extensionsResourceInfo;
	private ResourceInfo artifactsResourceInfo;
	BPELEditModel bpelEditModel;
	
	public BPELEditModelClient(IEditorPart editor, IFile file,
		IEditModelListener modelListener, Map loadOptions)
	{
		super(editor, file, modelListener, loadOptions);
		bpelEditModel = (BPELEditModel)getEditModel();

		getPrimaryResourceInfo().getResource();

		IFile extensionsFile = bpelEditModel.getExtensionsFile();
		if (extensionsFile.exists()) {
			extensionsResourceInfo = bpelEditModel.getResourceInfo(extensionsFile);
		} else {
			Resource bpelexResource = bpelEditModel.getResourceSet().createResource(
				URI.createPlatformResourceURI(extensionsFile.getFullPath().toString()));
			extensionsResourceInfo = bpelEditModel.getResourceInfo(extensionsFile);
			// create an empty extension map.
			ExtensionMap extensionMap = ExtensionmodelFactory.eINSTANCE.createExtensionMap(
				IBPELUIConstants.MODEL_EXTENSIONS_NAMESPACE);
			bpelexResource.getContents().add(extensionMap);
		}

		IFile artifactsFile = bpelEditModel.getArtifactsFile();
		if (artifactsFile.exists()) {
			artifactsResourceInfo = bpelEditModel.getResourceInfo(artifactsFile);
		} else {
			Resource artifactsResource = bpelEditModel.getResourceSet().createResource(
					URI.createPlatformResourceURI(artifactsFile.getFullPath().toString()));
			// create an empty definition too.
			Definition artifactsDefn = WSDLFactory.eINSTANCE.createDefinition();
			artifactsDefn.setLocation(artifactsResource.getURI().toString());

			// set the target namespace based on the target namespace of the process.
			EList bpelContents = getPrimaryResourceInfo().getResource().getContents();
			if (!bpelContents.isEmpty() && bpelContents.get(0) instanceof Process) {
				Process process = (Process)bpelContents.get(0);
				// TODO: is this correct?  can we make a helper to share this with the wizard?
				artifactsDefn.setTargetNamespace(process.getTargetNamespace()+"Artifacts"); //$NON-NLS-1$
				artifactsDefn.setQName(new QName(artifactsDefn.getTargetNamespace(),
					artifactsFile.getFullPath().removeFileExtension().lastSegment()));
			}
			artifactsResource.getContents().add(artifactsDefn);
			artifactsResourceInfo = bpelEditModel.getResourceInfo(artifactsFile);
		}
	}
	
	@Override
	protected EditModelCommandStack createCommandStack() {
		EditModelCommandStack commandStack = super.createCommandStack();
		commandStack.setUndoLimit(50);
		commandStack.addCommandStackListener(new CommandStackChangeBatcher());
		return commandStack;
	}
	
	@Override
	public void dispose() {
		if (artifactsResourceInfo != null) {
			bpelEditModel.releaseReference(artifactsResourceInfo);
		}
		if (extensionsResourceInfo != null) {
			bpelEditModel.releaseReference(extensionsResourceInfo);
		}
		super.dispose();
	}

	@Override
	protected EditModel getSharedResourceSet(IFile file) {
		return BPELEditModel.getEditModel(file);
	}
	
	public ResourceInfo getArtifactsResourceInfo() { return artifactsResourceInfo; }
	public ResourceInfo getExtensionsResourceInfo() { return extensionsResourceInfo; }
}

/*******************************************************************************
 * Copyright (c) 2008 IBM Corporation, University of Stuttgart (IAAS) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation, University of Stuttgart (IAAS) - initial API and implementation
 *******************************************************************************/

package org.eclipse.bpel.apache.ode.deploy.ui.util;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.xml.namespace.QName;

import org.eclipse.bpel.apache.ode.deploy.model.dd.ProcessType;
import org.eclipse.bpel.apache.ode.deploy.model.dd.TDatasource;
import org.eclipse.bpel.apache.ode.deploy.model.dd.TDeployment;
import org.eclipse.bpel.apache.ode.deploy.model.dd.TPolicy;
import org.eclipse.bpel.apache.ode.deploy.model.dd.ddFactory;
import org.eclipse.bpel.apache.ode.deploy.model.dd.ddPackage;
import org.eclipse.bpel.model.Activity;
import org.eclipse.bpel.model.Process;
import org.eclipse.bpel.simpl.model.DataManagementActivity;
import org.eclipse.bpel.ui.util.ModelHelper;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceVisitor;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EEnumLiteral;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.wst.wsdl.Definition;
import org.eclipse.wst.wsdl.Port;
import org.eclipse.wst.wsdl.Service;

/**
 * @author Simon Moser (IBM)
 * @author Tammo van Lessen (IAAS)
 */
public class DeployUtils {

	public static final String URL_PREFIX_FILE = "file"; //$NON-NLS-1$
	public static final String URL_PREFIX_PLATFORM = "platform"; //$NON-NLS-1$
	public static final String URL_PREFIX_RESOURCE = "resource"; //$NON-NLS-1$
	public static final String NONE_STRING = "-- none -- "; //$NON-NLS-1$

	public static ProcessType findProcessTypeInDD(
			org.eclipse.bpel.model.Process process, TDeployment dd) {
		for (ProcessType pt : dd.getProcess()) {
			if (pt.getName().getLocalPart().equals(process.getName())
					&& pt.getName().getNamespaceURI().equals(
							process.getTargetNamespace())) {
				return pt;
			}
		}
		return null;
	}

	public static ProcessType createProcessStub(
			org.eclipse.bpel.model.Process process) {
		ProcessType pt = ddFactory.eINSTANCE.createProcessType();
		QName processQName = new QName(process.getTargetNamespace(), process
				.getName());
		pt.setName(processQName);
		return pt;
	}

	public static QName getQNameFromSerialzedForm(String qNameAsString) {

		int pos = qNameAsString.lastIndexOf("}"); //$NON-NLS-1$

		String ns = qNameAsString.substring(1, pos);
		String name = qNameAsString.substring(pos + 1, qNameAsString.length());

		QName qName = new QName(ns, name);

		return qName;
	}

	@SuppressWarnings("unchecked")//$NON-NLS-1$
	public static Port findPortByName(String name, IProject bpelProject,
			ResourceSet resourceSet) {

		List serviceList = new ArrayList();
		List portList = new ArrayList();

		List<Definition> wsdlDefs = DeployUtils.loadAllWSDLFromProject(
				bpelProject, resourceSet);
		// Assemble All Services from WSDL's
		for (Iterator<Definition> iterator = wsdlDefs.iterator(); iterator
				.hasNext();) {
			Definition current = (Definition) iterator.next();
			Map services = current.getServices();
			if (!services.isEmpty()) {
				Collection values = services.values();
				for (Iterator iterator2 = values.iterator(); iterator2
						.hasNext();) {
					Service name2 = (Service) iterator2.next();
					serviceList.add(name2);
				}
			}
		}

		// now we have all services in a List .. get All Ports from these
		// services
		for (Iterator iterator = serviceList.iterator(); iterator.hasNext();) {
			Service currentService = (Service) iterator.next();
			Map portMap = currentService.getPorts();
			Collection ports = portMap.values();
			portList.addAll(ports);
		}

		for (Iterator iterator = portList.iterator(); iterator.hasNext();) {
			Port currentPort = (Port) iterator.next();
			if (currentPort.getName().equals(name)) {
				return currentPort;
			}
		}

		return null;

	}

	public static Process loadBPEL(IFile bpelFile, ResourceSet resourceSet) {

		IPath fullProcessPath = bpelFile.getFullPath();
		URI uri = URI.createPlatformResourceURI(fullProcessPath.toString(),
				false);
		Resource bpelResource = resourceSet.getResource(uri, true);

		EcorePackage instance = EcorePackage.eINSTANCE;
		instance.eAdapters();

		try {
			if (bpelResource.isLoaded()) {
				bpelResource.unload();
			}
			bpelResource.load(Collections.EMPTY_MAP);
			EList<EObject> contents = bpelResource.getContents();
			if (!contents.isEmpty()) {
				return (Process) contents.get(0);
			}
		} catch (Exception e) {
			// swallow exception
		}

		return null;
	}

	public static Definition loadWSDL(IFile wsdlFile, ResourceSet resourceSet) {

		IPath fullProcessPath = wsdlFile.getFullPath();
		URI uri = URI.createPlatformResourceURI(fullProcessPath.toString(),
				false);
		Resource wsdlResource = resourceSet.getResource(uri, true);

		EcorePackage instance = EcorePackage.eINSTANCE;
		instance.eAdapters();

		try {
			wsdlResource.load(Collections.EMPTY_MAP);
			EList<EObject> contents = wsdlResource.getContents();
			if (!contents.isEmpty()) {
				return (Definition) contents.get(0);
			}
		} catch (Exception e) {
			// swallow exception
		}

		return null;
	}

	public static List<Definition> loadAllWSDLFromProject(IProject project,
			ResourceSet resourceSet) {
		List<Definition> wsdlFiles = new ArrayList<Definition>();

		List<IFile> allFiles = DeployUtils.getAllFilesInProject(project);

		for (IFile file : allFiles) {

			if (file.getFileExtension().equalsIgnoreCase("wsdl")) { //$NON-NLS-1$
				// load it
				Definition currentDef = loadWSDL(file, resourceSet);
				// stuff it in wsdlFiles
				wsdlFiles.add(currentDef);
			}
		}

		return wsdlFiles;
	}

	public static List<Process> loadAllBPELFromProject(IProject project,
			ResourceSet resourceSet) {
		List<Process> bpelFiles = new ArrayList<Process>();

		List<IFile> allFiles = DeployUtils.getAllFilesInProject(project);

		for (IFile file : allFiles) {

			if (file.getFileExtension().equalsIgnoreCase("bpel")) { //$NON-NLS-1$
				// load it
				Process currentProcess = loadBPEL(file, resourceSet);
				// stuff it in bpelFiles
				bpelFiles.add(currentProcess);
			}
		}

		return bpelFiles;
	}

	public static List<IFile> getAllFilesInProject(IProject project) {
		final List<IFile> files = new ArrayList<IFile>();
		IResourceVisitor visitor = new IResourceVisitor() {
			public boolean visit(org.eclipse.core.resources.IResource resource)
					throws org.eclipse.core.runtime.CoreException {
				if (resource.getType() == IResource.FILE) {
					files.add((IFile) resource);
				}
				return true;
			}
		};
		try {
			project.accept(visitor);
		} catch (CoreException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return files;
	}

	public static IFile getIFileForURI(URI uri) {

		if (uri == null)
			return null;

		String filePath = null;
		String scheme = uri.scheme();

		if (URL_PREFIX_FILE.equals(scheme)) {
			filePath = uri.toFileString();
		} else if (URL_PREFIX_PLATFORM.equals(scheme) && uri.segmentCount() > 1
				&& URL_PREFIX_RESOURCE.equals(uri.segment(0))) {
			StringBuffer platformResourcePath = new StringBuffer();
			for (int i = 1, size = uri.segmentCount(); i < size; ++i) {
				platformResourcePath.append('/');
				platformResourcePath.append(uri.segment(i));
			}
			filePath = URI.decode(platformResourcePath.toString());
		}

		if (filePath == null)
			return null;

		IFile file = null;

		if (URL_PREFIX_FILE.equals(scheme)) { // 44110
			if (uri.device() != null) {
				filePath = filePath.substring(filePath.indexOf(uri.device()));
			}
			file = ResourcesPlugin.getWorkspace().getRoot().getFileForLocation(
					new Path(filePath));
		} else
			file = ResourcesPlugin.getWorkspace().getRoot().getFile(
					new Path(filePath));

		return file;
	}

	public static Activity findActivityByName(String name, Process process) {

		Activity activity = null;

		Object obj = ModelHelper.findElementByName(process, name,
				Activity.class);

		System.out.println("FOUND OBJECT: " + obj);

		if (obj != null) {
			if (obj instanceof Activity) {
				activity = (Activity) obj;
			}
		}

		return activity;
	}

	@SuppressWarnings("unchecked")
	public static TDatasource findDataSourceByName(String name,
			ProcessType processType) {

		List<TDatasource> datasources = processType.getDatasources();

		for (Iterator iterator = datasources.iterator(); iterator.hasNext();) {
			TDatasource currentDs = (TDatasource) iterator.next();
			if (currentDs.getDataSourceName().equals(name)) {
				System.out.println("FOUND DATASOURCE: " + currentDs);

				return currentDs;
			}
		}

		return null;
	}

	public static TPolicy queryPolicyByPath(String filePath) {
		// Create a new TPolicy Object
		TPolicy policy = ddFactory.eINSTANCE.createTPolicy();

		// Read the policy file content
		StringBuilder string = new StringBuilder();

		if (filePath != null || !filePath.isEmpty()) {

			BufferedReader in;
			try {
				in = new BufferedReader(new InputStreamReader(
						new FileInputStream(filePath)));

				try {
					String line;
					while ((line = in.readLine()) != null) {
						string.append(line);
						string.append("\n");
					}
				} finally {
					in.close();
				}
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}

			// Set the policy file content to the policy object
			policy.setLocalPath(filePath);
			policy.setPolicyData(string.toString());
		} else {
			policy.setLocalPath("");
		}

		return policy;
	}

	public static List<String> getProcessDataSourceNames(ProcessType processType) {
		EList<TDatasource> datasources = processType.getDatasources();

		List<String> dsNames = new ArrayList<String>();

		for (TDatasource data : datasources) {
			dsNames.add(data.getDataSourceName());
		}

		return dsNames;
	}

	public static List<Activity> getProcessDMActivities(Process process) {

		List<Activity> activities = new ArrayList<Activity>();

		for (TreeIterator<EObject> processContent = ((EObject) process)
				.eAllContents(); processContent.hasNext();) {
			EObject current = processContent.next();
			// TODO: Project dependency to the SIMPL Model to get the correct
			// Activities
			if (current instanceof DataManagementActivity) {
				activities.add((DataManagementActivity) current);
			}
		}

		return activities;
	}

	public static String[] getDMActivityNames(Process process) {
		List<Activity> activities = getProcessDMActivities(process);

		List<String> activityNames = new ArrayList<String>();

		for (Activity act : activities) {
			activityNames.add(act.getName());
		}

		return activityNames.toArray(new String[0]);
	}
	
	public static String[] getStrategies(){
		List<String> strategies = new ArrayList<String>();
		for (EEnumLiteral literal : ddPackage.eINSTANCE.getStrategyType().getELiterals()){
			strategies.add(literal.getName());
		}
		
		return strategies.toArray(new String[0]);
	}
}

/**
 * <b>Purpose:</b> This class implements the {@link BPELActivitySerializer} interface for the
 * serialization of all SIMPL Activities.<br>
 * <b>Description:</b> <br>
 * <b>Copyright:</b>  Licensed under the Apache License, Version 2.0. http://www.apache.org/licenses/LICENSE-2.0<br>
 * <b>Company:</b> SIMPL<br>
 * 
 * @author Michael Hahn <hahnml@studi.informatik.uni-stuttgart.de> <br>
 * @version $Id: DataManagementActivitySerializer.java 1966 2011-10-29 15:11:31Z michael.schneidt@arcor.de $ <br>
 * @link http://code.google.com/p/simpl09/
 *
 */
package org.eclipse.bpel.simpl.model.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.xml.namespace.QName;

import org.eclipse.bpel.model.Activity;
import org.eclipse.bpel.model.BPELFactory;
import org.eclipse.bpel.model.Extension;
import org.eclipse.bpel.model.Extensions;
import org.eclipse.bpel.model.Import;
import org.eclipse.bpel.model.Process;
import org.eclipse.bpel.model.adapters.INamespaceMap;
import org.eclipse.bpel.model.extensions.BPELActivitySerializer;
import org.eclipse.bpel.model.resource.BPELWriter;
import org.eclipse.bpel.model.util.BPELUtils;
import org.eclipse.bpel.simpl.model.DataManagementActivity;
import org.eclipse.bpel.simpl.model.IssueCommandActivity;
import org.eclipse.bpel.simpl.model.ModelPackage;
import org.eclipse.bpel.simpl.model.QueryDataActivity;
import org.eclipse.bpel.simpl.model.RetrieveDataActivity;
import org.eclipse.bpel.simpl.model.TransferDataActivity;
import org.eclipse.bpel.simpl.model.WriteDataBackActivity;
import org.eclipse.bpel.ui.IBPELUIConstants;
import org.eclipse.bpel.ui.util.BPELUtil;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.simpl.communication.ResourceManagementCommunication;
import org.eclipse.wst.wsdl.util.WSDLConstants;
import org.simpl.resource.management.data.Connector;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

public class DataManagementActivitySerializer implements BPELActivitySerializer {
  final static HashMap<File, String> SIMPL_SCHEMA_FILES = new HashMap<File, String>();
  
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.bpel.model.extensions.BPELActivitySerializer#marshall(javax
	 * .xml.namespace.QName, org.eclipse.bpel.model.Activity, org.w3c.dom.Node,
	 * org.eclipse.bpel.model.Process,
	 * org.eclipse.bpel.model.resource.BPELWriter)
	 */
	@Override
	public void marshall(QName elementType, Activity activity, Node parentNode,
			Process process, BPELWriter bpelWriter) {

		Document document = parentNode.getOwnerDocument();

		if (activity instanceof DataManagementActivity) {

			/**
			 * @see: Vukojevic, Karolina: "Architektur eines Workflow-Frameworks
			 *       zur graphischen Erstellung und Ausführung von
			 *       Simulationsexperimenten". 01.09.2009
			 * 
			 *       Zuerst wird überprüft, ob alle notwendigen XSD-Dateien im
			 *       Projektordner des Benutzers vorliegen. Wenn nicht, dann
			 *       werden diese erzeugt.
			 */
			createFilesForImports(process);

			setExtensionImports(process);
			
			setPrefixForImports(process);
		}

		/*
		 * QueryActivity
		 */
		if (activity instanceof QueryDataActivity) {

			// create a new DOM element for our Activity
			Element activityElement = document.createElementNS(elementType
					.getNamespaceURI(),
					DataManagementConstants.ND_QUERY_DATA_ACTIVITY);
			activityElement
					.setPrefix(DataManagementUtils.addNamespace(process));

			// handle the QueryDataActivity Attributes
			if (((QueryDataActivity) activity).getDsStatement() != null) {
				String attName = ModelPackage.eINSTANCE
						.getDataManagementActivity_DsStatement().getName();
				activityElement.setAttribute(attName,
						((QueryDataActivity) activity).getDsStatement());
			}

			if (((QueryDataActivity) activity).getDsKind() != null) {
				String attName = ModelPackage.eINSTANCE
						.getDataManagementActivity_DsKind().getName();
				activityElement.setAttribute(attName,
						((QueryDataActivity) activity).getDsKind());
			}

			if (((QueryDataActivity) activity).getDsType() != null) {
				String attName = ModelPackage.eINSTANCE
						.getDataManagementActivity_DsType().getName();
				activityElement.setAttribute(attName,
						((QueryDataActivity) activity).getDsType());
			}

			if (((QueryDataActivity) activity).getDsIdentifier() != null) {
				String attName = ModelPackage.eINSTANCE
						.getDataManagementActivity_DsIdentifier().getName();
				activityElement.setAttribute(attName,
						((QueryDataActivity) activity).getDsIdentifier());
			}

			if (((QueryDataActivity) activity).getDsLanguage() != null) {
				String attName = ModelPackage.eINSTANCE
						.getDataManagementActivity_DsLanguage().getName();
				activityElement.setAttribute(attName,
						((QueryDataActivity) activity).getDsLanguage());
			}

			if (((QueryDataActivity) activity).getQueryTarget() != null) {
				String attName = ModelPackage.eINSTANCE
						.getQueryDataActivity_QueryTarget().getName();
				activityElement.setAttribute(attName,
						((QueryDataActivity) activity).getQueryTarget());
			}

			// insert the DOM element into the DOM tree
			parentNode.appendChild(activityElement);
		}

		/*
		 * IssueCommandActivity
		 */
		if (activity instanceof IssueCommandActivity) {

			// create a new DOM element for our Activity
			Element activityElement = document.createElementNS(elementType
					.getNamespaceURI(),
					DataManagementConstants.ND_ISSUE_COMMAND_ACTIVITY);
			activityElement
					.setPrefix(DataManagementUtils.addNamespace(process));

			// handle the IssueCommandActivity Attributes
			if (((IssueCommandActivity) activity).getDsStatement() != null) {
				String attName = ModelPackage.eINSTANCE
						.getDataManagementActivity_DsStatement().getName();
				activityElement.setAttribute(attName,
						((IssueCommandActivity) activity).getDsStatement());
			}

			if (((IssueCommandActivity) activity).getDsKind() != null) {
				String attName = ModelPackage.eINSTANCE
						.getDataManagementActivity_DsKind().getName();
				activityElement.setAttribute(attName,
						((IssueCommandActivity) activity).getDsKind());
			}

			if (((IssueCommandActivity) activity).getDsType() != null) {
				String attName = ModelPackage.eINSTANCE
						.getDataManagementActivity_DsType().getName();
				activityElement.setAttribute(attName,
						((IssueCommandActivity) activity).getDsType());
			}

			if (((IssueCommandActivity) activity).getDsIdentifier() != null) {
				String attName = ModelPackage.eINSTANCE
						.getDataManagementActivity_DsIdentifier().getName();
				activityElement.setAttribute(attName,
						((IssueCommandActivity) activity).getDsIdentifier());
			}

			if (((IssueCommandActivity) activity).getDsLanguage() != null) {
				String attName = ModelPackage.eINSTANCE
						.getDataManagementActivity_DsLanguage().getName();
				activityElement.setAttribute(attName,
						((IssueCommandActivity) activity).getDsLanguage());
			}

			// insert the DOM element into the DOM tree
			parentNode.appendChild(activityElement);
		}

		/*
		 * RetrieveDataActivity
		 */
		if (activity instanceof RetrieveDataActivity) {

			// create a new DOM element for our Activity
			Element activityElement = document.createElementNS(elementType
					.getNamespaceURI(),
					DataManagementConstants.ND_RETRIEVE_DATA_ACTIVITY);
			activityElement
					.setPrefix(DataManagementUtils.addNamespace(process));

			// handle the RetrieveDataActivity Attributes
			if (((RetrieveDataActivity) activity).getDsStatement() != null) {
				String attName = ModelPackage.eINSTANCE
						.getDataManagementActivity_DsStatement().getName();
				activityElement.setAttribute(attName,
						((RetrieveDataActivity) activity).getDsStatement());
			}

			if (((RetrieveDataActivity) activity).getDsKind() != null) {
				String attName = ModelPackage.eINSTANCE
						.getDataManagementActivity_DsKind().getName();
				activityElement.setAttribute(attName,
						((RetrieveDataActivity) activity).getDsKind());
			}

			if (((RetrieveDataActivity) activity).getDsType() != null) {
				String attName = ModelPackage.eINSTANCE
						.getDataManagementActivity_DsType().getName();
				activityElement.setAttribute(attName,
						((RetrieveDataActivity) activity).getDsType());
			}

			if (((RetrieveDataActivity) activity).getDsIdentifier() != null) {
				String attName = ModelPackage.eINSTANCE
						.getDataManagementActivity_DsIdentifier().getName();
				activityElement.setAttribute(attName,
						((RetrieveDataActivity) activity).getDsIdentifier());
			}

			if (((RetrieveDataActivity) activity).getDsLanguage() != null) {
				String attName = ModelPackage.eINSTANCE
						.getDataManagementActivity_DsLanguage().getName();
				activityElement.setAttribute(attName,
						((RetrieveDataActivity) activity).getDsLanguage());
			}

			if (((RetrieveDataActivity) activity).getDataVariable() != null) {
				String attName = ModelPackage.eINSTANCE
						.getRetrieveDataActivity_DataVariable().getName();
				activityElement.setAttribute(attName,
						((RetrieveDataActivity) activity).getDataVariable()
								.getName());
			}

			// insert the DOM element into the DOM tree
			parentNode.appendChild(activityElement);
		}

    /*
     * WriteDataBackActivity
     */
    if (activity instanceof WriteDataBackActivity) {

      // create a new DOM element for our Activity
      Element activityElement = document.createElementNS(elementType
          .getNamespaceURI(),
          DataManagementConstants.ND_WRITE_DATA_BACK_ACTIVITY);
      activityElement
          .setPrefix(DataManagementUtils.addNamespace(process));

      // handle the WriteDataBackActivity Attributes
      if (((WriteDataBackActivity) activity).getDsStatement() != null) {
        String attName = ModelPackage.eINSTANCE
            .getDataManagementActivity_DsStatement().getName();
        activityElement.setAttribute(attName,
            ((WriteDataBackActivity) activity).getDsStatement());
      }

      if (((WriteDataBackActivity) activity).getDsKind() != null) {
        String attName = ModelPackage.eINSTANCE
            .getDataManagementActivity_DsKind().getName();
        activityElement.setAttribute(attName,
            ((WriteDataBackActivity) activity).getDsKind());
      }

      if (((WriteDataBackActivity) activity).getDsType() != null) {
        String attName = ModelPackage.eINSTANCE
            .getDataManagementActivity_DsType().getName();
        activityElement.setAttribute(attName,
            ((WriteDataBackActivity) activity).getDsType());
      }

      if (((WriteDataBackActivity) activity).getDsIdentifier() != null) {
        String attName = ModelPackage.eINSTANCE
            .getDataManagementActivity_DsIdentifier().getName();
        activityElement.setAttribute(attName,
            ((WriteDataBackActivity) activity).getDsIdentifier());
      }

      if (((WriteDataBackActivity) activity).getDsLanguage() != null) {
        String attName = ModelPackage.eINSTANCE
            .getDataManagementActivity_DsLanguage().getName();
        activityElement.setAttribute(attName,
            ((WriteDataBackActivity) activity).getDsLanguage());
      }
      
      if (((WriteDataBackActivity) activity).getDataVariable() != null) {
        String attName = ModelPackage.eINSTANCE
            .getWriteDataBackActivity_DataVariable().getName();
        activityElement.setAttribute(attName,
            ((WriteDataBackActivity) activity).getDataVariable()
                .getName());
      }

      if (((WriteDataBackActivity) activity).getWriteTarget() != null) {
        String attName = ModelPackage.eINSTANCE
            .getWriteDataBackActivity_WriteTarget().getName();
        activityElement.setAttribute(attName,
            ((WriteDataBackActivity) activity).getWriteTarget());
      }
      
      // insert the DOM element into the DOM tree
      parentNode.appendChild(activityElement);
    }

		
		/*
		 * TransferDataActivity
		 */
		if (activity instanceof TransferDataActivity) {

			// create a new DOM element for our Activity
			Element activityElement = document.createElementNS(elementType
					.getNamespaceURI(),
					DataManagementConstants.ND_TRANSFER_DATA_ACTIVITY);
			activityElement
					.setPrefix(DataManagementUtils.addNamespace(process));

			// handle the TransferDataActivity attributes
			if (((TransferDataActivity) activity).getDsStatement() != null) {
				String attName = ModelPackage.eINSTANCE
						.getDataManagementActivity_DsStatement().getName();
				activityElement.setAttribute(attName,
						((TransferDataActivity) activity).getDsStatement());
			}

			if (((TransferDataActivity) activity).getDsKind() != null) {
				String attName = ModelPackage.eINSTANCE
						.getDataManagementActivity_DsKind().getName();
				activityElement.setAttribute(attName,
						((TransferDataActivity) activity).getDsKind());
			}

			if (((TransferDataActivity) activity).getDsType() != null) {
				String attName = ModelPackage.eINSTANCE
						.getDataManagementActivity_DsType().getName();
				activityElement.setAttribute(attName,
						((TransferDataActivity) activity).getDsType());
			}

			if (((TransferDataActivity) activity).getDsIdentifier() != null) {
				String attName = ModelPackage.eINSTANCE
						.getDataManagementActivity_DsIdentifier().getName();
				activityElement.setAttribute(attName,
						((TransferDataActivity) activity).getDsIdentifier());
			}

			if (((TransferDataActivity) activity).getDsLanguage() != null) {
				String attName = ModelPackage.eINSTANCE
						.getDataManagementActivity_DsLanguage().getName();
				activityElement.setAttribute(attName,
						((TransferDataActivity) activity).getDsLanguage());
			}

			if (((TransferDataActivity) activity).getTargetDsType() != null) {
				String attName = ModelPackage.eINSTANCE
						.getTransferDataActivity_TargetDsType().getName();
				activityElement.setAttribute(attName,
						((TransferDataActivity) activity).getTargetDsType());
			}
			
			if (((TransferDataActivity) activity).getTargetDsKind() != null) {
				String attName = ModelPackage.eINSTANCE
						.getTransferDataActivity_TargetDsKind().getName();
				activityElement.setAttribute(attName,
						((TransferDataActivity) activity).getTargetDsKind());
			}
			
			if (((TransferDataActivity) activity).getTargetDsIdentifier() != null) {
				String attName = ModelPackage.eINSTANCE
						.getTransferDataActivity_TargetDsIdentifier().getName();
				activityElement.setAttribute(attName,
						((TransferDataActivity) activity).getTargetDsIdentifier());
			}
			
			if (((TransferDataActivity) activity).getTargetDsLanguage() != null) {
				String attName = ModelPackage.eINSTANCE
						.getTransferDataActivity_TargetDsLanguage().getName();
				activityElement.setAttribute(attName,
						((TransferDataActivity) activity).getTargetDsLanguage());
			}

			if (((TransferDataActivity) activity).getTargetDsContainer() != null) {
				String attName = ModelPackage.eINSTANCE
						.getTransferDataActivity_TargetDsContainer().getName();
				activityElement.setAttribute(attName,
						((TransferDataActivity) activity).getTargetDsContainer());
			}

			// insert the DOM element into the DOM tree
			parentNode.appendChild(activityElement);
		}
	}

	/**
	 * 
	 * @see: Vukojevic, Karolina: "Architektur eines Workflow-Frameworks zur
	 *       graphischen Erstellung und Ausführung von Simulationsexperimenten".
	 *       01.09.2009
	 * 
	 *       Die Methode createFilesForImport(Process process) überprüft, ob
	 *       alle von den SIMPL-Aktivitäten benötigten XSD-Dateien im
	 *       Projektordner des Benutzers vorhanden sind. Wenn nicht, dann werden
	 *       diese mit Hilfe von JET (Java Emitter Templates) erzeugt.
	 *       SIMPL-Aktivitäten benötigen folgende Dateien: simpl.xsd
	 * 
	 * @param process
	 *            : aktueller Prozess
	 */
	private void createFilesForImports(Process process) {
		/**
		 * Die BPEL Datei des Prozesses
		 */
		IFile bpelFile = BPELUtil.getBPELFile(process);

		/**
		 * Der Pfad zur BPEL Datei des Prozesses
		 */
		IPath bpelPath = bpelFile.getFullPath();

		/**
		 * Pfad zum Projektordner = Pfad zur BPEL Datei ohne BPEL Dateinamen und
		 * Dateiendung
		 */
		IPath projectPath = bpelPath.removeFileExtension()
				.removeLastSegments(1);

		/**
		 * Absoluter Workspace-Pfad. Pfad in Format "OSString" umwandeln, so
		 * dass man mit java.io arbeiten kann.
		 */
		String absolutWorkspacePath = ResourcesPlugin.getWorkspace().getRoot()
				.getLocation().toOSString();

		/******************************************************************
		 * simpl schema files
		 ******************************************************************/

    /**
     * Get all SIMPL schemas from the Resource Management and merge them to a WSDL with
     * type definitions. (simpl.wsdl)
     */
    List<String> dataFormats = null;
    List<Connector> connectors = null;
    String dataFormatSchemaString = null;

    // retrieve all XML schemas from Resource Management connectors
    try {
      dataFormats = new ArrayList<String>();
      connectors = ResourceManagementCommunication.getInstance().getAllConnectors();

      // get the existing workflow data formats from the connectors
      for (Connector connector : connectors) {
        if (connector.getDataConverter() != null) {
          String dataFormat = connector.getDataConverter().getWorkflowDataFormat();
          
          if (!dataFormats.contains(dataFormat)) {
            dataFormats.add(dataFormat);
          }
        }
      }

      // get the workflow data format XSD schemas from the Resource Management and parse
      // them into XSD schema objects.
      for (String dataFormat : dataFormats) {
        dataFormatSchemaString = ResourceManagementCommunication.getInstance().getDataFormatSchema(
            dataFormat);
        
        // write file
        File file = new File(absolutWorkspacePath + projectPath.append(dataFormat).addFileExtension(
            IBPELUIConstants.EXTENSION_XSD));
        FileWriter fstream = new FileWriter(file);
        BufferedWriter out = new BufferedWriter(fstream);
        out.write(dataFormatSchemaString);
        out.close();
        
        SIMPL_SCHEMA_FILES.put(file, dataFormatSchemaString);
      }

      // retrieve definitions schema from Resource Management
      String schema = ResourceManagementCommunication.getInstance().getAllTypeDefinitionsSchema();

      // write file
      File file = new File(absolutWorkspacePath + projectPath.append("simpl").addFileExtension(
          IBPELUIConstants.EXTENSION_XSD));
      FileWriter fstream = new FileWriter(file);
      BufferedWriter out = new BufferedWriter(fstream);
      out.write(schema);
      out.close();
      
      SIMPL_SCHEMA_FILES.put(file, schema);
      
      // refresh the workspace to update the files
      ResourcesPlugin.getWorkspace().getRoot()
          .refreshLocal(IResource.DEPTH_INFINITE, new NullProgressMonitor()); 
    } catch (FileNotFoundException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (CoreException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
	} // END createFilesForImport

	/**
	 * @see: Vukojevic, Karolina: "Architektur eines Workflow-Frameworks zur
	 *       graphischen Erstellung und Ausführung von Simulationsexperimenten".
	 *       01.09.2009
	 * 
	 *       Fügt eine WSDL-Datei als Import in den BPEL-Prozess ein, falls sie
	 *       noch nicht importiert wurde und macht sie als Extension bekannt.
	 * 
	 * @param process
	 */
	private void setExtensionImports(Process process) {
	  boolean exist = false;
	  
    for (File schemaFile : SIMPL_SCHEMA_FILES.keySet()) {
      // Prüfen ob Import schon existiert
      for (Import imp : process.getImports()) {
        if (imp.getLocation().equals(schemaFile.getName())) {
          exist = true;
        }
      }
      
      // Neuen Import erstellen
      if (!exist) {
        Import bpelImport = BPELFactory.eINSTANCE.createImport();
        String schema = SIMPL_SCHEMA_FILES.get(schemaFile);
        String namespace = schema.substring(schema.indexOf("targetNamespace=\"") + 17, schema.indexOf("\"", schema.indexOf("targetNamespace=\"") + 17));

        bpelImport.setImportType(WSDLConstants.XSD_NAMESPACE_URI);
        bpelImport.setLocation(schemaFile.getName());
        bpelImport.setNamespace(namespace);
        process.getImports().add(bpelImport);
      }
    }
	  
		/**
		 * Nun überprüfen, ob der durch die WSDL-Datei definierte Namespace schon
		 * im Prozess als Extension eingefügt wurde.
		 */
		boolean extensionExist = false;

		// Lesen die Prozess-Extensions aus
		Extensions processExtensions = process.getExtensions();

		// Überprüfung, ob die SIMPL Extension schon eingetragen ist
		if (process.getExtensions() != null) {
			for (Extension e : processExtensions.getChildren()) {
				if (e.getNamespace().equals("http://www.example.org/simpl")) {
					extensionExist = true;
				}
			}
		}

		if (!extensionExist) {
			// Erstellen die SIMPL Extension
			Extension simplExtension = BPELFactory.eINSTANCE.createExtension();
			simplExtension.setNamespace("http://www.example.org/simpl");
			simplExtension.setMustUnderstand(true);

			// Falls der Prozess keine Extensions hat, erstellen wir ein
			// neues Extensions-Objekt.
			if (processExtensions == null) {
				processExtensions = BPELFactory.eINSTANCE.createExtensions();
			}

			// Die SIMPL-Extension zu den Extensions hinzufügen
			processExtensions.getChildren().add(simplExtension);

			// Das erweiterte Extensions-Objekt im Prozess setzen
			process.setExtensions(processExtensions);
		}
	}
	
	/**
	 * Setz einen Prefix für die Namespaces der Imports.
	 * 
	 * @param process
	 */
	private void setPrefixForImports(Process process) {
    int i = 0;
    
	  for (File schemaFile : SIMPL_SCHEMA_FILES.keySet()) {
      String schema = SIMPL_SCHEMA_FILES.get(schemaFile);
      String nsURI = schema.substring(schema.indexOf("targetNamespace=\"") + 17, schema.indexOf("\"", schema.indexOf("targetNamespace=\"") + 17));
      String nsPrefix = "simpldf" + i++;
      INamespaceMap<String, String> nsMap = BPELUtils.getNamespaceMap(process);

      // Der Prefix für den Namespace http://www.example.org/simpl ist bereits durch den
      // Namespace des Ecore-Model gegeben
      if (!nsURI.equals(ModelPackage.eINSTANCE.getNsURI())) {
        nsMap.put(nsPrefix, nsURI);
      }
    }
	}
}

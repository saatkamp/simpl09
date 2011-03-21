/**
 * <b>Purpose:</b> This class implements the {@link BPELActivitySerializer} interface for the
 * serialization of all SIMPL Activities.<br>
 * <b>Description:</b> <br>
 * <b>Copyright:</b>  Licensed under the Apache License, Version 2.0. http://www.apache.org/licenses/LICENSE-2.0<br>
 * <b>Company:</b> SIMPL<br>
 * 
 * @author Michael Hahn <hahnml@studi.informatik.uni-stuttgart.de> <br>
 * @version $Id$ <br>
 * @link http://code.google.com/p/simpl09/
 *
 */
package org.eclipse.bpel.simpl.model.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

import javax.xml.XMLConstants;
import javax.xml.namespace.QName;

import org.eclipse.bpel.model.Activity;
import org.eclipse.bpel.model.BPELFactory;
import org.eclipse.bpel.model.Extension;
import org.eclipse.bpel.model.Extensions;
import org.eclipse.bpel.model.Import;
import org.eclipse.bpel.model.Process;
import org.eclipse.bpel.model.extensions.BPELActivitySerializer;
import org.eclipse.bpel.model.resource.BPELWriter;
import org.eclipse.bpel.simpl.model.DataManagementActivity;
import org.eclipse.bpel.simpl.model.IssueCommandActivity;
import org.eclipse.bpel.simpl.model.ModelPackage;
import org.eclipse.bpel.simpl.model.QueryDataActivity;
import org.eclipse.bpel.simpl.model.RetrieveDataActivity;
import org.eclipse.bpel.simpl.model.TransferDataActivity;
import org.eclipse.bpel.simpl.model.WriteDataBackActivity;
import org.eclipse.bpel.simpl.model.impl.jet.TemplateSIMPL;
import org.eclipse.bpel.ui.IBPELUIConstants;
import org.eclipse.bpel.ui.util.BPELUtil;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

public class DataManagementActivitySerializer implements BPELActivitySerializer {

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
			 *       zur graphischen Erstellung und Ausf�hrung von
			 *       Simulationsexperimenten". 01.09.2009
			 * 
			 *       Zuerst wird �berpr�ft, ob alle notwendigen XSD-Dateien im
			 *       Projektordner des Benutzers vorliegen. Wenn nicht, dann
			 *       werden diese erzeugt.
			 */
			createFilesForImport(process);

			setExtensionImport(process);
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

			if (((QueryDataActivity) activity).getDsAddress() != null) {
				String attName = ModelPackage.eINSTANCE
						.getDataManagementActivity_DsAddress().getName();
				activityElement.setAttribute(attName,
						((QueryDataActivity) activity).getDsAddress());
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

			if (((IssueCommandActivity) activity).getDsAddress() != null) {
				String attName = ModelPackage.eINSTANCE
						.getDataManagementActivity_DsAddress().getName();
				activityElement.setAttribute(attName,
						((IssueCommandActivity) activity).getDsAddress());
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

			if (((RetrieveDataActivity) activity).getDsAddress() != null) {
				String attName = ModelPackage.eINSTANCE
						.getDataManagementActivity_DsAddress().getName();
				activityElement.setAttribute(attName,
						((RetrieveDataActivity) activity).getDsAddress());
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

      if (((WriteDataBackActivity) activity).getDsAddress() != null) {
        String attName = ModelPackage.eINSTANCE
            .getDataManagementActivity_DsAddress().getName();
        activityElement.setAttribute(attName,
            ((WriteDataBackActivity) activity).getDsAddress());
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

			if (((TransferDataActivity) activity).getDsAddress() != null) {
				String attName = ModelPackage.eINSTANCE
						.getDataManagementActivity_DsAddress().getName();
				activityElement.setAttribute(attName,
						((TransferDataActivity) activity).getDsAddress());
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
			
			if (((TransferDataActivity) activity).getTargetDsAddress() != null) {
				String attName = ModelPackage.eINSTANCE
						.getTransferDataActivity_TargetDsAddress().getName();
				activityElement.setAttribute(attName,
						((TransferDataActivity) activity).getTargetDsAddress());
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
	 *       graphischen Erstellung und Ausf�hrung von Simulationsexperimenten".
	 *       01.09.2009
	 * 
	 *       Die Methode createFilesForImport(Process process) �berpr�ft, ob
	 *       alle von den SIMPL-Aktivit�ten ben�tigten XSD-Dateien im
	 *       Projektordner des Benutzers vorhanden sind. Wenn nicht, dann werden
	 *       diese mit Hilfe von JET (Java Emitter Templates) erzeugt.
	 *       SIMPL-Aktivit�ten ben�tigen folgende Dateien: simpl.xsd
	 * 
	 * @param process
	 *            : aktueller Prozess
	 */
	private void createFilesForImport(Process process) {

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
		 * simpl.xsd
		 ******************************************************************/

		/**
		 * Pfad zur XSD Datei = Pfad zum Projektordner + XSD Dateiname und
		 * Dateienendung
		 */
		IPath xsdPathSIMPL = projectPath.append("simpl").addFileExtension(
				IBPELUIConstants.EXTENSION_XSD);

		/**
		 * Objekte vom Typ File sind eine abstrakte Repr�sentation einer Datei.
		 * Sie verweisen auf die konkreten Dateien (oder Ordner). Dass es ein
		 * solches Objekt gibt, heisst noch nicht, dass es diese Datei auch
		 * gibt.
		 */
		File xsdFileSIMPL = new File(absolutWorkspacePath
				+ xsdPathSIMPL.toOSString());

		/**
		 * Wenn Datei nicht existiert, dann
		 */
		if (!xsdFileSIMPL.exists()) {
			try {
				/**
				 * ...Datei neu erzeugen und mit Inhalt f�llen (aus JET
				 * Template)
				 */
				if (xsdFileSIMPL.createNewFile()) {

					/**
					 * OutputStreamWriter zum Schreiben der Datei
					 */
					OutputStreamWriter out = new OutputStreamWriter(
							new FileOutputStream(xsdFileSIMPL.getAbsolutePath()),
							"UTF-8");

					/**
					 * Eine Instanz des JET-Templates erzeugen
					 */
					TemplateSIMPL myTemplate = new TemplateSIMPL();

					/**
					 * Dateiinhalt aus Template generieren und in Datei
					 * schreiben
					 */
					out.write(myTemplate.generate(null));
					out.close();

				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	} // END createFilesForImport

	/**
	 * @see: Vukojevic, Karolina: "Architektur eines Workflow-Frameworks zur
	 *       graphischen Erstellung und Ausf�hrung von Simulationsexperimenten".
	 *       01.09.2009
	 * 
	 *       F�gt eine XSD-Datei als Import in den BPEL-Prozess ein, falls sie
	 *       noch nicht importiert wurde und macht sie als Extension bekannt.
	 * 
	 * @param process
	 */
	private void setExtensionImport(Process process) {

		/**
		 * Zun�chst �berpr�fen, ob die ben�tigten XSD-Datei schon in den
		 * BPEL-Prozess importiert wurden. Die �berpr�fung erfolgt �ber die
		 * Location (Dateiname). Nur wenn es noch keinen Import dieser Dateien
		 * gibt, wird ein neuer Import erzeugt.
		 */
		boolean XsdExist = false;
		for (Import i : process.getImports()) {
			if (i.getLocation().equals("simpl.xsd")) {
				XsdExist = true;
			}
		}

		if (!XsdExist) {
			// Erstellen einen neuen Import
			Import xsdImportSIMPL = BPELFactory.eINSTANCE.createImport();
			xsdImportSIMPL.setImportType(XMLConstants.W3C_XML_SCHEMA_NS_URI);
			xsdImportSIMPL.setLocation("simpl.xsd");
			xsdImportSIMPL.setNamespace("http://www.example.org/simpl");
			process.getImports().add(xsdImportSIMPL);
		}

		/**
		 * Nun �berpr�fen, ob der durch die XSD-Datei definierte Namespace schon
		 * im Prozess als Extension eingef�gt wurde.
		 */
		boolean extensionExist = false;

		// Lesen die Prozess-Extensions aus
		Extensions processExtensions = process.getExtensions();

		// �berpr�fung, ob die SIMPL Extension schon eingetragen ist
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

			// Die SIMPL-Extension zu den Extensions hinzuf�gen
			processExtensions.getChildren().add(simplExtension);

			// Das erweiterte Extensions-Objekt im Prozess setzen
			process.setExtensions(processExtensions);
		}
	}
}
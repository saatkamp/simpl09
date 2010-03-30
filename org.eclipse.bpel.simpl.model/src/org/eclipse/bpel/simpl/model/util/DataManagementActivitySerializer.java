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
import org.eclipse.bpel.simpl.model.CallActivity;
import org.eclipse.bpel.simpl.model.CreateActivity;
import org.eclipse.bpel.simpl.model.DataManagementActivity;
import org.eclipse.bpel.simpl.model.DeleteActivity;
import org.eclipse.bpel.simpl.model.DropActivity;
import org.eclipse.bpel.simpl.model.InsertActivity;
import org.eclipse.bpel.simpl.model.ModelPackage;
import org.eclipse.bpel.simpl.model.QueryActivity;
import org.eclipse.bpel.simpl.model.RetrieveDataActivity;
import org.eclipse.bpel.simpl.model.UpdateActivity;
import org.eclipse.bpel.simpl.model.impl.jet.TemplateSIMPL;
import org.eclipse.bpel.ui.IBPELUIConstants;
import org.eclipse.bpel.ui.util.BPELUtil;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

/**
 * This class implements the {@link BPELActivitySerializer} interface for the
 * serialization of all SIMPL Activities.
 * 
 * @author Michael Hahn <hahnml@studi.informatik.uni-stuttgart.de>
 * 
 */
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
			 *       zur graphischen Erstellung und Ausführung von
			 *       Simulationsexperimenten". 01.09.2009
			 * 
			 *       Zuerst wird überprüft, ob alle notwendigen XSD-Dateien im
			 *       Projektordner des Benutzers vorliegen. Wenn nicht, dann
			 *       werden diese erzeugt.
			 */
			createFilesForImport(process);

			setExtensionImport(process);
		}

		/*
		 * QueryActivity
		 */
		if (activity instanceof QueryActivity) {

			// create a new DOM element for our Activity
			Element activityElement = document.createElementNS(elementType
					.getNamespaceURI(),
					DataManagementConstants.ND_QUERY_ACTIVITY);
			activityElement
					.setPrefix(DataManagementUtils.addNamespace(process));

			// handle the QueryActivity Attributes
			if (((QueryActivity) activity).getDsStatement() != null) {
				String attName = ModelPackage.eINSTANCE
						.getDataManagementActivity_DsStatement().getName();
				activityElement.setAttribute(attName,
						((QueryActivity) activity).getDsStatement());
			}

			if (((QueryActivity) activity).getDsKind() != null) {
				String attName = ModelPackage.eINSTANCE
						.getDataManagementActivity_DsKind().getName();
				activityElement.setAttribute(attName,
						((QueryActivity) activity).getDsKind());
			}

			if (((QueryActivity) activity).getDsType() != null) {
				String attName = ModelPackage.eINSTANCE
						.getDataManagementActivity_DsType().getName();
				activityElement.setAttribute(attName,
						((QueryActivity) activity).getDsType());
			}

			if (((QueryActivity) activity).getDsAddress() != null) {
				String attName = ModelPackage.eINSTANCE
						.getDataManagementActivity_DsAddress().getName();
				activityElement.setAttribute(attName,
						((QueryActivity) activity).getDsAddress());
			}

			if (((QueryActivity) activity).getDsLanguage() != null) {
				String attName = ModelPackage.eINSTANCE
						.getDataManagementActivity_DsLanguage().getName();
				activityElement.setAttribute(attName,
						((QueryActivity) activity).getDsLanguage());
			}

			if (((QueryActivity) activity).getQueryTarget() != null) {
				String attName = ModelPackage.eINSTANCE
						.getQueryActivity_QueryTarget().getName();
				activityElement.setAttribute(attName,
						((QueryActivity) activity).getQueryTarget());
			}

			// insert the DOM element into the DOM tree
			parentNode.appendChild(activityElement);
		}

		/*
		 * InsertActivity
		 */
		if (activity instanceof InsertActivity) {

			// create a new DOM element for our Activity
			Element activityElement = document.createElementNS(elementType
					.getNamespaceURI(),
					DataManagementConstants.ND_INSERT_ACTIVITY);
			activityElement
					.setPrefix(DataManagementUtils.addNamespace(process));

			// handle the InsertActivity Attributes
			if (((InsertActivity) activity).getDsStatement() != null) {
				String attName = ModelPackage.eINSTANCE
						.getDataManagementActivity_DsStatement().getName();
				activityElement.setAttribute(attName,
						((InsertActivity) activity).getDsStatement());
			}

			if (((InsertActivity) activity).getDsKind() != null) {
				String attName = ModelPackage.eINSTANCE
						.getDataManagementActivity_DsKind().getName();
				activityElement.setAttribute(attName,
						((InsertActivity) activity).getDsKind());
			}

			if (((InsertActivity) activity).getDsType() != null) {
				String attName = ModelPackage.eINSTANCE
						.getDataManagementActivity_DsType().getName();
				activityElement.setAttribute(attName,
						((InsertActivity) activity).getDsType());
			}

			if (((InsertActivity) activity).getDsAddress() != null) {
				String attName = ModelPackage.eINSTANCE
						.getDataManagementActivity_DsAddress().getName();
				activityElement.setAttribute(attName,
						((InsertActivity) activity).getDsAddress());
			}

			if (((InsertActivity) activity).getDsLanguage() != null) {
				String attName = ModelPackage.eINSTANCE
						.getDataManagementActivity_DsLanguage().getName();
				activityElement.setAttribute(attName,
						((InsertActivity) activity).getDsLanguage());
			}

			// insert the DOM element into the DOM tree
			parentNode.appendChild(activityElement);
		}

		/*
		 * UpdateActivity
		 */
		if (activity instanceof UpdateActivity) {

			// create a new DOM element for our Activity
			Element activityElement = document.createElementNS(elementType
					.getNamespaceURI(),
					DataManagementConstants.ND_UPDATE_ACTIVITY);
			activityElement
					.setPrefix(DataManagementUtils.addNamespace(process));

			// handle the UpdateActivity Attributes
			if (((UpdateActivity) activity).getDsStatement() != null) {
				String attName = ModelPackage.eINSTANCE
						.getDataManagementActivity_DsStatement().getName();
				activityElement.setAttribute(attName,
						((UpdateActivity) activity).getDsStatement());
			}

			if (((UpdateActivity) activity).getDsKind() != null) {
				String attName = ModelPackage.eINSTANCE
						.getDataManagementActivity_DsKind().getName();
				activityElement.setAttribute(attName,
						((UpdateActivity) activity).getDsKind());
			}

			if (((UpdateActivity) activity).getDsType() != null) {
				String attName = ModelPackage.eINSTANCE
						.getDataManagementActivity_DsType().getName();
				activityElement.setAttribute(attName,
						((UpdateActivity) activity).getDsType());
			}

			if (((UpdateActivity) activity).getDsAddress() != null) {
				String attName = ModelPackage.eINSTANCE
						.getDataManagementActivity_DsAddress().getName();
				activityElement.setAttribute(attName,
						((UpdateActivity) activity).getDsAddress());
			}

			if (((UpdateActivity) activity).getDsLanguage() != null) {
				String attName = ModelPackage.eINSTANCE
						.getDataManagementActivity_DsLanguage().getName();
				activityElement.setAttribute(attName,
						((UpdateActivity) activity).getDsLanguage());
			}

			// insert the DOM element into the DOM tree
			parentNode.appendChild(activityElement);
		}

		/*
		 * DeleteActivity
		 */
		if (activity instanceof DeleteActivity) {

			// create a new DOM element for our Activity
			Element activityElement = document.createElementNS(elementType
					.getNamespaceURI(),
					DataManagementConstants.ND_DELETE_ACTIVITY);
			activityElement
					.setPrefix(DataManagementUtils.addNamespace(process));

			// handle the DeleteActivity Attributes
			if (((DeleteActivity) activity).getDsStatement() != null) {
				String attName = ModelPackage.eINSTANCE
						.getDataManagementActivity_DsStatement().getName();
				activityElement.setAttribute(attName,
						((DeleteActivity) activity).getDsStatement());
			}

			if (((DeleteActivity) activity).getDsKind() != null) {
				String attName = ModelPackage.eINSTANCE
						.getDataManagementActivity_DsKind().getName();
				activityElement.setAttribute(attName,
						((DeleteActivity) activity).getDsKind());
			}

			if (((DeleteActivity) activity).getDsType() != null) {
				String attName = ModelPackage.eINSTANCE
						.getDataManagementActivity_DsType().getName();
				activityElement.setAttribute(attName,
						((DeleteActivity) activity).getDsType());
			}

			if (((DeleteActivity) activity).getDsAddress() != null) {
				String attName = ModelPackage.eINSTANCE
						.getDataManagementActivity_DsAddress().getName();
				activityElement.setAttribute(attName,
						((DeleteActivity) activity).getDsAddress());
			}

			if (((DeleteActivity) activity).getDsLanguage() != null) {
				String attName = ModelPackage.eINSTANCE
						.getDataManagementActivity_DsLanguage().getName();
				activityElement.setAttribute(attName,
						((DeleteActivity) activity).getDsLanguage());
			}

			// insert the DOM element into the DOM tree
			parentNode.appendChild(activityElement);
		}

		/*
		 * CreateActivity
		 */
		if (activity instanceof CreateActivity) {

			// create a new DOM element for our Activity
			Element activityElement = document.createElementNS(elementType
					.getNamespaceURI(),
					DataManagementConstants.ND_CREATE_ACTIVITY);
			activityElement
					.setPrefix(DataManagementUtils.addNamespace(process));

			// handle the CreateActivity Attributes
			if (((CreateActivity) activity).getDsStatement() != null) {
				String attName = ModelPackage.eINSTANCE
						.getDataManagementActivity_DsStatement().getName();
				activityElement.setAttribute(attName,
						((CreateActivity) activity).getDsStatement());
			}

			if (((CreateActivity) activity).getDsKind() != null) {
				String attName = ModelPackage.eINSTANCE
						.getDataManagementActivity_DsKind().getName();
				activityElement.setAttribute(attName,
						((CreateActivity) activity).getDsKind());
			}

			if (((CreateActivity) activity).getDsType() != null) {
				String attName = ModelPackage.eINSTANCE
						.getDataManagementActivity_DsType().getName();
				activityElement.setAttribute(attName,
						((CreateActivity) activity).getDsType());
			}

			if (((CreateActivity) activity).getDsAddress() != null) {
				String attName = ModelPackage.eINSTANCE
						.getDataManagementActivity_DsAddress().getName();
				activityElement.setAttribute(attName,
						((CreateActivity) activity).getDsAddress());
			}

			if (((CreateActivity) activity).getDsLanguage() != null) {
				String attName = ModelPackage.eINSTANCE
						.getDataManagementActivity_DsLanguage().getName();
				activityElement.setAttribute(attName,
						((CreateActivity) activity).getDsLanguage());
			}

			// insert the DOM element into the DOM tree
			parentNode.appendChild(activityElement);
		}

		/*
		 * DropActivity
		 */
		if (activity instanceof DropActivity) {

			// create a new DOM element for our Activity
			Element activityElement = document.createElementNS(elementType
					.getNamespaceURI(),
					DataManagementConstants.ND_DROP_ACTIVITY);
			activityElement
					.setPrefix(DataManagementUtils.addNamespace(process));

			// handle the DropActivity Attributes
			if (((DropActivity) activity).getDsStatement() != null) {
				String attName = ModelPackage.eINSTANCE
						.getDataManagementActivity_DsStatement().getName();
				activityElement.setAttribute(attName, ((DropActivity) activity)
						.getDsStatement());
			}

			if (((DropActivity) activity).getDsKind() != null) {
				String attName = ModelPackage.eINSTANCE
						.getDataManagementActivity_DsKind().getName();
				activityElement.setAttribute(attName, ((DropActivity) activity)
						.getDsKind());
			}

			if (((DropActivity) activity).getDsType() != null) {
				String attName = ModelPackage.eINSTANCE
						.getDataManagementActivity_DsType().getName();
				activityElement.setAttribute(attName, ((DropActivity) activity)
						.getDsType());
			}

			if (((DropActivity) activity).getDsAddress() != null) {
				String attName = ModelPackage.eINSTANCE
						.getDataManagementActivity_DsAddress().getName();
				activityElement.setAttribute(attName, ((DropActivity) activity)
						.getDsAddress());
			}

			if (((DropActivity) activity).getDsLanguage() != null) {
				String attName = ModelPackage.eINSTANCE
						.getDataManagementActivity_DsLanguage().getName();
				activityElement.setAttribute(attName, ((DropActivity) activity)
						.getDsLanguage());
			}

			// insert the DOM element into the DOM tree
			parentNode.appendChild(activityElement);
		}

		/*
		 * CallActivity
		 */
		if (activity instanceof CallActivity) {

			// create a new DOM element for our Activity
			Element activityElement = document.createElementNS(elementType
					.getNamespaceURI(),
					DataManagementConstants.ND_CALL_ACTIVITY);
			activityElement
					.setPrefix(DataManagementUtils.addNamespace(process));

			// handle the CallActivity Attributes
			if (((CallActivity) activity).getDsStatement() != null) {
				String attName = ModelPackage.eINSTANCE
						.getDataManagementActivity_DsStatement().getName();
				activityElement.setAttribute(attName, ((CallActivity) activity)
						.getDsStatement());
			}

			if (((CallActivity) activity).getDsKind() != null) {
				String attName = ModelPackage.eINSTANCE
						.getDataManagementActivity_DsKind().getName();
				activityElement.setAttribute(attName, ((CallActivity) activity)
						.getDsKind());
			}

			if (((CallActivity) activity).getDsType() != null) {
				String attName = ModelPackage.eINSTANCE
						.getDataManagementActivity_DsType().getName();
				activityElement.setAttribute(attName, ((CallActivity) activity)
						.getDsType());
			}

			if (((CallActivity) activity).getDsAddress() != null) {
				String attName = ModelPackage.eINSTANCE
						.getDataManagementActivity_DsAddress().getName();
				activityElement.setAttribute(attName, ((CallActivity) activity)
						.getDsAddress());
			}

			if (((CallActivity) activity).getDsLanguage() != null) {
				String attName = ModelPackage.eINSTANCE
						.getDataManagementActivity_DsLanguage().getName();
				activityElement.setAttribute(attName, ((CallActivity) activity)
						.getDsLanguage());
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
		 * Objekte vom Typ File sind eine abstrakte Repräsentation einer Datei.
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
				 * ...Datei neu erzeugen und mit Inhalt füllen (aus JET
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
	 *       graphischen Erstellung und Ausführung von Simulationsexperimenten".
	 *       01.09.2009
	 * 
	 *       Fügt eine XSD-Datei als Import in den BPEL-Prozess ein, falls sie
	 *       noch nicht importiert wurde und macht sie als Extension bekannt.
	 * 
	 * @param process
	 */
	private void setExtensionImport(Process process) {

		/**
		 * Zunächst überprüfen, ob die benötigten XSD-Datei schon in den
		 * BPEL-Prozess importiert wurden. Die Überprüfung erfolgt über die
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
		 * Nun überprüfen, ob der durch die XSD-Datei definierte Namespace schon
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
}

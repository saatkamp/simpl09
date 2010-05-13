package widgets;

import java.io.StringReader;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.eclipse.simpl.communication.SIMPLCommunication;
import org.eclipse.simpl.communication.SIMPLCore;
import org.eclipse.simpl.communication.client.DataSource;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

public class MetaDataXMLParser {
	
	public MetaDataXMLParser(){
		
	}
	
	/** The array of elements. */
	//ArrayList<String> arrayOfElements=new ArrayList<String>();
	Document document=null;
	ArrayList<Table> listOfTableObjekts = new ArrayList<Table>();
	Table tableObjekt = null;
	/**
	 * 
	 */
	ArrayList<FileSystemElement> listOfFileSysElements_command=new ArrayList<FileSystemElement>();
	ArrayList<FileSystemElement> listOfFileSysElements_file=new ArrayList<FileSystemElement>();
	ArrayList<FileSystemElement> listOfFileSysElements_drive=new ArrayList<FileSystemElement>();
	ArrayList<FileSystemElement> listOfFileSysElements_folder=new ArrayList<FileSystemElement>();
	FileSystemElement fileSysObjekt;
	public ArrayList<FileSystemElement> getListOfFileSysElements_command() {
		return listOfFileSysElements_command;
	}


	public ArrayList<FileSystemElement> getListOfFileSysElements_file() {
		return listOfFileSysElements_file;
	}


	public ArrayList<FileSystemElement> getListOfFileSysElements_drive() {
		return listOfFileSysElements_drive;
	}


	public ArrayList<FileSystemElement> getListOfFileSysElements_folder() {
		return listOfFileSysElements_folder;
	}
	
	
	/**
	 * for adding the tables names from the DB.
	 */
     public ArrayList<Table> loadTablesFromDB(DataSource dataSource) {
			
			
			//++++++++++++++++++++++++DSO Parsing++++++++++++++++++++++++
			SIMPLCore simplCore=SIMPLCommunication.getConnection();
			try {
				
				 String metaDataString =simplCore.getMetaData(dataSource, "");
				 //String metaDataString =simplCore.get
				// metaDataString
				 try {
				      DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
				      DocumentBuilder builder = factory.newDocumentBuilder();
				      InputSource is = new InputSource( new StringReader( metaDataString ) );
				      document = builder.parse( is );
				    }
				    catch( Exception ex ) {
				      ex.printStackTrace();
				    }
				 
				//TODO: es muss noch der SDO objekt von der simplCore geholt werden .
				Element rootElementOfDSO =document.getDocumentElement();//=DSO Element;
				NodeList nl = rootElementOfDSO.getElementsByTagName("table");
				if(nl != null && nl.getLength() > 0) {
				for(int i = 0 ; i < nl.getLength();i++) {
				    
					//get the child element
					if(nl.item(i).getNodeName()=="table"){
						
						Element dsoXMLElement = (Element)nl.item(i);
						if(dsoXMLElement.getTagName()=="table"){
							
							
							tableObjekt=new Table();
							
							
							//dsoXMLElement.getAttribute("value");
							//dsoXMLElement.getAttribute("text");
							
							tableObjekt.setTableName(dsoXMLElement.getAttribute("name"));
							if(dsoXMLElement.hasChildNodes()){
								
								NodeList columsNodesList=dsoXMLElement.getChildNodes();
								
								
								ArrayList<String> listOfColumnsNames=new ArrayList<String>();
								Element columXMLElement; // = (Element)nl.item(i);
								for(int j = 0 ; j < columsNodesList.getLength();j++) {
									//get the child element
									if(columsNodesList.item(j).getNodeName()=="column"){
										System.out.print(columsNodesList.item(j).getNodeName()+"\r");
										columXMLElement = (Element)columsNodesList.item(j);
										listOfColumnsNames.add(columXMLElement.getAttribute("name"));
									}
								}
								tableObjekt.setListOfColumnsNames(listOfColumnsNames);
							//add it to list
							}	
						}
					}
					listOfTableObjekts.add(tableObjekt);
				}
				
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
			
			
		return listOfTableObjekts;
		
		}
     
     
     /**
 	 * for parsing the FileSystem Elements from document.
 	 * parsing the XML elements into four lists of FileSystemElements
 	 * 		listOfFileSysElements_command
			listOfFileSysElements_file
			listOfFileSysElements_drive
			listOfFileSysElements_folder
 	 * 
 	 * to get the list use the get methods.
 	 */
      public void loadFileSystemElementsFromDB(DataSource dataSource) {
 			
 			
 		//++++++++++++++++++++++++DSO Parsing++++++++++++++++++++++++
 		SIMPLCore simplCore=SIMPLCommunication.getConnection();
 		try {
 				
 				 String metaDataString =simplCore.getMetaData(dataSource, "");
 				 //String metaDataString =simplCore.get
 				// metaDataString
 				 try {
 				      DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
 				      DocumentBuilder builder = factory.newDocumentBuilder();
 				      InputSource is = new InputSource( new StringReader( metaDataString ) );
 				      document = builder.parse( is );
 				    }
 				    catch( Exception ex ) {
 				      ex.printStackTrace();
 				    }
 				 
 				//TODO: es muss noch der SDO objekt von der simplCore geholt werden .
 				Element rootElementOfDSO =document.getDocumentElement();//=DSO Element;

 				NodeList commandsNodeList = rootElementOfDSO.getElementsByTagName("command");
 				NodeList driveNodeList = rootElementOfDSO.getElementsByTagName("drive");
 				NodeList fileNodeList = rootElementOfDSO.getElementsByTagName("file");
 				NodeList folderNodeList = rootElementOfDSO.getElementsByTagName("folder");
 				
 				if(commandsNodeList != null && commandsNodeList.getLength() > 0) {
	 				for(int i = 0 ; i < commandsNodeList.getLength();i++) {
	 					//get the child element
	 					if(commandsNodeList.item(i).getNodeName()=="command"){
	 						Element dsoXMLElement = (Element)commandsNodeList.item(i);
	 						if(dsoXMLElement.getTagName()=="command"){
	 							fileSysObjekt=new FileSystemElement();
	 							fileSysObjekt.setCommandName(dsoXMLElement.getAttribute("name"));
	 							fileSysObjekt.setCommandDescription(dsoXMLElement.getAttribute("description"));	
	 						}
	 					}
	 					listOfFileSysElements_command.add(fileSysObjekt);
	 				}
 				}
 				if(driveNodeList != null && driveNodeList.getLength() > 0) {
	 				for(int i = 0 ; i < driveNodeList.getLength();i++) {
	 					//get the child element
	 					if(driveNodeList.item(i).getNodeName()=="drive"){
	 						Element dsoXMLElement = (Element)driveNodeList.item(i);
	 						if(dsoXMLElement.getTagName()=="drive"){
	 							fileSysObjekt=new FileSystemElement();
	 							fileSysObjekt.setDriveName(dsoXMLElement.getAttribute("name"));
	 							fileSysObjekt.setDrive_info("Letter:	"+dsoXMLElement.getAttribute("letter")+"\r"+
	 									"Total space:	"+dsoXMLElement.getAttribute("totalSpace")+"\r"+
	 									"Free space:	"+dsoXMLElement.getAttribute("freeSpace")+"\r"+
	 									"Useable space:	"+dsoXMLElement.getAttribute("useableSpace")+"\r"+
	 									"Writable:	"+dsoXMLElement.getAttribute("writable")+"\r"+
	 									"Readable:	"+dsoXMLElement.getAttribute("readable")+"\r"+
	 									"Executable:	"+dsoXMLElement.getAttribute("executable")+"\r"+
	 									"Type:	"+dsoXMLElement.getAttribute("type"));	
	 						}
	 					}
	 					listOfFileSysElements_drive.add(fileSysObjekt);
	 				}
 				}
 				if(fileNodeList != null && fileNodeList.getLength() > 0) {
	 				for(int i = 0 ; i < fileNodeList.getLength();i++) {
	 					//get the child element
	 					if(fileNodeList.item(i).getNodeName()=="file"){
	 						Element dsoXMLElement = (Element)fileNodeList.item(i);
	 						if(dsoXMLElement.getTagName()=="file"){
	 							fileSysObjekt=new FileSystemElement();
	 							fileSysObjekt.setFileName(dsoXMLElement.getAttribute("name"));
	 								 						}
	 					}
	 					listOfFileSysElements_file.add(fileSysObjekt);
	 				}
 				}
 				if(folderNodeList != null && folderNodeList.getLength() > 0) {
	 				for(int i = 0 ; i < folderNodeList.getLength();i++) {
	 					//get the child element
	 					if(fileNodeList.item(i).getNodeName()=="folder"){
	 						Element dsoXMLElement = (Element)fileNodeList.item(i);
	 						if(dsoXMLElement.getTagName()=="folder"){
	 							fileSysObjekt=new FileSystemElement();
	 							fileSysObjekt.setFolderName(dsoXMLElement.getAttribute("name"));
	 								 						}
	 					}
	 					listOfFileSysElements_file.add(fileSysObjekt);
	 				}
 				}
 				
 			}
 			catch (Exception e) {
 				e.printStackTrace();
 			}
 			
 			
 			
 		
 		}
     
}

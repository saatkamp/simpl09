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
	ArrayList<String> arrayOfElements=new ArrayList<String>();
	Document document=null;
	ArrayList<Table> listOfTableObjekts = new ArrayList<Table>();
	Table tableObjekt = null;
	
	
	/**
	 * for adding the tables names from the DB.
	 */
     public ArrayList<Table> loadTablesFromDB(DataSource dataSource) {
			
			
			//++++++++++++++++++++++++DSO Parsing++++++++++++++++++++++++
			SIMPLCore simplCore=SIMPLCommunication.getConnection();
			try {
				
				 String metaDataString =simplCore.getMetaData(dataSource, "");
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
							
							arrayOfElements.add(dsoXMLElement.getAttribute("name"));
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
										columXMLElement = (Element)columsNodesList.item(j);
										listOfColumnsNames.add(columXMLElement.getAttribute("name"));
									}
								}
								tableObjekt.setListOfColumnsNames(listOfColumnsNames);
							//add it to list
							}	
						}
					}
				}
				listOfTableObjekts.add(tableObjekt);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
			
			
		return listOfTableObjekts;
		
		}
     
}

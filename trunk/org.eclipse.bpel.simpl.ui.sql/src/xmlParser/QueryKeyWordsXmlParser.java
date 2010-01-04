package xmlParser;

import java.awt.FlowLayout;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;




public class QueryKeyWordsXmlParser {

	//TODO: hier kommen die funktionen zum parsen der xml elemente
	//von der quaryKeyWords xml file.
	
	Composite myComp;
	public Composite getMyComp() {
		return myComp;
	}


	public void setMyComp(Composite myComp) {
		this.myComp = myComp;
	}


	Document dom;
	public ArrayList myEmpls = new ArrayList();
	ArrayList objectsList=new ArrayList();
	
	ArrayList listOfUI_Elements = new ArrayList<String>();
	
	public QueryKeyWordsXmlParser()
	{
		
	}
	
	
	public void parseXmlFile(String fileName){
		//get the factory
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

		try {

			
			System.out.print("\n in parseXmlFile()");
			//Using factory get an instance of document builder
			DocumentBuilder db = dbf.newDocumentBuilder();

			//parse using builder to get DOM representation of the XML file
			dom = db.parse(fileName);


		}catch(Exception e) {
			e.getMessage();
		}
	}
	
	public ArrayList<KeyWord> parseDocument(){
		
		//get the root element
		//myComp=mainComp;
		//FlowLayout flowLayout = new FlowLayout();
		//myComp.setLayoutData(flowLayout);
		
		//Label label=new Label(myComp, SWT.NONE);
		//label.setText("dsgdfghdfgh dsfhdfghdfghdfgh");
		
		
		//Label labelTest=new Label(myComp, SWT.NONE);
		//labelTest.setText("jshdf kjshd kj kjas jhs d");
		Element docEle = dom.getDocumentElement();
		ArrayList<KeyWord> listOfKeyWords=new ArrayList<KeyWord>();
		//get a nodelist of  elements
		NodeList nl = docEle.getElementsByTagName("MainKeyWord");
		if(nl != null && nl.getLength() > 0) {
			for(int i = 0 ; i < nl.getLength();i++) {
				
				//keyWord.setSize(20, 20);
				
//				//get the KeyWord child element
				if(nl.item(i).getNodeName()=="MainKeyWord"){
					KeyWord keyWord=new KeyWord();
					Element mainKeyWordElement = (Element)nl.item(i);
					if(mainKeyWordElement.getTagName()=="MainKeyWord"){
						
						
						keyWord.setMainKeyWord(mainKeyWordElement.getAttribute("value"));
						keyWord.setTheMajorKey(true);
						
						if(mainKeyWordElement.hasChildNodes()){
								keyWord.setListOfSubKeyWords(parseSubKeyWord(mainKeyWordElement));					
						}
						System.out.print("\n ***"+keyWord.getMainKeyWord());
						listOfKeyWords.add(keyWord);
						
										//add it to list
						
					}	
				}
			}
		}
		return listOfKeyWords;
	}


	private ArrayList<KeyWord> parseSubKeyWord(Element mainKeyWordElement) {
		// TODO Auto-generated method stub
		System.out.print("\n *** in parseSubKeyWord()");
		
		
		
		ArrayList<KeyWord> listOfKeyWords=new ArrayList<KeyWord>();
		NodeList nl = mainKeyWordElement.getChildNodes();
		
		
		if(nl != null && nl.getLength() > 0) {
			for(int i = 0 ; i < nl.getLength();i++) {
				
				//get the KeyWord child element
				if(nl.item(i).getNodeName()=="KeyWord"){
					KeyWord keyWord=new KeyWord();
					//keyWord.setSize(20, 20);
					
					Element subKeyWordElement = (Element)nl.item(i);
					if(subKeyWordElement.getTagName()=="KeyWord"){
	
						keyWord.setMainKeyWord(subKeyWordElement.getAttribute("value"));
						
						if(subKeyWordElement.hasChildNodes()){
							keyWord.setListOfSubKeyWords(parseSubKeyWord(subKeyWordElement));
			
						}
						
						System.out.print("\n ***"+keyWord.getMainKeyWord());
						
						listOfKeyWords.add(keyWord);
					}
				}
				//add it to list
				
			}
			
			
		}
		return listOfKeyWords;
	}
	
}

package xmlParser;

import java.awt.FlowLayout;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;




public class QueryKeyWordsXmlParser {

	//TODO: hier kommen die funktionen zum parsen der xml elemente
	//von der quaryKeyWords xml file.
	ArrayList<EditorButton> buttonList=new ArrayList<EditorButton>();
	
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
			dom = db.parse(getClass().getResourceAsStream(fileName));
			
		}catch(Exception e) {
			e.printStackTrace();
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
						keyWord.setTextOfKEyWord(mainKeyWordElement.getAttribute("text"));
						
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
						keyWord.setTextOfKEyWord(subKeyWordElement.getAttribute("text"));
						
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
	
	/**
	 * For creating the buttons out of the xml file ,wich contains
	 * the key words of the quary language. And after creating they
	 * will be added into the composite.
	 * 
	 * in this function we creat the buttons for the parsed KeyWords.
	 * 
	 * @param listOfMainKeyWords
	 */
	public void creatButtonsOfKeyWords(Composite buttonsComposite,final ArrayList<KeyWord> listOfMainKeyWords){
		//System.out.print("\n in creatButtonsOfKeyWords()");
		
		for(int i=0;i<listOfMainKeyWords.size();i++)
		{
			if((listOfMainKeyWords.get(i).getListOfSubKeyWords().size()>0)){
				creatButtonsOfKeyWords(buttonsComposite,listOfMainKeyWords.get(i).getListOfSubKeyWords());
			}
			final EditorButton keyWordAsButton=new EditorButton(myComp, SWT.NONE);
			keyWordAsButton.setText(listOfMainKeyWords.get(i).getMainKeyWord());
			keyWordAsButton.setTextOfAction(listOfMainKeyWords.get(i).getTextOfKEyWord());
			
			keyWordAsButton.setSize(20, 10);
			if(!listOfMainKeyWords.get(i).isTheMajorKey()){
				keyWordAsButton.setEnabled(false);
			}
			//else isInsertKeyWord=false;
			
			final KeyWord tmpKeyWord=listOfMainKeyWords.get(i);
			keyWordAsButton.addSelectionListener(new SelectionListener() {
				@Override
				public void widgetDefaultSelected(SelectionEvent e) {
					// TODO Auto-generated method stub
					widgetSelected(e);
				}

				@Override
				public void widgetSelected(SelectionEvent e) {
					// TODO hier muss der statement befehle in der textBox eingetragen werden.
					
					/*
					 * in the following for statement all the buttons are only
					 * then enabled if the father button (according to the Logik in the parsed xmlFile)
					 */
					keyWordAsButton.setEnabled(false);
					
					for(int x=0;x<buttonList.size();x++){
						//if(buttonList.get(x).getText().equals(e.text)){buttonList.get(x).setEnabled(false);}
						buttonList.get(x).setEnabled(false);
						for(int j=0;j<tmpKeyWord.getListOfSubKeyWords().size();j++){
							//
							if(tmpKeyWord.getListOfSubKeyWords().get(j).getMainKeyWord().equals(buttonList.get(x).getText())){
								buttonList.get(x).setEnabled(true);
							}
							
						}
						
						
					}
					
					//TODO: hier muss der befehle in der textBox eingefügt werden
					
//					fatherComp.getShell().getData("StyledText")
//					s.setStatementText("sdfsdf");
				}
			});
			
			buttonList.add(keyWordAsButton);

		}
		
	}
	
}

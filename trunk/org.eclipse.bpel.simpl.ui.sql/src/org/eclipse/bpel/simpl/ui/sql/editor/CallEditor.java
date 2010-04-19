package org.eclipse.bpel.simpl.ui.sql.editor;

import java.util.ArrayList;

import org.eclipse.bpel.simpl.ui.extensions.AStatementEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyleRange;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import widgets.LiveEditStyleText;
import xmlParser.KeyWord;
import xmlParser.QueryKeyWordsXmlParser;
import org.eclipse.bpel.simpl.ui.*;

public class CallEditor extends AStatementEditor {

	private Composite comp = null;
	private Composite compos = null;
	//private StyledText statementText = null;
	private LiveEditStyleText statementText=null;
	Label proceLabel;
	Button addToStatement;
	Text proceText;
	
	StyleRange style_Parameter = new StyleRange();
	
	//List of statement Changes
    ArrayList<String> listOfStatementTextChanges=new ArrayList<String>();
    
	
	public ArrayList<String> getListOfStatementTextChanges() {
		return listOfStatementTextChanges;
	}

	public void addToListOfStatementTextChanges() {
		listOfStatementTextChanges.add(statementText.getText());
	}
	
	Display display;
	
	ArrayList<Button> buttonList=new ArrayList<Button>();
	private Composite buttonsCompo=null;
	QueryKeyWordsXmlParser parser=new QueryKeyWordsXmlParser();
	
	/*
	 * The XML file wich contais the statment KeyWords
	 */
	//TODO: den kompleten echten dateipfaden hier rein schreiben
	private String xmlFilePath="/keywords/CallDMActivityXMLFile.xml";
	//gerade nicht in gebrauch
	
	public CallEditor() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void createComposite(Composite composite) {
		display=composite.getDisplay();
		GridData gridData1 = new GridData();
		gridData1.horizontalAlignment = GridData.FILL;
		gridData1.grabExcessHorizontalSpace = true;
		gridData1.grabExcessVerticalSpace = true;
		gridData1.verticalAlignment = GridData.FILL;
		GridData gridData = new GridData();
		gridData.horizontalAlignment = GridData.FILL;
		gridData.grabExcessHorizontalSpace = true;
		gridData.grabExcessVerticalSpace = true;
		gridData.verticalAlignment = GridData.FILL;
		comp = new Composite(composite, SWT.NONE);
		comp.setLayout(new GridLayout());
		GridData gridData2 = new GridData();
		gridData2.horizontalAlignment = GridData.FILL;
		gridData2.grabExcessHorizontalSpace = true;
		gridData2.grabExcessVerticalSpace = true;
		gridData2.verticalAlignment = GridData.FILL;
		
		GridData gridData1_1 = new GridData();
		gridData1_1.horizontalAlignment = GridData.END;
		gridData1_1.grabExcessHorizontalSpace = true;
		gridData1_1.grabExcessVerticalSpace = true;
		gridData1_1.verticalAlignment = GridData.END;
		
		compos = new Composite(comp, SWT.NONE);
		compos.setLayout(new GridLayout());
		compos.setLayoutData(gridData2);
		comp.setLayoutData(gridData);
		
		GridLayout gridLayoutA = new GridLayout();
		gridLayoutA.numColumns = 6;
		parser.parseXmlFile(xmlFilePath);
		buttonsCompo=new Composite(compos, SWT.RIGHT_TO_LEFT);
		buttonsCompo.setLayout(gridLayoutA);
		//buttonsCompo.setVisible(false);
		creatButtonsOfKeyWords(parser.parseDocument());
		
		try {
			//statementText = new StyledText(comp, SWT.BORDER| SWT.H_SCROLL| SWT.V_SCROLL);
			Composite statementCompo=new Composite(comp, SWT.NONE);
			
			statementCompo.setLayout(new GridLayout());
			statementCompo.setLayoutData(gridData2);
			
			statementText=new LiveEditStyleText(statementCompo);
			
			//+++++++++++++undoButton++++++++++++++++++++++++++++++++++
			Button undoButton=new Button(statementCompo, SWT.LEFT);
			undoButton.setLayoutData(gridData1_1);
			undoButton.setText("UNDO");
			undoButton.setToolTipText("UNDO Statement: delete last changes in the Statement.");
			undoButton.addSelectionListener(new SelectionListener() {
				@Override
				public void widgetSelected(SelectionEvent e) {
					if(listOfStatementTextChanges.size()>=2){
						statementText.setText(listOfStatementTextChanges.get(listOfStatementTextChanges.size()-2));
						listOfStatementTextChanges.remove(listOfStatementTextChanges.size()-1);
					}
				}
				
				@Override
				public void widgetDefaultSelected(SelectionEvent e) {
					if(listOfStatementTextChanges.size()>=2){
						statementText.setText(listOfStatementTextChanges.get(listOfStatementTextChanges.size()-2));
						listOfStatementTextChanges.remove(listOfStatementTextChanges.size()-1);
					}
				}
			});
			//+++++++++++++++++end undoButton+++++++++++++++++++++++++
			//TODO: Insert Editor UndoButton implementierung ist fertig . in callEditor muss
			//noch UndoButton Implementierung getestet werden .
			statementText.setLayoutData(gridData2);
			statementText.addModifyListener(new ModifyListener(){
     
				@Override
				public void modifyText(ModifyEvent e) {
					// TODO Auto-generated method stub
					setStatement(statementText.getText());
					
				}});
			
			setComposite(comp);
		} catch (Exception e) {
			System.out.print("ERROR: "+e.getMessage());
		}
		
		try {
			StyleRange style_KeyWord = new StyleRange();
			if (getStatement()!=null){
				statementText.setText(getStatement());
				if(statementText.getText().length()>8){
					if(statementText.getText().equals("statement")){
						style_KeyWord.start = statementText.getText().length();
						statementText.setText("CALL ");
						style_KeyWord.length= statementText.getText().length()-style_KeyWord.start;
						style_KeyWord.foreground  = composite.getDisplay().getSystemColor(SWT.COLOR_MAGENTA);
						statementText.setStyleRange(style_KeyWord);
						
					}
					
				}
				else{
					style_KeyWord.start = statementText.getText().length();
					statementText.setText("CALL ");
					style_KeyWord.length= statementText.getText().length()-style_KeyWord.start;
					style_KeyWord.foreground  = composite.getDisplay().getSystemColor(SWT.COLOR_MAGENTA);
					statementText.setStyleRange(style_KeyWord);
				}
				addToListOfStatementTextChanges();
			}
			else {
				style_KeyWord.start = statementText.getText().length();
				statementText.setText("CALL ");
				style_KeyWord.length= statementText.getText().length()-style_KeyWord.start;
				style_KeyWord.foreground  = composite.getDisplay().getSystemColor(SWT.COLOR_MAGENTA);
				statementText.setStyleRange(style_KeyWord);
			}
			
			
			CreateCallUIElements(compos);
		} catch (Exception e) {
			System.out.print("ERROR: "+e.getMessage());
		}
	}

	/**
	 * 
	 * @param compos2
	 */
	private void CreateCallUIElements(Composite composite) {
		
		GridData gridData2 = new GridData();
		gridData2.horizontalAlignment = GridData.FILL;
		gridData2.grabExcessVerticalSpace = true;
		gridData2.grabExcessHorizontalSpace = true;
		gridData2.verticalAlignment = GridData.CENTER;
		GridData gridData1 = new GridData();
		gridData1.horizontalAlignment = GridData.FILL;
		gridData1.grabExcessVerticalSpace = true;
		gridData1.grabExcessHorizontalSpace = true;
		gridData1.verticalAlignment = GridData.CENTER;
		GridData gridData = new GridData();
		gridData.horizontalAlignment = GridData.BEGINNING;
		gridData.grabExcessVerticalSpace = true;
		gridData.grabExcessHorizontalSpace = true;
		gridData.verticalAlignment = GridData.CENTER;
//		GridLayout gridLayout = new GridLayout();
//		gridLayout.numColumns = 3;
		
		
		proceLabel=new Label(composite, SWT.BORDER);
		
		proceLabel.setText("Procedure: ");
		proceLabel.setLayoutData(gridData1);
		proceText =new Text(composite, SWT.BORDER);
		proceText.setSize(100, 70);
		proceText.setLayoutData(gridData1);
		
		try {
			if(statementText.getText().length()>5){
				String cleandStatment=removeAllSpaces(statementText.getText());
				String[] wordsOfStatment =cleandStatment.split("\r");
				if(wordsOfStatment[0].contains("(")){
//				String[] firstLineWords=wordsOfStatment[0].split(" ");
//				String[] procedureName=firstLineWords[1].split("(");
//				proceText.setText(firstLineWords[0]);
				}
				else{ 
					proceText.setText("");
					
				}
				
			}
			
			addToStatement =new Button(composite, SWT.BORDER);
			addToStatement.setText("Add to Statment");
			addToStatement.addSelectionListener(new SelectionListener(){

				@Override
				public void widgetDefaultSelected(SelectionEvent e) {
					
					StyleRange style_Variable = new StyleRange();
					style_Variable.start = statementText.getText().length();
					statementText.setText(statementText.getText()+"	"+proceText.getText()+"(? ,? , ?, ...)");				
					style_Variable.length= statementText.getText().length()-style_Variable.start;
					style_Variable.foreground  = display.getSystemColor(SWT.COLOR_RED);
					statementText.setStyleRange(style_Variable);
					
					addToListOfStatementTextChanges();
									}

				@Override
				public void widgetSelected(SelectionEvent e) {
					StyleRange style_Variable = new StyleRange();
					style_Variable.start = statementText.getText().length();
					statementText.setText(statementText.getText()+"	"+proceText.getText()+"(? ,? , ?, ...)");				
					style_Variable.length= statementText.getText().length()-style_Variable.start;
					style_Variable.foreground  = display.getSystemColor(SWT.COLOR_RED);
					statementText.setStyleRange(style_Variable);
					
					addToListOfStatementTextChanges();
				}

			});
			
			addToStatement.setLayoutData(gridData);
			
			proceLabel.setEnabled(false);
			proceText.setEnabled(false);
			addToStatement.setEnabled(false);
			
			
			
			
//		final Button callButton =new Button(composite, SWT.BORDER);
//		callButton.setText("CALL");
//		callButton.addSelectionListener(new SelectionListener() {
//			
//			@Override
//			public void widgetSelected(SelectionEvent e) {
//				
//				statementText.setText("CALL");
//				proceLabel.setVisible(true);
//				proceText.setVisible(true);
//				addToStatement.setVisible(true);
//				buttonsCompo.setVisible(true);
//				callButton.setVisible(false);
//				
//			}
//			
//			@Override
//			public void widgetDefaultSelected(SelectionEvent e) {
//				statementText.setText("CALL\r");
//				proceLabel.setVisible(true);
//				proceText.setVisible(true);
//				addToStatement.setVisible(true);
//				buttonsCompo.setVisible(true);
//				
//				callButton.setVisible(false);
//			}
//		});
			
			proceLabel.setEnabled(true);
			proceText.setEnabled(true);
			addToStatement.setEnabled(true);
			buttonsCompo.setEnabled(true);
		} 
		catch (Exception e) {
			System.out.print("ERROR: "+e.getMessage());
		}
	}
	
	
	
	/**
	 * Removing all extra / unnasecerely Spaces in the String .
	 * @param theString
	 * @return cleanedString
	 */
	private String RemoveAllUnnasacerelySpaces(String theString){
		String cleanedString=theString;
		if((cleanedString!=null)&&(cleanedString.length()>1)){
			for(int i=0;i<cleanedString.length();i++){
				cleanedString=cleanedString.replace("  ", " ");
			}
			
		}
		return cleanedString;
	}
	
	/**
	 * removes all spaces from statment
	 * @param statement
	 * @return statmentAsOneString
	 */
	private String removeAllSpaces(String statement) {
		String cleanedString=RemoveAllUnnasacerelySpaces(statement);
		String[] wordsOfCentence = null;
		String statmentAsOneString = "";
		
		if(cleanedString!=null){
			try {
				if(cleanedString.contains(" ")){
					wordsOfCentence=cleanedString.split(" ");
				}
				if(wordsOfCentence!=null){
					for(int i=0;i<wordsOfCentence.length;i++){
						statmentAsOneString=statmentAsOneString+wordsOfCentence[i];
					}
				}
			} catch (Exception e) {
				System.out.print("ERROR: "+e.getMessage());
			}
			
			
			while(statmentAsOneString.contains(" ")){
				wordsOfCentence=cleanedString.split(" ");
				for(int i=0;i<wordsOfCentence.length;i++){
					statmentAsOneString=statmentAsOneString+wordsOfCentence[i];
					
				}
				
			}
		}
		return statmentAsOneString;
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
	public void creatButtonsOfKeyWords(final ArrayList<KeyWord> listOfMainKeyWords){
		//System.out.print("\n in creatButtonsOfKeyWords()");
		
		for(int i=0;i<listOfMainKeyWords.size();i++)
		{
			if((listOfMainKeyWords.get(i).getListOfSubKeyWords().size()>0)){
				try {
					creatButtonsOfKeyWords(listOfMainKeyWords.get(i).getListOfSubKeyWords());
				} catch (Exception e) {
					System.out.print("ERROR: "+e.getMessage());
				}
			}
			if(!(listOfMainKeyWords.get(i).getMainKeyWord().equals("CALL"))){	
				final Button keyWordAsButton=new Button(buttonsCompo, SWT.NONE);
				try {
					keyWordAsButton.setText(listOfMainKeyWords.get(i).getMainKeyWord());
					//keyWordAsButton.setTextOfAction(listOfMainKeyWords.get(i).getTextOfKEyWord());
					keyWordAsButton.setImage(new Image(Display.getCurrent(), getClass().getResourceAsStream("/icons/buttonIconORANGE.png")));
					
					keyWordAsButton.setSize(listOfMainKeyWords.get(i).getMainKeyWord().length()+20, 70);
				} catch (Exception e1) {
					System.out.print("ERROR: "+e1.getMessage());
				}
//				if(!listOfMainKeyWords.get(i).isTheMajorKey()){
//					keyWordAsButton.setImage(new Image(Display.getCurrent(), getClass().getResourceAsStream("/icons/buttonIconGRAY.png")));
//				}
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
						keyWordAsButton.setImage(new Image(Display.getCurrent(), getClass().getResourceAsStream("/icons/buttonIconORANGE.png")));
						
						for(int x=0;x<buttonList.size();x++){
							//if(buttonList.get(x).getText().equals(e.text)){buttonList.get(x).setEnabled(false);}
							buttonList.get(x).setImage(new Image(Display.getCurrent(), getClass().getResourceAsStream("/icons/buttonIconGRAY.png")));
							for(int j=0;j<tmpKeyWord.getListOfSubKeyWords().size();j++){
								//
								if(tmpKeyWord.getListOfSubKeyWords().get(j).getMainKeyWord().equals(buttonList.get(x).getText())){
									try {
										buttonList.get(x).setImage(new Image(Display.getCurrent(), getClass().getResourceAsStream("/icons/buttonIconORANGE.png")));
									} catch (Exception e1) {
										// TODO Auto-generated catch block
										System.out.print("ERROR: "+e1.getMessage());
									}
								}
								
							}
							
							
						}
//						
//						style_KeyWord.start = statementText.getText().length();
						statementText.setText(statementText.getText()+"\r"+tmpKeyWord.getTextOfKEyWord());
						addToListOfStatementTextChanges();
						
//						style_KeyWord.length= statementText.getText().length()-style_KeyWord.start;
//						style_KeyWord.foreground  = display.getSystemColor(SWT.COLOR_BLUE);
//						statementText.setStyleRange(style_KeyWord);
						
						proceLabel.setEnabled(true);
						proceText.setEnabled(true);
						addToStatement.setEnabled(true);
						buttonsCompo.setEnabled(true);
						
	//					if(tmpKeyWord.getMainKeyWord().equals("CALL")){
	//						proceLabel.setEnabled(true);
	//						proceText.setEnabled(true);
	//						addToStatement.setEnabled(true);
	//						buttonsCompo.setEnabled(true);
	//						statementText.setText("CALL ");
	//					}
	//					fatherComp.getShell().getData("StyledText")
	//					s.setStatementText("sdfsdf");
					}
				});
				
				buttonList.add(keyWordAsButton);
			}
		}
		
	}

}

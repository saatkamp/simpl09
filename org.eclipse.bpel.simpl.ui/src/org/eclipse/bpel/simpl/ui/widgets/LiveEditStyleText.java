/**
 * <b>Purpose:</b> <br>
 * <b>Description:</b> <br>
 * <b>Copyright:</b>  Licensed under the Apache License, Version 2.0. http://www.apache.org/licenses/LICENSE-2.0<br>
 * <b>Company:</b> SIMPL<br>
 * 
 * @author Firas Zoabi <zoabifs@studi.informatik.uni-stuttgart.de> <br>
 * @version $Id$ <br>
 * @link http://code.google.com/p/simpl09/
 *
 */
package org.eclipse.bpel.simpl.ui.widgets;

import java.util.LinkedList;

import org.eclipse.bpel.simpl.ui.properties.DMActivityPropertySection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.LineStyleEvent;
import org.eclipse.swt.custom.LineStyleListener;
import org.eclipse.swt.custom.StyleRange;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;

// TODO: Auto-generated Javadoc
/**
 * The Class LiveEditStyleText.
 */
public class LiveEditStyleText extends StyledText{
  

  /** The BPE l_ variable. */
  String BPEL_VARIABLE="BPEL_VARIABLE";
  
  /** The KEYWORD. */
  String KEYWORD="KEYWORD";
  
  /** The PARAMETER. */
  String PARAMETER="PARAMETER";
  
  String KOMPLEX_PARAMETER="KOMPLEX-PARAMETER",SIMPLE_PARAMETER="SIMPLE-PARAMETER";
  
  /** The TABL e_ name. */
  String TABLE_NAME="TABLE_NAME";
  
  /** The DEFAUL t_ text. */
  String DEFAULT_TEXT="DEFAULT_BLACK_TEXT";
  
  /** The keyword. */
  String keyword;
  
  /** The keywords. */
  String[] keywords={"SELECT","INSERT","CALL","INTO","DROP","DELETE","WHERE","IN","INOUT","GROUP BY","<",">","=","LIKE","ORDER BY","AVG","MAX","MIN","SUM","COUNT","ALL","DISTINCT","UPDATE","SCHEMA","TABLE","CREATE","FROM"};
  
  /** The tables. */
  String[] tables={"Table1","Table2","Table3","Table4","Table5","Table6","Table7","Table8","Table9"};
  
  /** The list. */
  LinkedList<StyleRange> list = new LinkedList<StyleRange>();
  
  /**
	 * Instantiates a new live edit style text.
	 * 
	 * @param theComposite
	 *            the the composite
	 */
  public LiveEditStyleText(Composite theComposite) {
    
	  //super(shell, SWT.MULTI | SWT.WRAP | SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
	  super(theComposite,SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);

	  //shell.setLayout(new GridLayout(2, false));
    
    
   
    //Checking Live if the typed text a KeyWord or a Parameter etc.
    this.addLineStyleListener(new LineStyleListener() {
      
    	public void lineGetStyle(LineStyleEvent event) {
    		
    		
        	
    		
        	String line = event.lineText;
        	int cursor = -1;
        	int index=0;
        	//String cleanedStringFromExtraSpaces=RemoveExtraSpaces(line);
        	line=line.replaceAll("  ", " ");
        	String[] wordsOfSyleText=line.split(" ");
        	String typedWord;
        	
	        	for(int i=0;i<wordsOfSyleText.length;i++){
		        	typedWord=wordsOfSyleText[i];
		        	index=index+typedWord.length();
		        	if(isEqualToKeyword(typedWord)){
//		        		System.out.print("is a keyword");
		      	          //list.add(getHighlightStyle(styledText.getText().length()-lastTypedWord.length(), styledText.getText().length()-1));
			    	    if( (cursor = line.indexOf(typedWord, cursor+1)) >= 0) {
			  	          list.add(getHighlightStyle(event.lineOffset+cursor, typedWord.length(),KEYWORD));
			  	        }  
		        		
		        		//list.add(getHighlightStyle(index, typedWord.length()));
		        	}
		        	else if(isEqualToTableName(typedWord)){
//		        		System.out.print("is a keyword");
		      	          //list.add(getHighlightStyle(styledText.getText().length()-lastTypedWord.length(), styledText.getText().length()-1));
		        		String tmpWord=removeSpacesFromWord(typedWord);
		        		if( (cursor = line.indexOf(typedWord, cursor+1)) >= 0) {
			  	          list.add(getHighlightStyle(event.lineOffset+cursor, tmpWord.length(),TABLE_NAME));
			  	        }  
		        		
		        		//list.add(getHighlightStyle(index, typedWord.length()));
		        	}
		        	else if(isEqualToSimplParameter(typedWord)){
//		        		System.out.print("is a keyword");
		      	          //list.add(getHighlightStyle(styledText.getText().length()-lastTypedWord.length(), styledText.getText().length()-1));
			    	    if( (cursor = line.indexOf(typedWord, cursor+1)) >= 0) {
			  	          list.add(getHighlightStyle(event.lineOffset+cursor, typedWord.length(),SIMPLE_PARAMETER));
			  	        }  
		        		
		        		//list.add(getHighlightStyle(index, typedWord.length()));
		        	}
		        	else if(isEqualToKomplexParameter(typedWord)){
//		        		System.out.print("is a keyword");
		      	          //list.add(getHighlightStyle(styledText.getText().length()-lastTypedWord.length(), styledText.getText().length()-1));
			    	    if( (cursor = line.indexOf(typedWord, cursor+1)) >= 0) {
			  	          list.add(getHighlightStyle(event.lineOffset+cursor, typedWord.length(),KOMPLEX_PARAMETER));
			  	        }  
		        		
		        		//list.add(getHighlightStyle(index, typedWord.length()));
		        	}
		        	
		        	else if(isEqualToBPLEVariable(typedWord)){
//		        		System.out.print("is a keyword");
		      	          //list.add(getHighlightStyle(styledText.getText().length()-lastTypedWord.length(), styledText.getText().length()-1));
			    	    if( (cursor = line.indexOf(typedWord, cursor+1)) >= 0) {
			  	          list.add(getHighlightStyle(event.lineOffset+cursor, typedWord.length(),BPEL_VARIABLE));
			  	        }  
		        		
		        		//list.add(getHighlightStyle(index, typedWord.length()));
		        	}
		        	
		        	else{
		        		 if( (cursor = line.indexOf(typedWord, cursor+1)) >= 0) {
				  	          list.add(getHighlightStyle(event.lineOffset+cursor, typedWord.length(),DEFAULT_TEXT));
				  	        } 
		        	}
		        	
			        event.styles = list.toArray(new StyleRange[list.size()]);
	        	}
	        	
//	        	objectDMPropertySection.setStatement(getText());
//	        	if(objectDMPropertySection!=null){
//	        		objectDMPropertySection.saveStatementToModel();
//		        	objectDMPropertySection.setStatement(getText());
//	        	}
	        	
	        	
        	}

      
	private String RemoveExtraSpaces(String text) {
		for(int i=0;i<text.length();i++)
		{
			//if(text.contains("  ")) 
				text.replaceAll( "  ", " ");
			
		}
//		System.out.print("\r**"+text+"**");
		return text;
	}


	private String removeSpacesFromWord(String typedWord) {
		while(typedWord.contains(" ")){
			typedWord.replace(" ", "");
		}
		return typedWord;
	}

	
	      
    });
    
   
	  
    
    redraw();
    //styledText.setText("AWT, SWING \r\nSWT & JFACE");
    
//    shell.pack();
//    shell.open();
//    //textUser.forceFocus();
//
//    // Set up the event loop.
//    while (!shell.isDisposed()) {
//      if (!display.readAndDispatch()) {
//        // If no more entries in event queue
//        display.sleep();
//      }
//    }
//
//    display.dispose();
    
    //this.setText(objectDMPropertySection.getStatement());
  }
  
//  public LiveEditStyleText(Composite theComposite) {
//	    
//	  //super(shell, SWT.MULTI | SWT.WRAP | SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
//	  super(theComposite,SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
//
//	  //shell.setLayout(new GridLayout(2, false));
//
//    //Checking Live if the typed text a KeyWord or a Parameter etc.
//     addLineStyleListener(new LineStyleListener() {
//      public void lineGetStyle(LineStyleEvent event) {
//        	String line = event.lineText;
//        	int cursor = -1;
//        	int index=0;
//        	
//        	String[] wordsOfSyleText=getText().split(" ");
//        	String typedWord;
//        	
//	        	for(int i=0;i<wordsOfSyleText.length;i++){
//		        	typedWord=wordsOfSyleText[i];
//		        	index=index+typedWord.length();
//		        	if(isEqualToKeyword(typedWord)){
//		        		System.out.print("is a keyword");
//		      	          //list.add(getHighlightStyle(styledText.getText().length()-lastTypedWord.length(), styledText.getText().length()-1));
//			    	    if( (cursor = line.indexOf(typedWord, cursor+1)) >= 0) {
//			  	          list.add(getHighlightStyle(event.lineOffset+cursor, typedWord.length(),KEYWORD));
//			  	        }  
//		        		
//		        		//list.add(getHighlightStyle(index, typedWord.length()));
//		        	}
//		        	else if(isEqualToTableName(typedWord)){
//		        		System.out.print("is a keyword");
//		      	          //list.add(getHighlightStyle(styledText.getText().length()-lastTypedWord.length(), styledText.getText().length()-1));
//		        		String tmpWord=removeSpacesFromWord(typedWord);
//		        		if( (cursor = line.indexOf(typedWord, cursor+1)) >= 0) {
//			  	          list.add(getHighlightStyle(event.lineOffset+cursor, tmpWord.length(),TABLE_NAME));
//			  	        }  
//		        		
//		        		//list.add(getHighlightStyle(index, typedWord.length()));
//		        	}
//		        	else if((isEqualToKomplexParameter(typedWord))||(isEqualToSimplParameter(typedWord))){
//		        		System.out.print("is a keyword");
//		      	          //list.add(getHighlightStyle(styledText.getText().length()-lastTypedWord.length(), styledText.getText().length()-1));
//			    	    if( (cursor = line.indexOf(typedWord, cursor+1)) >= 0) {
//			  	          list.add(getHighlightStyle(event.lineOffset+cursor, typedWord.length(),PARAMETER));
//			  	        }  
//		        		
//		        		//list.add(getHighlightStyle(index, typedWord.length()));
//		        	}
//		        	else if(isEqualToBPLEVariable(typedWord)){
//		        		System.out.print("is a keyword");
//		      	          //list.add(getHighlightStyle(styledText.getText().length()-lastTypedWord.length(), styledText.getText().length()-1));
//			    	    if( (cursor = line.indexOf(typedWord, cursor+1)) >= 0) {
//			  	          list.add(getHighlightStyle(event.lineOffset+cursor, typedWord.length(),BPEL_VARIABLE));
//			  	        }  
//		        		
//		        		//list.add(getHighlightStyle(index, typedWord.length()));
//		        	}
//		        	
//		        	else{
//		        		 if( (cursor = line.indexOf(typedWord, cursor+1)) >= 0) {
//				  	          list.add(getHighlightStyle(event.lineOffset+cursor, typedWord.length(),DEFAULT_TEXT));
//				  	        } 
//		        	}
//		        	
//			        event.styles = list.toArray(new StyleRange[list.size()]);
//	        	}
//        	}
//
//      
//	private String removeSpacesFromWord(String typedWord) {
//		while(typedWord.contains(" ")){
//			typedWord.replace(" ", "");
//		}
//		return typedWord;
//	}
//
//	
//	      
//    });
//    
//    
//    redraw();
//    //styledText.setText("AWT, SWING \r\nSWT & JFACE");
//    
////    shell.pack();
////    shell.open();
////    //textUser.forceFocus();
////
////    // Set up the event loop.
////    while (!shell.isDisposed()) {
////      if (!display.readAndDispatch()) {
////        // If no more entries in event queue
////        display.sleep();
////      }
////    }
////
////    display.dispose();
//  }
  
  

  
  
  /**
   * checking if the keywords-array contains the word
   * @param word
   * @return boolean
   */
  private boolean isEqualToKeyword(String word){
	  for(int i=0;i<keywords.length;i++){ 
		  if(keywords[i].toLowerCase().equals(word.toLowerCase())){
			  return true;
		  }
	  }
	  return false;
  }
  
  /**
   * checking if the word  is a BPELVariabel
   * @param word
   * @return boolean
   */
  private boolean isEqualToBPLEVariable(String typedWord) {
		// TODO Auto-generated method stub
		return false;
  }

  /**
   * checking if the word  is a Parameter
   * @param word
   * @return boolean
   */
  private boolean isEqualToSimplParameter(String typedWord) {
	  //char[] charsOfWord = null;
	  //typedWord.charAt(0).getChars(0, typedWord.length()-1, charsOfWord, 0);
	  if((typedWord.startsWith("#"))&&(typedWord.endsWith("#"))&&(typedWord.length()>1)){
		  return true;
	  }
		return false;
  }
  
  /**
   * checking if the word  is a Parameter
   * @param word
   * @return boolean
   */
  private boolean isEqualToKomplexParameter(String typedWord) {
	  //char[] charsOfWord = null;
	  //typedWord.charAt(0).getChars(0, typedWord.length()-1, charsOfWord, 0);
	  if((typedWord.startsWith("["))&&(typedWord.endsWith("]"))&&(typedWord.length()>1)){
		  return true;
	  }
		return false;
  }

  /**
   * checking if the word  is a Table name
   * @param word
   * @return boolean
   */
  private boolean isEqualToTableName(String typedWord) {
	 
	for(int i=0;i<tables.length;i++){ 
	  if((typedWord.startsWith("#"))&&(typedWord.endsWith("#"))&&(typedWord.length()>1)){
		  if(typedWord.toLowerCase().contains(tables[i].toLowerCase())){
			  return true;
		  }
			 
	  }
	}
	  return false;
  }
  
  
  /**
   * gives the right color for the typed words in the statement
   * @param startOffset
   * @param length
   * @param typeOfWord
   * @return
   */
  private StyleRange getHighlightStyle(int startOffset, int length, String typeOfWord) {
	 
	StyleRange styleRange = new StyleRange();
    
	styleRange.start = startOffset;
    styleRange.length = length;
    if(typeOfWord==BPEL_VARIABLE){
    	 styleRange.foreground = Display.getCurrent().getSystemColor(SWT.COLOR_BLUE);
    }
    if(typeOfWord==KEYWORD)	{
    	 styleRange.foreground = Display.getCurrent().getSystemColor(SWT.COLOR_DARK_MAGENTA);
    }
    if(typeOfWord==PARAMETER){
    	 styleRange.foreground = Display.getCurrent().getSystemColor(SWT.COLOR_BLUE);
    }
    if(typeOfWord==KOMPLEX_PARAMETER){
   	 styleRange.foreground = Display.getCurrent().getSystemColor(SWT.COLOR_GREEN);
    }
    if(typeOfWord==SIMPLE_PARAMETER){
   	 styleRange.foreground = Display.getCurrent().getSystemColor(SWT.COLOR_BLUE);
   }
    
    if(typeOfWord==TABLE_NAME){
   	 styleRange.foreground = Display.getCurrent().getSystemColor(SWT.COLOR_BLUE);
    }
    if(typeOfWord==DEFAULT_TEXT){
      	 styleRange.foreground = Display.getCurrent().getSystemColor(SWT.COLOR_BLACK);
    }
   
    return styleRange;
  }

  
//  public void setVisible(boolean b) {
//	super.setVisible(b);
//  }
//
////  public void setBackground(Color systemColor) {
////	setBackground(systemColor);
////	
////  }
//
//public void setLayoutData(GridData gridData1) {
//	setLayoutData(gridData1);
//}
//
//public void setEditable(boolean b) {
//	setEditable(b);
//}
//
//public void setText(String statement) {
//	super.setText(statement);	
//}
//public String getText(){
//	return super.getText();
//}

//
//  public static void main(String[] args) {
//    new testStyleText();
//  }
//  
}

package widgets;

import java.util.LinkedList;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.LineStyleEvent;
import org.eclipse.swt.custom.LineStyleListener;
import org.eclipse.swt.custom.StyleRange;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

public class LiveEditStyleText {
  Display display = new Display();
  Shell shell = new Shell(display);

  StyledText styledText;
  Text keywordText;
  Button button;
  
  String keyword;
  String[] keywords={"SELECT","INSERT","CALL","INTO","DROP"};
  LinkedList<StyleRange> list = new LinkedList<StyleRange>();
  
  public LiveEditStyleText() {
    shell.setLayout(new GridLayout(2, false));
    
    styledText = new StyledText(shell, SWT.MULTI | SWT.WRAP | SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
    GridData gridData = new GridData(GridData.FILL_BOTH);
    gridData.horizontalSpan = 2;    
    styledText.setLayoutData(gridData);
    
   
    //Checking Live if the typed text a KeyWord or a Parameter etc.
    styledText.addLineStyleListener(new LineStyleListener() {
      public void lineGetStyle(LineStyleEvent event) {
        	String line = event.lineText;
        	int cursor = -1;
        	
        	String[] wordsOfSyleText=styledText.getText().split(" ");
        	String lastTypedWord=wordsOfSyleText[wordsOfSyleText.length-1];
        	if(isEqualToKeyword(lastTypedWord)){
        		System.out.print("is a keyword");
        		
      	          //list.add(getHighlightStyle(styledText.getText().length()-lastTypedWord.length(), styledText.getText().length()-1));
	    	    if( (cursor = line.indexOf(lastTypedWord, cursor+1)) >= 0) {
	  	          list.add(getHighlightStyle(event.lineOffset+cursor, lastTypedWord.length()));
	  	          System.out.print("text area ist changed");
	  	        }
      	        
        	}
        	
	        event.styles = list.toArray(new StyleRange[list.size()]);
	      }
    });
    
    
    styledText.redraw();
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
  }
  
  /**
   * checking if the keywords-array contains the word
   * @param word
   * @return boolean
   */
  private boolean isEqualToKeyword(String word){
	  for(int i=0;i<keywords.length;i++){ 
		  if(keywords[i].equals(word)){
			  return true;
		  }
	  }
	  return false;
  }
  
  private StyleRange getHighlightStyle(int startOffset, int length) {
    StyleRange styleRange = new StyleRange();
    styleRange.start = startOffset;
    styleRange.length = length;
    styleRange.foreground = shell.getDisplay().getSystemColor(SWT.COLOR_DARK_MAGENTA);
    return styleRange;
  }

//
//  public static void main(String[] args) {
//    new testStyleText();
//  }
//  
}

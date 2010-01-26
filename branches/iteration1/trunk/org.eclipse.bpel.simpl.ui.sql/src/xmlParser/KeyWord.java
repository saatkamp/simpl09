package xmlParser;

import java.util.ArrayList;

import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;

public class KeyWord /*extends Button*/{

	public KeyWord(/*Composite parent,int style*/) {
		//super(parent, style);
		//setText(getMainKeyWord());
		
		
		// TODO Auto-generated constructor stub
	}
	
	String textOfKEyWord="";
	
	public String getTextOfKEyWord() {
		return textOfKEyWord;
	}
	public void setTextOfKEyWord(String textOfKEyWord) {
		this.textOfKEyWord = textOfKEyWord;
	}
	

	boolean isTheMajorKey=false;
	public boolean isTheMajorKey() {
		return isTheMajorKey;
	}
	public void setTheMajorKey(boolean isTheMajorKey) {
		this.isTheMajorKey = isTheMajorKey;
	}

	String mainKeyWord;
	ArrayList<KeyWord> listOfSubKeyWords=new ArrayList<KeyWord>();
	
	
	
	public String getMainKeyWord() {
		return mainKeyWord;
	}
	public void setMainKeyWord(String mainKeyWord) {
		this.mainKeyWord = mainKeyWord;
	}
	public ArrayList<KeyWord> getListOfSubKeyWords() {
		return listOfSubKeyWords;
	}
	public void setListOfSubKeyWords(ArrayList<KeyWord> listOfSubKeyWords) {
		this.listOfSubKeyWords = listOfSubKeyWords;
	}
}

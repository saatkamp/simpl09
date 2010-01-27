package xmlParser;

import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;

public class EditorButton extends Button{

	String textOfAction=null;
	
	
	public String getTextOfAction() {
		return textOfAction;
	}


	public void setTextOfAction(String textOfAction) {
		this.textOfAction = textOfAction;
	}


	public EditorButton(Composite parent, int style) {
		super(parent, style);
		
	}

	
}

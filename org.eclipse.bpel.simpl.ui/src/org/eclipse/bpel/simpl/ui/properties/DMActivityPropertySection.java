package org.eclipse.bpel.simpl.ui.properties;

import org.eclipse.bpel.simpl.ui.Application;
import org.eclipse.bpel.simpl.ui.StatementHashMap;
import org.eclipse.bpel.ui.commands.SetExtensionCommand;
import org.eclipse.bpel.ui.properties.BPELPropertySection;
import org.eclipse.bpel.ui.util.ModelHelper;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

/**
 * A BPELPropertySection for SIMPL Data-Management-Activities.
 * 
 * @author hahnml
 *
 */
public abstract class DMActivityPropertySection extends BPELPropertySection{


	private StatementHashMap statement = null;
	
	/**
	 * Opens a statement editor shell.
	 */
	public void openStatementEditor(String eClass, String language){
		//TODO Auswahl der Sprache muss noch dynamisch erledigt werden
		System.out.println("StatementEditor: " + eClass + " : " + language);
		new StatementEditor(this, language, eClass);
	}
	
	public void setStatement(StatementHashMap statement){
		this.statement = statement;
	}
	
	public StatementHashMap getStatement(){
		return this.statement;
	}
	
	//TODO: Im Modell können auch HashMaps gespeichert werden, dies würde die
	//ganze sache vereinfachen. Im Moment bleibt es hier aber wegen dem Modell beim String-Type
	public abstract StatementHashMap loadStatementFromModel();
	
	//TODO: Im Modell können auch HashMaps gespeichert werden, dies würde die
	//ganze sache vereinfachen. Im Moment bleibt es hier aber wegen dem Modell beim String-Type
	public abstract void saveStatementToModel();

}

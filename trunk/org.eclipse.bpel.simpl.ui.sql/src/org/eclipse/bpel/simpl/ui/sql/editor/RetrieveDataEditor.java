package org.eclipse.bpel.simpl.ui.sql.editor;

import java.util.ArrayList;

import org.eclipse.bpel.simpl.ui.extensions.AStatementEditor;
import org.eclipse.bpel.simpl.ui.sql.xmlParser.KeyWord;
import org.eclipse.bpel.simpl.ui.sql.xmlParser.QueryKeyWordsXmlParser;
import org.eclipse.bpel.simpl.ui.widgets.DBTable;
import org.eclipse.swt.SWT;
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
import org.eclipse.swt.widgets.Table;


public class RetrieveDataEditor extends SelectAndRetrieveDataEditor {


	
	public RetrieveDataEditor() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void createComposite(Composite composite) {
		
		setXmlFilePath("/keywords/RetrieveDataDMActivityXMLFile.xml");
		setMainStatmentKeyWord("UPDATE");
		super.createComposite(composite);
	}
	
	


}

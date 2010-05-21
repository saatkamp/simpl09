package org.eclipse.bpel.simpl.ui.sql.editor;

import org.eclipse.swt.widgets.Composite;

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

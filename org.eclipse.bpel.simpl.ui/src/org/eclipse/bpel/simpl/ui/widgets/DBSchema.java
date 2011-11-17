package org.eclipse.bpel.simpl.ui.widgets;

import java.util.ArrayList;

public class DBSchema {
	ArrayList<String> listOfTableNames = new ArrayList<String>();
	String schemaName;

	public ArrayList<String> getListOfTableNames() {
		return listOfTableNames;
	}

	public void setListOfTableNames(ArrayList<String> listOfColumnsNames) {
		this.listOfTableNames = listOfColumnsNames;
	}

	public String getSchemaName() {
		return schemaName;
	}

	public void setSchemaName(String schemaName) {
		this.schemaName = schemaName;
	}

	public DBSchema() {

	}

}

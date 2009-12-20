package org.eclipse.bpel.simpl.ui.sql.editor;

import org.eclipse.bpel.simpl.ui.StatementHashMap;
import org.eclipse.bpel.simpl.ui.extensions.AStatementEditor;
import org.eclipse.swt.widgets.Composite;

public class InsertEditor extends AStatementEditor {

	public InsertEditor() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void createComposite(Composite composite) {
		// TODO Auto-generated method stub
		StatementHashMap statem = new StatementHashMap();
		statem.put("INSERT", "(column1, column2, ...)");
		statem.put("INTO", "table");
		statem.put("VALUES", "(value1, value2, ...)");
		setStatement(statem);
	}

}

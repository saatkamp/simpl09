package org.eclipse.bpel.simpl.ui.properties;

import org.eclipse.bpel.simpl.ui.Application;
import org.eclipse.bpel.simpl.ui.extensions.IStatementEditor;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Button;

/**
 * 
 * 
 * @author hahnml
 * 
 */
public class StatementEditor {

	private Shell sShell = null; // @jve:decl-index=0:visual-constraint="10,10"
	private Composite composite = null;
	private Button okButton = null;
	private Button closeButton = null;
	private IStatementEditor compositeClass = null;
	private DMActivityPropertySection parentClass = null;

	public StatementEditor() {
		createSShell();
		sShell.open();
	}

	public StatementEditor(DMActivityPropertySection parent, String language,
			String activity) {
		createSShell();

		if (parent != null && language!=null && activity!=null) {
			this.parentClass = parent;
			compositeClass = Application.getInstance().getEditorClass(language,
					activity);
			if (compositeClass != null) {
				compositeClass.createComposite(composite);
				//TODO Statement in StatementEditor übergeben
				compositeClass.setStatement(parentClass.getStatement());
			}
		}
		sShell.open();
	}

	/**
	 * This method initializes composite
	 * 
	 */
	private void createComposite() {
		GridData gridData = new GridData();
		gridData.verticalAlignment = GridData.FILL;
		gridData.grabExcessHorizontalSpace = true;
		gridData.grabExcessVerticalSpace = true;
		gridData.horizontalSpan = 3;
		gridData.horizontalAlignment = GridData.FILL;
		GridLayout gridLayout = new GridLayout();
		gridLayout.numColumns = 3;
		composite = new Composite(sShell, SWT.BORDER);
		composite.setLayout(gridLayout);
		composite.setLayoutData(gridData);
	}

	/**
	 * This method initializes sShell
	 */
	private void createSShell() {
		GridData gridData2 = new GridData();
		gridData2.horizontalAlignment = GridData.END;
		gridData2.grabExcessHorizontalSpace = true;
		gridData2.widthHint = 60;
		gridData2.verticalAlignment = GridData.CENTER;
		GridData gridData1 = new GridData();
		gridData1.horizontalAlignment = GridData.END;
		gridData1.heightHint = -1;
		gridData1.widthHint = 60;
		gridData1.verticalAlignment = GridData.CENTER;
		GridLayout gridLayout1 = new GridLayout();
		gridLayout1.numColumns = 3;
		sShell = new Shell(Display.getDefault());
		sShell.setText("Statement Editor");
		createComposite();
		sShell.setLayout(gridLayout1);
		sShell.setBounds(new Rectangle(200, 100, 700, 500));
		Label filler1 = new Label(sShell, SWT.NONE);
		okButton = new Button(sShell, SWT.NONE);
		okButton.setText("OK");
		okButton.setLayoutData(gridData2);
		okButton.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				widgetSelected(e);
			}

			@Override
			public void widgetSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				// Hier muss das echte Statement übergeben werden
				if (compositeClass.getStatement() != null) {
					parentClass.setStatement(compositeClass.getStatement());
					parentClass.saveStatementToModel();
				}
				sShell.close();
			}
		});

		closeButton = new Button(sShell, SWT.NONE);
		closeButton.setText("Close");
		closeButton.setLayoutData(gridData1);
		closeButton.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				widgetSelected(e);
			}

			@Override
			public void widgetSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				sShell.close();
			}
		});
	}
}

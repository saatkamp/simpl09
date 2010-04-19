/**
 * <b>Purpose:</b> This class implements the shell of the StatementEditor which is used to encapsulate the different Composites which are attached over the queryLanguage extension point.<br>
 * <b>Description:</b> <br>
 * <b>Copyright:</b>  Licensed under the Apache License, Version 2.0. http://www.apache.org/licenses/LICENSE-2.0<br>
 * <b>Company:</b> SIMPL<br>
 * 
 * @author Michael Hahn <hahnml@studi.informatik.uni-stuttgart.de> <br>
 * @version $Id$ <br>
 * @link http://code.google.com/p/simpl09/
 *
 */
package org.eclipse.bpel.simpl.ui.properties;

import org.eclipse.bpel.simpl.ui.Application;
import org.eclipse.bpel.simpl.ui.extensions.AStatementEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;

import widgets.StatementEditDialog;

public class StatementEditor {

	private Shell sShell = null; // @jve:decl-index=0:visual-constraint="10,10"
	private Composite composite = null;
	private Button okButton = null;
	private Button closeButton = null;
	private AStatementEditor compositeClass = null;
	private DMActivityPropertySection parentClass = null;

	private StatementEditDialog statementEditDialogObjekt=new StatementEditDialog();
	/**
	 * Instantiates a new statement editor.
	 */
	public StatementEditor() {
		createSShell();
		sShell.open();
	}

	/**
	 * Instantiates a new statement editor.
	 * 
	 * @param parent
	 *            the parent property section
	 * @param language
	 *            the language of the statements
	 * @param activity
	 *            the activity, on which the statement editor relies
	 */
	public StatementEditor(DMActivityPropertySection parent, String language,
			String activity) {
		createSShell();

		if (parent != null && language != null && activity != null) {
			this.parentClass = parent;
			compositeClass = Application.getInstance().getEditorClass(language,
					activity);
			if (compositeClass != null) {
				compositeClass.setStatement(parentClass.getStatement());
				System.out.println("DMProperty-Statement: "
						+ parentClass.getStatement());
				System.out.println("DMCompos-Statement: "
						+ compositeClass.getStatement());
				compositeClass.createComposite(composite);
			}
		}
		sShell.open();
	}

	/**
	 * This method initializes the composite
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
	 * This method initializes the shell
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
		sShell.setImage(new Image(Display.getCurrent(), getClass().getResourceAsStream("/icons/database.png")));
		
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
				widgetSelected(e);
			}

			@Override
			public void widgetSelected(SelectionEvent e) {
				if (compositeClass.getStatement() != null) {
					parentClass.setStatement(compositeClass.getStatement());
					parentClass.saveStatementToModel();
					statementEditDialogObjekt.openWindow();
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
				widgetSelected(e);
			}

			@Override
			public void widgetSelected(SelectionEvent e) {
				sShell.close();
			}
		});
	}
}

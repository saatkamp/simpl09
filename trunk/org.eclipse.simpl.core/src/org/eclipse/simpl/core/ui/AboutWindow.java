package org.eclipse.simpl.core.ui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;

public class AboutWindow {

	private Shell sShell = null;  //  @jve:decl-index=0:visual-constraint="10,10"
	private Label simplIconLabel = null;
	private Label simplHeaderlabel = null;
	private Label simplApacheLabel = null;
	private Label simplApacheLabel2 = null;

	public AboutWindow() {
		createSShell();
		sShell.open();
	}

	/**
	 * This method initializes sShell
	 */
	private void createSShell() {
		GridData gridData = new GridData();
		gridData.horizontalAlignment = GridData.FILL;
		gridData.grabExcessHorizontalSpace = false;
		gridData.grabExcessVerticalSpace = false;
		gridData.horizontalSpan = 2;
		gridData.verticalAlignment = GridData.FILL;
		GridLayout gridLayout = new GridLayout();
		gridLayout.numColumns = 3;
		sShell = new Shell();
		sShell.setText("About SIMPL");
		sShell.setLocation(new Point(400, 100));
		sShell.setImage(new Image(Display.getCurrent(), getClass().getResourceAsStream("/icons/logo.png")));
		sShell.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_WHITE));
		sShell.setLayout(gridLayout);
		sShell.setSize(new Point(423, 293));
		simplIconLabel = new Label(sShell, SWT.NONE);
		simplIconLabel.setText("");
		simplIconLabel.setImage(new Image(Display.getCurrent(), getClass().getResourceAsStream("/icons/logo.png")));
		simplHeaderlabel = new Label(sShell, SWT.NONE);
		simplHeaderlabel.setText("SIMPL Framework for Eclipse");
		simplHeaderlabel.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_WHITE));
		Label filler1 = new Label(sShell, SWT.NONE);
		Label filler = new Label(sShell, SWT.NONE);
		simplApacheLabel = new Label(sShell, SWT.NONE);
		simplApacheLabel.setText("This product includes software developed by the");
		simplApacheLabel.setLayoutData(gridData);
		simplApacheLabel.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_WHITE));
		Label filler2 = new Label(sShell, SWT.NONE);
		simplApacheLabel2 = new Label(sShell, SWT.NONE);
		simplApacheLabel2.setText("Apache Software Foundation http://apache.org/");
		simplApacheLabel2.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_WHITE));
	}

}

package org.eclipse.simpl.core.ui;

import java.util.LinkedHashMap;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.simpl.core.Application;
import org.eclipse.simpl.core.Tuple;
import org.eclipse.simpl.core.extensions.AAdminConsoleComposite;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;

import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.graphics.Rectangle;

@SuppressWarnings("unused")
public class AdminConsoleUI {
	private Shell sShell = null;
	private Tree tree = null;
	private Button defaultButton = null;
	private Button saveButton = null;
	private Button resetButton = null;
	private Button closeButton = null;
	private Composite composite = null;
	private AAdminConsoleComposite compositeClass = null;
	private Composite oldComposite = null;
	private TreeItem selectedTreeItem = null;

	public AdminConsoleUI() {
		createSShell();
		sShell.open();
		Application.getInstance().setAdminConsoleVisibility(true);
	}

	/**
	 * This method initializes composite
	 * 
	 */
	private void createComposite() {
		GridData gridData1 = new GridData();
		gridData1.horizontalSpan = 2;
		gridData1.verticalAlignment = GridData.FILL;
		gridData1.horizontalAlignment = GridData.FILL;
		GridLayout gridLayout1 = new GridLayout();
		gridLayout1.numColumns = 1;
		composite = new Composite(sShell, SWT.BORDER);
		composite.setLayout(gridLayout1);
		composite.setLayoutData(gridData1);
	}

	/**
	 * This method initializes a new composite
	 * 
	 */
	private void showComposite(TreeItem treeItem) {

		if (oldComposite != null) {
			oldComposite.dispose();
		}

		if (Application.getInstance().getCompositeClass(treeItem.getText()) != null) {
			compositeClass = Application.getInstance().getCompositeClass(
					treeItem.getText());
			compositeClass.createComposite(composite);
			oldComposite = compositeClass.getComposite();

			if (compositeClass != null && selectedTreeItem != null) {
				// Buffer-Werte des Composites laden
				compositeClass.loadSettingsFromBuffer("buffer");
			}

			composite.layout();
			sShell.layout();
		}
	}

	/**
	 * This method initializes sShell
	 */
	private void createSShell() {
		GridData gridData4 = new GridData();
		gridData4.grabExcessHorizontalSpace = true;
		gridData4.verticalAlignment = GridData.CENTER;
		gridData4.widthHint = 80;
		gridData4.horizontalIndent = 0;
		gridData4.horizontalAlignment = GridData.END;
		GridData gridData3 = new GridData();
		gridData3.grabExcessHorizontalSpace = false;
		gridData3.verticalAlignment = GridData.CENTER;
		gridData3.widthHint = 80;
		gridData3.horizontalAlignment = GridData.END;
		GridData gridData2 = new GridData();
		gridData2.horizontalAlignment = GridData.END;
		gridData2.grabExcessHorizontalSpace = false;
		gridData2.widthHint = 80;
		gridData2.verticalAlignment = GridData.CENTER;
		GridData gridData11 = new GridData();
		gridData11.horizontalIndent = 0;
		gridData11.verticalAlignment = GridData.CENTER;
		gridData11.grabExcessHorizontalSpace = false;
		gridData11.grabExcessVerticalSpace = false;
		gridData11.widthHint = 80;
		gridData11.horizontalAlignment = GridData.END;
		GridLayout gridLayout = new GridLayout();
		gridLayout.numColumns = 3;
		gridLayout.horizontalSpacing = 5;
		gridLayout.marginWidth = 5;
		gridLayout.marginHeight = 5;
		gridLayout.makeColumnsEqualWidth = false;
		GridData gridData = new GridData();
		gridData.horizontalAlignment = GridData.FILL;
		gridData.grabExcessVerticalSpace = true;
		gridData.widthHint = 120;
		gridData.verticalAlignment = GridData.FILL;
		sShell = new Shell(Display.getDefault());
		sShell.setText("SIMPL Admin Console");
		sShell.setImage(new Image(Display.getCurrent(), getClass()
				.getResourceAsStream("/icons/SIMPL-logo-small.png")));
		sShell.setBounds(new Rectangle(200, 100, 900, 500));
		sShell.setLayout(gridLayout);
		tree = new Tree(sShell, SWT.SINGLE | SWT.BORDER);
		tree.setLayoutData(gridData);

		createComposite();

		// Baum mit Werten f�llen
		fillTree();

		Label filler3 = new Label(sShell, SWT.NONE);
		defaultButton = new Button(sShell, SWT.NONE);
		defaultButton.setText("Default");
		defaultButton.setLayoutData(gridData4);
		defaultButton.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				widgetSelected(e);
			}

			@Override
			public void widgetSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				// Alle Werte in allen Composites auf die Defaults setzen
				Application.getInstance().defaultCompositeValues();
			}
		});

		resetButton = new Button(sShell, SWT.NONE);
		resetButton.setText("Reset");
		resetButton.setLayoutData(gridData2);
		resetButton.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				widgetSelected(e);
			}

			@Override
			public void widgetSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				// Alle Werte aller Composites zur�cksetzen
				Application.getInstance().resetCompositeValues();
			}
		});

		Label filler2 = new Label(sShell, SWT.NONE);
		saveButton = new Button(sShell, SWT.NONE);
		saveButton.setText("Save");
		saveButton.setLayoutData(gridData3);
		saveButton.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				widgetSelected(e);
			}

			@Override
			public void widgetSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				// Werte des Composites speichern
				saveAllSettings();
				sShell.close();
				Application.getInstance().setAdminConsoleVisibility(false);
			}
		});

		closeButton = new Button(sShell, SWT.NONE);
		closeButton.setText("Close");
		closeButton.setLayoutData(gridData11);
		closeButton.addListener(SWT.Selection, new Listener() {

			@Override
			public void handleEvent(Event evt) {
				if (Application.getInstance().haveSettingsChanged()) {
					MessageDialog message = new MessageDialog(Display
							.getCurrent().getActiveShell(), "SIMPL", null,
							"Values have been changed. Save changes?",
							MessageDialog.QUESTION, new String[]{"Yes","No","Cancel"}, 0);
					
					int buttonPressed = message.open();
					
					//Hier pr�fen wir auf den Index der im Konstruktor �bergebenen Buttons.
					//Yes = 0, No = 1, Cancel = 2;
					if (buttonPressed == 0) {
						saveAllSettings();
						sShell.close();
						Application.getInstance().setAdminConsoleVisibility(false);
					}
					if (buttonPressed == 1){
						Application.getInstance().resetCompositeValues();
						sShell.close();
						Application.getInstance().setAdminConsoleVisibility(false);
					}
				}else {
					sShell.close();
					Application.getInstance().setAdminConsoleVisibility(false);
				}
			}
		});
	}

	private void fillTree() {
		// Werte und SelectionListener aus Extension-Points auslesen
		for (Tuple tuple : Application.getInstance().getTreeItems()) {
			TreeItem item = null;
			if (tuple.hasValue()) {
				item = new TreeItem(tree, SWT.NONE, tuple.getValue());
			} else {
				item = new TreeItem(tree, SWT.NONE);
			}

			item.setText(tuple.getName());

			if (!Application.getInstance().getTreeSubItems(tuple.getName())
					.isEmpty()) {
				for (String sub : Application.getInstance().getTreeSubItems(
						tuple.getName())) {
					TreeItem subItem = new TreeItem(item, SWT.NONE);
					subItem.setText(sub);
				}
			}
		}

		tree.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				widgetSelected(e);
			}

			@Override
			public void widgetSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				TreeItem sItem = (TreeItem) e.item;
				TreeItem parentItem = sItem.getParentItem();

				if (parentItem != null && sItem != null) {
					System.out.println(parentItem.getText() + " -> "
							+ sItem.getText() + " was selected");

					selectedTreeItem = sItem;
					showComposite(sItem);
				}
			}
		});

	}

	public void saveAllSettings() {
		LinkedHashMap<String, AAdminConsoleComposite> compClasses = Application
				.getInstance().getCompositeClasses();

		for (String key : compClasses.keySet()) {
			AAdminConsoleComposite compClass = compClasses.get(key);
			compClass.saveSettings(compClass.getParentConsoleItem(), compClass
					.getConsoleItem(), "lastSaved");
		}
	}
}
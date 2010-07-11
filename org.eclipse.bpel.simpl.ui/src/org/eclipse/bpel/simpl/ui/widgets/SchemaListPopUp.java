/**
 * <b>Purpose:</b> <br>
 * <b>Description:</b> <br>
 * <b>Copyright:</b>  Licensed under the Apache License, Version 2.0. http://www.apache.org/licenses/LICENSE-2.0<br>
 * <b>Company:</b> SIMPL<br>
 * 
 * @author Firas Zoabi <zoabifs@studi.informatik.uni-stuttgart.de> <br>
 * @version $Id: TablsListPopUp.java 1509 2010-06-10 12:02:07Z ferass_z2000@yahoo.com $ <br>
 * @link http://code.google.com/p/simpl09/
 *
 */
package org.eclipse.bpel.simpl.ui.widgets;

import java.util.ArrayList;

import org.eclipse.simpl.communication.client.DataSource;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

// TODO: Auto-generated Javadoc
/**
 * The Class ElementsListPopUp.
 */
public class SchemaListPopUp {

	ArrayList<DBSchema> listOfSchemas;
	/** The text to search. */
	Text schemaSearch;

	/** The list to search. */
	List listSchemas, listTables;

	/** The array of elements. */
	ArrayList<String> arrayOfSchemaElements = new ArrayList<String>();
	ArrayList<String> arrayOfTableElements = new ArrayList<String>();

	ArrayList<DBSchema> listOfSchemaObjects;
	/** The window is open. */
	boolean windowIsOpen = false;
	
	Label faultLabel;

	/**
	 * Checks if is window open.
	 * 
	 * @return true, if is window open
	 */
	public boolean isWindowOpen() {
		return windowIsOpen;
	}

	/**
	 * Sets the window is open.
	 * 
	 * @param windowIsOpen
	 *            the new window is open
	 */
	public void setWindowIsOpen(boolean windowIsOpen) {
		this.windowIsOpen = windowIsOpen;
	}

	private Shell theShell;// =new Shell();
	private Text tableSearch;

	/**
	 * Instantiates a new elements list pop up.
	 * 
	 * @param statementText
	 *            the statement text
	 */
	public SchemaListPopUp(LiveEditStyleText statementText) {
		createSShell(statementText);
	}

	private void createSShell(final LiveEditStyleText statementText) {
		GridLayout gridLayout = new GridLayout();
		gridLayout.numColumns = 4;
		theShell = new Shell(Display.getDefault());
		theShell.setText("Data-Management-Activity properties");
		// theShell.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_WHITE));
		theShell.setLayout(gridLayout);
		theShell.setSize(new Point(500, 350));

		GridData gridData4 = new GridData();
		gridData4.grabExcessHorizontalSpace = true;
		gridData4.horizontalAlignment = GridData.FILL;
		gridData4.verticalAlignment = GridData.FILL;
		gridData4.grabExcessVerticalSpace = true;
		gridData4.horizontalSpan = 2;

		GridData searchFields = new GridData();
		searchFields.grabExcessHorizontalSpace = true;
		searchFields.horizontalAlignment = GridData.FILL;
		searchFields.verticalAlignment = GridData.CENTER;

		Label schemaFilterLabel = new Label(theShell, SWT.NONE);
		schemaFilterLabel.setText("Search schema:");

		schemaSearch = new Text(theShell, SWT.BORDER);
		schemaSearch.setLayoutData(searchFields);
		schemaSearch.addModifyListener(new ModifyListener() {

			@Override
			public void modifyText(ModifyEvent e) {
				searchSchemaListForResults(schemaSearch.getText());
			}

		});

		Label tableFilterLabel = new Label(theShell, SWT.NONE);
		tableFilterLabel.setText("Search table:");

		tableSearch = new Text(theShell, SWT.BORDER);
		tableSearch.setLayoutData(searchFields);
		tableSearch.addModifyListener(new ModifyListener() {

			@Override
			public void modifyText(ModifyEvent e) {
				searchTableListForResults(tableSearch.getText());
			}

		});

		Label listToSearchLabel = new Label(theShell, SWT.NONE);
		listToSearchLabel.setText("Select a Schema:");

		Label filler = new Label(theShell, SWT.NONE);

		Label listTablesLabel = new Label(theShell, SWT.NONE);
		listTablesLabel.setText("Select a Table:");

		Label filler1 = new Label(theShell, SWT.NONE);

		listSchemas = new List(theShell, SWT.BORDER | SWT.SINGLE | SWT.V_SCROLL
				| SWT.H_SCROLL);

		listSchemas.setLayoutData(gridData4);

		listSchemas.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				loadTablesOfSchema(listSchemas.getItems()[listSchemas
						.getSelectionIndex()]);
				faultLabel.setVisible(false);
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});

		listTables = new List(theShell, SWT.BORDER | SWT.SINGLE | SWT.V_SCROLL
				| SWT.H_SCROLL);
		listTables.setLayoutData(gridData4);
		
		listTables.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				faultLabel.setVisible(false);
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});

		GridData gridData6 = new GridData();
		gridData6.horizontalAlignment = GridData.BEGINNING;
		gridData6.verticalAlignment = GridData.CENTER;
		gridData6.horizontalSpan = 4;
		
		faultLabel = new Label(theShell, SWT.NONE);
		faultLabel.setLayoutData(gridData6);
		Font font = new Font(theShell.getDisplay(), "Arial", 10, SWT.BOLD | SWT.ITALIC);
		faultLabel.setFont(font);
		faultLabel.setForeground(theShell.getDisplay().getSystemColor(SWT.COLOR_RED));
		faultLabel.setVisible(false);
		faultLabel.setText("Please select a schema and a table to insert in the statement.");

		GridData gridData5 = new GridData();
		gridData5.horizontalAlignment = GridData.END;
		gridData5.verticalAlignment = GridData.FILL;
		gridData5.horizontalSpan = 4;
		
		Button addToStatement = new Button(theShell, SWT.NONE);
		addToStatement.setText("Insert to statement");
		addToStatement.setLayoutData(gridData5);
		addToStatement.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {

				if (listSchemas.getSelectionIndex() != -1
						&& listTables.getSelectionIndex() != -1) {
					statementText.append(" " + listSchemas.getSelection()[0]);
					statementText.append("." + listTables.getSelection()[0]);

					closeWindow();
					setWindowIsOpen(false);
				}else {
					faultLabel.setVisible(true);
				}
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});

	}

	/**
	 * add the colums of the spezified table in the colums-liste
	 */
	private void loadTablesOfSchema(String searchedString) {
		DBSchema tmpSchemaObject = null;
		listTables.removeAll();
		arrayOfTableElements.clear();
		for (int i = 0; i < listOfSchemaObjects.size(); i++) {

			tmpSchemaObject = listOfSchemaObjects.get(i);
			if (tmpSchemaObject.getSchemaName().equals(searchedString)) {
				for (int j = 0; j < tmpSchemaObject.getListOfTableNames()
						.size(); j++) {
					listTables
							.add(tmpSchemaObject.getListOfTableNames().get(j));
					arrayOfTableElements.add(tmpSchemaObject
							.getListOfTableNames().get(j));
				}
			}
		}

		// if there is already set a search string, we have to filter the list
		// directly
		if (!tableSearch.getText().isEmpty()) {
			searchTableListForResults(tableSearch.getText());
		}
	}

	/**
	 * search live for results in the list
	 * 
	 * @param text
	 */
	private void searchSchemaListForResults(String text) {
		// arrayOfElements= listToSearch.getItems();
		listSchemas.removeAll();
		for (int i = 0; i < arrayOfSchemaElements.size(); i++) {
			if (arrayOfSchemaElements.get(i).toUpperCase().contains(
					text.toUpperCase())) {
				listSchemas.add(arrayOfSchemaElements.get(i));
			}
		}
	}

	private void searchTableListForResults(String text) {
		// arrayOfElements= listToSearch.getItems();
		listTables.removeAll();
		for (int i = 0; i < arrayOfTableElements.size(); i++) {
			if (arrayOfTableElements.get(i).toUpperCase().contains(
					text.toUpperCase())) {
				listTables.add(arrayOfTableElements.get(i));
			}
		}
	}

	/**
	 * Open window.
	 */
	public void openWindow() {
		theShell.open();
	}

	/**
	 * Close window.
	 */
	public void closeWindow() {
		theShell.close();
	}

	/**
	 * Sets the text.
	 * 
	 * @param string
	 *            the new text
	 */
	public void setText(String string) {
		theShell.setText(string);
	}

	/**
	 * for adding the tables names from the DB.
	 */
	public void loadSchemasFromDB(DataSource dataSource) {

		MetaDataXMLParser metaDataXMLParser_Objekt = new MetaDataXMLParser();
		ArrayList<DBSchema> listOfSchemas = metaDataXMLParser_Objekt
				.loadSchemasFromDB(dataSource);
		listOfSchemaObjects = listOfSchemas;

		for (int i = 0; i < listOfSchemas.size(); i++) {
			arrayOfSchemaElements.add(listOfSchemaObjects.get(i)
					.getSchemaName());
			listSchemas.add(listOfSchemaObjects.get(i).getSchemaName());
		}
	}
}

package org.eclipse.bpel.simpl.ui.sql.widgets;
import java.io.*;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.*;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.*;
import org.eclipse.swt.widgets.*;

/**
 * Creates a tabbed display with four tabs, and a few controls on each page
 */
public class TabPanel {
  private static final String IMAGE_PATH = "images"
      + System.getProperty("file.separator");

  private Image circle;
  private Image square;
  private Image triangle;
  private Image star;
  
  Label tableName;
  Text textTableName;
  Text statementText;
  List tablsList;
  List columnList;
  Composite valuesCompo;
  Text valuesText;
  List valuesList;
  Composite actionsCompo;
  String[] arrayOfParsedColumns;
	String[] arrayOfParsedValues;
	
  /**
   * Gets the control for tab one
   * 
   * @param tabFolder the parent tab folder
   * @return Control
   */
  private Control getTabOneControl(TabFolder tabFolder) {
    // Create a composite and add four buttons to it
    Composite composite = new Composite(tabFolder, SWT.NONE);
    composite.setLayout(new FillLayout(SWT.VERTICAL));
    
    Button insertButton;
    
    GridLayout gridLayoutx = new GridLayout();
	gridLayoutx.numColumns = 3;
	Composite tableNameComposite = new Composite(composite, SWT.PUSH);
	//tableNameComposite.setEnabled(false);
	tableNameComposite.setLayout(gridLayoutx);
//	if(statementText.getText().length()<8){
//		tableNameComposite.setEnabled(false);
//	}
	
	tableName =new Label(tableNameComposite, SWT.NONE);
	tableName.setText("Type the name of the Table: ");
	textTableName = new Text(tableNameComposite, SWT.BORDER);
	final Button addTable =new Button(tableNameComposite, SWT.NONE);
	addTable.setText("Add to Statement");
	addTable.addSelectionListener(new SelectionListener() {
		
		@Override
		public void widgetSelected(SelectionEvent e) {
			// TODO Auto-generated method stub
			
			try {
				if(textTableName.getText().length()>0){
					statementText.setText(statementText.getText()+"	"+textTableName.getText()+"\r	VALUES ");
					//columnCompo.setEnabled(true);
					//buttonsCompo.setEnabled(true);
					//columsListCompo.setEnabled(true);
				}
			} catch (Exception e1) {
				System.out.print("ERROR: "+e1.getMessage());
			}
		}
		
		@Override
		public void widgetDefaultSelected(SelectionEvent e) {
			// TODO Auto-generated method stub
			
			if(textTableName.getText().length()>0){
				statementText.setText(statementText.getText()+"	"+textTableName.getText()+"\r	VALUES ");
				//columnCompo.setEnabled(true);
				//buttonsCompo.setEnabled(true);
				//columsListCompo.setEnabled(true);
			}
			
		}
	});
	//************************************
	//*************************************************
	GridData gridData1 = new GridData();
	//gridData1.horizontalAlignment = GridData.FILL;
	gridData1.grabExcessHorizontalSpace = true;
	gridData1.grabExcessVerticalSpace = true;
	gridData1.verticalAlignment = GridData.FILL;
	
	tablsList = new List(tableNameComposite, SWT.BORDER | SWT.MULTI | SWT.V_SCROLL);
	tablsList.setBounds(40, 20, 320, 100);
	tablsList.setLayoutData(gridData1);
	tablsList.setEnabled(false);
	try {
		loadTheTablesIntoList();
	} catch (Exception e2) {
		System.out.print("ERROR: "+e2.getMessage());
	}
	
	try {
		tablsList.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent e) {
				
				//statementText.setText(statementText.getText()+" "+tablsList.getItem(tablsList.getSelectionIndex()));
				try {
					loadColumnsOfTable();
				} catch (Exception e1) {
					System.out.print("ERROR: "+e1.getMessage());
				}
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				
				//statementText.setText(statementText.getText()+" "+tablsList.getItem(tablsList.getSelectionIndex()));
				try {
					loadColumnsOfTable();
				} catch (Exception e1) {
					System.out.print("ERROR: "+e1.getMessage());
				}
			}

			
		});
	} catch (Exception e1) {
		System.out.print("ERROR: "+e1.getMessage());
	}
	//**************************************************************
	
	//************************************************************
	columnList=new List(tableNameComposite, SWT.BORDER | SWT.MULTI | SWT.V_SCROLL| SWT.H_SCROLL);
	columnList.setBounds(40, 20, 420, 200);
	GridData gridDatax = new GridData();
	gridDatax.horizontalAlignment = GridData.FILL;
	gridDatax.grabExcessHorizontalSpace = true;
	gridDatax.grabExcessVerticalSpace = true;
	gridDatax.verticalAlignment = GridData.FILL;
	columnList.setLayoutData(gridDatax);
	//columnList.setSize(230, 150);
	
	
	
	
	
//		columnList.addSelectionListener(new SelectionListener() {
//			
//			@Override
//			public void widgetSelected(SelectionEvent e) {
//				if(columnList.getItems().length>0) 	tmpString2="\r			,";
//				else tmpString2="";
//				//statementText.setText(statementText.getText()+tmpString2+columnList.getItem(columnList.getSelectionIndex()));
//				
//			}
//			
//			@Override
//			public void widgetDefaultSelected(SelectionEvent e) {
//				if(columnList.getItems().length>0) 	tmpString2="\r			,";
//				else tmpString2="";
//				//statementText.setText(statementText.getText()+tmpString2+columnList.getItem(columnList.getSelectionIndex()));
//
//			}
//		});
	//************************************************************
	
	//************************************************************
	
	
	
	//************************************************************
	
	//***********************************************************
	valuesCompo=new Composite(tableNameComposite, SWT.BORDER);
	valuesCompo.setEnabled(false);
	GridLayout gridLayoutY=new GridLayout();
	gridLayoutY.numColumns=1;
	valuesCompo.setLayout(gridLayoutY);
	Label valuesLabel=new Label(valuesCompo, SWT.NONE);
	valuesLabel.setText("The Values for Inserting: ");
	valuesText=new Text(valuesCompo, SWT.BORDER);
	valuesText.setSize(250, 50);
	Button addValuesIntoList=new Button(valuesCompo, SWT.NONE);
	addValuesIntoList.setText("Add Values to Statement");
	//valuesText.setText(getParsedVlauesFromStatement()); 
	addValuesIntoList.addSelectionListener(new SelectionListener() {
		
		@Override
		public void widgetSelected(SelectionEvent e) {
			valuesList.add(valuesText.getText());
			valuesText.setText("");
		}
		
		@Override
		public void widgetDefaultSelected(SelectionEvent e) {
			valuesList.add(valuesText.getText());
			valuesText.setText("");
		}
	});
	valuesList=new List(valuesCompo, SWT.BORDER | SWT.MULTI | SWT.V_SCROLL);
	valuesList.setBounds(40, 20, 420, 200);
	GridData gridDatax2 = new GridData();
	gridDatax2.horizontalAlignment = GridData.FILL;
	gridDatax2.grabExcessHorizontalSpace = true;
	gridDatax2.grabExcessVerticalSpace = true;
	gridDatax2.verticalAlignment = GridData.FILL;
	valuesList.setLayoutData(gridDatax2);
	valuesList.setSize(170, 150);
	//***********************************************************
	
	
	//***********************************************************
	
	//***********************************************************
	
	//********************************
	actionsCompo=new Composite(tableNameComposite, SWT.NONE);
	GridLayout gridLayoutZ = new GridLayout();
	gridLayoutZ.numColumns = 2;
	
	actionsCompo.setLayout(gridLayoutZ);
//		if(statementText.getText().length()<8){
//			actionsCompo.setEnabled(false);
//		}
	
	
	Button insertColumsIntoStatment=new Button(actionsCompo, SWT.NONE);
	insertColumsIntoStatment.setToolTipText("Add Columns and the values into the Statment");
	insertColumsIntoStatment.setText("Insert to Statement");
	insertColumsIntoStatment.addSelectionListener(new SelectionListener() {
		
		String tmpString="";
		@Override
		public void widgetSelected(SelectionEvent e) {
			
			if(columnList.getItems().length>0) 	tmpString=" ,";
			else tmpString=" ";
			
			statementText.setText(statementText.getText()+ tablsList.getItem(tablsList.getSelectionIndex())+"(	");
			statementText.setText(statementText.getText()+" "+columnList.getItems()[columnList.getSelectionIndices()[0]]);
			for(int i=1;i<columnList.getSelectionIndices().length;i++){
				//if(columnList.isSelected(i)){
					statementText.setText(statementText.getText()+tmpString+columnList.getItems()[columnList.getSelectionIndices()[i]]);
				//}
			}
			statementText.setText(statementText.getText()+")\r");

			
			if(valuesList.getItems().length>0) 	tmpString=" ,";
			else tmpString=" ";
			statementText.setText(statementText.getText()+"	VALUES (");
			statementText.setText(statementText.getText()+" "+valuesList.getItems()[0]);
			for(int i=1;i<valuesList.getItems().length;i++){
				statementText.setText(statementText.getText()+tmpString+valuesList.getItems()[i]);
		
			}
			statementText.setText(statementText.getText()+")\r");

		}
		
		@Override
		public void widgetDefaultSelected(SelectionEvent e) {
			
			if(columnList.getItems().length>0) 	tmpString=" ,";
			else tmpString=" ";
			statementText.setText(statementText.getText()+ tablsList.getItem(tablsList.getSelectionIndex())+"(	");
			statementText.setText(statementText.getText()+" "+columnList.getItems()[columnList.getSelectionIndices()[0]]);
			for(int i=1;i<columnList.getSelectionIndices().length;i++){
				//if(columnList.isSelected(i)){
					statementText.setText(statementText.getText()+tmpString+columnList.getItems()[columnList.getSelectionIndices()[i]]);
				//}
			}
			statementText.setText(statementText.getText()+")\r");

			if(valuesList.getItems().length>0) 	tmpString=" ,";
			else tmpString=" ";
			statementText.setText(statementText.getText()+"	VALUES (");
			statementText.setText(statementText.getText()+" "+valuesList.getItems()[0]);
			for(int i=1;i<valuesList.getItems().length;i++){
				try {
					statementText.setText(statementText.getText()+tmpString+valuesList.getItems()[i]);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					System.out.print("ERROR:"+e1.getMessage());
				}
		
			}
			statementText.setText(statementText.getText()+")\r");

		}
	});
	
	Button deleteFromColumnList =new Button(actionsCompo, SWT.NONE);
	deleteFromColumnList.setText("Remove from List");
	deleteFromColumnList.addSelectionListener(new SelectionListener() {
		@Override
		public void widgetSelected(SelectionEvent e) {
			// TODO Auto-generated method stub
			columnList.remove(columnList.getSelectionIndex());
		}
		
		@Override
		public void widgetDefaultSelected(SelectionEvent e) {
			// TODO Auto-generated method stub
			columnList.remove(columnList.getSelectionIndex());
		}
	});
	
	//**************************************************************
	//parsing the colums and values from the statement into the lists.
	try {
		parseColumnsFromStatment();
	} catch (Exception e1) {
		System.out.print("ERROR:"+e1.getMessage());
	}
	if(arrayOfParsedColumns!=null){
		try {
			loadTheColumnsIntoList(arrayOfParsedColumns);
		} catch (Exception e1) {
			System.out.print("ERROR:"+e1.getMessage());
		}
	}else{columnList.removeAll();}
	
	if(arrayOfParsedValues!=null){
		try {
			loadTheColumnsIntoList(arrayOfParsedValues);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			System.out.print("ERROR:"+e1.getMessage());
		}
	}else{valuesList.removeAll();}
	
	tableNameComposite.setEnabled(true);
	tablsList.setEnabled(true);
	valuesCompo.setEnabled(true);
	
	
	
    return composite;
  }
  
 //################################
  
  
  
  private void loadTheColumnsIntoList(String[] arrayOfParsedColumns2) {
	// TODO Auto-generated method stub
	
}

private void parseColumnsFromStatment() {
	// TODO Auto-generated method stub
	
}

private void loadColumnsOfTable() {
		// TODO Auto-generated method stub
		
	}
  
  private void loadTheTablesIntoList() {
	// TODO Auto-generated method stub
	
}





/**
   * Runs the application
   */
  public void run() {
    Display display = new Display();
    Shell shell = new Shell(display);
    shell.setLayout(new FillLayout());
    shell.setText("Complex Tabs");
    createImages(shell);
    createContents(shell);
    shell.open();
    while (!shell.isDisposed()) {
      if (!display.readAndDispatch()) {
        display.sleep();
      }
    }
    display.dispose();
  }

  /**
   * Creates the contents
   * 
   * @param shell the parent shell
   */
  private void createContents(Shell shell) {
    // Create the containing tab folder
    final TabFolder tabFolder = new TabFolder(shell, SWT.NONE);

    // Create each tab and set its text, tool tip text,
    // image, and control
    TabItem one = new TabItem(tabFolder, SWT.NONE);
    one.setText("one");
    one.setToolTipText("This is tab one");
    one.setImage(circle);
    one.setControl(getTabOneControl(tabFolder));

    TabItem two = new TabItem(tabFolder, SWT.NONE);
    two.setText("two");
    two.setToolTipText("This is tab two");
    two.setImage(square);
    two.setControl(getTabTwoControl(tabFolder));

    TabItem three = new TabItem(tabFolder, SWT.NONE);
    three.setText("three");
    three.setToolTipText("This is tab three");
    three.setImage(triangle);
    three.setControl(getTabThreeControl(tabFolder));

    TabItem four = new TabItem(tabFolder, SWT.NONE);
    four.setText("four");
    four.setToolTipText("This is tab four");
    four.setImage(star);

    // Select the third tab (index is zero-based)
    tabFolder.setSelection(2);

    // Add an event listener to write the selected tab to stdout
    tabFolder.addSelectionListener(new SelectionAdapter() {
      public void widgetSelected(org.eclipse.swt.events.SelectionEvent event) {
        System.out.println(tabFolder.getSelection()[0].getText() + " selected");
      }
    });
  }

  /**
   * Creates the images
   * 
   * @param shell the parent shell
   */
  private void createImages(Shell shell) {
    try {
      circle = new Image(shell.getDisplay(), new FileInputStream(IMAGE_PATH
          + "circle.gif"));
      square = new Image(shell.getDisplay(), new FileInputStream(IMAGE_PATH
          + "square.gif"));
      star = new Image(shell.getDisplay(), new FileInputStream(IMAGE_PATH
          + "star.gif"));
      triangle = new Image(shell.getDisplay(), new FileInputStream(IMAGE_PATH
          + "triangle.gif"));
    } catch (IOException e) {
      // Images not found; handle gracefully
    }
  }

  /**
   * Disposes the images
   */
  private void disposeImages() {
    if (circle != null)
      circle.dispose();
    if (square != null)
      square.dispose();
    if (star != null)
      star.dispose();
    if (triangle != null)
      triangle.dispose();
  }

  

  /**
   * Gets the control for tab two
   * 
   * @param tabFolder the parent tab folder
   * @return Control
   */
  private Control getTabTwoControl(TabFolder tabFolder) {
    // Create a multi-line text field
    return new Text(tabFolder, SWT.BORDER | SWT.MULTI | SWT.WRAP);
  }

  /**
   * Gets the control for tab three
   * 
   * @param tabFolder the parent tab folder
   * @return Control
   */
  private Control getTabThreeControl(TabFolder tabFolder) {
    // Create some labels and text fields
    Composite composite = new Composite(tabFolder, SWT.NONE);
    composite.setLayout(new RowLayout());
    new Label(composite, SWT.LEFT).setText("Label One:");
    new Text(composite, SWT.BORDER);
    new Label(composite, SWT.RIGHT).setText("Label Two:");
    new Text(composite, SWT.BORDER);
    return composite;
  }
  
  /**
   * The entry point for the application
   * 
   * @param args the command line arguments
   */
  public static void main(String[] args) {
    new TabPanel().run();
  }
}

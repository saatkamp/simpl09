package org.eclipse.simpl.statementtest.ui.wizards.pages;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.simpl.statementtest.model.StatementTest;
import org.eclipse.simpl.statementtest.ui.wizards.StatementTestWizard;
import org.eclipse.simpl.statementtest.utils.DataSourceUtils;
import org.eclipse.simpl.statementtest.utils.IssueRecognition;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
import org.simpl.resource.management.data.DataSource;

/**
 * <b>Purpose:</b>Wizard page for the data source selection.<br>
 * <b>Description:</b> <br>
 * <b>Copyright:</b>Licensed under the Apache License, Version 2.0.
 * http://www.apache.org/licenses/LICENSE-2.0<br>
 * <b>Company:</b><br>
 * 
 * @author Michael Schneidt <michael.schneidt@arcor.de><br>
 * @version $Id: DataSourceSelectionPage.java 89 2010-08-18 16:27:23Z hiwi $<br>
 * @link http://code.google.com/p/simpl09/
 */
public class DataSourceSelectionPage extends StatementTestWizardPage {
  static final String PAGE_TITLE = "Select a data source";
  static final String PAGE_MESSAGE = "The statement test is performed on the selected data source.";
  static final String REFRESH_ICON = "icons/action_refresh.gif";
  static final String REFRESH_ITEM_TOOLTIP = "Refresh Data Source List";

  StatementTestWizard statementTestWizard = null;
  StatementTest statementTest = null;
  Label label = null;
  Combo combo = null;
  Table table = null;
  ToolBar toolBar = null;
  ToolItem refreshItem = null;
  Image refreshImage = null;
  TableItem tableItem = null;
  TableColumn propertyColumn = null;
  TableColumn valueColumn = null;
  DataSource selectedDataSource = null;
  String[] dataSources = null;

  /**
   * @param pageName
   * @param title
   * @param titleImage
   */
  public DataSourceSelectionPage(String pageName, ImageDescriptor titleImage) {
    super(pageName, PAGE_TITLE, titleImage);

    titleImage = ImageDescriptor.createFromImageData(titleImage.getImageData().scaledTo(
        56, 56));

    this.setImageDescriptor(titleImage);
    this.setMessage(DataSourceSelectionPage.PAGE_MESSAGE);
  }

  @Override
  public void createControl(Composite parent) {
    super.createControl(parent);
    statementTestWizard = (StatementTestWizard) this.getWizard();
    statementTest = statementTestWizard.getStatementTest();

    // next page is not available until data source selection
    this.setPageComplete(false);

    // data source retrieval
    dataSources = DataSourceUtils.getAllDataSourceNames(statementTest.getProcess());
    dataSources = DataSourceUtils.filterDataSourceNamesByType(statementTest.getProcess(), dataSources, statementTest.getDataSource().getType());

    // layout
    this.pageComposite.setLayout(new GridLayout(3, false));

    // label
    label = new Label(this.pageComposite, SWT.NULL);
    label.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false));
    label.setText("Select data source: ");

    // combo
    combo = new Combo(this.pageComposite, SWT.READ_ONLY);
    combo.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));
    combo.setItems(dataSources);
    combo.select(-1);
    combo.addSelectionListener(new SelectionListener() {
      @Override
      public void widgetDefaultSelected(SelectionEvent e) {
        widgetSelected(e);
      }

      @Override
      public void widgetSelected(SelectionEvent e) {
        String issue;
        String selectedValue = ((Combo) e.widget).getItem(((Combo) e.widget)
            .getSelectionIndex());

        // enable next page
        setPageComplete(true);

        // set selected data source object
        selectedDataSource = DataSourceUtils.findDataSourceByName(statementTest.getProcess(), selectedValue);

        // recognize the issue of the statement
        issue = IssueRecognition.getInstance().recognizeIssue(
            selectedDataSource.getLanguage(), statementTest.getStatement());

        if (issue != null) {
          statementTest.setIssue(issue);
        }

        // save selected data source
        statementTest.setDataSource(selectedDataSource);

        // update table items
        table.removeAll();

        tableItem = new TableItem(table, SWT.NONE);
        tableItem.setText(0, "Name");
        tableItem.setText(1, selectedDataSource.getName());

        tableItem = new TableItem(table, SWT.NONE);
        tableItem.setText(0, "Type");
        tableItem.setText(1, selectedDataSource.getType());

        tableItem = new TableItem(table, SWT.NONE);
        tableItem.setText(0, "Subtype");
        tableItem.setText(1, selectedDataSource.getSubType());

        tableItem = new TableItem(table, SWT.NONE);
        tableItem.setText(0, "Language");
        tableItem.setText(1, selectedDataSource.getLanguage());

        tableItem = new TableItem(table, SWT.NONE);
        tableItem.setText(0, "Address");
        tableItem.setText(1, selectedDataSource.getAddress());

        tableItem = new TableItem(table, SWT.NONE);
        tableItem.setText(0, "Workflow Data Format");
        tableItem.setText(1, (selectedDataSource.getConnector().getDataConverter()
            .getWorkflowDataFormat() != null) ? selectedDataSource.getConnector()
            .getDataConverter().getWorkflowDataFormat() : "");
      }
    });

    toolBar = new ToolBar(this.pageComposite, SWT.FLAT);
    toolBar.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false));

//    refreshImage = new Image(Display.getDefault(), StatementTestPlugin
//        .getImageDescriptor(REFRESH_ICON).getImageData());
//    refreshItem = new ToolItem(toolBar, SWT.NONE);
//    refreshItem.setImage(refreshImage);
//    refreshItem.setToolTipText(DataSourceSelectionPage.REFRESH_ITEM_TOOLTIP);
//    refreshItem.addSelectionListener(new SelectionAdapter() {
//      @Override
//      public void widgetSelected(SelectionEvent e) {
//        DataSourceUtils.refresh();
//        dataSources = DataSourceUtils.getAllDataSourceNames(statementTest.getProcess());
//        dataSources = DataSourceUtils.filterDataSourceNamesByType(statementTest.getProcess(), dataSources, statementTest.getDataSource().getType());
//        combo.setItems(dataSources);
//      }
//    });
//
//    refreshItem.setEnabled(false);

    // table
    table = new Table(this.pageComposite, SWT.BORDER | SWT.HIDE_SELECTION);
    table.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 3, 0));
    table.setLinesVisible(true);
    table.setHeaderVisible(true);

    // table header
    propertyColumn = new TableColumn(table, SWT.NONE);
    valueColumn = new TableColumn(table, SWT.NONE);
    propertyColumn.setText("Property");
    valueColumn.setText("Value");
    propertyColumn.setWidth(100);
    valueColumn.setWidth(300);

    // select the data source of the activity
    for (int i = 0; i < dataSources.length; i++) {
      if (dataSources[i].equals(this.statementTest.getActivity().getDsIdentifier())) {
        combo.select(i);

        // fire combo selection event to initialize the table
        combo.notifyListeners(SWT.Selection, new Event());
      }
    }

    this.setControl(this.parentComposite);
  }
}
package org.eclipse.simpl.statementtest.ui.views;

import java.util.LinkedHashMap;

import org.eclipse.bpel.model.Variable;
import org.eclipse.simpl.statementtest.StatementTestPlugin;
import org.eclipse.simpl.statementtest.model.StatementTest;
import org.eclipse.simpl.statementtest.model.results.CreateTableResult;
import org.eclipse.simpl.statementtest.model.results.RDBResult;
import org.eclipse.simpl.statementtest.model.variables.ContainerVariable;
import org.eclipse.simpl.statementtest.model.variables.ParameterVariable;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.part.ViewPart;

/**
 * <b>Purpose:</b>View for the executed tests.<br>
 * <b>Description:</b>Shows the executed tests and their results.<br>
 * <b>Copyright:</b>Licensed under the Apache License, Version 2.0.
 * http://www.apache.org/licenses/LICENSE-2.0<br>
 * <b>Company:</b><br>
 * 
 * TODO: Scrollrad für Ergebnistabelle geht nicht
 * 
 * @author Michael Schneidt <michael.schneidt@arcor.de><br>
 * @version $Id: StatementTestView.java 82 2010-08-04 12:42:11Z hiwi $<br>
 * @link http://code.google.com/p/simpl09/
 */
public class StatementTestView extends ViewPart {
  static final String STATUS_SUCCESSFUL_ICON = "icons/tick-circle.png";
  static final String STATUS_FAILED_ICON = "icons/cross-circle.png";
  static final String STATUS_SUCCESSFUL = "Successful";
  static final String STATUS_FAILED = "Failed";
  static final String STATISTIC_GAP = "    ";

  // composite for all results
  Composite resultComposite = null;

  // composite for the statistics
  Composite statisticComposite = null;

  // composite for a result table (label+table)
  Composite tableComposite = null;

  // statistic label
  Label whiteValueLabel = null;
  Label redValueLabel = null;
  Label greenValueLabel = null;
  Label blueValueLabel = null;

  Table statementTestTable = null;
  TableColumn activityColumn = null;
  TableColumn typeColumn = null;
  TableColumn processColumn = null;
  TableColumn statementColumn = null;
  TableColumn statusColumn = null;
  TableItem tableItem = null;

  TabFolder tabFolder = null;
  TabItem resultTabItem = null;
  TabItem parameterTabItem = null;
  TabItem logTabItem = null;

  Table resultTable = null;
  Table originalTable = null;

  Table parameterTable = null;
  TableColumn parameterTableColumn = null;
  Text parameterTextField = null;
  Text logTextField = null;

  GridData gridData = null;

  StatementTest selectedStatementTest = null;

  /**
   * Maps the table items to the according statement tests.
   */
  LinkedHashMap<TableItem, StatementTest> statementTests = new LinkedHashMap<TableItem, StatementTest>();

  /**
   * Maps the statement tests to their result composite.
   */
  LinkedHashMap<StatementTest, Composite> statementTestResultComposites = new LinkedHashMap<StatementTest, Composite>();

  Label resultTableLabel = null;
  Label originalTableLabel = null;

  /*
   * (non-Javadoc)
   * @see
   * org.eclipse.ui.part.WorkbenchPart#createPartControl(org.eclipse.swt.widgets.Composite
   * )
   */
  @Override
  public void createPartControl(Composite parent) {
    parent.setLayout(new GridLayout(2, true));
    statementTestTable = new Table(parent, SWT.BORDER | SWT.FULL_SELECTION);
    this.buildStatementTestsTable();

    tabFolder = new TabFolder(parent, SWT.BORDER);
    tabFolder.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

    // result tab item
    this.buildResultComposite(null);
    resultTabItem = new TabItem(tabFolder, SWT.NONE);
    resultTabItem.setText("Result");
    resultTabItem.setControl(resultComposite);

    // parameter tab item
    parameterTable = new Table(tabFolder, SWT.BORDER);
    parameterTable.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
    parameterTable.setEnabled(false);
    parameterTable.setLinesVisible(true);
    parameterTable.setHeaderVisible(true);
    parameterTableColumn = new TableColumn(parameterTable, SWT.LEFT);
    parameterTableColumn.setText("Variable");
    parameterTableColumn.setWidth(150);
    parameterTableColumn = new TableColumn(parameterTable, SWT.LEFT);
    parameterTableColumn.setText("Value");
    parameterTableColumn.setWidth(300);
    parameterTabItem = new TabItem(tabFolder, SWT.NONE);
    parameterTabItem.setText("Parameters");
    parameterTabItem.setControl(parameterTable);

    // log tab item
    logTextField = new Text(tabFolder, SWT.WRAP | SWT.BORDER | SWT.V_SCROLL);
    logTextField.setEditable(false);
    logTabItem = new TabItem(tabFolder, SWT.NONE);
    logTabItem.setText("Log");
    logTabItem.setControl(logTextField);
  }

  /*
   * (non-Javadoc)
   * @see org.eclipse.ui.part.WorkbenchPart#setFocus()
   */
  @Override
  public void setFocus() {
    statementTestTable.setFocus();
  }

  /**
   * Adds a statement test to the view.
   * 
   * @param statementTest
   */
  public void addStatementTest(StatementTest statementTest) {
    tableItem = new TableItem(statementTestTable, SWT.NONE);
    tableItem.setText(1, statementTest.getActivity().getName());
    tableItem.setText(2, statementTest.getActivity().eClass().getName());
    tableItem.setText(3, statementTest.getProcess().getName());
    tableItem.setText(4, statementTest.getStatement());

    if (statementTest.isExecuted()) {
      tableItem.setText(0, STATUS_SUCCESSFUL);
      tableItem.setImage(0, new Image(statementTestTable.getDisplay(),
          StatementTestPlugin.getImageDescriptor(STATUS_SUCCESSFUL_ICON).getImageData()));
    } else {
      tableItem.setText(0, STATUS_FAILED);
      tableItem.setImage(0, new Image(statementTestTable.getDisplay(),
          StatementTestPlugin.getImageDescriptor(STATUS_FAILED_ICON).getImageData()));
    }

    statementTests.put(tableItem, statementTest);

    statementTestTable.select(statementTestTable.getItemCount() - 1);
    statementTestTable.forceFocus();

    this.updateTabFolder(statementTest);
  }

  /**
   * Shows the log tab.
   */
  public void showLog() {
    this.tabFolder.setSelection(0);
  }

  /**
   * Updates the folder items with the given statement test.
   * 
   * @param statementTest
   */
  private void updateTabFolder(StatementTest statementTest) {
    this.buildResultComposite(statementTest);

    if (statementTest.getParameterVariables().size() > 0
        || statementTest.getContainerVariables().size() > 0) {
      this.buildParameterTable(statementTest);
    } else {
      this.clearParameterTable();
    }

    if (!statementTest.getLog().isEmpty()) {
      String log = "";

      for (String line : statementTest.getLog()) {
        log += line + "\n";
      }

      logTextField.setText(log);
      logTextField.setEnabled(true);
      logTextField.setBackground(new Color(tabFolder.getDisplay(), 255, 255, 255));
    } else {
      logTextField.setText("");
      logTextField.setEnabled(false);
      logTextField.setBackground(tabFolder.getBackground());
    }
  }

  /**
   * Builds the table for the statement tests.
   * 
   * @param parent
   */
  private void buildStatementTestsTable() {
    statementTestTable.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
    statementTestTable.setLinesVisible(true);
    statementTestTable.setHeaderVisible(true);
    statementTestTable.addListener(SWT.Selection, new Listener() {
      @Override
      public void handleEvent(Event event) {
        if (selectedStatementTest != statementTests.get(((Table) event.widget)
            .getSelection()[0])) {
          updateTabFolder(statementTests.get(((Table) event.widget).getSelection()[0]));
          selectedStatementTest = statementTests.get(((Table) event.widget)
              .getSelection()[0]);
        }
      }
    });

    // table header
    statusColumn = new TableColumn(statementTestTable, SWT.LEFT);
    activityColumn = new TableColumn(statementTestTable, SWT.LEFT);
    typeColumn = new TableColumn(statementTestTable, SWT.LEFT);
    processColumn = new TableColumn(statementTestTable, SWT.LEFT);
    statementColumn = new TableColumn(statementTestTable, SWT.LEFT);

    statusColumn.setText("Status");
    activityColumn.setText("Activity");
    typeColumn.setText("Type");
    processColumn.setText("Process");
    statementColumn.setText("Statement");

    activityColumn.setWidth(120);
    typeColumn.setWidth(100);
    processColumn.setWidth(100);
    statementColumn.setWidth(280);
    statusColumn.setWidth(80);
  }

  /**
   * Builts a result table from rdb result data.
   * 
   * @param result
   */
  private void buildRDBResultTable(RDBResult result, Table table) {
    TableItem row = null;
    TableColumn column = null;

    table.setLinesVisible(true);
    table.setHeaderVisible(true);
    table.setEnabled(true);

    // numeration column
    column = new TableColumn(table, SWT.LEFT);
    column.setText("#");
    column.setWidth(25);

    for (String columnName : result.getColumns()) {
      column = new TableColumn(table, SWT.LEFT);
      
      // primary key marker
      if (result.getPrimaryKeys().contains(columnName)) {
        columnName = "*" + columnName;
      }
      
      column.setText(columnName);
      column.setWidth(100);
    }

    for (int i = 0; i < result.getRowCount(); i++) {
      row = new TableItem(table, SWT.NONE);

      // numeration
      row.setText(0, String.valueOf(i + 1));

      for (int k = 0; k < result.getValues(i).length; k++) {
        row.setText(k + 1, result.getValues(i)[k]);
      }
    }

    this.whiteValueLabel.setText(String.valueOf(result.getRowCount()) + STATISTIC_GAP);
  }

  /**
   * Builts a comparative result table between two rdb results, where altered data is
   * colorized.
   * 
   * result: after execution comparativeResult: before execution
   * 
   * green = new data red = deleted data orange = altered data
   * 
   * @param result
   * @param comparativeResult
   */
  private void buildRDBResultComparativeTable(RDBResult result,
      RDBResult comparativeResult) {
    TableItem row = null;
    TableColumn column = null;

    int newTuple = 0;
    int deletedTuple = 0;
    int alteredTuple = 0;
    boolean changeInRow = false;

    resultTable.setLinesVisible(true);
    resultTable.setHeaderVisible(true);
    resultTable.setEnabled(true);

    // numeration column
    column = new TableColumn(resultTable, SWT.LEFT);
    column.setText("#");
    column.setWidth(25);

    if (result.getRowCount() >= comparativeResult.getRowCount()) {
      for (String columnName : result.getColumns()) {
        column = new TableColumn(resultTable, SWT.LEFT);
        column.setText(columnName);
        column.setWidth(100);
      }

      for (int i = 0; i < result.getRowCount(); i++) {
        row = new TableItem(resultTable, SWT.NONE);

        changeInRow = false;

        // numeration
        row.setText(0, String.valueOf(i + 1));

        for (int k = 0; k < result.getValues(i).length; k++) {
          // check if row exists in comparative result
          if (comparativeResult.getValues(i).length > 0) {
            if (result.getValues(i)[k].equals(comparativeResult.getValues(i)[k])) {
              row.setText(k + 1, result.getValues(i)[k]);
            } else {
              row.setBackground(k + 1, row.getDisplay().getSystemColor(SWT.COLOR_BLUE));
              row.setText(k + 1, result.getValues(i)[k]);

              if (!changeInRow) {
                alteredTuple++;
                changeInRow = true;
              }
            }
          } else {
            row.setBackground(k + 1, row.getDisplay().getSystemColor(SWT.COLOR_GREEN));
            row.setText(k + 1, result.getValues(i)[k]);

            if (!changeInRow) {
              newTuple++;
              changeInRow = true;
            }
          }
        }
      }

      this.whiteValueLabel.setText(String.valueOf(result.getRowCount()) + STATISTIC_GAP);
    } else {
      for (String columnName : comparativeResult.getColumns()) {
        column = new TableColumn(resultTable, SWT.LEFT);
        column.setText(columnName);
        column.setWidth(100);
      }

      for (int i = 0; i < comparativeResult.getRowCount(); i++) {
        row = new TableItem(resultTable, SWT.NONE);

        changeInRow = false;

        // numeration
        row.setText(0, String.valueOf(i + 1));

        for (int k = 0; k < comparativeResult.getValues(i).length; k++) {
          // check if row exists in result
          if (result.getValues(i).length > 0) {
            if (comparativeResult.getValues(i)[k].equals(result.getValues(i)[k])) {
              row.setText(k + 1, comparativeResult.getValues(i)[k]);
            } else {
              row.setBackground(k + 1, row.getDisplay().getSystemColor(SWT.COLOR_BLUE));
              row.setText(k + 1, comparativeResult.getValues(i)[k]);

              if (!changeInRow) {
                alteredTuple++;
                changeInRow = true;
              }
            }
          } else {
            row.setBackground(k + 1, row.getDisplay().getSystemColor(SWT.COLOR_RED));
            row.setText(k + 1, comparativeResult.getValues(i)[k]);

            if (!changeInRow) {
              deletedTuple++;
              changeInRow = true;
            }
          }
        }
      }

      this.whiteValueLabel.setText(String.valueOf(comparativeResult.getRowCount())
          + STATISTIC_GAP);
    }

    this.greenValueLabel.setText(String.valueOf(newTuple) + STATISTIC_GAP);
    this.redValueLabel.setText(String.valueOf(deletedTuple) + STATISTIC_GAP);
    this.blueValueLabel.setText(String.valueOf(alteredTuple) + STATISTIC_GAP);
  }

  /**
   * Builts a table from a create table result.
   * 
   * @param tableResult
   */
  private void buildCreateTableResultTable(CreateTableResult tableResult) {
    TableItem row = null;
    TableColumn column = null;

    resultTable.setLinesVisible(true);
    resultTable.setHeaderVisible(true);
    resultTable.setEnabled(true);

    column = new TableColumn(resultTable, SWT.LEFT);
    column.setText("Column");
    column.setWidth(100);

    column = new TableColumn(resultTable, SWT.LEFT);
    column.setText("Type");
    column.setWidth(100);

    for (int i = 0; i < tableResult.getColumns().size(); i++) {
      String col = tableResult.getColumns().get(i);
      String colName = col.substring(0, col.indexOf(" "));
      String colType = col.substring(col.indexOf(" "), col.length());

      row = new TableItem(resultTable, SWT.NONE);

      // primary key marker
      if (tableResult.getPrimaryKeys().contains(colName)) {
        colName = "*" + colName;
      }

      // column name
      row.setText(0, colName);

      // column type
      row.setText(1, colType);
    }
  }

  /**
   * Builds the parameter table from the variables.
   * 
   * @param statementTest
   */
  private void buildParameterTable(StatementTest statementTest) {
    TableItem row = null;
    String quote = "";
    ParameterVariable parameterVariable = null;
    ContainerVariable containerVariable = null;

    this.clearParameterTable();

    parameterTable.setLinesVisible(true);
    parameterTable.setHeaderVisible(true);
    parameterTable.setEnabled(true);

    for (Variable variable : statementTest.getParameterVariables().keySet()) {
      parameterVariable = statementTest.getParameterVariables().get(variable);
      row = new TableItem(parameterTable, SWT.NONE);

      if (parameterVariable.isQuote()) {
        quote = "'";
      } else {
        quote = "";
      }

      row.setText(0, "#" + parameterVariable.getName() + "#");
      row.setText(1, quote + parameterVariable.getValue() + quote);
    }

    for (Variable variable : statementTest.getContainerVariables().keySet()) {
      containerVariable = statementTest.getContainerVariables().get(variable);
      row = new TableItem(parameterTable, SWT.NONE);

      row.setText(0, "[" + containerVariable.getName() + "]");
      row.setText(1, containerVariable.toString());
    }
  }

  /**
   * Builds the resultComposite within the tabFolder.
   */
  private void buildResultComposite(StatementTest statementTest) {
    if (!statementTestResultComposites.containsKey(statementTest)) {
      // result composite with tables and statistics
      resultComposite = new Composite(tabFolder, SWT.NONE);
      resultComposite.setLayout(new GridLayout(2, true));
      resultComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

      // result table
      tableComposite = new Composite(resultComposite, SWT.NONE);
      tableComposite.setLayout(new GridLayout(1, true));

      if (statementTest == null || !statementTest.isEnhancedResult()) {
        // span over original table column
        tableComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 2, 1));
      } else {
        tableComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
      }

      resultTableLabel = new Label(tableComposite, SWT.NONE);
      resultTableLabel.setLayoutData(gridData = new GridData(SWT.LEFT, SWT.CENTER, false,
          false));
      resultTableLabel.setText("Result Table:");

      resultTable = new Table(tableComposite, SWT.BORDER);
      resultTable.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
      resultTable.setEnabled(false);
      resultTable.setLinesVisible(true);
      resultTable.setHeaderVisible(true);

      if (statementTest != null && statementTest.isEnhancedResult()) {
        tableComposite = new Composite(resultComposite, SWT.NONE);
        tableComposite.setLayout(new GridLayout(1, true));
        tableComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

        originalTableLabel = new Label(tableComposite, SWT.NONE);
        originalTableLabel
            .setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false));
        originalTableLabel.setText("Original Table:");

        originalTable = new Table(tableComposite, SWT.BORDER);
        originalTable.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
        originalTable.setEnabled(false);
        originalTable.setLinesVisible(true);
        originalTable.setHeaderVisible(true);

        // synchronus scrolling
        // originalTable.getVerticalBar().addSelectionListener(new SelectionAdapter() {
        // @Override
        // public void widgetSelected(SelectionEvent e) {
        // if (originalTable.getVerticalBar().getSelection() < resultTable
        // .getVerticalBar().getMaximum()) {
        // resultTable.getVerticalBar().setSelection(
        // originalTable.getVerticalBar().getSelection());
        // }
        // }
        // });
        //
        // resultTable.getVerticalBar().addSelectionListener(new SelectionAdapter() {
        // @Override
        // public void widgetSelected(SelectionEvent e) {
        // if (resultTable.getVerticalBar().getSelection() < originalTable
        // .getVerticalBar().getMaximum())
        // originalTable.getVerticalBar().setSelection(
        // resultTable.getVerticalBar().getSelection());
        // }
        // });
      }

      // statistic composite
      this.buildStatisticComposite();

      // build the result tables
      if (statementTest != null) {
        this.buildResultTables(statementTest);
      }

      statementTestResultComposites.put(statementTest, resultComposite);

      // show the new result composite
      if (resultTabItem != null) {
        resultTabItem.setControl(resultComposite);
      }
    } else {
      // reuse the stored result composite
      resultTabItem.setControl(statementTestResultComposites.get(statementTest));
    }
  }

  /**
   * Builds the statisticComposite within the resultComposite.
   */
  private void buildStatisticComposite() {
    Label whiteLabel = null;
    Label whiteTextLabel = null;
    Label redLabel = null;
    Label redTextLabel = null;
    Label greenLabel = null;
    Label greenTextLabel = null;
    Label blueLabel = null;
    Label blueTextLabel = null;
    Label primaryKeyLabel = null;
    Label primaryKeyTextLabel = null;
    
    statisticComposite = new Composite(resultComposite, SWT.NONE);
    statisticComposite.setLayout(new GridLayout(14, false));
    statisticComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 2, 1));

    gridData = new GridData(SWT.LEFT, SWT.CENTER, false, false);
    gridData.heightHint = 11;
    gridData.widthHint = 11;

    // white label
    whiteLabel = new Label(statisticComposite, SWT.NONE);
    whiteLabel.setBackground(whiteLabel.getDisplay().getSystemColor(SWT.COLOR_WHITE));
    whiteLabel.setLayoutData(gridData);

    whiteTextLabel = new Label(statisticComposite, SWT.NONE);
    whiteTextLabel.setText("All Tuple:");
    whiteTextLabel.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false));

    whiteValueLabel = new Label(statisticComposite, SWT.NONE);
    whiteValueLabel.setText("0    ");
    whiteValueLabel.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false));

    // green label
    greenLabel = new Label(statisticComposite, SWT.NONE);
    greenLabel.setBackground(greenLabel.getDisplay().getSystemColor(SWT.COLOR_GREEN));
    greenLabel.setLayoutData(gridData);

    greenTextLabel = new Label(statisticComposite, SWT.NONE);
    greenTextLabel.setText("New Tuple:");
    greenTextLabel.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false));

    greenValueLabel = new Label(statisticComposite, SWT.NONE);
    greenValueLabel.setText("0    ");
    greenValueLabel.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false));

    // red label
    redLabel = new Label(statisticComposite, SWT.NONE);
    redLabel.setBackground(redLabel.getDisplay().getSystemColor(SWT.COLOR_RED));
    redLabel.setLayoutData(gridData);

    redTextLabel = new Label(statisticComposite, SWT.NONE);
    redTextLabel.setText("Deleted Tuple:");
    redTextLabel.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false));

    redValueLabel = new Label(statisticComposite, SWT.NONE);
    redValueLabel.setText("0    ");
    redValueLabel.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false));

    // blue label
    blueLabel = new Label(statisticComposite, SWT.NONE);
    blueLabel.setBackground(blueLabel.getDisplay().getSystemColor(SWT.COLOR_BLUE));
    blueLabel.setLayoutData(gridData);

    blueTextLabel = new Label(statisticComposite, SWT.NONE);
    blueTextLabel.setText("Altered Tuple:");
    blueTextLabel.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false));

    blueValueLabel = new Label(statisticComposite, SWT.NONE);
    blueValueLabel.setText("0    ");
    blueValueLabel.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false));
    
    // primary key label
    primaryKeyLabel = new Label(statisticComposite, SWT.NONE);
    primaryKeyLabel.setText("*");
    primaryKeyLabel.setLayoutData(gridData);

    primaryKeyTextLabel = new Label(statisticComposite, SWT.NONE);
    primaryKeyTextLabel.setText("Primary Key");
    primaryKeyTextLabel.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false));
  }

  /**
   * Builds the result tables.
   * 
   * @param statementTest
   */
  private void buildResultTables(StatementTest statementTest) {
    if (statementTest.getResult() instanceof RDBResult
        && statementTest.getComparativeResult() == null) {
      this.buildRDBResultTable(((RDBResult) statementTest.getResult()), resultTable);
    } else if (statementTest.getResult() instanceof RDBResult
        && statementTest.getComparativeResult() instanceof RDBResult) {
      this.buildRDBResultComparativeTable(((RDBResult) statementTest.getResult()),
          ((RDBResult) statementTest.getComparativeResult()));

      if (statementTest.isEnhancedResult()) {
        this.buildRDBResultTable(((RDBResult) statementTest.getComparativeResult()),
            originalTable);
      }
    } else if (statementTest.getResult() instanceof CreateTableResult) {
      this.buildCreateTableResultTable(((CreateTableResult) statementTest.getResult()));
    }

    // activity specific gui changes
    if (statementTest.getActivity().eClass().getName().equals("CreateActivity")) {
      if (statementTest.getResult() != null) {
        resultTableLabel.setText("Created Table: "
            + ((CreateTableResult) statementTest.getResult()).getTable());
      } else {
        resultTableLabel.setText("Created Table:");
      }
    } else if (statementTest.getActivity().eClass().getName().equals("DropActivity")) {
      resultTableLabel.setText("Dropped Table: "
          + ((RDBResult) statementTest.getComparativeResult()).getTable());
    }
  }

  /**
   * Clears the parameter table.
   */
  private void clearParameterTable() {
    parameterTable.removeAll();
    parameterTable.setEnabled(false);
  }
}
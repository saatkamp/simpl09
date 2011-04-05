package org.eclipse.simpl.statementtest.execution;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.simpl.statementtest.model.StatementTest;
import org.eclipse.simpl.statementtest.model.results.CreateTableResult;
import org.eclipse.simpl.statementtest.model.results.RelationalResult;
import org.eclipse.simpl.statementtest.utils.StringUtils;
import org.simpl.core.webservices.client.SIMPLCoreService;
import org.simpl.resource.management.data.LateBinding;

/**
 * TODO <b>Purpose:</b><br>
 * <b>Description:</b><br>
 * <b>Copyright:</b>Licensed under the Apache License, Version 2.0.
 * http://www.apache.org/licenses/LICENSE-2.0<br>
 * <b>Company:</b>SIMPL<br>
 * 
 * @author Michael Schneidt <michael.schneidt@arcor.de><br>
 * @version $Id$<br>
 * @link http://code.google.com/p/simpl09/
 */
public class DatabaseStatementExecution extends StatementExecution {
  /**
   * @param statementTest
   * @param simplCoreService
   */
  public DatabaseStatementExecution(StatementTest statementTest,
      SIMPLCoreService simplCoreService) {
    super(statementTest, simplCoreService);
  }

  @Override
  public void executeDeleteActivityStatement() {
    String statementPattern = "(?i)DELETE (?i)FROM (.*?)(?:\\s(?i)WHERE(.*?))?$";
    String tables = null;
    String condition = null;

    Pattern parameterPattern = Pattern.compile(statementPattern);
    Matcher matcher = parameterPattern
        .matcher(this.statementTest.getGeneratedStatement());

    this.statementTest.log("= Statement analysis =");

    // get tables and condition from statement
    if (matcher.find()) {
      tables = matcher.group(1).trim();

      if (matcher.group(2) != null) {
        condition = matcher.group(2);
      }
    } else {
      statementTest.log("Invalid statement.");
      return;
    }

    this.statementTest.log("Table: " + tables);

    if (condition != null) {
      this.statementTest
          .log("Condition before: " + (condition != null ? condition : "-"));
      this.statementTest.log("Condition after: " + (condition != null ? condition : "-"));
    }

    // execute statement
    this.executeStatementAndCompare(tables, null, condition, null, true);
  }

  @Override
  public void executeDropActivityStatement() {
    String statementPattern = "(?i)DROP (?i)TABLE (.*?)$";
    String table = null;

    Pattern parameterPattern = Pattern.compile(statementPattern);
    Matcher matcher = parameterPattern
        .matcher(this.statementTest.getGeneratedStatement());

    this.statementTest.log("= Statement analysis =");

    // get tables and condition from statement
    if (matcher.find()) {
      table = matcher.group(1).trim();
    } else {
      statementTest.log("Invalid statement.");
      return;
    }

    this.statementTest.log("Table: " + table);

    // execute statement
    this.executeStatementAndCompare(table, null, null, null, false);
  }

  @Override
  public void executeInsertActivityStatement() {
    String tables = null;
    List<String> columns = new ArrayList<String>();
    List<String> values = new ArrayList<String>();

    String statementPattern = "(?i)INSERT (?i)INTO (.*?) (?:\\((.*?)\\) (?i)VALUES \\((.*?)\\)||(?i)VALUES \\((.*?)\\))$";
    String statementPatternSubSelect = "(?i)INSERT (?i)INTO (.*?) (?:\\((.*?)\\) \\(?(.*?)\\)?||\\(?(.*?)\\)?)$";

    Pattern parameterPattern = Pattern.compile(statementPattern);
    Pattern parameterPatternSubSelect = Pattern.compile(statementPatternSubSelect);

    Matcher matcher = parameterPattern
        .matcher(this.statementTest.getGeneratedStatement());
    Matcher matcherSubSelect = parameterPatternSubSelect.matcher(this.statementTest
        .getGeneratedStatement());

    String condition = null;

    this.statementTest.log("= Statement analysis =");

    // get table, columns and values from statement
    if (matcher.find()) {
      tables = matcher.group(1).trim();

      if (matcher.group(4) == null) {
        // e.g. INSERT INTO table (id, name, ...) VALUES (value1, value2, ...)
        columns = Arrays.asList(matcher.group(2).trim().split(","));
        values = Arrays.asList(matcher.group(3).trim().split(","));
      } else {
        // e.g. INSERT INTO table VALUES (value1, value2, ...)
        values = Arrays.asList(matcher.group(4).trim().split(","));
      }
    } else if (matcherSubSelect.find()) {
      tables = matcherSubSelect.group(1).trim();

      if (matcherSubSelect.group(4) == null) {
        // e.g. INSERT INTO table1 (id, name) (SELECT * FROM table2)
        // e.g. INSERT INTO table1 (id, name) SELECT * FROM table2
        columns = Arrays.asList(matcherSubSelect.group(2).trim().split(","));
      } else {
        // e.g. INSERT INTO table1 (SELECT * FROM table2)
        // e.g. INSERT INTO table1 SELECT * FROM table2
        // no information about columns or values in statement
      }
    } else {
      statementTest.log("Invalid statement.");
      return;
    }

    if (statementTest.isExclusiveResult()) {
      condition = addToCondition(null, columns, values);

      // clear columns to select all columns on execution
      columns.clear();
    }

    this.statementTest.log("Table: " + tables);
    this.statementTest.log("Columns: " + columns.toString().replaceAll("\\[|\\]", ""));
    this.statementTest.log("Condition before: " + (condition != null ? condition : "-"));
    this.statementTest.log("Condition after: " + (condition != null ? condition : "-"));

    // execute statement
    this.statementTest.log("= Statement execution =");
    this.executeStatementAndCompare(tables, columns, condition, null, true);
  }

  @Override
  public void executeQueryActivityStatement() {
    String statementPattern = "(?i)SELECT (.*?) (?i)FROM (.*?)(?:\\s(?i)WHERE(.*?))?$";
    String tables = null;
    List<String> columns = new ArrayList<String>();
    String execStatement = this.statementTest.getGeneratedStatement();

    Pattern parameterPattern = Pattern.compile(statementPattern);
    Matcher matcher = parameterPattern
        .matcher(this.statementTest.getGeneratedStatement());

    this.statementTest.log("= Statement analysis =");

    // get tables, columns and condition from statement
    if (matcher.find()) {
      tables = matcher.group(2).trim();

      String columnValues = matcher.group(1).trim();
      String[] columnValuesSplit = columnValues.split("[,]");

      for (int i = 0; i < columnValuesSplit.length; i++) {
        columns.add(columnValuesSplit[i].trim());
      }
    } else {
      statementTest.log("Invalid statement.");
      return;
    }

    // TODO abfangen, wenn bereits ein LIMIT gesetzt ist.
    if (this.statementTest.isLimitedResult()) {
      execStatement += " LIMIT 10";
    }

    this.statementTest.log("Tables: " + tables);
    this.statementTest.log("Columns: " + columns.toString().replaceAll("\\[|\\]", ""));

    // execute statement
    this.statementTest.log("= Statement execution =");
    this.executeStatementAndRetrieve();
  }

  @Override
  public void executeUpdateActivityStatement() {
    String statementPattern = "(?i)UPDATE (.*?) (?i)SET (.*?)(?:\\s(?i)WHERE(.*?))?$";
    String tables = null;
    String condition = null;
    String comparativeCondition = null;
    List<String> columns = new ArrayList<String>();

    Pattern parameterPattern = Pattern.compile(statementPattern);
    Matcher matcher = parameterPattern
        .matcher(this.statementTest.getGeneratedStatement());

    this.statementTest.log("= Statement analysis =");

    // get tables, columns and condition from statement
    if (matcher.find()) {
      tables = matcher.group(1).trim();
      comparativeCondition = matcher.group(2).trim().replaceAll(",", " OR ");

      if (matcher.group(3) != null) {
        condition = matcher.group(3);
      } else {
        condition = matcher.group(3);
      }

      String columnsAndValues = matcher.group(2).trim();
      String[] columnAndValuesSplit = columnsAndValues.split("[=|,]");

      for (int i = 0; i < columnAndValuesSplit.length; i = i + 2) {
        columns.add(columnAndValuesSplit[i].trim());
      }
    } else {
      statementTest.log("Invalid statement.");

      return;
    }

    if (statementTest.isExclusiveResult()) {
      // clear columns to select all columns on execution
      columns.clear();

      if (condition != null) {
        if (comparativeCondition != null) {
          condition += " OR (" + comparativeCondition + ")";
        } else {
          condition = comparativeCondition;
        }
      }
    }

    this.statementTest.log("Table: " + tables);
    this.statementTest.log("Columns: " + columns.toString().replaceAll("\\[|\\]", ""));
    this.statementTest.log("Condition before: " + (condition != null ? condition : "-"));
    this.statementTest.log("Condition after: "
        + (comparativeCondition != null ? comparativeCondition : "-"));

    // execute the statement
    this.statementTest.log("= Statement execution =");
    this.executeStatementAndCompare(tables, columns, condition, comparativeCondition,
        true);
  }

  @Override
  public void executeCreateActivityStatement() {
    String statementPattern = "(?i)CREATE (?i)TABLE (.*?) \\((.*)\\)";
    String tableSchema = null;
    List<String> columns = new ArrayList<String>();
    List<String> pkColumns = new ArrayList<String>();
    List<String> primaryKeys = new ArrayList<String>();

    Pattern parameterPattern = Pattern.compile(statementPattern);
    Matcher matcher = parameterPattern
        .matcher(this.statementTest.getGeneratedStatement());

    this.statementTest.log("= Statement analysis =");

    // get tables and condition from statement
    if (matcher.find()) {
      tableSchema = matcher.group(1).trim();
      columns = new ArrayList<String>(Arrays.asList(matcher.group(2).trim().split(",")));

      // search for primary keys
      for (String column : columns) {
        if (column.contains("PRIMARY KEY")) {
          // e.g. CREATE TABLE Customer (SID integer, Last_Name varchar(30),
          // First_Name varchar(30), PRIMARY KEY (SID));
          Pattern pkPattern1 = Pattern.compile("PRIMARY KEY.*\\((.*?)\\)");

          // e.g. CREATE TABLE Customer (SID integer PRIMARY KEY, Last_Name
          // varchar(30), First_Name varchar(30));
          Pattern pkPattern2 = Pattern.compile("(.*?) PRIMARY KEY");

          matcher = pkPattern1.matcher(column);

          if (matcher.find()) {
            primaryKeys = Arrays.asList(matcher.group(1).trim().split(","));
          } else {
            matcher = pkPattern2.matcher(column);

            if (matcher.find()) {
              primaryKeys = Arrays.asList(matcher.group(1).trim());
            }
          }

          pkColumns.add(column);
        }
      }

      // remove primary key declarations from columns
      columns.removeAll(pkColumns);

      // trim column names
      for (int i = 0; i < columns.size(); i++) {
        columns.set(i, columns.get(i).trim());
      }
    } else {
      statementTest.log("Invalid statement.");
      return;
    }

    this.statementTest.log("Table: " + tableSchema);
    this.statementTest.log("Columns: " + columns.toString().replaceAll("\\[|\\]", ""));
    this.statementTest.log("Primary Keys: "
        + primaryKeys.toString().replaceAll("\\[|\\]", ""));

    // execute statement
    this.statementTest.log("= Statement execution =");
    this.executeStatement(tableSchema, columns, primaryKeys);
  }

  /**
   * Executes the generated statement and sets columns as result.
   * 
   * @param statement
   */
  private void executeStatement(String table, List<String> columns,
      List<String> primaryKeys) {
    try {
      this.statementTest.log("Execute statement: "
          + this.statementTest.getGeneratedStatement());

      boolean result = this.simplCoreService.issueCommandByDataSourceObject(
          this.statementTest.getDataSource(), this.statementTest.getGeneratedStatement(), new LateBinding());
      statementTest.setExecuted(result);

      if (result) {
        statementTest.setResult(new CreateTableResult(table, columns, primaryKeys));
        this.statementTest.log("Successfully executed statement.");
      } else {
        this.statementTest.log("Failed to execute statement.");

        return;
      }
    } catch (Exception e) {
      System.out.println(e.getMessage());
      this.statementTest.log("Error on statement execution.");
    }
  }

  /**
   * Executes the generated statement and retrieves data.
   * 
   * @param statement
   */
  private void executeStatementAndRetrieve() {
    try {
      this.statementTest.log("Execute statement: "
          + this.statementTest.getGeneratedStatement());

      String result = this.simplCoreService.retrieveDataByDataSourceObject(
          this.statementTest.getDataSource(), this.statementTest.getGeneratedStatement(), new LateBinding());

      if (result != null && !result.equals("")) {
        statementTest.setExecuted(true);
        statementTest.setResult(new RelationalResult(result));
        this.statementTest.log("Retrieved "
            + ((RelationalResult) this.statementTest.getResult()).getRowCount()
            + " tuple.");
      } else {
        this.statementTest.log("Failed to retrieve result.");

        return;
      }
    } catch (Exception e) {
      System.out.println(e.getMessage());
      this.statementTest.log("Error on statement execution.");
    }
  }

  /**
   * Executes the generated statement and retrieves comparative results.
   * 
   * @param tables
   * @param columns
   * @param conditionBefore
   * @param conditionAfter
   * @param getResultAfter
   */
  private void executeStatementAndCompare(String tables, List<String> columns,
      String conditionBefore, String conditionAfter, boolean getResultAfter) {
    String resultBefore = null;
    String resultAfter = null;
    String execStatement = null;

    // define statement to execute
    if (this.statementTest.isExclusiveResult()) {
      execStatement = "SELECT "
          + (columns == null || columns.isEmpty() ? "*" : StringUtils.implode(columns,
              ",")) + " FROM " + tables
          + (conditionBefore == null ? "" : " WHERE " + conditionBefore);
    } else {
      execStatement = "SELECT * FROM " + tables;
    }

    // TODO abfangen, wenn bereits ein LIMIT gesetzt ist.
    if (this.statementTest.isLimitedResult()) {
      execStatement += " LIMIT 10";
    }

    // retrieve result before
    try {
      this.statementTest.log("Retrieve result before: " + execStatement);

      resultBefore = this.simplCoreService.retrieveDataByDataSourceObject(
          this.statementTest.getDataSource(), execStatement, new LateBinding());

      // set table name (for drop activity)
      RelationalResult result = new RelationalResult(resultBefore);
      result.setTable(tables);

      if (resultBefore != null && !resultBefore.isEmpty()) {
        this.statementTest.setComparativeResult(new RelationalResult(resultBefore));
        this.statementTest.log("Retrieved "
            + ((RelationalResult) this.statementTest.getComparativeResult())
                .getRowCount() + " tuple.");
      } else {
        this.statementTest.log("Failed to retrieve result.");

        return;
      }
    } catch (Exception e) {
      System.out.println(e.getMessage());
      this.statementTest.log("Error on statement execution.");

      return;
    }

    // execute statement
    try {
      this.statementTest.log("Execute statement: "
          + this.statementTest.getGeneratedStatement());

      boolean result = simplCoreService.issueCommandByDataSourceObject(
          this.statementTest.getDataSource(), this.statementTest.getGeneratedStatement(), new LateBinding());
      statementTest.setExecuted(result);

      if (result) {
        statementTest.log("Successfully executed statement.");
      } else {
        statementTest.log("Failed to execute statement.");
      }
    } catch (Exception e) {
      System.out.println(e.getMessage());
      this.statementTest.log("Error on statement execution.");

      return;
    }

    if (getResultAfter) {
      // modify statement to execute with condition after
      if (this.statementTest.isExclusiveResult() && conditionAfter != null) {
        execStatement = execStatement.replaceFirst("(?i)WHERE(.*?)$", "");
        execStatement += " WHERE " + conditionAfter;

        // TODO abfangen, wenn bereits ein LIMIT gesetzt ist.
        if (this.statementTest.isLimitedResult()) {
          execStatement += " LIMIT 10";
        }
      }

      // retrieve result after
      try {
        statementTest.log("Retrieve result after: " + execStatement);
        resultAfter = this.simplCoreService.retrieveDataByDataSourceObject(
            this.statementTest.getDataSource(), execStatement, new LateBinding());

        if (resultAfter != null && !resultAfter.equals("")) {
          this.statementTest.setResult(new RelationalResult(resultAfter));
          statementTest.log("Retrieved "
              + ((RelationalResult) this.statementTest.getResult()).getRowCount()
              + " tuple.");
        } else {
          this.statementTest.setResult(new RelationalResult()); // empty result
          statementTest.log("Failed to retrieve result.");

          return;
        }
      } catch (Exception e) {
        System.out.println(e.getMessage());
        this.statementTest.log("Error on statement execution.");

        return;
      }
    } else {
      this.statementTest.setResult(new RelationalResult());
    }
  }

  /**
   * Adds column/value pairs to a condition.
   * 
   * @param cond
   * @param columns
   * @param values
   * @param operation
   */
  private String addToCondition(String cond, List<String> columns, List<String> values) {
    String condition = cond;

    if (condition != null && !condition.equals("")) {
      // secure the condition
      condition = "(" + condition + ")";

      for (int i = 0; i < values.size(); i++) {
        condition += " AND " + columns.get(i).trim() + "=" + values.get(i).trim();
      }
    } else {
      // resolves to true for the following "AND"
      condition = "1=1";

      for (int i = 0; i < values.size(); i++) {
        condition += " AND " + columns.get(i).trim() + "=" + values.get(i).trim();
      }
    }

    return condition;
  }
}
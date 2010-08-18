package org.eclipse.simpl.statementtest.model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.LinkedList;

import org.eclipse.bpel.model.Process;
import org.eclipse.bpel.model.Variable;
import org.eclipse.bpel.simpl.model.DataManagementActivity;
import org.eclipse.simpl.communication.client.DataSource;
import org.eclipse.simpl.statementtest.model.results.Result;
import org.eclipse.simpl.statementtest.model.variables.ContainerVariable;
import org.eclipse.simpl.statementtest.model.variables.ParameterVariable;

/**
 * <b>Purpose: Represents a statement test and holds all information about the test
 * settings and results. Some statement tests, like for SQL INSERT or DELETE statements,
 * retrieve comparative data before statement execution, that can be used to verify the
 * result.</b><br>
 * <b>Description:</b> <br>
 * <b>Copyright:</b>Licensed under the Apache License, Version 2.0.
 * http://www.apache.org/licenses/LICENSE-2.0<br>
 * <b>Company:</b>SIMPL<br>
 * 
 * @author Michael Schneidt <michael.schneidt@arcor.de><br>
 * @version $Id: StatementTest.java 83 2010-08-04 13:35:18Z hiwi $<br>
 * @link http://code.google.com/p/simpl09/
 */
public class StatementTest {
  private DataManagementActivity activity = null;
  private Process process = null;
  private String statement = null;
  private String generatedStatement = null;
  private DataSource dataSource = null;
  private Result result = null;
  private Result comparativeResult = null;
  private LinkedList<String> log = new LinkedList<String>();
  private LinkedHashMap<Variable, ParameterVariable> parameterVariables = new LinkedHashMap<Variable, ParameterVariable>();
  private LinkedHashMap<Variable, ContainerVariable> containerVariables = new LinkedHashMap<Variable, ContainerVariable>();
  private boolean executed = false;
  private boolean limitedResult = false;
  private boolean exclusiveResult = false;
  private boolean enhancedResult = false;

  /**
   * Initialization.
   * 
   * @param activity
   * @param process
   */
  public StatementTest(DataManagementActivity activity, Process process) {
    this.activity = activity;
    this.process = process;
  }

  /**
   * @return the activity
   */
  public DataManagementActivity getActivity() {
    return activity;
  }

  /**
   * @param activity
   *          the activity to set
   */
  public void setActivity(DataManagementActivity activity) {
    this.activity = activity;
  }

  /**
   * @return the process
   */
  public Process getProcess() {
    return process;
  }

  /**
   * @param process
   *          the process to set
   */
  public void setProcess(Process process) {
    this.process = process;
  }

  /**
   * @return the statement
   */
  public String getStatement() {
    return statement;
  }

  /**
   * @param statement
   *          the statement to set
   */
  public void setStatement(String statement) {
    this.statement = statement.trim();
  }

  /**
   * @return the generatedStatement
   */
  public String getGeneratedStatement() {
    return generatedStatement;
  }

  /**
   * @param generatedStatement
   *          the generatedStatement to set
   */
  public void setGeneratedStatement(String generatedStatement) {
    this.generatedStatement = generatedStatement.trim();
  }

  /**
   * @return the dataSource
   */
  public DataSource getDataSource() {
    return dataSource;
  }

  /**
   * @param dataSource
   *          the dataSource to set
   */
  public void setDataSource(DataSource dataSource) {
    this.dataSource = dataSource;
  }

  /**
   * @return the result
   */
  public Result getResult() {
    return this.result;
  }

  /**
   * @param result
   *          the result to set
   */
  public void setResult(Result result) {
    this.result = result;
  }

  /**
   * @return the comparativeResult
   */
  public Result getComparativeResult() {
    return comparativeResult;
  }

  /**
   * @param comparativeResult
   *          the comparativeResult to set
   */
  public void setComparativeResult(Result comparativeResult) {
    this.comparativeResult = comparativeResult;
  }

  /**
   * @return the log
   */
  public LinkedList<String> getLog() {
    return log;
  }

  /**
   * @param log
   *          the log to set
   */
  public void log(String entry) {
    String timestamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());

    this.log.add("[" + timestamp + "] " + entry);
  }

  /**
   * @return the parameterVariables
   */
  public LinkedHashMap<Variable, ParameterVariable> getParameterVariables() {
    return parameterVariables;
  }

  /**
   * @param parameterVariables
   *          the parameterVariables to set
   */
  public void setParameterVariables(
      LinkedHashMap<Variable, ParameterVariable> parameterVariables) {
    this.parameterVariables = parameterVariables;
  }

  /**
   * @return the containerVariables
   */
  public LinkedHashMap<Variable, ContainerVariable> getContainerVariables() {
    return containerVariables;
  }

  /**
   * @param containerVariables
   *          the containerVariables to set
   */
  public void setContainerVariables(
      LinkedHashMap<Variable, ContainerVariable> containerVariables) {
    this.containerVariables = containerVariables;
  }

  /**
   * @return the executed
   */
  public boolean isExecuted() {
    return executed;
  }

  /**
   * @param executed
   *          the executed to set
   */
  public void setExecuted(boolean executed) {
    this.executed = executed;
  }

  /**
   * @return the limitedResult
   */
  public boolean isLimitedResult() {
    return limitedResult;
  }

  /**
   * @param limitedResult
   *          the limitedResult to set
   */
  public void setLimitedResult(boolean limitedResult) {
    this.limitedResult = limitedResult;
  }

  /**
   * @return the exclusiveResult
   */
  public boolean isExclusiveResult() {
    return exclusiveResult;
  }

  /**
   * @param exclusiveResult
   *          the exclusiveResult to set
   */
  public void setExclusiveResult(boolean exclusiveResult) {
    this.exclusiveResult = exclusiveResult;
  }

  /**
   * @return the enhancedResult
   */
  public boolean isEnhancedResult() {
    return enhancedResult;
  }

  /**
   * @param enhancedResult
   *          the enhancedResult to set
   */
  public void setEnhancedResult(boolean enhancedResult) {
    this.enhancedResult = enhancedResult;
  }
}
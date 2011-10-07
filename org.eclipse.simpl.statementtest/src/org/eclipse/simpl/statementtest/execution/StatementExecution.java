package org.eclipse.simpl.statementtest.execution;

import org.eclipse.simpl.statementtest.model.StatementTest;
import org.simpl.core.webservices.client.SIMPLCoreService;

/**
 * TODO <b>Purpose:</b><br>
 * <b>Description:</b><br>
 * <b>Copyright:</b>Licensed under the Apache License, Version 2.0.
 * http://www.apache.org/licenses/LICENSE-2.0<br>
 * <b>Company:</b>SIMPL<br>
 * 
 * TODO: Auf die drei Methoden beim SIMPL Core umstellen
 * 
 * @author Michael Schneidt <michael.schneidt@arcor.de><br>
 * @version $Id$<br>
 * @link http://code.google.com/p/simpl09/
 */
public abstract class StatementExecution {
  /**
   * The statement test.
   */
  protected StatementTest statementTest = null;

  /**
   * Instance of the SIMPL Core web service.
   */
  protected SIMPLCoreService simplCoreService = null;

  /**
   * Constructor.
   */
  public StatementExecution(StatementTest statementTest,
      SIMPLCoreService dataSourceService) {
    this.statementTest = statementTest;
    this.simplCoreService = dataSourceService;
  }

  /**
   * Executes a query activity statement.
   */
  public void executeQueryActivityStatement() {

  }

  /**
   * Executes a create activity statement.
   */
  public void executeCreateActivityStatement() {

  }

  /**
   * Executes a update activity statement.
   */
  public void executeUpdateActivityStatement() {

  }

  /**
   * Executes a insert activity statement.
   */
  public void executeInsertActivityStatement() {

  }

  /**
   * Executes a delete activity statement.
   */
  public void executeDeleteActivityStatement() {

  }

  /**
   * Executes a drop activity statement.
   */
  public void executeDropActivityStatement() {

  }
}
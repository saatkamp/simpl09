package org.eclipse.simpl.statementtest.execution;

import org.eclipse.simpl.communication.client.DatasourceService;
import org.eclipse.simpl.statementtest.model.StatementTest;
import org.eclipse.simpl.statementtest.model.results.RDBResult;

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
public class FilesystemStatementExecution extends StatementExecution {
  /**
   * @param statementTest
   * @param dataSourceService
   */
  public FilesystemStatementExecution(StatementTest statementTest,
      DatasourceService dataSourceService) {
    super(statementTest, dataSourceService);
  }

  @Override
  public void executeQueryActivityStatement() {
    try {
      this.statementTest.log("Execute statement: "
          + this.statementTest.getGeneratedStatement());

      String result = this.dataSourceService.retrieveData(this.statementTest
          .getDataSource(), this.statementTest.getGeneratedStatement());

      if (result != null && !result.equals("")) {
        statementTest.setExecuted(true);
        statementTest.setResult(new RDBResult(result));
        this.statementTest.log("Retrieved "
            + ((RDBResult) this.statementTest.getResult()).getRowCount() + " tuple.");
      } else {
        this.statementTest.log("Failed to retrieve result.");

        return;
      }
    } catch (Exception e) {
      System.out.println(e.getMessage());
      this.statementTest.log("Error on statement execution.");
    }
  }
}
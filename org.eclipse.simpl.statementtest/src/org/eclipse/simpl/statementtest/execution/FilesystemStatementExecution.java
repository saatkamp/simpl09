package org.eclipse.simpl.statementtest.execution;

import org.eclipse.simpl.statementtest.model.StatementTest;
import org.eclipse.simpl.statementtest.model.results.RelationalResult;
import org.eclipse.simpl.statementtest.model.results.XmlResult;
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
public class FilesystemStatementExecution extends StatementExecution {
  /**
   * @param statementTest
   * @param simplCoreService
   */
  public FilesystemStatementExecution(StatementTest statementTest,
      SIMPLCoreService simplCoreService) {
    super(statementTest, simplCoreService);
  }

  @Override
  public void executeQueryActivityStatement() {
    try {
      this.statementTest.log("Execute statement: "
          + this.statementTest.getGeneratedStatement());

      String result = this.simplCoreService.retrieveDataByDataSourceObject(
          this.statementTest.getDataSource(), this.statementTest.getGeneratedStatement(),
          new LateBinding());

      if (result != null && !result.equals("")) {
        statementTest.setExecuted(true);

        if (result.contains("dataFormatType=\"CSVDataFormat\"")) {
          statementTest.setResult(new RelationalResult(result));
          this.statementTest.log("Retrieved "
              + ((RelationalResult) this.statementTest.getResult()).getRowCount()
              + " tuple.");
        } else {
          statementTest.setResult(new XmlResult(result));
          this.statementTest.log("Retrieved file content.");          
        }
      } else {
        this.statementTest.log("Failed to retrieve result.");

        return;
      }
    } catch (Exception e) {
      System.out.println(e.getMessage());
      this.statementTest.log("Error on statement execution.");
    }
  }

  @Override
  public void executeDeleteActivityStatement() {
    executeStatement();
  }

  private boolean executeStatement() {
    boolean successed = false;

    try {
      this.statementTest.log("Execute statement: "
          + this.statementTest.getGeneratedStatement());

      successed = this.simplCoreService.issueCommandByDataSourceObject(
          this.statementTest.getDataSource(), this.statementTest.getGeneratedStatement(),
          new LateBinding());

      if (successed) {
        statementTest.setExecuted(true);
        this.statementTest.log("Successfully executed statement.");
      } else {
        this.statementTest.log("Failed to execute statement.");
      }
    } catch (Exception e) {
      System.out.println(e.getMessage());
      this.statementTest.log("Error on statement execution.");
    }

    return successed;
  }
}
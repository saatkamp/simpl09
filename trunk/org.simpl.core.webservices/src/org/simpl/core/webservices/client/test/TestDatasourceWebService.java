package org.simpl.core.webservices.client.test;

import org.simpl.core.helpers.Parameter;
import org.simpl.core.helpers.Printer;
import org.simpl.core.webservices.client.ConnectionException_Exception;
import org.simpl.core.webservices.client.DatasourceService;
import org.simpl.core.webservices.client.DatasourceService_Service;

import commonj.sdo.DataObject;

/**
 * <b>Purpose:</b> <br>
 * <b>Description:</b> <br>
 * <b>Copyright:</b> <br>
 * <b>Company:</b> SIMPL<br>
 * 
 * @author schneimi<br>
 * @version $Id: TestDatasourceWebService.java 879 2010-02-12 12:58:42Z michael.schneidt@arcor.de $<br>
 */
public class TestDatasourceWebService {

  /**
   * @param args
   * @throws ConnectionException_Exception
   */
  public static void main(String[] args) throws ConnectionException_Exception {
    DatasourceService_Service datasourceService = new DatasourceService_Service();
    DatasourceService datasourceServicePort = datasourceService
        .getDatasourceServicePort();
    
    System.out.println(Parameter.deserialize(datasourceServicePort.getDatasourceTypes()));
    System.out.println(Parameter.deserialize(datasourceServicePort.getDatasourceSubtypes("Database")));
    Printer.printDataObject((DataObject)Parameter.deserialize(datasourceServicePort.queryData("simplDB",
        "SELECT * FROM test.tabelle1", "Database", "EmbeddedDerby")));
    Parameter.deserialize(datasourceServicePort.getMetaData("simplDB", "Database", "EmbeddedDerby"));
  }
}
package org.simpl.core.webservices.client.test;

import org.simpl.core.webservices.client.ConnectionException_Exception;
import org.simpl.core.webservices.client.DatasourceService;
import org.simpl.core.webservices.client.DatasourceService_Service;
import org.simpl.core.webservices.helpers.Parameter;

/**
 * <b>Purpose:</b> <br>
 * <b>Description:</b> <br>
 * <b>Copyright:</b> <br>
 * <b>Company:</b> SIMPL<br>
 * 
 * @author Michael Schneidt <michael.schneidt@arcor.de><br>
 * @version $Id$<br>
 */
public class TestDatasourceWebService {

  /**
   * @param args
   * @throws ConnectionException_Exception 
   */
  public static void main(String[] args) throws ConnectionException_Exception {
    DatasourceService_Service datasourceService = new DatasourceService_Service();
    DatasourceService datasourceServicePort = datasourceService.getDatasourceServicePort();
    
    System.out.println(Parameter.deserialize(datasourceServicePort.queryData("simplDB", "SELECT * FROM test.tabelle1", "RDB")));
  }
}

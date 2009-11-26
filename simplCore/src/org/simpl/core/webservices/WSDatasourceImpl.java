package org.simpl.core.webservices;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

import org.simpl.core.datasource.ConnectionException;
import org.simpl.core.datasource.DataException;
import org.simpl.core.datasource.DatasourceService;
import org.simpl.core.datasource.DatasourceServiceFactory;

@WebService(name = "DatasourceService", targetNamespace = "http://localhost:8080/DatasourceService", wsdlLocation = "DatasourceService.wsdl")
@SOAPBinding(style = SOAPBinding.Style.RPC)
public class WSDatasourceImpl implements WSDatasource {
  @Override
  @WebMethod
  public String executeStatement(String statement, String type) {
    // TODO Auto-generated method stub
    //String statement = "CREATE TABLE ADMINISTRATOR.TEST ( COLUMN1 BIGINT  NOT NULL , COLUMN2 VARCHAR (10)  NOT NULL  , CONSTRAINT CC1259163587812 PRIMARY KEY ( COLUMN1)  )";

    // String statement =
    // "DROP TABLE ADMINISTRATOR.TEST";

    DatasourceService service = DatasourceServiceFactory.getInstance(type)
        .getDatasourceProvider();
    try {
      service.sendStatement(statement);
    } catch (ConnectionException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (DataException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    return "Tabelle erstellt. :-)";
  }
}
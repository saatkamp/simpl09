package web.service.data.db;

import java.io.ByteArrayOutputStream;
import java.io.IOException;


import commonj.sdo.DataObject;
import commonj.sdo.helper.XMLDocument;
import commonj.sdo.helper.XMLHelper;

public class DataSourceService {
  PostgreSQLRDBDataSourceService service = new PostgreSQLRDBDataSourceService();

  public boolean executeStatement(DataSource dataSource, String statement)
      throws Exception {
    boolean success = false;

    success = service.executeStatement(dataSource, statement);

    return success;
  }

  public String retrieveData(DataSource dataSource, String statement) throws Exception {
    DataObject dataObject = null;
    String data = null;
    RDBDataFormat dataFormat = new RDBDataFormat();
    RDBResult rdbResult = service.retrieveData(dataSource, statement);
    
    // Output stream to write SDO to XML string
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    
    dataObject = dataFormat.toSDO(rdbResult);

    if (dataObject != null) {      
      try {
        XMLDocument xmlDocument = XMLHelper.INSTANCE.createDocument(dataObject,
            "commonj.sdo", "dataObject");
        xmlDocument.setEncoding("UTF-8");
        XMLHelper.INSTANCE.save(xmlDocument, outputStream, null);
      } catch (IOException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
    }

    data = new String(outputStream.toByteArray());
    outputStream.close();
    
    return data;
  }
}
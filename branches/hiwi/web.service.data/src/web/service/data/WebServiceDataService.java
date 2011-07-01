package web.service.data;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.stream.FactoryConfigurationError;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.xml.sax.InputSource;

import web.service.data.db.DataSource;
import web.service.data.db.DataSourceService;
import de.uni_stuttgart.dwarf.www.biif.BiifType;

/**
 * <b>Purpose:</b>Web Service to retrieve data for measuring.<br>
 * <b>Description:</b>Let you retrieve data of different size from a PostgreSQL database.
 * Data can be passed in raw format or as file location.<br>
 * <b>Company:</b>IPVS<br>
 * 
 * @author hiwi<br>
 * @version $Id$<br>
 * @author Michael Schneidt <michael.schneidt@arcor.de>
 */
public class WebServiceDataService {
  DataSourceService dataSourceService = new DataSourceService();
  DataSource dataSource = WebServiceDataConfig.getInstance().getDataSource();

  /**
   * Returns data of specific size.
   * 
   * @return
   * @throws Exception
   */
  public BiifType getData(int size) throws Throwable {
    BiifType data = null;
    String statement = "";
    String result = null;

    statement += "SELECT data AS data ";
    statement += "FROM webservicedata ";
    statement += "WHERE size = " + size;

    // retrieve data
    result = dataSourceService.retrieveData(dataSource, statement);

    if (result != null) {
      data = this.getDataFromResult(result);
    }

    return data;
  }

  /**
   * Adds data of specific size.
   * 
   * @return
   * @throws Exception
   */
  public boolean addData(int size, String data) throws Throwable {
    String statement = "";
    boolean result = false;

    statement = "INSERT INTO webservicedata (size, data) VALUES (";
    statement += String.valueOf(size);
    statement += ", '" + data + "'";
    statement += ")";

    result = dataSourceService.executeStatement(dataSource, statement);

    return result;
  }

  /**
   * Updates data of a specific size.
   * 
   * @return
   * @throws Exception
   */
  public boolean updateData(int id, int size, String data) throws Throwable {
    String statement = "";
    boolean result = false;

    statement = "UPDATE webservicedata SET size=" + String.valueOf(size) + " ";

    if (!data.equals("")) {
      statement += ", data='" + data + "' ";
    }

    statement += "WHERE id=" + id;

    result = dataSourceService.executeStatement(dataSource, statement);

    return result;
  }

  /**
   * Creates the necessary tables in the configured PostgreSQL data source.
   * 
   * The SQL statements are retrieved from the web_service_data.sql file that is placed
   * inside the jar.
   * 
   * @return
   * @throws Exception
   */
  public boolean createTables() throws Throwable {
    final String sqlFile = "sql/web_service_data.sql";

    boolean successful = false;
    String fileLine = null;
    String statement = "";
    ArrayList<String> statements = new ArrayList<String>();
    BufferedReader bufferedFileReader = null;
    InputStream fileStream = null;

    fileStream = this.getClass().getClassLoader().getResourceAsStream(sqlFile);
    bufferedFileReader = new BufferedReader(new InputStreamReader(fileStream));

    // TODO: optimize statement recognition and allow empty lines between function
    // declarations

    // build one-line statements
    while ((fileLine = bufferedFileReader.readLine()) != null) {
      if (!fileLine.equals("")) {
        statement += fileLine;
      } else {
        statements.add(statement);
        statement = "";
      }
    }

    // execute statements
    for (int i = 0; i < statements.size(); i++) {
      successful = dataSourceService.executeStatement(dataSource, statements.get(i));

      if (!successful) {
        break;
      }
    }

    // drop tables on failure
    if (!successful) {
      dataSourceService.executeStatement(dataSource,
          "DROP TABLE IF EXISTS webservicedata");
    }

    return successful;
  }

  /**
   * Returns the data from a RDB data format result.
   * 
   * @param result
   * @return
   * @throws FactoryConfigurationError
   * @throws java.lang.Exception
   * @throws Exception
   */
  @SuppressWarnings("unchecked")
  private BiifType getDataFromResult(String result) throws FactoryConfigurationError,
      java.lang.Exception {
    BiifType biifType = null;

    Document configDoc = null;
    Element root = null;
    List<Element> rows = null;
    SAXBuilder saxBuilder = new SAXBuilder();

    // transform the document to a list of data source objects
    configDoc = saxBuilder.build(new InputSource(new StringReader(result)));
    root = configDoc.getRootElement();
    rows = root.getChild("table").getChildren("row");

    for (Element row : rows) {
      List<Element> columns = row.getChildren("column");

      for (Element column : columns) {
        if (column.getAttribute("name").getValue().equals("data")) {
          String marshalledData = column.getValue();

          // fix encoding issue
          marshalledData = marshalledData.replace("\\015\\012", "");

          XMLStreamReader reader = XMLInputFactory.newInstance().createXMLStreamReader(
              new ByteArrayInputStream(marshalledData.getBytes()));

          biifType = BiifType.Factory.parse(reader);

          break;
        }
      }
    }

    return biifType;
  }
}
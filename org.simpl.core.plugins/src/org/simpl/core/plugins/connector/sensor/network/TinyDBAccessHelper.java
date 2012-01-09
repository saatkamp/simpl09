package org.simpl.core.plugins.connector.sensor.network;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import org.simpl.core.plugins.dataconverter.sensor.network.TinyDBResult;
import org.simpl.resource.management.data.DataSource;

import net.tinyos.tinydb.QueryResult;
import net.tinyos.tinydb.ResultListener;
import net.tinyos.tinydb.TinyDBMain;
import net.tinyos.tinydb.TinyDBQuery;
import net.tinyos.tinydb.parser.ParseException;
import net.tinyos.tinydb.parser.SensorQueryer;

/**
 * <b>Purpose:</b>This class is used to get a connection to TinyDB and to
 * start/abort queries.<br>
 * <b>Description:</b>Two files are necessary: 'catalog.xml' and 'tinydb.conf'.
 * They must be stored at the following location:
 * 'webapps/ode/WEB-INF/conf/tinydb_conf/'.<br>
 * <b>Copyright:</b>Licensed under the Apache License, Version 2.0.
 * http://www.apache.org/licenses/LICENSE-2.0<br>
 * <b>Company:</b>SIMPL<br>
 * 
 * @author Henrik Pietranek <henrik.pietranek@googlemail.com><br>
 * @link http://code.google.com/p/simpl09/
 */
public class TinyDBAccessHelper {

  private DataSource dataSource = null;
  // results will be stored in this list
  private List<QueryResult> queryResults = null;
  private TinyDBQuery tinyDBQuery;
  private ResultListener resultListener;

  /**
   * Initialize the helper.
   * 
   * @param dataSource
   *          the data source
   */
  public TinyDBAccessHelper(DataSource dataSource) {
    this.dataSource = dataSource;
  }

  /**
   * This method stops a running query.
   */
  public void abortQuery() {
    TinyDBMain.network.abortQuery(tinyDBQuery);
    TinyDBMain.network.removeQuery(tinyDBQuery);
    TinyDBMain.network.removeResultListener(resultListener);
    TinyDBMain.notifyRemovedQuery(tinyDBQuery);
    TinyDBMain.removeQueryListener(TinyDBMain.network);
  }

  /**
   * This method creates a TinyDBResult from the retrieved data.
   * 
   * @return TinyDBResult
   */
  public TinyDBResult getResult() {
    TinyDBResult tinyDBResult = null;
    if (queryResults.size() > 0) {
      tinyDBResult = new TinyDBResult();
      tinyDBResult.setQueryResults(queryResults);
      tinyDBResult.setColumnHeadings(tinyDBQuery.getColumnHeadings());
      tinyDBResult.setDataSource(dataSource);
    }
    return tinyDBResult;
  }

  /**
   * This method starts a query.
   * 
   * @param statement
   * @throws IOException
   * @throws ParseException
   */
  public void injectQuery(String statement) throws IOException, ParseException {
    queryResults = new LinkedList<QueryResult>();
    resultListener = new ResultListener() {
      @Override
      public void addResult(QueryResult queryResult) {
        queryResults.add(queryResult);
      }
    };

    // initialize
    TinyDBMain.initMain("webapps/ode/WEB-INF/conf/tinydb_conf/tinydb.conf");

    // parse the query
    tinyDBQuery = SensorQueryer.translateQuery(statement, (byte) 1);

    // inject the query, registering ourselves as a listener for result
    TinyDBMain.injectQuery(tinyDBQuery, resultListener);
  }
}

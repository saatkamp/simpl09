package org.eclipse.simpl.statementtest.model.results;

import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.NodeList;

/**
 * <b>Purpose:</b>Result that holds the data from a RDBFormat SDO.<br>
 * <b>Description:</b><br>
 * <b>Copyright:</b>Licensed under the Apache License, Version 2.0.
 * http://www.apache.org/licenses/LICENSE-2.0<br>
 * <b>Company:</b>SIMPL<br>
 * 
 * @author Michael Schneidt <michael.schneidt@arcor.de><br>
 * @version $Id: RDBResult.java 87 2010-08-08 14:56:15Z hiwi $<br>
 * @link http://code.google.com/p/simpl09/
 */
public class RDBResult extends Result {
  int rowCount = 0;
  String schema = null;
  String table = null;
  List<String> columns = new ArrayList<String>();
  List<String> values = new ArrayList<String>();

  public RDBResult() {

  }

  /**
   * SDO result.
   * 
   * @param result
   */
  public RDBResult(String result) {
    super(result);

    rowCount = doc.getElementsByTagName("row").getLength();

    NodeList tableNodes = doc.getElementsByTagName("table");
    NodeList rowNodes = tableNodes.item(0).getChildNodes();
    NodeList columnNodes = null;
    NamedNodeMap columnAttributes = null;

    if (rowNodes != null && rowNodes.item(1) != null) {
      columnNodes = rowNodes.item(1).getChildNodes();

      for (int i = 0; i < columnNodes.getLength(); i++) {
        if (columnNodes.item(i).getNodeName().equals("column")) {
          columnAttributes = columnNodes.item(i).getAttributes();

          if (columnAttributes != null) {
            columns.add(columnAttributes.getNamedItem("name").getNodeValue());
          }
        }
      }
    }
  }

  /**
   * @return The table name.
   */
  public String getTable() {
    if (doc != null) {
      NodeList tableNodes = doc.getElementsByTagName("table");
      NamedNodeMap tableAttributes = tableNodes.item(0).getAttributes();

      table = tableAttributes.getNamedItem("name").getNodeValue();
    }

    return table;
  }

  /**
   * @return The schema name.
   */
  public String getSchema() {
    if (doc != null) {
      NodeList tableNodes = doc.getElementsByTagName("table");
      NamedNodeMap tableAttributes = tableNodes.item(0).getAttributes();

      schema = tableAttributes.getNamedItem("schema").getNodeValue();
    }

    return schema;
  }

  /**
   * @return List of columns.
   */
  public String[] getColumns() {
    return columns.toArray(new String[columns.size()]);
  }

  /**
   * @return List of values from a column.
   */
  public String[] getValues(int row) {
    if (doc != null) {
      NodeList tableNodes = doc.getElementsByTagName("table");
      NodeList rowNodes = tableNodes.item(0).getChildNodes();
      NodeList columnNodes = null;
      NamedNodeMap columnAttributes = null;
      int rowCounter = 0;

      values.clear();

      if (rowNodes != null) {
        for (int i = 0; i < rowNodes.getLength(); i++) {
          if (rowNodes.item(i).getNodeName().equals("row") && rowCounter++ == row) {
            columnNodes = rowNodes.item(i).getChildNodes();

            for (int j = 0; j < columnNodes.getLength(); j++) {
              columnAttributes = columnNodes.item(j).getAttributes();

              if (columnAttributes != null) {
                if (columnAttributes.getNamedItem("value") != null) {
                  values.add(columnAttributes.getNamedItem("value").getNodeValue());
                } else {
                  values.add("");
                }
              }
            }
          }
        }
      }
    }

    return values.toArray(new String[values.size()]);
  }

  /**
   * @return Number of rows.
   */
  public int getRowCount() {
    return rowCount;
  }

  // for (int i = 0; i < tableNodes.getLength(); i++) {
  // columnNodes = tableNodes.item(i).getChildNodes();
  //
  // for (int j = 0; j < columnNodes.getLength(); j++) {
  // columnAttributes = columnNodes.item(j).getAttributes();
  //
  // if (columnAttributes != null) {
  // // build table header
  // if (i == 0) {
  // column = new TableColumn(resultTable, SWT.LEFT);
  // column.setText(columnAttributes.getNamedItem("name").getNodeValue());
  // column.setWidth(100);
  // }
  //
  // // add value
  // row = new TableItem(resultTable, SWT.NONE);
  // row.setText(j, columnAttributes.getNamedItem("value").getNodeValue());
  // }
  // }
  // }
}
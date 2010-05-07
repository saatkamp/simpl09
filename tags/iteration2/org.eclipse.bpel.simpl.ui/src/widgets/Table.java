package widgets;

import java.util.ArrayList;

public class Table {
   ArrayList<String> listOfColumnsNames=new ArrayList<String>();
   String tableName;
   
   public ArrayList<String> getListOfColumnsNames() {
	return listOfColumnsNames;
}

public void setListOfColumnsNames(ArrayList<String> listOfColumnsNames) {
	this.listOfColumnsNames = listOfColumnsNames;
}

public String getTableName() {
	return tableName;
}

public void setTableName(String tableName) {
	this.tableName = tableName;
}

public Table(){
	   
   }
   
}

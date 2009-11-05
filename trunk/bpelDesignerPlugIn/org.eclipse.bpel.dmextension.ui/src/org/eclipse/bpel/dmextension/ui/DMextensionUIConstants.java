package org.eclipse.bpel.dmextension.ui;

public class DMextensionUIConstants {

	// The path of the icons
	public static final String ICON_PATH = "icons/";
	
	// Images
	
	// Activities
	public static final String ICON_DATAMANAGEMENT_16 = "obj16/datamanagementactivity.gif";
	public static final String ICON_DATAMANAGEMENT_32 = "obj20/datamanagementactivity.png";
	public static final String ICON_CALLPROCEDURE_16 = "obj16/callprocedureactivity.gif"; 
	public static final String ICON_CALLPROCEDURE_32 = "obj20/callprocedureactivity.png"; 
	public static final String ICON_CREATE_16 = "obj16/createactivity.gif"; 
	public static final String ICON_CREATE_32 = "obj20/createactivity.png";
	public static final String ICON_DELETE_16 = "obj16/deleteactivity.gif"; 
	public static final String ICON_DELETE_32 = "obj20/deleteactivity.png";
	public static final String ICON_INSERT_16 = "obj16/insertactivity.gif";
	public static final String ICON_INSERT_32 = "obj20/insertactivity.png";
	public static final String ICON_RETRIEVESET_16 = "obj16/retrievesetactivity.gif";
	public static final String ICON_RETRIEVESET_32 = "obj20/retrievesetactivity.png";
	public static final String ICON_SELECT_16 = "obj16/selectactivity.gif";
	public static final String ICON_SELECT_32 = "obj20/selectactivity.png";
	public static final String ICON_UPDATE_16 = "obj16/updateactivity.gif"; 
	public static final String ICON_UPDATE_32 = "obj20/updateactivity.png";
	public static final String ICON_WRITEBACK_16 = "obj16/writebackactivity.gif"; 
	public static final String ICON_WRITEBACK_32 = "obj20/writebackactivity.png";
	
	// Property-View constants for the building of statements
	private static final String[] dataSourceTypes = new String[]{"file system", "database", "sensor net"};
	
	private static final String[] fileSystemStatements = new String[]{"GET", "PUT", "RM", "MKDIR", "MKFILE"}; 
	private static final String[] dataSourceStatements = new String[]{"SELECT", "INSERT", "UPDATE", "DELETE", "CREATE", "CALL"};
	private static final String[] sensorNetStatements = new String[]{"SELECT", "CREATE BUFFER", "DROP ALL"};
	
	public static String[] getStatements(String dataSourceType){
		String[] statements = new String[]{""};
		if (dataSourceType.contentEquals("file system")) {
			statements = fileSystemStatements;
		}else {
			if (dataSourceType.contentEquals("data source")) {
				statements = dataSourceStatements;
			}else {
				if (dataSourceType.contentEquals("sensor net")) {
					statements = sensorNetStatements;
				}
			}
		}
		return statements;
	}
	
	public static String[] getStatements(int index){
		String[] statements = new String[]{""};
		if (index==0) {
			statements = fileSystemStatements;
		}else {
			if (index==1) {
				statements = dataSourceStatements;
			}else {
				if (index==2) {
					statements = sensorNetStatements;
				}
			}
		}
		return statements;
	}
	
	public static String[] getDataSourceTypes(){
		return dataSourceTypes;
	}
}

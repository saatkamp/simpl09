package org.eclipse.bpel.simpl.ui.properties;


public class Constants {

	/**
	 * This variable holds all types of data sources, which can be choosed during the modeling process.
	 */
	private static final String[] dataSourceTypes = new String[]{"file system", "database", "sensor net"};
	
	/**
	 * This variable holds all kinds of file systems, which can be choosed during the modeling process.
	 */
	private static final String[] fileSystemKinds = new String[]{"local", "external"};
	
	/**
	 * This variable holds all kinds of data bases, which can be choosed during the modeling process.
	 */
	private static final String[] dataBaseKinds = new String[]{"DB2", "MySQL"};
	
	/**
	 * This variable holds all kinds of sensor nets, which can be choosed during the modeling process.
	 */
	private static final String[] sensorNetKinds = new String[]{"TinyDB", "???"};
	
	/**
	 * This variable holds all statements of a file systems, which can be used to work with.
	 */
	private static final String[] fileSystemStatements = new String[]{"GET", "PUT", "RM", "MKDIR", "MKFILE"}; 
	
	/**
	 * This variable holds all statements of a data source, which can be used to work with.
	 */
	private static final String[] dataSourceStatements = new String[]{"SELECT", "INSERT", "UPDATE", "DELETE", "CREATE", "CALL"};
	
	/**
	 * This variable holds all statements of a sensor net, which can be used to work with.
	 */
	private static final String[] sensorNetStatements = new String[]{"SELECT", "CREATE BUFFER", "DROP ALL"};
	
	/**
	 * Returns a list of statements of a given type of data source.
	 * 
	 * @param dataSourceType The type of a data source (file system, data base or sensor net)
	 * @return A list of statements which can be used for the specific data source.
	 */
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
	
	/**
	 * Returns a list of statements of a given type of data source.
	 * 
	 * @param index of the data source type (file system=0, data base=1 and sensor net=2)
	 * @return A list of statements which can be used for the specific data source.
	 */
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
	
	/**
	 * Returns the list of the data source types.
	 * 
	 * @return A list with all supported data source types.
	 */
	public static String[] getDataSourceTypes(){
		return dataSourceTypes;
	}
	
	/**
	 * Returns a list with all supported kinds of file systems.
	 * 
	 * @return A list of the supported kinds of file systems.
	 */
	public static String[] getFileSystemKinds(){
		return fileSystemKinds;
	}
	
	/**
	 * Returns a list with all supported kinds of data bases.
	 * 
	 * @return A list of the supported kinds of data bases.
	 */
	public static String[] getDatabasekinds() {
		return dataBaseKinds;
	}

	/**
	 * Returns a list with all supported kinds of sensor nets.
	 * 
	 * @return A list of the supported kinds of sensor nets.
	 */
	public static String[] getSensornetkinds() {
		return sensorNetKinds;
	}	
}

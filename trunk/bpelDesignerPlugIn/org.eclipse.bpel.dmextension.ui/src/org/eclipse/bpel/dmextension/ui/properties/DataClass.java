package org.eclipse.bpel.dmextension.ui.properties;

import java.util.Observable;

//Nur zu Vorführungszwecken
public class DataClass extends Observable{
	
	private static DataClass instance = null;
	private String dsAdress = "";
	private String dsType = "";
	private String dsKind = "";
	private String statement = "";
	private String innerSelect = "";
	private String multiColumn = "";
	private String macroSource = "";
	private String microSource = "";
	
	public static DataClass getInstance(){
		if (instance == null){
			instance = new DataClass();
		}
		return instance;
	}




	public String getDsAdress() {
		return dsAdress;
	}




	public void setDsAdress(String dsAdress) {
		this.dsAdress = dsAdress;
		setChanged();
		notifyObservers("dsAdress");
	}




	public String getDsType() {
		return dsType;
	}




	public void setDsType(String dsType) {
		this.dsType = dsType;
		setChanged();
		notifyObservers("dsType");
	}




	public String getDsKind() {
		return dsKind;
	}




	public void setDsKind(String dsKind) {
		this.dsKind = dsKind;
		setChanged();
		notifyObservers("dsKind");
	}




	public String getStatement() {
		return statement;
	}




	public void setStatement(String statement) {
		this.statement = statement;
		setChanged();
		notifyObservers("statement");
	}




	public String getInnerSelect() {
		return innerSelect;
	}




	public void setInnerSelect(String innerSelect) {
		this.innerSelect = innerSelect;
	}




	public String getMultiColumn() {
		return multiColumn;
	}




	public void setMultiColumn(String multiColumn) {
		this.multiColumn = multiColumn;
	}




	public String getMacroSource() {
		return macroSource;
	}




	public void setMacroSource(String macroSource) {
		this.macroSource = macroSource;
	}




	public String getMicroSource() {
		return microSource;
	}




	public void setMicroSource(String microSource) {
		this.microSource = microSource;
	}
	
	/**
	 * Processes the statement, which is formed out of the selected items in the combo boxes.
	 * @return The correct formed user selected statement.
	 */
	public void processStatement(){
		String statement = "";
		if (multiColumn.isEmpty() && !innerSelect.isEmpty()){
			statement = "SELECT" + " " + microSource + " " + "FROM" + " " + " " + innerSelect;
		}else {
			if (!multiColumn.isEmpty() && innerSelect.isEmpty()){
				statement = "SELECT" + " " + multiColumn + " " + "FROM" + " " + " " + macroSource;
			}else {
				statement = "SELECT" + " " + microSource + " " + "FROM" + " " + " " + macroSource;
			}
		}
		setStatement(statement);
	}
}

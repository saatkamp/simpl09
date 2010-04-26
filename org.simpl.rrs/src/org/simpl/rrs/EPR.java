package org.simpl.rrs;

public class EPR {

	String adapterType = null;
	String statement = null;
	String adress = null;
	String referenceName = null;

	public String getAdress() {
		return adress;
	}

	public void setAdress(String adress) {
		this.adress = adress;
	}

	public String getadapterType() {
		return adapterType;
	}

	public void setadapterType(String adapterType) {
		this.adapterType = adapterType;
	}

	public String getStatement() {
		return statement;
	}

	public void setStatement(String statement) {
		this.statement = statement;
	}

	public String getReferenceName() {
		return referenceName;
	}
	
	public void setReferenceName(String referenceName) {
		this.referenceName = referenceName;
	}
}

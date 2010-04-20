package org.simpl.rrs;

public class EPR {

	String adapterURI = null;
	String statement = null;
	String adress = null;
	String referenceName = null;

	public String getAdress() {
		return adress;
	}

	public void setAdress(String adress) {
		this.adress = adress;
	}

	public String getadapterURI() {
		return adapterURI;
	}

	public void setadapterURI(String adapterURI) {
		this.adapterURI = adapterURI;
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

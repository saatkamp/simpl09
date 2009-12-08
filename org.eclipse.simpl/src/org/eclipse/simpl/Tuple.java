package org.eclipse.simpl;

public class Tuple {
	
	private String name = null;
	private int value = -1;
	
	public Tuple() {
		super();
	}

	public Tuple(String name) {
		super();
		this.name = name;
	}

	public Tuple(int value) {
		super();
		this.value = value;
	}

	public Tuple(String name, int value) {
		super();
		this.name = name;
		this.value = value;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	
	public boolean hasValue(){
		if (this.value!=-1){
			return true;
		}else {
			return false;
		}
	}
}

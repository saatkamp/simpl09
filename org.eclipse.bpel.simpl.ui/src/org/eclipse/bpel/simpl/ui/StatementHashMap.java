package org.eclipse.bpel.simpl.ui;

import java.util.LinkedHashMap;

@SuppressWarnings("serial")
public class StatementHashMap extends LinkedHashMap<String, String> {

	@Override
	public String toString() {
		StringBuilder statem = new StringBuilder();
		if (!this.isEmpty()) {
			for (String part : this.keySet()) {
				statem.append(part);
				statem.append(" ");
				statem.append(this.get(part));
				statem.append(" ");
			}
			statem.deleteCharAt(statem.length() - 1);
		}
		return statem.toString();
	}
}

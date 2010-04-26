package org.simpl.rrs.retrieval;

import java.io.File;

public interface RetrievalService {

	public Object get(String uri, String adapterType, String referenceName, String statement);
	
}

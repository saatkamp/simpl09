package org.simpl.rrs.management;

import java.io.File;
import java.sql.SQLException;
import java.util.LinkedHashMap;

import org.simpl.rrs.retrieval.RetrievalService;

public interface ManagementService {

	public String Delete(String table, LinkedHashMap<String, String> EPR, String EPRName);
	
	public boolean Update(String table, LinkedHashMap<String, String> EPR, String EPRName) throws SQLException;
	
	public File Insert(String table, LinkedHashMap<String, String> EPR, String EPRName) throws SQLException;
	
	
	
}

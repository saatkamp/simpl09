package org.simpl.rrs.management;

import java.io.File;

import org.simpl.rrs.retrieval.RetrievalService;

public interface ManagementService {

	public String Delete(File EPR);
	
	public String Update(File EPR);
	
	public File Insert(File EPR);
	
	
	
}

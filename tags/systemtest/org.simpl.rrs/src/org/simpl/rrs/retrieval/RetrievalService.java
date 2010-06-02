package org.simpl.rrs.retrieval;

import org.simpl.rrs.webservices.EPR;
import org.simpl.rrs.webservices.RDBSet;

public interface RetrievalService {

	public RDBSet get(EPR epr);
	
}

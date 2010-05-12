package org.simpl.rrs.retrieval;

import java.sql.ResultSet;

import org.simpl.rrs.webservices.EPR;

public interface RetrievalService {

	public ResultSet get(EPR epr);
	
}

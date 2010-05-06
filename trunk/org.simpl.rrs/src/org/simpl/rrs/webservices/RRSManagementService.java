
/**
 * RRSManagementService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.5  Built on : Apr 30, 2009 (06:07:24 EDT)
 */

package org.simpl.rrs.webservices;

/*
 *  RRSManagementService java interface
 */

public interface RRSManagementService {

	/**
	 * Auto generated method signature
	 * 
	 * @param update100
	 */

	public org.simpl.rrs.webservices.UpdateResponse update(

	org.simpl.rrs.webservices.Update update100) throws java.rmi.RemoteException;

	/**
	 * Auto generated method signature for Asynchronous Invocations
	 * 
	 * @param update100
	 */
	public void startupdate(

	org.simpl.rrs.webservices.Update update100,

	final org.simpl.rrs.webservices.RRSManagementServiceCallbackHandler callback)

	throws java.rmi.RemoteException;

	/**
	 * Auto generated method signature
	 * 
	 * @param delete102
	 */

	public org.simpl.rrs.webservices.DeleteResponse delete(

	org.simpl.rrs.webservices.Delete delete102) throws java.rmi.RemoteException;

	/**
	 * Auto generated method signature for Asynchronous Invocations
	 * 
	 * @param delete102
	 */
	public void startdelete(

	org.simpl.rrs.webservices.Delete delete102,

	final org.simpl.rrs.webservices.RRSManagementServiceCallbackHandler callback)

	throws java.rmi.RemoteException;

	/**
	 * Auto generated method signature
	 * 
	 * @param insert104
	 */

	public org.simpl.rrs.webservices.InsertResponse insert(

	org.simpl.rrs.webservices.Insert insert104) throws java.rmi.RemoteException;

	/**
	 * Auto generated method signature for Asynchronous Invocations
	 * 
	 * @param insert104
	 */
	public void startinsert(

	org.simpl.rrs.webservices.Insert insert104,

	final org.simpl.rrs.webservices.RRSManagementServiceCallbackHandler callback)

	throws java.rmi.RemoteException;

	//
}

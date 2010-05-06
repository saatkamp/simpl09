

/**
 * RRSRetrievalService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.5  Built on : Apr 30, 2009 (06:07:24 EDT)
 */

    package org.simpl.rrs.webservices;

    /*
     *  RRSRetrievalService java interface
     */

    public interface RRSRetrievalService {
          

        /**
          * Auto generated method signature
          * 
                    * @param get12
                
         */

         
                     public org.simpl.rrs.webservices.GetResponse get(

                        org.simpl.rrs.webservices.Get get12)
                        throws java.rmi.RemoteException
             ;

        
         /**
            * Auto generated method signature for Asynchronous Invocations
            * 
                * @param get12
            
          */
        public void startget(

            org.simpl.rrs.webservices.Get get12,

            final org.simpl.rrs.webservices.RRSRetrievalServiceCallbackHandler callback)

            throws java.rmi.RemoteException;

     

        
       //
       }
    
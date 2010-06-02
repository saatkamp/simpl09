

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
                    * @param update36
                
         */

         
                     public org.simpl.rrs.webservices.UpdateResponse update(

                        org.simpl.rrs.webservices.Update update36)
                        throws java.rmi.RemoteException
             ;

        
         /**
            * Auto generated method signature for Asynchronous Invocations
            * 
                * @param update36
            
          */
        public void startupdate(

            org.simpl.rrs.webservices.Update update36,

            final org.simpl.rrs.webservices.RRSManagementServiceCallbackHandler callback)

            throws java.rmi.RemoteException;

     

        /**
          * Auto generated method signature
          * 
                    * @param delete38
                
         */

         
                     public org.simpl.rrs.webservices.DeleteResponse delete(

                        org.simpl.rrs.webservices.Delete delete38)
                        throws java.rmi.RemoteException
             ;

        
         /**
            * Auto generated method signature for Asynchronous Invocations
            * 
                * @param delete38
            
          */
        public void startdelete(

            org.simpl.rrs.webservices.Delete delete38,

            final org.simpl.rrs.webservices.RRSManagementServiceCallbackHandler callback)

            throws java.rmi.RemoteException;

     

        /**
          * Auto generated method signature
          * 
                    * @param insert40
                
         */

         
                     public org.simpl.rrs.webservices.InsertResponse insert(

                        org.simpl.rrs.webservices.Insert insert40)
                        throws java.rmi.RemoteException
             ;

        
         /**
            * Auto generated method signature for Asynchronous Invocations
            * 
                * @param insert40
            
          */
        public void startinsert(

            org.simpl.rrs.webservices.Insert insert40,

            final org.simpl.rrs.webservices.RRSManagementServiceCallbackHandler callback)

            throws java.rmi.RemoteException;

     

        
       //
       }
    
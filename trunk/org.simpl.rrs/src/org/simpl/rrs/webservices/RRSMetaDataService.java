

/**
 * RRSMetaDataService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.5  Built on : Apr 30, 2009 (06:07:24 EDT)
 */

    package org.simpl.rrs.webservices;

    /*
     *  RRSMetaDataService java interface
     */

    public interface RRSMetaDataService {
          

        /**
          * Auto generated method signature
          * 
                    * @param getAvailableAdapters76
                
         */

         
                     public org.simpl.rrs.webservices.GetAvailableAdaptersResponse getAvailableAdapters(

                        org.simpl.rrs.webservices.GetAvailableAdapters getAvailableAdapters76)
                        throws java.rmi.RemoteException
             ;

        
         /**
            * Auto generated method signature for Asynchronous Invocations
            * 
                * @param getAvailableAdapters76
            
          */
        public void startgetAvailableAdapters(

            org.simpl.rrs.webservices.GetAvailableAdapters getAvailableAdapters76,

            final org.simpl.rrs.webservices.RRSMetaDataServiceCallbackHandler callback)

            throws java.rmi.RemoteException;

     

        /**
          * Auto generated method signature
          * 
                    * @param getAllEPR78
                
         */

         
                     public org.simpl.rrs.webservices.GetAllEPRResponse getAllEPR(

                        org.simpl.rrs.webservices.GetAllEPR getAllEPR78)
                        throws java.rmi.RemoteException
             ;

        
         /**
            * Auto generated method signature for Asynchronous Invocations
            * 
                * @param getAllEPR78
            
          */
        public void startgetAllEPR(

            org.simpl.rrs.webservices.GetAllEPR getAllEPR78,

            final org.simpl.rrs.webservices.RRSMetaDataServiceCallbackHandler callback)

            throws java.rmi.RemoteException;

     

        /**
          * Auto generated method signature
          * 
                    * @param getEPR80
                
         */

         
                     public org.simpl.rrs.webservices.GetEPRResponse getEPR(

                        org.simpl.rrs.webservices.GetEPR getEPR80)
                        throws java.rmi.RemoteException
             ;

        
         /**
            * Auto generated method signature for Asynchronous Invocations
            * 
                * @param getEPR80
            
          */
        public void startgetEPR(

            org.simpl.rrs.webservices.GetEPR getEPR80,

            final org.simpl.rrs.webservices.RRSMetaDataServiceCallbackHandler callback)

            throws java.rmi.RemoteException;

     

        
       //
       }
    


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
                    * @param getAvailableAdapters36
                
         */

         
                     public org.simpl.rrs.webservices.GetAvailableAdaptersResponse getAvailableAdapters(

                        org.simpl.rrs.webservices.GetAvailableAdapters getAvailableAdapters36)
                        throws java.rmi.RemoteException
             ;

        
         /**
            * Auto generated method signature for Asynchronous Invocations
            * 
                * @param getAvailableAdapters36
            
          */
        public void startgetAvailableAdapters(

            org.simpl.rrs.webservices.GetAvailableAdapters getAvailableAdapters36,

            final org.simpl.rrs.webservices.RRSMetaDataServiceCallbackHandler callback)

            throws java.rmi.RemoteException;

     

        /**
          * Auto generated method signature
          * 
                    * @param getAllEPR38
                
         */

         
                     public org.simpl.rrs.webservices.GetAllEPRResponse getAllEPR(

                        org.simpl.rrs.webservices.GetAllEPR getAllEPR38)
                        throws java.rmi.RemoteException
             ;

        
         /**
            * Auto generated method signature for Asynchronous Invocations
            * 
                * @param getAllEPR38
            
          */
        public void startgetAllEPR(

            org.simpl.rrs.webservices.GetAllEPR getAllEPR38,

            final org.simpl.rrs.webservices.RRSMetaDataServiceCallbackHandler callback)

            throws java.rmi.RemoteException;

     

        /**
          * Auto generated method signature
          * 
                    * @param getEPR40
                
         */

         
                     public org.simpl.rrs.webservices.GetEPRResponse getEPR(

                        org.simpl.rrs.webservices.GetEPR getEPR40)
                        throws java.rmi.RemoteException
             ;

        
         /**
            * Auto generated method signature for Asynchronous Invocations
            * 
                * @param getEPR40
            
          */
        public void startgetEPR(

            org.simpl.rrs.webservices.GetEPR getEPR40,

            final org.simpl.rrs.webservices.RRSMetaDataServiceCallbackHandler callback)

            throws java.rmi.RemoteException;

     

        
       //
       }
    
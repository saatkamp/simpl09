

/**
 * Transformation.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.5  Built on : Apr 30, 2009 (06:07:24 EDT)
 */

    package org.simpl.rrs.transformation.webservices;

    /*
     *  Transformation java interface
     */

    public interface Transformation {
          

        /**
          * Auto generated method signature
          * 
                    * @param transform12
                
         */

         
                     public org.simpl.rrs.transformation.webservices.TransformResponse transform(

                        org.simpl.rrs.transformation.webservices.Transform transform12)
                        throws java.rmi.RemoteException
             ;

        
         /**
            * Auto generated method signature for Asynchronous Invocations
            * 
                * @param transform12
            
          */
        public void starttransform(

            org.simpl.rrs.transformation.webservices.Transform transform12,

            final org.simpl.rrs.transformation.webservices.TransformationCallbackHandler callback)

            throws java.rmi.RemoteException;

     

        
       //
       }
    
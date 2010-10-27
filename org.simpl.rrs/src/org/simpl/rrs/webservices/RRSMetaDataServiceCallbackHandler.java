
/**
 * RRSMetaDataServiceCallbackHandler.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.5  Built on : Apr 30, 2009 (06:07:24 EDT)
 */

    package org.simpl.rrs.webservices;

    /**
     *  RRSMetaDataServiceCallbackHandler Callback class, Users can extend this class and implement
     *  their own receiveResult and receiveError methods.
     */
    public abstract class RRSMetaDataServiceCallbackHandler{



    protected Object clientData;

    /**
    * User can pass in any object that needs to be accessed once the NonBlocking
    * Web service call is finished and appropriate method of this CallBack is called.
    * @param clientData Object mechanism by which the user can pass in user data
    * that will be avilable at the time this callback is called.
    */
    public RRSMetaDataServiceCallbackHandler(Object clientData){
        this.clientData = clientData;
    }

    /**
    * Please use this constructor if you don't want to set any clientData
    */
    public RRSMetaDataServiceCallbackHandler(){
        this.clientData = null;
    }

    /**
     * Get the client data
     */

     public Object getClientData() {
        return clientData;
     }

        
           /**
            * auto generated Axis2 call back method for getAvailableAdapters method
            * override this method for handling normal response from getAvailableAdapters operation
            */
           public void receiveResultgetAvailableAdapters(
                    org.simpl.rrs.webservices.GetAvailableAdaptersResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getAvailableAdapters operation
           */
            public void receiveErrorgetAvailableAdapters(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for getAllEPR method
            * override this method for handling normal response from getAllEPR operation
            */
           public void receiveResultgetAllEPR(
                    org.simpl.rrs.webservices.GetAllEPRResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getAllEPR operation
           */
            public void receiveErrorgetAllEPR(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for getEPR method
            * override this method for handling normal response from getEPR operation
            */
           public void receiveResultgetEPR(
                    org.simpl.rrs.webservices.GetEPRResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getEPR operation
           */
            public void receiveErrorgetEPR(java.lang.Exception e) {
            }
                


    }
    
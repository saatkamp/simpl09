
/**
 * RRSMetaDataServiceMessageReceiverInOut.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.5  Built on : Apr 30, 2009 (06:07:24 EDT)
 */
        package org.simpl.rrs.webservices;

        /**
        *  RRSMetaDataServiceMessageReceiverInOut message receiver
        */

        public class RRSMetaDataServiceMessageReceiverInOut extends org.apache.axis2.receivers.AbstractInOutMessageReceiver{


        public void invokeBusinessLogic(org.apache.axis2.context.MessageContext msgContext, org.apache.axis2.context.MessageContext newMsgContext)
        throws org.apache.axis2.AxisFault{

        try {

        // get the implementation class for the Web Service
        Object obj = getTheImplementationObject(msgContext);

        RRSMetaDataServiceSkeleton skel = (RRSMetaDataServiceSkeleton)obj;
        //Out Envelop
        org.apache.axiom.soap.SOAPEnvelope envelope = null;
        //Find the axisOperation that has been set by the Dispatch phase.
        org.apache.axis2.description.AxisOperation op = msgContext.getOperationContext().getAxisOperation();
        if (op == null) {
        throw new org.apache.axis2.AxisFault("Operation is not located, if this is doclit style the SOAP-ACTION should specified via the SOAP Action to use the RawXMLProvider");
        }

        java.lang.String methodName;
        if((op.getName() != null) && ((methodName = org.apache.axis2.util.JavaUtils.xmlNameToJavaIdentifier(op.getName().getLocalPart())) != null)){

        

            if("getAvailableAdapters".equals(methodName)){
                
                org.simpl.rrs.webservices.GetAvailableAdaptersResponse getAvailableAdaptersResponse1 = null;
	                        org.simpl.rrs.webservices.GetAvailableAdapters wrappedParam =
                                                             (org.simpl.rrs.webservices.GetAvailableAdapters)fromOM(
                                    msgContext.getEnvelope().getBody().getFirstElement(),
                                    org.simpl.rrs.webservices.GetAvailableAdapters.class,
                                    getEnvelopeNamespaces(msgContext.getEnvelope()));
                                                
                                               getAvailableAdaptersResponse1 =
                                                   
                                                   
                                                         skel.getAvailableAdapters(wrappedParam)
                                                    ;
                                            
                                        envelope = toEnvelope(getSOAPFactory(msgContext), getAvailableAdaptersResponse1, false);
                                    } else 

            if("getAllEPR".equals(methodName)){
                
                org.simpl.rrs.webservices.GetAllEPRResponse getAllEPRResponse3 = null;
	                        org.simpl.rrs.webservices.GetAllEPR wrappedParam =
                                                             (org.simpl.rrs.webservices.GetAllEPR)fromOM(
                                    msgContext.getEnvelope().getBody().getFirstElement(),
                                    org.simpl.rrs.webservices.GetAllEPR.class,
                                    getEnvelopeNamespaces(msgContext.getEnvelope()));
                                                
                                               getAllEPRResponse3 =
                                                   
                                                   
                                                         skel.getAllEPR(wrappedParam)
                                                    ;
                                            
                                        envelope = toEnvelope(getSOAPFactory(msgContext), getAllEPRResponse3, false);
                                    } else 

            if("getEPR".equals(methodName)){
                
                org.simpl.rrs.webservices.GetEPRResponse getEPRResponse5 = null;
	                        org.simpl.rrs.webservices.GetEPR wrappedParam =
                                                             (org.simpl.rrs.webservices.GetEPR)fromOM(
                                    msgContext.getEnvelope().getBody().getFirstElement(),
                                    org.simpl.rrs.webservices.GetEPR.class,
                                    getEnvelopeNamespaces(msgContext.getEnvelope()));
                                                
                                               getEPRResponse5 =
                                                   
                                                   
                                                         skel.getEPR(wrappedParam)
                                                    ;
                                            
                                        envelope = toEnvelope(getSOAPFactory(msgContext), getEPRResponse5, false);
                                    
            } else {
              throw new java.lang.RuntimeException("method not found");
            }
        

        newMsgContext.setEnvelope(envelope);
        }
        }
        catch (java.lang.Exception e) {
        throw org.apache.axis2.AxisFault.makeFault(e);
        }
        }
        
        //
            private  org.apache.axiom.om.OMElement  toOM(org.simpl.rrs.webservices.GetAvailableAdapters param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault {

            
                        try{
                             return param.getOMElement(org.simpl.rrs.webservices.GetAvailableAdapters.MY_QNAME,
                                          org.apache.axiom.om.OMAbstractFactory.getOMFactory());
                        } catch(org.apache.axis2.databinding.ADBException e){
                            throw org.apache.axis2.AxisFault.makeFault(e);
                        }
                    

            }
        
            private  org.apache.axiom.om.OMElement  toOM(org.simpl.rrs.webservices.GetAvailableAdaptersResponse param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault {

            
                        try{
                             return param.getOMElement(org.simpl.rrs.webservices.GetAvailableAdaptersResponse.MY_QNAME,
                                          org.apache.axiom.om.OMAbstractFactory.getOMFactory());
                        } catch(org.apache.axis2.databinding.ADBException e){
                            throw org.apache.axis2.AxisFault.makeFault(e);
                        }
                    

            }
        
            private  org.apache.axiom.om.OMElement  toOM(org.simpl.rrs.webservices.GetAllEPR param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault {

            
                        try{
                             return param.getOMElement(org.simpl.rrs.webservices.GetAllEPR.MY_QNAME,
                                          org.apache.axiom.om.OMAbstractFactory.getOMFactory());
                        } catch(org.apache.axis2.databinding.ADBException e){
                            throw org.apache.axis2.AxisFault.makeFault(e);
                        }
                    

            }
        
            private  org.apache.axiom.om.OMElement  toOM(org.simpl.rrs.webservices.GetAllEPRResponse param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault {

            
                        try{
                             return param.getOMElement(org.simpl.rrs.webservices.GetAllEPRResponse.MY_QNAME,
                                          org.apache.axiom.om.OMAbstractFactory.getOMFactory());
                        } catch(org.apache.axis2.databinding.ADBException e){
                            throw org.apache.axis2.AxisFault.makeFault(e);
                        }
                    

            }
        
            private  org.apache.axiom.om.OMElement  toOM(org.simpl.rrs.webservices.GetEPR param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault {

            
                        try{
                             return param.getOMElement(org.simpl.rrs.webservices.GetEPR.MY_QNAME,
                                          org.apache.axiom.om.OMAbstractFactory.getOMFactory());
                        } catch(org.apache.axis2.databinding.ADBException e){
                            throw org.apache.axis2.AxisFault.makeFault(e);
                        }
                    

            }
        
            private  org.apache.axiom.om.OMElement  toOM(org.simpl.rrs.webservices.GetEPRResponse param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault {

            
                        try{
                             return param.getOMElement(org.simpl.rrs.webservices.GetEPRResponse.MY_QNAME,
                                          org.apache.axiom.om.OMAbstractFactory.getOMFactory());
                        } catch(org.apache.axis2.databinding.ADBException e){
                            throw org.apache.axis2.AxisFault.makeFault(e);
                        }
                    

            }
        
                    private  org.apache.axiom.soap.SOAPEnvelope toEnvelope(org.apache.axiom.soap.SOAPFactory factory, org.simpl.rrs.webservices.GetAvailableAdaptersResponse param, boolean optimizeContent)
                        throws org.apache.axis2.AxisFault{
                      try{
                          org.apache.axiom.soap.SOAPEnvelope emptyEnvelope = factory.getDefaultEnvelope();
                           
                                    emptyEnvelope.getBody().addChild(param.getOMElement(org.simpl.rrs.webservices.GetAvailableAdaptersResponse.MY_QNAME,factory));
                                

                         return emptyEnvelope;
                    } catch(org.apache.axis2.databinding.ADBException e){
                        throw org.apache.axis2.AxisFault.makeFault(e);
                    }
                    }
                    
                         private org.simpl.rrs.webservices.GetAvailableAdaptersResponse wrapgetAvailableAdapters(){
                                org.simpl.rrs.webservices.GetAvailableAdaptersResponse wrappedElement = new org.simpl.rrs.webservices.GetAvailableAdaptersResponse();
                                return wrappedElement;
                         }
                    
                    private  org.apache.axiom.soap.SOAPEnvelope toEnvelope(org.apache.axiom.soap.SOAPFactory factory, org.simpl.rrs.webservices.GetAllEPRResponse param, boolean optimizeContent)
                        throws org.apache.axis2.AxisFault{
                      try{
                          org.apache.axiom.soap.SOAPEnvelope emptyEnvelope = factory.getDefaultEnvelope();
                           
                                    emptyEnvelope.getBody().addChild(param.getOMElement(org.simpl.rrs.webservices.GetAllEPRResponse.MY_QNAME,factory));
                                

                         return emptyEnvelope;
                    } catch(org.apache.axis2.databinding.ADBException e){
                        throw org.apache.axis2.AxisFault.makeFault(e);
                    }
                    }
                    
                         private org.simpl.rrs.webservices.GetAllEPRResponse wrapgetAllEPR(){
                                org.simpl.rrs.webservices.GetAllEPRResponse wrappedElement = new org.simpl.rrs.webservices.GetAllEPRResponse();
                                return wrappedElement;
                         }
                    
                    private  org.apache.axiom.soap.SOAPEnvelope toEnvelope(org.apache.axiom.soap.SOAPFactory factory, org.simpl.rrs.webservices.GetEPRResponse param, boolean optimizeContent)
                        throws org.apache.axis2.AxisFault{
                      try{
                          org.apache.axiom.soap.SOAPEnvelope emptyEnvelope = factory.getDefaultEnvelope();
                           
                                    emptyEnvelope.getBody().addChild(param.getOMElement(org.simpl.rrs.webservices.GetEPRResponse.MY_QNAME,factory));
                                

                         return emptyEnvelope;
                    } catch(org.apache.axis2.databinding.ADBException e){
                        throw org.apache.axis2.AxisFault.makeFault(e);
                    }
                    }
                    
                         private org.simpl.rrs.webservices.GetEPRResponse wrapgetEPR(){
                                org.simpl.rrs.webservices.GetEPRResponse wrappedElement = new org.simpl.rrs.webservices.GetEPRResponse();
                                return wrappedElement;
                         }
                    


        /**
        *  get the default envelope
        */
        private org.apache.axiom.soap.SOAPEnvelope toEnvelope(org.apache.axiom.soap.SOAPFactory factory){
        return factory.getDefaultEnvelope();
        }


        private  java.lang.Object fromOM(
        org.apache.axiom.om.OMElement param,
        java.lang.Class type,
        java.util.Map extraNamespaces) throws org.apache.axis2.AxisFault{

        try {
        
                if (org.simpl.rrs.webservices.GetAvailableAdapters.class.equals(type)){
                
                           return org.simpl.rrs.webservices.GetAvailableAdapters.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                    

                }
           
                if (org.simpl.rrs.webservices.GetAvailableAdaptersResponse.class.equals(type)){
                
                           return org.simpl.rrs.webservices.GetAvailableAdaptersResponse.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                    

                }
           
                if (org.simpl.rrs.webservices.GetAllEPR.class.equals(type)){
                
                           return org.simpl.rrs.webservices.GetAllEPR.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                    

                }
           
                if (org.simpl.rrs.webservices.GetAllEPRResponse.class.equals(type)){
                
                           return org.simpl.rrs.webservices.GetAllEPRResponse.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                    

                }
           
                if (org.simpl.rrs.webservices.GetEPR.class.equals(type)){
                
                           return org.simpl.rrs.webservices.GetEPR.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                    

                }
           
                if (org.simpl.rrs.webservices.GetEPRResponse.class.equals(type)){
                
                           return org.simpl.rrs.webservices.GetEPRResponse.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                    

                }
           
        } catch (java.lang.Exception e) {
        throw org.apache.axis2.AxisFault.makeFault(e);
        }
           return null;
        }



    

        /**
        *  A utility method that copies the namepaces from the SOAPEnvelope
        */
        private java.util.Map getEnvelopeNamespaces(org.apache.axiom.soap.SOAPEnvelope env){
        java.util.Map returnMap = new java.util.HashMap();
        java.util.Iterator namespaceIterator = env.getAllDeclaredNamespaces();
        while (namespaceIterator.hasNext()) {
        org.apache.axiom.om.OMNamespace ns = (org.apache.axiom.om.OMNamespace) namespaceIterator.next();
        returnMap.put(ns.getPrefix(),ns.getNamespaceURI());
        }
        return returnMap;
        }

        private org.apache.axis2.AxisFault createAxisFault(java.lang.Exception e) {
        org.apache.axis2.AxisFault f;
        Throwable cause = e.getCause();
        if (cause != null) {
            f = new org.apache.axis2.AxisFault(e.getMessage(), cause);
        } else {
            f = new org.apache.axis2.AxisFault(e.getMessage());
        }

        return f;
    }

        }//end of class
    

/**
 * RRSManagementServiceMessageReceiverInOut.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.5  Built on : Apr 30, 2009 (06:07:24 EDT)
 */
        package org.simpl.rrs.webservices;

        /**
        *  RRSManagementServiceMessageReceiverInOut message receiver
        */

        public class RRSManagementServiceMessageReceiverInOut extends org.apache.axis2.receivers.AbstractInOutMessageReceiver{


        public void invokeBusinessLogic(org.apache.axis2.context.MessageContext msgContext, org.apache.axis2.context.MessageContext newMsgContext)
        throws org.apache.axis2.AxisFault{

        try {

        // get the implementation class for the Web Service
        Object obj = getTheImplementationObject(msgContext);

        RRSManagementServiceSkeleton skel = (RRSManagementServiceSkeleton)obj;
        //Out Envelop
        org.apache.axiom.soap.SOAPEnvelope envelope = null;
        //Find the axisOperation that has been set by the Dispatch phase.
        org.apache.axis2.description.AxisOperation op = msgContext.getOperationContext().getAxisOperation();
        if (op == null) {
        throw new org.apache.axis2.AxisFault("Operation is not located, if this is doclit style the SOAP-ACTION should specified via the SOAP Action to use the RawXMLProvider");
        }

        java.lang.String methodName;
        if((op.getName() != null) && ((methodName = org.apache.axis2.util.JavaUtils.xmlNameToJavaIdentifier(op.getName().getLocalPart())) != null)){

        

            if("update".equals(methodName)){
                
                org.simpl.rrs.webservices.UpdateResponse updateResponse1 = null;
	                        org.simpl.rrs.webservices.Update wrappedParam =
                                                             (org.simpl.rrs.webservices.Update)fromOM(
                                    msgContext.getEnvelope().getBody().getFirstElement(),
                                    org.simpl.rrs.webservices.Update.class,
                                    getEnvelopeNamespaces(msgContext.getEnvelope()));
                                                
                                               updateResponse1 =
                                                   
                                                   
                                                         skel.update(wrappedParam)
                                                    ;
                                            
                                        envelope = toEnvelope(getSOAPFactory(msgContext), updateResponse1, false);
                                    } else 

            if("delete".equals(methodName)){
                
                org.simpl.rrs.webservices.DeleteResponse deleteResponse3 = null;
	                        org.simpl.rrs.webservices.Delete wrappedParam =
                                                             (org.simpl.rrs.webservices.Delete)fromOM(
                                    msgContext.getEnvelope().getBody().getFirstElement(),
                                    org.simpl.rrs.webservices.Delete.class,
                                    getEnvelopeNamespaces(msgContext.getEnvelope()));
                                                
                                               deleteResponse3 =
                                                   
                                                   
                                                         skel.delete(wrappedParam)
                                                    ;
                                            
                                        envelope = toEnvelope(getSOAPFactory(msgContext), deleteResponse3, false);
                                    } else 

            if("insert".equals(methodName)){
                
                org.simpl.rrs.webservices.InsertResponse insertResponse5 = null;
	                        org.simpl.rrs.webservices.Insert wrappedParam =
                                                             (org.simpl.rrs.webservices.Insert)fromOM(
                                    msgContext.getEnvelope().getBody().getFirstElement(),
                                    org.simpl.rrs.webservices.Insert.class,
                                    getEnvelopeNamespaces(msgContext.getEnvelope()));
                                                
                                               insertResponse5 =
                                                   
                                                   
                                                         skel.insert(wrappedParam)
                                                    ;
                                            
                                        envelope = toEnvelope(getSOAPFactory(msgContext), insertResponse5, false);
                                    
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
            private  org.apache.axiom.om.OMElement  toOM(org.simpl.rrs.webservices.Update param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault {

            
                        try{
                             return param.getOMElement(org.simpl.rrs.webservices.Update.MY_QNAME,
                                          org.apache.axiom.om.OMAbstractFactory.getOMFactory());
                        } catch(org.apache.axis2.databinding.ADBException e){
                            throw org.apache.axis2.AxisFault.makeFault(e);
                        }
                    

            }
        
            private  org.apache.axiom.om.OMElement  toOM(org.simpl.rrs.webservices.UpdateResponse param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault {

            
                        try{
                             return param.getOMElement(org.simpl.rrs.webservices.UpdateResponse.MY_QNAME,
                                          org.apache.axiom.om.OMAbstractFactory.getOMFactory());
                        } catch(org.apache.axis2.databinding.ADBException e){
                            throw org.apache.axis2.AxisFault.makeFault(e);
                        }
                    

            }
        
            private  org.apache.axiom.om.OMElement  toOM(org.simpl.rrs.webservices.Delete param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault {

            
                        try{
                             return param.getOMElement(org.simpl.rrs.webservices.Delete.MY_QNAME,
                                          org.apache.axiom.om.OMAbstractFactory.getOMFactory());
                        } catch(org.apache.axis2.databinding.ADBException e){
                            throw org.apache.axis2.AxisFault.makeFault(e);
                        }
                    

            }
        
            private  org.apache.axiom.om.OMElement  toOM(org.simpl.rrs.webservices.DeleteResponse param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault {

            
                        try{
                             return param.getOMElement(org.simpl.rrs.webservices.DeleteResponse.MY_QNAME,
                                          org.apache.axiom.om.OMAbstractFactory.getOMFactory());
                        } catch(org.apache.axis2.databinding.ADBException e){
                            throw org.apache.axis2.AxisFault.makeFault(e);
                        }
                    

            }
        
            private  org.apache.axiom.om.OMElement  toOM(org.simpl.rrs.webservices.Insert param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault {

            
                        try{
                             return param.getOMElement(org.simpl.rrs.webservices.Insert.MY_QNAME,
                                          org.apache.axiom.om.OMAbstractFactory.getOMFactory());
                        } catch(org.apache.axis2.databinding.ADBException e){
                            throw org.apache.axis2.AxisFault.makeFault(e);
                        }
                    

            }
        
            private  org.apache.axiom.om.OMElement  toOM(org.simpl.rrs.webservices.InsertResponse param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault {

            
                        try{
                             return param.getOMElement(org.simpl.rrs.webservices.InsertResponse.MY_QNAME,
                                          org.apache.axiom.om.OMAbstractFactory.getOMFactory());
                        } catch(org.apache.axis2.databinding.ADBException e){
                            throw org.apache.axis2.AxisFault.makeFault(e);
                        }
                    

            }
        
                    private  org.apache.axiom.soap.SOAPEnvelope toEnvelope(org.apache.axiom.soap.SOAPFactory factory, org.simpl.rrs.webservices.UpdateResponse param, boolean optimizeContent)
                        throws org.apache.axis2.AxisFault{
                      try{
                          org.apache.axiom.soap.SOAPEnvelope emptyEnvelope = factory.getDefaultEnvelope();
                           
                                    emptyEnvelope.getBody().addChild(param.getOMElement(org.simpl.rrs.webservices.UpdateResponse.MY_QNAME,factory));
                                

                         return emptyEnvelope;
                    } catch(org.apache.axis2.databinding.ADBException e){
                        throw org.apache.axis2.AxisFault.makeFault(e);
                    }
                    }
                    
                         private org.simpl.rrs.webservices.UpdateResponse wrapupdate(){
                                org.simpl.rrs.webservices.UpdateResponse wrappedElement = new org.simpl.rrs.webservices.UpdateResponse();
                                return wrappedElement;
                         }
                    
                    private  org.apache.axiom.soap.SOAPEnvelope toEnvelope(org.apache.axiom.soap.SOAPFactory factory, org.simpl.rrs.webservices.DeleteResponse param, boolean optimizeContent)
                        throws org.apache.axis2.AxisFault{
                      try{
                          org.apache.axiom.soap.SOAPEnvelope emptyEnvelope = factory.getDefaultEnvelope();
                           
                                    emptyEnvelope.getBody().addChild(param.getOMElement(org.simpl.rrs.webservices.DeleteResponse.MY_QNAME,factory));
                                

                         return emptyEnvelope;
                    } catch(org.apache.axis2.databinding.ADBException e){
                        throw org.apache.axis2.AxisFault.makeFault(e);
                    }
                    }
                    
                         private org.simpl.rrs.webservices.DeleteResponse wrapdelete(){
                                org.simpl.rrs.webservices.DeleteResponse wrappedElement = new org.simpl.rrs.webservices.DeleteResponse();
                                return wrappedElement;
                         }
                    
                    private  org.apache.axiom.soap.SOAPEnvelope toEnvelope(org.apache.axiom.soap.SOAPFactory factory, org.simpl.rrs.webservices.InsertResponse param, boolean optimizeContent)
                        throws org.apache.axis2.AxisFault{
                      try{
                          org.apache.axiom.soap.SOAPEnvelope emptyEnvelope = factory.getDefaultEnvelope();
                           
                                    emptyEnvelope.getBody().addChild(param.getOMElement(org.simpl.rrs.webservices.InsertResponse.MY_QNAME,factory));
                                

                         return emptyEnvelope;
                    } catch(org.apache.axis2.databinding.ADBException e){
                        throw org.apache.axis2.AxisFault.makeFault(e);
                    }
                    }
                    
                         private org.simpl.rrs.webservices.InsertResponse wrapinsert(){
                                org.simpl.rrs.webservices.InsertResponse wrappedElement = new org.simpl.rrs.webservices.InsertResponse();
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
        
                if (org.simpl.rrs.webservices.Update.class.equals(type)){
                
                           return org.simpl.rrs.webservices.Update.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                    

                }
           
                if (org.simpl.rrs.webservices.UpdateResponse.class.equals(type)){
                
                           return org.simpl.rrs.webservices.UpdateResponse.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                    

                }
           
                if (org.simpl.rrs.webservices.Delete.class.equals(type)){
                
                           return org.simpl.rrs.webservices.Delete.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                    

                }
           
                if (org.simpl.rrs.webservices.DeleteResponse.class.equals(type)){
                
                           return org.simpl.rrs.webservices.DeleteResponse.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                    

                }
           
                if (org.simpl.rrs.webservices.Insert.class.equals(type)){
                
                           return org.simpl.rrs.webservices.Insert.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                    

                }
           
                if (org.simpl.rrs.webservices.InsertResponse.class.equals(type)){
                
                           return org.simpl.rrs.webservices.InsertResponse.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                    

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
    
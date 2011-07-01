
/**
 * WebServiceDataServiceMessageReceiverInOut.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.5.1  Built on : Oct 19, 2009 (10:59:00 EDT)
 */
        package web.service.data;

        /**
        *  WebServiceDataServiceMessageReceiverInOut message receiver
        */

        public class WebServiceDataServiceMessageReceiverInOut extends org.apache.axis2.receivers.AbstractInOutMessageReceiver{


        public void invokeBusinessLogic(org.apache.axis2.context.MessageContext msgContext, org.apache.axis2.context.MessageContext newMsgContext)
        throws org.apache.axis2.AxisFault{

        try {

        // get the implementation class for the Web Service
        Object obj = getTheImplementationObject(msgContext);

        WebServiceDataServiceSkeletonInterface skel = (WebServiceDataServiceSkeletonInterface)obj;
        //Out Envelop
        org.apache.axiom.soap.SOAPEnvelope envelope = null;
        //Find the axisOperation that has been set by the Dispatch phase.
        org.apache.axis2.description.AxisOperation op = msgContext.getOperationContext().getAxisOperation();
        if (op == null) {
        throw new org.apache.axis2.AxisFault("Operation is not located, if this is doclit style the SOAP-ACTION should specified via the SOAP Action to use the RawXMLProvider");
        }

        java.lang.String methodName;
        if((op.getName() != null) && ((methodName = org.apache.axis2.util.JavaUtils.xmlNameToJavaIdentifier(op.getName().getLocalPart())) != null)){

        

            if("addData".equals(methodName)){
                
                web.service.data.AddDataResponse addDataResponse9 = null;
	                        web.service.data.AddData wrappedParam =
                                                             (web.service.data.AddData)fromOM(
                                    msgContext.getEnvelope().getBody().getFirstElement(),
                                    web.service.data.AddData.class,
                                    getEnvelopeNamespaces(msgContext.getEnvelope()));
                                                
                                               addDataResponse9 =
                                                   
                                                   
                                                         skel.addData(wrappedParam)
                                                    ;
                                            
                                        envelope = toEnvelope(getSOAPFactory(msgContext), addDataResponse9, false);
                                    } else 

            if("createTables".equals(methodName)){
                
                web.service.data.CreateTablesResponse createTablesResponse11 = null;
	                        web.service.data.CreateTables wrappedParam =
                                                             (web.service.data.CreateTables)fromOM(
                                    msgContext.getEnvelope().getBody().getFirstElement(),
                                    web.service.data.CreateTables.class,
                                    getEnvelopeNamespaces(msgContext.getEnvelope()));
                                                
                                               createTablesResponse11 =
                                                   
                                                   
                                                         skel.createTables(wrappedParam)
                                                    ;
                                            
                                        envelope = toEnvelope(getSOAPFactory(msgContext), createTablesResponse11, false);
                                    } else 

            if("getData".equals(methodName)){
                
                web.service.data.GetDataResponse getDataResponse13 = null;
	                        web.service.data.GetData wrappedParam =
                                                             (web.service.data.GetData)fromOM(
                                    msgContext.getEnvelope().getBody().getFirstElement(),
                                    web.service.data.GetData.class,
                                    getEnvelopeNamespaces(msgContext.getEnvelope()));
                                                
                                               getDataResponse13 =
                                                   
                                                   
                                                         skel.getData(wrappedParam)
                                                    ;
                                            
                                        envelope = toEnvelope(getSOAPFactory(msgContext), getDataResponse13, false);
                                    } else 

            if("updateData".equals(methodName)){
                
                web.service.data.UpdateDataResponse updateDataResponse15 = null;
	                        web.service.data.UpdateData wrappedParam =
                                                             (web.service.data.UpdateData)fromOM(
                                    msgContext.getEnvelope().getBody().getFirstElement(),
                                    web.service.data.UpdateData.class,
                                    getEnvelopeNamespaces(msgContext.getEnvelope()));
                                                
                                               updateDataResponse15 =
                                                   
                                                   
                                                         skel.updateData(wrappedParam)
                                                    ;
                                            
                                        envelope = toEnvelope(getSOAPFactory(msgContext), updateDataResponse15, false);
                                    
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
            private  org.apache.axiom.om.OMElement  toOM(web.service.data.AddData param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault {

            
                        try{
                             return param.getOMElement(web.service.data.AddData.MY_QNAME,
                                          org.apache.axiom.om.OMAbstractFactory.getOMFactory());
                        } catch(org.apache.axis2.databinding.ADBException e){
                            throw org.apache.axis2.AxisFault.makeFault(e);
                        }
                    

            }
        
            private  org.apache.axiom.om.OMElement  toOM(web.service.data.AddDataResponse param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault {

            
                        try{
                             return param.getOMElement(web.service.data.AddDataResponse.MY_QNAME,
                                          org.apache.axiom.om.OMAbstractFactory.getOMFactory());
                        } catch(org.apache.axis2.databinding.ADBException e){
                            throw org.apache.axis2.AxisFault.makeFault(e);
                        }
                    

            }
        
            private  org.apache.axiom.om.OMElement  toOM(web.service.data.CreateTables param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault {

            
                        try{
                             return param.getOMElement(web.service.data.CreateTables.MY_QNAME,
                                          org.apache.axiom.om.OMAbstractFactory.getOMFactory());
                        } catch(org.apache.axis2.databinding.ADBException e){
                            throw org.apache.axis2.AxisFault.makeFault(e);
                        }
                    

            }
        
            private  org.apache.axiom.om.OMElement  toOM(web.service.data.CreateTablesResponse param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault {

            
                        try{
                             return param.getOMElement(web.service.data.CreateTablesResponse.MY_QNAME,
                                          org.apache.axiom.om.OMAbstractFactory.getOMFactory());
                        } catch(org.apache.axis2.databinding.ADBException e){
                            throw org.apache.axis2.AxisFault.makeFault(e);
                        }
                    

            }
        
            private  org.apache.axiom.om.OMElement  toOM(web.service.data.GetData param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault {

            
                        try{
                             return param.getOMElement(web.service.data.GetData.MY_QNAME,
                                          org.apache.axiom.om.OMAbstractFactory.getOMFactory());
                        } catch(org.apache.axis2.databinding.ADBException e){
                            throw org.apache.axis2.AxisFault.makeFault(e);
                        }
                    

            }
        
            private  org.apache.axiom.om.OMElement  toOM(web.service.data.GetDataResponse param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault {

            
                        try{
                             return param.getOMElement(web.service.data.GetDataResponse.MY_QNAME,
                                          org.apache.axiom.om.OMAbstractFactory.getOMFactory());
                        } catch(org.apache.axis2.databinding.ADBException e){
                            throw org.apache.axis2.AxisFault.makeFault(e);
                        }
                    

            }
        
            private  org.apache.axiom.om.OMElement  toOM(web.service.data.UpdateData param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault {

            
                        try{
                             return param.getOMElement(web.service.data.UpdateData.MY_QNAME,
                                          org.apache.axiom.om.OMAbstractFactory.getOMFactory());
                        } catch(org.apache.axis2.databinding.ADBException e){
                            throw org.apache.axis2.AxisFault.makeFault(e);
                        }
                    

            }
        
            private  org.apache.axiom.om.OMElement  toOM(web.service.data.UpdateDataResponse param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault {

            
                        try{
                             return param.getOMElement(web.service.data.UpdateDataResponse.MY_QNAME,
                                          org.apache.axiom.om.OMAbstractFactory.getOMFactory());
                        } catch(org.apache.axis2.databinding.ADBException e){
                            throw org.apache.axis2.AxisFault.makeFault(e);
                        }
                    

            }
        
                    private  org.apache.axiom.soap.SOAPEnvelope toEnvelope(org.apache.axiom.soap.SOAPFactory factory, web.service.data.AddDataResponse param, boolean optimizeContent)
                        throws org.apache.axis2.AxisFault{
                      try{
                          org.apache.axiom.soap.SOAPEnvelope emptyEnvelope = factory.getDefaultEnvelope();
                           
                                    emptyEnvelope.getBody().addChild(param.getOMElement(web.service.data.AddDataResponse.MY_QNAME,factory));
                                

                         return emptyEnvelope;
                    } catch(org.apache.axis2.databinding.ADBException e){
                        throw org.apache.axis2.AxisFault.makeFault(e);
                    }
                    }
                    
                         private web.service.data.AddDataResponse wrapaddData(){
                                web.service.data.AddDataResponse wrappedElement = new web.service.data.AddDataResponse();
                                return wrappedElement;
                         }
                    
                    private  org.apache.axiom.soap.SOAPEnvelope toEnvelope(org.apache.axiom.soap.SOAPFactory factory, web.service.data.CreateTablesResponse param, boolean optimizeContent)
                        throws org.apache.axis2.AxisFault{
                      try{
                          org.apache.axiom.soap.SOAPEnvelope emptyEnvelope = factory.getDefaultEnvelope();
                           
                                    emptyEnvelope.getBody().addChild(param.getOMElement(web.service.data.CreateTablesResponse.MY_QNAME,factory));
                                

                         return emptyEnvelope;
                    } catch(org.apache.axis2.databinding.ADBException e){
                        throw org.apache.axis2.AxisFault.makeFault(e);
                    }
                    }
                    
                         private web.service.data.CreateTablesResponse wrapcreateTables(){
                                web.service.data.CreateTablesResponse wrappedElement = new web.service.data.CreateTablesResponse();
                                return wrappedElement;
                         }
                    
                    private  org.apache.axiom.soap.SOAPEnvelope toEnvelope(org.apache.axiom.soap.SOAPFactory factory, web.service.data.GetDataResponse param, boolean optimizeContent)
                        throws org.apache.axis2.AxisFault{
                      try{
                          org.apache.axiom.soap.SOAPEnvelope emptyEnvelope = factory.getDefaultEnvelope();
                           
                                    emptyEnvelope.getBody().addChild(param.getOMElement(web.service.data.GetDataResponse.MY_QNAME,factory));
                                

                         return emptyEnvelope;
                    } catch(org.apache.axis2.databinding.ADBException e){
                        throw org.apache.axis2.AxisFault.makeFault(e);
                    }
                    }
                    
                         private web.service.data.GetDataResponse wrapgetData(){
                                web.service.data.GetDataResponse wrappedElement = new web.service.data.GetDataResponse();
                                return wrappedElement;
                         }
                    
                    private  org.apache.axiom.soap.SOAPEnvelope toEnvelope(org.apache.axiom.soap.SOAPFactory factory, web.service.data.UpdateDataResponse param, boolean optimizeContent)
                        throws org.apache.axis2.AxisFault{
                      try{
                          org.apache.axiom.soap.SOAPEnvelope emptyEnvelope = factory.getDefaultEnvelope();
                           
                                    emptyEnvelope.getBody().addChild(param.getOMElement(web.service.data.UpdateDataResponse.MY_QNAME,factory));
                                

                         return emptyEnvelope;
                    } catch(org.apache.axis2.databinding.ADBException e){
                        throw org.apache.axis2.AxisFault.makeFault(e);
                    }
                    }
                    
                         private web.service.data.UpdateDataResponse wrapupdateData(){
                                web.service.data.UpdateDataResponse wrappedElement = new web.service.data.UpdateDataResponse();
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
        
                if (web.service.data.AddData.class.equals(type)){
                
                           return web.service.data.AddData.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                    

                }
           
                if (web.service.data.AddDataResponse.class.equals(type)){
                
                           return web.service.data.AddDataResponse.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                    

                }
           
                if (web.service.data.CreateTables.class.equals(type)){
                
                           return web.service.data.CreateTables.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                    

                }
           
                if (web.service.data.CreateTablesResponse.class.equals(type)){
                
                           return web.service.data.CreateTablesResponse.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                    

                }
           
                if (web.service.data.GetData.class.equals(type)){
                
                           return web.service.data.GetData.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                    

                }
           
                if (web.service.data.GetDataResponse.class.equals(type)){
                
                           return web.service.data.GetDataResponse.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                    

                }
           
                if (web.service.data.UpdateData.class.equals(type)){
                
                           return web.service.data.UpdateData.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                    

                }
           
                if (web.service.data.UpdateDataResponse.class.equals(type)){
                
                           return web.service.data.UpdateDataResponse.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                    

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
    
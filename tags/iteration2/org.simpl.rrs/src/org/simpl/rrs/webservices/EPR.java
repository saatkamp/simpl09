
/**
 * EPR.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.5  Built on : Apr 30, 2009 (06:07:47 EDT)
 */
            
                package org.simpl.rrs.webservices;
            

            /**
            *  EPR bean class
            */
        
        public  class EPR
        implements org.apache.axis2.databinding.ADBBean{
        /* This type was generated from the piece of schema that had
                name = EPR
                Namespace URI = http://webservices.rrs.simpl.org/
                Namespace Prefix = ns1
                */
            

        private static java.lang.String generatePrefix(java.lang.String namespace) {
            if(namespace.equals("http://webservices.rrs.simpl.org/")){
                return "ns1";
            }
            return org.apache.axis2.databinding.utils.BeanUtil.getUniquePrefix();
        }

        

                        /**
                        * field for Address
                        */

                        
                                    protected org.apache.axis2.databinding.types.URI localAddress ;
                                

                           /**
                           * Auto generated getter method
                           * @return org.apache.axis2.databinding.types.URI
                           */
                           public  org.apache.axis2.databinding.types.URI getAddress(){
                               return localAddress;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param Address
                               */
                               public void setAddress(org.apache.axis2.databinding.types.URI param){
                            
                                            this.localAddress=param;
                                    

                               }
                            

                        /**
                        * field for ReferenceProperties
                        */

                        
                                    protected org.simpl.rrs.webservices.ReferenceProperties localReferenceProperties ;
                                

                           /**
                           * Auto generated getter method
                           * @return org.simpl.rrs.webservices.ReferenceProperties
                           */
                           public  org.simpl.rrs.webservices.ReferenceProperties getReferenceProperties(){
                               return localReferenceProperties;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param ReferenceProperties
                               */
                               public void setReferenceProperties(org.simpl.rrs.webservices.ReferenceProperties param){
                            
                                            this.localReferenceProperties=param;
                                    

                               }
                            

                        /**
                        * field for ReferenceParameters
                        */

                        
                                    protected org.simpl.rrs.webservices.ReferenceParameters localReferenceParameters ;
                                

                           /**
                           * Auto generated getter method
                           * @return org.simpl.rrs.webservices.ReferenceParameters
                           */
                           public  org.simpl.rrs.webservices.ReferenceParameters getReferenceParameters(){
                               return localReferenceParameters;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param ReferenceParameters
                               */
                               public void setReferenceParameters(org.simpl.rrs.webservices.ReferenceParameters param){
                            
                                            this.localReferenceParameters=param;
                                    

                               }
                            

                        /**
                        * field for PortType
                        */

                        
                                    protected javax.xml.namespace.QName localPortType ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localPortTypeTracker = false ;
                           

                           /**
                           * Auto generated getter method
                           * @return javax.xml.namespace.QName
                           */
                           public  javax.xml.namespace.QName getPortType(){
                               return localPortType;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param PortType
                               */
                               public void setPortType(javax.xml.namespace.QName param){
                            
                                       if (param != null){
                                          //update the setting tracker
                                          localPortTypeTracker = true;
                                       } else {
                                          localPortTypeTracker = false;
                                              
                                       }
                                   
                                            this.localPortType=param;
                                    

                               }
                            

                        /**
                        * field for ServiceName
                        */

                        
                                    protected org.simpl.rrs.webservices.ServiceName localServiceName ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localServiceNameTracker = false ;
                           

                           /**
                           * Auto generated getter method
                           * @return org.simpl.rrs.webservices.ServiceName
                           */
                           public  org.simpl.rrs.webservices.ServiceName getServiceName(){
                               return localServiceName;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param ServiceName
                               */
                               public void setServiceName(org.simpl.rrs.webservices.ServiceName param){
                            
                                       if (param != null){
                                          //update the setting tracker
                                          localServiceNameTracker = true;
                                       } else {
                                          localServiceNameTracker = false;
                                              
                                       }
                                   
                                            this.localServiceName=param;
                                    

                               }
                            

                        /**
                        * field for Policy
                        */

                        
                                    protected java.lang.String localPolicy ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localPolicyTracker = false ;
                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getPolicy(){
                               return localPolicy;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param Policy
                               */
                               public void setPolicy(java.lang.String param){
                            
                                       if (param != null){
                                          //update the setting tracker
                                          localPolicyTracker = true;
                                       } else {
                                          localPolicyTracker = false;
                                              
                                       }
                                   
                                            this.localPolicy=param;
                                    

                               }
                            

     /**
     * isReaderMTOMAware
     * @return true if the reader supports MTOM
     */
   public static boolean isReaderMTOMAware(javax.xml.stream.XMLStreamReader reader) {
        boolean isReaderMTOMAware = false;
        
        try{
          isReaderMTOMAware = java.lang.Boolean.TRUE.equals(reader.getProperty(org.apache.axiom.om.OMConstants.IS_DATA_HANDLERS_AWARE));
        }catch(java.lang.IllegalArgumentException e){
          isReaderMTOMAware = false;
        }
        return isReaderMTOMAware;
   }
     
     
        /**
        *
        * @param parentQName
        * @param factory
        * @return org.apache.axiom.om.OMElement
        */
       public org.apache.axiom.om.OMElement getOMElement (
               final javax.xml.namespace.QName parentQName,
               final org.apache.axiom.om.OMFactory factory) throws org.apache.axis2.databinding.ADBException{


        
               org.apache.axiom.om.OMDataSource dataSource =
                       new org.apache.axis2.databinding.ADBDataSource(this,parentQName){

                 public void serialize(org.apache.axis2.databinding.utils.writer.MTOMAwareXMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException {
                       EPR.this.serialize(parentQName,factory,xmlWriter);
                 }
               };
               return new org.apache.axiom.om.impl.llom.OMSourcedElementImpl(
               parentQName,factory,dataSource);
            
       }

         public void serialize(final javax.xml.namespace.QName parentQName,
                                       final org.apache.axiom.om.OMFactory factory,
                                       org.apache.axis2.databinding.utils.writer.MTOMAwareXMLStreamWriter xmlWriter)
                                throws javax.xml.stream.XMLStreamException, org.apache.axis2.databinding.ADBException{
                           serialize(parentQName,factory,xmlWriter,false);
         }

         public void serialize(final javax.xml.namespace.QName parentQName,
                               final org.apache.axiom.om.OMFactory factory,
                               org.apache.axis2.databinding.utils.writer.MTOMAwareXMLStreamWriter xmlWriter,
                               boolean serializeType)
            throws javax.xml.stream.XMLStreamException, org.apache.axis2.databinding.ADBException{
            
                


                java.lang.String prefix = null;
                java.lang.String namespace = null;
                

                    prefix = parentQName.getPrefix();
                    namespace = parentQName.getNamespaceURI();

                    if ((namespace != null) && (namespace.trim().length() > 0)) {
                        java.lang.String writerPrefix = xmlWriter.getPrefix(namespace);
                        if (writerPrefix != null) {
                            xmlWriter.writeStartElement(namespace, parentQName.getLocalPart());
                        } else {
                            if (prefix == null) {
                                prefix = generatePrefix(namespace);
                            }

                            xmlWriter.writeStartElement(prefix, parentQName.getLocalPart(), namespace);
                            xmlWriter.writeNamespace(prefix, namespace);
                            xmlWriter.setPrefix(prefix, namespace);
                        }
                    } else {
                        xmlWriter.writeStartElement(parentQName.getLocalPart());
                    }
                
                  if (serializeType){
               

                   java.lang.String namespacePrefix = registerPrefix(xmlWriter,"http://webservices.rrs.simpl.org/");
                   if ((namespacePrefix != null) && (namespacePrefix.trim().length() > 0)){
                       writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","type",
                           namespacePrefix+":EPR",
                           xmlWriter);
                   } else {
                       writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","type",
                           "EPR",
                           xmlWriter);
                   }

               
                   }
               
                                    namespace = "";
                                    if (! namespace.equals("")) {
                                        prefix = xmlWriter.getPrefix(namespace);

                                        if (prefix == null) {
                                            prefix = generatePrefix(namespace);

                                            xmlWriter.writeStartElement(prefix,"address", namespace);
                                            xmlWriter.writeNamespace(prefix, namespace);
                                            xmlWriter.setPrefix(prefix, namespace);

                                        } else {
                                            xmlWriter.writeStartElement(namespace,"address");
                                        }

                                    } else {
                                        xmlWriter.writeStartElement("address");
                                    }
                                

                                          if (localAddress==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("address cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localAddress));
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             
                                            if (localReferenceProperties==null){
                                                 throw new org.apache.axis2.databinding.ADBException("referenceProperties cannot be null!!");
                                            }
                                           localReferenceProperties.serialize(new javax.xml.namespace.QName("","referenceProperties"),
                                               factory,xmlWriter);
                                        
                                            if (localReferenceParameters==null){
                                                 throw new org.apache.axis2.databinding.ADBException("referenceParameters cannot be null!!");
                                            }
                                           localReferenceParameters.serialize(new javax.xml.namespace.QName("","referenceParameters"),
                                               factory,xmlWriter);
                                         if (localPortTypeTracker){
                                    namespace = "";
                                    if (! namespace.equals("")) {
                                        prefix = xmlWriter.getPrefix(namespace);

                                        if (prefix == null) {
                                            prefix = generatePrefix(namespace);

                                            xmlWriter.writeStartElement(prefix,"portType", namespace);
                                            xmlWriter.writeNamespace(prefix, namespace);
                                            xmlWriter.setPrefix(prefix, namespace);

                                        } else {
                                            xmlWriter.writeStartElement(namespace,"portType");
                                        }

                                    } else {
                                        xmlWriter.writeStartElement("portType");
                                    }
                                

                                          if (localPortType==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("portType cannot be null!!");
                                                  
                                          }else{

                                        
                                                writeQName(localPortType,xmlWriter);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localServiceNameTracker){
                                            if (localServiceName==null){
                                                 throw new org.apache.axis2.databinding.ADBException("serviceName cannot be null!!");
                                            }
                                           localServiceName.serialize(new javax.xml.namespace.QName("","serviceName"),
                                               factory,xmlWriter);
                                        } if (localPolicyTracker){
                                    namespace = "";
                                    if (! namespace.equals("")) {
                                        prefix = xmlWriter.getPrefix(namespace);

                                        if (prefix == null) {
                                            prefix = generatePrefix(namespace);

                                            xmlWriter.writeStartElement(prefix,"policy", namespace);
                                            xmlWriter.writeNamespace(prefix, namespace);
                                            xmlWriter.setPrefix(prefix, namespace);

                                        } else {
                                            xmlWriter.writeStartElement(namespace,"policy");
                                        }

                                    } else {
                                        xmlWriter.writeStartElement("policy");
                                    }
                                

                                          if (localPolicy==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("policy cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localPolicy);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             }
                    xmlWriter.writeEndElement();
               

        }

         /**
          * Util method to write an attribute with the ns prefix
          */
          private void writeAttribute(java.lang.String prefix,java.lang.String namespace,java.lang.String attName,
                                      java.lang.String attValue,javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException{
              if (xmlWriter.getPrefix(namespace) == null) {
                       xmlWriter.writeNamespace(prefix, namespace);
                       xmlWriter.setPrefix(prefix, namespace);

              }

              xmlWriter.writeAttribute(namespace,attName,attValue);

         }

        /**
          * Util method to write an attribute without the ns prefix
          */
          private void writeAttribute(java.lang.String namespace,java.lang.String attName,
                                      java.lang.String attValue,javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException{
                if (namespace.equals(""))
              {
                  xmlWriter.writeAttribute(attName,attValue);
              }
              else
              {
                  registerPrefix(xmlWriter, namespace);
                  xmlWriter.writeAttribute(namespace,attName,attValue);
              }
          }


           /**
             * Util method to write an attribute without the ns prefix
             */
            private void writeQNameAttribute(java.lang.String namespace, java.lang.String attName,
                                             javax.xml.namespace.QName qname, javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException {

                java.lang.String attributeNamespace = qname.getNamespaceURI();
                java.lang.String attributePrefix = xmlWriter.getPrefix(attributeNamespace);
                if (attributePrefix == null) {
                    attributePrefix = registerPrefix(xmlWriter, attributeNamespace);
                }
                java.lang.String attributeValue;
                if (attributePrefix.trim().length() > 0) {
                    attributeValue = attributePrefix + ":" + qname.getLocalPart();
                } else {
                    attributeValue = qname.getLocalPart();
                }

                if (namespace.equals("")) {
                    xmlWriter.writeAttribute(attName, attributeValue);
                } else {
                    registerPrefix(xmlWriter, namespace);
                    xmlWriter.writeAttribute(namespace, attName, attributeValue);
                }
            }
        /**
         *  method to handle Qnames
         */

        private void writeQName(javax.xml.namespace.QName qname,
                                javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException {
            java.lang.String namespaceURI = qname.getNamespaceURI();
            if (namespaceURI != null) {
                java.lang.String prefix = xmlWriter.getPrefix(namespaceURI);
                if (prefix == null) {
                    prefix = generatePrefix(namespaceURI);
                    xmlWriter.writeNamespace(prefix, namespaceURI);
                    xmlWriter.setPrefix(prefix,namespaceURI);
                }

                if (prefix.trim().length() > 0){
                    xmlWriter.writeCharacters(prefix + ":" + org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
                } else {
                    // i.e this is the default namespace
                    xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
                }

            } else {
                xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
            }
        }

        private void writeQNames(javax.xml.namespace.QName[] qnames,
                                 javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException {

            if (qnames != null) {
                // we have to store this data until last moment since it is not possible to write any
                // namespace data after writing the charactor data
                java.lang.StringBuffer stringToWrite = new java.lang.StringBuffer();
                java.lang.String namespaceURI = null;
                java.lang.String prefix = null;

                for (int i = 0; i < qnames.length; i++) {
                    if (i > 0) {
                        stringToWrite.append(" ");
                    }
                    namespaceURI = qnames[i].getNamespaceURI();
                    if (namespaceURI != null) {
                        prefix = xmlWriter.getPrefix(namespaceURI);
                        if ((prefix == null) || (prefix.length() == 0)) {
                            prefix = generatePrefix(namespaceURI);
                            xmlWriter.writeNamespace(prefix, namespaceURI);
                            xmlWriter.setPrefix(prefix,namespaceURI);
                        }

                        if (prefix.trim().length() > 0){
                            stringToWrite.append(prefix).append(":").append(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qnames[i]));
                        } else {
                            stringToWrite.append(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qnames[i]));
                        }
                    } else {
                        stringToWrite.append(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qnames[i]));
                    }
                }
                xmlWriter.writeCharacters(stringToWrite.toString());
            }

        }


         /**
         * Register a namespace prefix
         */
         private java.lang.String registerPrefix(javax.xml.stream.XMLStreamWriter xmlWriter, java.lang.String namespace) throws javax.xml.stream.XMLStreamException {
                java.lang.String prefix = xmlWriter.getPrefix(namespace);

                if (prefix == null) {
                    prefix = generatePrefix(namespace);

                    while (xmlWriter.getNamespaceContext().getNamespaceURI(prefix) != null) {
                        prefix = org.apache.axis2.databinding.utils.BeanUtil.getUniquePrefix();
                    }

                    xmlWriter.writeNamespace(prefix, namespace);
                    xmlWriter.setPrefix(prefix, namespace);
                }

                return prefix;
            }


  
        /**
        * databinding method to get an XML representation of this object
        *
        */
        public javax.xml.stream.XMLStreamReader getPullParser(javax.xml.namespace.QName qName)
                    throws org.apache.axis2.databinding.ADBException{


        
                 java.util.ArrayList elementList = new java.util.ArrayList();
                 java.util.ArrayList attribList = new java.util.ArrayList();

                
                                      elementList.add(new javax.xml.namespace.QName("",
                                                                      "address"));
                                 
                                        if (localAddress != null){
                                            elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localAddress));
                                        } else {
                                           throw new org.apache.axis2.databinding.ADBException("address cannot be null!!");
                                        }
                                    
                            elementList.add(new javax.xml.namespace.QName("",
                                                                      "referenceProperties"));
                            
                            
                                    if (localReferenceProperties==null){
                                         throw new org.apache.axis2.databinding.ADBException("referenceProperties cannot be null!!");
                                    }
                                    elementList.add(localReferenceProperties);
                                
                            elementList.add(new javax.xml.namespace.QName("",
                                                                      "referenceParameters"));
                            
                            
                                    if (localReferenceParameters==null){
                                         throw new org.apache.axis2.databinding.ADBException("referenceParameters cannot be null!!");
                                    }
                                    elementList.add(localReferenceParameters);
                                 if (localPortTypeTracker){
                                      elementList.add(new javax.xml.namespace.QName("",
                                                                      "portType"));
                                 
                                        if (localPortType != null){
                                            elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localPortType));
                                        } else {
                                           throw new org.apache.axis2.databinding.ADBException("portType cannot be null!!");
                                        }
                                    } if (localServiceNameTracker){
                            elementList.add(new javax.xml.namespace.QName("",
                                                                      "serviceName"));
                            
                            
                                    if (localServiceName==null){
                                         throw new org.apache.axis2.databinding.ADBException("serviceName cannot be null!!");
                                    }
                                    elementList.add(localServiceName);
                                } if (localPolicyTracker){
                                      elementList.add(new javax.xml.namespace.QName("",
                                                                      "policy"));
                                 
                                        if (localPolicy != null){
                                            elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localPolicy));
                                        } else {
                                           throw new org.apache.axis2.databinding.ADBException("policy cannot be null!!");
                                        }
                                    }

                return new org.apache.axis2.databinding.utils.reader.ADBXMLStreamReaderImpl(qName, elementList.toArray(), attribList.toArray());
            
            

        }

  

     /**
      *  Factory class that keeps the parse method
      */
    public static class Factory{

        
        

        /**
        * static method to create the object
        * Precondition:  If this object is an element, the current or next start element starts this object and any intervening reader events are ignorable
        *                If this object is not an element, it is a complex type and the reader is at the event just after the outer start element
        * Postcondition: If this object is an element, the reader is positioned at its end element
        *                If this object is a complex type, the reader is positioned at the end element of its outer element
        */
        public static EPR parse(javax.xml.stream.XMLStreamReader reader) throws java.lang.Exception{
            EPR object =
                new EPR();

            int event;
            java.lang.String nillableValue = null;
            java.lang.String prefix ="";
            java.lang.String namespaceuri ="";
            try {
                
                while (!reader.isStartElement() && !reader.isEndElement())
                    reader.next();

                
                if (reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","type")!=null){
                  java.lang.String fullTypeName = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance",
                        "type");
                  if (fullTypeName!=null){
                    java.lang.String nsPrefix = null;
                    if (fullTypeName.indexOf(":") > -1){
                        nsPrefix = fullTypeName.substring(0,fullTypeName.indexOf(":"));
                    }
                    nsPrefix = nsPrefix==null?"":nsPrefix;

                    java.lang.String type = fullTypeName.substring(fullTypeName.indexOf(":")+1);
                    
                            if (!"EPR".equals(type)){
                                //find namespace for the prefix
                                java.lang.String nsUri = reader.getNamespaceContext().getNamespaceURI(nsPrefix);
                                return (EPR)org.simpl.rrs.webservices.ExtensionMapper.getTypeObject(
                                     nsUri,type,reader);
                              }
                        

                  }
                

                }

                

                
                // Note all attributes that were handled. Used to differ normal attributes
                // from anyAttributes.
                java.util.Vector handledAttributes = new java.util.Vector();
                

                 
                    
                    reader.next();
                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","address").equals(reader.getName())){
                                
                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setAddress(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToAnyURI(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                else{
                                    // A start element we are not expecting indicates an invalid parameter was passed
                                    throw new org.apache.axis2.databinding.ADBException("Unexpected subelement " + reader.getLocalName());
                                }
                            
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","referenceProperties").equals(reader.getName())){
                                
                                                object.setReferenceProperties(org.simpl.rrs.webservices.ReferenceProperties.Factory.parse(reader));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                else{
                                    // A start element we are not expecting indicates an invalid parameter was passed
                                    throw new org.apache.axis2.databinding.ADBException("Unexpected subelement " + reader.getLocalName());
                                }
                            
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","referenceParameters").equals(reader.getName())){
                                
                                                object.setReferenceParameters(org.simpl.rrs.webservices.ReferenceParameters.Factory.parse(reader));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                else{
                                    // A start element we are not expecting indicates an invalid parameter was passed
                                    throw new org.apache.axis2.databinding.ADBException("Unexpected subelement " + reader.getLocalName());
                                }
                            
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","portType").equals(reader.getName())){
                                
                                    java.lang.String content = reader.getElementText();
                                    
                                            int index = content.indexOf(":");
                                            if(index > 0){
                                                prefix = content.substring(0,index);
                                             } else {
                                                prefix = "";
                                             }
                                             namespaceuri = reader.getNamespaceURI(prefix);
                                             object.setPortType(
                                                  org.apache.axis2.databinding.utils.ConverterUtil.convertToQName(content,namespaceuri));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","serviceName").equals(reader.getName())){
                                
                                                object.setServiceName(org.simpl.rrs.webservices.ServiceName.Factory.parse(reader));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","policy").equals(reader.getName())){
                                
                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setPolicy(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                  
                            while (!reader.isStartElement() && !reader.isEndElement())
                                reader.next();
                            
                                if (reader.isStartElement())
                                // A start element we are not expecting indicates a trailing invalid property
                                throw new org.apache.axis2.databinding.ADBException("Unexpected subelement " + reader.getLocalName());
                            



            } catch (javax.xml.stream.XMLStreamException e) {
                throw new java.lang.Exception(e);
            }

            return object;
        }

        }//end of factory class

        

        }
           
          
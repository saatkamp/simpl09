
/**
 * ExtensionMapper.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.5  Built on : Apr 30, 2009 (06:07:47 EDT)
 */

            package org.simpl.rrs.webservices;
            /**
            *  ExtensionMapper class
            */
        
        public  class ExtensionMapper{

          public static java.lang.Object getTypeObject(java.lang.String namespaceURI,
                                                       java.lang.String typeName,
                                                       javax.xml.stream.XMLStreamReader reader) throws java.lang.Exception{

              
                  if (
                  "http://webservices.rrs.simpl.org/".equals(namespaceURI) &&
                  "ServiceName".equals(typeName)){
                   
                            return  org.simpl.rrs.webservices.ServiceName.Factory.parse(reader);
                        

                  }

              
                  if (
                  "http://webservices.rrs.simpl.org/".equals(namespaceURI) &&
                  "EPRArray".equals(typeName)){
                   
                            return  org.simpl.rrs.webservices.EPRArray.Factory.parse(reader);
                        

                  }

              
                  if (
                  "http://webservices.rrs.simpl.org/".equals(namespaceURI) &&
                  "EPR".equals(typeName)){
                   
                            return  org.simpl.rrs.webservices.EPR.Factory.parse(reader);
                        

                  }

              
                  if (
                  "http://webservices.rrs.simpl.org/".equals(namespaceURI) &&
                  "ReferenceParameters".equals(typeName)){
                   
                            return  org.simpl.rrs.webservices.ReferenceParameters.Factory.parse(reader);
                        

                  }

              
                  if (
                  "http://webservices.rrs.simpl.org/".equals(namespaceURI) &&
                  "ReferenceProperties".equals(typeName)){
                   
                            return  org.simpl.rrs.webservices.ReferenceProperties.Factory.parse(reader);
                        

                  }

              
                  if (
                  "http://webservices.rrs.simpl.org/".equals(namespaceURI) &&
                  "RDBSet".equals(typeName)){
                   
                            return  org.simpl.rrs.webservices.RDBSet.Factory.parse(reader);
                        

                  }

              
                  if (
                  "http://webservices.rrs.simpl.org/".equals(namespaceURI) &&
                  "stringArray".equals(typeName)){
                   
                            return  org.simpl.rrs.webservices.StringArray.Factory.parse(reader);
                        

                  }

              
                  if (
                  "http://webservices.rrs.simpl.org/".equals(namespaceURI) &&
                  "column".equals(typeName)){
                   
                            return  org.simpl.rrs.webservices.Column.Factory.parse(reader);
                        

                  }

              
                  if (
                  "http://webservices.rrs.simpl.org/".equals(namespaceURI) &&
                  "table".equals(typeName)){
                   
                            return  org.simpl.rrs.webservices.Table.Factory.parse(reader);
                        

                  }

              
             throw new org.apache.axis2.databinding.ADBException("Unsupported type " + namespaceURI + " " + typeName);
          }

        }
    
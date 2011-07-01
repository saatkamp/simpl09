
/**
 * ExtensionMapper.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.5.1  Built on : Oct 19, 2009 (10:59:34 EDT)
 */

            package de.uni_stuttgart.dwarf.www.biif;
            /**
            *  ExtensionMapper class
            */
        
        public  class ExtensionMapper{

          public static java.lang.Object getTypeObject(java.lang.String namespaceURI,
                                                       java.lang.String typeName,
                                                       javax.xml.stream.XMLStreamReader reader) throws java.lang.Exception{

              
                  if (
                  "http://www.dwarf.uni-stuttgart.de/BIIF/".equals(namespaceURI) &&
                  "regionAnnotationType".equals(typeName)){
                   
                            return  de.uni_stuttgart.dwarf.www.biif.RegionAnnotationType.Factory.parse(reader);
                        

                  }

              
                  if (
                  "http://www.dwarf.uni-stuttgart.de/BIIF/".equals(namespaceURI) &&
                  "dnaAlnType".equals(typeName)){
                   
                            return  de.uni_stuttgart.dwarf.www.biif.DnaAlnType.Factory.parse(reader);
                        

                  }

              
                  if (
                  "http://data.service.web/".equals(namespaceURI) &&
                  "Exception".equals(typeName)){
                   
                            return  web.service.data.Exception.Factory.parse(reader);
                        

                  }

              
                  if (
                  "http://www.dwarf.uni-stuttgart.de/BIIF/".equals(namespaceURI) &&
                  "biifType".equals(typeName)){
                   
                            return  de.uni_stuttgart.dwarf.www.biif.BiifType.Factory.parse(reader);
                        

                  }

              
                  if (
                  "http://www.dwarf.uni-stuttgart.de/BIIF/".equals(namespaceURI) &&
                  "formatType".equals(typeName)){
                   
                            return  de.uni_stuttgart.dwarf.www.biif.FormatType.Factory.parse(reader);
                        

                  }

              
                  if (
                  "http://www.dwarf.uni-stuttgart.de/BIIF/".equals(namespaceURI) &&
                  "aaAlnType".equals(typeName)){
                   
                            return  de.uni_stuttgart.dwarf.www.biif.AaAlnType.Factory.parse(reader);
                        

                  }

              
                  if (
                  "http://www.dwarf.uni-stuttgart.de/BIIF/".equals(namespaceURI) &&
                  "dataType".equals(typeName)){
                   
                            return  de.uni_stuttgart.dwarf.www.biif.DataType.Factory.parse(reader);
                        

                  }

              
                  if (
                  "http://www.dwarf.uni-stuttgart.de/BIIF/".equals(namespaceURI) &&
                  "similarityAlnType".equals(typeName)){
                   
                            return  de.uni_stuttgart.dwarf.www.biif.SimilarityAlnType.Factory.parse(reader);
                        

                  }

              
                  if (
                  "http://www.dwarf.uni-stuttgart.de/BIIF/".equals(namespaceURI) &&
                  "rnaAlnType".equals(typeName)){
                   
                            return  de.uni_stuttgart.dwarf.www.biif.RnaAlnType.Factory.parse(reader);
                        

                  }

              
                  if (
                  "http://www.dwarf.uni-stuttgart.de/BIIF/".equals(namespaceURI) &&
                  "pairAnnotationType".equals(typeName)){
                   
                            return  de.uni_stuttgart.dwarf.www.biif.PairAnnotationType.Factory.parse(reader);
                        

                  }

              
                  if (
                  "http://www.dwarf.uni-stuttgart.de/BIIF/".equals(namespaceURI) &&
                  "annotationType".equals(typeName)){
                   
                            return  de.uni_stuttgart.dwarf.www.biif.AnnotationType.Factory.parse(reader);
                        

                  }

              
                  if (
                  "http://www.dwarf.uni-stuttgart.de/BIIF/".equals(namespaceURI) &&
                  "binaryChoiceType".equals(typeName)){
                   
                            return  de.uni_stuttgart.dwarf.www.biif.BinaryChoiceType.Factory.parse(reader);
                        

                  }

              
                  if (
                  "http://www.dwarf.uni-stuttgart.de/BIIF/".equals(namespaceURI) &&
                  "sourceType".equals(typeName)){
                   
                            return  de.uni_stuttgart.dwarf.www.biif.SourceType.Factory.parse(reader);
                        

                  }

              
                  if (
                  "http://www.dwarf.uni-stuttgart.de/BIIF/".equals(namespaceURI) &&
                  "alignmentSet".equals(typeName)){
                   
                            return  de.uni_stuttgart.dwarf.www.biif.AlignmentSet.Factory.parse(reader);
                        

                  }

              
                  if (
                  "http://www.dwarf.uni-stuttgart.de/BIIF/".equals(namespaceURI) &&
                  "databaseType".equals(typeName)){
                   
                            return  de.uni_stuttgart.dwarf.www.biif.DatabaseType.Factory.parse(reader);
                        

                  }

              
                  if (
                  "http://www.dwarf.uni-stuttgart.de/BIIF/".equals(namespaceURI) &&
                  "bioSequenceType".equals(typeName)){
                   
                            return  de.uni_stuttgart.dwarf.www.biif.BioSequenceType.Factory.parse(reader);
                        

                  }

              
                  if (
                  "http://www.dwarf.uni-stuttgart.de/BIIF/".equals(namespaceURI) &&
                  "lsidType".equals(typeName)){
                   
                            return  de.uni_stuttgart.dwarf.www.biif.LsidType.Factory.parse(reader);
                        

                  }

              
                  if (
                  "http://www.dwarf.uni-stuttgart.de/BIIF/".equals(namespaceURI) &&
                  "pointAnnotationType".equals(typeName)){
                   
                            return  de.uni_stuttgart.dwarf.www.biif.PointAnnotationType.Factory.parse(reader);
                        

                  }

              
                  if (
                  "http://www.dwarf.uni-stuttgart.de/BIIF/".equals(namespaceURI) &&
                  "sequenceSet".equals(typeName)){
                   
                            return  de.uni_stuttgart.dwarf.www.biif.SequenceSet.Factory.parse(reader);
                        

                  }

              
             throw new org.apache.axis2.databinding.ADBException("Unsupported type " + namespaceURI + " " + typeName);
          }

        }
    
package org.eclipse.simpl.rrs.transformation.jet;

public class TemplateRrsXSD
{
  protected static String nl;
  public static synchronized TemplateRrsXSD create(String lineSeparator)
  {
    nl = lineSeparator;
    TemplateRrsXSD result = new TemplateRrsXSD();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "<xsd:schema xmlns:ecore=\"http://www.eclipse.org/emf/2002/Ecore\" xmlns:rrs=\"http://uni-stuttgart.de/simpl/rrs\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" ecore:nsPrefix=\"rrs\" ecore:package=\"org.eclipse.simpl.rrs.model.rrs\" targetNamespace=\"http://uni-stuttgart.de/simpl/rrs\">" + NL + " \t<xs:element name=\"epr\" type=\"tns:EPR\"/>" + NL + "    <xs:complexType name=\"EPR\">" + NL + "        <xs:sequence>" + NL + "            <xs:element name=\"address\" type=\"xs:anyURI\"/>" + NL + "            <xs:element name=\"referenceProperties\" type=\"tns:ReferenceProperties\"/>" + NL + "            <xs:element name=\"referenceParameters\" type=\"tns:ReferenceParameters\"/>" + NL + "            <xs:element minOccurs=\"0\" name=\"portType\" type=\"xs:QName\"/>" + NL + "            <xs:element minOccurs=\"0\" name=\"serviceName\" type=\"tns:ServiceName\"/>" + NL + "            <xs:element minOccurs=\"0\" name=\"policy\" type=\"xs:string\"/>" + NL + "        </xs:sequence>" + NL + "    </xs:complexType>" + NL + "    <xs:complexType name=\"ReferenceProperties\">" + NL + "        <xs:sequence>" + NL + "            <xs:element minOccurs=\"0\" name=\"resolutionSystem\" type=\"xs:string\"/>" + NL + "        </xs:sequence>" + NL + "    </xs:complexType>" + NL + "    <xs:complexType name=\"ReferenceParameters\">" + NL + "        <xs:sequence>" + NL + "            <xs:element name=\"referenceName\" type=\"xs:string\"/>" + NL + "            <xs:element name=\"dsAddress\" type=\"xs:string\"/>" + NL + "            <xs:element name=\"statement\" type=\"xs:string\"/>" + NL + "        </xs:sequence>" + NL + "    </xs:complexType>" + NL + "    <xs:complexType name=\"ServiceName\">" + NL + "        <xs:simpleContent>" + NL + "            <xs:extension base=\"xs:QName\">" + NL + "                <xs:attribute name=\"portName\" type=\"xs:NCName\"/>" + NL + "            </xs:extension>" + NL + "        </xs:simpleContent>" + NL + "    </xs:complexType>" + NL + "</xsd:schema>";

  public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
    stringBuffer.append(TEXT_1);
    return stringBuffer.toString();
  }
}

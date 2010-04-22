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
  protected final String TEXT_1 = "<xsd:schema xmlns:ecore=\"http://www.eclipse.org/emf/2002/Ecore\" xmlns:rrs=\"http://uni-stuttgart.de/simpl/rrs\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" ecore:nsPrefix=\"rrs\" ecore:package=\"org.eclipse.simpl.rrs.model.rrs\" targetNamespace=\"http://uni-stuttgart.de/simpl/rrs\">" + NL + "  <xsd:complexType name=\"EPR\">" + NL + "    <xsd:sequence>" + NL + "      <xsd:element form=\"qualified\" name=\"address\" type=\"xsd:anyURI\"/>" + NL + "      <xsd:element form=\"qualified\" name=\"referenceProperties\" type=\"rrs:ReferenceProperties\"/>" + NL + "      <xsd:element form=\"qualified\" name=\"referenceParameters\" type=\"rrs:ReferenceParameters\"/>" + NL + "      <xsd:element form=\"qualified\" minOccurs=\"0\" name=\"portType\" type=\"xsd:QName\"/>" + NL + "      <xsd:element form=\"qualified\" minOccurs=\"0\" name=\"serviceName\" type=\"rrs:ServiceName\"/>" + NL + "      <xsd:element form=\"qualified\" minOccurs=\"0\" name=\"policy\" type=\"xsd:string\"/>" + NL + "    </xsd:sequence>" + NL + "  </xsd:complexType>" + NL + "  <xsd:complexType name=\"ReferenceParameters\">" + NL + "    <xsd:sequence>" + NL + "      <xsd:element form=\"qualified\" name=\"referenceName\" type=\"xsd:string\"/>" + NL + "      <xsd:element form=\"qualified\" name=\"statement\" type=\"xsd:string\"/>" + NL + "    </xsd:sequence>" + NL + "  </xsd:complexType>" + NL + "  <xsd:complexType name=\"ReferenceProperties\">" + NL + "    <xsd:sequence>" + NL + "      <xsd:element form=\"qualified\" minOccurs=\"0\" name=\"resolutionSystem\" type=\"xsd:string\"/>" + NL + "    </xsd:sequence>" + NL + "  </xsd:complexType>" + NL + "  <xsd:complexType name=\"ServiceName\">" + NL + "    <xsd:simpleContent>" + NL + "      <xsd:extension base=\"xsd:QName\">" + NL + "        <xsd:attribute name=\"portName\" type=\"xsd:NCName\"/>" + NL + "      </xsd:extension>" + NL + "    </xsd:simpleContent>" + NL + "  </xsd:complexType>" + NL + "" + NL + "    <xsd:element name=\"epr\" type=\"rrs:EPR\"></xsd:element>" + NL + "</xsd:schema>";

  public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
    stringBuffer.append(TEXT_1);
    return stringBuffer.toString();
  }
}

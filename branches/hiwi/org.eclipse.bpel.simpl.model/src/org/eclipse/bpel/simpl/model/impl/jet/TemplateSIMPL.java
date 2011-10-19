package org.eclipse.bpel.simpl.model.impl.jet;

public class TemplateSIMPL
{
  protected static String nl;
  public static synchronized TemplateSIMPL create(String lineSeparator)
  {
    nl = lineSeparator;
    TemplateSIMPL result = new TemplateSIMPL();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" + NL + "<xsd:schema elementFormDefault=\"qualified\" targetNamespace=\"http://www.example.org/simpl\"" + NL + "  xmlns:simpl=\"http://www.example.org/simpl\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\">" + NL + "  <xsd:simpleType name=\"BPELVariableName\">" + NL + "    <xsd:restriction base=\"xsd:NCName\">" + NL + "      <xsd:pattern value=\"[^\\.]+\" />" + NL + "    </xsd:restriction>" + NL + "  </xsd:simpleType>" + NL + "" + NL + "  <xsd:complexType name=\"DataContainerReferenceType\">" + NL + "  </xsd:complexType>" + NL + "" + NL + "  <xsd:complexType name=\"DataSourceReferenceType\">" + NL + "    <xsd:sequence>" + NL + "      <xsd:element name=\"name\" type=\"xsd:string\"></xsd:element>" + NL + "      <xsd:element name=\"requirements\" type=\"xsd:string\"></xsd:element>" + NL + "    </xsd:sequence>" + NL + "  </xsd:complexType>" + NL + "" + NL + "  <xsd:complexType name=\"RelationalDatabaseDataContainerReferenceType\">" + NL + "    <xsd:complexContent>" + NL + "      <xsd:extension base=\"simpl:DataContainerReferenceType\">" + NL + "        <xsd:sequence>" + NL + "          <xsd:element name=\"schema\" type=\"xsd:string\" maxOccurs=\"1\"" + NL + "            minOccurs=\"0\">" + NL + "          </xsd:element>" + NL + "          <xsd:element name=\"table\" type=\"xsd:string\" maxOccurs=\"1\"" + NL + "            minOccurs=\"1\">" + NL + "          </xsd:element>" + NL + "        </xsd:sequence>" + NL + "        <xsd:attribute name=\"stringPattern\" type=\"xsd:string\" use=\"required\" fixed=\"schema.table\">" + NL + "        </xsd:attribute>" + NL + "      </xsd:extension>" + NL + "    </xsd:complexContent>" + NL + "  </xsd:complexType>" + NL + "</xsd:schema>";

  public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
    stringBuffer.append(TEXT_1);
    return stringBuffer.toString();
  }
}

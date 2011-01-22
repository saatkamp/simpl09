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
  protected final String TEXT_1 = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" + NL + "<xsd:schema elementFormDefault=\"qualified\" targetNamespace=\"http://www.example.org/simpl\"" + NL + "  xmlns:simpl=\"http://www.example.org/simpl\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\">" + NL + "" + NL + "  <xsd:element name=\"queryActivity\" type=\"simpl:QueryDataActivityType\" />" + NL + "" + NL + "  <xsd:complexType name=\"DataManagementActivityType\">" + NL + "    <xsd:attribute name=\"name\" type=\"xsd:string\" use=\"optional\" />" + NL + "    <xsd:attribute name=\"dsType\" type=\"xsd:string\" use=\"optional\" />" + NL + "    <xsd:attribute name=\"dsKind\" type=\"xsd:string\" use=\"optional\" />" + NL + "    <xsd:attribute name=\"dsLanguage\" type=\"xsd:string\"></xsd:attribute>" + NL + "    <xsd:attribute name=\"dsStatement\" type=\"xsd:string\"" + NL + "      use=\"optional\" />" + NL + "    <xsd:attribute name=\"dsAddress\" type=\"xsd:string\"" + NL + "      use=\"optional\" />" + NL + "  </xsd:complexType>" + NL + "" + NL + "  <xsd:complexType name=\"QueryDataActivityType\">" + NL + "    <xsd:complexContent>" + NL + "      <xsd:extension base=\"simpl:DataManagementActivityType\">" + NL + "" + NL + "        <xsd:attribute name=\"queryTarget\" type=\"xsd:string\"></xsd:attribute>" + NL + "      </xsd:extension>" + NL + "    </xsd:complexContent>" + NL + "  </xsd:complexType>" + NL + "" + NL + "  <xsd:complexType name=\"RetrieveDataActivityType\">" + NL + "    <xsd:complexContent>" + NL + "      <xsd:extension base=\"simpl:DataManagementActivityType\">" + NL + "" + NL + "        <xsd:attribute name=\"dataVariable\"" + NL + "          type=\"simpl:BPELVariableName\" use=\"optional\">" + NL + "        </xsd:attribute>" + NL + "      </xsd:extension>" + NL + "    </xsd:complexContent>" + NL + "  </xsd:complexType>" + NL + "" + NL + "  <xsd:element name=\"issueCommandActivity\"" + NL + "    type=\"simpl:DataManagementActivityType\">" + NL + "  </xsd:element>" + NL + "" + NL + "  <xsd:element name=\"retrieveDataActivity\"" + NL + "    type=\"simpl:RetrieveDataActivityType\">" + NL + "  </xsd:element>" + NL + "  " + NL + "  <xsd:simpleType name=\"BPELVariableName\">" + NL + "        <xsd:restriction base=\"xsd:NCName\">" + NL + "            <xsd:pattern value=\"[^\\.]+\"/>" + NL + "        </xsd:restriction>" + NL + "    </xsd:simpleType>" + NL + "" + NL + "  <xsd:complexType name=\"ContainerReferenceType\">" + NL + "    <xsd:sequence>" + NL + "      <xsd:element name=\"schema\" type=\"xsd:string\" maxOccurs=\"1\"" + NL + "        minOccurs=\"0\">" + NL + "      </xsd:element>" + NL + "      <xsd:element name=\"table\" type=\"xsd:string\" maxOccurs=\"1\"" + NL + "        minOccurs=\"1\">" + NL + "      </xsd:element>" + NL + "" + NL + "    </xsd:sequence>" + NL + "    <xsd:attribute name=\"stringPattern\" type=\"xsd:string\"" + NL + "      use=\"required\" fixed=\"schema.table\">" + NL + "    </xsd:attribute>" + NL + "" + NL + "  </xsd:complexType>" + NL + "" + NL + "  <xsd:element name=\"containerReference\" type=\"simpl:ContainerReferenceType\"></xsd:element>" + NL + "" + NL + "" + NL + "  <xsd:element name=\"writeDataBackActivity\"" + NL + "    type=\"simpl:WriteDataBackActivityType\">" + NL + "  </xsd:element>" + NL + "  " + NL + "  <xsd:complexType name=\"WriteDataBackActivityType\">" + NL + "    <xsd:complexContent>" + NL + "      <xsd:extension base=\"simpl:DataManagementActivityType\">" + NL + "" + NL + "        <xsd:attribute name=\"dataVariable\"" + NL + "          type=\"simpl:BPELVariableName\" use=\"optional\">" + NL + "        </xsd:attribute>" + NL + "      </xsd:extension>" + NL + "    </xsd:complexContent>" + NL + "  </xsd:complexType></xsd:schema>";

  public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
    stringBuffer.append(TEXT_1);
    return stringBuffer.toString();
  }
}

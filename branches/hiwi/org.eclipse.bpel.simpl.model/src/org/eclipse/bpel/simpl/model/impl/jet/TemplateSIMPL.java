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
  protected final String TEXT_1 = "<xsd:schema elementFormDefault=\"qualified\" targetNamespace=\"http://www.example.org/simpl\"" + NL + "\txmlns:simpl=\"http://www.example.org/simpl\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\">" + NL + "" + NL + "\t<xsd:element name=\"queryActivity\" type=\"simpl:QueryActivityType\" />" + NL + "" + NL + "\t<xsd:complexType name=\"DataManagementActivityType\">" + NL + "\t\t<xsd:attribute name=\"name\" type=\"xsd:string\" use=\"optional\" />" + NL + "\t\t<xsd:attribute name=\"dsType\" type=\"xsd:string\" use=\"optional\" />" + NL + "\t\t<xsd:attribute name=\"dsKind\" type=\"xsd:string\" use=\"optional\" />" + NL + "\t\t<xsd:attribute name=\"dsLanguage\" type=\"xsd:string\"></xsd:attribute>" + NL + "\t\t<xsd:attribute name=\"dsStatement\" type=\"xsd:string\"" + NL + "\t\t\tuse=\"optional\" />" + NL + "\t\t<xsd:attribute name=\"dsAddress\" type=\"xsd:string\"" + NL + "\t\t\tuse=\"optional\" />" + NL + "\t</xsd:complexType>" + NL + "" + NL + "" + NL + "\t<xsd:complexType name=\"QueryActivityType\">" + NL + "\t\t<xsd:complexContent>" + NL + "\t\t\t<xsd:extension base=\"simpl:DataManagementActivityType\">" + NL + "" + NL + "\t\t\t\t<xsd:attribute name=\"queryTarget\" type=\"xsd:string\"></xsd:attribute>" + NL + "\t\t\t</xsd:extension>" + NL + "\t\t</xsd:complexContent>" + NL + "\t</xsd:complexType>" + NL + "" + NL + "\t<xsd:complexType name=\"RetrieveDataActivityType\">" + NL + "\t\t<xsd:complexContent>" + NL + "\t\t\t<xsd:extension base=\"simpl:DataManagementActivityType\">" + NL + "" + NL + "\t\t\t\t<xsd:attribute name=\"dataVariable\"" + NL + "\t\t\t\t\ttype=\"simpl:BPELVariableName\" use=\"optional\">" + NL + "\t\t\t\t</xsd:attribute>" + NL + "\t\t\t</xsd:extension>" + NL + "\t\t</xsd:complexContent>" + NL + "\t</xsd:complexType>" + NL + "" + NL + "\t<xsd:element name=\"insertActivity\"" + NL + "\t\ttype=\"simpl:DataManagementActivityType\">" + NL + "\t</xsd:element>" + NL + "" + NL + "\t<xsd:element name=\"updateActivity\"" + NL + "\t\ttype=\"simpl:DataManagementActivityType\">" + NL + "\t</xsd:element>" + NL + "" + NL + "\t<xsd:element name=\"deleteActivity\"" + NL + "\t\ttype=\"simpl:DataManagementActivityType\">" + NL + "\t</xsd:element>" + NL + "" + NL + "\t<xsd:element name=\"createActivity\"" + NL + "\t\ttype=\"simpl:DataManagementActivityType\">" + NL + "\t</xsd:element>" + NL + "" + NL + "\t<xsd:element name=\"dropActivity\"" + NL + "\t\ttype=\"simpl:DataManagementActivityType\">" + NL + "\t</xsd:element>" + NL + "" + NL + "\t<xsd:element name=\"callActivity\"" + NL + "\t\ttype=\"simpl:DataManagementActivityType\">" + NL + "\t</xsd:element>" + NL + "" + NL + "\t<xsd:element name=\"retrieveDataActivity\"" + NL + "\t\ttype=\"simpl:RetrieveDataActivityType\">" + NL + "\t</xsd:element>" + NL + "\t" + NL + "\t<xsd:simpleType name=\"BPELVariableName\">" + NL + "        <xsd:restriction base=\"xsd:NCName\">" + NL + "            <xsd:pattern value=\"[^\\.]+\"/>" + NL + "        </xsd:restriction>" + NL + "    </xsd:simpleType>" + NL + "" + NL + "\t<xsd:complexType name=\"ContainerReferenceType\">" + NL + "\t\t<xsd:sequence>" + NL + "\t\t\t<xsd:element name=\"schema\" type=\"xsd:string\" maxOccurs=\"1\"" + NL + "\t\t\t\tminOccurs=\"0\">" + NL + "\t\t\t</xsd:element>" + NL + "\t\t\t<xsd:element name=\"table\" type=\"xsd:string\" maxOccurs=\"1\"" + NL + "\t\t\t\tminOccurs=\"1\">" + NL + "\t\t\t</xsd:element>" + NL + "\t\t</xsd:sequence>" + NL + "\t\t<xsd:attribute name=\"stringPattern\" type=\"xsd:string\"" + NL + "\t\t\tuse=\"required\" fixed=\"schema.table\">" + NL + "\t\t</xsd:attribute>" + NL + "\t</xsd:complexType>" + NL + "" + NL + "\t<xsd:element name=\"containerReference\" type=\"simpl:ContainerReferenceType\"></xsd:element>" + NL + "</xsd:schema>";

  public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
    stringBuffer.append(TEXT_1);
    return stringBuffer.toString();
  }
}

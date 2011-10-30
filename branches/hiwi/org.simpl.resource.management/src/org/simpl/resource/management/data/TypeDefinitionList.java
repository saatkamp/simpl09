package org.simpl.resource.management.data;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "typeDefinitionList", propOrder = { "typeDefinitions" })
public class TypeDefinitionList {
  @XmlElement(name = "typeDefinition", nillable = true)
  protected List<TypeDefinition> typeDefinitions;

  public List<TypeDefinition> getTypeDefinitions() {
    if (typeDefinitions == null) {
      typeDefinitions = new ArrayList<TypeDefinition>();
    }

    return this.typeDefinitions;
  }
}
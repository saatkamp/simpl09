package org.simpl.core.resolver;

public class SchemaElement {
  private String name;
  private String type;
  private String value;
  private boolean required;

  /**
   * @return the name
   */
  public String getName() {
    return name;
  }

  /**
   * @param name
   *          the name to set
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * @return the type
   */
  public String getType() {
    return type;
  }

  /**
   * @param type
   *          the type to set
   */
  public void setType(String type) {
    this.type = type;
  }

  /**
   * @return the value
   */
  public String getValue() {
    return value;
  }

  /**
   * @param value
   *          the value to set
   */
  public void setValue(String value) {
    this.value = value;
  }

  /**
   * @return the required
   */
  public boolean isRequired() {
    return required;
  }

  /**
   * @param required
   *          the required to set
   */
  public void setRequired(boolean required) {
    this.required = required;
  }
}

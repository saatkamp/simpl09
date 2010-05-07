package org.simpl.core.services.datasource;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <b>Purpose:</b>Represents a data source with all relevant information that is needed to
 * build up a connection.<br>
 * <b>Description:</b>Type and subtype are used by the SIMPL Core to determine a data
 * source service that can respond to the data source over the address. Further it holds
 * the necessary authentication information and provides information for the strategic
 * late binding of the data source with a policy.<br>
 * <b>Copyright:</b> <br>
 * <b>Company:</b> SIMPL<br>
 * 
 * @author schneimi<br>
 * @version $Id$<br>
 * @link http://code.google.com/p/simpl09/
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "DataSource")
@XmlType(name = "DataSource", propOrder = { "name", "address", "type", "subType",
    "authentication", "lateBinding", "language", "dataFormat"})
public class DataSource {
  private String name;
  private String address;
  private String type;
  private String subType;
  private String language;
  private String dataFormat;
  private Authentication authentication = new Authentication();
  private LateBinding lateBinding = new LateBinding();

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
   * @return the address
   */
  public String getAddress() {
    return address;
  }

  /**
   * @param address
   *          the address to set
   */
  public void setAddress(String address) {
    this.address = address;
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
   * @return the subType
   */
  public String getSubType() {
    return subType;
  }

  /**
   * @param subType
   *          the subType to set
   */
  public void setSubType(String subType) {
    this.subType = subType;
  }

  /**
   * @return the authentication
   */
  public Authentication getAuthentication() {
    return authentication;
  }

  /**
   * @param authentication
   *          the authentication to set
   */
  public void setAuthentication(Authentication authentication) {
    this.authentication = authentication;
  }

  /**
   * @return the lateBinding
   */
  public LateBinding getLateBinding() {
    return lateBinding;
  }

  /**
   * @param lateBinding
   *          the lateBinding to set
   */
  public void setLateBinding(LateBinding lateBinding) {
    this.lateBinding = lateBinding;
  }

  /**
   * @return the language
   */
  public String getLanguage() {
    return language;
  }

  /**
   * @param language
   *          the language to set
   */
  public void setLanguage(String language) {
    this.language = language;
  }

  /**
   * @return the dataFormat
   */
  public String getDataFormat() {
    return dataFormat;
  }

  /**
   * @param dataFormat
   *          the dataFormat to set
   */
  public void setDataFormat(String dataFormat) {
    this.dataFormat = dataFormat;
  }
}
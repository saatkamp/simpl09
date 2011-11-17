package org.simpl.resource.management.data;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "strategyPluginList", propOrder = { "strategyPlugins" })
public class StrategyPluginList {
  @XmlElement(name = "strategyPlugin", nillable = true)
  protected List<StrategyPlugin> strategyPlugins;

  public List<StrategyPlugin> getStrategyPlugins() {
    if (strategyPlugins == null) {
      strategyPlugins = new ArrayList<StrategyPlugin>();
    }

    return this.strategyPlugins;
  }
}
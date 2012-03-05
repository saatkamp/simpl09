package org.simpl.resource.discovery.strategy;

import java.util.List;

import org.apache.neethi.Policy;
import org.simpl.resource.management.data.DataSource;

/**
 * <b>Purpose:</b>Defines the functions a strategy plug-ins needs to implement.<br>
 * <b>Description:</b><br>
 * <b>Copyright:</b>Licensed under the Apache License, Version 2.0.
 * http://www.apache.org/licenses/LICENSE-2.0<br>
 * <b>Company:</b>SIMPL<br>
 *
 * @author hiwi<br>
 * @version $Id: IStrategy.java 1962 2011-10-25 10:32:46Z michael.schneidt@arcor.de $<br>
 * @link http://code.google.com/p/simpl09/
 */
public interface IStrategy {
  public DataSource find(Policy policy, List<DataSource> dataSources);
}
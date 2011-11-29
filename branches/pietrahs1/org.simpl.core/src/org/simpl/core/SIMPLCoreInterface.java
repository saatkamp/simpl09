package org.simpl.core;

import java.io.InputStream;

import org.simpl.core.clients.RDClient;
import org.simpl.core.clients.RMClient;
import org.simpl.core.exceptions.ConnectionException;
import org.simpl.resource.management.data.DataSource;
import org.simpl.resource.management.data.LateBinding;

import commonj.sdo.DataObject;

/**
 * <b>Purpose:</b>Interface to access the SIMPL Core implementation that provides late
 * binding of data sources and the resolution of logical data source descriptors (data
 * source references).<br>
 * <b>Description:</b> If the given data source is not fully specified and late binding
 * information is given, the discovery service is consulted to find a matching full
 * specified data source.<br>
 * <b>Copyright:</b>Licensed under the Apache License, Version 2.0.
 * http://www.apache.org/licenses/LICENSE-2.0<br>
 * <b>Company:</b>SIMPL<br>
 * 
 * @author schneimi<br>
 * @version $Id: SIMPLCoreInterface.java 1815 2011-07-05 12:33:12Z
 *          michael.schneidt@arcor.de $<br>
 * @link http://code.google.com/p/simpl09/
 */
public class SIMPLCoreInterface {
  SIMPLCoreImpl simplCore = new SIMPLCoreImpl();
  RDClient resourceDiscovery = new RDClient();

  public boolean issueCommand(DataSource dataSource, String statement,
      LateBinding lateBinding) throws ConnectionException {
    boolean success = false;

    // late binding
    if (this.lateBindingIsValid(lateBinding)) {
      DataSource lateBoundDataSource = this.resourceDiscovery.findDataSource(lateBinding);
      
      if (lateBoundDataSource.getName() != null) {
        dataSource = lateBoundDataSource;
      }
    }

    // execute statement
    success = simplCore.issueCommand(dataSource, statement);

    return success;
  }

  public boolean issueCommand(String dataSourceName, String statement,
      LateBinding lateBinding) throws ConnectionException {
    DataSource dataSource = resolveDataSource(dataSourceName);

    return this.issueCommand(dataSource, statement, lateBinding);
  }

  public boolean queryData(DataSource dataSource, String statement, String target,
      LateBinding lateBinding) throws ConnectionException {
    boolean success = false;

    // late binding
    if (this.lateBindingIsValid(lateBinding)) {
      DataSource lateBoundDataSource = this.resourceDiscovery.findDataSource(lateBinding);
      
      if (lateBoundDataSource.getName() != null) {
        dataSource = lateBoundDataSource;
      }
    }

    // deposit data
    success = simplCore.queryData(dataSource, statement, target);

    return success;
  }

  public boolean queryData(String dataSourceName, String statement, String target,
      LateBinding lateBinding) throws ConnectionException {
    DataSource dataSource = resolveDataSource(dataSourceName);

    return this.queryData(dataSource, statement, target, lateBinding);
  }

  public DataObject retrieveData(DataSource dataSource, String statement,
      LateBinding lateBinding) throws ConnectionException {
    DataObject retrievedData = null;

    // late binding
    if (this.lateBindingIsValid(lateBinding)) {
      DataSource lateBoundDataSource = this.resourceDiscovery.findDataSource(lateBinding);
      
      if (lateBoundDataSource.getName() != null) {
        dataSource = lateBoundDataSource;
      }
    }

    // retrieve data
    retrievedData = simplCore.retrieveData(dataSource, statement);

    return retrievedData;
  }

  public DataObject retrieveData(String dataSourceName, String statement,
      LateBinding lateBinding) throws ConnectionException {
    DataSource dataSource = resolveDataSource(dataSourceName);

    return this.retrieveData(dataSource, statement, lateBinding);
  }

  public boolean writeDataBack(DataSource dataSource, DataObject data, String target,
      LateBinding lateBinding) throws ConnectionException {
    boolean success = false;

    // late binding
    if (this.lateBindingIsValid(lateBinding)) {
      DataSource lateBoundDataSource = this.resourceDiscovery.findDataSource(lateBinding);
      
      if (lateBoundDataSource.getName() != null) {
        dataSource = lateBoundDataSource;
      }
    }

    success = simplCore.writeDataBack(dataSource, data, target);

    return success;
  }

  public boolean writeDataBack(String dataSourceName, DataObject data,
      String target, LateBinding lateBinding) throws ConnectionException {
    DataSource dataSource = resolveDataSource(dataSourceName);

    return this.writeDataBack(dataSource, data, target, lateBinding);
  }

  public DataObject getMetaData(DataSource dataSource, String filter)
      throws ConnectionException {
    DataObject data = null;

    // get meta data
    data = simplCore.getMetaData(dataSource, filter);

    return data;
  }

  public DataObject getMetaData(String dataSourceName, String filter)
      throws ConnectionException {
    DataSource dataSource = resolveDataSource(dataSourceName);

    return this.getMetaData(dataSource, filter);
  }

  public InputStream getMetaDataSchemaFile(DataSource dataSource) {
    return this.simplCore.getMetaDataSchemaFile(dataSource);
  }

  /**
   * Checks if a given data source has valid late binding information.
   * 
   * TODO: perhaps check policy validity
   * 
   * @param dataSource
   * @return
   */
  private boolean lateBindingIsValid(LateBinding lateBinding) {
    boolean isValid = false;

    if (lateBinding != null && lateBinding.getPolicy() != null
        && lateBinding.getStrategy() != null) {
      isValid = true;
    }

    return isValid;
  }

  /**
   * Resolves the data source from its name.
   * 
   * @param dataSourceName
   * @return
   */
  private DataSource resolveDataSource(String dataSourceName) {
    DataSource dataSource = null;

    dataSource = RMClient.getInstance().getDataSourceByName(
        dataSourceName);

    return dataSource;
  }
}
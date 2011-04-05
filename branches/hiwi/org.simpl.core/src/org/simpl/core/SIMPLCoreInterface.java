package org.simpl.core;

import java.io.InputStream;

import org.simpl.core.clients.RMClient;
import org.simpl.core.discovery.DiscoveryService;
import org.simpl.core.discovery.DiscoveryServiceImpl;
import org.simpl.core.exceptions.ConnectionException;
import org.simpl.resource.management.data.DataSource;
import org.simpl.resource.management.data.LateBinding;

import commonj.sdo.DataObject;

/**
 * <b>Purpose:</b>Interface to access the SIMPL Core implementation that provides late
 * binding of data sources and the resolution of logical data source descriptors (LDSD).<br>
 * <b>Description:</b> If the given data source is not fully specified and late binding
 * information is given, the discovery service is consulted to find a matching full
 * specified data source.<br>
 * <b>Copyright:</b>Licensed under the Apache License, Version 2.0.
 * http://www.apache.org/licenses/LICENSE-2.0<br>
 * <b>Company:</b>SIMPL<br>
 * 
 * @author schneimi<br>
 * @version $Id: DataSourceServiceInterface.java 1780 2011-03-19 16:38:36Z
 *          michael.schneidt@arcor.de $<br>
 * @link http://code.google.com/p/simpl09/
 */
public class SIMPLCoreInterface {
  SIMPLCoreImpl simplCore = new SIMPLCoreImpl();
  DiscoveryService discoveryService = new DiscoveryServiceImpl();

  public boolean issueCommand(DataSource dataSource, String statement,
      LateBinding lateBinding) throws ConnectionException {
    boolean success = false;

    // late binding
    if (this.lateBindingIsValid(lateBinding)) {
      dataSource = this.discoveryService.findDataSource(lateBinding);
    }

    // execute statement
    success = simplCore.issueCommand(dataSource, statement);

    return success;
  }

  public boolean issueCommand(String dataSourceDescriptor, String statement,
      LateBinding lateBinding) throws ConnectionException {
    DataSource dataSource = resolveDataSourceDescriptor(dataSourceDescriptor);

    return this.issueCommand(dataSource, statement, lateBinding);
  }

  public boolean queryData(DataSource dataSource, String statement, String target,
      LateBinding lateBinding) throws ConnectionException {
    boolean success = false;

    // late binding
    if (this.lateBindingIsValid(lateBinding)) {
      dataSource = this.discoveryService.findDataSource(lateBinding);
    }

    // deposit data
    success = simplCore.queryData(dataSource, statement, target);

    return success;
  }

  public boolean queryData(String dataSourceDescriptor, String statement, String target,
      LateBinding lateBinding) throws ConnectionException {
    DataSource dataSource = resolveDataSourceDescriptor(dataSourceDescriptor);

    return this.queryData(dataSource, statement, target, lateBinding);
  }

  public DataObject retrieveData(DataSource dataSource, String statement,
      LateBinding lateBinding) throws ConnectionException {
    DataObject retrievedData = null;

    // late binding
    if (this.lateBindingIsValid(lateBinding)) {
      dataSource = this.discoveryService.findDataSource(lateBinding);
    }

    // retrieve data
    retrievedData = simplCore.retrieveData(dataSource, statement);

    return retrievedData;
  }

  public DataObject retrieveData(String dataSourceDescriptor, String statement,
      LateBinding lateBinding) throws ConnectionException {
    DataSource dataSource = resolveDataSourceDescriptor(dataSourceDescriptor);

    return this.retrieveData(dataSource, statement, lateBinding);
  }

  public boolean writeDataBack(DataSource dataSource, DataObject data, String target,
      LateBinding lateBinding) throws ConnectionException {
    boolean success = false;

    // late binding
    if (this.lateBindingIsValid(lateBinding)) {
      dataSource = this.discoveryService.findDataSource(lateBinding);
    }

    success = simplCore.writeDataBack(dataSource, data, target);

    return success;
  }

  public boolean writeDataBack(String dataSourceDescriptor, DataObject data,
      String target, LateBinding lateBinding) throws ConnectionException {
    DataSource dataSource = resolveDataSourceDescriptor(dataSourceDescriptor);

    return this.writeDataBack(dataSource, data, target, lateBinding);
  }

  public DataObject getMetaData(DataSource dataSource, String filter)
      throws ConnectionException {
    DataObject data = null;

    // get meta data
    data = simplCore.getMetaData(dataSource, filter);

    return data;
  }

  public DataObject getMetaData(String dataSourceDescriptor, String filter)
      throws ConnectionException {
    DataSource dataSource = resolveDataSourceDescriptor(dataSourceDescriptor);

    return this.getMetaData(dataSource, filter);
  }

  public InputStream getMetaDataSchemaFile(DataSource dataSource) {
    return this.simplCore.getMetaDataSchemaFile(dataSource);
  }

  /**
   * Checks if a given data source has valid late binding information.
   * 
   * TODO: check policy validity
   * 
   * @param dataSource
   * @return
   */
  private boolean lateBindingIsValid(LateBinding lateBinding) {
    boolean hasLateBindingInformation = false;

    if (lateBinding != null && lateBinding.getPolicy() != null
        && lateBinding.getStrategy() != null) {
      hasLateBindingInformation = true;
    }

    return hasLateBindingInformation;
  }

  /**
   * Resolves the data source descriptor.
   * 
   * @param dataSourceDescriptor
   * @return
   */
  private DataSource resolveDataSourceDescriptor(String dataSourceDescriptor) {
    DataSource dataSource = null;

    dataSource = RMClient.getInstance().getDataSourceByName(
        dataSourceDescriptor);

    return dataSource;
  }
}
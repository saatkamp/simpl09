package org.simpl.core.services.datasource;

import java.io.InputStream;

import org.simpl.core.SIMPLResourceManagement;
import org.simpl.core.services.datasource.exceptions.ConnectionException;
import org.simpl.core.services.discovery.DiscoveryService;
import org.simpl.core.services.discovery.DiscoveryServiceImpl;
import org.simpl.resource.management.client.DataSource;
import org.simpl.resource.management.client.LateBinding;

import commonj.sdo.DataObject;

/**
 * <b>Purpose:</b>Interface to access the common data source service implementation,
 * providing late binding of data sources and the resolution of logical data source
 * descriptors (LDSD).<br>
 * <b>Description:</b> If the given data source is not fully specified and late binding
 * information is given, the discovery service is consulted to find a matching full
 * specified data source.<br>
 * <b>Copyright:</b>Licensed under the Apache License, Version 2.0.
 * http://www.apache.org/licenses/LICENSE-2.0<br>
 * <b>Company:</b>SIMPL<br>
 * 
 * @author schneimi<br>
 * @version $Id$<br>
 * @link http://code.google.com/p/simpl09/
 */
public class DataSourceServiceInterface {
  DataSourceServiceImpl dataSourceService = new DataSourceServiceImpl();
  DiscoveryService discoveryService = new DiscoveryServiceImpl();

  public boolean executeStatement(DataSource dataSource, String statement,
      LateBinding lateBinding) throws ConnectionException {
    boolean success = false;

    // late binding
    if (this.lateBindingIsValid(lateBinding)) {
      dataSource = this.discoveryService.findDataSource(lateBinding);
    }

    // execute statement
    success = dataSourceService.executeStatement(dataSource, statement);

    return success;
  }

  public boolean depositData(DataSource dataSource, String statement, String target,
      LateBinding lateBinding) throws ConnectionException {
    boolean success = false;

    // late binding
    if (this.lateBindingIsValid(lateBinding)) {
      dataSource = this.discoveryService.findDataSource(lateBinding);
    }

    // deposit data
    success = dataSourceService.depositData(dataSource, statement, target);

    return success;
  }

  public DataObject retrieveData(DataSource dataSource, String statement,
      LateBinding lateBinding) throws ConnectionException {
    DataObject retrievedData = null;

    // late binding
    if (this.lateBindingIsValid(lateBinding)) {
      dataSource = this.discoveryService.findDataSource(lateBinding);
    }

    // retrieve data
    retrievedData = dataSourceService.retrieveData(dataSource, statement);

    return retrievedData;
  }

  public boolean writeData(DataSource dataSource, DataObject data, String target,
      LateBinding lateBinding) throws ConnectionException {
    boolean success = false;

    // late binding
    if (this.lateBindingIsValid(lateBinding)) {
      dataSource = this.discoveryService.findDataSource(lateBinding);
    }

    success = dataSourceService.writeData(dataSource, data, target);

    return success;
  }

  public boolean writeBack(DataSource dataSource, DataObject data, LateBinding lateBinding)
      throws ConnectionException {
    boolean success = false;

    // late binding
    if (this.lateBindingIsValid(lateBinding)) {
      dataSource = this.discoveryService.findDataSource(lateBinding);
    }

    success = dataSourceService.writeBack(dataSource, data);

    return success;
  }

  public boolean executeStatement(String dataSourceDescriptor, String statement,
      LateBinding lateBinding) throws ConnectionException {
    DataSource dataSource = resolveDataSourceDescriptor(dataSourceDescriptor);

    return this.executeStatement(dataSource, statement, lateBinding);
  }

  public boolean depositData(String dataSourceDescriptor, String statement,
      String target, LateBinding lateBinding) throws ConnectionException {
    DataSource dataSource = resolveDataSourceDescriptor(dataSourceDescriptor);

    return this.depositData(dataSource, statement, target, lateBinding);
  }

  public DataObject retrieveData(String dataSourceDescriptor, String statement,
      LateBinding lateBinding) throws ConnectionException {
    DataSource dataSource = resolveDataSourceDescriptor(dataSourceDescriptor);

    return this.retrieveData(dataSource, statement, lateBinding);
  }

  public boolean writeData(String dataSourceDescriptor, DataObject data, String target,
      LateBinding lateBinding) throws ConnectionException {
    DataSource dataSource = resolveDataSourceDescriptor(dataSourceDescriptor);

    return this.writeData(dataSource, data, target, lateBinding);
  }

  public boolean writeBack(String dataSourceDescriptor, DataObject data,
      LateBinding lateBinding) throws ConnectionException {
    DataSource dataSource = resolveDataSourceDescriptor(dataSourceDescriptor);

    return this.writeBack(dataSource, data, lateBinding);
  }

  public DataObject getMetaData(DataSource dataSource, String filter)
      throws ConnectionException {
    DataObject data = null;

    // get meta data
    data = dataSourceService.getMetaData(dataSource, filter);

    return data;
  }

  public boolean createTarget(DataSource dataSource, DataObject dataObject, String target)
      throws ConnectionException {
    boolean success = false;

    // create target
    success = dataSourceService.createTarget(dataSource, dataObject, target);

    return success;
  }

  public boolean createTarget(String dataSourceDescriptor, DataObject dataObject,
      String target) throws ConnectionException {
    DataSource dataSource = resolveDataSourceDescriptor(dataSourceDescriptor);

    return this.createTarget(dataSource, dataObject, target);
  }

  public DataObject getMetaData(String dataSourceDescriptor, String filter)
      throws ConnectionException {
    DataSource dataSource = resolveDataSourceDescriptor(dataSourceDescriptor);

    return this.getMetaData(dataSource, filter);
  }

  public InputStream getMetaDataSchemaFile(DataSource dataSource) {
    return this.dataSourceService.getMetaDataSchemaFile(dataSource);
  }

  /**
   * Checks if a given data source has late binding information.
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

    dataSource = SIMPLResourceManagement.getInstance().getDataSourceByName(
        dataSourceDescriptor);

    return dataSource;
  }
}
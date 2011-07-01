/**
 * WebServiceDataServiceSkeleton.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.5.1  Built on : Oct 19, 2009 (10:59:00 EDT)
 */
package web.service.data;

/**
 * WebServiceDataServiceSkeleton java skeleton for the axisService
 */
public class WebServiceDataServiceSkeleton implements
    WebServiceDataServiceSkeletonInterface {
  WebServiceDataService webServiceDataService = new WebServiceDataService();

  /**
   * Auto generated method signature
   * 
   * @param addData0
   */

  public web.service.data.AddDataResponse addData(web.service.data.AddData request) {
    AddDataResponse response = new AddDataResponse();

    try {
      response.set_return(webServiceDataService.addData(request.getSize(),
          request.getData()));
    } catch (Throwable e) {
      // throw new java.lang.UnsupportedOperationException("Please implement "
      // + this.getClass().getName() + "#addData");
    }

    return response;
  }

  /**
   * Auto generated method signature
   * 
   * @param createTables2
   */

  public web.service.data.CreateTablesResponse createTables(
      web.service.data.CreateTables createTables2) {
    // TODO : fill this with the necessary business logic
    throw new java.lang.UnsupportedOperationException("Please implement "
        + this.getClass().getName() + "#createTables");
  }

  /**
   * Auto generated method signature
   * 
   * @param getData4
   */

  public web.service.data.GetDataResponse getData(web.service.data.GetData request) {
    GetDataResponse response = new GetDataResponse();

    try {
      System.out.println("size: " + request.getSize());
      System.out.println("result: " + webServiceDataService.getData(request.getSize()));
      response.setBiif(webServiceDataService.getData(request.getSize()));
    } catch (Throwable e) {
      // throw new java.lang.UnsupportedOperationException("Please implement "
      // + this.getClass().getName() + "#addData");
    }

    return response;
  }

  /**
   * Auto generated method signature
   * 
   * @param updateData6
   */

  public web.service.data.UpdateDataResponse updateData(
      web.service.data.UpdateData request) {
    UpdateDataResponse response = new UpdateDataResponse();

    try {
      response.set_return(webServiceDataService.updateData(request.getId(),
          request.getSize(), request.getData()));
    } catch (Throwable e) {
      // throw new java.lang.UnsupportedOperationException("Please implement "
      // + this.getClass().getName() + "#addData");
    }

    return response;
  }

}

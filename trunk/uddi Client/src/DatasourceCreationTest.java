/*
 * Copyright 2001-2010 The Apache Software Foundation.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

import org.uddi.api_v3.*;
import org.apache.juddi.ClassUtil;
import org.apache.juddi.api_v3.*;
import org.apache.juddi.v3.client.config.UDDIClientContainer;
import org.apache.juddi.v3.client.transport.Transport;
import org.uddi.v3_service.UDDISecurityPortType;
import org.uddi.v3_service.UDDIPublicationPortType;
import org.apache.juddi.v3_service.JUDDIApiPortType;

public class DatasourceCreationTest {
	private static UDDISecurityPortType security = null;
	
	private static JUDDIApiPortType juddiApi = null;
	private static UDDIPublicationPortType publish = null;

	public DatasourceCreationTest() {
        try {
        	String clazz = UDDIClientContainer.getUDDIClerkManager(null).
            	getClientConfig().getUDDINode("default").getProxyTransport();
        	Class<?> transportClass = ClassUtil.forName(clazz, Transport.class);
			if (transportClass!=null) {
				Transport transport = (Transport) transportClass.
					getConstructor(String.class).newInstance("default");

				security = transport.getUDDISecurityService();
				juddiApi = transport.getJUDDIApiService();
				publish = transport.getUDDIPublishService();
				
			}	
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}

	public void publish() {
		try {
			// Setting up the values to get an authentication token for the 'root' user ('root' user has admin privileges
			// and can save other publishers).
			GetAuthToken getAuthTokenRoot = new GetAuthToken();
			getAuthTokenRoot.setUserID("root");
			getAuthTokenRoot.setCred("");
			

			// Making API call that retrieves the authentication token for the 'root' user.
			AuthToken rootAuthToken = security.getAuthToken(getAuthTokenRoot);
			System.out.println ("root AUTHTOKEN = " + rootAuthToken.getAuthInfo());

//			// Creating a new publisher that we will use to publish our entities to.
//			Publisher p = new Publisher();
//			p.setAuthorizedName("simpl");
//			p.setPublisherName("Simpl Publisher");
//			p.setIsAdmin("false");
//			p.setIsEnabled("true");
//
//			// Adding the publisher to the "save" structure, using the 'root' user authentication info and saving away. 
//			SavePublisher sp = new SavePublisher();
//			sp.getPublisher().add(p);
//			sp.setAuthInfo(rootAuthToken.getAuthInfo());
//			juddiApi.savePublisher(sp);
			
//			// Our publisher is now saved, so now we want to retrieve its authentication token
//			GetAuthToken getAuthTokenMyPub = new GetAuthToken();
//			getAuthTokenMyPub.setUserID("simpl");
//			getAuthTokenMyPub.setCred("");
//			AuthToken myPubAuthToken = security.getAuthToken(getAuthTokenMyPub);
//			System.out.println ("myPub AUTHTOKEN = " + myPubAuthToken.getAuthInfo());
			
			// Creating the parent business entity that will contain our service.
			BusinessEntity myBusEntity = new BusinessEntity();
			Name myBusName = new Name();
			myBusEntity.setBusinessKey("uddi:juddi.apache.org:business");
			
			myBusName.setValue("SIMPL");
			myBusEntity.getName().add(myBusName);
			
			Description description = new Description();
			description.setLang("ger");
			description.setValue("Das ist SIMPL");
			myBusEntity.getDescription().add(description);
			
			
			// Adding the business entity to the "save" structure, using our publisher's authentication info and saving away.
			SaveBusiness sb = new SaveBusiness();
			sb.getBusinessEntity().add(myBusEntity);
			sb.setAuthInfo(rootAuthToken.getAuthInfo());
			BusinessDetail bd = publish.saveBusiness(sb);
			String myBusKey = bd.getBusinessEntity().get(0).getBusinessKey();
			System.out.println("myBusiness key:  " + myBusKey);
			
			//Test Business Details
			
						
			// Creating a service to save.  Only adding the minimum data: the parent business key retrieved from saving the business 
			// above and a single name.
			BusinessService myService = new BusinessService();
			myService.setBusinessKey(myBusKey);
			myService.setServiceKey("uddi:juddi.apache.org:datasource");
			Name myServName = new Name();
			myServName.setValue("Datenquelle1");
			myService.getName().add(myServName);
			// Add binding templates, etc...
			
			// Adding the service to the "save" structure, using our publisher's authentication info and saving away.
			SaveService ss = new SaveService();
			ss.getBusinessService().add(myService);
			ss.setAuthInfo(rootAuthToken.getAuthInfo());
			ServiceDetail sd = publish.saveService(ss);
			String myServKey = sd.getBusinessService().get(0).getServiceKey();
			System.out.println("myService key:  " + myServKey);
			
			//TModel Test
			TModel tmodel = new TModel();
			Description tmodeld = new Description();
			tmodeld.setValue("Xml-DataBase");
			tmodel.getDescription().add(tmodeld);
//			CategoryBag bag = new CategoryBag();
			
//			KeyedReference reference = new KeyedReference();
//			reference.setTModelKey(tmodel.getTModelKey());
//			reference.setKeyName("database");
//			reference.setKeyValue("database");
//			bag.getKeyedReference().add(reference);
//			tmodel.setCategoryBag(bag);
			tmodel.setTModelKey("uddi:juddi.apache.org:xml");
			Name name = new Name();
			name.setValue("uddi.org:xml");
			tmodel.setName(name);
			
			SaveTModel saveTmodel = new SaveTModel();
			saveTmodel.getTModel().add(tmodel);
			saveTmodel.setAuthInfo(rootAuthToken.getAuthInfo());
			
			TModelDetail td = publish.saveTModel(saveTmodel);
			String myTKey = td.getTModel().get(0).getTModelKey();
			System.out.println("myTModel key:   " + myTKey);
			
			
			
			// Now you have a publisher saved who in turn published a business and service via the jUDDI API!
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}		
}

package org.simpl.uddi.client;
import java.util.ArrayList;

public class ClientTest implements IUddiConfig {
	public static void main(String[] args) {
		UddiBusiness business = new UddiBusiness();
		business.addName("SimplBusiness", "ger");
		business.addDescription("Business fuer SIMPL Datasources", "ger");
		business.setKey(KEYPREFIX + "simpl");

		UddiDataWriter dataWriter = UddiDataWriter.getInstance();
		
		dataWriter.writeBusiness(business);

		for (int i = 0; i < 1000; i++) {

			UddiDataSource dataSource = new UddiDataSource(business.getKey());
			dataSource.setAddress("http://source1.datasources.org");
			dataSource.setName("Mysql Datenbank"+i);
			dataSource.setKey(KEYPREFIX + "source_1"+i);
			dataSource.addDescription("Mysql Datenbank", "ger");
			dataSource.addAttribute("type", "database", KEYPREFIX + "type");
			dataSource.addAttribute("subtype", "rdb", KEYPREFIX + "subtype");
			dataSource.addAttribute("wspolicy",
					"http://source1.datasources.org/wspolicy+1", KEYPREFIX
							+ "wspolicy");
			dataWriter.writeDatasource(dataSource);
			System.out.println(dataSource.getKey());
		}

		UddiDataSource dataSource2 = new UddiDataSource(business.getKey());
		dataSource2.setAddress("http://source2.datasources.org");
		dataSource2.setName("Ext 3 File System rene ist doof");
		dataSource2.setKey(KEYPREFIX + "source_2");
		dataSource2.addDescription("Filesystem Ext3", "ger");
		dataSource2.addAttribute("type", "filesystem", KEYPREFIX + "type");
		dataSource2.addAttribute("subtype", "ext3", KEYPREFIX + "subtype");
		dataSource2.addAttribute("wspolicy",
				"http://source2.datasources.org/wspolicy", KEYPREFIX
						+ "wspolicy");

	

		dataWriter.writeDatasource(dataSource2);

		ArrayList<UddiDataSource> ds = UddiDatasourceReader.getInstance()
				.getAllDatasources();

		System.out.println(ds.size());
		// UddiDataSource ds = reader.getByKey(KEYPREFIX + "source_1");

		// System.out.println(ds.getKey());

		System.out.println("done");

	}
}

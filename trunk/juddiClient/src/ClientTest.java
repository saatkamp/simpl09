
import java.util.ArrayList;



public class ClientTest implements IUddiConfig{
	public static void main(String [] args){
		UddiBusiness business = new UddiBusiness();
		business.addName("SimplBusiness", "ger");
		business.addDescription("Business fuer SIMPL Datasources", "ger");
		business.setKey(KEYPREFIX + "simpl");
				
		
		UddiDataSource dataSource = new UddiDataSource(business.getKey());
		dataSource.setAddress("http://datasource1.datasources.de");
		dataSource.setName("Rene ist doof Datenbank");
		dataSource.setKey(KEYPREFIX + "source_1");
		dataSource.addDescription("Mysql Datenbank", "ger");
		dataSource.addAttribute("type", "database", KEYPREFIX + "type");
		dataSource.addAttribute("subtype", "mysql", KEYPREFIX + "subtype");
		dataSource.addAttribute("wspolicy","http://datasource1.datsources.de/policy.xml", KEYPREFIX + "wspolicy");
		
		UddiDataSource dataSource2 = new UddiDataSource(business.getKey());
		dataSource2.setAddress("http://datasource2.datasources.de");
		dataSource2.setName("DataSource 1");
		dataSource2.setKey(KEYPREFIX + "source_2");
		dataSource2.addDescription("Filesystem Ext3", "ger");
		dataSource2.addAttribute("type", "filesystem", KEYPREFIX + "type");
		dataSource2.addAttribute("subtype", "ext3", KEYPREFIX + "subtype");
		dataSource2.addAttribute("wspolicy", "http://datasource2.datsources.de/policy.xml", KEYPREFIX + "wspolicy");
//		
		UddiDataWriter.getInstance().writeBusiness(business);
//		
//		
		UddiDataWriter.getInstance().writeDatasource(dataSource);
////		
////		
////		
		UddiDataWriter.getInstance().writeDatasource(dataSource2);
////		
		ArrayList<UddiDataSource> ds = new ArrayList<UddiDataSource>();	
		
		ds = UddiDatasourceReader.getInstance().getAllDarasources();
		
		System.out.println(ds.size());
		
//		UddiDataSource ds = reader.getByKey(KEYPREFIX + "source_1");
		
//		System.out.println(ds.getKey());
		
		System.out.println("done");
		
	}
}

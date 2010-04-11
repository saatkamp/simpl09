import java.util.ArrayList;



public class ClientTest implements IUddiConfig{
	public static void main(String [] args){
//		UddiBusiness business = new UddiBusiness();
//		business.addName("SimplBusiness", "ger");
//		business.addDescription("Ein Simples Business", "ger");
//		business.setKey("simpl");
//				
//		UDDIService service = new UDDIService(business.getKey());
//		service.addDescription("Eine tolle mysql Datenbank", "ger");
//		service.addName("Database", "ger");
//		service.setKey(KEYPREFIX + "databasekey");
//		
//		
//		UddiDataSource dataSource = new UddiDataSource(service.getKey());
//		dataSource.setAddress("http://rene.ist.doof.de");
//		dataSource.setKey(KEYPREFIX + "source_1");
//		dataSource.addDescription("Mysql Datenbank", "ger");
//		dataSource.addAttribute("type", "database", KEYPREFIX + "type");
//		dataSource.addAttribute("subtype", "mysql", KEYPREFIX + "subtype");
//		dataSource.addAttribute("wspolicy","http://rene.ist.immer.noch.doof.de", KEYPREFIX + "ws-policy");
//		
//		UddiDataSource dataSource2 = new UddiDataSource(service.getKey());
//		dataSource2.setAddress("http://rene.ist.doof.de");
//		dataSource2.setKey(KEYPREFIX + "source_2");
//		dataSource2.addDescription("Mysql Datenbank", "ger");
//		dataSource2.addAttribute("type", "filesystem", KEYPREFIX + "type");
//		dataSource2.addAttribute("subtype", "ext3", KEYPREFIX + "subtype");
//		dataSource2.addAttribute("wspolicy", "http://rene.ist.immer.immer.noch.doof.de", KEYPREFIX + "ws-policy");
//		
//		UddiDataWriter writer = new UddiDataWriter();
//		writer.writeBusiness(business);
//		
//		writer.writeService(service);
//		
//		writer.writeDatasource(dataSource);
//		
//		writer.writeDatasource(dataSource2);
		
		UddiDatasourceReader reader = new UddiDatasourceReader();
		
//		ArrayList<UddiDataSource> ds = new ArrayList<UddiDataSource>();	
		
		UddiDataSource ds = reader.getByKey(KEYPREFIX + "source_1");
		
		System.out.println(ds.getKey());
	}
}

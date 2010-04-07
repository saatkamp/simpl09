

public class clientTest implements IUddiConfig{
	public static void main(String [] args){
		UddiBusiness business = new UddiBusiness();
		business.addName("SimplBusiness", "ger");
		business.addDescription("Ein Simples Business", "ger");
		business.setKey("simpl");
				
		UDDIService service = new UDDIService(business.getKey());
		service.addDescription("Eine tolle mysql Datenbank", "ger");
		service.addName("Database", "ger");
		service.setKey("databasekey");
		
//		UDDIService service2 = new UDDIService(business.getKey());
//		service2.addDescription("Eine tolle mysql Datenbank", "ger");
//		service2.addName("Database", "ger");
//		service2.setKey("databasekey2");
//		service2.writeData();
		
		UddiDataSource dataSource = new UddiDataSource(service.getKey());
		dataSource.setAddress("http://rene.ist.doof.de");
		dataSource.setKey("source_1");
		dataSource.addDescription("Mysql Datenbank", "ger");
		dataSource.setName("Mysql Datenbank 1");
		dataSource.addAttribute("category", "datasource", KEYPREFIX + "category");
		dataSource.addAttribute("type", "database", KEYPREFIX + "type");
		dataSource.addAttribute("subtype", "mysql", KEYPREFIX + "subtype");
		dataSource.addAttribute("wspolicy","http://rene.ist.immer.noch.doof.de", KEYPREFIX + "ws-policy");
		
		UddiDataSource dataSource2 = new UddiDataSource(service.getKey());
		dataSource2.setAddress("http://rene.ist.doof.de");
		dataSource2.setKey("source_2");
		dataSource2.addDescription("Mysql Datenbank", "ger");
		dataSource2.setName("Xml Datenbank");
		dataSource2.addAttribute("category", "datasource", KEYPREFIX + "category");
		dataSource2.addAttribute("type", "filesystem", KEYPREFIX + "type");
		dataSource2.addAttribute("subtype", "ext3", KEYPREFIX + "subtype");
		dataSource2.addAttribute("wspolicy", "http://rene.ist.immer.immer.noch.doof.de", KEYPREFIX + "ws-policy");
		
		UddiDataWriter writer = new UddiDataWriter();
		writer.writeBusiness(business);
		
		writer.writeService(service);
		
		writer.writeDatasource(dataSource);
		
		writer.writeDatasource(dataSource2);
		
		UddiDataReader reader = new UddiDataReader();
		
		System.out.println("done");
	}
}

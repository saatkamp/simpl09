import java.util.ArrayList;



public class ClientTest implements IUddiConfig{
	public static void main(String [] args){
		UddiBusiness business = new UddiBusiness();
		business.addName("SimplBusiness", "ger");
		business.addDescription("Business fuer SIMPL Datasources", "ger");
		business.setKey(KEYPREFIX + "simpl");
				
		
		UddiDataSource dataSource = new UddiDataSource(business.getKey());
		dataSource.setAddress("http://rene.ist.doof.de");
		dataSource.setName("Rene ist doof Datenbank");
		dataSource.setKey(KEYPREFIX + "source_1");
		dataSource.addDescription("Mysql Datenbank", "ger");
		dataSource.addAttribute("type", "database", KEYPREFIX + "type");
		dataSource.addAttribute("subtype", "mysql", KEYPREFIX + "subtype");
		dataSource.addAttribute("wspolicy","http://rene.ist.immer.noch.doof.de", KEYPREFIX + "wspolicy");
		
		UddiDataSource dataSource2 = new UddiDataSource(business.getKey());
		dataSource2.setAddress("http://rene.ist.doof.de");
		dataSource2.setName("Rene ist ganz ganz ganz dolle doof filesystem");
		dataSource2.setKey(KEYPREFIX + "source_2");
		dataSource2.addDescription("Filesystem Ext3", "ger");
		dataSource2.addAttribute("type", "filesystem", KEYPREFIX + "type");
		dataSource2.addAttribute("subtype", "ext3", KEYPREFIX + "subtype");
		dataSource2.addAttribute("wspolicy", "http://rene.ist.immer.immer.noch.doof.de", KEYPREFIX + "wspolicy");
//		
		UddiDataWriter writer = new UddiDataWriter();
		
		writer.writeBusiness(business);
		
		
		System.out.println(business.getKey());
		writer.writeDatasource(dataSource);
//		
//		
//		
		writer.writeDatasource(dataSource2);
//		
		UddiDatasourceReader reader = new UddiDatasourceReader();
		
		ArrayList<UddiDataSource> ds = new ArrayList<UddiDataSource>();	
		
		ds = reader.getAllDarasources();
		
		System.out.println(ds.size());
		
//		UddiDataSource ds = reader.getByKey(KEYPREFIX + "source_1");
		
//		System.out.println(ds.getKey());
		
		System.out.println("done");
		
	}
}

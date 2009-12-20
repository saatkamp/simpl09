package adminService;
import java.sql.*;
import java.util.*;


public class adminServiceTest {
	public static void main(String args[])throws Exception{
		adminService as=new adminService();
		Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
//		Connection conn=DriverManager.getConnection("jdbc:derby:mydb;create=true");
//		Statement st=conn.createStatement();
//		st.execute("create table adminconsole(id varchar(10) primary key,use varchar(10),password varchar(20))");
//		System.out.println("erstelle die db adminconsole");
		
		HashMap<String, String> settings=new HashMap();
//		settings.put("id", "default");
//		settings.put("use", "'qiyan'");
//		settings.put("password", "'tuxi922'");
		settings=as.load(null, "adminconsole", "'preset'");
		
//		as.save(null, "adminconsole", "'preset'", settings);
	}

}
class adminService{
	public boolean save(String schema,String table,String settingName,HashMap<String,String> settings) throws SQLException{
		try{Set set=settings.entrySet();
		Iterator i=set.iterator();
		//erstelle eine view und pointer fuer die HashMap obj; 
		String key="id";
	    String value=settingName;
	    while(i.hasNext()){
       		Map.Entry me=(Map.Entry) i.next();
       		key+="," + me.getKey();
       		value+="," + me.getValue();
       		System.out.println(key);System.out.println("wert ist "+ value);
       		        	}
        //liest die Schluesselwort und seine Wert von HashMap obj aus
		Connection conn=null;
		conn=DriverManager.getConnection("jdbc:derby:mydb;create=true");
		Statement stat=conn.createStatement();
		ResultSet ts=stat.executeQuery("select id from "+table+" where id="+settingName);
		//suche die gewueschete Werte aus,und speichern die in eine ResultSet obj;
		if(ts.next()){
    	stat.executeUpdate("update "+table+" set password='ttx123' where id="+settingName);
		System.out.println("update fertig.");
		
		}else{
			stat.execute("insert into "+table+" values ( "+value+" )");
	    	System.out.println("insert adminconsole fertig");	
		}
		}catch(Throwable e){
		e.printStackTrace();}
				
	    
	    return true;
	
		}
	
	public HashMap<String, String> load(String schema, String table, String settingName) throws Exception{
		HashMap<String, String> settings=new HashMap();
		try{
		Connection conn=null;
	    conn=DriverManager.getConnection("jdbc:derby:mydb;create=true");
	    Statement stat=conn.createStatement();
	    ResultSet rs=stat.executeQuery("select * from "+table+" where id="+settingName);
	    ResultSetMetaData meta=rs.getMetaData();
	    //zuerst suchen die gewueschte Data aus,and speichern die in eine ResultSet obj;
	    //danach erhaltet man durch die ResultSetMetaData obj die Attributename. 
	    for(int i=1;i<=meta.getColumnCount();i++){
	    	System.out.println(meta.getColumnName(i));
	    	
	    }
	    while(rs.next()){
		for(int i=1;i<=meta.getColumnCount();i++){
		settings.put(meta.getColumnName(i), rs.getString(i));
		
		//erstellen die Maping zwischen key und value;
		}
	    }
		
		}catch(Throwable e){
			e.printStackTrace();}
		return settings;//Einstellungen als HashMap;}
	   	}
}
	
	
	
	
	                                         

	
	

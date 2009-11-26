import java.sql.*;
import java.util.*;
//derby demo;
public class firstDB {
	public static void main(String[] args){
		try{//load the driver
			Class.forName("org.apache.derby.jdbc.EmbeddedDriver").newInstance();
			System.out.println("Load the Embedded drive");
			Connection conn=null;
						
			//create and connected the datenbank named helloDB;
			conn=DriverManager.getConnection("jdbc:derby:hellodb;create=true");
			System.out.println("create and connect to helloDB"); 
			conn.setAutoCommit(false);	
			
			//create a table and 2 records;
			Statement s=conn.createStatement();
			s.execute("create table simpl(id int primary key,name varchar(20))");
			System.out.println("Create table simpl");
			s.execute("insert into simpl values(1,'Tu')");
			s.execute("insert into simpl values(2,'Xi')");
			
			//liest die Records aus;
			ResultSet rs=s.executeQuery("select * from simpl");
			System.out.println("id+name");
			while(rs.next()){
				System.out.print(rs.getString("id")+" " +rs.getString("name"));
				System.out.println();
						}
			//delete simpl;
			//s.execute("drop table simpl");
			//System.out.println("delete die Table");
		}catch(Throwable e){
			//handle the exception;
		} 
	}

}

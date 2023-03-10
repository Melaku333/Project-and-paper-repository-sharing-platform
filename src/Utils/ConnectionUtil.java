package Utils;

import java.sql.*;

public class ConnectionUtil {

	  Connection connected=null;
	  
	  public static Connection conDB() {
		  try {
			  String dbUrl="jdbc:mysql://localhost:3306/projectrepository";
				String username="root";
				String password="";
			  Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection(dbUrl,username,password);
			
			return con;
			  
		  } catch(Exception ex) {
			  System.err.println("Connection util"+ex.getMessage());
			  return null;  
		  } 
		  
	  }
	 
}

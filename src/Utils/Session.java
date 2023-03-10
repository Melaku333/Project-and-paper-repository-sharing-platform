package Utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Session {
	
public static String Sessionuser ;

	
	public static String SessionUser() {
		
		return Sessionuser;
			
	}
public static void SetSessionUser(String name) {
		
	 Sessionuser=name;
			
	}

public static String typeOfUser() {
	String userName = null;
	String userName2=null;
	Connection con=ConnectionUtil.conDB();
	String sql = "select * from users where email=?";
   	PreparedStatement preparedStatement = null;
   	ResultSet resulSet=null;
	try {
   		preparedStatement = con.prepareStatement(sql);
   		preparedStatement.setString(1,SessionUser());
   	} catch (SQLException e) {
   		System.out.println("Error while preparing the statement to con"+e);
   	}
   	try {
   		 resulSet = preparedStatement.executeQuery();
   	} catch (SQLException e) {
   		System.out.println("Error while excuting the query"+e);
   	}
   	
   	try {
		while(resulSet.next()) {
			
			 userName=resulSet.getString("email");
			
			
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
   	
   	String sql1 = "select * from Admins where email=?";

	try {
   		preparedStatement = con.prepareStatement(sql1);
   		preparedStatement.setString(1,SessionUser());
   	} catch (SQLException e) {
   		System.out.println("Error while preparing the statement to con"+e);
   	}
   	try {
   		 resulSet = preparedStatement.executeQuery();
   	} catch (SQLException e) {
   		System.out.println("Error while excuting the query"+e);
   	}
   	
   	try {
		while(resulSet.next()) {
			
			 userName2=resulSet.getString("email");
			
			
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
   	
   	if(userName==null && userName2!=null)
   	{ 
   		return "Admin";
   		
   	}
   	else if(userName!=null && userName2==null) {
   		System.out.println("It is working");
   		return "User";
   	}
	return null;
}

}

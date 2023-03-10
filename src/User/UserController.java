package User;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import Home.DataController;
import Utils.ConnectionUtil;
import Utils.Session;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class UserController implements Initializable {

String email;
String password;
	Connection	con;
	ResultSet result;
    @FXML
    private Button btnSignOut;
    @FXML
    private Button btnUpdatePassword;
    
    @FXML
    private Label lblName;
    @FXML
    private Label lblError;
    

    @FXML
    private Label lblPassword;

    @FXML
    private VBox vb;
    @FXML
    private PasswordField txtpass;
    PreparedStatement preparedStatement = null;
    ResultSet resulSet = null;
    public int id;
    public String emailp;
    public String projectName;
    public String Description;
    public String file;
    @FXML
    private PasswordField txtpassConfirm;
    String pass1;
    String pass2;
    @FXML
    void SignUp(ActionEvent event) {
    	if (event.getSource() == btnUpdatePassword) {  
    		System.out.println("Clicked");
        pass1=txtpass.getText();
        	pass2=txtpassConfirm.getText();
        System.out.println(pass1+pass2);
        PreparedStatement preparedStatement = null;
        int resulSet = 0;
        	if(pass1!=pass2) {
        		String sql = "update users set  password=? where email=?";
        		
        		try {
        			 preparedStatement = con.prepareStatement(sql);
        		preparedStatement.setString(1, pass1);
        		preparedStatement.setString(2, Session.SessionUser());}
        		
        			catch(Exception e) {
        				System.out.println("Error preparing"+e);
        			}
        			try {
        		
				resulSet =preparedStatement.executeUpdate();}
        		catch(Exception e) {
    				System.out.println("Error while updating "+e);
    			}
        			try {
        		if(resulSet!=0)
        			lblError.setText("Successfully updated");
        	}catch(Exception e) {
        	System.out.println("error in result set "+e);	
        	}
        	}
        else if(pass1==pass2 || pass1==null || pass2==null) {
        		lblError.setText("The two passwords must be the same");
        	}}
    }
    
    void UpdatePassword() {
    	}
    	
    public void put() throws IOException {
   	 String sql = "select * from projects where email=?";
   	try {
   		preparedStatement = con.prepareStatement(sql);
   		preparedStatement.setString(1, Session.SessionUser());
   	} catch (SQLException e) {
   		System.out.println("Error while preparing the statement to con"+e);
   	}
   	try {
   		resulSet = preparedStatement.executeQuery();
   	} catch (SQLException e) {
   		System.out.println("Error while excuting the query"+e);
   	}
   	
   	   try {
   		   
   		   
   		while (resulSet.next()) {
   			
   			 id=resulSet.getInt("ID");
   			emailp=resulSet.getString("email");
   			projectName=resulSet.getString("projectName");
   			Description=resulSet.getString("Description");
   			file=resulSet.getString("file");
   			
   			UserDataController.getter(id, emailp, projectName, Description, file);
   			
   		 	Parent root =FXMLLoader.load(getClass().getResource("Data.fxml"));
   		 	
   		 	Pane m=new Pane(root);
   		     vb.getChildren().add(m);
   		 }
   		
   		
   	} catch (SQLException e) {
   		System.out.println("Error while fetching from result (SQL)+e");
   	} catch (IOException e) {
   		System.out.println("Error in loading Data.fxml "+e);
   	}
   }
    
    public void putmain() throws IOException {
   	 String sql = "select * from users where email=?";
   	 
   		 PreparedStatement prepstmt = null;
   		try {
   			prepstmt = con.prepareStatement(sql);
   			prepstmt.setString(1, Session.SessionUser());
   			
   		} catch (SQLException e1) {
   			System.out.println("Error while setting prepare");
   		}
   		
   		
   		try {
   			 result = prepstmt.executeQuery();
   		} catch (SQLException e1) {
   			System.out.println("Error while saving " +e1);
   		}


   	   try {
   		   
   		   
   		while (result.next()) {

   			email=result.getString("email");
   			password=result.getString("password");
   		
   			lblName.setText(email);
   			lblPassword.setText(password);
   		 }
   		
   		
   	} catch (SQLException e) {
   		System.out.println("Error while fetching from result (SQL)+e");
   	}
   }
     
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		con=ConnectionUtil.conDB();
		try {
			putmain();
			put();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

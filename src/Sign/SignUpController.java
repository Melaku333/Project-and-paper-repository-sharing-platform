package Sign;

import java.awt.Window;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

import Utils.ConnectionUtil;
import Utils.Session;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.control.PasswordField;


public class SignUpController implements Initializable {

    @FXML
    private Button btnSignIn;

    @FXML
    private Button btnSignUp;

    @FXML
    private TextField txtCompany;

    @FXML
    private TextField txtDate;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtFname;

    @FXML
    private TextField txtJobTitle;

    @FXML
    private TextField txtLname;

    @FXML
    private TextField txtMonth;

    @FXML
    private TextField txtPassword;

    @FXML
    private TextField txtPasswordConfirm;

    @FXML
    private TextField txtPhoneNo;

    @FXML
    private TextField txtYear;

	@FXML
	private Label lblError;
    
    
    Connection con = null;
	
    public SignUpController() {
 		con=ConnectionUtil.conDB();
 		
 		
 	}
    
@FXML  
public void handleButton(ActionEvent event) {
		
	
	if (event.getSource() == btnSignUp) {
		
        if( signUp().equals("Success")) {
     	   try { 
     	   Node node=(Node) event.getSource();
     	   Stage stage=(Stage) node.getScene().getWindow();
     	   stage.close();
     	   
     	  
				Scene scene=new Scene(FXMLLoader.load(getClass().getResource("/Home/HomeIn.fxml")));
				stage.setScene(scene);
				stage.show();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				System.err.println(e.getMessage());
			}
     	   
        }
		}
	else if(event.getSource()==btnSignIn) {
		
		   try { 
	     	   Node node=(Node) event.getSource();
	     	   Stage stage=(Stage) node.getScene().getWindow();
	     	   stage.close();
	     	   
	     	  
					Scene scene=new Scene(FXMLLoader.load(getClass().getResource("SignIn.fxml")));
					stage.setScene(scene);
					stage.show();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					System.err.println(e.getMessage());
				}
	}
		else {
			lblError.setTextFill(Color.TOMATO);
			lblError.setText("Sign up was unseccessful.Please try again");
		}

		
		
}
    

@FXML
public void Back(MouseEvent event) {
	 
		
		   try { 
	     	   Node node=(Node) event.getSource();
	     	   Stage stage=(Stage) node.getScene().getWindow();
	     	   stage.close();
	     	   
	     	  
					Scene scene=new Scene(FXMLLoader.load(getClass().getResource("/Home/HomeFxml.fxml")));
					stage.setScene(scene);
					stage.show();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					System.err.println(e.getMessage());
				}
	}
	 
    
    
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}
	
	

	PreparedStatement preparedStatement = null;
	ResultSet resulSet = null;

	private String signUp() {
		String Fname = txtFname.getText().toString();
		String Lname=txtLname.getText().toString();
		String password=txtPassword.getText().toString();
		String email=txtEmail.getText().toString();
		int phoneNo= Integer.parseInt(txtPhoneNo.getText());
		int year=Integer.parseInt(txtYear.getText()); 
		int month=Integer.parseInt(txtMonth.getText()); 
		int date= Integer.parseInt(txtDate.getText());
		String company=txtCompany.getText().toString();
		String jobTitle= txtJobTitle.getText().toString();
		
	

		String sql = "insert into users (Fname,Lname,email,PhoneNo,password,BirthDate,company,JobTitle)values(?,?,?,?,?,?,?,?)";
		try {
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setString(1, Fname);
			preparedStatement.setString(2, Lname);
			preparedStatement.setString(3, email);
			preparedStatement.setInt(4, phoneNo);
			preparedStatement.setString(5, password);
			
			String BirthDate=year+"-"+month+"-"+date;
			preparedStatement.setString(6, BirthDate);
			
			preparedStatement.setString(7, company);
			
			preparedStatement.setString(8, jobTitle);
			
			
		int resulSet =preparedStatement.executeUpdate();
			if (resulSet<=0) {
				lblError.setTextFill(Color.TOMATO);
				lblError.setText("Please check all your information and try again");
				return "Error";

			}

			else {
				lblError.setTextFill(Color.GREEN);
				lblError.setText("Sign up successful");
				Session.SetSessionUser(email);
				return "Success";
			}

		} catch (Exception e) {
			System.err.println(e.getMessage());
			return "Exception";
		}

	}




}

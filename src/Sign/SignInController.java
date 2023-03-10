package Sign;


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

public class SignInController implements Initializable {
	@FXML
	private TextField txtUsername;
	@FXML
	private PasswordField txtPassword;
	@FXML
	private Label lblErrors;
	@FXML
	private Button btnSignin;
	@FXML
	private Button btnForgot;
	@FXML
	private Button btnSignup;

	public void handleButtononAction(MouseEvent event) {
		
		if (event.getSource() == btnSignin) {
			 
           if( login().equals("Success")) {
        	   
        	   try { 
        	   Node node=(Node) event.getSource();
        	   Stage stage=(Stage) node.getScene().getWindow();
        	  
        	   
        	  
				Scene scene=new Scene(FXMLLoader.load(getClass().getResource("/Home/HomeIn.fxml")));
				stage.setScene(scene);
				stage.show();
			} catch (Exception e) {
			
			}
        	   
           }
		}}
	@FXML
	public void SignUp(MouseEvent event) {
		 
			   try { 
		     	   Node node=(Node) event.getSource();
		     	   Stage stage=(Stage) node.getScene().getWindow();
		     	   
		     	   
		     	  
						Scene scene=new Scene(FXMLLoader.load(getClass().getResource("SignUp.fxml")));
						stage.setScene(scene);
						stage.show();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						System.err.println(e.getMessage());
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
	Connection con = null;
	public SignInController() {
		
		con=ConnectionUtil.conDB();
	}
	
	
	

	PreparedStatement preparedStatement = null;
	ResultSet resulSet = null;
	PreparedStatement preparedStatement1 = null;
	ResultSet resulSet1 = null;

	private String login() {
		String email = txtUsername.getText().toString();
		String password = txtPassword.getText().toString();

		String sql = "select * from users where email= ? and password= ?";
		String sql1 = "select * from admins where email= ? and password= ?";
		try {
			preparedStatement = con.prepareStatement(sql);
			preparedStatement1 = con.prepareStatement(sql1);
			preparedStatement.setString(1, email);
			preparedStatement.setString(2, password);
			preparedStatement1.setString(1, email);
			preparedStatement1.setString(2, password);
			resulSet = preparedStatement.executeQuery();
			resulSet1 = preparedStatement1.executeQuery();
			if (!resulSet.next() && !resulSet1.next()) {
				lblErrors.setTextFill(Color.TOMATO);
				lblErrors.setText("Enter correct Email or password");
				System.err.println("Wrong logins");
				return "Error";

			}

			else {
				lblErrors.setTextFill(Color.GREEN);
				lblErrors.setText("Login successful");
				System.out.println("Successful logins");
				Session.SetSessionUser(email);
				return "Success";
			}

		} catch (Exception e) {
			System.err.println(e.getMessage());
			return "Exception";
		}

	}



}

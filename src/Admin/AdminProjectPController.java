package Admin;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import Utils.ConnectionUtil;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AdminProjectPController implements Initializable{
	static String ID;
    @FXML
    private Label lblDesc;

    @FXML
    private Label lblOwner;

    @FXML
    private Label lblProjectName;

    @FXML
    private VBox vbFolder;
    
   String email;
   String projectName;
   String Description;
   String file;
    PreparedStatement preparedStatement = null;
	ResultSet resulSet = null;
    public static void getter(String id) {
        ID=id;	
        }
	@FXML
	public void Back(MouseEvent event) {
		 
			
			   try { 
		     	   Node node=(Node) event.getSource();
		     	   Stage stage=(Stage) node.getScene().getWindow();
		     	   stage.close();
		     	   
		     	  
						Scene scene=new Scene(FXMLLoader.load(getClass().getResource("HomeIn.fxml")));
						stage.setScene(scene);
						stage.show();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						System.err.println(e.getMessage());
					}
		}
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		Connection con=ConnectionUtil.conDB();
		 String sql = "select * from projects where ID="+ID;
			try {
				preparedStatement = con.prepareStatement(sql);
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
					
					 
					email=resulSet.getString("email");
					projectName=resulSet.getString("projectName");
					Description=resulSet.getString("Description");
					file=resulSet.getString("file");}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
				   
				   
				
		
		
		lblProjectName.setText(projectName);
		lblOwner.setText(email);
		lblDesc.setText(Description);
	}

}

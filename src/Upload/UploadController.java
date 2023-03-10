package Upload;

import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

import Utils.ConnectionUtil;
import Utils.Session;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.Labeled;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class UploadController implements Initializable {

   @FXML
    private Button btnAdd;
    @FXML
    private Button btnCancel;
    @FXML
    private Button btnUpload;
    @FXML
    private Label lblError;
    @FXML
    private TextArea txtDesc;
    @FXML
    private TextField txtName;
    @FXML
    private ChoiceBox<String> txtCategory;

    private File UploadedFile=null;
    Connection con = null;
	PreparedStatement preparedStatement = null;
	ResultSet resulSet = null;
	String fileName=null;
	 String cat=null;
	 String ProjectName=null;
  
    @FXML  
    public void handleButton(ActionEvent event) {
    	
    if(event.getSource() == btnAdd) {
    	 Node node=(Node) event.getSource();
   	   Stage stage=(Stage) node.getScene().getWindow();
   	   
   	   DirectoryChooser dirChooser=new DirectoryChooser();
   	   File fil=dirChooser.showDialog(stage);

		this.UploadedFile=fil;
		 fileName =UploadedFile.getAbsolutePath();
		btnAdd.setText(fileName);
    }	
    else if (event.getSource() == btnUpload) {
    		
    	 ProjectName = txtName.getText().toString();	
    		cat= txtCategory.getValue();
    		if(UploadedFile!=null)
    	 fileName =UploadedFile.getAbsolutePath();
 
    		if(fileName==null  ) {
				lblError.setTextFill(Color.TOMATO);

       		 lblError.setText("you have to choose a file");
       	    } 
    		else if(ProjectName==null ) {
				lblError.setTextFill(Color.TOMATO);

        		 lblError.setText("Project name can not be null");
        	 }
    		else if(cat==null  ) {
     				lblError.setTextFill(Color.TOMATO);

        		 lblError.setText("Catagory must be choosen");
        	 }
        	 else {
        		 
        		 
        		 if( UploadProject().equals("Success")) {
        	     	   try { 
        	     	   Node node=(Node) event.getSource();
        	     	   Stage stage=(Stage) node.getScene().getWindow();
        	     	  	Scene scene=new Scene(FXMLLoader.load(getClass().getResource("/Home/HomeIn.fxml")));
        					stage.setScene(scene);
        					stage.show();
        					
        				} catch (Exception e) {
        					// TODO Auto-generated catch block
        					System.err.println(e.getMessage());
        				}
        	     	   
        	        }
        		   		 
         }
    		
    		
    	}	
    		
    	else if(event.getSource()==btnCancel) {
    		if( Session.SessionUser()!=null) {
    		   try { 
    	     	   Node node=(Node) event.getSource();
    	     	   Stage stage=(Stage) node.getScene().getWindow();
    	     	 
    	     	   
    	     	 
    					Scene scene=new Scene(FXMLLoader.load(getClass().getResource("/Home/HomeIn.fxml")));
    					stage.setScene(scene);
    					stage.show();
    				} catch (Exception e) {
    					// TODO Auto-generated catch block
    					System.err.println(e.getMessage());
    				}
    				
    		   }
    		else
    		{ 
    			try { 
    			Node node=(Node) event.getSource();
 	     	   Stage stage=(Stage) node.getScene().getWindow();
 	     	 
 	     	   
 	     	 
 					Scene scene=new Scene(FXMLLoader.load(getClass().getResource("/Home/HomeFxml.fxml")));
 					stage.setScene(scene);
 					stage.show();
 				} catch (Exception e) {
 					// TODO Auto-generated catch block
 					System.err.println(e.getMessage());
 				}
 				
    			
    		}
    		   
    	}
    		

    		
    		
    }
 
	private String UploadProject() {
		String ProjectName = txtName.getText().toString();
		String cat=txtCategory.getValue();
		String Description= txtDesc.getText().toString();
		
	

		String sql = "insert into projects (email,ProjectName,	Description,file,category) values(?,?,?,?,?)";
		try {Session.SessionUser();
		
			preparedStatement = con.prepareStatement(sql);
			
			preparedStatement.setString(1,Session.SessionUser() );
			preparedStatement.setString(2, ProjectName);
			preparedStatement.setString(3, Description);
			
			
			preparedStatement.setString(4, fileName);
			preparedStatement.setString(5, cat);
			
		
			
			
		int resulSet =preparedStatement.executeUpdate();
			if (resulSet<=0) {
				lblError.setTextFill(Color.TOMATO);
				lblError.setText("Please check all your information and try again");
				return "Error";

			}

			else {
				lblError.setTextFill(Color.GREEN);
				lblError.setText("Upload successful");
				return "Success";
			}

		} catch (Exception e) {
			System.err.println(e.getMessage());
			return "Exception";
		}

	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		con=ConnectionUtil.conDB();
		String st[] = { "None","Technology", "Marketing", "Health", "Enginering","Humanities and Art","Physics","Mathimatics"};
        txtCategory.getItems().addAll(st);
	}
}


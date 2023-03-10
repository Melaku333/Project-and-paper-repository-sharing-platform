package User;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import Utils.ConnectionUtil;
import Utils.Session;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class UserDataController implements Initializable {

	PreparedStatement preparedStatement = null;
	ResultSet resulSet = null;
	Connection con;

	@FXML
    private Text txtDescription;

    @FXML
    private Text txtProject;

    @FXML
    private Text txtUser;

    @FXML
    private TextField txtComment;
    @FXML
    private Button btnExpand;
    @FXML
    private Button btnUpdate;
    @FXML
    private Button btnDelete;
    @FXML
    private TextArea txtArea;
    @FXML
    private VBox txtCommentArea;
    @FXML
    private VBox vb;
    @FXML
    private HBox hb;
    @FXML
    private Label ProjectId;
    @FXML
    private Label   lblError;
    @FXML
    private Button btnLike;
    @FXML
    private Button btnSignout;
    
    @FXML
    private Button btnDownload;
    @FXML
    private Button btnComment;

    public static String id;
    public static String email;
    public static String projectName;
    public static String Description;
    public static String file;
    String ID;
    String owner;
    String Desc;
    int liked=0;
    int likeno;
    
    int CommentClicked=0;
    
@FXML  
public void handleButton(ActionEvent event) {
		   
     	   try { 
     		  ID=ProjectId.getText();
     		 UserProjectPController.getter(ID);

     	   Node node=(Node) event.getSource();
     	   Stage stage=(Stage) node.getScene().getWindow();
     	   
     	   
     	 
				Scene scene=new Scene(FXMLLoader.load(getClass().getResource("ProjectP.fxml")));
				stage.setScene(scene);
				stage.show();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				System.err.println(e.getMessage());
			}
	
		 
}

@FXML
public void Buttons(ActionEvent event) {
	ID=ProjectId.getText();
	int idd=Integer.parseInt(ID);
	if (event.getSource() == btnDelete) {
			String sql = "delete from projects where ID=?";
			try {
				preparedStatement = con.prepareStatement(sql);
				preparedStatement.setInt(1, idd);	
	
			int resulSet =preparedStatement.executeUpdate();
			if(resulSet!=0)
				System.out.println("Deleted successfuly");
			 
		 }catch(Exception e) {
			 System.out.println("Error while inserting message to the database "+e);}
		 }else if(event.getSource() == btnSignout) {
			   
		  	   try { 
		  	   Node node=(Node) event.getSource();
		  	   Stage stage=(Stage) node.getScene().getWindow();
		  	   
		  	   
		  	
						Scene scene=new Scene(FXMLLoader.load(getClass().getResource("HomeFxml.fxml")));
						stage.setScene(scene);
						stage.show();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						System.out.println("Error while loading fxml");
					}
		

	    }
}		   

    private void comment( ) throws SQLException, IOException {
 
    	UserCommentController.setter(ProjectId.getText());

	 	Parent root =FXMLLoader.load(getClass().getResource("comment.fxml"));
	 	
	 	Pane m=new Pane(root);
	 	 if(CommentClicked==1)
	     vb.getChildren().add(m);
	 	 else if(CommentClicked==0)
	 		 vb.getChildren().remove(m);
	    
    }
    

	public static void getter(int idu,String emailu,String projectNameu,String Descriptionu,String fileu) {
    	
    	id=Integer. toString(idu);
    	email=emailu;
    	projectName=projectNameu;
    	Description=Descriptionu;
    	file=fileu;
			
    }
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		 con=ConnectionUtil.conDB();

		ProjectId.setText(id);
		
		txtProject.setText(projectName);
		
		txtUser.setText(email);
		
		txtArea.setText(Description);
		
		
		
	}
 
    
}




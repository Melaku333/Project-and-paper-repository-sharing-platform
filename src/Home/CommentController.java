package Home;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import Utils.ConnectionUtil;
import Utils.Session;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class CommentController implements Initializable  {
	PreparedStatement preparedStatement = null;
	ResultSet resulSet = null;
	Connection con;

    public static int id;
    public static String email;
    public static String projectName;
    public static String Description;
    public static String file;
    ResultSet done;
	    @FXML
	    private Button btnAdd;

	    @FXML
	    private HBox hb;

	    @FXML
	    private Label lblError;

	    @FXML
	    private TextField txtComment;

	    @FXML
	    private VBox txtCommentArea;

	    static int  idd;
	    int CommentClicked=0;
 static String  ID;
 String comm;
    public static void setter(String IDu) {
    	
    	ID=IDu;
     idd=Integer.parseInt(ID);
    }
 public void addcomment() {
	 if(Session.SessionUser()!=null) {
	String commented= txtComment.getText();
	

	String sql = "insert into comments (projectid,userid,comment)values(?,?,?)";
	try {
		preparedStatement = con.prepareStatement(sql);
		preparedStatement.setInt(1, idd);	
		preparedStatement.setString(2, Session.SessionUser());	
		preparedStatement.setString(3, commented);		
	int resulSet =preparedStatement.executeUpdate();
	if(resulSet!=0)
		System.out.println("inserted successfuly");
	 
 }catch(Exception e) {
	 System.out.println("Error while inserting message to the database "+e);}
 }else {
	 lblError.setText("You have to sign in or sign up to add a comment");
 }
 }
public void put() throws IOException {
	 String sql = "select * from comments where projectid="+idd;
		 PreparedStatement prepstmt = null;
		try {
			prepstmt = con.prepareStatement(sql);
		} catch (SQLException e1) {
			System.out.println("Error while setting prepare");
		}
		
		
		try {
			 done = prepstmt.executeQuery();
		} catch (SQLException e1) {
			System.out.println("Error while saving");
		}


	   try {
		   
		   
		while (done.next()) {

			email=done.getString("userid");
			comm=done.getString("comment");
		
Label m=new Label(email+" : "+comm);
		 	txtCommentArea.getChildren().add(m);
		 }
		
		
	} catch (SQLException e) {
		System.out.println("Error while fetching from result (SQL)+e");
	}
}
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
    con=ConnectionUtil.conDB();
		try {
			put();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
    
}

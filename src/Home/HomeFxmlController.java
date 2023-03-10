package Home;



import java.awt.Window;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import Utils.ConnectionUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ScrollPane;


public class HomeFxmlController implements Initializable {
    @FXML
    private Button btnEnginering;

    @FXML
    private Button btnFeedSetting;

    @FXML
    private Button btnHealth;

    @FXML
    private Button btnHistory;

    @FXML
    private Button btnHumanitiesAndArt;

    @FXML
    private Button btnLibrary;

    @FXML
    private Button btnMarketing;

    @FXML
    private Button btnMathimatics;

    @FXML
    private Button btnPhysics;

    @FXML
    private Button btnSearch;

    @FXML
    private Button btnSetting;

    @FXML
    private Button btnSignIn;

    @FXML
    private Button btnSignUp;

    @FXML
    private Button btnTechnology;
    
    @FXML
    private Button btnAll;
    
    @FXML
    private VBox vb;
    @FXML
    private ScrollPane vbp;
    @FXML
    private TextField txtSearch;
    
    public int id;
    public String email;
    public String projectName;
    public String Description;
    public String file;
    
    PreparedStatement preparedStatement = null;
	ResultSet resulSet = null;
    
    
    
    Connection con = null;
	public HomeFxmlController() {
		
		con=ConnectionUtil.conDB();
	}
	

public void put() throws IOException {
	 String sql = "select * from projects";
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
			
			 id=resulSet.getInt("ID");
			email=resulSet.getString("email");
			projectName=resulSet.getString("projectName");
			Description=resulSet.getString("Description");
			file=resulSet.getString("file");
			
			DataController.getter(id, email, projectName, Description, file);
			
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

@Override
public void initialize(URL arg0, ResourceBundle arg1) {
	
	 try {	
		put();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		System.out.println("Can't initialize");
	}	
}
    
@FXML
public void SignIn(ActionEvent event) {
	 Node node=(Node) event.getSource();
	   Stage stage=(Stage) node.getScene().getWindow();
	   stage.close();
	   
	 Scene scene = null;
	try {
		scene = new Scene(FXMLLoader.load(getClass().getResource("/Sign/SignIn.fxml")));
	} catch (IOException e) {
		System.out.println("Error while creating the scene "+e);
	}
		stage.setScene(scene);
		stage.show();
		
 }

@FXML
public void SignUp(ActionEvent event) {
	 Node node=(Node) event.getSource();
	   Stage stage=(Stage) node.getScene().getWindow();
	   stage.close();
	   
	 Scene scene = null;
	try {
		scene = new Scene(FXMLLoader.load(getClass().getResource("/Sign/SignUp.fxml")));
	} catch (IOException e) {
		System.out.println("Error while creating the scene "+e);
	}
		stage.setScene(scene);
		stage.show();
		
}

public void Category(ActionEvent event) throws IOException {
	
if (event.getSource() == btnTechnology) {
	
	vb.getChildren().clear();

	String sql = "select * from projects where Category='Technology'";

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
			
			 id=resulSet.getInt("ID");
			email=resulSet.getString("email");
			
			projectName=resulSet.getString("projectName");
			Description=resulSet.getString("Description");
			file=resulSet.getString("file");
			
			DataController.getter(id, email, projectName, Description, file);
			
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
else if (event.getSource() == btnMarketing) {

	
	vb.getChildren().clear();

	String sql = "select * from projects where Category='Marketing'";

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
			
			 id=resulSet.getInt("ID");
			email=resulSet.getString("email");
			
			projectName=resulSet.getString("projectName");
			Description=resulSet.getString("Description");
			file=resulSet.getString("file");
			
			DataController.getter(id, email, projectName, Description, file);
			
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
else if (event.getSource() == btnHealth) {
	

	
	vb.getChildren().clear();

	String sql = "select * from projects where Category='Health'";

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
			
			 id=resulSet.getInt("ID");
			email=resulSet.getString("email");
			
			projectName=resulSet.getString("projectName");
			Description=resulSet.getString("Description");
			file=resulSet.getString("file");
			
			DataController.getter(id, email, projectName, Description, file);
			
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
else if (event.getSource() == btnEnginering) {
	

	
	vb.getChildren().clear();

	String sql = "select * from projects where Category='Enginering'";

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
			
			 id=resulSet.getInt("ID");
			email=resulSet.getString("email");
			
			projectName=resulSet.getString("projectName");
			Description=resulSet.getString("Description");
			file=resulSet.getString("file");
			
			DataController.getter(id, email, projectName, Description, file);
			
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
else if (event.getSource() == btnHumanitiesAndArt) {

	
	vb.getChildren().clear();

	String sql = "select * from projects where Category='HumanitiesAndArt'";

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
			
			 id=resulSet.getInt("ID");
			email=resulSet.getString("email");
			
			projectName=resulSet.getString("projectName");
			Description=resulSet.getString("Description");
			file=resulSet.getString("file");
			
			DataController.getter(id, email, projectName, Description, file);
			
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
else if (event.getSource() == btnPhysics) {

	
	vb.getChildren().clear();

	String sql = "select * from projects where Category='Physics'";

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
			
			 id=resulSet.getInt("ID");
			email=resulSet.getString("email");
			
			projectName=resulSet.getString("projectName");
			Description=resulSet.getString("Description");
			file=resulSet.getString("file");
			
			DataController.getter(id, email, projectName, Description, file);
			
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
else if (event.getSource() == btnMathimatics) {
	

	
	vb.getChildren().clear();

	String sql = "select * from projects where Category='Mathimatics'";

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
			
			 id=resulSet.getInt("ID");
			email=resulSet.getString("email");
			
			projectName=resulSet.getString("projectName");
			Description=resulSet.getString("Description");
			file=resulSet.getString("file");
			
			DataController.getter(id, email, projectName, Description, file);
			
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
else if (event.getSource() == btnSearch) {
	
	String search = txtSearch.getText().toString();
	
	vb.getChildren().clear();

String sql = "select * from projects where projectName='search'";

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
			
			 id=resulSet.getInt("ID");
			email=resulSet.getString("email");
			
			projectName=resulSet.getString("projectName");
			Description=resulSet.getString("Description");
			file=resulSet.getString("file");
			
			DataController.getter(id, email, projectName, Description, file);
			
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

else if (event.getSource() == btnAll) {
	

	
	vb.getChildren().clear();

	String sql = "select * from projects";

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
			
			 id=resulSet.getInt("ID");
			email=resulSet.getString("email");
			
			projectName=resulSet.getString("projectName");
			Description=resulSet.getString("Description");
			file=resulSet.getString("file");
			
			DataController.getter(id, email, projectName, Description, file);
			
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

}




}


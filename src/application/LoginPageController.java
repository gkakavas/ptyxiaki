package application;


import java.io.IOException;

import javafx.application.Platform;
import javafx.event.ActionEvent;

import javafx.fxml.*;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.control.PasswordField;
public class LoginPageController {

	@FXML
	private Button connectionBtn;
	@FXML 
	private Button exit;
	@FXML
	private TextField usernameTextField;
	@FXML 
	private PasswordField passwordField;
	@FXML
	private Label textDangerLbl;
	@FXML
	private Label signDangerLbl;
	
	
	@FXML
	public void getConnection(ActionEvent e) throws IOException{
		LoginPageModel lpm = new LoginPageModel();
		lpm.retrieveData(usernameTextField.getText().toString(),passwordField.getText().toString());
		if (lpm.isAuthentication()==true) {
			Parent root;
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("main.fxml"));
			root = loader.load();
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			Stage stage = new Stage();
			stage.setTitle("Πτυχιακή");
			stage.setScene(scene);
			stage.setMaximized(true);
			stage.show();
			Main.getStage().close();
		}
		else 
			textDangerLbl.setVisible(true);
			signDangerLbl.setVisible(true);
		
	}
	@FXML
	public void exitBtn(ActionEvent e1) throws IOException{
		Platform.exit();
		System.exit(0);
	}
}
package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

public class MainController implements Initializable {
	@FXML
	private Button managementOrder;
	@FXML
	private Button createOrder;
	@FXML
	private Button managementCustomer;
	@FXML
	private Button editProduct;
	@FXML 
	private Button exit;
	@FXML
	private BorderPane root;
	@FXML 
	private HBox hbox;
	@FXML 
	private Label userLbl;
	
	private String type1 = "warehouse";
	private String type2 = "sales";
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		AnchorPane orderManagementAnch;
		try {
			orderManagementAnch = FXMLLoader.load(getClass().getResource("orderManagement.fxml"));
			root.setCenter(orderManagementAnch);
			userLbl.setText(LoginPageModel.userType);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@FXML
	public void loadOrderManagement() {
		root.setCenter(null);
		AnchorPane orderManagementAnch;
		try {
			orderManagementAnch = FXMLLoader.load(getClass().getResource("orderManagement.fxml"));
			this.root.setCenter(orderManagementAnch);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@FXML
		public void loadCreateOrder() {
			root.setCenter(null);
			AnchorPane createOrderAnch;
			if(LoginPageModel.userType.equals(type2))
			try {
				createOrderAnch = FXMLLoader.load(getClass().getResource("createOrder.fxml"));
				root.setCenter(createOrderAnch);
			} catch (IOException e) {
				
				e.printStackTrace();
			}
	}
	
	@FXML
	public void loadCustomerManagement() {
		root.setCenter(null);
		AnchorPane customerManagementAnch;
		if(LoginPageModel.userType.equals(type2))	
		try {
				customerManagementAnch = FXMLLoader.load(getClass().getResource("customerManagement.fxml"));
				this.root.setCenter(customerManagementAnch);
			} 	catch (IOException e) {
				e.printStackTrace();
			}
			
		}
	

	@FXML
	public void loadProductManagement() {
		root.setCenter(null);
		AnchorPane productManagementAnch;
		if(LoginPageModel.userType.equals(type1))
		try {
			productManagementAnch = FXMLLoader.load(getClass().getResource("productManagement.fxml"));
			this.root.setCenter(productManagementAnch);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@FXML 
	public void exit() {
		Platform.exit();
		System.exit(0);
	}
}
	

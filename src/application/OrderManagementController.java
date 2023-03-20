package application;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.SelectionMode;

import javafx.scene.control.TableColumn;

import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import application.OrderManagementData;

public class OrderManagementController extends detailsOfOrderController implements Initializable {
@FXML
private TableView<OrderManagementData> orderTableView;
@FXML
private TableColumn<OrderManagementData,String> order_id;
@FXML
private TableColumn <OrderManagementData,String> order_date;
@FXML
private TableColumn <OrderManagementData,String> f_name;
@FXML
private TableColumn <OrderManagementData,String> l_name;
@FXML
private TableColumn <OrderManagementData,String> phone_num;
@FXML
private TableColumn <OrderManagementData,String> address;
@FXML
private TableColumn <OrderManagementData,String> total_price;
@FXML
private TableColumn <OrderManagementData,String> fk_order_status_id;
@FXML
private Button deleteBtn;
@FXML 
private ComboBox<String> orderStatusCombo;
@FXML
private TextField orderIdTextField;
@FXML 
private Button okBtn;

private static String currentOrderId;

ObservableList<OrderManagementData> obslist1 = FXCollections.observableArrayList();
ObservableList<String> obslist2 = FXCollections.observableArrayList();

@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
	
		try {
			Connection con = ConnectDB.getConnection();
			ResultSet rs1 = con.createStatement().executeQuery("SELECT orders.order_id,orders.order_date,customer.f_name,customer.l_name,customer.phone_num,customer.address,orders.total_price,orders.fk_order_status_string FROM ORDERS INNER JOIN CUSTOMER ON fk_customer_id = customer.customer_id");
				while(rs1.next()) {
					obslist1.add(new OrderManagementData(rs1.getString(1),rs1.getString(2),rs1.getString(3),
					rs1.getString(4),rs1.getString(5),rs1.getString(6),
					rs1.getString(7),rs1.getString(8)));
					//DEBUG
					System.out.println(rs1.getString(1)+rs1.getString(2)+rs1.getString(3)+
					rs1.getString(4)+rs1.getString(5)+rs1.getString(6)+
					rs1.getString(7)+rs1.getString(8));
					System.out.println("________________________________________________________");
				}
			ResultSet rs2 = con.createStatement().executeQuery("SELECT order_status.order_status_string from order_status");
				while (rs2.next()) {

					obslist2.add(new OrderManagementData(null,rs2.getString(1)).getOrderStatus());
					//DEBUG
					System.out.println(rs2.getString(1));
					System.out.println("________________________________________________________");
				
				}	
		}
		catch (ClassNotFoundException | SQLException e) {
		e.printStackTrace();
		}
	
		order_id.setCellValueFactory(new PropertyValueFactory<OrderManagementData,String>("id"));
		order_date.setCellValueFactory(new PropertyValueFactory<OrderManagementData,String>("date"));
		f_name.setCellValueFactory(new PropertyValueFactory<OrderManagementData,String>("fName"));
		l_name.setCellValueFactory(new PropertyValueFactory<OrderManagementData,String>("lName"));
		phone_num.setCellValueFactory(new PropertyValueFactory<OrderManagementData,String>("phone"));
		address.setCellValueFactory(new PropertyValueFactory<OrderManagementData,String>("addr"));
		total_price.setCellValueFactory(new PropertyValueFactory<OrderManagementData,String>("ammount"));
		fk_order_status_id.setCellValueFactory(new PropertyValueFactory<OrderManagementData,String>("orderStatus"));
		
		orderTableView.setItems(null);
		orderTableView.setItems(obslist1);
		//DEBUG
		System.out.println(obslist1);
		orderTableView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
		orderStatusCombo.setItems(null);
		orderStatusCombo.setItems(obslist2);
		System.out.println(obslist2);
		}
	
	public void deleteLine(ActionEvent e) throws ClassNotFoundException {
		
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Προειδοποίηση");
		alert.setContentText("Η παραγγελία θα διαγραφεί οριστικά");
		Optional<ButtonType> result = alert.showAndWait();
		if(result.get() == ButtonType.OK) {
		
			OrderManagementData omd = orderTableView.getSelectionModel().getSelectedItem();
			//DEBUG
			System.out.println(omd.getId()+omd.getDate()+omd.getfName()+omd.getlName());
			try {
				Connection conn = ConnectDB.getConnection();
				conn.createStatement().executeQuery("DELETE FROM orders where order_id="+omd.getId());
			}
			catch(SQLException ex){
				ex.printStackTrace();
				Alert alert2 = new Alert(AlertType.WARNING);
				alert2.setTitle("Προειδοποίηση");
				alert2.setContentText("H σύνδεση με την Βαση δεδομένων απέτυχε");
				alert2.showAndWait();
			}
			orderTableView.getItems().removeAll(orderTableView.getSelectionModel().getSelectedItem());
			orderTableView.setItems(null);
			orderTableView.setItems(obslist1);
		}
	}
	
	public void changeOrderStatus(ActionEvent e) {
		String chooseId = orderIdTextField.getText();
		String chooseOrderStatus = orderStatusCombo.getSelectionModel().getSelectedItem();
		OrderManagementData omd= new OrderManagementData(chooseId,chooseOrderStatus);
		//DEBUG
		System.out.println(omd.getId()+omd.getOrderStatus());
		
			try {
				Connection conn = ConnectDB.getConnection();
				conn.createStatement().executeQuery("UPDATE ORDERS SET orders.fk_order_status_string = '"+omd.getOrderStatus()+"' WHERE orders.order_id= "+omd.getId());
				orderTableView.getItems().clear();
				initialize(null, null);
			} 
			catch (ClassNotFoundException | SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	}	
	
	public void orderDetailsView(ActionEvent e) {
		try {
			OrderManagementData omData;
			omData = orderTableView.getSelectionModel().getSelectedItem();
			currentOrderId = omData.getId();
			System.out.println(currentOrderId+" currentOrderId");
			Parent root = FXMLLoader.load(getClass().getResource("detailsOfOrder.fxml"));
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			Stage stage = new Stage();
			stage.setTitle("Πτυχιακή");
			stage.setScene(scene);
			stage.show();
			
		} 
		catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	public static String getCurrentOrderId() {
		return currentOrderId;
	}
	
}
	



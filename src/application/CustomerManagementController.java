package application;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

public class CustomerManagementController implements Initializable{
	@FXML
	private TableView<CustomerManagementData> customerTableView;
	@FXML
	private TableColumn<CustomerManagementData,String> customer_id;
	@FXML
	private TableColumn<CustomerManagementData,String> f_name;
	@FXML
	private TableColumn <CustomerManagementData,String> l_name;
	@FXML
	private TableColumn <CustomerManagementData,String> company_name;
	@FXML
	private TableColumn <CustomerManagementData,String> ADDRESS;
	@FXML
	private TableColumn <CustomerManagementData,String> CITY;
	@FXML
	private TableColumn <CustomerManagementData,String> REGION;
	@FXML
	private TableColumn <CustomerManagementData,String> ZIP;
	@FXML
	private TableColumn <CustomerManagementData,String> phone_num;
	@FXML
	private TableColumn <CustomerManagementData,String> EMAIL;
	@FXML
	private TextField fNameTF;
	@FXML
	private TextField lNameTF;
	@FXML
	private TextField companyNameTF;
	@FXML
	private TextField addressTF;
	@FXML
	private TextField cityTF;
	@FXML
	private TextField regionTF;
	@FXML
	private TextField zipTF;
	@FXML
	private TextField phoneTF;
	@FXML
	private TextField emailTF;
	
	ObservableList<CustomerManagementData> obslist1 = FXCollections.observableArrayList();
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		try {
			Connection con = ConnectDB.getConnection();
			ResultSet rs1 = con.createStatement().executeQuery("SELECT * FROM customer");
				while(rs1.next()) {
					obslist1.add(new CustomerManagementData(rs1.getString(1),rs1.getString(2),rs1.getString(3),
					rs1.getString(4),rs1.getString(5),rs1.getString(6),
					rs1.getString(7),rs1.getString(8),rs1.getString(9),rs1.getString(10)));
					//DEBUG
					System.out.println(rs1.getString(1)+rs1.getString(2)+rs1.getString(3)+
					rs1.getString(4)+rs1.getString(5)+rs1.getString(6)+
					rs1.getString(7)+rs1.getString(8)+rs1.getString(9)+rs1.getString(10));
					System.out.println("________________________________________________________");
				}
		}
		catch (ClassNotFoundException | SQLException e) {
		e.printStackTrace();
		}
		
		customer_id.setCellValueFactory(new PropertyValueFactory<CustomerManagementData,String>("customerId"));
		f_name.setCellValueFactory(new PropertyValueFactory<CustomerManagementData,String>("fName"));
		l_name.setCellValueFactory(new PropertyValueFactory<CustomerManagementData,String>("lName"));
		company_name.setCellValueFactory(new PropertyValueFactory<CustomerManagementData,String>("companyName"));
		ADDRESS.setCellValueFactory(new PropertyValueFactory<CustomerManagementData,String>("address"));
		CITY.setCellValueFactory(new PropertyValueFactory<CustomerManagementData,String>("city"));
		REGION.setCellValueFactory(new PropertyValueFactory<CustomerManagementData,String>("region"));
		ZIP.setCellValueFactory(new PropertyValueFactory<CustomerManagementData,String>("zip"));
		phone_num.setCellValueFactory(new PropertyValueFactory<CustomerManagementData,String>("phone"));
		EMAIL.setCellValueFactory(new PropertyValueFactory<CustomerManagementData,String>("email"));
		
		customerTableView.setItems(null);
		customerTableView.setItems(obslist1);
		//DEBUG
		System.out.println(obslist1);
		customerTableView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
	}
	
	public void customerRegistration(ActionEvent e) {
		companyNameTF.getText();
		addressTF.getText();
		cityTF.getText();
		regionTF.getText();
		zipTF.getText();
		phoneTF.getText();
		emailTF.getText();
		CustomerManagementData cmd= new CustomerManagementData(null,fNameTF.getText(),lNameTF.getText(),companyNameTF.getText(),
				addressTF.getText(),cityTF.getText(),regionTF.getText(),zipTF.getText(),phoneTF.getText(),emailTF.getText());
		try {
			Connection conn = ConnectDB.getConnection();
			conn.createStatement().executeQuery("INSERT INTO CUSTOMER(customer_id, f_name, l_name, company_name, address, city, region, zip, phone_num, email) "
					+ "VALUES(default,'" +cmd.getFName()+ "','"+cmd.getLName()+"','"+cmd.getCompanyName()+"','"+cmd.getAddress()+"','"+cmd.getCity()+
					"','"+cmd.getRegion()+"',"+cmd.getZip()+",'"+cmd.getPhone()+"','"+cmd.getEmail()+"')");
			customerTableView.getItems().clear();
			initialize(null, null);
		} catch (ClassNotFoundException | SQLException e1) {
			e1.printStackTrace();
		}
	}
	
	public void deleteCustomer(ActionEvent e) {
		
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Προειδοποίηση");
		alert.setContentText("Ο πελάτης θα διαγραφεί οριστικά");
		Optional<ButtonType> result = alert.showAndWait();
		if(result.get() == ButtonType.OK) {
			CustomerManagementData cmd = customerTableView.getSelectionModel().getSelectedItem();
			try {
				Connection conn = ConnectDB.getConnection();
				conn.createStatement().executeQuery("DELETE FROM Customer where customer_id="+cmd.getCustomerId());
			}
			catch(SQLException |ClassNotFoundException ex){
				ex.printStackTrace();
				Alert alert2 = new Alert(AlertType.WARNING);
				alert2.setTitle("Προειδοποίηση");
				alert2.setContentText("H σύνδεση με την Βαση δεδομένων απέτυχε");
				alert2.showAndWait();
			}
			customerTableView.getItems().remove(customerTableView.getSelectionModel().getSelectedItem());
		}
	}
}

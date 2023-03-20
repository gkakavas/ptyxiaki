package application;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;


public class ProductManagementController implements Initializable  {
	 
		@FXML
		private TableView<ProductManagementData> productTableView;
		@FXML
		private TableColumn<ProductManagementData,String> PRODUCT_ID;
		@FXML
		private TableColumn <ProductManagementData,String> MANUFACTURER_CODE;
		@FXML
		private TableColumn <ProductManagementData,String> PRODUCT_NAME;
		@FXML
		private TableColumn <ProductManagementData,String> PRODUCT_TYPE;
		@FXML
		private TableColumn <ProductManagementData,String> UNIT_PRICE;
		@FXML
		private TableColumn <ProductManagementData,String> VAT;
		@FXML
		private TableColumn <ProductManagementData,String> WEIGHT;
		@FXML
		private TableColumn <ProductManagementData,String> VOLUME;
		@FXML
		private TableColumn <ProductManagementData,String> UNIT_DESCRIPTION;
		@FXML
		private TableColumn <ProductManagementData,String> STOCK;
		@FXML
		private TextField manufacturerCodeTF;
		@FXML
		private TextField productNameTF;
		@FXML
		private TextField unitPriceTF;
		@FXML
		private TextField vatTF;
		@FXML
		private TextField weightTF;
		@FXML
		private TextField typeTF;
		@FXML
		private TextField descriptionTF;
		@FXML
		private TextField volumeTF;
		@FXML
		private RadioButton chooseNewProductRB;
		@FXML
		private RadioButton chooseAddStockRB;
		@FXML
		private TextField pruductIdTF;
		@FXML
		private TextField stockTF;
		@FXML
		private Button deleteBtn;
		@FXML
		private Button addNewProductBtn;
		@FXML
		private Button addStockBtn;
		
		ObservableList<ProductManagementData> obslist1 = FXCollections.observableArrayList();
		
		@Override
		public void initialize(URL arg0, ResourceBundle arg1) {
			try {
				Connection conn = ConnectDB.getConnection();
				ResultSet rs1 = conn.createStatement().executeQuery("SELECT * FROM PRODUCT");
				while(rs1.next()) {
					obslist1.add(new ProductManagementData(rs1.getString(1),rs1.getString(2),rs1.getString(3),rs1.getString(4),rs1.getString(5),
						rs1.getString(6),rs1.getString(7),rs1.getString(8),rs1.getString(9),rs1.getString(10)));
				}
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
			PRODUCT_ID.setCellValueFactory(new PropertyValueFactory<ProductManagementData,String>("productId"));
			MANUFACTURER_CODE.setCellValueFactory(new PropertyValueFactory<ProductManagementData,String>("manufacturerCode"));
			PRODUCT_NAME.setCellValueFactory(new PropertyValueFactory<ProductManagementData,String>("productName"));
			PRODUCT_TYPE.setCellValueFactory(new PropertyValueFactory<ProductManagementData,String>("productType"));
			UNIT_PRICE.setCellValueFactory(new PropertyValueFactory<ProductManagementData,String>("unitPrice"));
			VAT.setCellValueFactory(new PropertyValueFactory<ProductManagementData,String>("vat"));
			WEIGHT.setCellValueFactory(new PropertyValueFactory<ProductManagementData,String>("weight"));
			VOLUME.setCellValueFactory(new PropertyValueFactory<ProductManagementData,String>("volume"));
			UNIT_DESCRIPTION.setCellValueFactory(new PropertyValueFactory<ProductManagementData,String>("unitDescription"));
			STOCK.setCellValueFactory(new PropertyValueFactory<ProductManagementData,String>("stock"));
			productTableView.setItems(null);
			productTableView.setItems(obslist1);
		}
		
		public void deleteProduct(ActionEvent e){
			ProductManagementData pmd = productTableView.getSelectionModel().getSelectedItem();
			try {
				Connection con = ConnectDB.getConnection();
				con.createStatement().executeQuery("DELETE FROM PRODUCT where PRODUCT_ID ="+pmd.getProductId());
				productTableView.getItems().clear();
				initialize(null,null);
			}catch(SQLException|ClassNotFoundException e1){
				e1.printStackTrace();
			}
		}
		
		public void chooseAddNewProduct(ActionEvent e) {
			if(chooseNewProductRB.isSelected()) {
				pruductIdTF.setDisable(true);
				stockTF.setDisable(true);
				manufacturerCodeTF.setDisable(false);
				productNameTF.setDisable(false);
				unitPriceTF.setDisable(false);
				vatTF.setDisable(false);
				weightTF.setDisable(false);
				typeTF.setDisable(false);
				descriptionTF.setDisable(false);
				volumeTF.setDisable(false);
				chooseAddStockRB.setSelected(false);
			}
			else {
				manufacturerCodeTF.setDisable(true);
				productNameTF.setDisable(true);
				unitPriceTF.setDisable(true);
				vatTF.setDisable(true);
				weightTF.setDisable(true);
				typeTF.setDisable(true);
				descriptionTF.setDisable(true);
				volumeTF.setDisable(true);
			}
		}
		
		public void chooseAddStock(ActionEvent e){
			if(chooseAddStockRB.isSelected()) {
				manufacturerCodeTF.setDisable(true);
				productNameTF.setDisable(true);
				unitPriceTF.setDisable(true);
				vatTF.setDisable(true);
				weightTF.setDisable(true);
				typeTF.setDisable(true);
				descriptionTF.setDisable(true);
				volumeTF.setDisable(true);
				pruductIdTF.setDisable(false);
				stockTF.setDisable(false);
				chooseNewProductRB.setSelected(false);
			}
			else {
				pruductIdTF.setDisable(true);
				stockTF.setDisable(true);
			}
		}
		
		public void addNewProduct(ActionEvent e) {
			if(manufacturerCodeTF.getText().toString()!=null &&
				productNameTF.getText().toString()!=null && typeTF.getText().toString()!=null && unitPriceTF.getText().toString()!=null && 
				vatTF.getText().toString()!=null && weightTF.getText().toString()!=null && volumeTF.getText().toString()!=null &&
				descriptionTF.getText().toString()!=null) {
				
				ProductManagementData pmd = new ProductManagementData(null, manufacturerCodeTF.getText().toString(),
					productNameTF.getText().toString(), typeTF.getText().toString(), unitPriceTF.getText().toString(), 
					vatTF.getText().toString(), weightTF.getText().toString(), volumeTF.getText().toString(),
					descriptionTF.getText().toString(),null);
				try {
					Connection con = ConnectDB.getConnection();
					con.createStatement().executeQuery("INSERT INTO PRODUCT (product_id, manufacturer_code, product_name, product_type, unit_price, \r\n" + 
						"vat, weight, volume, unit_description, stock) VALUES(default,'"+pmd.getManufacturerCode()+"','"+pmd.getProductName()+"','"+pmd.getProductType()+"',"+pmd.getUnitPrice()+","+pmd.getVat()+","+pmd.getWeight()+",'"+pmd.getVolume()+"','"+pmd.getUnitDescription()+"',0)");                                      
					productTableView.getItems().clear();
					initialize(null,null);
				}
				catch(SQLException|ClassNotFoundException e1){
				}
				
			}
			else {
				Alert alert = new Alert(AlertType.CONFIRMATION);
				alert.setTitle("Προειδοποίηση");
				alert.setContentText("Εισάγετε σωστά τα δεδομένα και συμπληρώστε τα απαραίτητα πεδία");
			}
		}
		
		public void addStock(ActionEvent e) {
			if((pruductIdTF.getText().toString()!=null) && (stockTF.getText().toString() !=null)) {
				ProductManagementData pmt = new ProductManagementData(pruductIdTF.getText().toString(), null,
						null, null, null, null,null, null,null,stockTF.getText().toString());
				try {
					Connection con = ConnectDB.getConnection();
					con.createStatement().executeQuery("UPDATE PRODUCT SET product.stock="+pmt.getStock()+" WHERE product.product_id ="+pmt.getProductId());
					productTableView.getItems().clear();
					initialize(null,null);
				}
				catch(SQLException|ClassNotFoundException e1) {	
				}
			}
			else {
				Alert alert = new Alert(AlertType.CONFIRMATION);
				alert.setTitle("Προειδοποίηση");
				alert.setContentText("Εισάγετε σωστά τα δεδομένα και συμπληρώστε τα απαραίτητα πεδία");
			}	
		}
}

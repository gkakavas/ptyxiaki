package application;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class detailsOfOrderController implements Initializable   {
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
	@FXML
	private Label orderDateLbl;
	@FXML
	private Label paymentTypeLbl;
	@FXML
	private Label paymentStatusTf;
	@FXML
	private Label deliveryTypeLbl;
	@FXML
	private Label orderStatusLbl;
	@FXML
	private Label totalPriceLbl;
	@FXML
	private Label totalVatLbl;
	@FXML
	private TableView<Basket> basketTableView;
	@FXML
	private TableColumn<Basket,String> productNameCol;
	@FXML
	private TableColumn<Basket,String> unitPriceCol;
	@FXML
	private TableColumn<Basket,String> quantityCol;
	
	private String orderId;
	private String currentCustomerId;
	private String currentPaymentId;
	private String currentDeliveryId;
	private String currentPaymentTypeId;
	
	ObservableList<Basket> obslist = FXCollections.observableArrayList();
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		this.orderId=OrderManagementController.getCurrentOrderId();
		
		System.out.println(orderId+ "this is orderId");
		try {
			Connection con = ConnectDB.getConnection();
			ResultSet rs = con.createStatement().executeQuery("SELECT orders.FK_CUSTOMER_ID, orders.FK_PAYMENT_ID, orders.FK_DELIVERY_ID"
					+ " FROM orders WHERE order_id = "+orderId);
			while(rs.next()) {
				currentCustomerId = rs.getString(1);
				currentPaymentId = rs.getString(2);
				currentDeliveryId = rs.getString(3);
			}
			ResultSet rs1 = con.createStatement().executeQuery("SELECT * FROM customer WHERE customer_id = "+currentCustomerId);
			while(rs1.next()) {
				fNameTF.setText(rs1.getString(2));
				lNameTF.setText(rs1.getString(3));
				companyNameTF.setText(rs1.getString(4));
				addressTF.setText(rs1.getString(5));
				cityTF.setText(rs1.getString(6));
				regionTF.setText(rs1.getString(7));
				zipTF.setText(rs1.getString(8));
				phoneTF.setText(rs1.getString(9));
				emailTF.setText(rs1.getString(10));
			}
			ResultSet rs2 = con.createStatement().executeQuery("SELECT orders.ORDER_DATE, orders.TOTAL_PRICE, orders.TOTAL_VAT, orders.fk_order_status_string  FROM orders WHERE order_id = "+orderId);
			while(rs2.next()) {
				orderDateLbl.setText(rs2.getString(1));
				totalPriceLbl.setText(rs2.getString(2));
				totalVatLbl.setText(rs2.getString(3));
				orderStatusLbl.setText(rs2.getString(4));
			}
			
			ResultSet rs3 = con.createStatement().executeQuery("SELECT payment.fk_payment_type_id,payment.payment_status FROM payment WHERE payment_id= "+currentPaymentId);
			while(rs3.next()) {
				currentPaymentTypeId =rs3.getString(1);
				paymentStatusTf.setText(rs3.getString(2));
			}
			
			ResultSet rs4 = con.createStatement().executeQuery("SELECT payment_type.payment_type_string FROM payment_type WHERE payment_type_id= "+currentPaymentTypeId);
			while (rs4.next()) {
				paymentTypeLbl.setText(rs4.getString(1));
			}
			
			ResultSet rs5 = con.createStatement().executeQuery("SELECT delivery.delivery_type FROM delivery WHERE delivery_id= "+currentDeliveryId);
			while(rs5.next()) {
				deliveryTypeLbl.setText(rs5.getString(1));
			}
			
			ResultSet rs6 = con.createStatement().executeQuery("SELECT order_items.product_name, order_items.partial_price, order_items.quantity FROM order_items WHERE order_id= "+orderId);
			while(rs6.next()) {
				obslist.add(new Basket(rs6.getString(1),rs6.getString(2),rs6.getString(3)));
			}
		}
		catch(SQLException|ClassNotFoundException e) {
			e.printStackTrace();
		}
		productNameCol.setCellValueFactory(new PropertyValueFactory<Basket,String>("productName"));
		unitPriceCol.setCellValueFactory(new PropertyValueFactory<Basket,String>("productUnitPrice"));
		quantityCol.setCellValueFactory(new PropertyValueFactory<Basket,String>("productQuantity"));
		
		basketTableView.setItems(obslist);
	}
}

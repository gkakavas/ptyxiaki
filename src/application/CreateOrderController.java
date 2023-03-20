package application;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.net.URL;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;

public class CreateOrderController implements Initializable{
	@FXML
	private TableView<CreateOrderData> productTableView;
	@FXML
	private TableColumn<CreateOrderData,String> product_id;
	@FXML
	private TableColumn<CreateOrderData,String> manufacturer_code;
	@FXML
	private TableColumn <CreateOrderData,String> product_name;
	@FXML
	private TableColumn <CreateOrderData,String> unit_price;
	@FXML
	private TableColumn <CreateOrderData,String> VAT;
	@FXML
	private TableColumn <CreateOrderData,String> WEIGHT;
	@FXML
	private TableColumn <CreateOrderData,String> VOLUME;
	@FXML
	private TableColumn <CreateOrderData,String> product_type;
	@FXML
	private TableView<Basket> basketTableView;
	@FXML
	private TableColumn <Basket,String> basket_product_name;
	@FXML
	private TableColumn <Basket,String> basket_product_unit_price;
	@FXML
	private TableColumn <Basket,String> basket_product_quantity;
	@FXML
	private TableColumn <Basket,String> basket_product_vat;
	@FXML
	private Button addQuantityBtn;
	@FXML
	private Button subtractQuantityBtn;
	@FXML
	private Button addToBasketBtn;
	@FXML
	private TextField  quantityTextField;
	@FXML
	private RadioButton chooseSearchCustomer;
	@FXML
	private RadioButton chooseNewCustomer;
	@FXML
	private TextField searchCustomerTF;
	@FXML
	private Button searchBtn;
	@FXML
	private Label customerName;
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
	private RadioButton chooseCash;
	@FXML
	private RadioButton chooseCard;
	@FXML
	private RadioButton chooseDeposit;
	@FXML
	private RadioButton choosePaypal;
	@FXML
	private RadioButton chooseCourier;
	@FXML
	private RadioButton chooseReceiveByShop;
	@FXML
	private Label partialPrice;
	@FXML
	private Label totalVat;
	@FXML
	private Label total;
	
	private String existingCustomerId;
	private String currentOrderId;
	private String currentDeliveryId;
	private String currentPaymentId;
	
	ObservableList<CreateOrderData> obslist1 = FXCollections.observableArrayList();
	ObservableList<Basket> obslist2 = FXCollections.observableArrayList();
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		try {
			Connection con = ConnectDB.getConnection();
			ResultSet rs1 = con.createStatement().executeQuery("SELECT product.PRODUCT_ID,product.MANUFACTURER_CODE,product.PRODUCT_NAME,product.UNIT_PRICE,product.VAT,product.WEIGHT,product.VOLUME,product.PRODUCT_TYPE\r\n" + 
					" FROM PRODUCT");
				while(rs1.next()) {
					obslist1.add(new CreateOrderData(rs1.getString(1),rs1.getString(2),rs1.getString(3),
					rs1.getString(4),rs1.getString(5),rs1.getString(6),
					rs1.getString(7),rs1.getString(8)));
					//DEBUG
					System.out.println(rs1.getString(1)+rs1.getString(2)+rs1.getString(3)+
					rs1.getString(4)+rs1.getString(5)+rs1.getString(6)+
					rs1.getString(7)+rs1.getString(8));
					System.out.println("________________________________________________________");
				}
		}
		catch(SQLException | ClassNotFoundException e){
			e.printStackTrace();
		}
		
		product_id.setCellValueFactory(new PropertyValueFactory<CreateOrderData,String>("productId"));
		manufacturer_code.setCellValueFactory(new PropertyValueFactory<CreateOrderData,String>("manufacturerCode"));
		product_name.setCellValueFactory(new PropertyValueFactory<CreateOrderData,String>("productName"));
		unit_price.setCellValueFactory(new PropertyValueFactory<CreateOrderData,String>("unitPrice"));
		VAT.setCellValueFactory(new PropertyValueFactory<CreateOrderData,String>("vat"));
		WEIGHT.setCellValueFactory(new PropertyValueFactory<CreateOrderData,String>("weight"));
		VOLUME.setCellValueFactory(new PropertyValueFactory<CreateOrderData,String>("volume"));
		product_type.setCellValueFactory(new PropertyValueFactory<CreateOrderData,String>("productType"));
		
		productTableView.setItems(null);
		productTableView.setItems(obslist1);
		//DEBUG
		System.out.println(obslist1);
		productTableView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
		
		quantityTextField.setText("1");
	}
	
	@FXML
	public void addQuantity(ActionEvent e) {
		int productQuantity = Integer.parseInt(quantityTextField.getText());
		++productQuantity;
		quantityTextField.setText(String.valueOf(productQuantity)); 
	}
	
	@FXML
	public void subtractQuantity(ActionEvent e) {
		int productQuantity = Integer.parseInt(quantityTextField.getText());
		if(productQuantity>0)
			--productQuantity;
			quantityTextField.setText(String.valueOf(productQuantity)); 
	}
	
	@FXML
	public void addToBasket(ActionEvent e) {
		basketTableView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
		CreateOrderData cod = productTableView.getSelectionModel().getSelectedItem();
		String selectedProductName = cod.getProductName();
		String selectedProductPrice = cod.getUnitPrice();
		String selectedProductQuantity = quantityTextField.getText();
		String selectedProductVat = cod.getVat();
		String selectedProductId = cod.getProductId();
		
		
		obslist2.addAll(new Basket(selectedProductId,selectedProductName,selectedProductPrice,selectedProductQuantity,selectedProductVat));
		
		
		basket_product_name.setCellValueFactory(new PropertyValueFactory<Basket,String>("productName"));
		basket_product_unit_price.setCellValueFactory(new PropertyValueFactory<Basket,String>("productUnitPrice"));
		basket_product_quantity.setCellValueFactory(new PropertyValueFactory<Basket,String>("productQuantity"));
		basket_product_vat.setCellValueFactory(new PropertyValueFactory<Basket,String>("productVat"));
		basketTableView.setItems(obslist2);
		
		Basket basket = new Basket(selectedProductPrice,selectedProductQuantity,selectedProductVat);
		
		String previousPartialPrice=partialPrice.getText();
		float previousPartialPriceFloat = Float.parseFloat(previousPartialPrice);
		Float nextPartialPrice = basket.getBasketInvoicePartial(selectedProductPrice, selectedProductQuantity, selectedProductVat);
		partialPrice.setText(String.valueOf(previousPartialPriceFloat+nextPartialPrice));
		
		String previousVat=totalVat.getText();
		float previousVatFloat = Float.parseFloat(previousVat);
		Float nextVat = basket.getBasketInvoiceVat(selectedProductPrice, selectedProductQuantity, selectedProductVat);
		totalVat.setText(String.valueOf(previousVatFloat+nextVat));
		
		String previousUnitPrice = total.getText();
		float previousTotal = Float.parseFloat(previousUnitPrice);
		float currentTotal = Float.parseFloat(selectedProductPrice);
		int currentQuantity = Integer.parseInt(selectedProductQuantity);
		total.setText(String.valueOf(previousTotal+(currentTotal*currentQuantity)));
		
	
	}
	
	@FXML
	public void chooseSearchCustomer(ActionEvent e) {
		if(chooseSearchCustomer.isSelected()) {
			searchCustomerTF.setDisable(false);
			searchBtn.setDisable(false);
			fNameTF.setDisable(true);
			lNameTF.setDisable(true);
			companyNameTF.setDisable(true);
			addressTF.setDisable(true);
			cityTF.setDisable(true);
			regionTF.setDisable(true);
			zipTF.setDisable(true);
			phoneTF.setDisable(true);
			emailTF.setDisable(true);
			chooseNewCustomer.setSelected(false);
		}
		else{
			searchCustomerTF.setDisable(true);
			searchBtn.setDisable(true);
		}
	}
	
	@FXML
	public void chooseNewCustomer(ActionEvent e) {
		if(chooseNewCustomer.isSelected()) {
			fNameTF.setDisable(false);
			lNameTF.setDisable(false);
			companyNameTF.setDisable(false);
			addressTF.setDisable(false);
			cityTF.setDisable(false);
			regionTF.setDisable(false);
			zipTF.setDisable(false);
			phoneTF.setDisable(false);
			emailTF.setDisable(false);
			searchCustomerTF.setDisable(true);
			searchBtn.setDisable(true);
			chooseSearchCustomer.setSelected(false);
		}
		else {
			fNameTF.setDisable(true);
			lNameTF.setDisable(true);
			companyNameTF.setDisable(true);
			addressTF.setDisable(true);
			cityTF.setDisable(true);
			regionTF.setDisable(true);
			zipTF.setDisable(true);
			phoneTF.setDisable(true);
			emailTF.setDisable(true);
		}
	}
	
	public void controlPaymentSelection1(ActionEvent e) {
		if(chooseCash.isSelected()) {
			chooseCard.setSelected(false);
			chooseDeposit.setSelected(false);
			choosePaypal.setSelected(false);
		}
	}
	
	public void controlPaymentSelection2(ActionEvent e) {
		if(chooseCard.isSelected()) {
			chooseCash.setSelected(false);
			chooseDeposit.setSelected(false);
			choosePaypal.setSelected(false);
		}
	}
	public void controlPaymentSelection3(ActionEvent e) {
		if(chooseDeposit.isSelected()) {
			chooseCash.setSelected(false);
			chooseCard.setSelected(false);
			choosePaypal.setSelected(false);
			}
	}
		
	public void controlPaymentSelection4(ActionEvent e) {
		if(choosePaypal.isSelected()) {
			chooseCash.setSelected(false);
			chooseCard.setSelected(false);
			chooseDeposit.setSelected(false);
			}
	}
	
	public void controlDeliverySelection1(ActionEvent e) {
		if(chooseCourier.isSelected()) {
			chooseReceiveByShop.setSelected(false);
			}
	}
	
	public void controlDeliverySelection2(ActionEvent e) {
		if(chooseReceiveByShop.isSelected()) {
			chooseCourier.setSelected(false);
			}
	}
	
	public void searchCustomer(ActionEvent e) {
		if (chooseSearchCustomer.isSelected()){
			String controlNull = searchCustomerTF.getText();
			if(controlNull != null) {
				try {
						Connection con = ConnectDB.getConnection();
						ResultSet rs1 = con.createStatement().executeQuery("SELECT customer_id,customer.f_name,customer.l_name FROM CUSTOMER WHERE phone_num ='"+searchCustomerTF.getText()+"'");
						while(rs1.next()) {
							customerName.setText(rs1.getString(2)+" "+rs1.getString(3));
							existingCustomerId = rs1.getString(1);
						}
				}
					catch (ClassNotFoundException | SQLException e1) {
						e1.printStackTrace();
					}
			}
		}
	}
	
	
	public void createOrderElements(ActionEvent e) {
		
		if(chooseNewCustomer.isSelected()) {
			if((fNameTF.getText()!= null) && (lNameTF.getText()!= null) && (companyNameTF.getText()!= null) &&
					(addressTF.getText()!= null) && (cityTF.getText()!= null) && (regionTF.getText()!= null) && (zipTF.getText()!= null) && (phoneTF.getText()!= null) && (emailTF.getText()!= null)) {
				CustomerManagementData cmd = new CustomerManagementData(null,fNameTF.getText(),lNameTF.getText(),companyNameTF.getText(),
				addressTF.getText(),cityTF.getText(),regionTF.getText(),zipTF.getText(),phoneTF.getText(),emailTF.getText());
				try {
					Connection con = ConnectDB.getConnection();
					con.createStatement().executeQuery("INSERT INTO CUSTOMER(customer_id,f_name,l_name,company_name,address,city,region,zip,phone_num,email)"
									+ " VALUES(default,'"+cmd.getFName().toString()+"','"+cmd.getLName().toString()+"','"+cmd.getCompanyName().toString()+"','"+cmd.getAddress().toString()+"','"+cmd.getCity().toString()
									+"','"+cmd.getRegion().toString()+"',"+cmd.getZip().toString()+",'"+cmd.getPhone().toString()+"','"+cmd.getEmail().toString()+"')");
					ResultSet rs1 = con.createStatement().executeQuery("SELECT MAX(CUSTOMER.customer_id) FROM CUSTOMER");
					while(rs1.next()) {
						existingCustomerId =rs1.getString(1);
					}
				}
				 
				catch (ClassNotFoundException | SQLException e1) {
					e1.printStackTrace();
				}
					if(chooseCash.isSelected()) {
						try {
							Connection con1 = ConnectDB.getConnection();
							con1.createStatement().executeQuery("INSERT INTO PAYMENT(payment_id,fk_payment_type_id,payment_transaction_date,payment_status)"
									+ " VALUES(default,10,sysdate,'Εκκρεμεί')");
							ResultSet rs = con1.createStatement().executeQuery("SELECT MAX(payment.payment_id) FROM payment");
							while(rs.next()) {
								currentPaymentId =rs.getString(1);
							}
						} catch (ClassNotFoundException | SQLException e3) {
							e3.printStackTrace();
						}
					}
					else if(chooseCard.isSelected()){
						try {
							Connection con2 = ConnectDB.getConnection();
							con2.createStatement().executeQuery("INSERT INTO PAYMENT(payment_id,fk_payment_type_id,payment_transaction_date,payment_status)"
									+ " VALUES(default,20,sysdate,'Πληρώθηκε')");
							ResultSet rs = con2.createStatement().executeQuery("SELECT MAX(payment.payment_id) FROM payment");
							while(rs.next()) {
								currentPaymentId =rs.getString(1);
							}
						} catch (ClassNotFoundException | SQLException e4) {
							e4.printStackTrace();
						}
					}
					else if(chooseDeposit.isSelected()){
						try {
							Connection con3 = ConnectDB.getConnection();
							con3.createStatement().executeQuery("INSERT INTO PAYMENT(payment_id,fk_payment_type_id,payment_transaction_date,payment_status)"
									+ " VALUES(default,30,sysdate,'Πληρώθηκε')");
							ResultSet rs = con3.createStatement().executeQuery("SELECT MAX(payment.payment_id) FROM payment");
							while(rs.next()) {
								currentPaymentId =rs.getString(1);
							}
						} catch (ClassNotFoundException | SQLException e5) {
							e5.printStackTrace();
						}
					}
					else if(choosePaypal.isSelected()){
						try {
							Connection con4 = ConnectDB.getConnection();
							con4.createStatement().executeQuery("INSERT INTO PAYMENT(payment_id,fk_payment_type_id,payment_transaction_date,payment_status)"
									+ " VALUES(default,40,sysdate,'Πληρώθηκε')");
							ResultSet rs = con4.createStatement().executeQuery("SELECT MAX(payment.payment_id) FROM payment");
							while(rs.next()) {
								currentPaymentId =rs.getString(1);
							}
						} catch (ClassNotFoundException | SQLException e6) {
							e6.printStackTrace();
						}
					}
					if (chooseCourier.isSelected()) {
						try {
							Connection con5 = ConnectDB.getConnection();
							con5.createStatement().executeQuery("INSERT INTO DELIVERY(delivery_id,delivery_type,departure_date)"
									+ " VALUES(default,'Αποστολή με κόυριερ',sysdate)");
							ResultSet rs =con5.createStatement().executeQuery("SELECT MAX(delivery.delivery_id) FROM delivery");
							while(rs.next()) {
								currentDeliveryId =rs.getString(1);
							}
						} catch (ClassNotFoundException | SQLException e7) {
							e7.printStackTrace();
						}
					}
					if (chooseReceiveByShop.isSelected()) {
						try {
							Connection con6 = ConnectDB.getConnection();
							con6.createStatement().executeQuery("INSERT INTO DELIVERY(delivery_id,delivery_type,departure_date)"
									+ " VALUES(default,'Παραλαβή από το κατάστημα',sysdate)");
							ResultSet rs =con6.createStatement().executeQuery("SELECT MAX(delivery.delivery_id) FROM delivery");
							while(rs.next()) {
								currentDeliveryId =rs.getString(1);
							}
						} catch (ClassNotFoundException | SQLException e8) {
							e8.printStackTrace();
						}
					}
				try {
					Connection con7 = ConnectDB.getConnection();
					
					con7.createStatement().executeQuery("INSERT INTO ORDERS(orders.order_id, orders.order_date, orders.total_price, orders.total_vat, orders.fk_customer_id, orders.fk_order_status_string, orders.fk_payment_id, orders.fk_delivery_id) VALUES(default,sysdate,"+total.getText().toString()+","+totalVat.getText().toString()+","+existingCustomerId+","+"'Έχει καταχωρηθεί',"+currentPaymentId+","+currentDeliveryId+")");
					
					ResultSet rs2 = con7.createStatement().executeQuery("SELECT MAX(ORDER_ID) FROM ORDERS");
					while (rs2.next()) {
						currentOrderId =rs2.getString(1);
					}
					}
					catch(SQLException|ClassNotFoundException ex){
						ex.printStackTrace();
					}
			}
			else {
				Alert alert = new Alert(AlertType.WARNING);
				alert.setContentText("Σφάλμα παρακαλώ εισάγετε όλα τα στοιχεία");
			}
		
		}
		else if(chooseSearchCustomer.isSelected()) {
			if(chooseCash.isSelected()) {
				try {
					Connection con = ConnectDB.getConnection();
					con.createStatement().executeQuery("INSERT INTO PAYMENT(payment_id,fk_payment_type_id,payment_transaction_date,payment_status)"
							+ " VALUES(default,10,null,'Εκκρεμεί')");
					ResultSet rs =con.createStatement().executeQuery("SELECT MAX(payment.payment_id) FROM payment");
					while(rs.next()) {
						currentPaymentId =rs.getString(1);
					}
				} catch (ClassNotFoundException | SQLException e3) {
					e3.printStackTrace();
				}
			}
			else if(chooseCard.isSelected()){
				try {
					Connection con1 = ConnectDB.getConnection();
					con1.createStatement().executeQuery("INSERT INTO PAYMENT(payment_id,fk_payment_type_id,payment_transaction_date,payment_status)"
							+ " VALUES(default,20,sysdate,'Πληρώθηκε')");
					ResultSet rs =con1.createStatement().executeQuery("SELECT MAX(payment.payment_id) FROM payment");
					while(rs.next()) {
						currentPaymentId =rs.getString(1);
					}
				} catch (ClassNotFoundException | SQLException e4) {
					e4.printStackTrace();
				}
			}
			else if(chooseDeposit.isSelected()){
				try {
					Connection con2 = ConnectDB.getConnection();
					con2.createStatement().executeQuery("INSERT INTO PAYMENT(payment_id,fk_payment_type_id,payment_transaction_date,payment_status)"
							+ " VALUES(default,30,sysdate,'Πληρώθηκε')");
					ResultSet rs =con2.createStatement().executeQuery("SELECT MAX(payment.payment_id) FROM payment");
					while(rs.next()) {
						currentPaymentId =rs.getString(1);
					}
				} catch (ClassNotFoundException | SQLException e5) {
					e5.printStackTrace();
				}
			}
			else if(choosePaypal.isSelected()){
				try {
					Connection con3 = ConnectDB.getConnection();
					con3.createStatement().executeQuery("INSERT INTO PAYMENT(payment_id,fk_payment_type_id,payment_transaction_date,payment_status)"
							+ " VALUES(default,40,sysdate,'Πληρώθηκε')");
					ResultSet rs =con3.createStatement().executeQuery("SELECT MAX(payment.payment_id) FROM payment");
					while(rs.next()) {
						currentPaymentId =rs.getString(1);
					}
				} catch (ClassNotFoundException | SQLException e6) {
					e6.printStackTrace();
				}
			}
			if (chooseCourier.isSelected()) {
				try {
					Connection con4 = ConnectDB.getConnection();
					con4.createStatement().executeQuery("INSERT INTO DELIVERY(delivery_id,delivery_type,departure_date)"
							+ " VALUES(default,'Αποστολή με κόυριερ',sysdate)");
					ResultSet rs =con4.createStatement().executeQuery("SELECT MAX(delivery.delivery_id) FROM delivery");
					while(rs.next()) {
						currentDeliveryId =rs.getString(1);
					}
				} catch (ClassNotFoundException | SQLException e7) {
					e7.printStackTrace();
				}
			}
			if (chooseReceiveByShop.isSelected()) {
				try {
					Connection con5 = ConnectDB.getConnection();
					con5.createStatement().executeQuery("INSERT INTO DELIVERY(delivery_id,delivery_type,departure_date)"
							+ " VALUES(default,'Παραλαβή από το κατάστημα',sysdate)");
					ResultSet rs =con5.createStatement().executeQuery("SELECT MAX(delivery.delivery_id) FROM delivery");
					while(rs.next()) {
						currentDeliveryId =rs.getString(1);
					}
				} catch (ClassNotFoundException | SQLException e8) {
					e8.printStackTrace();
				}
			}
			try {
				Connection con = ConnectDB.getConnection();
				con.createStatement().executeQuery("INSERT INTO ORDERS(orders.order_id, orders.order_date, orders.total_price, orders.total_vat, orders.fk_customer_id, orders.fk_order_status_string, orders.fk_payment_id, orders.fk_delivery_id) VALUES(default,sysdate,"+total.getText().toString()+","+totalVat.getText().toString()+","+existingCustomerId+","+"'Έχει καταχωρηθεί',"+currentPaymentId+","+currentDeliveryId+")");
				ResultSet rs3 = con.createStatement().executeQuery("SELECT max(order_id) FROM ORDERS");
				while(rs3.next()) {
					currentOrderId = rs3.getString(1);
				}
			} catch (ClassNotFoundException | SQLException e2) {
				e2.printStackTrace();
			}
		}
		int i = 0;
			for(Basket basket1:obslist2) {
				
				obslist2.get(i);
				++i;
				try {
					Connection con = ConnectDB.getConnection();
					System.out.println(currentOrderId+basket1.getProductId().toString()+basket1.getProductQuantity().toString()+basket1.getProductUnitPrice()+basket1.getProductName().toString());
					con.createStatement().executeQuery("INSERT INTO ORDER_ITEMS(order_id,product_id,quantity,partial_price,product_name) VALUES"
							+ "("+currentOrderId+","+basket1.getProductId().toString()+","+basket1.getProductQuantity().toString()+","+basket1.getProductUnitPrice()+",'"+basket1.getProductName().toString()+"')");
					
				} catch (ClassNotFoundException | SQLException e10) {
					e10.printStackTrace();
				}
			}
		Alert successAlert = new Alert(AlertType.CONFIRMATION);
		successAlert.setTitle("Προειδοποίηση");
		successAlert.setContentText("H παραγγελία προστεθηκε επιτυχώς");
		successAlert.showAndWait();
	}
	
}
	

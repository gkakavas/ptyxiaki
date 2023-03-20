package application;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;


public class OrderManagementData {
private StringProperty id;
private StringProperty date;
private StringProperty fName;
private StringProperty lName;
private StringProperty phone;
private StringProperty addr;
private StringProperty ammount;
private StringProperty orderStatus;


public OrderManagementData(String order_id, String order_date, String f_name, String l_name, String phone_num, String address, String total_price,
		 String fk_order_status_string) {
	this.id = new SimpleStringProperty(order_id);
	this.date = new SimpleStringProperty(order_date);
	this.fName = new SimpleStringProperty(f_name);
	this.lName = new SimpleStringProperty(l_name);
	this.phone = new SimpleStringProperty(phone_num);
	this.addr = new SimpleStringProperty(address);
	this.ammount = new SimpleStringProperty(total_price);
	this.orderStatus = new SimpleStringProperty(fk_order_status_string);
}

public OrderManagementData(String order_id,String fk_order_status_string) {
	this.id = new SimpleStringProperty(order_id);
	this.orderStatus = new SimpleStringProperty(fk_order_status_string);
	
}

//id
public StringProperty idProperty(){
	return this.id;
}

public String getId() {
	return this.idProperty().get();
}

public void setId(String id) {
	this.idProperty().set(id);
}


//date
public StringProperty dateProperty() {
	return this.date;
}

public String getDate() {
	return this.dateProperty().get();
}

public void setDate(String date) {
	this.dateProperty().set(date); 
}


//fName
public StringProperty fNameProperty() {
	return this.fName;
	
}
public String getfName() {
	return this.fNameProperty().get();
}

public void setfName(String fName) {
	this.fNameProperty().set(fName); 
}


//lName
public StringProperty lNameProperty() {
	return this.lName;
	
}

public String getlName() {
	return this.lNameProperty().get();
}

public void setlName(String lName) {
	this.lNameProperty().set(lName);
}


//phone
public StringProperty phoneProperty() {
	return this.phone;
}

public String getPhone() {
	return this.phoneProperty().get();
}

public void setPhone(String phone) {
	this.phoneProperty().set(phone);
}


//addr
public StringProperty addrProperty() {
	return this.addr;
}

public String getAddr() {
	return this.addrProperty().get();
}

public void setAddr(String addr) {
	this.addrProperty().set(addr);
}


//ammount
public StringProperty ammountProperty() {
	return this.ammount;
}
public String getAmmount() {
	return this.ammountProperty().get();
}

public void setAmmount(String ammount) {
	this.ammountProperty().set(ammount);
}


//orderStatus
public StringProperty orderStatusProperty() {
	return this.orderStatus;
}

public String getOrderStatus() {
	return this.orderStatusProperty().get();
}


public void setOrderStatus(String orderStatus) {
	this.orderStatusProperty().set(orderStatus);
}

}


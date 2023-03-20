package application;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class CustomerManagementData {
private StringProperty customerId;
private StringProperty fName;
private StringProperty lName;
private StringProperty companyName;
private StringProperty address;
private StringProperty city;
private StringProperty region;
private StringProperty zip;
private StringProperty phone;
private StringProperty email;

public CustomerManagementData(String customer_id,String f_name,String l_name,String company_name,
		String ADDRESS,String CITY,String REGION,String ZIP,String phone_num,String EMAIL) {
	this.customerId = new SimpleStringProperty(customer_id);
	this.fName = new SimpleStringProperty(f_name);
	this.lName = new SimpleStringProperty(l_name);
	this.companyName = new SimpleStringProperty(company_name);
	this.address = new SimpleStringProperty(ADDRESS);
	this.city = new SimpleStringProperty(CITY);
	this.region = new SimpleStringProperty(REGION);
	this.zip = new SimpleStringProperty(ZIP);
	this.phone = new SimpleStringProperty(phone_num);
	this.email = new SimpleStringProperty(EMAIL);
}

//customerId
public StringProperty customerIdProperty(){
	return this.customerId;
}

public String getCustomerId() {
	return this.customerIdProperty().get();
}

public void setCustomerId(String customerId) {
	this.customerIdProperty().set(customerId);
}

//fName
public StringProperty fNameProperty(){
	return this.fName;
}

public String getFName() {
	return this.fNameProperty().get();
}

public void setFName(String fName) {
	this.fNameProperty().set(fName);
}

//lName
public StringProperty lNameProperty(){
	return this.lName;
}

public String getLName() {
	return this.lNameProperty().get();
}

public void setLName(String lName) {
	this.lNameProperty().set(lName);
}

//companyName
public StringProperty companyNameProperty(){
	return this.companyName;
}

public String getCompanyName() {
	return this.companyNameProperty().get();
}

public void setCompanyName(String companyName) {
	this.companyNameProperty().set(companyName);
}

//address
public StringProperty addressProperty(){
	return this.address;
}

public String getAddress() {
	return this.addressProperty().get();
}

public void setAddress(String address) {
	this.addressProperty().set(address);
}
//city
public StringProperty cityProperty(){
	return this.city;
}

public String getCity() {
	return this.cityProperty().get();
}

public void setCity(String city) {
	this.cityProperty().set(city);
}
//region
public StringProperty regionProperty(){
	return this.region;
}

public String getRegion() {
	return this.regionProperty().get();
}

public void setRegion(String region) {
	this.regionProperty().set(region);
}
//zip
public StringProperty zipProperty(){
	return this.zip;
}

public String getZip() {
	return this.zipProperty().get();
}

public void setZip(String zip) {
	this.zipProperty().set(zip);
}
//phone
public StringProperty phoneProperty(){
	return this.phone;
}

public String getPhone() {
	return this.phoneProperty().get();
}

public void setPhone(String phone) {
	this.phoneProperty().set(phone);
}
//email
public StringProperty emailProperty(){
	return this.email;
}

public String getEmail() {
	return this.emailProperty().get();
}

public void setEmail(String email) {
	this.emailProperty().set(email);
}

}	

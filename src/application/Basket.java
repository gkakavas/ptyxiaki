package application;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Basket {
private StringProperty productId;
private StringProperty productName;
private StringProperty productUnitPrice;
private StringProperty productQuantity;
private StringProperty productVat;


public Basket() {

}

public Basket(String product_name,String product_unit_price,String product_quantity) {
	this.productName = new SimpleStringProperty(product_name);
	this.productUnitPrice = new SimpleStringProperty(product_unit_price);
	this.productQuantity = new SimpleStringProperty(product_quantity);
	
}

public Basket(String product_id,String product_name,String product_unit_price,String product_quantity, String VAT) {
	this.productId = new SimpleStringProperty(product_id);
	this.productName = new SimpleStringProperty(product_name);
	this.productUnitPrice = new SimpleStringProperty(product_unit_price);
	this.productQuantity = new SimpleStringProperty(product_quantity);
	this.productVat = new SimpleStringProperty(VAT);
}

//productId
public StringProperty productIdProperty() {
	return this.productId;
}

public String getProductId() {
	return this.productIdProperty().get();
}

public void setProductId(String product_id) {
	this.productIdProperty().set(product_id);
}

//productName
public StringProperty productNameProperty() {
	return this.productName;
}

public String getProductName() {
	return this.productNameProperty().get();
}

public void setProductName(String productName) {
	this.productNameProperty().set(productName);
}
//productUnitPrice
public StringProperty productUnitPriceProperty() {
	return this.productUnitPrice;
}

public String getProductUnitPrice() {
	return this.productUnitPriceProperty().get();
}

public void setProductUnitPrice(String productUnitPrice) {
	this.productUnitPriceProperty().set(productUnitPrice);
}
//productQuantity
public StringProperty productQuantityProperty() {
	return this.productQuantity;
}
public String getProductQuantity() {
	return this.productQuantityProperty().get();
}

public void setProductQuantity(String productQuantity) {
	this.productQuantityProperty().set(productQuantity);
}

//productVat
public StringProperty productVatProperty() {
	return this.productVat;
}
public String getProductVat() {
	return this.productVatProperty().get();
}

public void setProductVat(String productVat) {
	this.productVatProperty().set(productVat);
}

public float getBasketInvoicePartial(String productUnitPrice,String productQuantity,String productVat){
	float unitPrice = Float.parseFloat(productUnitPrice);
	int quantity = Integer.parseInt(productQuantity);
	int vat = Integer.parseInt(productVat);
	float partialPrice = (unitPrice*quantity)-(unitPrice*quantity)*vat/100;
	return partialPrice;	
}

public float getBasketInvoiceVat(String productUnitPrice,String productQuantity,String productVat){
	float unitPrice = Float.parseFloat(productUnitPrice);
	int quantity = Integer.parseInt(productQuantity);
	int vat = Integer.parseInt(productVat);
	float totalVat = (unitPrice*quantity)*vat/100;
	return totalVat;	
}

}

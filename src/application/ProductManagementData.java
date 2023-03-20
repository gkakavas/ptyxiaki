package application;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ProductManagementData {
private StringProperty productId;		//PRODUCT_ID
private StringProperty manufacturerCode; 			//MANUFACTURER_CODE
private StringProperty productName;			//PRODUCT_NAME
private StringProperty productType; 			//PRODUCT_TYPE
private StringProperty unitPrice;			//UNIT_PRICE
private StringProperty vat; 					//VAT
private StringProperty weight;
private StringProperty volume;					//WEIGHT
private StringProperty unitDescription;			//VOLUME
private StringProperty stock;			//UNIT_DESCRIPTION
											//STOCK
public ProductManagementData(String PRODUCT_ID, String MANUFACTURER_CODE, String PRODUCT_NAME, String PRODUCT_TYPE,
		String UNIT_PRICE, String VAT, String WEIGHT, String VOLUME, String UNIT_DESCRIPTION, String STOCK) {
	this.productId = new SimpleStringProperty(PRODUCT_ID);
	this.manufacturerCode = new SimpleStringProperty(MANUFACTURER_CODE);
	this.productName = new SimpleStringProperty(PRODUCT_NAME);
	this.productType = new SimpleStringProperty(PRODUCT_TYPE);
	this.unitPrice = new SimpleStringProperty(UNIT_PRICE);
	this.vat = new SimpleStringProperty(VAT);
	this.weight = new SimpleStringProperty(WEIGHT);
	this.volume = new SimpleStringProperty(VOLUME);
	this.unitDescription = new SimpleStringProperty(UNIT_DESCRIPTION);
	this.stock = new SimpleStringProperty(STOCK);
}
//productId
public StringProperty productIdProperty(){
	return this.productId;
}

public String getProductId() {
	return this.productIdProperty().get();
}

public void setProductId(String PRODUCT_ID) {
	this.productIdProperty().set(PRODUCT_ID);
}
//manufacturerCode
public StringProperty manufacturerCodeProperty(){
	return this.manufacturerCode;
}

public String getManufacturerCode() {
	return this.manufacturerCodeProperty().get();
}

public void setManufacturerCode(String MANUFACTURER_CODE) {
	this.manufacturerCodeProperty().set(MANUFACTURER_CODE);
}
//productName
public StringProperty productNameProperty(){
	return this.productName;
}

public String getProductName() {
	return this.productNameProperty().get();
}

public void setProductName(String PRODUCT_NAME) {
	this.productNameProperty().set(PRODUCT_NAME);
}
//productType
public StringProperty productTypeProperty(){
	return this.productType;
}

public String getProductType() {
	return this.productTypeProperty().get();
}

public void setProductType(String PRODUCT_TYPE) {
	this.productTypeProperty().set(PRODUCT_TYPE);
}
//unitPrice
public StringProperty unitPriceProperty(){
	return this.unitPrice;
}

public String getUnitPrice() {
	return this.unitPriceProperty().get();
}

public void setUnitPrice(String UNIT_PRICE) {
	this.unitPriceProperty().set(UNIT_PRICE);
}
//vat
public StringProperty vatProperty(){
	return this.vat;
}

public String getVat() {
	return this.vatProperty().get();
}

public void setVat(String VAT) {
	this.vatProperty().set(VAT);
}
//weight
public StringProperty weightProperty(){
	return this.weight;
}

public String getWeight() {
	return this.weightProperty().get();
}

public void setWeight(String WEIGHT) {
	this.weightProperty().set(WEIGHT);
}
//volume
public StringProperty volumeProperty(){
	return this.volume;
}

public String getVolume() {
	return this.volumeProperty().get();
}

public void setVolume(String VOLUME) {
	this.volumeProperty().set(VOLUME);
}
//unitDescription
public StringProperty unitDescriptionProperty(){
	return this.unitDescription;
}

public String getUnitDescription() {
	return this.unitDescriptionProperty().get();
}

public void setUnitDescription(String UNIT_DESCRIPTION) {
	this.unitDescriptionProperty().set(UNIT_DESCRIPTION);
}
//stock
public StringProperty stockProperty(){
	return this.stock;
}

public String getStock() {
	return this.stockProperty().get();
}

public void setStock(String STOCK) {
	this.stockProperty().set(STOCK);
}
}

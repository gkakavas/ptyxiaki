package application;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class CreateOrderData {

	private StringProperty productId;
	private StringProperty manufacturerCode;
	private StringProperty productName;
	private StringProperty unitPrice;
	private StringProperty vat;
	private StringProperty weight;
	private StringProperty volume;
	private StringProperty productType;

	public CreateOrderData(String product_id, String manufacturer_code, String product_name, String unit_price,
			String VAT, String WEIGTH, String VOLUME, String product_type) {
		this.productId = new SimpleStringProperty(product_id);
		this.manufacturerCode = new SimpleStringProperty(manufacturer_code);
		this.productName = new SimpleStringProperty(product_name);
		this.unitPrice = new SimpleStringProperty(unit_price);
		this.vat = new SimpleStringProperty(VAT);
		this.weight = new SimpleStringProperty(WEIGTH);
		this.volume = new SimpleStringProperty(VOLUME);
		this.productType = new SimpleStringProperty(product_type);
	}

	// id
	public StringProperty productIdProperty() {
		return this.productId;
	}

	public String getProductId() {
		return this.productIdProperty().get();
	}

	public void setProductId(String productId) {
		this.productIdProperty().set(productId);
	}

	// date
	public StringProperty manufacturerCodeProperty() {
		return this.manufacturerCode;
	}

	public String getManufacturerCode() {
		return this.manufacturerCodeProperty().get();
	}

	public void setManufacturerCode(String manufacturerCode) {
		this.manufacturerCodeProperty().set(manufacturerCode);
	}

	// productName
	public StringProperty productNameProperty() {
		return this.productName;

	}

	public String getProductName() {
		return this.productNameProperty().get();
	}

	public void setProductName(String productName) {
		this.productNameProperty().set(productName);
	}

	// unitPrice
	public StringProperty unitPriceProperty() {
		return this.unitPrice;

	}

	public String getUnitPrice() {
		return this.unitPriceProperty().get();
	}

	public void setUnitPrice(String unitPrice) {
		this.unitPriceProperty().set(unitPrice);
	}

	// vat
	public StringProperty vatProperty() {
		return this.vat;
	}

	public String getVat() {
		return this.vatProperty().get();
	}

	public void setVat(String vat) {
		this.vatProperty().set(vat);
	}

	// weight
	public StringProperty weightProperty() {
		return this.weight;
	}

	public String getWeight() {
		return this.weightProperty().get();
	}

	public void setWeight(String weight) {
		this.weightProperty().set(weight);
	}

	// volume
	public StringProperty volumeProperty() {
		return this.volume;
	}

	public String getVolume() {
		return this.volumeProperty().get();
	}

	public void setVolume(String volume) {
		this.volumeProperty().set(volume);
	}

	// productType
	public StringProperty productTypeProperty() {
		return this.productType;
	}

	public String getProductType() {
		return this.productTypeProperty().get();
	}

	public void setOrderStatus(String productType) {
		this.productTypeProperty().set(productType);
	}
}

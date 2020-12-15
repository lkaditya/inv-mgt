package sg.edu.iss.test.model;

import javax.persistence.*;

import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@Entity
public class Product {
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductDescription() {
		return productDescription;
	}

	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}

	public String getProductType() {
		return productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}

	public String getProductCategory() {
		return productCategory;
	}

	public void setProductCategory(String productCategory) {
		this.productCategory = productCategory;
	}

	public String getProductSubCategory() {
		return productSubCategory;
	}

	public void setProductSubCategory(String productSubCategory) {
		this.productSubCategory = productSubCategory;
	}

	public Inventory getInventory() {
		return inventory;
	}

	public void setInventory(Inventory inventory) {
		this.inventory = inventory;
	}

	public Supplier getSupplier() {
		return supplier;
	}

	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}

	public Brand getBrand() {
		return brand;
	}

	public void setBrand(Brand brand) {
		this.brand = brand;
	}

	public Returned getReturned() {
		return returned;
	}

	public void setReturned(Returned returned) {
		this.returned = returned;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String productName;
	private String productDescription;
	private String productType;
	private String productCategory;
	private String productSubCategory;
    @OneToOne(cascade = {CascadeType.ALL})  
    @JoinColumn(name="inventory_id")
	private Inventory inventory;
    @ManyToOne(cascade = {CascadeType.ALL})
	private Supplier supplier;
    @ManyToOne(cascade = {CascadeType.ALL})
	private Brand brand;
    @ManyToOne(cascade = {CascadeType.ALL})
	private Returned returned;
	public Product(String productName, String productDescription, String productType, String productCategory,
			String productSubCategory, Inventory inventory, Supplier supplier, Brand brand, Returned returned) {
		super();
		this.productName = productName;
		this.productDescription = productDescription;
		this.productType = productType;
		this.productCategory = productCategory;
		this.productSubCategory = productSubCategory;
		this.inventory = inventory;
		this.supplier = supplier;
		this.brand = brand;
		this.returned = returned;
	}

}

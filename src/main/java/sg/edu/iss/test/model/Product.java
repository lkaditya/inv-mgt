package sg.edu.iss.test.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
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

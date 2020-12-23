package sg.edu.iss.test.model;

import javax.persistence.*;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Size(min=5, max=100)
	private String productName;
	@Size(min=5, max=100)
	private String productDescription;
	@Size(min=2, max=100)
	private String productType;
	@Size(min=2, max=100)
	private String productCategory;
	@Size(min=2, max=100)
	private String productSubCategory;
    @OneToOne(cascade = {CascadeType.ALL})  
    @JoinColumn(name="inventory_id")
	private Inventory inventory;
    @ManyToOne
	private Supplier supplier;
    @ManyToOne
	private Brand brand;
	public Product(String productName, String productDescription, String productType, String productCategory,
			String productSubCategory, Inventory inventory, Supplier supplier, Brand brand) {
		super();
		this.productName = productName;
		this.productDescription = productDescription;
		this.productType = productType;
		this.productCategory = productCategory;
		this.productSubCategory = productSubCategory;
		this.inventory = inventory;
		this.supplier = supplier;
		this.brand = brand;

	}
	
	
}

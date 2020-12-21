package sg.edu.iss.test.model;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.validation.constraints.*;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
public class Supplier {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Size(min=5, max=100)
	private String supplierName;
	@Size(min=5, max=100)
	private String supplierAddress;
	@Pattern(regexp="\\d{6}")
	private String phone;
	@Size(min=5, max=100)
	private String email;
	//minimum order quantity 
	@Size(min=1, max=4)
	private String MOQ;
	@OneToMany(mappedBy = "supplier")
	private Collection <Product>  products;
	@ManyToMany(mappedBy ="suppliers")
	private Collection <Brand> brand;
	public Supplier(String supplierName, String supplierAddress, String phone, String email, String mOQ,
			Collection<Product> products, Collection<Brand> brand) {
		super();
		this.supplierName = supplierName;
		this.supplierAddress = supplierAddress;
		this.phone = phone;
		this.email = email;
		MOQ = mOQ;
		this.products = products;
		this.brand = brand;
	}
	public Supplier() {
		super();
		// TODO Auto-generated constructor stub
	}

}
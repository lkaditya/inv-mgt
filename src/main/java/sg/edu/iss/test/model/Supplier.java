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
	private String supplierName;
	private String supplierAddress;
	private String phone;
	@Email
	private String email;
	//minimum order quantity 
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

}
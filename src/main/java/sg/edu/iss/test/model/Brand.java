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
@NoArgsConstructor
@Entity
public class Brand {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String brandName;
	@OneToMany(mappedBy = "brand")
	private Collection <Product> products;
    @ManyToMany 
    private Collection<Supplier> suppliers;
    
	public Brand(String brandName, Collection<Product> products, Collection<Supplier> suppliers) {
		super();
		this.brandName = brandName;
		this.products = products;
		this.suppliers = suppliers;
	}
}


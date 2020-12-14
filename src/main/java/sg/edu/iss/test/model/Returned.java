package sg.edu.iss.test.model;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity	
public class Returned {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	//quantity
	private long qt;
	private String reason;	
	@OneToMany(mappedBy = "returned")
	private Collection <Product> products;
    @ManyToOne  
	private Supplier supplier;
	public Returned(long qt, String reason, Collection<Product> products, Supplier supplier) {
		super();
		this.qt = qt;
		this.reason = reason;
		this.products = products;
		this.supplier = supplier;
	}
    
    
}
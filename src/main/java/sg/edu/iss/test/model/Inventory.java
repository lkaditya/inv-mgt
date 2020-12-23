package sg.edu.iss.test.model;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.Digits;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class Inventory {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	//quantity on hand
	private long qoh;
	//reorder level
	private long rol;
	@Digits(integer=6, fraction=2)
	private double wholesale;
	//price of retail
	@Digits(integer=6, fraction=2)
	private double por;
	//price of partner
	@Digits(integer=6, fraction=2)
	private double pop;
    @OneToOne(mappedBy = "inventory")
    private Product product;
    @OneToMany(mappedBy="inventory")
    private Collection<Returned> returned;
	public Inventory(long id, long qoh, long rol, @Digits(integer = 6, fraction = 2) double wholesale, @Digits(integer = 6, fraction = 2) double por, @Digits(integer = 6, fraction = 2) double pop, Product product,
			Collection<Returned> returned) {
		super();
		this.id = id;
		this.qoh = qoh;
		this.rol = rol;
		this.wholesale = wholesale;
		this.por = por;
		this.pop = pop;
		this.product = product;
		this.returned = returned;
	}
    
    
    
	
	
}
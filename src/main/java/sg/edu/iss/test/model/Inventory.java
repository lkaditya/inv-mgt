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
import javax.validation.constraints.Min;

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
	@Min(0)
	private long qoh;
	//reorder level
	private long rol;
	private double wholesale;
	//price of retail
	private double por;
	//price of partner
	private double pop;
    @OneToOne(mappedBy = "inventory")
    private Product product;
    @OneToMany(mappedBy="inventory")
    private Collection<Returned> returned;
	public Inventory(long id, long qoh, long rol,  double wholesale, double por, double pop, Product product,
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
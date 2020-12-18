package sg.edu.iss.test.model;

import javax.persistence.*;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;

@Data
@NoArgsConstructor
@Entity
public class Inventory {
	public Inventory(long id, long qoh, long rol, String wholesale, String por, String pop, Product product, Collection<Returned> returned) {
		this.id = id;
		this.qoh = qoh;
		this.rol = rol;
		this.wholesale = wholesale;
		this.por = por;
		this.pop = pop;
		this.product = product;
		this.returned = returned;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	//quantity on hand
	private long qoh;
	//reorder level
	private long rol;
	private String wholesale;
	//price of retail
	private String por;
	//price of partner
	private String pop;
    @OneToOne(mappedBy = "inventory")
    private Product product;
	@OneToMany(mappedBy = "inventory")
	private Collection<Returned> returned;

	
	
}
package sg.edu.iss.test.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

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
	private String wholesale;
	//price of retail
	private String por;
	//price of partner
	private String pop;
    @OneToOne(mappedBy = "inventory")
    private Product product;
    @ManyToOne(cascade = {CascadeType.ALL})
	private Returned returned;
	public Inventory(long id, long qoh, long rol, String wholesale, String por, String pop, Product product,
			Returned returned) {
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
	public Inventory(long qoh, long rol, String wholesale, String por, String pop, Product product, Returned returned) {
		super();
		this.qoh = qoh;
		this.rol = rol;
		this.wholesale = wholesale;
		this.por = por;
		this.pop = pop;
		this.product = product;
		this.returned = returned;
	}
	@Override
	public String toString() {
		return "Inventory [id=" + id + ", qoh=" + qoh + ", rol=" + rol + ", wholesale=" + wholesale + ", por=" + por
				+ ", pop=" + pop + ", product=" + product + ", returned=" + returned + "]";
	}    

	
	
}
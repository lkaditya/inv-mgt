package sg.edu.iss.test.model;

import java.util.Collection;

import javax.persistence.*;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity	
public class Returned {
	public Returned(long qt, String reason, String supplierName, Inventory inventory) {
		this.qt = qt;
		this.reason = reason;
		this.supplierName = supplierName;
		this.inventory = inventory;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	//quantity
	private long qt;
	private String reason;
	private String supplierName;
	@ManyToOne(cascade = {CascadeType.ALL})
	private Inventory inventory;
//    @ManyToOne
//	private Supplier supplier;

    
    
}
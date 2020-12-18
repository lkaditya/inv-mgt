package sg.edu.iss.test.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;


import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity	
public class Returned {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	//quantity
	private long qt;
	private String reason;	
	private String supplierName;
    @ManyToOne(cascade = {CascadeType.ALL})  
	private Inventory inventory;
	public Returned(long qt, String reason, String supplierName, Inventory inventory) {
		super();
		this.qt = qt;
		this.reason = reason;
		this.supplierName = supplierName;
		this.inventory = inventory;
	}
   
}
package sg.edu.iss.test.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.Data;

@Entity
@Data
public class ProductUsage {
	
	@Id
	private long productUsageId;
	//To-Do: put the annotation of one to many in product to make it bidirectional
	@ManyToOne()
	private Product product;
	@ManyToOne()
	private RepairOrder rep;
	private int quantity;
	
	

}

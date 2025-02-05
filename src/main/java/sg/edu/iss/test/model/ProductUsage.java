package sg.edu.iss.test.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Min;

import lombok.Data;

@Entity
@Data
public class ProductUsage {
	
	public ProductUsage() {
		super();
	}
	public ProductUsage(Product product, RepairOrder rep, int quantity) {
		super();
		this.product = product;
		this.rep = rep;
		this.quantity = quantity;
	}
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long productUsageId;
	//To-Do: put the annotation of one to many in product to make it bidirectional
	@ManyToOne()
	private Product product;
	@ManyToOne()
	private RepairOrder rep;
	@ManyToOne()
	private Cart cart;
	@Min(1)
	private int quantity;
	@Override
	public String toString() {
		return productUsageId + ", " + product.getProductName().replace(",", "-") + ", " + quantity;


	}
}

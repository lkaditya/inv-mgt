package sg.edu.iss.test.model;

import java.util.Collection;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Data;

@Entity
@Data
public class Customer {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long customerId;
	private String name;
	private int mobile;
	@OneToMany(mappedBy="customer")
	private List<RepairOrder>orders;
	@OneToMany(mappedBy = "customer")
	private Collection <Cart> carts;
	public Customer(String name, int mobile, List<RepairOrder> orders, Collection<Cart> carts) {
		super();
		this.name = name;
		this.mobile = mobile;
		this.orders = orders;
		this.carts = carts;
	}
	
	
}

package sg.edu.iss.test.model;

import java.util.Collection;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.*;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Customer {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long customerId;
	@NotEmpty
	@Size (min=5, max=50)
	private String name;
	@Pattern(regexp="\\d{8}")
	private String mobile;
	@OneToMany(mappedBy="customer")
	private List<RepairOrder>orders;

	public Customer(String name, @Pattern(regexp = "\\d{8}") String mobile, List<RepairOrder> orders) {
		super();
		this.name = name;
		this.mobile = mobile;
		this.orders = orders;

	}
	
	public Customer(String name, @Pattern(regexp = "\\d{8}") String mobile) {
		super();
		this.name = name;
		this.mobile = mobile;
	}
	
	
}

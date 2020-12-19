package sg.edu.iss.test.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
@Entity
public class Cart {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	@OneToMany(cascade=CascadeType.ALL, mappedBy="cart")
	private List<ProductUsage>usage;
	@OneToOne
	private User user;
	@OneToOne
	private Customer customer;
	@DateTimeFormat (pattern="yyyy-MM-dd")
	private LocalDate date;
	

	public Cart() {
		usage= new ArrayList<ProductUsage>();
	}
	
	public void addToCart(ProductUsage p) {
		this.usage.add(p);
	}

}

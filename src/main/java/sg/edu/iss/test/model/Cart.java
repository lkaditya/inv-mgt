package sg.edu.iss.test.model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

import lombok.Data;

@Data
@Entity
public class Cart {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	@ManyToMany
	private List<ProductUsage>usage;
	@OneToOne
	private User user;
	@OneToOne
	private Customer customer;
	private LocalDate date;
	

	public Cart() {
		// TODO Auto-generated constructor stub
	}

}

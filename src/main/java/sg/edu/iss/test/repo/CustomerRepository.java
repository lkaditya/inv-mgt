package sg.edu.iss.test.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import sg.edu.iss.test.model.Customer;
import sg.edu.iss.test.model.User;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
	public Customer findByName(String un);
}

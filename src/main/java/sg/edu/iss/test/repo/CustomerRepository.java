package sg.edu.iss.test.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import sg.edu.iss.test.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

	List<Customer>findCustomerByName(String name);
}

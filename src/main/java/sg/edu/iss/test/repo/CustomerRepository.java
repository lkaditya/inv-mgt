package sg.edu.iss.test.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import sg.edu.iss.test.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

}

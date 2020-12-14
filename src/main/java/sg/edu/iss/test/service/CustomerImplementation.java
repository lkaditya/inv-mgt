package sg.edu.iss.test.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sg.edu.iss.test.model.Customer;
import sg.edu.iss.test.repo.CustomerRepository;

@Service
public class CustomerImplementation implements CustomerInterface {

	@Autowired
	CustomerRepository crepo;
	
	@Override
	public void createCustomer(Customer customer) {
		crepo.save(customer);
	}

	@Override
	public void updateCustomer(Customer customer) {
		crepo.save(customer);	
	}

	@Override
	public List<Customer> listAllCustomer() {
		return crepo.findAll();
	}

	@Override
	public void deleteCustomer(Customer customer) {
		crepo.delete(customer);
	}

	@Override
	public Customer findByName(String name) {
		return crepo.findByName(name);
	}

}

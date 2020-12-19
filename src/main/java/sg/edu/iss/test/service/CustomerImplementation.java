package sg.edu.iss.test.service;

import java.util.ArrayList;
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
	public void deleteCustomer(Long customerId) {
		crepo.delete(crepo.findCustomerByCustomerId(customerId));
	}

	@Override
	public Customer findByName(String name) {
		return crepo.findCustomerByName(name);
	}
	
	@Override
	public Customer findCustomerByCustomerId(Long customerId) {
		return crepo.findCustomerByCustomerId(customerId);
	}

	@Override
	public List<String> listAllCustomerNames() {
		List<Customer>group=crepo.findAll();
		List<String> names= new ArrayList<String>();
		group.stream().forEach(x->names.add(x.getName()));
		return names;
	}

}
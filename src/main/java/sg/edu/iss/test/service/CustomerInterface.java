package sg.edu.iss.test.service;

import java.util.List;

import sg.edu.iss.test.model.Customer;

public interface CustomerInterface {

	public void createCustomer(Customer customer);
	public void updateCustomer(Customer customer);
	public List<Customer> listAllCustomer();
	public void deleteCustomer(Customer customer);
	//public boolean authenticate(Customer customer);
	public Customer findByName(String name);
}
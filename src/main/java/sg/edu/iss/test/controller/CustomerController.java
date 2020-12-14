package sg.edu.iss.test.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import sg.edu.iss.test.model.Customer;
import sg.edu.iss.test.model.Product;
import sg.edu.iss.test.service.CatalogueImplementation;
import sg.edu.iss.test.service.CustomerImplementation;
import sg.edu.iss.test.service.CustomerInterface;

@Controller
@RequestMapping("/customer")
public class CustomerController {
	
	@Autowired
	private CustomerInterface cservice;
	
	@Autowired
	public void setCustomer(CustomerImplementation customer) {
		this.cservice = customer;
	}
	
	@RequestMapping(value = "/showcustomerform", method = RequestMethod.GET)
	public String showForm(Model model) {
		Customer customer = new Customer();
		model.addAttribute("customer", customer);
		return "customerform";
	}
	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(@ModelAttribute("customer") Customer customer, BindingResult bindingResult, Model model) {
		cservice.createCustomer(customer);
		return "forward:/customer/list";
	}
	
	@RequestMapping(value = "/list")
	public String list(Model model) {
		List<Customer> plist = cservice.listAllCustomer();
		model.addAttribute("plist", plist);
		return "customer";
	}

	
	
	
}

package sg.edu.iss.test.controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import sg.edu.iss.test.model.Customer;
import sg.edu.iss.test.model.Inventory;
import sg.edu.iss.test.model.User;
import sg.edu.iss.test.service.CustomerImplementation;
import sg.edu.iss.test.service.CustomerInterface;

@Controller
@RequestMapping("/customer")
public class CustomerController {

	@Autowired
	CustomerInterface cservice;
	
	@Autowired
	public void setCustomerImplementation(CustomerImplementation cimpl) {
		this.cservice = cimpl;
	}
	
	@RequestMapping(value = "/viewcustomers", method = RequestMethod.GET)
	public String list(Model model) {
		List<Customer> clist = cservice.listAllCustomer();
		model.addAttribute("clist", clist);
		return "customer";
	}
	
	@RequestMapping(value = "/customerform", method = RequestMethod.GET)
	public String add(Model model) {
		Customer customer = new Customer();
		model.addAttribute("customer", customer);
		return "customerform";
	}
	

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(@ModelAttribute("Customer") Customer customer, BindingResult bindingResult, Model model) {
		cservice.createCustomer(customer);
		return "redirect:/customer/viewcustomers";
	}
//
//	@RequestMapping(value = "/list")
//	public String list(Model model) {
//		List<Inventory> ilist = iservice.list();
//		model.addAttribute("ilist", ilist);
//		return "index";
//	}	
//	
//	
//	@RequestMapping(value= "/edit/{id}")
//	public String editForm(@PathVariable("id") Long id, Model model) {
//		Optional<Inventory> inventoryById = iservice.findInventoryById(id);
//		model.addAttribute("inventory", inventoryById
//				);
//		return "inventoryform";
//	}
//	
//	@RequestMapping(value = "/delete")
//	public String deleteInventory(Long productID) {
//
//		iservice.deleteInventory(productID);
//		return "redirect:/inventory/list";
//	}
	
	
}
	

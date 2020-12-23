package sg.edu.iss.test.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import sg.edu.iss.test.model.Customer;
import sg.edu.iss.test.model.RepairOrder;
import sg.edu.iss.test.repo.RepairOrderRepository;
import sg.edu.iss.test.service.CustomerImplementation;
import sg.edu.iss.test.service.CustomerService;

@Controller
@RequestMapping("/customer")
public class CustomerController {

	@Autowired
	CustomerService cservice;
	
	@Autowired
	RepairOrderRepository repairrepo;
	
	@Autowired
	public void setCustomerImplementation(CustomerImplementation cimpl) {
		this.cservice = cimpl;
	}
	
	@RequestMapping(value = "/viewcustomers", method = RequestMethod.GET)
	public String list(Model model) {
		List<Customer> clist = cservice.listAllCustomer();
		model.addAttribute("clist", clist);
		model.addAttribute("control","customer");
		return "customer";
	}
	
	@RequestMapping(value = "/customerform", method = RequestMethod.GET)
	public String add(Model model) {
		Customer customer = new Customer();
		model.addAttribute("customer", customer);
		model.addAttribute("control","customer");
		return "customerform";
	}
	

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(@ModelAttribute("Customer") Customer customer, BindingResult bindingResult, Model model) {
		cservice.createCustomer(customer);
		return "redirect:/customer/viewcustomers";
	}


	@RequestMapping(value= "/edit/{id}")
	public String editForm(@PathVariable("id") Long id, Model model) {
		Customer customer = cservice.findCustomerByCustomerId(id);
		model.addAttribute("customer", customer);
		model.addAttribute("control","customer");
		return "customerform";
	}
	
	@RequestMapping(value = "/delete")
	public String deleteCustomer(Long customerId, Model model) {
		List<RepairOrder> re = repairrepo.findRepairOrderByCid(customerId);
		if (re.size()>0){
			  model.addAttribute("msg","Can not delete! There are still repair order under the customer!");
			  model.addAttribute("url","/customer/viewcustomers");
		      return "erro";
		}else {
			cservice.deleteCustomer(customerId);
			return "redirect:/customer/viewcustomers";
		}
		
	}
}
	

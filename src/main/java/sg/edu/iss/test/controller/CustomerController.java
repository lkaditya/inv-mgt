package sg.edu.iss.test.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import sg.edu.iss.test.model.Customer;
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
	
}
	

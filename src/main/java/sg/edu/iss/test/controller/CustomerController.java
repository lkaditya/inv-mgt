package sg.edu.iss.test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import sg.edu.iss.test.service.CustomerInterface;

@Controller
@RequestMapping("/customer")
public class CustomerController {
	
	@Autowired
	private CustomerInterface custrepo;
	
	
	
	
}

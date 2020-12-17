package sg.edu.iss.test.controller;

import java.util.List;
import java.util.Optional;

import org.hibernate.sql.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import sg.edu.iss.test.model.Inventory;
import sg.edu.iss.test.model.Returned;
import sg.edu.iss.test.service.InventoryImplementation;
import sg.edu.iss.test.service.ReturnedImplementation;
import sg.edu.iss.test.service.ReturnedInterface;


@Controller
@RequestMapping("/returned")
public class ReturnedController {
	@Autowired
	private ReturnedInterface rservice;
	
	@Autowired
	public void setReturned(ReturnedImplementation returned) {
		this.rservice = returned;
	}
	
	
	@RequestMapping(value = "/showform", method = RequestMethod.GET)
	public String showForm(Model model) {
		Returned returned = new Returned();
		model.addAttribute("returned", returned);
		return "returnedform";
	}
	
	@RequestMapping(value = "/save")
	public String save(@ModelAttribute("returned") Returned returned, BindingResult bindingResult, Model model, String productid) {
		Long a = returned.getProduct().getId();
		rservice.update(returned.getQt(),(Long.parseLong(productid)));
		rservice.save(returned);
		return "redirect:/returned/list";
	}

	@RequestMapping(value = "/list")
	public String list(Model model) {
		List<Returned> rlist = rservice.list();
		model.addAttribute("rlist", rlist);
		return "returned";
	}	
	
	
	@RequestMapping(value= "/edit/{id}")
	public String editForm(@PathVariable("id") Long id, Model model) {
		Optional<Returned> returnedById = rservice.findReturnedById(id);
		model.addAttribute("returned", returnedById
				);
		return "returnedform";
	}
	
	@RequestMapping(value = "/delete/{id}")
	public String delete(@PathVariable("id") Long id, Model model) {
		rservice.delete(rservice.findById(id));
		return "redirect:/returned/list";
	}

	
}

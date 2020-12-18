package sg.edu.iss.test.controller;

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
import sg.edu.iss.test.service.CatalogueInterface;
import sg.edu.iss.test.service.ReturnedImplementation;
import sg.edu.iss.test.service.ReturnedInterface;

import java.util.List;
import java.util.Optional;


@Controller
@RequestMapping("/returned")
public class ReturnedController {
	@Autowired
	private ReturnedInterface rservice;

	@Autowired
	public void setReturned(ReturnedImplementation returned) {
		this.rservice = returned;
	}

	public void setCatalogueInterface(CatalogueInterface catalogueInterface) {
		this.catalogueInterface = catalogueInterface;
	}

	@Autowired
	private CatalogueInterface catalogueInterface;


	@RequestMapping(value = "/showform")
	public String showForm(Model model,long id) {
		Returned returned = new Returned();
		String supplierName = catalogueInterface.findById(id).getSupplier().getSupplierName();
		System.out.println(supplierName);
		model.addAttribute("returned", returned);
		model.addAttribute("name",supplierName);
		model.addAttribute("InventoryId",id);
		return "returnedform";
	}

	@RequestMapping(value = "/save")
	public String save(@ModelAttribute("returned") Returned returned, BindingResult bindingResult, Model model, String productid,Long InventoryId) {
		Long qt = returned.getQt();
		Inventory inventory = rservice.update(qt, InventoryId);
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
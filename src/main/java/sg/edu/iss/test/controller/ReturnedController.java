package sg.edu.iss.test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import sg.edu.iss.test.model.Inventory;
import sg.edu.iss.test.model.Returned;
import sg.edu.iss.test.service.*;

import java.util.List;
import java.util.Optional;


@Controller
@RequestMapping("/returned")
public class ReturnedController {
	@Autowired
	private ReturnedService rservice;
	
	@Autowired
	public void setReturned(ReturnedImplementation returned) {
		this.rservice = returned;
	}

	public void setCatalogueInterface(CatalogueService catalogueInterface) {
		this.catalogueInterface = catalogueInterface;
	}

	@Autowired
	private CatalogueService catalogueInterface;

	public void setMailService(MailService mailService) {
		this.mailService = mailService;
	}

	@Autowired
	private MailService mailService;
	
	@Autowired
	private InventoryService invservice;


	@RequestMapping(value = "/showform")
	public String showForm(Model model,long id) {
		Returned returned = new Returned();
		Inventory inv= invservice.findInventoryById(id).get();
		returned.setInventory(inv);
		String supplierName=inv.getProduct().getSupplier().getSupplierName();
		System.out.println(supplierName);
		model.addAttribute("returned", returned);
		model.addAttribute("name",supplierName);
		model.addAttribute("InventoryId",id);
		model.addAttribute("control","inventory");
		return "returnedform";
	}
	
	@RequestMapping(value = "/save")
	public String save(@ModelAttribute("returned") Returned returned, BindingResult bindingResult, Model model) {
		Long qt = returned.getQt();
		Inventory inv=invservice.findInventoryById(returned.getInventory().getProduct().getId()).get();
		Long InventoryId=inv.getId();
		Inventory inventory = rservice.update(qt, InventoryId);

		inventory=invservice.findInventoryById(InventoryId).get();
		returned.setInventory(inventory);
		if (inventory.getQoh()<inventory.getRol()){
			String message=inventory.getProduct().getProductName()+" needs to be reordered.";
			message+="\n The quantity now is "+inventory.getQoh();
			message+="\n The minimum available quantity is "+inventory.getRol();
			message+="\n Please reorder to "+inventory.getProduct().getSupplier().getSupplierName();
			message+="\n with email= "+inventory.getProduct().getSupplier().getEmail();
			message+="\n with minimum order= "+inventory.getProduct().getSupplier().getMOQ();
			
			mailService.sendSimpleMail("Reorder Reminder",message);
		}

		rservice.save(returned);
		return "redirect:/returned/list";
	}

	@RequestMapping(value = "/list")
	public String list(Model model) {
		List<Returned> rlist = rservice.list();
		model.addAttribute("rlist", rlist);
		model.addAttribute("control","inventory");
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
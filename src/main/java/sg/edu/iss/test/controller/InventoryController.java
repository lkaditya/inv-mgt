package sg.edu.iss.test.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import sg.edu.iss.test.model.Inventory;
import sg.edu.iss.test.model.ObjectInput;
import sg.edu.iss.test.model.Product;
import sg.edu.iss.test.model.ProductUsage;
import sg.edu.iss.test.model.RepairOrder;
import sg.edu.iss.test.service.InventoryImplementation;
import sg.edu.iss.test.service.InventoryInterface;

@Controller
@RequestMapping("/inventory")
public class InventoryController {
	
	@Autowired
	private InventoryInterface iservice;
	
	@Autowired
	public void setInventory(InventoryImplementation inventory) {
		this.iservice = inventory;
	}
	
	@RequestMapping(value = "/showform", method = RequestMethod.POST)
	public String showForm(Model model) {
		List<Inventory> ilist = iservice.list();
		model.addAttribute("ilist", ilist);
		ObjectInput f = new ObjectInput();
		model.addAttribute("filter", f);
		
		return "inventoryform";
	}
	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(@ModelAttribute("inventory") Inventory inventory, BindingResult bindingResult, Model model) {
		iservice.saveInventory(inventory);
		return "redirect:/inventory/list";
	}

	@RequestMapping(value = "/list")
	public String list(Model model) {
		List<Inventory> ilist = iservice.list();
		model.addAttribute("ilist", ilist);
		return "index";
	}	
	
	
	@RequestMapping(value= "/edit/{id}")
	public String editForm(@PathVariable("id") Long id, Model model) {
		Optional<Inventory> inventoryById = iservice.findInventoryById(id);
		model.addAttribute("inventory", inventoryById
				);
		return "inventoryform";
	}
	
	@RequestMapping(value = "/delete")
	public String deleteInventory(Long productID) {

		iservice.deleteInventory(productID);
		return "redirect:/inventory/list";
	}
	
	//link when hidden search button is clicked
	@RequestMapping(value="/search",method=RequestMethod.POST)
	public String showRelevantInventory(@ModelAttribute("keyword") String keyword, Model model) {
		List<Inventory> ilist =iservice.findInventoryByKeyword(keyword);
		model.addAttribute("ilist",ilist);	
		return "index";
	}

}

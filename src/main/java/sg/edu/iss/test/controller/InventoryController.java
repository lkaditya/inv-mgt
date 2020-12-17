package sg.edu.iss.test.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import sg.edu.iss.test.model.Inventory;
import sg.edu.iss.test.model.Product;
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
		Inventory inventory = new Inventory();
		model.addAttribute("inventory", inventory);
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
//
//	@RequestMapping(value = "/return/{id}")
//	public String returnInventory(@PathVariable("id") Long id) {
//		iservice.returnInventory(iservice.findInventoryById(id));
//		return "forward:/returned/list";
//	}
}
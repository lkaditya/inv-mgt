package sg.edu.iss.test.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import sg.edu.iss.test.model.Cart;
import sg.edu.iss.test.model.Product;
import sg.edu.iss.test.model.Supplier;
import sg.edu.iss.test.model.User;
import sg.edu.iss.test.repo.SupplierRepository;
import sg.edu.iss.test.service.CartService;
import sg.edu.iss.test.service.CatalogueService;
import sg.edu.iss.test.service.SupplierServiceImplementation;
import sg.edu.iss.test.service.UserServiceImplementation;

@Controller
@RequestMapping("/admin")
public class AdminController {


	@Autowired
	private SupplierServiceImplementation supplierServices;
	@Autowired
	private UserServiceImplementation userServices;

	@Autowired
	private CatalogueService catalogueInterface;
	
	@Autowired
	private CartService cartService;

	@Autowired
	private SupplierRepository supplierRepository;

	@RequestMapping(value = "/viewusers")
	public String ListUsers(Model model) {
		model.addAttribute("Users", userServices.findAllUsers());
		model.addAttribute("control","User");
		return "users";
	}

	@RequestMapping(value = "/adduser")
	public String addUser(Model model) {
		model.addAttribute("user", new User());
		model.addAttribute("control","User");
		return "user-form";
	}

	@RequestMapping(value = "/edituser/{id}")
	public String editUser(@PathVariable("id") Long id, Model model) {
		User user = userServices.findUserById(id);
		model.addAttribute("user", user);
		model.addAttribute("control","user");
		return "user-form";
	}
	
	@RequestMapping(value = "/saveuser")
	public String saveUser(@ModelAttribute("User") @Valid User user, 
			BindingResult bindingResult,  Model model) {
		if (bindingResult.hasErrors()) {
			model.addAttribute("control","User");
			return "user-form";
		}
		 userServices.saveUser(user);
		return "forward:/admin/viewusers";
	}

	@RequestMapping(value = "/deleteuser/{id}")
	public String deleteuser(@PathVariable("id") Long id, Model model) {
		User user = userServices.findUserById(id);
		
		List<Cart> c = cartService.findCartByUsrId(id);
		if (c.size()>0){
			  model.addAttribute("msg","Can not delete! There is still cart under the user!");
			  model.addAttribute("url","/admin/viewusers");
		      return "erro";
		}else {
			userServices.deleteUser(user);
			return "forward:/admin/viewusers";
		}
		
	}
    
	
	@RequestMapping(value = "/viewsuppliers")
	public String listsuppliers(Model model) {
		model.addAttribute("Suppliers", supplierServices.findAllSuppliers());
		model.addAttribute("control","Supplier");
		return "suppliers";
	}
	@RequestMapping(value = "/addsupplier")
	public String addSupplier(Model model) {
		model.addAttribute("Supplier", new Supplier());
		model.addAttribute("control","Supplier");
		return "supplier-form";
	}
	@RequestMapping(value = "/editsupplier/{id}")
	public String editSupplier(@PathVariable("id") Long id, Model model) {
		model.addAttribute("Supplier", supplierServices.findSupplierById(id));
		model.addAttribute("control","Supplier");
		return "supplier-formForModify";
	}
	@RequestMapping(value = "/savesupplier")
	public String saveSupplier(@ModelAttribute("Supplier") @Valid Supplier supplier, 
			BindingResult bindingResult,  Model model) {
		if (bindingResult.hasErrors()) {
			return "supplier-form";
		}
		Supplier supplier1 = supplierRepository.findSupplierBySupplierName(supplier.getSupplierName());
		if (supplier1!=null){
			supplierServices.saveSupplier(supplier1);
		}else {
			supplierServices.saveSupplier(supplier);
		}

		return "forward:/admin/viewsuppliers";
	}
	@RequestMapping(value = "/savesupplierforEdit")
	public String saveSupplierforEdit(@ModelAttribute("Supplier") @Valid Supplier supplier,
	                     BindingResult bindingResult,  Model model) {
	   if (bindingResult.hasErrors()) {
	      return "supplier-form";
	   }
	      supplierServices.saveSupplier(supplier);
	   return "forward:/admin/viewsuppliers";
	}
	@RequestMapping(value = "/deletesupplier/{id}")
	public String deleteSupplier(@PathVariable("id") Long id, Model model) {
		
		String supplierName = supplierServices.findSupplierById(id).getSupplierName();
		List<Product> p = catalogueInterface.findProductBySupName(supplierName);
		if (p.size()>0){
			  model.addAttribute("msg","Can not delete! There are still products under this supplier!");
			  model.addAttribute("url","/admin/viewsuppliers");
		      return "erro";
		}else {
			supplierServices.deleteSupplier(supplierServices.findSupplierById(id));
			return "forward:/admin/viewsuppliers";
		}	
		
	}


}

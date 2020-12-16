package sg.edu.iss.test.controller;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import sg.edu.iss.test.model.Admin;
import sg.edu.iss.test.model.Mechanic;
import sg.edu.iss.test.model.Product;
import sg.edu.iss.test.model.Supplier;
import sg.edu.iss.test.service.AdminServiceImplementation;
import sg.edu.iss.test.service.MechanicServiceImplementation;
import sg.edu.iss.test.service.ProductServiceImplementation;
import sg.edu.iss.test.service.SupplierServiceImplementation;

@Controller
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private MechanicServiceImplementation mechanicServices;
	@Autowired
	private SupplierServiceImplementation supplierServices;
	@Autowired
	private AdminServiceImplementation adminServices;
	@Autowired
	private ProductServiceImplementation productServices;

	@RequestMapping(value = "/viewmechanics")
	public String listMechanic(Model model) {
		model.addAttribute("mechanics", mechanicServices.findAllMechanics());
		return "mechanics";
	}  
	@RequestMapping(value = "/addmechanic")
	public String addMechanic(Model model) {
		model.addAttribute("mechanic", new Mechanic());
		return "mechanic-form";
	}
	@RequestMapping(value = "/editmechanic/{id}")
	public String editMechanic(@PathVariable("id") Long id, Model model) {
		model.addAttribute("mechanic", mechanicServices.findMechanicById(id));
		return "mechanic-form";
	}
	
	@RequestMapping(value = "/savemechanic")
	public String saveMechanic(@ModelAttribute("mechanic") @Valid Mechanic mechanic, 
			BindingResult bindingResult,  Model model) {
		if (bindingResult.hasErrors()) {
			return "mechanic-form";
		}
		mechanicServices.saveMechanic(mechanic);
		return "forward:/admin/viewmechanics";
	}
	@RequestMapping(value = "/deletemechanic/{id}")
	public String deleteMechanic(@PathVariable("id") Long id) {
		Mechanic mechanic = mechanicServices.findMechanicById(id);
		mechanicServices.deleteMechanic(mechanic);
		return "forward:/admin/viewmechanics";
	}
	
//	@RequestMapping(value = "/list")
//	public String list(Model model) {
//		model.addAttribute("mechanics", mechanicServices.findAllMechanics());
//		return "mechanics";
//	} 
//	
	@RequestMapping("/viewadmins")
	public String ListAdmins(Model model) {
		model.addAttribute("admins", adminServices.findAllAdmins());
		return "admins";
	}

	@RequestMapping(value = "/addadmin")
	public String addAdmin(Model model) {
		model.addAttribute("admin", new Admin());
		return "admin-form";
	}
	
//	@RequestMapping(value = "/edit/{id}")
//	public String editMechanic(@PathVariable("id") Long id, Model model) {
//		model.addAttribute("member", mechanicServices.findMechanicById(id));
//		return "mechanic-form";
//	}
//	
	@RequestMapping(value = "/editadmin/{id}")
	public String editadmin(@PathVariable("id") Long id, Model model) {
		Admin admin = adminServices.findAdminById(id);
		model.addAttribute("admin", admin);
		return "admin-form";
	}
	
	@RequestMapping(value = "/saveadmin")
	public String saveAdmin(@ModelAttribute("admin") @Valid Admin admin, 
			BindingResult bindingResult,  Model model) {
		if (bindingResult.hasErrors()) {
			return "admin-form";
		}
		adminServices.saveAdmin(admin);
		return "forward:/admin/viewadmins";
	}


	@RequestMapping(value = "/deleteadmin/{id}")
	public String deleteadmin(@PathVariable("id") Long id) {
		Admin admin = adminServices.findAdminById(id);
		adminServices.deleteAdmin(admin);
		return "redirect:/admin/viewadmins";
	}
    
	
	@RequestMapping(value = "/viewsuppliers")
	public String listsuppliers(Model model) {
		model.addAttribute("suppliers", supplierServices.findAllSuppliers());
		return "suppliers";
	}
	@RequestMapping(value = "/addsupplier")
	public String addSupplier(Model model) {
		model.addAttribute("supplier", new Supplier());
		return "supplier-form";
	}
	@RequestMapping(value = "/editsupplier/{id}")
	public String editSupplier(@PathVariable("id") Long id, Model model) {
		model.addAttribute("supplier", supplierServices.findSupplierById(id));
		return "supplier-form";
	}
	@RequestMapping(value = "/savesupplier")
	public String saveSupplier(@ModelAttribute("supplier") @Valid Supplier supplier, 
			BindingResult bindingResult,  Model model) {
		if (bindingResult.hasErrors()) {
			return "supplier-form";
		}
		supplierServices.saveSupplier(supplier);
		return "forward:/admin/viewsuppliers";
	}
	@RequestMapping(value = "/deletesupplier/{id}")
	public String deleteSupplier(@PathVariable("id") Long id) {
		supplierServices.deleteSupplier(supplierServices.findSupplierById(id));
		return "forward:/admin/viewsuppliers";
	}
	
	
	@RequestMapping(value = "/viewproducts")
	public String list(Model model) {
		model.addAttribute("products", productServices.findALLProducts());
		return "products";
	}
	@RequestMapping(value = "/addproduct")
	public String addForm(Model model) {
		model.addAttribute("product", new Product());
		return "product-form";
	}
	@RequestMapping(value = "/editproduct/{id}")
	public String editForm(@PathVariable("id") Long id, Model model) {
		model.addAttribute("product", productServices.findProductById(id));
		return "product-form";
	}
	@RequestMapping(value = "/saveproduct")
	public String saveProduct(@ModelAttribute("product") @Valid Product product, 
			BindingResult bindingResult,  Model model) {
		if (bindingResult.hasErrors()) {
			return "product-form";
		}
		productServices.saveProduct(product);
		return "forward:/admin/viewproducts";
	}
	@RequestMapping(value = "/deleteproduct/{id}")
	public String deleteProduct(@PathVariable("id") Long id) {
		productServices.deleteProduct(productServices.findProductById(id));
		return "forward:/admin/viewproducts";
	}
	
}

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


import sg.edu.iss.test.model.Product;
import sg.edu.iss.test.model.Supplier;
import sg.edu.iss.test.model.User;
import sg.edu.iss.test.service.ProductServiceImplementation;
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
	private ProductServiceImplementation productServices;

	@RequestMapping(value = "/viewusers")
	public String ListUsers(Model model) {
		model.addAttribute("users", userServices.findAllUsers());
		model.addAttribute("control","user");
		return "users";
	}

	@RequestMapping(value = "/adduser")
	public String addUser(Model model) {
		model.addAttribute("user", new User());
		model.addAttribute("control","user");
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
	public String saveUser(@ModelAttribute("user") @Valid User user, 
			BindingResult bindingResult,  Model model) {
		if (bindingResult.hasErrors()) {
			model.addAttribute("control","user");
			return "user-form";
		}
		 userServices.saveUser(user);
		return "forward:/admin/viewusers";
	}

	@RequestMapping(value = "/deleteuser/{id}")
	public String deleteuser(@PathVariable("id") Long id) {
		User user = userServices.findUserById(id);
		userServices.deleteUser(user);
		return "forward:/admin/viewusers";
	}
    
	
	@RequestMapping(value = "/viewsuppliers")
	public String listsuppliers(Model model) {
		model.addAttribute("suppliers", supplierServices.findAllSuppliers());
		model.addAttribute("control","supplier");
		return "suppliers";
	}
	@RequestMapping(value = "/addsupplier")
	public String addSupplier(Model model) {
		model.addAttribute("supplier", new Supplier());
		model.addAttribute("control","supplier");
		return "supplier-form";
	}
	@RequestMapping(value = "/editsupplier/{id}")
	public String editSupplier(@PathVariable("id") Long id, Model model) {
		model.addAttribute("supplier", supplierServices.findSupplierById(id));
		model.addAttribute("control","supplier");
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
	public String deleteSupplier(@PathVariable("id") Long id, Model model) {
		
		String supplierName = supplierServices.findSupplierById(id).getSupplierName();
		List<Product> p = productServices.findProductBySupName(supplierName);
		if (p.size()>0){
			  model.addAttribute("msg","Can not delete! There are still products under this supplier!");
			  model.addAttribute("url","/admin/viewsuppliers");
		      return "erro";
		}else {
			supplierServices.deleteSupplier(supplierServices.findSupplierById(id));
			return "forward:/admin/viewsuppliers";
		}	
		
	}
	
	//====================================================================
	//TODO need to be deleted (including products.html and product-form.html as it is duplicated from catalogue controller????
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
	//=================================================================
	
	@RequestMapping(path = "/login")
	public String login(Model model) {
		User u = new User();
		model.addAttribute("user", u);
		return "login";
	}
	
	@RequestMapping(path = "/authenticate")
	public String authenticate(@ModelAttribute("user") User user, Model model, HttpSession session) {
		if(userServices.authenticate(user)) 
		{
			User u = userServices.findUserByUserName(user.getUserName());
			session.setAttribute("usession", u);
			return "redirect:/inventory/list";
		}
		else
			return "login";
	}
	
	@RequestMapping(path = "/logout")
	public String logout(HttpSession session) {
		session.removeAttribute("usession");
		return "logout";
	}
}

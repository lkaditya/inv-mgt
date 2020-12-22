package sg.edu.iss.test.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import sg.edu.iss.test.model.Cart;
import sg.edu.iss.test.model.Inventory;
import sg.edu.iss.test.model.ObjectInput;
import sg.edu.iss.test.model.Product;
import sg.edu.iss.test.model.ProductUsage;
import sg.edu.iss.test.model.User;
import sg.edu.iss.test.service.CartService;
import sg.edu.iss.test.service.InventoryImplementation;
import sg.edu.iss.test.service.InventoryInterface;
import sg.edu.iss.test.service.ProductService;
import sg.edu.iss.test.service.ProductUsageService;
import sg.edu.iss.test.service.UserService;

@Controller
@RequestMapping("/inventory")
public class InventoryController {
	
	@Autowired
	private ProductService proservice;
	@Autowired
	private InventoryInterface iservice;
	
	@Autowired
	public void setInventory(InventoryImplementation inventory) {
		this.iservice = inventory;
	}
	
	@Autowired
	private CartService cartservice;
	
	
	@Autowired
	private ProductUsageService puservice;
	
	
	@Autowired
	private UserService userservice;
	
	@RequestMapping(value = "/showform", method = RequestMethod.POST)
	public String showForm(@Valid Model model) {
		List<Inventory> ilist = iservice.list();
		model.addAttribute("ilist", ilist);
		ObjectInput f = new ObjectInput();
		model.addAttribute("filter", f);
		model.addAttribute("control","product");
		
		return "inventoryform";
	}
	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(@Valid @ModelAttribute("inventory") Inventory inventory, BindingResult bindingResult, Model model) {
		Product x= proservice.findProductById(inventory.getProduct().getId());
		inventory.setProduct(x);
		iservice.saveInventory(inventory);
		return "redirect:/inventory/list";
	}

	@RequestMapping(value = "/list")
	public String list(Model model,HttpSession session) {
		List<Inventory> ilist = iservice.list();
		//TODO: later need to replace this when the login done
		//String username="sharon";
		//User u=userservice.findUserByUserName(username);
		//session.setAttribute("user", u);
		//----------------------------------------------------
		model.addAttribute("ilist", ilist);
		model.addAttribute("control","inventory");
		return "inventorylisting";
	}	
	
	
	@RequestMapping(value= "/edit/{id}")
	public String editForm(@PathVariable("id") Long id, Model model) {
		Optional<Inventory> inventoryById = iservice.findInventoryById(id);
		model.addAttribute("inventory", inventoryById
				);
		model.addAttribute("control","inventory");
		return "inventoryform";
	}
	
	@RequestMapping(value = "/delete")
	public String deleteInventory(Long productID) {

		iservice.deleteInventory(productID);
		return "redirect:/inventory/list";
	}
	
	//link when hidden search button is clicked
	@RequestMapping(value="/search",method=RequestMethod.POST)
	public String showRelevantInventory(@Valid @ModelAttribute("keyword") String keyword, Model model) {
		List<Inventory> ilist =iservice.findInventoryByKeyword(keyword);
		model.addAttribute("ilist",ilist);	
		model.addAttribute("control","inventory");
		return "inventorylisting";
	}
	
	@RequestMapping(value="/add")
	public String add(Long inventoryid,HttpSession session) {
		User user=(User)session.getAttribute("usession");
		Cart c=cartservice.showAllCartByUserName(user.getUserName());
		//assuming the cart is 1 per user regardless of customer
		if(c!=null) {
			Product p=proservice.findProductById(inventoryid);
			ProductUsage existing= puservice.showCartProductUsageByProductName(p);
			if(existing!=null) {
				existing.setQuantity(existing.getQuantity()+1);
				puservice.addProductUsage(existing);
			}else {
				ProductUsage pu= new ProductUsage ();
				pu.setProduct(p);
				pu.setQuantity(1);
				pu.setCart(c);
				puservice.addProductUsage(pu);
				c.addToCart(pu);
			}
			cartservice.save(c);
			return "redirect:/inventory/list";
		}else {
			Cart c1 = new Cart();
			LocalDate now=LocalDate.now();
			c1.setDate(now);
			c1.setUser(user);
			//c1.addToCart(pu);
			cartservice.save(c1);
			
			Product p=proservice.findProductById(inventoryid);
			ProductUsage pu= new ProductUsage ();
			pu.setProduct(p);
			pu.setQuantity(1);
			pu.setCart(c1);
			puservice.addProductUsage(pu);
			
			//Cart c2=cartservice.showAllCartByUserName(user.getUserName());
			c1.addToCart(pu);
			cartservice.save(c1);
			return "redirect:/inventory/list";
		}		

	}

}

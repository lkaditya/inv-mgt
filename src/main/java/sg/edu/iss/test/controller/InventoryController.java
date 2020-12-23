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
import sg.edu.iss.test.model.Returned;
import sg.edu.iss.test.model.User;
import sg.edu.iss.test.service.*;

@Controller
@RequestMapping("/inventory")
public class InventoryController {


	@Autowired
	private CatalogueService catalogueInterface;

	@Autowired
	private InventoryService iservice;
	
	@Autowired
	public void setInventory(InventoryImplementation inventory) {
		this.iservice = inventory;
	}
	
	@Autowired
	private CartService cartservice;
	
	@Autowired
	private ProductUsageService puservice;
	
	@Autowired
	private ReturnedService rservice;
	
	@Autowired
	private UserService userservice;
	
	@RequestMapping(value = "/showform", method = RequestMethod.POST)
	public String showForm(Model model) {
		List<Inventory> ilist = iservice.list();
		model.addAttribute("ilist", ilist);
		ObjectInput f = new ObjectInput();
		model.addAttribute("filter", f);
		model.addAttribute("control","product");
		
		return "inventoryform";
	}
	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(@Valid @ModelAttribute("inventory") Inventory inventory, BindingResult bindingResult, Model model) {
		Product x= catalogueInterface.findById(inventory.getProduct().getId());
		inventory.setProduct(x);
		iservice.saveInventory(inventory);
		return "redirect:/inventory/list";
	}

	@RequestMapping(value = "/list")
	public String list(Model model,HttpSession session) {
		List<Inventory> ilist = iservice.list();
		//TODO: later need to replace this when the login done
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
	public String deleteInventory(Long productID, Model model) {
		List<Returned> r = rservice.findReturnedByProId(productID);
		List<ProductUsage> u = puservice.findProductUsageByProId(productID);
		if (r.size()>0 || u.size()>0){
			  model.addAttribute("msg","Can not delete! There are still return or repair recording under this product!");
			  model.addAttribute("url","/inventory/list");
		      return "erro";
		}else {
			iservice.deleteInventory(productID);
			return "redirect:/inventory/list";
		}	
		
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
			Product p=catalogueInterface.findById(inventoryid);
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
			cartservice.save(c1);
			
			Product p=catalogueInterface.findById(inventoryid);
			ProductUsage pu= new ProductUsage ();
			pu.setProduct(p);
			pu.setQuantity(1);
			pu.setCart(c1);
			puservice.addProductUsage(pu);

			c1.addToCart(pu);
			cartservice.save(c1);
			return "redirect:/inventory/list";
		}		

	}

}

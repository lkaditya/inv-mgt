package sg.edu.iss.test.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import sg.edu.iss.test.model.Cart;
import sg.edu.iss.test.model.Inventory;
import sg.edu.iss.test.model.ProductUsage;
import sg.edu.iss.test.model.RepairOrder;
import sg.edu.iss.test.service.CartImplementation;
import sg.edu.iss.test.service.CartService;
import sg.edu.iss.test.service.InventoryInterface;
import sg.edu.iss.test.service.ProductUsageService;

@RestController
@RequestMapping("/cart")
public class CartController {
	
	@Autowired
	private CartService cartservice;
	
	@Autowired
	public void setInventory(CartImplementation cart) {
		this.cartservice = cart;
	}
	
	@Autowired
	private ProductUsageService uservice;
	
	@Autowired
	private InventoryInterface inventoryservice;
	
	@RequestMapping(value="/show",method=RequestMethod.GET)
	public String showCart(Model model,HttpSession session) {
		String username=(String) session.getAttribute("username");
		Cart c= cartservice.showAllCartByUserName(username);
		RepairOrder rep= new RepairOrder();
		model.addAttribute("cart",c);
		model.addAttribute("repairrecord",rep);
		model.addAttribute("control","cart");
		return "cartpageform";
	}
	

	@RequestMapping(value="/save",method=RequestMethod.POST)
	public String saverepair(@ModelAttribute("${cart}")Cart cart,BindingResult bindingResult ,Model model) {
		RepairOrder rep = new RepairOrder();
		rep.setCustomer(cart.getCustomer());
		rep.setProductUsageList(cart.getUsage());
		rep.setRepairDate(cart.getDate());
		uservice.saveRepairOrder(rep);
		List<ProductUsage>details=cart.getUsage();
		for(ProductUsage i:details) {
			i.setRep(rep);
			uservice.addProductUsage(i);
			
			//reduce the inventory amount
			Inventory a= inventoryservice.findInventoryByProductName(i.getProduct().getProductName());
			long currentAmount=a.getQoh();
			long used=currentAmount-i.getQuantity(); 
			a.setQoh(currentAmount-used);
			inventoryservice.saveInventory(a);
		}		
		return "recordrepair";
	}
	

	

}

package sg.edu.iss.test.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import sg.edu.iss.test.model.Cart;
import sg.edu.iss.test.model.Inventory;
import sg.edu.iss.test.model.ProductUsage;
import sg.edu.iss.test.model.RepairOrder;
import sg.edu.iss.test.service.CartImplementation;
import sg.edu.iss.test.service.CartService;
import sg.edu.iss.test.service.InventoryInterface;
import sg.edu.iss.test.service.ProductUsageService;

@Controller
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
	
	@RequestMapping(value="/show")
	//TODO: don't forget implement session later, remove the hardcoded user name
	public String showCart(Model model,HttpSession session) {
		//String username=(String) session.getAttribute("username");
		//later put session
		String username="frank";
		Cart c= cartservice.showAllCartByUserName(username);
		System.out.println(c.getCustomer().getName());
		model.addAttribute("cart",c);
		model.addAttribute("control","cart");
		return "cartpageform";
	}
	

	@RequestMapping(value="/save",method=RequestMethod.POST)
	public String saverepair(@ModelAttribute("${cart}")Cart cart,BindingResult bindingResult ,Model model) {
		RepairOrder rep = new RepairOrder();
		rep.setCustomer(cart.getCustomer());
		rep.setRepairDate(cart.getDate());
		
		
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
		rep.setProductUsageList(details);
		uservice.saveRepairOrder(rep);
		return "recordrepair";
	}
	

	

}

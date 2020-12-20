package sg.edu.iss.test.controller;


import java.util.ArrayList;
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
import sg.edu.iss.test.model.Customer;
import sg.edu.iss.test.model.Inventory;
import sg.edu.iss.test.model.ObjectInput;
import sg.edu.iss.test.model.Product;
import sg.edu.iss.test.model.ProductUsage;
import sg.edu.iss.test.model.RepairOrder;
import sg.edu.iss.test.service.CartImplementation;
import sg.edu.iss.test.service.CartService;
import sg.edu.iss.test.service.CustomerInterface;
import sg.edu.iss.test.service.InventoryInterface;
import sg.edu.iss.test.service.ProductService;
import sg.edu.iss.test.service.ProductUsageService;
import sg.edu.iss.test.service.UserService;

@Controller
@RequestMapping("/cart")
public class CartController {
	
	@Autowired
	private CartService cartservice;
	
	@Autowired
	private CustomerInterface custservice;
	
	@Autowired
	public void setInventory(CartImplementation cart) {
		this.cartservice = cart;
	}
	
	@Autowired
	private ProductUsageService uservice;
	
	@Autowired
	private ProductService proservice;
	
	@Autowired
	private UserService userservice;
	
	@Autowired
	private InventoryInterface inventoryservice;
	
	
	@RequestMapping(value="/show")
	//TODO: don't forget implement session later, remove the hardcoded user name
	public String showCart(Model model,HttpSession session) {
		//String username=(String) session.getAttribute("username");
		//later put session
		//assuming
		String username="sharon";
		Cart c= cartservice.showAllCartByUserName(username);
		if(c!=null) {
			List<ProductUsage> group= c.getUsage(); //currently null
			ObjectInput usageform= new ObjectInput();
			usageform.setUsages(group);
			usageform.setCart(c);
			model.addAttribute("usages",usageform);
		}else {
			ObjectInput usageform= new ObjectInput();
			usageform.setCart(new Cart());
			model.addAttribute("usages",usageform);	
		}
		model.addAttribute("control","inventory");
		model.addAttribute("customers",custservice.listAllCustomerNames());
		return "cartpageform";
	}
	

	@RequestMapping(value="/save",method=RequestMethod.POST)
	public String saverepair(@ModelAttribute("${usages}")ObjectInput obj,BindingResult bindingResult ,Model model) {
		
		RepairOrder rep = new RepairOrder();
		String namecust=obj.getCart().getCustomer().getName();
		Customer cu=custservice.findByName(namecust);
		rep.setCustomer(cu);
		//custservice.createCustomer(cu);
		rep.setRepairDate(obj.getCart().getDate());
		uservice.saveRepairOrder(rep);
		
		List<ProductUsage>formdata=obj.getUsages();
		List<Integer> quantityusages= new ArrayList<Integer>();
		formdata.stream().forEach(x->quantityusages.add(x.getQuantity()));
		
		long cartid=obj.getCart().getId();
		List<ProductUsage> details=uservice.showProductUsagesByCartId(cartid);
		//for(ProductUsage i:details) 
		for(int x=0;x<details.size();x++){
			ProductUsage i=details.get(x);
			long productid=i.getProduct().getId();
			i.setRep(rep);
			i.setQuantity(quantityusages.get(x));
			Product pro=proservice.findProductById(productid);
			i.setProduct(pro);
			i.setCart(null);
			uservice.addProductUsage(i);
			
			//reduce the inventory amount
			Inventory a= inventoryservice.findInventoryByProductName(i.getProduct().getProductName());
			long currentAmount=a.getQoh();
			long rem=currentAmount-i.getQuantity(); 
			a.setQoh(rem);
			inventoryservice.saveInventory(a);
		}
		rep.setProductUsageList(details);
		//clean up the cart
//		long cartid=obj.getCart().getId();
//		List<ProductUsage> cusage=uservice.showProductUsagesByCartId(cartid);
//		for (ProductUsage x :cusage) {
//			uservice.deleteProductUsage(x);
//		}
		//Cart x= cartservice.findCartById(cartid);
		//cartservice.delete(x);

		return "redirect:/repair/showrecord";
	}
	

	

}

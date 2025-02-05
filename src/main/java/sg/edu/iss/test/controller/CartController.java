package sg.edu.iss.test.controller;


import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

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
import sg.edu.iss.test.model.User;
import sg.edu.iss.test.service.*;

@Controller
@RequestMapping("/cart")
public class CartController {
	
	@Autowired
	private CartService cartservice;
	
	@Autowired
	private CustomerService custservice;
	
	@Autowired
	public void setInventory(CartImplementation cart) {
		this.cartservice = cart;
	}
	
	@Autowired
	private ProductUsageService uservice;

	@Autowired
	private CatalogueService catalogueInterface;

	@Autowired
	private InventoryService inventoryservice;

	@Autowired
	private MailService mailService;

	@RequestMapping(value="/show")
	public String showCart(Model model,HttpSession session) {
		User user=(User)session.getAttribute("usession");
		String username=user.getUserName();
		Cart c= cartservice.showAllCartByUserName(username);
		if(c!=null) {
			List<ProductUsage> group= c.getUsage(); //currently null
			ObjectInput usageform= new ObjectInput();
			usageform.setUsages(group);
			usageform.setCart(c);
			model.addAttribute("usages",usageform);
		}else {
			ObjectInput usageform= new ObjectInput();
			List<ProductUsage> group= new ArrayList<ProductUsage>();
			usageform.setUsages(group);
			usageform.setCart(new Cart());
			model.addAttribute("usages",usageform);	
		}
		model.addAttribute("control","inventory");
		model.addAttribute("customers",custservice.listAllCustomerNames());
		return "cartpageform";
	}	

	@RequestMapping(value="/save",method=RequestMethod.POST)
	public String saverepair(@Valid @ModelAttribute("usages")ObjectInput obj,BindingResult bindingResult ,Model model) {
		
		if(bindingResult.hasErrors()) {
			model.addAttribute("customers",custservice.listAllCustomerNames());
			return "cartpageform";
		}
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
		int unitofusage=0; 
		for(int x=0;x<details.size();x++){
			int quantity=quantityusages.get(x);
			if(quantity>0) {
				unitofusage++;
				ProductUsage i=details.get(x);
				long productid=i.getProduct().getId();
				i.setRep(rep);
				i.setQuantity(quantity);
				Product pro=catalogueInterface.findById(productid);
				i.setProduct(pro);
				i.setCart(null);
				uservice.addProductUsage(i);
				
				//reduce the inventory amount
				Inventory a= inventoryservice.findInventoryByProductName(i.getProduct().getProductName());
				long currentAmount=a.getQoh();
				long rem=currentAmount-i.getQuantity(); 
				a.setQoh(rem);
				if (a.getQoh()<a.getRol()){
					String message=a.getProduct().getProductName()+" needs to be reordered.";
					message+="\n The quantity now is "+a.getQoh();
					message+="\n The minimum available quantity is "+a.getRol();
					message+="\n Please reorder to "+a.getProduct().getSupplier().getSupplierName();
					message+="\n with email= "+a.getProduct().getSupplier().getEmail();
					message+="\n with minimum order= "+a.getProduct().getSupplier().getMOQ();

					mailService.sendSimpleMail("Reorder Reminder",message);
				}
				inventoryservice.saveInventory(a);
			}else {
				List<ProductUsage> group=uservice.showProductUsagesByCartId(obj.getCart().getId());
				group.stream().forEach(t->t.setCart(null));
				group.stream().forEach(t->uservice.deleteProductUsage(t));
				
			}
		}
		if(unitofusage==0) {
			uservice.deleteRepairOrder(rep);
		}
		

		return "redirect:/repair/showrecord";
	}
	

	

}

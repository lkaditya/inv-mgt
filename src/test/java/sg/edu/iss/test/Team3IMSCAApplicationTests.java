package sg.edu.iss.test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import sg.edu.iss.test.model.Cart;
import sg.edu.iss.test.model.Customer;
import sg.edu.iss.test.model.Inventory;
import sg.edu.iss.test.model.Product;
import sg.edu.iss.test.model.ProductUsage;
import sg.edu.iss.test.model.RepairOrder;
import sg.edu.iss.test.model.User;
import sg.edu.iss.test.repo.CartRepository;
import sg.edu.iss.test.repo.CustomerRepository;
import sg.edu.iss.test.repo.InventoryRepository;
import sg.edu.iss.test.repo.ProductRepository;
import sg.edu.iss.test.repo.RepairOrderRepository;
import sg.edu.iss.test.repo.UserRepository;
import sg.edu.iss.test.service.ProductUsageImplementation;
import sg.edu.iss.test.service.ProductUsageService;

@SpringBootTest
class Team3IMSCAApplicationTests {
	@Autowired
	UserRepository urepo;
	@Autowired
	CustomerRepository cusrepo;
	@Autowired
	RepairOrderRepository repairrepo;
	
	@Autowired
	private ProductUsageService uservice;
	
	@Autowired
	private ProductRepository prorepo;
	
	@Autowired
	private UserRepository userrepo;
	
	@Autowired
	private CartRepository cartrepo;
	
	@Autowired
	private InventoryRepository invrepo;
	
	@Autowired
	public void setProductUsage(ProductUsageImplementation usage) {
		this.uservice=usage;
	}
	

	@Test
	void contextLoads() {
		//User u1 = new User("dilbert", "password", RoleType.ADMIN);
		User u1= new User("frank", "password", "ADMIN");
		urepo.save(u1);

	}
	
	@Test
	void loadCustomers() {
		Customer a=new Customer("Alpha","34562283");
		cusrepo.save(a);
		Customer b=new Customer("Beta","84349210");
		cusrepo.save(b);
		Customer c=new Customer("Charlie","23894012");
		cusrepo.save(c);
		Customer d=new Customer("Delta","99749329");
		cusrepo.save(d);	
	}
	
	@Test
	void loadRepairOrder() {
		RepairOrder rep1=new RepairOrder();
		RepairOrder rep2=new RepairOrder();
		Customer a= cusrepo.findCustomerByName("Alpha");
		Customer b= cusrepo.findCustomerByName("Beta");
		rep1.setCustomer(a);
		LocalDate d1=LocalDate.now();
		rep1.setRepairDate(d1);
		repairrepo.save(rep1);
		rep2.setCustomer(b);
		LocalDate d2=LocalDate.of(2020, 12, 13);
		rep2.setRepairDate(d2);
		repairrepo.save(rep2);
		
		int i=1;
		long l=i;
		Product p= prorepo.findById((l)).get();
		int i2=3;
		long l2=i2;
		Product q= prorepo.findById((l2)).get();
		ProductUsage pu1= new ProductUsage(p, rep1, 10);
		ProductUsage pu2= new ProductUsage(q, rep1, 5);
		ProductUsage pu3= new ProductUsage(q, rep2, 7);
		uservice.addProductUsage(pu1);
		uservice.addProductUsage(pu2);
		uservice.addProductUsage(pu3);
		
	}


	@Test
	void test1() {
		//List<RepairOrder> group=uservice.showAllRepairOrders();
		//LocalDate a= LocalDate.of(2020, 12, 15);
		//List<RepairOrder>group= uservice.showRepairOrderByKeyword("alpha");
		ArrayList<User>group= (ArrayList<User>)userrepo.findAll();
		System.out.println(group.size());
	}
	
	@Test
	@Transactional
	void test2query() {
		String username="frank";
		Cart c= cartrepo.findCartByUserName(username);
		System.out.println(c.getId());
		List<ProductUsage> group= c.getUsage();
		System.out.println(group.size());
		System.out.println(group.get(0).getProduct().getProductName());
	}
	
	@Test
	void InventoryTest() {
		Inventory a= invrepo.findInventoryByName("Crankcase");
		System.out.println(a.getQoh());
	}
}

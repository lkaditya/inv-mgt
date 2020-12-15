package sg.edu.iss.test;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import sg.edu.iss.test.model.Customer;
import sg.edu.iss.test.model.RepairOrder;
import sg.edu.iss.test.model.RoleType;
import sg.edu.iss.test.model.User;
import sg.edu.iss.test.repo.CustomerRepository;
import sg.edu.iss.test.repo.RepairOrderRepository;
import sg.edu.iss.test.repo.UserRepository;
import sg.edu.iss.test.service.ProductUsageImplementation;
import sg.edu.iss.test.service.ProductUsageInterface;

@SpringBootTest
class EtoeApplicationTests {
	@Autowired
	UserRepository urepo;
	@Autowired
	CustomerRepository cusrepo;
	@Autowired
	RepairOrderRepository repairrepo;
	
	@Autowired
	private ProductUsageInterface uservice;
	
	@Autowired
	public void setProductUsage(ProductUsageImplementation usage) {
		this.uservice=usage;
	}
	

	@Test
	void contextLoads() {
		User u1 = new User("dilbert", "password", RoleType.ADMIN);
		urepo.save(u1);
		
	}
	
	@Test
	void loadCustomers() {
		Customer a=new Customer("Alpha",34562283);
		cusrepo.save(a);
		Customer b=new Customer("Beta",84349210);
		cusrepo.save(b);
		Customer c=new Customer("Charlie",23894012);
		cusrepo.save(c);
		Customer d=new Customer("Delta",99749329);
		cusrepo.save(d);	
	}
	
	@Test
	void loadRepairOrder() {
		RepairOrder rep1=new RepairOrder();
		Customer a= cusrepo.findCustomerByName("Alpha").get(0);
		Customer b= cusrepo.findCustomerByName("Beta").get(0);
		Customer c= cusrepo.findCustomerByName("Charlie").get(0);
		Customer d= cusrepo.findCustomerByName("Delta").get(0);
		rep1.setCustomer(a);
		LocalDate d1=LocalDate.now();
		rep1.setRepairDate(d1);
		repairrepo.save(rep1);
	}
	
	@Test 
	void test1() {
		//List<RepairOrder> group=uservice.showAllRepairOrders();
		LocalDate a= LocalDate.of(2020, 12, 15);
		List<RepairOrder>group= uservice.showRepairOrderByDate(a,a);
		System.out.println(group.size());
	}
	

}

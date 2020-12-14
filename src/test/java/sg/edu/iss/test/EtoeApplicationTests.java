package sg.edu.iss.test;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import sg.edu.iss.test.model.Customer;
import sg.edu.iss.test.model.RoleType;
import sg.edu.iss.test.model.User;
import sg.edu.iss.test.repo.CustomerRepository;
import sg.edu.iss.test.repo.UserRepository;

@SpringBootTest
class EtoeApplicationTests {
	@Autowired
	UserRepository urepo;
	@Autowired
	CustomerRepository cusrepo;
	

	@Test
	void contextLoads() {
		User u1 = new User("dilbert", "password", RoleType.ADMIN);
		urepo.save(u1);
		
	}
	
	@Test
	void loadCustomers() {
		Customer a=new Customer("Alpha",34562283);
		cusrepo.save(a);
		
		
	}
	

}

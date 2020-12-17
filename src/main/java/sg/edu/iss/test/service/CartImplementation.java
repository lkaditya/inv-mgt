package sg.edu.iss.test.service;

import org.springframework.beans.factory.annotation.Autowired;

import sg.edu.iss.test.model.Cart;
import sg.edu.iss.test.repo.CartRepository;

public class CartImplementation implements CartService {

	public CartImplementation() {
		// TODO Auto-generated constructor stub
	}
	
	@Autowired 
	private CartRepository cartrepo;
	

	@Override
	public Cart showAllCartByUserName(String username) {
		Cart c= cartrepo.findCartByUserName(username);
		return c;
	}

}

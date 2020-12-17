package sg.edu.iss.test.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import sg.edu.iss.test.model.Cart;
import sg.edu.iss.test.repo.CartRepository;

@Service
public class CartImplementation implements CartService {

	public CartImplementation() {
		// TODO Auto-generated constructor stub
	}
	
	@Autowired 
	private CartRepository cartrepo;
	

	@Override
	@Transactional
	public Cart showAllCartByUserName(String username) {
		Cart c= cartrepo.findCartByUserName(username);
		return c;
	}

}

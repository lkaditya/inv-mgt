package sg.edu.iss.test.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import sg.edu.iss.test.model.Cart;
import sg.edu.iss.test.repo.CartRepository;

@Service
public class CartImplementation implements CartService {

	
	@Autowired 
	private CartRepository cartrepo;
	

	@Override
	@Transactional
	public Cart showAllCartByUserName(String username) {
		Cart c= cartrepo.findCartByUserName(username);
		return c;
	}


	@Override
	public void save(Cart cart) {
		cartrepo.save(cart);
	}


	@Override
	public void delete(Cart cart) {
		cartrepo.delete(cart);
		
	}


	@Override
	public Cart findCartById(Long id) {
		return cartrepo.findById(id).get();
	}
}

package sg.edu.iss.test.service;


import java.util.List;

import sg.edu.iss.test.model.Cart;



public interface CartService {
	
	public Cart showAllCartByUserName(String username);
	public void save(Cart cart);
	public void delete(Cart cart);
	public Cart findCartById(Long id);
	public List<Cart> findCartByUsrId(Long uid);
}

package sg.edu.iss.test.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import sg.edu.iss.test.model.Inventory;
import sg.edu.iss.test.repo.InventoryRepository;
import sg.edu.iss.test.repo.ProductRepository;

@Service
public class InventoryImplementation implements InventoryService {
	
	@Autowired
	ProductRepository prepo;
	
	@Autowired
	InventoryRepository irepo;
	
	@Transactional
	public void save(Inventory inventory) {
		irepo.save(inventory);
	}
	
	@Transactional(timeout = 30, readOnly = true)
	public List<Inventory> list() {
		return irepo.findAll();
	}

	@Override
	public Optional<Inventory> findInventoryById(Long id) {
		Optional<Inventory> byId = irepo.findById(id);
		return byId;
	}



	@Override
	public void returnInventory(Inventory inventory) {

	}

	@Override
	public void deleteInventory(long productId) {
		prepo.deleteById(productId);
		
	}

	@Transactional
	public void saveInventory(Inventory inventory) {
		irepo.save(inventory);
	}


	@Override
	public Inventory findInventoryByProductName(String name) {
		return irepo.findInventoryByName(name);
	}

	@Override
	public List<Inventory> findInventoryByKeyword(String keyword) {
		return irepo.findInventoryByKeyword(keyword);
	}


	

}

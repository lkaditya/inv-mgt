package sg.edu.iss.test.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import sg.edu.iss.test.model.Inventory;
import sg.edu.iss.test.repo.InventoryRepository;
import sg.edu.iss.test.repo.ProductRepository;

@Service
public class InventoryImplementation implements InventoryInterface  {
	
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
	public Inventory findInventoryById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteInventory(Inventory inventory) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void saveInventory(Inventory inventory) {
		// TODO Auto-generated method stub
		
	}




	
	

}

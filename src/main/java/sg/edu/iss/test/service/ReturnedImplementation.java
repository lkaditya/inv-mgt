package sg.edu.iss.test.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import sg.edu.iss.test.model.Inventory;
import sg.edu.iss.test.model.Product;
import sg.edu.iss.test.model.Returned;
import sg.edu.iss.test.repo.InventoryRepository;
import sg.edu.iss.test.repo.ProductRepository;
import sg.edu.iss.test.repo.ReturnedRepository;

@Service
public class ReturnedImplementation implements ReturnedInterface{
	
	@Autowired
	ProductRepository prepo;
	
	@Autowired
	ReturnedRepository rrepo;
	
	@Autowired
	InventoryRepository irepo;

	@Transactional
	public void save(Returned returned) {
		rrepo.save(returned);
	}
	
	@Transactional(timeout = 30, readOnly = true)
	public List<Returned> list() {
		return rrepo.findAll();
	}

	@Override
	public Optional<Returned> findReturnedById(Long id) {
		Optional<Returned> byId = rrepo.findById(id);
		return byId;
	}

	@Transactional
	public void delete(Returned returned) {
		 rrepo.delete(returned);
	}

	@Override
	public Returned findById(Long id) {
		return rrepo.findById(id).get();
	}


	@Override
	public Inventory update( Long qt, Long Id) {
		Inventory inventory = irepo.findById(Id).orElse(null);
		long l = inventory.getQoh() - qt;
		inventory.setQoh(l);
		irepo.save(inventory);
		return inventory;
	}
	

	

}

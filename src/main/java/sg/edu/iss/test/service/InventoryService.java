package sg.edu.iss.test.service;

import java.util.List;
import java.util.Optional;

import sg.edu.iss.test.model.Inventory;


public interface InventoryService {

	public void saveInventory(Inventory inventory);
	public List<Inventory> list();
	public Optional<Inventory> findInventoryById(Long id);
	public void deleteInventory(long productId);
	void returnInventory(Inventory inventory);
	public List<Inventory> findInventoryByKeyword(String keyword);
	public Inventory findInventoryByProductName(String name);
	

}
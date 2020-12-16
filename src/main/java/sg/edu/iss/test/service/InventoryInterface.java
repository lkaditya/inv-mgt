package sg.edu.iss.test.service;

import java.util.List;

import sg.edu.iss.test.model.Inventory;


public interface InventoryInterface {

	public void saveInventory(Inventory inventory);
	public List<Inventory> list();
	public Inventory findInventoryById(Long id);
	public void deleteInventory(Inventory inventory);
	void returnInventory(Inventory inventory);
	

}
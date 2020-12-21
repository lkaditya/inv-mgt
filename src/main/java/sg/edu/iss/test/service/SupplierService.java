package sg.edu.iss.test.service;

import java.util.ArrayList;
import java.util.List;

import sg.edu.iss.test.model.Supplier;

public interface SupplierService {
	
public void saveSupplier(Supplier supplier);
public ArrayList<Supplier> findAllSuppliers();
public Supplier findSupplierById(Long id);
public void deleteSupplier(Supplier supplier);
public long totalSupplier();
public List<Supplier> findAll();
public Supplier findSupplierByName(String name);
}

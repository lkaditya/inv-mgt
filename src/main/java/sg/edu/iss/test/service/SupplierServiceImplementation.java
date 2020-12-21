package sg.edu.iss.test.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sg.edu.iss.test.model.Supplier;
import sg.edu.iss.test.repo.SupplierRepository;

@Service
public class SupplierServiceImplementation implements SupplierService {
   @Autowired
   SupplierRepository sRepository;
   
   @Transactional
   public void saveSupplier(Supplier supplier) {
	  sRepository.save(supplier);
   }
   @Transactional
   public ArrayList<Supplier> findAllSuppliers(){
	   return (ArrayList<Supplier>)sRepository.findAll();
   }
   @Transactional
   public Supplier findSupplierById(Long id) {
	   return sRepository.findById(id).get();
   }
   @Transactional
   public void deleteSupplier(Supplier supplier) {
	   sRepository.delete(supplier);
   }
   @Transactional
   public long totalSupplier() {
	   return sRepository.count();
   }

    @Override
    public List<Supplier> findAll() {
        List<Supplier> suppliers = sRepository.findAll();
        return suppliers;
    }

    @Override
    public Supplier findSupplierByName(String name) {
        Supplier supplierBySupplierName = sRepository.findSupplierBySupplierName(name);
        return supplierBySupplierName;
    }


}

package sg.edu.iss.test.service;

import java.util.ArrayList;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sg.edu.iss.test.model.Brand;
import sg.edu.iss.test.repo.BrandRepository;

@Service
public class BrandServiceImplementation implements BrandService {
   @Autowired
   BrandRepository bRepository;

   @Transactional
   public void saveBrand(Brand brand) {
	  bRepository.save(brand);
   }
   @Transactional
   public ArrayList<Brand> findAllBrands(){
	   return (ArrayList<Brand>)bRepository.findAll();
   }
   @Transactional
   public Brand findBrandById(Long id) {
	   return bRepository.findById(id).get();
   }
   @Transactional
   public void deleteBrand(Brand brand) {
	   bRepository.delete(brand);
   }
   @Transactional
   public long totalBrand() {
	   return bRepository.count();
   }

   @Override
   public Brand findBrandByName(String name) {
       Brand brandByBrandName = bRepository.findBrandByBrandName(name);
       return brandByBrandName;
   }
}

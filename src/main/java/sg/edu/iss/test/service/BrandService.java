package sg.edu.iss.test.service;

import java.util.ArrayList;

import sg.edu.iss.test.model.Brand;

public interface BrandService {

public void saveBrand(Brand brand);
public ArrayList<Brand> findAllBrands();
public Brand findBrandById(Long id);
public void deleteBrand(Brand brand);
public long totalBrand();
public Brand findBrandByName(String name); 

}

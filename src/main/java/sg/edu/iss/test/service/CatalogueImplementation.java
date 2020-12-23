package sg.edu.iss.test.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import sg.edu.iss.test.model.Brand;
import sg.edu.iss.test.model.Product;
import sg.edu.iss.test.model.ProductQuery;
import sg.edu.iss.test.model.Supplier;
import sg.edu.iss.test.repo.BrandRepository;
import sg.edu.iss.test.repo.ProductRepository;
import sg.edu.iss.test.repo.SupplierRepository;

@Service
public class CatalogueImplementation implements CatalogueInterface  {
	
	@Autowired
	ProductRepository prepo;
	
	@Autowired
	SupplierService supplierService;
	
	@Autowired
	SupplierRepository srepo;
	
	@Autowired
	BrandService brandService;

	@Autowired
	BrandRepository brepo;
	
	@Transactional
	public void save(Product product) {
		String supplierName = product.getSupplier().getSupplierName();
		Supplier supplier = supplierService.findSupplierByName(supplierName);
		if (supplier!=null){
		      product.setSupplier(supplier);
		}else {
		      srepo.save(product.getSupplier());
		}
		String brandName = product.getBrand().getBrandName();
		Brand brand = brandService.findBrandByName(brandName);
		if (brand!=null){
		      product.setBrand(brand);
		}else {
		      brepo.save(product.getBrand());
		}

		
		prepo.save(product);
	}
	
	@Transactional(timeout = 30, readOnly = true)
	public List<Product> list() {
		return prepo.findAll();
	}

	@Override
	public Page<Product> findProductByFliter(Integer page, Integer size,ProductQuery productQuery) {
		Sort sort = Sort.by(Sort.Direction.ASC,"id");
		Pageable pageable= PageRequest.of(page,size, sort);
		Specification<Product> spec=new Specification<Product>() {
			@Override
			public Predicate toPredicate(Root<Product> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				Path<Object> productName= root.get("productName");
				Path<Object> supplierName= root.get("supplier").get("supplierName");
				Path<Object> brandName = root.get("brand").get("brandName");
				List<Predicate> list = new ArrayList<Predicate>();

				if (null!=productQuery.getProductName()&&!"".equals(productQuery.getProductName())){
					list.add((Predicate)cb.like(productName.as(String.class),"%"+productQuery.getProductName()+"%"));
				}
				if (null!=productQuery.getSupplierName()&&!"".equals(productQuery.getSupplierName())){
					list.add((Predicate)cb.like(supplierName.as(String.class),"%"+productQuery.getSupplierName()+"%"));
				}
				if (null!=productQuery.getBrandName()&&!"".equals(productQuery.getBrandName())){
					list.add((Predicate)cb.like(brandName.as(String.class),"%"+productQuery.getBrandName()+"%"));
				}
				Predicate[] p=new Predicate[list.size()];
				return cb.and(list.toArray(p));
//				Predicate p1=cb.like(productName.as(String.class),"%"+productQuery.getProductName()+"%");
//				Predicate p2=cb.like(supplierName.as(String.class),"%"+productQuery.getSupplierName()+"%");
//				Predicate p3=cb.like(brandName.as(String.class),"%"+productQuery.getBrandName()+"%");
//
//				cb.and(p1,p2,p3).var
//				return and;
			}
		};


		return prepo.findAll(spec,pageable);
	}

	@Override
	public Product findById(long id) {
		Product product = prepo.findById(id).orElse(null);
		return product;
	}

	@Override
	public void delete(long id) {
		prepo.deleteById(id);
	}

	@Override
	public void edit(long id) {

	}

	@Override
	public List<Product> findProductBySupName(String Sup_Name) {
		List<Product> productBySupplierName = prepo.findProductBySupplierName(Sup_Name);
		return productBySupplierName;
	}
}




package sg.edu.iss.test.service;

import java.util.ArrayList;
import java.util.List;

import ch.qos.logback.classic.spi.EventArgUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import sg.edu.iss.test.model.Product;
import sg.edu.iss.test.model.ProductQuery;
import sg.edu.iss.test.repo.ProductRepository;
import sg.edu.iss.test.repo.SupplierRepository;

import javax.persistence.criteria.*;

@Service
public class CatalogueImplementation implements CatalogueInterface  {
	
	@Autowired
	ProductRepository prepo;
	
	@Autowired
	SupplierRepository srepo;
	
	@Transactional
	public void save(Product product) {
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
}




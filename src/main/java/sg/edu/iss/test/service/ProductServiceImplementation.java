package sg.edu.iss.test.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sg.edu.iss.test.model.Product;
import sg.edu.iss.test.repo.ProductRepository;

@Service
public class ProductServiceImplementation implements ProductService {
@Autowired
private ProductRepository prepo;

	@Transactional
	public Product findProductById(Long id) {
		return prepo.findById(id).get();
	}

	@Transactional
	public void saveProduct(Product product) {
		prepo.save(product);
		
	}

	@Transactional
	public ArrayList<Product> findALLProducts() {
		return (ArrayList<Product>) prepo.findAll();
	}

	@Transactional
	public void deleteProduct(Product product) {
		prepo.delete(product);
		
	}

	@Override
	   public List<Product> findProductBySupName(String name) {
	       List<Product> productBySupplierName = prepo.findProductBySupplierName(name);
	       return productBySupplierName;
	   }
}

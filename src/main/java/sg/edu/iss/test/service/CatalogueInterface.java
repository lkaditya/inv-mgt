package sg.edu.iss.test.service;

import java.util.List;

import org.springframework.data.domain.Page;
import sg.edu.iss.test.model.Product;
import sg.edu.iss.test.model.ProductQuery;

public interface CatalogueInterface {

	public void save(Product product);
	public List<Product> list();
	public Page<Product> findProductByFliter(Integer page, Integer size,ProductQuery productQuery);
	public Product findById(long id);
	public void delete(long id);
	public void edit(long id);
	public List<Product> findProductBySupName(String Sup_Name);


}
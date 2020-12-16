package sg.edu.iss.test.service;

import java.util.ArrayList;

import sg.edu.iss.test.model.Product;

public interface ProductService {
        public Product findProductById(Long id);
        public void saveProduct(Product product);
        public ArrayList<Product> findALLProducts();
        public void deleteProduct(Product product);
}

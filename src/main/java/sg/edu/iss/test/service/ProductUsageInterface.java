package sg.edu.iss.test.service;

import java.time.LocalDate;
import java.util.List;

import sg.edu.iss.test.model.Product;
import sg.edu.iss.test.model.ProductUsage;
import sg.edu.iss.test.model.RepairOrder;

public interface ProductUsageInterface {
	
	public void addProductUsage(ProductUsage prod);
	public void deleteProductUsage(ProductUsage prod);
	
	public List<ProductUsage> showAllProductUsage();
	public List<ProductUsage> showRelevantProductUsage(RepairOrder rep);
	public List<ProductUsage> showProductUsageByDate(Product product,LocalDate start);
	public List<ProductUsage> showProductUsageByProduct(Product product);
	
	public void addRepairOrder(RepairOrder rep);
	public void deleteRepairOrder(RepairOrder rep);
	public List<RepairOrder> showAllRepairOrders();
	public List<RepairOrder> showRepairOrderByDate(LocalDate start,LocalDate end);
	public List<RepairOrder> showRepairOrderByKeyword(String keyword);
	
	
	
	

}

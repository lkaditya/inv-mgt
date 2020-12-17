package sg.edu.iss.test.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sg.edu.iss.test.model.Product;
import sg.edu.iss.test.model.ProductUsage;
import sg.edu.iss.test.model.RepairOrder;
import sg.edu.iss.test.repo.ProductUsageRepository;
import sg.edu.iss.test.repo.RepairOrderRepository;

@Service
public class ProductUsageImplementation implements ProductUsageService{

	@Autowired
	private ProductUsageRepository produserepo;
	
	@Autowired
	private RepairOrderRepository repairrepo;
	
	@Override
	public void addProductUsage(ProductUsage prod) {

		produserepo.save(prod);
		
	}

	@Override
	public void deleteProductUsage(ProductUsage prod) {
		produserepo.delete(prod);
		
	}

	@Override
	public List<ProductUsage> showAllProductUsage() {
		return produserepo.findAll();
	}

	@Override
	public List<ProductUsage> showRelevantProductUsage(RepairOrder rep) {
		long repairId=rep.getRepairId();
		return produserepo.findAllProductUsageByRepairId(repairId);
	}

	@Override
	public List<ProductUsage> showProductUsageByDate(Product product, LocalDate date) {
		long productId=product.getId();
		return produserepo.findAllProductUsageByDate(productId,date);
	}

	@Override
	public List<ProductUsage> showProductUsageByKeyword(String keyword) {
		return produserepo.findProductUsageByKeyword(keyword);
	}

	@Override
	public void saveRepairOrder(RepairOrder rep) {
		repairrepo.save(rep);
		
	}

	@Override
	public void deleteRepairOrder(RepairOrder rep) {
		repairrepo.delete(rep);
		
	}

	@Override
	public List<RepairOrder> showAllRepairOrders() {
		
		return repairrepo.findAll();
	}

	@Override
	public List<RepairOrder> showRepairOrderByDate(LocalDate start, LocalDate end) {

		return repairrepo.findDateRangedRepairOrder(start, end);
	}

	@Override
	public List<RepairOrder> showRepairOrderByKeyword(String keyword) {

		return repairrepo.findRepairOrderByKeyword(keyword);
	}

	@Override
	public RepairOrder findRepairOrderById(Long id) {
		return repairrepo.findById(id).get();
	}

}

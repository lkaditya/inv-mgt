package sg.edu.iss.test.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
	@Transactional
	public void addProductUsage(ProductUsage prod) {

		produserepo.save(prod);
		
	}

	@Override
	@Transactional
	public void deleteProductUsage(ProductUsage prod) {
		produserepo.delete(prod);
		
	}

	@Override
	@Transactional
	public List<ProductUsage> showAllProductUsage() {
		return produserepo.findAll();
	}

	@Override
	@Transactional
	public List<ProductUsage> showRelevantProductUsage(RepairOrder rep) {
		long repairId=rep.getRepairId();
		return produserepo.findAllProductUsageByRepairId(repairId);
	}

	@Override
	@Transactional
	public List<ProductUsage> showProductUsageByDate(Product product, LocalDate date) {
		long productId=product.getId();
		return produserepo.findAllProductUsageByDate(productId,date);
	}

	@Override
	@Transactional
	public List<ProductUsage> showProductUsageByKeyword(String keyword) {
		return produserepo.findProductUsageByKeyword(keyword);
	}

	@Override
	@Transactional
	public void saveRepairOrder(RepairOrder rep) {
		repairrepo.save(rep);
		
	}

	@Override
	@Transactional
	public void deleteRepairOrder(RepairOrder rep) {
		repairrepo.delete(rep);
		
	}

	@Override
	@Transactional
	public List<RepairOrder> showAllRepairOrders() {
		
		return repairrepo.findAll();
	}

	@Override
	@Transactional
	public List<RepairOrder> showRepairOrderByDate(LocalDate start, LocalDate end) {

		return repairrepo.findDateRangedRepairOrder(start, end);
	}

	@Override
	@Transactional
	public List<RepairOrder> showRepairOrderByKeyword(String keyword) {

		return repairrepo.findRepairOrderByKeyword(keyword);
	}

	@Override
	@Transactional
	public RepairOrder findRepairOrderById(Long id) {
		return repairrepo.findById(id).get();
	}

	@Override
	public ProductUsage showCartProductUsageByProductName(Product product) {
	
		return produserepo.findCartProductUsageByProductName(product.getProductName());
	}

	@Override
	public List<ProductUsage> showProductUsagesByCartId(Long id) {
		return produserepo.findProductUsageByCartId(id);
	}

	@Override
	   public List<ProductUsage> findProductUsageByProId(Long pid) {
	       List<ProductUsage> productUsageByPid = produserepo.findProductUsageByPid(pid);
	       return productUsageByPid;
	   }
}

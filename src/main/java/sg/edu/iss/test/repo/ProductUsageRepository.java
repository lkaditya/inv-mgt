package sg.edu.iss.test.repo;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import sg.edu.iss.test.model.ProductUsage;

public interface ProductUsageRepository extends JpaRepository<ProductUsage, Long> {
	
	
	@Query("Select x From ProductUsage x where x.rep.repairId=:inputid")
	List<ProductUsage> findAllProductUsageByRepairId(@Param("inputid")long repId);	

	@Query("Select x From ProductUsage x where x.rep.repairDate=:date and x.product.id=:inputid")
	List<ProductUsage> findAllProductUsageByDate(@Param("inputid")long productId,@Param("date")LocalDate date);
	
	@Query("select x from ProductUsage x where x.productUsageId like CONCAT('%',:key,'%') or x.product.productName like CONCAT('%',:key,'%')")
	List<ProductUsage> findProductUsageByKeyword(@Param("key")String key);

	@Query("Select x From ProductUsage x where x.rep=null and x.product.productName=:name")
	ProductUsage findCartProductUsageByProductName(@Param("name")String productName);

	@Query("Select x From ProductUsage x where x.cart.id=:id")
	List<ProductUsage> findProductUsageByCartId(@Param ("id") Long id);

	@Query("select x from ProductUsage as x where x.product.id=:pid")
    public List<ProductUsage> findProductUsageByPid(@Param("pid") Long pid);

}

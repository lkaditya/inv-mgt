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
	
	@Query("Select x From ProductUsage x where x.product.id=:inputid")
	List<ProductUsage> findAllProductUsageByProduct(@Param("inputid")long productId);



}

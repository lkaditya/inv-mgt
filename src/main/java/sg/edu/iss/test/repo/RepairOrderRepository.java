package sg.edu.iss.test.repo;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import sg.edu.iss.test.model.RepairOrder;

public interface RepairOrderRepository extends JpaRepository<RepairOrder, Long> {

	@Query("select x from RepairOrder x where x.repairDate<=:end and x.repairDate>=:start ")
	List<RepairOrder> findDateRangedRepairOrder(@Param("start")LocalDate start,@Param("end")LocalDate end);

	@Query("select x from RepairOrder x where x.repairDate like CONCAT('%',:key,'%') or x.customer.customerId like CONCAT('%',:key,'%') or x.repairId like CONCAT('%',:key,'%')")
	List<RepairOrder> findRepairOrderByKeyword(@Param("key")String key);
	

	

}

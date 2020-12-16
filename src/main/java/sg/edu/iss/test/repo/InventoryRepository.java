package sg.edu.iss.test.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import sg.edu.iss.test.model.Inventory;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {
	
	 @Query("Select i from Inventory i where i.id LIKE :id")
	 List<Inventory> findInventoryById(@Param("id") String id);
}

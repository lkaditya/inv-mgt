package sg.edu.iss.test.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import sg.edu.iss.test.model.Inventory;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {
	@Query("select x from Inventory x where x.id like CONCAT('%',:key,'%') "
			+ "or x.product.productName like CONCAT('%',:key,'%') "
			+ "or x.pop like CONCAT('%',:key,'%')"
			+ "or x.por like CONCAT('%',:key,'%')"
			+ "or x.qoh like CONCAT('%',:key,'%')"
			+ "or x.rol like CONCAT('%',:key,'%')"
			+ "or x.wholesale like CONCAT('%',:key,'%')")
	public List<Inventory> findInventoryByKeyword(@Param("key")String key);
	@Query("Select i from Inventory i where i.product.productName=:name")
	Inventory findInventoryByName(@Param("name")String name);
}

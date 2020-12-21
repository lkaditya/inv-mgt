package sg.edu.iss.test.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import sg.edu.iss.test.model.RepairOrder;
import sg.edu.iss.test.model.Supplier;

import java.util.List;

public interface SupplierRepository extends JpaRepository<Supplier, Long> {

    @Query("select s from Supplier as s where s.supplierName=:name")
    public Supplier findSupplierBySupplierName(@Param("name") String name);
}

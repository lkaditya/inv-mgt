package sg.edu.iss.test.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import sg.edu.iss.test.model.Returned;

public interface ReturnedRepository extends JpaRepository<Returned, Long>, JpaSpecificationExecutor<Returned> {
	
	@Query("select r from Returned as r where r.inventory.id=:pid")
    public List<Returned> findReturnedByPid(@Param("pid") Long pid);

}

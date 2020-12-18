package sg.edu.iss.test.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import sg.edu.iss.test.model.Returned;

public interface ReturnedRepository extends JpaRepository<Returned, Long>, JpaSpecificationExecutor<Returned> {

}

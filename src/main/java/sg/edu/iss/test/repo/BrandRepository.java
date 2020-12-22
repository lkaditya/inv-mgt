package sg.edu.iss.test.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import sg.edu.iss.test.model.Brand;

public interface BrandRepository extends JpaRepository<Brand,Long> {

	@Query("select b from Brand as b where b.brandName=:name")
    public Brand findBrandByBrandName(@Param("name") String name);

} 

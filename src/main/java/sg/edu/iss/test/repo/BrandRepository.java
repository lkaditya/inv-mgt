package sg.edu.iss.test.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import sg.edu.iss.test.model.Brand;

public interface BrandRepository extends JpaRepository<Brand,Long> {
}

package sg.edu.iss.test.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import sg.edu.iss.test.model.Mechanic;

public interface MechanicRepository extends JpaRepository<Mechanic, Long>{

}

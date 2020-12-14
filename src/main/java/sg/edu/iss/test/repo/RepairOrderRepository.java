package sg.edu.iss.test.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import sg.edu.iss.test.model.RepairOrder;

public interface RepairOrderRepository extends JpaRepository<RepairOrder, Long> {

}

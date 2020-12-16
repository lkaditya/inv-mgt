package sg.edu.iss.test.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import sg.edu.iss.test.model.Admin;


public interface AdminRepository extends JpaRepository<Admin, Long> {


}

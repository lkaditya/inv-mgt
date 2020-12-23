package sg.edu.iss.test.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import sg.edu.iss.test.model.Cart;

public interface CartRepository extends JpaRepository<Cart, Long> {
	
	@Query("Select x from Cart x where x.user.userName=:username")
	Cart findCartByUserName(@Param("username")String username);

	@Query("select x from Cart as x where x.user.id=:uid")
    public List<Cart> findCartByUid(@Param("uid") Long uid);
}

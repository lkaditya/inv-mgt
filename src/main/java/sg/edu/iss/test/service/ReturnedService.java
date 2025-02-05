package sg.edu.iss.test.service;

import java.util.List;
import java.util.Optional;

import sg.edu.iss.test.model.Inventory;
import sg.edu.iss.test.model.Returned;

public interface ReturnedService {
	
	public void save(Returned returned);
	public List<Returned> list();
	public Optional<Returned> findReturnedById(Long id);
	public void delete(Returned returned);
	public Returned findById(Long id);
	public Inventory update( Long qt, Long id );
	public List<Returned> findReturnedByProId(Long pid);
}

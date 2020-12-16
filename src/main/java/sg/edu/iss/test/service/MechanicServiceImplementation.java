package sg.edu.iss.test.service;

import java.util.ArrayList;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sg.edu.iss.test.model.Mechanic;
import sg.edu.iss.test.repo.MechanicRepository;

@Service
public class MechanicServiceImplementation implements MechanicService {
	@Autowired
	MechanicRepository mrepo;

	@Transactional
	public void saveMechanic(Mechanic mechanic) {
		mrepo.save(mechanic);
	}

	@Transactional
	public ArrayList<Mechanic> findAllMechanics() {
		return (ArrayList<Mechanic>) mrepo.findAll();
	}

	@Transactional
	public Mechanic findMechanicById(Long id) {
		return mrepo.findById(id).get();
	}

	@Transactional
	public void deleteMechanic(Mechanic member) {
		 mrepo.delete(member);
	}
    
	@Transactional
	public long totalMechanic() {
		return mrepo.count();
	}

}

package sg.edu.iss.test.service;

import java.util.ArrayList;

import sg.edu.iss.test.model.Mechanic;

public interface MechanicService {
	 public void saveMechanic(Mechanic mechanic);
	 public ArrayList<Mechanic> findAllMechanics();
	 public Mechanic findMechanicById(Long id);
	 public void deleteMechanic(Mechanic mechanic);
	 public long totalMechanic();
}

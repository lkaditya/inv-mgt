package sg.edu.iss.test.service;

import java.util.ArrayList;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sg.edu.iss.test.model.Admin;
import sg.edu.iss.test.repo.AdminRepository;


@Service
public class AdminServiceImplementation implements AdminService {
	@Autowired
	private AdminRepository adminRepository;
    
	@Transactional
	public Admin findAdminById(Long id) {
		return adminRepository.findById(id).get();
	}
	@Transactional
	public void saveAdmin(Admin admin) {
		adminRepository.save(admin);
	}
	@Transactional
	public long totalAdmin() {
		return adminRepository.count();
	}
	@Transactional
	public ArrayList<Admin> findAllAdmins() {
		return (ArrayList<Admin>) adminRepository.findAll();
	}
	@Transactional
	public void deleteAdmin(Admin admin) {
		adminRepository.delete(admin);
	}
}

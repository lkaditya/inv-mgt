package sg.edu.iss.test.service;

import java.util.ArrayList;

import sg.edu.iss.test.model.Admin;

public interface AdminService {
	public Admin findAdminById(Long id);
	public void saveAdmin(Admin admin);
	public long totalAdmin();
	public ArrayList<Admin> findAllAdmins();
	public void deleteAdmin(Admin admin);
}

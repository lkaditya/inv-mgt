package sg.edu.iss.test.service;

import java.util.ArrayList;

import sg.edu.iss.test.model.User;

public interface UserService {
	public User findUserById(Long id);
	public void saveUser(User user);
	public long totalUser();
	public ArrayList<User> findAllUsers();
	public void deleteUser(User user);
	
	public boolean authenticate(User user);
	public User findUserByUserName(String name);
}
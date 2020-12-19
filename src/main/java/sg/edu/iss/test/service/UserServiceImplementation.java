package sg.edu.iss.test.service;

import java.util.ArrayList;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import sg.edu.iss.test.model.User;
import sg.edu.iss.test.repo.UserRepository;


@Service
public class UserServiceImplementation implements UserService {
	@Autowired
	private UserRepository userRepository;

	@Transactional
	public User findUserById(Long id) {
		return userRepository.findById(id).get();
	}
	@Transactional
	public void saveUser(User user) {
		userRepository.save(user);
	}
	@Transactional
	public long totalUser() {
		return userRepository.count();
	}
	@Transactional
	public ArrayList<User> findAllUsers() {
		return (ArrayList<User>) userRepository.findAll();
	}
	@Transactional
	public void deleteUser(User user) {
		userRepository.delete(user);
	}
	
	
	@Override
	public User findUserByUserName(String name) {
		return userRepository.findUserByUserName(name);
	}
	
	@Override
	public boolean authenticate(User user) {
		User dbuser = userRepository.findUserByUserName(user.getUserName());
		if (dbuser.getUserName().equals(user.getUserName()) && dbuser.getPassword().equals(user.getPassword()))
			return true;
		else
			return false;
	}
}
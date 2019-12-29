package com.hos.hosuserservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

	@Autowired
	private UserServiceRepo userServiceRepo;
	
	public String saveUser(User user) {
		userServiceRepo.save(user);
		return "Success";
	} 
	public List<User> getAllUsers(){
		return userServiceRepo.findAll();
	}
}

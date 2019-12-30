package com.hos.hosuserservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController()
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping("hello")
	public String greetings() {
		return "Hello World";
	}
	
	@GetMapping("user")
	public List<User> getUser() {
		return userService.getAllUsers();
	}
	
	@PostMapping("user")
	public ResponseEntity<String> saveUser(@RequestBody User user) {
		return new ResponseEntity<String>(userService.saveUser(user),HttpStatus.OK);
	}

}

package com.hos.hosuserservice;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {
	
	@Autowired
	UserService userService;

	@PostMapping("login")
	public ResponseEntity login(@RequestBody LoginUser loginUser) {
			return userService.login(loginUser);
	}
	
	@PostMapping("registration")
	public LoginResponseDTO setLoginUser(@RequestBody LoginUser loginUser) {
			return userService.registration(loginUser);
	}
	
	@PostMapping("accessToken")
	public ResponseEntity getRefreshToken(@RequestBody LoginResponseDTO loginDTO) {
		return userService.getRefreshToken(loginDTO);
	}
}

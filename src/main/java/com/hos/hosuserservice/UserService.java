package com.hos.hosuserservice;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;


import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class UserService {

	@Autowired
	private UserServiceRepo userServiceRepo;
	
	@Autowired
	LoginRepo loginRepo;
	
	public String saveUser(User user) {
		userServiceRepo.save(user);
		return "Success";
	} 
	public List<User> getAllUsers(){
		return userServiceRepo.findAll();
	}
	
	public ResponseEntity login(LoginUser loginUser) {
		LoginUser user= loginRepo.validateUser(loginUser.getUserName(), loginUser.getPassword());
		if(user == null) {
			return new ResponseEntity("Invalid login Credentials!", HttpStatus.UNAUTHORIZED);
		}
		
		LoginResponseDTO loginDTO= new LoginResponseDTO();
		loginDTO.setUserName(loginUser.getUserName());
		loginDTO.setJwtToken(createJwtToken(user));
		loginDTO.setRefreshToken(createRefreshToken(user));
		return new ResponseEntity(loginDTO,HttpStatus.ACCEPTED);
	}
	
	public ResponseEntity getRefreshToken(String refreshToken) {
		LoginUser user= loginRepo.findByRefreshToken(refreshToken);
		if(user == null) {
			return new ResponseEntity("Invalid login Credentials!", HttpStatus.UNAUTHORIZED);
		}
		
		LoginResponseDTO loginDTO= new LoginResponseDTO();
		loginDTO.setUserName(user.getUserName());
		loginDTO.setJwtToken(createJwtToken(user));
		loginDTO.setRefreshToken(createRefreshToken(user));
		return new ResponseEntity(loginDTO,HttpStatus.ACCEPTED);
	}
	
	public LoginResponseDTO registration(LoginUser loginUser) {
		LoginUser user= loginRepo.save(loginUser);
		LoginResponseDTO loginDTO= new LoginResponseDTO();
		if(user != null) {
			loginDTO.setUserName(loginUser.getUserName());
			loginDTO.setJwtToken(createJwtToken(user));
			loginDTO.setRefreshToken(createRefreshToken(user));
		}
		
		return loginDTO;
	}
	
	public String createJwtToken(LoginUser user) {
		
		Instant now = Instant.now();
//		List<SimpleGrantedAuthority> grantAuthorities = new ArrayList<>();
//		grantAuthorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
		String accessToken = Jwts.builder().setSubject(user.getUserName())
//                .claim("authorities", grantAuthorities.stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
                .setIssuedAt(Date.from(now))
                .setExpiration(Date.from(now.plusSeconds(ApiParameters.JWT_EXPIRATION)))
                .signWith(SignatureAlgorithm.HS256, ApiParameters.JWT_SECRET.getBytes()).claim("ud", user.getUserName())
                .claim("type", "ACCESS").compact();
		return accessToken;
	}
	
	public String createRefreshToken(LoginUser user) {
//		List<SimpleGrantedAuthority> grantedAuthorityList = new ArrayList<>();
//	    grantedAuthorityList.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
		Instant now = Instant.now();
        String refreshToken = Jwts.builder().setSubject(user.getUserName())
//        		.claim("authorities", grantedAuthorityList.stream()
//                .map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
                .setIssuedAt(Date.from(now)).setExpiration(Date.from(now.plusSeconds(ApiParameters.REFRESH_TOKEN_EXPIRATION)))
                .signWith(SignatureAlgorithm.HS256, ApiParameters.JWT_SECRET.getBytes()).claim("ud", user.getUserName())
                .claim("type","REFRESH" ).compact();
        loginRepo.updateRefreshToken(user.getUserName(), user.getRefreshToken());
        return refreshToken;
	}
	
	
}

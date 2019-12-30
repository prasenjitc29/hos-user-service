package com.hos.hosuserservice;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;

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
		LoginUser user= loginRepo.save(loginUser);
		if(user == null) {
			return new ResponseEntity("Invalid login Credentials!", HttpStatus.UNAUTHORIZED);
		}
		
		LoginResponseDTO loginDTO= new LoginResponseDTO();
		loginDTO.setUserName(loginUser.getUserName());
		loginDTO.setJwtToken(getJwtToken(user));
		loginDTO.setRefreshToken(getRefreshToken(user));
		return new ResponseEntity(loginDTO,HttpStatus.ACCEPTED);
	}
	
	public LoginResponseDTO registration(LoginUser loginUser) {
		LoginUser user= loginRepo.save(loginUser);
		LoginResponseDTO loginDTO= new LoginResponseDTO();
		if(user != null) {
			loginDTO.setUserName(loginUser.getUserName());
			loginDTO.setJwtToken(getJwtToken(user));
			loginDTO.setRefreshToken(getRefreshToken(user));
		}
		
		return loginDTO;
	}
	
	public String getJwtToken(LoginUser user) {
		
		Instant now = Instant.now();
		List<SimpleGrantedAuthority> grantAuthorities = new ArrayList<>();
		grantAuthorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
		String accessToken = Jwts.builder().setSubject(user.getUserName())
                .claim("authorities", grantAuthorities.stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
                .setIssuedAt(Date.from(now))
                .setExpiration(Date.from(now.plusSeconds(expiration)))
                .signWith(SignatureAlgorithm.HS256, secret.getBytes()).claim("ud", uuid)
                .claim("type", "ACCESS").compact();
		return accessToken;
	}
	
	public String getRefreshToken(LoginUser user) {
		List<SimpleGrantedAuthority> grantedAuthorityList = new ArrayList<>();
	     grantedAuthorityList.add(new SimpleGrantedAuthority("ROLE_" + user.getAppUserRole()));
	     String refreshToken = JwtGenerator.generateRefreshToken(user.getName(), user.getUsername(),grantedAuthorityList,ApiParameters.REFRESH_TOKEN_EXPIRATION,ApiParameters.JWT_SECRET);
	     userReposatory.updateRefreshToken(user.getUsername(), refreshToken);
	     return refreshToken;
	}
	
	
}

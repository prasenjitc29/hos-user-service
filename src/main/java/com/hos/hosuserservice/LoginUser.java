package com.hos.hosuserservice;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="LoginUser")
public class LoginUser {
	
	@Id
    @Column
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;	
	@Column(name="username")
	private String userName;
	@Column(name="password")
	private String password;
	@Column(name="refreshtoken")
	private String refreshToken;
	
	public String getRefreshToken() {
		return refreshToken;
	}
	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	

	
}

package com.hos.hosuserservice;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface LoginRepo extends JpaRepository<LoginUser, String> {
	
	@Query("SELECT u FROM LOGINUSER u WHERE u.userName=?1 AND u.password=?2")
	LoginUser validateUser(String username,String password);
	
	@Modifying
	@Query("update LOGINUSER u set u.refreshToken = :refreshToken WHERE u.userName = :userName")
	@Transactional
	int updateRefreshToken(@Param("userName") String userName, @Param("refreshToken") String refreshToken);
	
	@Query("SELECT u FROM User u WHERE u.refreshToken=?1")
	LoginUser findByRefreshToken(String refreshToken);

}

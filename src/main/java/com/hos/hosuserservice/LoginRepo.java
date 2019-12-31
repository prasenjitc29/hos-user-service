package com.hos.hosuserservice;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface LoginRepo extends JpaRepository<LoginUser, Long> {
	
	@Query("SELECT u FROM LoginUser u WHERE u.userName=?1 AND u.password=?2")
	LoginUser validateUser(String username,String password);
	
	@Modifying
	@Query("update LoginUser u set u.refreshToken = :refreshToken WHERE u.userName = :username")
	@Transactional
	int updateRefreshToken(@Param("username") String username, @Param("refreshToken") String refreshtoken);
	
	@Query("SELECT u FROM LoginUser u WHERE u.refreshToken=?1")
	LoginUser findByRefreshToken(String refreshtoken);

}

package com.hos.hosuserservice;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserServiceRepo extends JpaRepository<User, Integer> {

}

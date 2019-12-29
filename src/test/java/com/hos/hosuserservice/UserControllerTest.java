package com.hos.hosuserservice;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Arrays;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class UserControllerTest {

	@InjectMocks
	UserService userService;
	
	@Mock
	UserServiceRepo userRepo;
	
	@AfterClass
	public static void afterAll() {
		System.out.println("After All");
	}
	@BeforeClass
	public static void beforeAll() {
		System.out.println("Before All");
	}
	
	@After
	public void after() {
		System.out.println("After");
	}
	@Before
	public void before() {
		System.out.println("Before");
		when(userRepo.findAll()).thenReturn(Arrays.asList(new User(1,"Suman","Choudhury","sc@gmail.com"),new User(2,"Suman1","Choudhury1","sc1@gmail.com")));
	}

	@Test
	public void contextLoads() {
		System.out.println(userService.getAllUsers());
		assertEquals("Choudhury", userService.getAllUsers().get(0).getFirstName());
	}


}

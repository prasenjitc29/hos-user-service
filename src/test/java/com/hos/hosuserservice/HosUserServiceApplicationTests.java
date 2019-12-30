package com.hos.hosuserservice;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@WebMvcTest
public class HosUserServiceApplicationTests {

	@Autowired
	MockMvc mockMvc;
	
	@MockBean
	UserService userService;
	
	@Test
	public void testAllUser() throws Exception {
		when(userService.getAllUsers()).thenReturn(Arrays.asList(new User(1,"kjkj@gmail.com","Choudhury","Suman")));
//		this.mockMvc.perform(get("/user")).andDo(print()).andExpect(status().isOk()).andExpect(content().json("[{'id': 1,'email': 'kjkj@gmail.com',	'firstName': 'Suman','lastName': 'Choudhury'}]"));
		 this.mockMvc.perform(get("/user"))
	        .andExpect(status().isOk())
	        .andExpect(jsonPath("$", hasSize(1)))
	        .andExpect(jsonPath("$[0].id", is(1)))
	        .andExpect(jsonPath("$[0].lastName", is("Suman")))
	        .andExpect(jsonPath("$[0].firstName", is("Choudhury")))
	        .andExpect(jsonPath("$[0].email", is("kjkj@gmail.com")));
		 verify(userService,times(1)).getAllUsers();
	}
	
	@Test
	public void testSaveUser() throws Exception {
		when(userService.saveUser(any(User.class))).thenReturn("Success");
		this.mockMvc.perform(post("/user")
				.content("{\"id\": 1,\"email\": \"kjkj@gmail.com\",\"firstName\": \"Suman\",\"lastName\": \"Choudhury\"}").header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
		.andDo(print())
		.andExpect(status().isOk())
		.andReturn().getResponse().getContentAsString().contentEquals("Success");
		verify(userService,times(1)).saveUser(any(User.class));
	}
}

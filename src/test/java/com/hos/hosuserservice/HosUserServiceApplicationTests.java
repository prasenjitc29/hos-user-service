package com.hos.hosuserservice;

import static org.hamcrest.CoreMatchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.mockito.Mockito.*;
import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import org.springframework.http.*;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

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
	}
}

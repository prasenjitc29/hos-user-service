package com.hos.hosuserservice;


import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        properties = {"spring.cloud.discovery.enabled = false"})
@Import({com.hos.hosuserservice1.Class1.class})
class Class2Test {
	
	@Autowired
	List<Ratings> ratings = new ArrayList<>();

	@Test
	void test() {
		assertThat(ratings.get(0).getInt(),is(equalTo(1)));
		assertThat(ratings.get(1).getInt(),is(equalTo(2)));
		assertThat(ratings.get(2).getInt(),is(equalTo(3)));
	}

}

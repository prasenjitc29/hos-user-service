package com.hos.hosuserservice1;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.hos.hosuserservice.Ratings;

@Component
@Order(Ordered.LOWEST_PRECEDENCE)
public class Class1 implements Ratings {

	public int getInt() {
		return 3;
	}
}

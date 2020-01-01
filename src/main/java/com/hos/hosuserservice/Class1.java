package com.hos.hosuserservice;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(1)
public class Class1 implements Ratings {

	public int getInt() {
		return 1;
	}
}

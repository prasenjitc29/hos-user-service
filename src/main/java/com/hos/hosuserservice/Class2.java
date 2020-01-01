package com.hos.hosuserservice;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(2)
public class Class2 implements Ratings {

	public int getInt() {
		return 2;
	}
}

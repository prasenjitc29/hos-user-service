package com.hos.hosuserservice;

import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@EnableWebSecurity
@Configuration
@ComponentScan("com.hos.hosuserservice")
@Order(3)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception{
		httpSecurity.cors().disable()
		.csrf().disable()
		.logout().disable()
		.formLogin().disable()
		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and()
        .anonymous()
        .and()
        .exceptionHandling().authenticationEntryPoint(
        (req, rsp, e) -> rsp.sendError(HttpServletResponse.SC_UNAUTHORIZED))
        .and()
        .authorizeRequests() 
        .antMatchers(HttpMethod.OPTIONS).permitAll()
        .antMatchers("/**").permitAll()
        .anyRequest().authenticated();
	}
}

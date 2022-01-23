package com.springsecurity.basics.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	// Spring will autowire h2 database we have to only give spring-boot-starter-jdbc and h2 database dependency
	@Autowired
	DataSource dataSource;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// We have configured different schema in schema.sql
		// We have 2 tables here: my_users table having uname,pass and enabled and my_authorities table having uname(fk which references uname in my_users table) and authroity) 
		// We have also populated some data in data.sql
		// We need tell spring security to look at our schema instead of default schema
		// We do this using usersByUsernameQuery and authoritiesByUsernameQuery
		// Here we have overriden table name as weel as column names
		auth.jdbcAuthentication()
			.dataSource(dataSource)
			.usersByUsernameQuery("select uname,pass,enabled from my_users where uname = ?")
			.authoritiesByUsernameQuery("select uname,authority from my_authorities where uname = ?");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// Remember to go from most restrictive to least restrictive role
		http.authorizeRequests()
		.antMatchers("/admin").hasRole("ADMIN")
		.antMatchers("/user").hasAnyRole("USER", "ADMIN")
		.antMatchers("/").permitAll()
		.and()
		.formLogin();
	}

	@Bean
	public PasswordEncoder getPasswordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}
}

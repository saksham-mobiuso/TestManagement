package com.mobi.config;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@SuppressWarnings("deprecation")
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	@Autowired
	DataSource dataSource;
	
	@Autowired
	public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
		auth.jdbcAuthentication()
		.dataSource(dataSource)
		.passwordEncoder(new BCryptPasswordEncoder())
		.usersByUsernameQuery("select username,userpassword,enabled from users where username=?")
		.authoritiesByUsernameQuery("select username,role from users where username=?");
	}
	

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.csrf().disable()
		.authorizeRequests()
		.antMatchers(HttpMethod.POST, "/**").hasRole("ADMIN")
		.antMatchers(HttpMethod.PUT, "/**").hasRole("ADMIN")
		.antMatchers(HttpMethod.DELETE,"/**").hasRole("ADMIN")
		.antMatchers("/tests").hasRole("ADMIN")
		.antMatchers("/users").hasRole("ADMIN")
		.antMatchers("/users/results").hasRole("ADMIN")
		.antMatchers(HttpMethod.POST,"/usertests/**").hasRole("USER")
		.anyRequest().authenticated()
		.and()
		.httpBasic();

	}
	
	

}

package com.cts.sms.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.cts.sms.Filter.JwtFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private JwtFilter jwtFilter;
	
	
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception
	{
		return http
//		this will disable the csrf auto configures 
		.csrf(customizer -> customizer.disable())
		
//		This statemest will authenticate each request comming from the client 
		.authorizeHttpRequests(requests-> requests
				.requestMatchers("/access/register","/access/login")
				.permitAll()
				.anyRequest().authenticated())
		
//		This statement will enable the form login in browser 
//		.formLogin(Customizer.withDefaults())
		
//		This statement will enable the http basics for api applications like postman and handle their request 
		.httpBasic(Customizer.withDefaults())
		
//	using this statement the session id is getting generated each time of login due to which we are unable to login through the 
//		form because the session id is generated everytime and in form login we are maintaining the session 
		.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
		
//		First parameter is nothing but the actual filter and 2nd one is the in replacement of
		.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class) 		
		.build();
		
		

		
	}
	
//	configuring the Authentication provider
	@Bean
	public AuthenticationProvider authenticationProvider()
	{
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setPasswordEncoder(new BCryptPasswordEncoder(12));	
		provider.setUserDetailsService(userDetailsService);
		return provider;
	}
	
	
//	configuring authentication manager(I) 
	
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception
	{
		
//		get the object of authentication manager and return it 
//		This will return the object of the authentication manager
		return config.getAuthenticationManager(); 
	}
	

}

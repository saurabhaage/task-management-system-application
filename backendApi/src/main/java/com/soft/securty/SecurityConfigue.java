package com.soft.securty;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;



@Configuration
@EnableWebSecurity
public class SecurityConfigue {

	private UserDetailsService userdetailservice;
	
	private JWTAuthentication jwtuthentication;
	
	
	SecurityConfigue(UserDetailsService userdetailservice,JWTAuthentication jwtauthentication){
		this.userdetailservice=userdetailservice;
		this.jwtuthentication=jwtauthentication;
	}

	@Bean
	public SecurityFilterChain securityfilterchain(HttpSecurity httpsecurity) throws Exception  {
		httpsecurity.authorizeHttpRequests(	
				request->request
					.requestMatchers("user/login").permitAll()
					.requestMatchers("user/register").permitAll()
					.requestMatchers("/task/**").permitAll()
					.requestMatchers("/admin/**").permitAll()
					.anyRequest().authenticated()
				);
		httpsecurity.formLogin(Customizer.withDefaults());
	httpsecurity.httpBasic(Customizer.withDefaults());
	httpsecurity.csrf().disable();
	httpsecurity.addFilterBefore(jwtuthentication, UsernamePasswordAuthenticationFilter.class);
	return httpsecurity.build();
	}
	
//	@Bean
//	public UserDetailsService userdetailservice() {
//		UserDetails suraj=
//				User
//					.withUsername("suraj")
//					.password("{noop}suraj")
//					.roles("USER")
//					.build();
//		
//		UserDetails saurabh=
//				User
//					.withUsername("saurabh")
//					.password("{noop}saurabh")
//					.roles("USER")
//					.build();
//		
//		
//		return new InMemoryUserDetailsManager(suraj,saurabh);	
//	}
	
	@Bean
	BCryptPasswordEncoder bCryptPasswordEncoder () {
		return new BCryptPasswordEncoder(16);
		
	}
	
	@Bean
	public AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setUserDetailsService(userdetailservice);
		provider.setPasswordEncoder(bCryptPasswordEncoder());
		return provider;
	}
	
	@Bean
	public AuthenticationManager authenticationmanager(AuthenticationConfiguration counfiuration) throws Exception {
		return counfiuration.getAuthenticationManager();
	}
	
}

package com.project.phoneshop.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.project.phoneshop.config.security.jwt.JwtLoginFilter;
import com.project.phoneshop.config.security.jwt.TokenVerifyFilter;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableGlobalMethodSecurity(
		  prePostEnabled = true, 
		  securedEnabled = true, 
		  jsr250Enabled = true)
public class SecurityConfig{
	private final PasswordEncoder passwordEncoder;
	private final UserDetailsService userDetailsService;
	private final AuthenticationConfiguration authenticationConfiguration;
	
	@Bean
	protected SecurityFilterChain configure(HttpSecurity http) throws Exception {
		http.csrf().disable()
			.addFilter(new JwtLoginFilter(authenticationManager2(authenticationConfiguration)))
			.addFilterAfter(new TokenVerifyFilter(), JwtLoginFilter.class)
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
			.and()
			.authorizeHttpRequests()
			.antMatchers("/","/index","/home","css/**","js/**","/swagger-ui/").permitAll()
			//.antMatchers("/models").hasRole("SALE")
			//.antMatchers(HttpMethod.POST, "/brands").hasAuthority(PermissionEnum.BRAND_WRITE.getDescription())
			//.antMatchers(HttpMethod.GET, "/brands").hasAuthority(PermissionEnum.BRAND_READ.getDescription())
			//.antMatchers(HttpMethod.POST, "/brands").hasAnyAuthority(PermissionEnum.BRAND_READ.getDescription(), PermissionEnum.BRAND_WRITE.getDescription())
			.anyRequest()
			.authenticated();
		
		return http.build();
	}
	
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(getAuthenticationProvider());
	}
	
	@Bean
	public AuthenticationProvider getAuthenticationProvider() {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setUserDetailsService(userDetailsService);
		provider.setPasswordEncoder(passwordEncoder);
		return provider;
	}
	
	// step 1
	@Bean
	AuthenticationManager authenticationManager2(
	        AuthenticationConfiguration authenticationConfiguration) throws Exception {
	    return authenticationConfiguration.getAuthenticationManager();
	}
	
	/*
	@Bean
	@Override
	protected UserDetailsService userDetailsService() {
		//User dara = new User("dara", passwordEncoder.encode("dara123"), Collections.emptyList());
		
		UserDetails dara = User.builder()
			.username("dara")
			.password(passwordEncoder.encode("dara123"))
			//.roles("SALE") // ROLE_SALE
			.authorities(RoleEnum.SALE.getAuthorities())
			.build();
		
		UserDetails thida = User.builder()
			.username("thida")
			.password(passwordEncoder.encode("thida"))
			//.roles("ADMIN") // ROLE_ADMIN
			.authorities(RoleEnum.ADMIN.getAuthorities())
			.build();
		
		UserDetailsService detailsService = new InMemoryUserDetailsManager(dara, thida);
		return detailsService;
	}
	*/
}

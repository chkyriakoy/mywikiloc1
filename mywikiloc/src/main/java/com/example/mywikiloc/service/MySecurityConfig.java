package com.example.mywikiloc.service;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.example.mywikiloc.jwt.JwtTokenFIlter;
import com.example.mywikiloc.jwt.JwtTokenUtil;

@Configuration
@EnableWebSecurity

//@EnableGlobalMethodSecurity(
//    prePostEnabled = false, securedEnabled = false, jsr250Enabled = true
//)
public class MySecurityConfig extends WebSecurityConfigurerAdapter  {
	
	@Autowired
	private JwtTokenFIlter jwtTokenFilter;
	
	@Bean
	public UserDetailsService userDetailServ() {
		return new UserDetailsServiceImpl();
	}
	
	@Bean 
	public BCryptPasswordEncoder passwEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	// rest code
	@Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
	//rest code end
	
	
	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setPasswordEncoder(passwEncoder());
		authProvider.setUserDetailsService(userDetailServ());
		return authProvider;
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authenticationProvider());
		//super.configure(auth);
	}
	
	
	/*
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		.authorizeRequests()
		.antMatchers("/").permitAll()
	    .antMatchers("/edit/**").hasAnyAuthority("ADMIN", "EDITOR")
        .antMatchers("/delete/**").hasAuthority("ADMIN")
        .anyRequest().authenticated()
		.and()
		.formLogin()
		.loginPage("/users/login")
		.usernameParameter("email")
		.permitAll()
		.and()
		.logout()
		.logoutSuccessUrl("/")  
		.permitAll()
		.and()
        .exceptionHandling().accessDeniedPage("/users/403")
       	;
	}
	*/
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		 http.csrf().disable();
	     http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	     
	    
	     
	     http.authorizeRequests()
	     .antMatchers("/auth/login","/routes/test").permitAll()
	    // .antMatchers("/routes/")
        // .anonymous()
         .antMatchers("/routes/admin").hasAuthority("ADMIN")
         .antMatchers("/routes/viewer").hasAnyAuthority("ADMIN", "EDITOR")
	     .anyRequest().authenticated();
	     
	     http.addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class);
	     
	     /*
	     http.exceptionHandling().authenticationEntryPoint(
	    		 (reuqest, response, ex) ->{
	    			 response.sendError(HttpServletResponse.SC_UNAUTHORIZED, ex.getMessage());
	    		 }
    		 );
    	*/
			     http.exceptionHandling()
		         .authenticationEntryPoint(
		             (request, response, ex) -> {
		                 response.sendError(
		                     HttpServletResponse.SC_UNAUTHORIZED,
		                     ex.getMessage()
		                 );
		             }
		     );
			     
	}

}

package br.com.ifrn.personalapp.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import br.com.ifrn.personalapp.models.Role;
import br.com.ifrn.personalapp.security.CurrentUserDetailsService;
import br.com.ifrn.personalapp.service.PessoaService;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private PessoaService userService;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		 http
		 	.csrf().disable()
		 	.headers()
		 		.cacheControl()
		 		.contentTypeOptions()
		 		.httpStrictTransportSecurity()
		 		.and()
		 	.authorizeRequests()
		 		.antMatchers("/static/**").permitAll()
		 		.and()
		 	.formLogin()
		 		.loginPage("/login")		 		
		 		.permitAll()
		 		.and()
		 	.logout()
		 		.permitAll();
	}		
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth)throws Exception {
		auth
			.inMemoryAuthentication().withUser("admin").password("admin").authorities(AuthorityUtils.createAuthorityList(Role.ROLE_ADMIN.toString()))
			.and()
			.withUser("user").password("user").authorities(AuthorityUtils.createAuthorityList(Role.ROLE_USER.toString()));
		auth
			.userDetailsService(userDetailsService())
			.passwordEncoder(passwordEncoder());
	}
	
	@Bean
	public UserDetailsService userDetailsService() {
		return new CurrentUserDetailsService(userService);
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}	
}

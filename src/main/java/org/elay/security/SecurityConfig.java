package org.elay.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	@Autowired
	private DataSource datasource;
//	@Override
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		// si on laisse ssuper.configure(auth); donc la configuration par defaut
//		
//	}
	
	// comment chercher les users
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		PasswordEncoder passowrdEncoder=passwordEncoder();
		System.out.println("****************************");
		System.out.println(passowrdEncoder.encode("1234"));
		System.out.println("*****************************");
		
		auth.jdbcAuthentication().dataSource(datasource)
			.usersByUsernameQuery("SELECT username as principal, password as credentials, active from users where username=?")
			.authoritiesByUsernameQuery("select username as principal, role from users_roles where username=? ")
			.passwordEncoder(passowrdEncoder)
			.rolePrefix("ROLE_");
		
		
		
	/*	auth.inMemoryAuthentication().withUser("user1").password(passowrdEncoder.encode("1234")).roles("USER");
		auth.inMemoryAuthentication().withUser("user2").password(passowrdEncoder.encode("1234")).roles("USER");
		auth.inMemoryAuthentication().withUser("admin").password(passowrdEncoder.encode("1234")).roles("USER","ADMIN");
	*/	
	}
	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// http.formLogin() donc on definie la maniere d'authentification 
		// .loginPage("/login") pour definir la page d'auth
		http.formLogin().loginPage("/login");
		
		// un formulaire basci javascripte simple
//		http.httpBasic();
		
		// pour toutes les requites http necissaite une authentification
		// et donc spring ne sait pas comment faire l'authentifcation
		 // je defini les roles maintenant 
		http.authorizeRequests().antMatchers("/save**/**","/delet**/**","/form**/**","pro**/**").hasRole("ADMIN");
		http.authorizeRequests().antMatchers("/produits**/**").hasRole("USER");

		http.authorizeRequests().antMatchers("/user**/**","/connect","/login","/webjars**/**").permitAll();
		http.authorizeRequests().anyRequest().authenticated();
		// par defaut est active
		// pour desactiver il faut http.csrf().disable()
		// attaque csrf
		http.csrf();
		// pour redireger un user a une ressource non authorize 
		http.exceptionHandling().accessDeniedPage("/notAutorized");
		
		
	}
	
	// pour injecter ce objet dans le context de l'application @Bean
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new  BCryptPasswordEncoder();
	}
}


// si on laisse ssuper.configure(auth); donc la configuration par defaut super.configure(http);

package com.example.demo;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;


import com.example.demo.business.IAuthTokenService;
import com.example.demo.model.JwtTokenUtil;
import com.example.demo.persistence.UsuarioRepository;
import com.example.demo.web.Constantes;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsService userDetailService;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// auth.inMemoryAuthentication().withUser("pepe").password(passwordEncoder.encode("clave")).roles("USER").and()
		// .withUser("admin").password(passwordEncoder.encode("123")).roles("ADMIN");
		auth.userDetailsService(userDetailService);

	}

	// @Autowired
	// private PasswordEncoder passwordEncoder;

	@Bean
	public static PasswordEncoder passwordEncoder() {
		// return NoOpPasswordEncoder.getInstance();
		return new BCryptPasswordEncoder();
	}

	@Bean
	CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration configuration = new CorsConfiguration();
		configuration.setAllowedOrigins(Arrays.asList("*"));
		configuration.setAllowCredentials(true);
		configuration.setAllowedHeaders(Arrays.asList("Access-Control-Allow-Headers", "Access-Control-Allow-Origin",
				"Access-Control-Request-Method", "Access-Control-Request-Headers", "Origin", "Cache-Control",
				"Content-Type", "Authorization"));
		configuration.setAllowedMethods(Arrays.asList("DELETE", "GET", "POST", "PATCH", "PUT"));
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);
		return source;
	}

	@Autowired
	private IAuthTokenService authTokenService;
	@Autowired
	private UsuarioRepository usuariosDAO;
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	@Autowired

	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.addFilterAfter(new CustomTokenAuthenticationFilter(
				authTokenService, usuariosDAO,userDetailService,
				jwtTokenUtil),
				UsernamePasswordAuthenticationFilter.class);
		http.httpBasic();
		http.authorizeRequests().antMatchers("/api/v1/**").authenticated();
		http.authorizeRequests().antMatchers(Constantes.URL_AUTH_INFO, Constantes.URL_LOGINOK, Constantes.URL_TOKEN)
				.authenticated();
		http.authorizeRequests().antMatchers("/pruebajwt").permitAll();

		// http.formLogin().loginPage("/login.html").successForwardUrl("/index.html");
		// http.logout().deleteCookies("JSESSIONID", "rmiw3");
		// http.rememberMe().tokenRepository(rmRepository()).rememberMeParameter("rmp").rememberMeCookieName("rmiw3");

		http.formLogin().loginPage(Constantes.URL_DENY).successForwardUrl(Constantes.URL_LOGINOK)
				.loginProcessingUrl("/dologin").failureUrl(Constantes.URL_DENY);

		http.headers().frameOptions().disable();
		http.cors();
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

		http.csrf().disable();
	}

	/*
	 * @Autowired private DataSource ds;
	 * 
	 * private PersistentTokenRepository rmRepository() { JdbcTokenRepositoryImpl r
	 * = new JdbcTokenRepositoryImpl(); r.setDataSource(ds); return r; }
	 */
	/*
	 * CREATE TABLE `persistent_logins` ( `username` varchar(100) NOT NULL, `series`
	 * varchar(64) NOT NULL, `token` varchar(64) NOT NULL, `last_used` timestamp NOT
	 * NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP, PRIMARY KEY
	 * (`series`) ) ENGINE=InnoDB DEFAULT CHARSET=utf8
	 */

}

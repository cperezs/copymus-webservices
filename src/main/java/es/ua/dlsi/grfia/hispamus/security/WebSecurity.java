package es.ua.dlsi.grfia.hispamus.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private JsonAuthenticationEntryPoint authenticationEntryPoint;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// This is needed to enable the h2 console 
		http.cors().and().csrf().disable();
		http.headers().frameOptions().disable();
		http.authorizeRequests().antMatchers("/h2-console/**").permitAll();
		
		// Allow access to Swagger UI and OpenAPI documentation
		http.authorizeRequests().antMatchers(HttpMethod.GET, "/", "/swagger-ui/**", "/api-docs.yaml").permitAll();
		// Any other entry points are secured with an API key
		http.authorizeRequests().anyRequest().authenticated();
		http.addFilter(new ApiKeySecurityFilter(authenticationManager()));
		
		// Returns authorization errors as JSON, from http://www.baeldung.com/spring-security-basic-authentication
		http.httpBasic().authenticationEntryPoint(authenticationEntryPoint);
		
		// This disables session creation on Spring Security
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	}

	@Bean
	CorsConfigurationSource corsConfigurationSource() {
		final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
		return source;
	}
}

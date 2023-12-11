package DoAn.B19DCCN445.EMarket.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import DoAn.B19DCCN445.EMarket.filter.CustomAuthorizationFilter;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {
//	private final CustomAuthenticationFilter authenticationFilter;
	private final CustomAuthorizationFilter authorizationFilter;
	private final AuthenticationProvider authenticationProvider;

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
		http.csrf((csrf)-> csrf.disable());
		http.authorizeHttpRequests((authorizeHttpRequests)->{
			authorizeHttpRequests.requestMatchers("/api/auth/**").permitAll()
								.requestMatchers("/api/zaloPay/**").permitAll()
								.requestMatchers("/api/data/**").permitAll()
								.requestMatchers("/api/seller/**").hasAnyAuthority("SELLER","ADMIN")
								.anyRequest().authenticated();
								
		});	
		http.sessionManagement((sessionManagement)->sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS));	
		http.authenticationProvider(authenticationProvider)
			.addFilterBefore(authorizationFilter, UsernamePasswordAuthenticationFilter.class);	
		return http.build();
		
	}
}
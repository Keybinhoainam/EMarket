package DoAn.B19DCCN445.EMarket.filter;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.fasterxml.jackson.databind.ObjectMapper;

import DoAn.B19DCCN445.EMarket.service.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
public class CustomAuthorizationFilter extends OncePerRequestFilter {
	@Autowired
	private final JwtService jwtService;
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Override
	protected void doFilterInternal(
			@NonNull HttpServletRequest request, 
			@NonNull HttpServletResponse response,
			@NonNull FilterChain filterChain
			) throws ServletException, IOException {
//				if(request.getServletPath().startsWith("/api/auth")) {
//					filterChain.doFilter(request, response);
//					return;
//				}
				final String authHeader =request.getHeader("Authorization");
//				System.out.println(authHeader);
				final String jwt;
				String username=null;
				if(authHeader==null||!authHeader.startsWith("Bearer ")) {
					filterChain.doFilter(request, response);
					return;
				}
//				System.out.println("check jwt");
				jwt=authHeader.substring(7);
				try {
//					System.out.println(jwt);
					username=jwtService.extractUsername(jwt);
//					System.out.println(username);
				} catch (Exception e) {
					log.error("Error logging in: {}",e.getMessage());
					response.setHeader("error", e.getMessage());
					response.setContentType(MediaType.APPLICATION_JSON_VALUE);
					new ObjectMapper().writeValue(response.getOutputStream(),e.getMessage() );
				}
				if(username !=null && SecurityContextHolder.getContext().getAuthentication()==null) {
					UserDetails userDetails=this.userDetailsService.loadUserByUsername(username);
//					System.out.println(userDetails.getUsername());
					if(jwtService.isTokenValid(jwt, userDetails)) {
//						System.out.println(userDetails.getAuthorities());
						UsernamePasswordAuthenticationToken authToken=new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
						authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
						SecurityContextHolder.getContext().setAuthentication(authToken);
					}
//					System.out.println("ok");
					filterChain.doFilter(request, response);
				}
	}

}

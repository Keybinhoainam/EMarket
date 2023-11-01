//package DoAn.B19DCCN445.EMarket.filter;
//
//import java.io.IOException;
//
//import org.springframework.security.core.Authentication;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//import org.springframework.stereotype.Component;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//
//import DoAn.B19DCCN445.EMarket.model.Users;
//import DoAn.B19DCCN445.EMarket.service.JwtService;
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.http.MediaType;
//
//@Component
//@RequiredArgsConstructor
//@Slf4j
//public class CustomAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
//	
//	private final JwtService jwtService;
//	private final AuthenticationManager authenticationManager;
//	
//	@Override
//	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) {
//		String username= request.getParameter("username");
//		String password= request.getParameter("password");
//		log.info("Username is {}",username);log.info("Password is {}",password);
//		UsernamePasswordAuthenticationToken authenticationToken= new UsernamePasswordAuthenticationToken(
//				username, 
//				password
//				);
//		return authenticationManager.authenticate(authenticationToken);
//	}
//
//	@Override
//	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
//			Authentication authResult) throws IOException, ServletException {
//		Users user = (Users) authResult.getPrincipal();
//		var jwtToken=jwtService.genarateToken(user); 
////		response.setHeader("access_token", jwtToken);
//		response.setContentType(MediaType.APPLICATION_JSON_VALUE);
//		new ObjectMapper().writeValue(response.getOutputStream(), jwtToken);
//	}
//
//	
//}

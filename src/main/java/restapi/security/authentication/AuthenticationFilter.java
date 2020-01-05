package restapi.security.authentication;

import com.auth0.jwt.JWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.client.RestTemplate;
import restapi.security.dao.LoginViewModel;
import restapi.security.dao.UserDao;
import restapi.security.properties.JwtProperties;
import org.springframework.security.authentication.AuthenticationManager;
import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

import static com.auth0.jwt.algorithms.Algorithm.HMAC512;

public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {
   private AuthenticationManager authenticationManager;
   private MyDaoAuthenticationProvider authenticationProvider;

   public AuthenticationFilter(AuthenticationManager authenticationManager) {
      this.authenticationManager = authenticationManager;
   }

   @Override
   public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
         throws AuthenticationException {
      LoginViewModel credentials = null;
      try {
         credentials = new ObjectMapper().readValue(request.getInputStream(), LoginViewModel.class);
      } catch (IOException e) {
         e.printStackTrace();
      }

      String username = Objects.requireNonNull(credentials).getUsername();
      String password = credentials.getPassword();
      String uri = "http://localhost:8082/api/user/get/" + username;

      RestTemplate restTemplate = new RestTemplate();
      UserDao user = restTemplate.getForObject(uri, UserDao.class);
      if (user != null) {
         UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
               user.getUsername(), user.getPassword(), new ArrayList<>());
         this.authenticationProvider = new MyDaoAuthenticationProvider(username, password);
         // Authenticate user
         Authentication auth = this.authenticationProvider.authenticate(authenticationToken);
         System.out.println("User is authenticated");
         auth.isAuthenticated();
         return auth;
      } else {
         throw new AuthenticationCredentialsNotFoundException("User can not be found in the db!");
      }
   }

   @Override
   protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
         Authentication authResult) {
      // Create JWT Token
      String token = JWT.create().withSubject(authResult.getPrincipal().toString())
            .withExpiresAt(new Date(System.currentTimeMillis() + JwtProperties.EXPIRATION_TIME))
            .sign(HMAC512(JwtProperties.SECRET.getBytes()));

      // Add token in response
      response.addHeader(JwtProperties.HEADER_STRING, JwtProperties.TOKEN_PREFIX + token);
   }
}

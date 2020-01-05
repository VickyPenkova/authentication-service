package restapi.security.authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import restapi.security.service.UserPrincipalDetailsServices;

public class MyDaoAuthenticationProvider extends DaoAuthenticationProvider {
   private UserPrincipalDetailsServices userPrincipalDetailsServices;
   private String username;
   private String password;

   public MyDaoAuthenticationProvider(String username, String password) {
      this.username = username;
      this.password = password;
   }

   public MyDaoAuthenticationProvider() {
   }

   @Override
   public Authentication authenticate(Authentication authentication) throws AuthenticationException {
      UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken) authentication;
      String username = token.getName();

      UserDetails userDetails = null;
      if (username != null) {
         this.userPrincipalDetailsServices = new UserPrincipalDetailsServices();
         userDetails = userPrincipalDetailsServices.loadUserByUsername(username);
      }

      if (userDetails == null) {
         throw new UsernameNotFoundException("username " + username + " not found");
      } else if (!userDetails.isEnabled()) {
         throw new DisabledException("disabled");
      }
      BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
      if (!passwordEncoder.matches(this.password, token.getCredentials().toString())) {
         throw new BadCredentialsException("Invalid username/password");
      }

      return authentication;
   }
}

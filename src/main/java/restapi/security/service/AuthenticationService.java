package restapi.security.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import restapi.security.dao.UserDao;
import restapi.security.response.JWTTokenResponse;

@Service
public class AuthenticationService {
   private JwtTokenService jwtTokenService;
   private PasswordEncoder passwordEncoder;

   public AuthenticationService(JwtTokenService jwtTokenService, PasswordEncoder passwordEncoder) {
      this.jwtTokenService = jwtTokenService;
      this.passwordEncoder = passwordEncoder;
   }

   public JWTTokenResponse generateJWTToken(String username, String password, String role) throws Exception {
      String uri = "";
      if (role.toUpperCase().equals("DOCTOR")) {
         uri = "http://localhost:8082/api/doctor/username/" + username;
      } else if (role.toUpperCase().equals("PATIENT")) {

         uri = "http://localhost:8082/api/patient/username/" + username;
      }

      System.out.println(uri);
      RestTemplate restTemplate = new RestTemplate();
      UserDao user = restTemplate.getForObject(uri, UserDao.class);

      if (user == null) {
         throw new Exception("User can not be retrieved from " + uri);
      }

      JWTTokenResponse responseToken = new JWTTokenResponse(jwtTokenService.generateToken(username));
      System.out.println(responseToken.getToken());
      return responseToken;
   }
}

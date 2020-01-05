package restapi.security.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import restapi.security.dao.UserDao;

@Service
public class UserPrincipalDetailsServices implements UserDetailsService {
   @Override
   public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
      String uri = "http://localhost:8082/api/user/get/" + s;
      RestTemplate restTemplate = new RestTemplate();
      UserDao user = restTemplate.getForObject(uri, UserDao.class);
      System.out.println("UserPrincipalDetailsServices pass: " + user.getPassword() );
      restapi.security.service.UserPrincipal userPrincipal = new restapi.security.service.UserPrincipal (user);
      return userPrincipal;
   }
}

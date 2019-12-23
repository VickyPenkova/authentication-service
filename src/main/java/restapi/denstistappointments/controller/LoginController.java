package restapi.denstistappointments.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import restapi.denstistappointments.dao.UserDao;

@RestController
@RequestMapping("api/public")
@CrossOrigin
public class LoginController {
   @RequestMapping("/login")
   public void login(@RequestBody UserDao userDao){
      final String uri = "http://localhost:8082/api";

      RestTemplate restTemplate = new RestTemplate();
      String result = restTemplate.getForObject(uri, String.class);

      System.out.println(result);
   }

}

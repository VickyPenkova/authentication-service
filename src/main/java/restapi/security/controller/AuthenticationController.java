package restapi.security.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import restapi.security.request.AuthenticationRequest;
import restapi.security.response.JWTTokenResponse;
import restapi.security.service.AuthenticationService;

import javax.persistence.EntityNotFoundException;

@RestController
@RequestMapping
public class AuthenticationController {
   private AuthenticationService authenticationService;

   public AuthenticationController(AuthenticationService authenticationService) {
      this.authenticationService = authenticationService;
   }

   @PostMapping("/login")
   public ResponseEntity<JWTTokenResponse> createCustomer(@RequestBody AuthenticationRequest request) throws Exception {
      return new ResponseEntity<>(
            authenticationService.generateJWTToken(request.getUsername(), request.getPassword(), request.getRole()),
            HttpStatus.OK);
   }

   @ExceptionHandler(EntityNotFoundException.class)
   public ResponseEntity<String> handleEntityNotFoundException(EntityNotFoundException ex) {
      return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
   }
}

package restapi.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import restapi.security.authentication.AuthenticationFilter;
import restapi.security.authentication.MyDaoAuthenticationProvider;
import restapi.security.service.UserPrincipalDetailsServices;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
   private UserPrincipalDetailsServices userPrincipalDetailsService;

   public SecurityConfiguration(UserPrincipalDetailsServices userPrincipalDetailsService) {
      this.userPrincipalDetailsService = userPrincipalDetailsService;
   }

   @Override
   protected void configure(AuthenticationManagerBuilder auth) throws Exception {
      auth.authenticationProvider(myAuthProvider());
   }

   @Override
   protected void configure(HttpSecurity http) throws Exception {
      http
            // remove csrf and state in session because in jwt we do not need them
            .csrf().disable().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
            // add jwt filters (1. authentication, 2. authorization)
            .addFilter(new AuthenticationFilter(authenticationManager())).authorizeRequests()
            // configure access rules
            .antMatchers(HttpMethod.POST, "/login").permitAll();
   }

   @Bean
   public MyDaoAuthenticationProvider myAuthProvider() {
      MyDaoAuthenticationProvider provider = new MyDaoAuthenticationProvider();
      provider.setPasswordEncoder(passwordEncoder());
      provider.setUserDetailsService(this.userPrincipalDetailsService);
      return provider;
   }

   @Bean
   PasswordEncoder passwordEncoder() {
      return new BCryptPasswordEncoder();
   }
}

package restapi.security;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
@EnableEurekaClient
public class BootSecurityApplication {

    public static void main(String[] args) {
        ApplicationContext applicationContext = SpringApplication.run(BootSecurityApplication.class, args);
        for (String name : applicationContext.getBeanDefinitionNames()) {
            System.out.println(name);
        }
    }

}

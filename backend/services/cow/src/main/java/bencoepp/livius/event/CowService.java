package bencoepp.livius.event;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;

@Configuration
@EnableMethodSecurity
@SpringBootApplication
@EntityScan(basePackages="de.bencoepp")
@ComponentScan(basePackages="bencoepp.livius")
@EnableMongoRepositories(basePackages = "bencoepp.livius")
@EnableDiscoveryClient
@EnableFeignClients
public class CowService {
    public static void main(String[] args) {
        SpringApplication.run(CowService.class, args);
    }

}

package bencoepp.livius.gateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * This class is responsible for configuring the gateway routing in the application.
 */
@Configuration
public class GatewayConfig {
    /**
     * Creates a custom RouteLocator bean for routing requests.
     *
     * @param builder the RouteLocatorBuilder object used to build the route
     * @return a RouteLocator object representing the custom route
     */
    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("user-service", r -> r.path("/api/v1/user/**")
                        .uri("lb://USER-SERVICE"))
                .route("auth-service", r -> r.path("/api/v1/auth/**")
                        .uri("lb://USER-SERVICE"))
                .route("cow-service", r -> r.path("/api/v1/cow/**")
                        .uri("lb://COW-SERVICE"))
                .route("weather-service", r -> r.path("/api/v1/weather/**")
                        .uri("lb://WEATHER-SERVICE"))
                .build();
    }

}
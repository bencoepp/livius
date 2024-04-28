package bencoepp.livius.info.config;

import io.micrometer.observation.ObservationRegistry;
import io.micrometer.observation.aop.ObservedAspect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration class for observation-related beans.
 */
@Configuration
public class ObservationConfig {
    /**
     * Creates a new instance of the ObservedAspect class with the provided ObservationRegistry.
     *
     * @param registry The ObservationRegistry to use for the ObservedAspect instance.
     * @return The created ObservedAspect instance.
     */
    @Bean
    ObservedAspect observedAspect(ObservationRegistry registry) {
        return new ObservedAspect(registry);
    }
}

package bencoepp.livius.security;

import feign.RequestInterceptor;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * The FeignClientConfig class provides configuration for Feign client.
 */
public class FeignClientConfig {

    /**
     * Returns a RequestInterceptor object that is used to intercept Feign client requests and add the "Authorization" header with the Bearer token.
     * <p>
     * This method creates and returns an anonymous inner class that implements the RequestInterceptor interface. The apply() method of this class is overridden
     * to add the "Authorization" header to the request template. The value of the "Authorization" header is retrieved from the current HTTP request header, and
     * if it starts with "Bearer ", it is added to the request template.
     *
     * @return a RequestInterceptor object
     */
    @Bean
    public RequestInterceptor requestTokenBearerInterceptor() {
        return requestTemplate -> {
            ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            if (requestAttributes != null) {
                HttpServletRequest request = requestAttributes.getRequest();
                String authorizationHeader = request.getHeader("Authorization");
                if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
                    requestTemplate.header("Authorization", authorizationHeader);
                }
            }
        };
    }
}

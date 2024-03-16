package bencoepp.livius.security.jwt;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import java.io.IOException;

/**
 * AuthEntryPointJwt is an implementation of the AuthenticationEntryPoint interface.
 * It is used to handle unauthorized access to protected resources and return an HTTP 401 Unauthorized response.
 */
@Component
public class AuthEntryPointJwt implements AuthenticationEntryPoint {

    private static final Logger logger = LoggerFactory.getLogger(AuthEntryPointJwt.class);

    /**
     * This method is invoked when a user tries to access a protected resource without proper authentication.
     *
     * @param request The HttpServletRequest object representing the incoming request.
     * @param response The HttpServletResponse object representing the outgoing response.
     * @param authException The AuthenticationException object representing the authentication exception that occurred.
     * @throws IOException If an I/O error occurs.
     */
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException {
        logger.error("Unauthorized error: {}", authException.getMessage());
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Error: Unauthorized");
    }

}

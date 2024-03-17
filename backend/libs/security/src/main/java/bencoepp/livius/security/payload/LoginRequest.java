package bencoepp.livius.security.payload;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

/**
 * The LoginRequest class represents a login request.
 */
@Getter
@Setter
public class LoginRequest {
    /**
     * The username associated with the login request.
     * <p>
     * This variable represents the username provided by the user during the login process.
     * It is a non-blank string that identifies the user.
     * The username field is annotated with the @NotBlank annotation to ensure that it is not empty or consisting of only whitespace characters.
     * <p>
     * Example usage:
     * <p>
     * LoginRequest loginRequest = new LoginRequest();
     * loginRequest.setUsername("john.doe");
     * String username = loginRequest.getUsername();
     *
     * @see LoginRequest
     * @see NotBlank
     */
    @NotBlank
    private String username;
    /**
     * The password associated with the login request.
     * <p>
     * This variable represents the password provided by the user during the login process.
     * It is a non-blank string that is used for authentication purposes.
     * The password field is annotated with the @NotBlank annotation to ensure that it is not empty or consisting of only whitespace characters.
     * <p>
     * Example usage:
     * <p>
     * LoginRequest loginRequest = new LoginRequest();
     * loginRequest.setPassword("password123");
     * String password = loginRequest.getPassword();
     *
     * @see LoginRequest
     * @see NotBlank
     */
    @NotBlank
    private String password;
}

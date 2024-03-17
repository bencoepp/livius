package bencoepp.livius.security.payload;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import java.util.Set;

/**
 * The SignupRequest class represents the request object for user signup.
 */
@Getter
@Setter
public class SignupRequest {
    /**
     * The username variable represents the username of a user during signup.
     *
     * <p>
     * Constraints:
     * <ul>
     *   <li>Cannot be null or empty</li>
     *   <li>Must be between 3 and 50 characters in length</li>
     * </ul>
     *
     * @see SignupRequest
     */
    @NotBlank
    @Size(min = 3, max = 50)
    private String username;

    /**
     * The email variable represents the email address of a user during signup.
     *
     * <p>
     * Constraints:
     * <ul>
     *   <li>Cannot be null or empty</li>
     *   <li>Must be between 1 and 100 characters in length</li>
     *   <li>Must be a valid email address format</li>
     * </ul>
     *
     * @see SignupRequest
     */
    @NotBlank
    @Size(max = 100)
    @Email
    private String email;
    /**
     * The roles variable represents a set of roles assigned to a user during signup.
     *
     * <p>
     * Roles are used to define the level of access and privileges a user has within the system.
     * This variable is part of the SignupRequest class, which is used as the request object for user signup.
     *
     * <p>
     * The roles are represented as a Set of String values. Each String value represents a specific role assigned to the user.
     *
     * <p>
     * Example usage:
     * <pre>{@code
     *     SignupRequest signupRequest = new SignupRequest();
     *     signupRequest.setRoles(new HashSet<>(Arrays.asList("USER", "ADMIN")));
     * }</pre>
     *
     * @since 1.0.0
     * @see SignupRequest
     */
    private Set<String> roles;
    /**
     * The password field represents the user's password for authentication purposes.
     *
     * <p>
     * Constraints:
     * <ul>
     *   <li>Cannot be null or empty</li>
     *   <li>Must be between 12 and 150 characters in length</li>
     * </ul>
     *
     * @see SignupRequest
     */
    @NotBlank
    @Size(min = 12, max = 150)
    private String password;
}

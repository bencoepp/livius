package bencoepp.livius.security.payload;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
/**
 * JwtResponse represents the response object returned from the authentication process.
 * It contains the JWT token, user information, and the roles associated with the user.
 */
@Getter
@Setter
public class JwtResponse {
    /**
     * The variable "token" holds the JWT token associated with the authenticated user.
     * It is a String type variable.
     * <p>
     * JwtResponse is the containing class of the initial symbol.
     * It represents the response object returned from the authentication process.
     * It contains the JWT token, user information, and the roles associated with the user.
     * <p>
     * This variable is set using the constructor of JwtResponse, where the token parameter is passed.
     * <p>
     * Example usage:
     * JwtResponse jwtResponse = new JwtResponse("eyJhbGciOiJIUzUxMi...", "1", "john", "john@example.com", ["ROLE_USER"]);
     * String token = jwtResponse.getToken();
     */
    private String token;
    /**
     * The variable "type" represents the type of authentication used.
     * It is a private String variable and has a default value of "Bearer".
     * <p>
     * This variable belongs to the class JwtResponse, which represents the response object returned from the authentication process.
     * JwtResponse contains the JWT token, user information, and the roles associated with the user.
     * <p>
     * The value of "type" can be accessed using the getter and modified using the setter methods of JwtResponse class.
     * <p>
     * Example usage:
     * JwtResponse jwtResponse = new JwtResponse("eyJhbGciOiJIUzUxMi...", "1", "john", "john@example.com", ["ROLE_USER"]);
     * String type = jwtResponse.getType(); // Returns "Bearer"
     * jwtResponse.setType("CustomType"); // Changes the value of type to "CustomType"
     */
    private String type = "Bearer";
    /**
     * The variable "id" represents the unique identifier of a user.
     * It is a private String variable.
     * <p>
     * This variable belongs to the class JwtResponse, which represents the response object returned from the authentication process.
     * JwtResponse contains the JWT token, user information, and the roles associated with the user.
     * <p>
     * The value of "id" can be accessed using the getter method getId() of JwtResponse class.
     * <p>
     * Example usage:
     * JwtResponse jwtResponse = new JwtResponse("eyJhbGciOiJIUzUxMi...", "1", "john", "john@example.com", ["ROLE_USER"]);
     * String id = jwtResponse.getId();
     * System.out.println(id); // Prints "1"
     */
    private String id;
    /**
     * Represents the username associated with a user.
     * <p>
     * This variable is a private instance variable of the class JwtResponse.
     * It stores the username of the user returned in the JwtResponse object.
     *
     * @see JwtResponse
     */
    private String username;
    /**
     * This variable represents the email address of a user.
     */
    private String email;
    private List<String> roles;

    /**
     * Constructs a new JwtResponse object with the specified parameters.
     *
     * @param accessToken the JWT token associated with the authenticated user
     * @param id the unique identifier of the user
     * @param username the username of the user
     * @param email the email address of the user
     * @param roles the roles associated with the user
     */
    public JwtResponse(String accessToken, String id, String username, String email, List<String> roles) {
        this.token = accessToken;
        this.id = id;
        this.username = username;
        this.email = email;
        this.roles = roles;
    }
}

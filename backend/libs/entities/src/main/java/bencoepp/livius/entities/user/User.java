package bencoepp.livius.entities.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashSet;
import java.util.Set;

/**
 * Represents a user entity in a MongoDB collection.
 *
 * The User class is used to model a user within a system,
 * storing their username, email, and password. It also
 * contains a Set of roles associated with the user, which
 * define their permissions and access within the system.
 */
@Getter
@Setter
@Document(collection = "users")
public class User {
    /**
     * The unique identifier for the user.
     */
    @Id
    private String id;

    /**
     * The username of the user.
     * The username must be unique and is limited to 20 characters.
     */
    @NotBlank
    @Size(max = 50)
    private String username;

    /**
     * The email address of the user.
     * The email must be a valid format and is limited to 100 characters.
     */
    @NotBlank
    @Size(max = 100)
    @Email
    private String email;

    /**
     * The password of the user.
     * The password is limited to 150 characters.
     */
    @NotBlank
    @Size(max = 150)
    private String password;

    /**
     * A set of roles associated with the user.
     * Roles define the user's permissions and access within the system.
     */
    @DBRef
    private Set<Role> roles = new HashSet<>();

    /**
     * Represents a user entity in a MongoDB collection.
     */
    public User() {
    }

    /**
     * Represents a User entity in a MongoDB collection.
     *
     * This class is used to model a user within a system,
     * storing their username, email, and password.
     *
     * @param username: The username of the user.
     *                  The username must be unique and is limited to 20 characters.
     * @param email: The email address of the user.
     *               The email must be a valid format and is limited to 100 characters.
     * @param password: The password of the user.
     *                  The password is limited to 150 characters.
     */
    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }
}

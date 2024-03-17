package bencoepp.livius.entities.user;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Represents a role entity in a MongoDB collection.
 * This class is used to model roles within a system, defining different levels of access or permissions.
 */
@Getter
@Setter
@Document(collection = "roles")
public class Role {
    /**
     * The unique identifier for the role.
     */
    @Id
    private String id;

    /**
     * The name of the role, represented by an instance of ERole enum.
     * This defines the specific role type (e.g., ADMIN, USER).
     */
    private ERole name;
}

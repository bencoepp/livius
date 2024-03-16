package bencoepp.livius.repositories.user;

import bencoepp.livius.entities.user.ERole;
import bencoepp.livius.entities.user.Role;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.Optional;

/**
 * The RoleRepository interface represents a repository for managing roles in a MongoDB collection.
 * It provides methods for querying and manipulating role entities.
 */
public interface RoleRepository extends MongoRepository<Role, String> {
    /**
     * Retrieves a role entity from the MongoDB collection by its name.
     *
     * @param name the name of the role to search for
     * @return an Optional object containing the role if found, otherwise empty
     */
    Optional<Role> findByName(ERole name);
}

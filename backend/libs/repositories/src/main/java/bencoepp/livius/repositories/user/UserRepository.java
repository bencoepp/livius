package bencoepp.livius.repositories.user;

import bencoepp.livius.entities.user.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.Optional;

/**
 * The UserRepository interface provides methods to interact with the "users" collection in MongoDB.
 * It extends the MongoRepository interface and represents a repository for User entities.
 */
public interface UserRepository extends MongoRepository<User, String> {
    /**
     * Finds a user by their username.
     *
     * @param username The username of the user.
     * @return An Optional<User> object representing the user if found, otherwise an empty Optional.
     */
    Optional<User> findByUsername(String username);
    /**
     * Checks if a user exists by their username.
     *
     * @param username The username of the user.
     * @return true if a user with the given username exists, false otherwise.
     */
    Boolean existsByUsername(String username);
    /**
     * Checks if a user exists by their email address.
     *
     * @param email The email address of the user.
     * @return true if a user with the given email address exists, false otherwise.
     */
    Boolean existsByEmail(String email);

    /**
     * Deletes a user from the repository based on their email address.
     *
     * @param email The email address of the user to delete.
     * @return The number of documents deleted.
     */
    long deleteByEmail(String email);
}

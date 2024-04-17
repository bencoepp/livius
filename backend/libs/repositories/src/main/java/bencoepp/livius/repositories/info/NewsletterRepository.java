package bencoepp.livius.repositories.info;

import bencoepp.livius.entities.info.Newsletter;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * NewsletterRepository Interface
 *
 * <p>
 * The NewsletterRepository interface extends the MongoRepository interface and provides methods for accessing and manipulating newsletters in a MongoDB database.
 * </p>
 *
 * <p>
 * Example usage:
 * </p>
 *
 * <pre>{@code
 * // Create a new newsletter repository
 * NewsletterRepository newsletterRepository = new NewsletterRepositoryImpl();
 *
 * // Save a newsletter to the database
 * Newsletter newsletter = new Newsletter();
 * newsletter.setId("1");
 * // Set other properties of the newsletter...
 * newsletterRepository.save(newsletter);
 *
 * // Retrieve a newsletter from the database
 * String newsletterId = "1";
 * Newsletter retrievedNewsletter = newsletterRepository.findById(newsletterId);
 * }</pre>
 *
 * @param <Newsletter>  the type of entity to be accessed
 * @param <String> the type of the entity's identifier
 *
 * @see MongoRepository
 */
public interface NewsletterRepository extends MongoRepository<Newsletter, String> {
}

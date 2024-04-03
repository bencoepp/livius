package bencoepp.livius.repositories.war;

import bencoepp.livius.entities.war.War;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * The WarRepository interface provides CRUD operations for the War class in the MongoDB database.
 * It extends the MongoRepository interface, which is part of the Spring Data MongoDB framework.
 * The MongoRepository interface provides methods for querying and manipulating data in the MongoDB database.
 *
 * This interface does not define any additional methods because it inherits all the necessary methods from the MongoRepository interface.
 *
 * Example usage:
 * ```
 * WarRepository warRepository = new WarRepositoryImpl();
 * War war = new War();
 * war.setName("World War II");
 * war.setType(2);
 * war.setRegion(3);
 * warRepository.save(war);
 *
 * String warId = war.getId();
 * Optional<War> retrievedWar = warRepository.findById(warId);
 *
 * retrievedWar.ifPresent(w -> {
 *     System.out.println("Name: " + w.getName());
 *     System.out.println("Type: " + w.getType());
 *     System.out.println("Region: " + w.getRegion());
 * });
 * ```
 *
 * @see MongoRepository
 * @see War
 */
public interface WarRepository extends MongoRepository<War, String> {
}

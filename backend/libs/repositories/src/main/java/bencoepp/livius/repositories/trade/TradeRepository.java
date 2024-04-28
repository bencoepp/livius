package bencoepp.livius.repositories.trade;

import bencoepp.livius.entities.trade.Trade;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * This interface represents a repository for storing and retrieving trade data.
 * It extends the MongoRepository interface to provide MongoDB specific CRUD operations.
 *
 * @param <Trade> The type of trade entity to be stored in the repository.
 * @param <String> The type of the trade entity's identifier.
 */
public interface TradeRepository extends MongoRepository<Trade, String> {
}

package bencoepp.livius.utils;

import bencoepp.livius.entities.AutogenerateId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import java.util.Objects;
import static org.springframework.data.mongodb.core.FindAndModifyOptions.options;

/**
 * This class is responsible for generating sequence numbers for specific sequence names.
 */
@Service
public class SequenceGeneratorService {
    @Autowired
    private MongoOperations mongoOperations;

    /**
     * Retrieves the sequence number for the given sequence name.
     *
     * @param sequenceName the name of the sequence
     * @return the sequence number as a string, or "1" if the counter is null
     */
    public String getSequenceNumber(String sequenceName){
        Query query=new Query(Criteria.where("order_id").is(sequenceName));

        Update update=new Update().inc("seq",1);

        AutogenerateId counter = mongoOperations
                .findAndModify(query,
                        update, options().returnNew(true).upsert(true),
                        AutogenerateId.class);

        return !Objects.isNull(counter) ? counter.getSeq() : "1";
    }
}
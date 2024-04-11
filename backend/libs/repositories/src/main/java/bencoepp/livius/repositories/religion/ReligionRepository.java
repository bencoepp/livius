package bencoepp.livius.repositories.religion;

import bencoepp.livius.entities.religion.Religion;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * The {@code ReligionRepository} interface represents a repository for managing {@link Religion} objects.
 * It provides CRUD (Create, Read, Update, Delete) operations for the {@link Religion} objects.
 * This repository extends the {@link MongoRepository} interface and uses MongoDB for data persistence.
 */
public interface ReligionRepository extends MongoRepository<Religion, String> {
    /**
     * Finds a list of {@link Religion} objects by the specified year.
     *
     * @param year The year to search for.
     * @return A list of {@link Religion} objects matching the specified year.
     */
    List<Religion> findByYear(Integer year);

    /**
     * Finds a list of {@link Religion} objects with a year less than or equal to the specified year.
     *
     * @param year The year to be used as the upper bound for the search.
     * @return A list of {@link Religion} objects with a year less than or equal to the specified year.
     */
    List<Religion> findByYearLessThanEqual(Integer year);

    /**
     * Finds a list of {@link Religion} objects with a year greater than or equal to the specified year.
     *
     * @param year The year to be used as the lower bound for the search.
     * @return A list of {@link Religion} objects with a year greater than or equal to the specified year.
     */
    List<Religion> findByYearGreaterThanEqual(Integer year);

    /**
     * Finds a list of {@link Religion} objects with the specified state ID.
     *
     * @param id The state ID to search for.
     * @return A list of {@link Religion} objects matching the specified state ID.
     */
    List<Religion> findByState_Id(String id);

    /**
     * Finds a list of {@link Religion} objects by the specified cow ID.
     *
     * @param cowId The cow ID to search for.
     * @return A list of {@link Religion} objects matching the specified cow ID.
     */
    List<Religion> findByCowId(Integer cowId);

    /**
     * Finds a list of {@link Religion} objects by the specified data type.
     *
     * @param dataType The data type to search for.
     * @return A list of {@link Religion} objects matching the specified data type.
     */
    List<Religion> findByDataType(String dataType);

    /**
     * Finds a list of {@link Religion} objects by the specified reliability of record.
     *
     * @param reliabilityOfRecord The reliability of record to search for.
     * @return A list of {@link Religion} objects matching the specified reliability of record.
     */
    List<Religion> findByReliabilityOfRecord(String reliabilityOfRecord);

    /**
     * Finds a list of Religion objects by the specified level of reliability of record.
     *
     * @param levelOfReliabilityOfRecord The level of reliability of record to search for.
     * @return A list of Religion objects matching the specified level of reliability of record.
     */
    List<Religion> findByLevelOfReliabilityOfRecord(String levelOfReliabilityOfRecord);
}

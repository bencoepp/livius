package bencoepp.livius.repositories.job;

import bencoepp.livius.entities.job.Job;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * The JobRepository interface is a repository that provides CRUD (Create, Read, Update, Delete) operations
 * for Job objects. It extends the MongoRepository interface, which provides MongoDB-specific methods for
 * interacting with the database.
 * <p>
 * JobRepository is implemented using the MongoDB driver's operations for accessing MongoDB collections.
 * It uses the `jobs` collection in the MongoDB database to store and retrieve Job objects.
 * <p>
 * This interface does not include any additional methods beyond the ones provided by the MongoRepository interface.
 * It inherits the following methods from MongoRepository:
 * <p>
 * - Iterable<T> findAll() - Returns all Job objects in the database.
 * - Optional<T> findById(ID id) - Returns the Job object with the specified ID, if it exists.
 * - <S extends T> S save(S entity) - Saves the given Job object in the database and returns the saved entity.
 * - void deleteById(ID id) - Deletes the Job object with the specified ID from the database, if it exists.
 * - void delete(T entity) - Deletes the specified Job object from the database.
 * <p>
 * Additionally, the JobRepository interface inherits methods for pagination, sorting, and other common database operations
 * from the PagingAndSortingRepository and CrudRepository interfaces.
 * <p>
 * Example usage:
 * <p>
 * // Autowire the JobRepository instance
 * @Autowired
 * private JobRepository jobRepository;
 * <p>
 * // Save a new Job object
 * Job job = new Job();
 * job.setName("My Job");
 * job.setUrl("<a href="https://example.com">...</a>");
 * job.setType("Type");
 * job.setStatus("Status");
 * job.setHandler("Handler");
 * job.setDependencies(new ArrayList<>());
 * Job savedJob = jobRepository.save(job);
 * <p>
 * // Find a Job by ID
 * Optional<Job> optionalJob = jobRepository.findById(savedJob.getId());
 * if (optionalJob.isPresent()) {
 *     Job foundJob = optionalJob.get();
 *     // Perform operations on foundJob
 * }
 * <p>
 * // Find all Jobs
 * Iterable<Job> allJobs = jobRepository.findAll();
 * for (Job job : allJobs) {
 *     // Perform operations on each job
 * }
 * <p>
 * // Delete a Job by ID
 * jobRepository.deleteById(savedJob.getId());
 * <p>
 * // Delete a Job object
 * jobRepository.delete(savedJob);
 */
public interface JobRepository extends MongoRepository<Job, String> {
    /**
     * Returns the Job object with the specified name, if it exists.
     *
     * @param name the name of the job to find
     * @return the Job object with the specified name, or null if no such job exists
     */
    Job findByName(String name);

    /**
     * Returns whether a Job object with the specified name exists.
     *
     * @param name the name of the job to check
     * @return true if a Job object with the specified name exists, false otherwise
     */
    boolean existsByName(String name);
}

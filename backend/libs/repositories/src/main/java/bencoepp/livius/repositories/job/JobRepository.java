package bencoepp.livius.repositories.job;

import bencoepp.livius.entities.job.Job;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * The JobRepository interface is a repository that provides CRUD (Create, Read, Update, Delete) operations
 * for Job objects. It extends the MongoRepository interface, which provides MongoDB-specific methods for
 * interacting with the database.
 *
 * JobRepository is implemented using the MongoDB driver's operations for accessing MongoDB collections.
 * It uses the `jobs` collection in the MongoDB database to store and retrieve Job objects.
 *
 * This interface does not include any additional methods beyond the ones provided by the MongoRepository interface.
 * It inherits the following methods from MongoRepository:
 *
 * - Iterable<T> findAll() - Returns all Job objects in the database.
 * - Optional<T> findById(ID id) - Returns the Job object with the specified ID, if it exists.
 * - <S extends T> S save(S entity) - Saves the given Job object in the database and returns the saved entity.
 * - void deleteById(ID id) - Deletes the Job object with the specified ID from the database, if it exists.
 * - void delete(T entity) - Deletes the specified Job object from the database.
 *
 * Additionally, the JobRepository interface inherits methods for pagination, sorting, and other common database operations
 * from the PagingAndSortingRepository and CrudRepository interfaces.
 *
 * Example usage:
 *
 * // Autowire the JobRepository instance
 * @Autowired
 * private JobRepository jobRepository;
 *
 * // Save a new Job object
 * Job job = new Job();
 * job.setName("My Job");
 * job.setUrl("https://example.com");
 * job.setType("Type");
 * job.setStatus("Status");
 * job.setHandler("Handler");
 * job.setDependencies(new ArrayList<>());
 * Job savedJob = jobRepository.save(job);
 *
 * // Find a Job by ID
 * Optional<Job> optionalJob = jobRepository.findById(savedJob.getId());
 * if (optionalJob.isPresent()) {
 *     Job foundJob = optionalJob.get();
 *     // Perform operations on foundJob
 * }
 *
 * // Find all Jobs
 * Iterable<Job> allJobs = jobRepository.findAll();
 * for (Job job : allJobs) {
 *     // Perform operations on each job
 * }
 *
 * // Delete a Job by ID
 * jobRepository.deleteById(savedJob.getId());
 *
 * // Delete a Job object
 * jobRepository.delete(savedJob);
 */
public interface JobRepository extends MongoRepository<Job, String> {
    Job findByName(String name);

    boolean existsByName(String name);
}

package bencoepp.livius.data.events;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;
/**
 * The JobEvent class represents an event that occurs during the execution of a job.
 * It extends the ApplicationEvent class, which is the base class for application events in Spring.
 * JobEvent contains information about the condition and jobId of the job.
 * <p>
 * Use the JobEvent class to pass information about the job to event listeners or handlers.
 */
@Getter
public class JobEvent extends ApplicationEvent {
    /**
     * The condition variable represents a specific condition related to a job event.
     * <p>
     * This variable is a private field in the JobEvent class, which extends the ApplicationEvent class in Spring. It is used to hold information about the condition of the job that
     * triggered the event.
     * <p>
     * Conditions can vary depending on the type of job and its associated event. They can be used to indicate success, failure, progress, or any other specific state or criteria
     *.
     * <p>
     * The condition variable is of type String and holds the value of the specific condition related to the job event.
     * <p>
     * This variable is private to ensure encapsulation and should only be accessed through appropriate getter and setter methods defined in the JobEvent class.
     *
     * @see JobEvent
     * @see ApplicationEvent
     */
    private String condition;
    /**
     * The jobId variable represents the unique identifier of a job.
     * <p>
     * This variable is a private field in the containing class. It is used to hold the jobId associated with a specific job.
     * <p>
     * The jobId is a unique identifier assigned to a job, typically generated by the system or provided by an external source. It distinguishes one job from another and is used for
     * <p>
     * tracking and referencing purposes.
     * <p>
     * The jobId variable is of type String and holds the value of the job identifier.
     * <p>
     * This variable is private to ensure encapsulation and should only be accessed through appropriate getter and setter methods defined in the containing class.
     */
    private String jobId;
    /**
     * The JobEvent class represents an event that occurs during the execution of a job.
     * It extends the ApplicationEvent class, which is the base class for application events in Spring.
     * JobEvent contains information about the condition and jobId of the job.
     * <p>
     * Use the JobEvent class to pass information about the job to event listeners or handlers.
     */
    public JobEvent(Object source, String condition, String jobId) {
        super(source);
        this.condition = condition;
        this.jobId = jobId;
    }
}

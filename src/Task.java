import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * This class represents a task
 */
public class Task implements Cloneable {

    private final String description; // description of task
    private Date dueDate; // due date of task

    /**
     * constructor
     *
     * @param description String representing description of task
     * @param dueDate     due date of task
     */
    public Task(String description, Date dueDate) {
        this.description = description;
        this.dueDate = dueDate;
    }

    /**
     * getter for description attribute
     *
     * @return description(String)
     */
    public String getDescription() {
        return description;
    }

    /**
     * getter for dueDate attribute
     *
     * @return dueDate(Date)
     */
    public Date getDueDate() {
        return dueDate;
    }

    /**
     * setter for dueDate attribute- sets the time of due date to time of given date
     *
     * @param dueDate Date holding time to be set to dueDate
     */
    void setDueDate(Date dueDate) {
        this.dueDate.setTime(dueDate.getTime());
    }


    /**
     * This function produces a String with description and due date
     *
     * @return a String with description and due date
     */
    @Override
    public String toString() {
        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        String strDate = dateFormat.format(dueDate);
        return description + ", " + strDate;
    }

    /**
     * This function returns a deep cloned task of this object
     *
     * @return a deep cloned task of this object
     */
    @Override
    protected Task clone() {
        try {
            Task task = (Task) super.clone();
            task.dueDate = new Date(getDueDate().getTime());
            return task;
        } catch (CloneNotSupportedException exception) {
            return null;
        }
    }

    /**
     * This function checks if given object equals to this Task
     *
     * @param obj any object that we want to test if equals to this Task
     * @return true if obj equals to this Task false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null) return false;
        if (obj.getClass() != this.getClass()) return false;
        Task task = (Task) obj;
        return this.dueDate.equals(task.dueDate) && this.description.equals(task.description);
    }

    /**
     * This function produces a unique hash code to this object's attributes
     *
     * @return a unique hash code to this object's attributes
     */
    @Override
    public int hashCode() {
        return toString().hashCode();
    }
}

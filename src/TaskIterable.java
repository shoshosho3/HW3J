import java.util.Date;

/**
 * This interface represents an iterable task
 */
public interface TaskIterable extends Iterable<Task> {

    /**
     * This function sets a maximum date of elements given in scan
     *
     * @param date maximum date of elements given in scan
     */
    void setScanningDueDate(Date date);
}

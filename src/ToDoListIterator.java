import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

/**
 * This class represents am iterator of a ToDoList
 */
public class ToDoListIterator implements Iterator<Task> {

    private final ArrayList<Task> sortedAr = new ArrayList<>(); // elements in to-do list
    private int index; // index of element in list to be iterated

    /**
     * Constructor of iterator
     * Sorts the list according to date
     *
     * @param toDoList     ToDoList being iterated
     * @param scanningDate max scanning date given in iteration(if null doesn't make a difference)
     */
    public ToDoListIterator(ToDoList toDoList, Date scanningDate) {
        for (Task task : toDoList.getTasks()) {
            if (scanningDate != null && scanningDate.before(task.getDueDate())) continue;
            int i = 0;
            while (i < sortedAr.size() && sortedAr.get(i).getDueDate().before(task.getDueDate())) i++;
            sortedAr.add(i, task);
        }
        index = 0;
    }

    /**
     * This function checks if index in queue is in bounds
     *
     * @return true if index in queue is in bounds false otherwise
     */
    @Override
    public boolean hasNext() {
        return index < sortedAr.size();
    }

    /**
     * This function returns next element in list and
     *
     * @return next element in list
     */
    @Override
    public Task next() {
        return sortedAr.get(index++);
    }
}

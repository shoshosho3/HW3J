import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

/**
 * This class represent a to-do list
 */
public class ToDoList implements Cloneable, TaskIterable {

    private ArrayList<Task> tasks = new ArrayList<>(); // elements in to-do list
    private Date scanningDate = null; // maximum scanning date

    /**
     * getter for tasks attribute
     *
     * @return tasks attribute(ArrayList<Task>)
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * This function adds a task to to-do list(tasks)
     *
     * @param task Task to be added to to-do list
     */
    public void addTask(Task task) {
        if (hasTaskDescription(task)) throw new TaskAlreadyExistsException();
        tasks.add(task);
    }

    /**
     * This function checks if to-do list contains a task with description of given task
     *
     * @param task task being tested
     * @return true if to-do list contains a task with description of given task, false otherwise
     */
    private boolean hasTaskDescription(Task task) {
        for (Task taskIn : tasks) {
            if (taskIn.getDescription().equals(task.getDescription())) return true;
        }
        return false;
    }

    /**
     * This function checks if to-do list contains a task that equals given task
     *
     * @param task task being tested
     * @return true if to-do list contains a task that equals given task, false otherwise
     */
    private boolean hasTask(Task task) {
        for (Task taskIn : tasks) {
            if (taskIn.equals(task)) return true;
        }
        return false;
    }

    /**
     * This function produces a String representing this to do list
     *
     * @return a String representing this to do list
     */
    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("[");
        for (Task task : tasks) {
            stringBuilder.append("(");
            stringBuilder.append(task.toString());
            stringBuilder.append("), ");
        }
        stringBuilder.delete(stringBuilder.length() - 2, stringBuilder.length());
        stringBuilder.append("]");
        return stringBuilder.toString();
    }

    /**
     * This function produces a deep cloned ToDoList of this ToDoList
     *
     * @return a deep cloned ToDoList of this ToDoList
     */
    @Override
    protected ToDoList clone() {
        try {
            ToDoList newList = (ToDoList) super.clone();
            ArrayList<Task> newTasks = new ArrayList<>();
            for (Task task : tasks) {
                newTasks.add(task.clone());
            }
            newList.tasks = newTasks;
            return newList;
        } catch (CloneNotSupportedException exception) {
            return null;
        }
    }

    /**
     * This function checks if given object equals to this ToDoList
     *
     * @param obj any object that we want to test if equals to this ToDoList
     * @return true if obj equals to this ToDoList false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null) return false;
        if (obj.getClass() != getClass()) return false;
        ToDoList toDoList = (ToDoList) obj;
        if (toDoList.tasks.size() != this.tasks.size()) return false;
        for (Task task : tasks) {
            if (!toDoList.hasTask(task)) return false;
        }
        return true;
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

    /**
     * This function sets a maximum date of elements given in scan
     *
     * @param date maximum date of elements given in scan
     */
    @Override
    public void setScanningDueDate(Date date) {
        scanningDate = date;
    }

    /**
     * This function returns iterator(enables foreach)
     *
     * @return iterator of this object
     */
    @Override
    public Iterator<Task> iterator() {
        return new ToDoListIterator(this, scanningDate);
    }
}

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

public class ToDoList implements Cloneable, TaskIterable {

    private ArrayList<Task> tasks = new ArrayList<>();
    private Date scanningDate = null;

    public ToDoList() {
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public void addTask(Task task) {
        if (hasTask(task)) throw new TaskAlreadyExistsException();
        if (scanningDate != null && task.getDueDate().before(scanningDate))
            task.getDueDate().setTime(scanningDate.getTime());
        tasks.add(task);
    }

    private boolean hasTask(Task task) {
        for (Task taskIn : tasks) {
            if (taskIn.equals(task)) return true;
        }
        return false;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("[");
        for (Task task : tasks) {
            stringBuilder.append(task.toString());
            stringBuilder.append(", ");
        }
        stringBuilder.delete(stringBuilder.length() - 2, stringBuilder.length());
        stringBuilder.append("]");
        return stringBuilder.toString();
    }

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

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null) return false;
        if (obj.getClass() != getClass()) return false;
        ToDoList toDoList = (ToDoList) obj;
        if (toDoList.tasks.size() != this.tasks.size()) return false;
        for (int i = 0; i < tasks.size(); i++) {
            if (!toDoList.tasks.get(i).equals(this.tasks.get(i))) return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        return toString().hashCode();
    }

    @Override
    public void setScanningDueDate(Date date) {
        scanningDate = date;
    }

    @Override
    public Iterator<Task> iterator() {
        return new ToDoListIterator(this);
    }
}

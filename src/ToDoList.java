import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

public class ToDoList implements Cloneable, TaskIterable{

    private final ArrayList<Task> tasks = new ArrayList<>();

    public ToDoList() {
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public void addTask(Task task) throws TaskAlreadyExistsException {
        if(hasTask(task)) throw new TaskAlreadyExistsException();
        tasks.add(task);
    }

    private boolean hasTask(Task task) {
        for(Task taskIn: tasks) {
            if (taskIn.equals(task)) return true;
        }
        return false;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("[");
        for(Task task: tasks) {
            stringBuilder.append(task.toString());
        }
        stringBuilder.append("]");
        return stringBuilder.toString();
    }

    @Override
    protected ToDoList clone() throws CloneNotSupportedException {
        ToDoList newList = (ToDoList) super.clone();
        ArrayList<Task> arrayList = newList.getTasks();
        for(int i = 0; i < tasks.size(); i++) {
            arrayList.set(i, tasks.get(i).clone());
        }
        return newList;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == this) return true;
        if(obj == null) return false;
        if(obj.getClass() != getClass()) return false;
        ToDoList toDoList = (ToDoList) obj;
        if(toDoList.tasks.size() != this.tasks.size()) return false;
        for(int i = 0; i < tasks.size(); i++) {
            if(!toDoList.tasks.get(i).equals(this.tasks.get(i))) return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        return toString().hashCode();
    }

    @Override
    public void setScanningDueDate(Date date) {

    }

    @Override
    public Iterator<Task> iterator() {
        return new ToDoListIterator(this);
    }
}

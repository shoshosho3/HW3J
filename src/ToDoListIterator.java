import java.util.Iterator;

public class ToDoListIterator implements Iterator<Task>{

    private final ToDoList toDoList;
    private int index;

    public ToDoListIterator(ToDoList toDoList) {
        this.toDoList = toDoList;
        index = 0;
    }

    @Override
    public boolean hasNext() {
        return index < toDoList.getTasks().size() - 1;
    }

    @Override
    public Task next() {
        return toDoList.getTasks().get(index++);
    }
}

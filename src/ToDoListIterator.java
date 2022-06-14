import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.zip.DataFormatException;

public class ToDoListIterator implements Iterator<Task>{

    private final ArrayList<Task> sortedAr = new ArrayList<>();
    private int index;

    public ToDoListIterator(ToDoList toDoList, Date scanningDate) {
        for(Task task : toDoList.getTasks()) {
            if(scanningDate != null && scanningDate.before(task.getDueDate())) continue;
            int i = 0;
            while(i < sortedAr.size() && sortedAr.get(i).getDueDate().before(task.getDueDate())) i++;
            sortedAr.add(i, task);
        }
        index = 0;
    }

    @Override
    public boolean hasNext() {
        return index < sortedAr.size();
    }

    @Override
    public Task next() {
        return sortedAr.get(index++);
    }
}

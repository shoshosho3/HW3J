import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Task implements Cloneable {

    private final String description;
    private Date dueDate;

    public Task(String description, Date dueDate) {
        this.description = description;
        this.dueDate = dueDate;
    }

    public Date getDueDate() {
        return dueDate;
    }

    void setDueDate(Date dueDate) {
        this.dueDate.setTime(dueDate.getTime());
    }

    @Override
    public String toString() {
        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        String strDate = dateFormat.format(dueDate);
        return "(" + description + ", " + strDate + ")";
    }

    @Override
    protected Task clone(){
        try {
            Task task = (Task) super.clone();
            task.dueDate = new Date(getDueDate().getTime());
            return task;
        } catch (CloneNotSupportedException exception) {
            return null;
        }
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == this) return true;
        if (obj == null) return false;
        if (obj.getClass() != this.getClass()) return false;
        Task task = (Task) obj;
        return this.dueDate.equals(task.dueDate) && this.description.equals(task.description);
    }

    @Override
    public int hashCode() {
        return toString().hashCode();
    }
}

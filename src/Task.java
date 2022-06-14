import java.util.Date;

public class Task implements Cloneable {

    private final String description;
    private Date dueDate;

    public Task(String description, Date dueDate) {
        this.description = description;
        this.dueDate = dueDate;
    }

    void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    @Override
    public String toString() {
        return "(" + description + ", " + dueDate.getDay() + '.' + dueDate.getMonth() + '.' + dueDate.getYear() + ")";
    }

    @Override
    protected Task clone() {
        return new Task(description, (Date) dueDate.clone());
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (obj.getClass() != this.getClass()) return false;
        Task task = (Task) obj;
        return this.dueDate.equals(task.dueDate) && this.description.equals(task.description);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}

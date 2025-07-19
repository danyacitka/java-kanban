import java.util.Objects;

public class Task {
    String name;
    String description;
    String id;
    Status status;

    public Task (String name, String description){
        this.name = name;
        this.description = description;
        status = Status.NEW;
        id = String.valueOf(this.hashCode());

    }
    public String getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return id == task.id && Objects.equals(name, task.name) && Objects.equals(description, task.description) && status == task.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, description, id, status);
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}

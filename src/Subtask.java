public class Subtask extends Task {
private Integer epicId;


    public Subtask(String name, String description, Status status, Integer epicId) {
        super(name, description, status);
        this.epicId = epicId; //добавлен атрибут epicId инициализирующийся в конструкторе
    }

    public Integer getEpicId() {
        return epicId;
    }

}

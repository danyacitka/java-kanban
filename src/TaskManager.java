import java.util.HashMap;
import java.util.Scanner;

public class TaskManager {
    public static Scanner scanner = new Scanner(System.in);
    HashMap<String, Task> tasks = new HashMap();
    HashMap<String, Epic> epics = new HashMap();

    public void getAllTasksList(String type, String id) {
        switch (type) {
            case "1":
                for (Task task : tasks.values()) {
                    System.out.println(task.name + " " + task.id);
                }
                return;
            case "2":
                for (Epic epic : epics.values()) {
                    System.out.println(epic.name + " " + epic.id);

                }
                return;
            case "3":
                if (!(epics.containsKey(id))){
                    System.out.println("Введен неправильный ID");
                    return;
                } else {
                    Epic epic = epics.get(id);
                    System.out.println(epic.name + ":");
                    for (Subtask subtask : epic.subTasks.values()) {
                        System.out.println("    " + subtask.name + " " + subtask.id);
                    }
                    return;
                }
            default:
                System.out.println("Введен неверный тип задачи");
        }
    }

    public void deleteAllTasks(String type, String id) {
        switch (type) {
            case "1":
                tasks.clear();
                return;
            case "2":
                epics.clear();
                return;
            case "3":
                if (!epics.containsKey(id)){
                    System.out.println("Введен неправильный ID");
                    return;
                } else {
                    Epic epic = epics.get(id);
                    epic.subTasks.clear();
                    return;
                }
            default:
                System.out.println("Введен неверный тип задачи");
        }
    }


    public Task getTask(String type, String id,String subId) {
        switch (type) {
            case "1":
                if (!tasks.containsKey(id)){
                    System.out.println("Введен неправильный ID");
                    return null;
                } else {
                    Task task = tasks.get(id);
                    System.out.println(task.name + ": " + task.description + " " + task.id);
                    return task;
                }
            case "2": {
                if (!tasks.containsKey(id)) {
                    System.out.println("Введен неправильный ID");
                    return null;
                } else {
                    Epic epic = epics.get(id);
                    System.out.println(epic.name + ": " + epic.description + " " + epic.id);
                    return epic;
                }
            }
            case "3": { //Скобки сделаны для того, чтобы создать отдельную область видимости, чтобы компилятор не жаловался на создание переменной epic (case не имеет областей видимости и он воспринял создание epic как повторное)
                if (!epics.containsKey(id)) {
                    System.out.println("Введен неправильный ID сверхзадачи");
                    return null;
                } else if (!epics.get(id).subTasks.containsKey(subId)) {
                    System.out.println("Введен неправильный ID подзадачи");
                    return null;
                } else {
                    Epic epic = epics.get(id);
                    Subtask subtask = epic.subTasks.get(subId);
                    System.out.println(subtask.name + ": " + subtask.description + " " + subtask.id);
                    return subtask;
                }
            }
            default:
                System.out.println("Введен неверный тип задачи");
                return null;
        }
    }

    public void createTask(String type, String name, String description, String id) {

        switch (type) {
            case "1":
                Task task = new Task(name, description);
                tasks.put(task.id, task);
                System.out.println("Задача " + task.name +" добавлена");
                return;
            case "2":
                Epic epic = new Epic(name, description);
                epics.put(epic.id, epic);
                System.out.println("Задача " + epic.name +" добавлена");
                return;
            case "3":
                if (!epics.containsKey(id)){
                    System.out.println("Введен неправильный ID");
                    return;
                } else {
                    Subtask subtask = new Subtask(name, description);
                    epics.get(id).subTasks.put(subtask.id, subtask);
                    System.out.println("Задача " + subtask.name + " добавлена");
                    return;
                }
            default:
                System.out.println("Введен неверный тип задачи");
        }
    }

    public void updateTask(String type, String name, String description, String id, String subId) {
        switch (type) {
            case "1":
                if (!tasks.containsKey(id)) {
                    System.out.println("Введен неправильный ID");
                    return;
                } else {
                    Task task = tasks.get(id);
                    task.name = name;
                    task.description = description;
                    tasks.put(id, task);
                    System.out.println("Задача \"" + task.name + "\" обновлена");
                    return;
                }
            case "2":
                if (!epics.containsKey(id)) {
                    System.out.println("Введен неправильный ID");
                    return;
                } else {
                    Epic epic = epics.get(id);
                    epic.name = name;
                    epic.description = description;
                    epics.put(id, epic);
                    System.out.println("Сверхзадача \"" + epic.name + "\" обновлена");
                    return;
                }
            case "3":
                if (!epics.containsKey(id)) {
                    System.out.println("Введен неправильный ID сверхзадачи");
                    return;
                } else if (!epics.get(id).subTasks.containsKey(subId)){
                    System.out.println("Введен неправильный ID подзадачи");
                    return;
                } else {
                    Epic epic = epics.get(id);
                    Subtask subtask = epic.subTasks.get(subId);
                    subtask.name = name;
                    subtask.description = description;
                    epic.subTasks.put(subId, subtask);
                    epics.put(id, epic);
                    System.out.println("Подзадача \"" + subtask.name + "\" обновлена");
                    return;
                }
            default:
                System.out.println("Введен неверный тип задачи");
        }
    }


    public void deleteTaskByID(String type, String id, String subId) {
        switch (type) {
            case "1":
                if (!tasks.containsKey(id)) {
                    System.out.println("Введен неправильный ID");
                    return;
                } else {
                    tasks.remove(id);
                } return;
            case "2":
                if (!epics.containsKey(id)) {
                    System.out.println("Введен неправильный ID");
                    return;
                } else {
                    epics.remove(id);
                } return;
            case "3":
                if (!epics.containsKey(id)) {
                    System.out.println("Введен неправильный ID сверхзадачи");
                    return;
                } else if (!epics.get(id).subTasks.containsKey(subId)){
                    System.out.println("Введен неправильный ID подзадачи");
                    return;
                } else {
                    epics.get(id).subTasks.remove(subId);
                    return;
                }
            default:
                System.out.println("Введен неверный тип задачи");
        }
    }


    public void getListOfEpic(String id) {
        if (!epics.containsKey(id)) {
            System.out.println("Введен неправильный ID");
            return;
        } else {
            Epic epic = epics.get(id);
            System.out.println(epic.name + ":");
            for (Subtask subtask : epic.subTasks.values()) {
                System.out.println("    " + subtask.name);
            }
        }
    }
    public void changeStatus(String type,String status,String id,String subId) {
        Status newStatus;
        switch (status) {
            case "1":
                newStatus = Status.NEW;
                break;
            case "2":
                newStatus = Status.IN_PROGRESS;
                break;
            case "3":
                newStatus = Status.DONE;
                break;
            default:
                System.out.println("Введен неправильный тип статуса");
                return;
        }
        switch (type) {
            case "1":
                if (!tasks.containsKey(id)) {
                    System.out.println("Введен неправильный ID");
                    return;
                } else {
                    Task task = tasks.get(id);
                    task.status = newStatus;
                    tasks.put(task.id, task);
                }
            case "2":
                System.out.println("Статус сверхзадачи меняется только от подзадач");
                return;
            case "3":
                if (!epics.containsKey(id)) {
                    System.out.println("Введен неправильный ID сверхзадачи");
                    return;
                } else if (epics.get(id).subTasks.isEmpty()) {
                    epics.get(id).status = Status.NEW; //Если список подзача пуст, то статус эпика - новый
                    return;
                } else if (!epics.get(id).subTasks.containsKey(subId)) {
                    System.out.println("Введен неправильный ID подзадачи");
                    return;
                } else {
                    boolean allNew = true;
                    boolean allDone = true; //создание переменных для проверки статуса эпика
                    Epic epic = epics.get(id);
                    Subtask subtask = epic.subTasks.get(subId);
                    subtask.status = newStatus;
                    epic.subTasks.put(subtask.id, subtask);
                    for (Subtask st : epics.get(id).subTasks.values()) {
                        if (st.status != Status.NEW)
                            allNew = false;
                        if (st.status != Status.DONE)
                            allDone = false;
                    }

                    if (allNew) {
                        epic.status = Status.NEW;
                    } else if (allDone) {
                        epic.status = Status.DONE;
                    } else {
                        epic.status = Status.IN_PROGRESS;
                    }
                    epics.put(epic.id, epic);
                }
            default:
                System.out.println("Введен неверный тип задачи");
                return;
        }
    }



    public void createObjectsForTest () { //метод чтобы сразу создать обьекты для теста программы
        Task task1 = new Task("task1", "firsttask");
        Task task2 = new Task("task2", "secondtask");
        Epic epic1 = new Epic("epic1", "firstepic");
        Subtask subtask1 = new Subtask("subtask1", "firstsubtask");
        Subtask subtask2 = new Subtask("subtask2", "secondsubtask");
        epic1.subTasks.put(subtask1.id, subtask1);
        epic1.subTasks.put(subtask2.id, subtask2);
        Epic epic2 = new Epic("epic2", "secondepic");
        Subtask subtask3 = new Subtask("subtask3", "thirdsubtask");
        epic2.subTasks.put(subtask3.id, subtask3);
        tasks.put(task1.id, task1);
        tasks.put(task2.id, task2);
        epics.put(epic1.id, epic1);
        epics.put(epic2.id, epic2);
        System.out.println("Задачи созданы");
    }
}




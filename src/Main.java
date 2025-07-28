import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    static TaskManager tm = new TaskManager();
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Task> taskList = new ArrayList<>();
        ArrayList<Epic> epicList = new ArrayList<>();
        ArrayList<Subtask> subtaskList = new ArrayList<>();
        String type;
        String id;
        String description;
        String name;
        String subId;
        Task task;
        Epic epic;
        Subtask subtask;
        String chooseStatus;
        Status status;
        Integer epicId;
        while (true){
            printMenu();
            String command = scanner.next();
            scanner.nextLine();
            switch (command){
                case "1": //Список по типу задач
                    System.out.println("Введите тип задачи для печати всех задач:\n1 - Задача\n2 - Сверхзадача\n" +
                            "3 - Подзадачи");
                    type = scanner.nextLine();
                    if (type.equals("1")){
                        taskList = tm.getAllTasksList();
                    } else if (type.equals("2")){
                        epicList = tm.getAllEpicsList();
                    } else if (type.equals("3")) {
                       subtaskList = tm.getAllSubtasksList();
                    } else {
                        System.out.println("Введен неправильный тип задачи");
                    }
                    break;



                case "2": //Удаление по типу задачи
                    System.out.println("Введите тип задачи для удаления всех задач:\n1 - Задача\n2 - Сверхзадача\n" +
                            "3 - Сверхзадача с подзадачами");
                    type = scanner.nextLine();
                    if (type.equals("3")){
                        tm.deleteAllSubtasks();
                    } else if (type.equals("1")) {
                        tm.deleteAllTasks();
                    } else if (type.equals("2")){
                        tm.deleteAllEpics();
                    } else {
                        System.out.println("Введен неправильный тип задачи");
                    } break;



                case "3": //Получение по идентификатору
                    System.out.println("Введите тип задачи для получения:\n1 - Задача\n2 - Сверхзадача\n" +
                            "3 - Сверхзадача с подзадачами");
                    type = scanner.nextLine();
                    if (type.equals("3")){
                        System.out.println("Введите ID подзадачи");
                        id = scanner.nextLine();
                        subtask = tm.getSubtask(Integer.valueOf(id));
                    } else if (type.equals("1")) {
                        System.out.println("Введите ID задачи");
                        id = scanner.nextLine();
                        task = tm.getTask(Integer.valueOf(id));
                    } else if (type.equals("2")) {
                        System.out.println("Введите ID сверхзадачи");
                        id = scanner.nextLine();
                        epic = tm.getEpic(Integer.valueOf(id));
                    } else {
                        System.out.println("Введен неправильный тип задачи");
                    }
                    break;



                case "4": //Создание задачи
                    System.out.println("Введите тип задачи которую хотите создать:\n" +
                            "1 - Задача\n2 - Сверхзадача\n3 - Подзадача");
                    type = scanner.nextLine();
                    System.out.println("Введите имя задачи");
                    name = scanner.nextLine();
                    System.out.println("Введите описание задачи");
                    description= scanner.nextLine();
                    if (type.equals("3")) {
                        System.out.println("введите id сверхзадачи");
                        epicId = Integer.valueOf(scanner.nextLine());
                       subtask = new Subtask(name, description, Status.NEW, epicId);
                        tm.createSubtask(subtask);
                    } else if (type.equals("1")) {
                        task = new Task(name, description, Status.NEW);
                        tm.createTask(task);
                    } else if (type.equals("2")) {
                        epic = new Epic(name, description);
                        tm.createEpic(epic);
                    } else {
                        System.out.println("Введен неправильный тип задачи");
                    }
                    break;



                case "5": //Обновление задачи
                    System.out.println("Введите тип задачи которую хотите создать:\n" +
                            "1 - Задача\n2 - Сверхзадача\n3 - Подзадача");
                    type = scanner.nextLine();
                    System.out.println("Введите id задачи");
                    id = scanner.nextLine();
                    System.out.println("Введите имя задачи");
                    name = scanner.nextLine();
                    System.out.println("Введите описание задачи");
                    description= scanner.nextLine();
                    System.out.println("Выберите статус задачи: 1 - новая, 2 - в процессе, 3 - закончена");
                    chooseStatus = scanner.nextLine();
                    switch (chooseStatus) {
                        case "1":
                            status = Status.NEW;
                            break;
                        case "2":
                            status = Status.IN_PROGRESS;
                            break;
                        case "3":
                            status = Status.DONE;
                            break;
                        default:
                            System.out.println("Введен неправильный статус");
                            status = Status.IN_PROGRESS;
                    }
                    if (type.equals("3")) {
                        System.out.println("введите id сверхзадачи");
                        epicId = Integer.valueOf(scanner.nextLine());
                        subtask = new Subtask(name, description, status, epicId);
                        subtask.setId(Integer.valueOf(id));
                        tm.updateSubtask(subtask);
                        System.out.println("подзадача обновлена");
                    } else if (type.equals("1")) {
                        task = new Task(name, description, status);
                        task.setId(Integer.valueOf(id));
                        tm.updateTask(task);
                        System.out.println("задача обновлена");
                    } else if (type.equals("2")) {
                        epic = new Epic(name, description);
                        epic.setId(Integer.valueOf(id));
                        tm.updateEpic(epic);
                        System.out.println("сверхзадача обновлена");
                    } else {
                        System.out.println("Введен неправильный тип задачи");
                    }
                    break;



                case "6": //Удаление по ID
                    System.out.println("Введите тип задачи которую хотите удалить:\n" +
                            "1 - Задача\n2 - Сверхзадача\n3 - Подзадача");
                    type = scanner.nextLine();
                    System.out.println("Введите id");
                    id = scanner.nextLine();
                    if (type.equals("3")) {
                        tm.deleteSubaskByID(Integer.valueOf(id));
                    } else if (type.equals("1")) {
                        tm.deleteTaskByID(Integer.valueOf(id));
                    } else if (type.equals("2")) {
                        tm.deleteEpicByID(Integer.valueOf(id));
                    } else {
                        System.out.println("Введен неправильный тип задачи");
                    }
                    break;



                case "7": //Получение списка подзадач эпика
                    System.out.println("Введите id сверхзадачи для получения подзадач");
                    id = scanner.nextLine();
                    tm.getListOfSubtasksOfEpic(Integer.valueOf(id));
                    break;



                case "8": //Создание обьектов для проверки программы
                    createObjectsForTest();
                    break;



                case "9": //Выход
                    System.out.println("До свидания!");
                    return;
                default:
                    System.out.println("Введена неверная команда");
                    break;
            }
        }


    }
    public static void printMenu(){
        System.out.println("Выберите команду:\n" +
                "1 - список типов задач\n" +
                "2 - удаление всех задач\n" +
                "3 - получение по идентификатору\n" +
                "4 - создание задачи\n" +
                "5 - обновление задачи\n" +
                "6 - удаление по идентификатору\n" +
                "7 - получение подзадач эпика\n" +
                "8 - обьекты для проверки\n"
                + "9 - выход");

    }
    public static void createObjectsForTest () { //метод чтобы сразу создать обьекты для теста программы
        Task task1 = new Task("task1", "firsttask", Status.NEW);
        Task task2 = new Task("task2", "secondtask", Status.DONE);
        Epic epic1 = new Epic("epic1", "firstepic");
        tm.createEpic(epic1);
        Subtask subtask1 = new Subtask("subtask1", "firstsubtask",Status.IN_PROGRESS, epic1.getId());
        Subtask subtask2 = new Subtask("subtask2", "secondsubtask",Status.IN_PROGRESS, epic1.getId());
        tm.createSubtask(subtask1);
        tm.createSubtask(subtask2);
        Epic epic2 = new Epic("epic2", "secondepic");
        tm.createEpic(epic2);
        Subtask subtask3 = new Subtask("subtask3", "thirdsubtask", Status.NEW, epic2.getId());
        tm.createSubtask(subtask3);
        tm.createTask(task1);
        tm.createTask(task2);
        System.out.println("Задачи созданы");
    }

}

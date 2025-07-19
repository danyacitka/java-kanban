import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        TaskManager taskManager = new TaskManager();
        Scanner scanner = new Scanner(System.in);
        String type;
        String id;
        String description;
        String name;
        String subId;
        Task task;
        Epic epic;
        Subtask subtask;
        String status;
        while (true){
            printMenu();
            String command = scanner.next();
            scanner.nextLine();
            switch (command){
                case "1": //Список по типу задач
                    System.out.println("Введите тип задачи для печати всех задач:\n1 - Задача\n2 - Сверхзадача\n" +
                            "3 - Сверхзадача с подзадачами");
                    type = scanner.nextLine();
                    if (type.equals("3")){
                        System.out.println("Введите ID сверхзадачи");
                        id = scanner.nextLine();
                        taskManager.getAllTasksList(type, id);
                    } else if (type.equals("1") || type.equals("2")){
                        taskManager.getAllTasksList(type, "");
                    } else {
                        System.out.println("Введен неправильный тип задачи");
                    }
                    break;

                case "2": //Смена статуса
                    System.out.println("Введите тип задачи изменения статуса задач:\n1 - Задача\n2 - Сверхзадача\n" +
                            "3 - Сверхзадача с подзадачами");
                    type = scanner.nextLine();
                    if (type.equals("3")){
                        System.out.println("Введите ID сверхзадачи");
                        id = scanner.nextLine();
                        System.out.println("Введите ID подзадачи");
                        subId = scanner.nextLine();
                        System.out.println("Введите новый статус задачи:\n1 - Новая задача\n" +
                                "2 - Задача в исполнении\n3 - Задача выполнена");
                        status = scanner.nextLine();
                        taskManager.changeStatus(type, status, id, subId);
                    } else if (type.equals("1")) {
                        System.out.println("Введите ID задачи");
                        id = scanner.nextLine();
                        System.out.println("Введите новый статус задачи:\n1 - Новая задача\n" +
                                "2 - Задача в исполнении\n3 - Задача выполнена");
                        status = scanner.nextLine();
                        taskManager.changeStatus(type, status, id, "");
                    } else if (type.equals("2")){
                        System.out.println("Статус сверхзадачи меняется только от подзадач");
                    } else {
                        System.out.println("Введен неправильный тип задачи");
                    }
                    break;
                case "3": //Удаление по типу задачи
                    System.out.println("Введите тип задачи для удаления всех задач:\n1 - Задача\n2 - Сверхзадача\n" +
                            "3 - Сверхзадача с подзадачами");
                    type = scanner.nextLine();
                    if (type.equals("3")){
                        System.out.println("Введите ID сверхзадачи");
                        id = scanner.nextLine();
                        taskManager.deleteAllTasks(type, id);
                    } else if (type.equals("1") || type.equals("2")) {
                        taskManager.deleteAllTasks(type, "");
                    } else {
                        System.out.println("Введен неправильный тип задачи");
                    } break;
                case "4": //Получение по идентификатору
                    System.out.println("Введите тип задачи для получения:\n1 - Задача\n2 - Сверхзадача\n" +
                            "3 - Сверхзадача с подзадачами");
                    type = scanner.nextLine();
                    if (type.equals("3")){
                        System.out.println("Введите ID сверхзадачи");
                        id = scanner.nextLine();
                        System.out.println("Введите ID подзадачи");
                        subId = scanner.nextLine();
                        subtask = (Subtask) taskManager.getTask(type, id, subId); //так как метод getTask возвращает тип Task, нужно конвертировать значение в нужное
                    } else if (type.equals("1") || type.equals("2")) {
                        System.out.println("Введите ID задачи");
                        id = scanner.nextLine();
                        if (type.equals("1")) {
                            task = taskManager.getTask(type, id, "");
                        } else {
                            epic = (Epic) taskManager.getTask(type, id, "");
                        }
                    } else {
                        System.out.println("Введен неправильный тип задачи");
                    }
                    break;

                case "5": //Создание задачи
                    System.out.println("Введите тип задачи которую хотите создать:\n" +
                            "1 - Задача\n2 - Сверхзадача\n3 - Подзадача");
                    type = scanner.nextLine();
                    if (!(type.equals("1") || type.equals("2") || type.equals("3"))){
                        System.out.println("Введен неправильный тип задачи");
                    } else {
                        System.out.println("Введите название задачи");
                        name = scanner.nextLine();
                        System.out.println("Введите описание задачи");
                        description = scanner.nextLine();
                        if (type.equals("1") || type.equals("2")) {
                            taskManager.createTask(type, name, description, "");
                        } else if (type.equals("3")){
                            System.out.println("Введите id сверхзадачи в которую хотите добавить подзадачу");
                            id = scanner.nextLine();
                            taskManager.createTask(type, name, description, id);
                        }
                    } break;
                case "6": //Обновление задачи
                    System.out.println("Введите тип задачи которую хотите обновить:\n" +
                            "1 - Задача\n2 - Сверхзадача\n3 - Подзадача");
                    type = scanner.nextLine();
                    if (!(type.equals("1") || type.equals("2") || type.equals("3"))){
                        System.out.println("Введен неправильный тип задачи");
                        break;
                    } else {
                        if (type.equals("1") || type.equals("2")) {
                            System.out.println("Введите id задачи");
                            id = scanner.nextLine();
                            System.out.println("Введите новое название задачи");
                            name = scanner.nextLine();
                            System.out.println("Введите новое описание задачи");
                            description = scanner.nextLine();
                            taskManager.updateTask(type, name, description, id, "");
                        } else if (type.equals("3")) {
                            System.out.println("Введите id сверхзадачи в которой хотите обновить подзадачу");
                            id = scanner.nextLine();
                            System.out.println("Введите id подзадачи");
                            subId = scanner.nextLine();
                            System.out.println("Введите новое название задачи");
                            name = scanner.nextLine();
                            System.out.println("Введите новое описание задачи");
                            description = scanner.nextLine();
                            taskManager.updateTask(type, name, description, id, subId);
                        }
                    } break;
                case "7": //Удаление по ID
                    System.out.println("Введите тип задачи которую хотите удалить:\n" +
                            "1 - Задача\n2 - Сверхзадача\n3 - Подзадача");
                    type = scanner.nextLine();
                    if (!(type.equals("1") || type.equals("2") || type.equals("3"))){
                        System.out.println("Введен неправильный тип задачи");
                        return;
                    } else {
                        if (type.equals("1") || type.equals("2")) {
                            System.out.println("Введите id задачи");
                            id = scanner.nextLine();
                            taskManager.deleteTaskByID(type, id, "");
                        } else if (type.equals("3")) {
                            System.out.println("Введите id сверхзадачи");
                            id = scanner.nextLine();
                            System.out.println("Введите id подзадачи");
                            subId = scanner.nextLine();
                            taskManager.deleteTaskByID(type, id, subId);
                        }
                    } break;
                case "8": //Получение списка подзадач эпика
                    System.out.println("Введите id сверхзадачи для получения подзадач");
                    id = scanner.nextLine();
                    taskManager.getListOfEpic(id);
                    break;
                case "9": //Создание обьектов для проверки программы
                    taskManager.createObjectsForTest();
                    break;
                case "10": //Выход
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
                "2 - сменить статус\n" +
                "3 - удаление всех задач\n" +
                "4 - получение по идентификатору\n" +
                "5 - создание задачи\n" +
                "6 - обновление задачи\n" +
                "7 - удаление по идентификатору\n" +
                "8 - получение подзадач эпика\n" +
                "9 - обьекты для проверки\n"
                + "10 - выход");

    }
}

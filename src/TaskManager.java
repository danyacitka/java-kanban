import java.util.ArrayList;
import java.util.HashMap;

public class TaskManager {
    private HashMap<Integer, Task> tasks = new HashMap();
    private HashMap<Integer, Epic> epics = new HashMap();
    private HashMap<Integer, Subtask> subtasks = new HashMap();

    public ArrayList<Task> getAllTasksList() { //Метод теперь возвращает список обьектов, а не просто печатает в консоль
        ArrayList<Task> tasksList = new ArrayList<>();
        for (Task task : tasks.values()) {
            System.out.println(task.name + " " + task.id);
            tasksList.add(task);
        }
        return tasksList;
    }

    public ArrayList<Epic> getAllEpicsList() {
        ArrayList<Epic> epicsList = new ArrayList<>();
        for (Epic epic : epics.values()) {
            System.out.println(epic.name + " " + epic.id);
            epicsList.add(epic);
        }
        return epicsList;
    }

    public ArrayList<Subtask> getAllSubtasksList() {
        ArrayList<Subtask> subtasksList = new ArrayList<>();
        for (Subtask subtask : subtasks.values()) {
            System.out.println(subtask.name + " " + subtask.id);
            subtasksList.add(subtask);
        }
        return subtasksList;
    }


    public void deleteAllTasks() {
        tasks.clear();
    }

    public void deleteAllEpics() {
        epics.clear();
    }

    public void deleteAllSubtasks() {
        subtasks.clear();
    }


    public Task getTask(Integer id) {
        if (tasks.containsKey(id)) {
            Task task = tasks.get(id);
            System.out.println(task.getName() + " " + task.getDescription());
            return task;
        } else {
            System.out.println("Неправильный id");
        }
        return null;
    }

    public Epic getEpic(Integer id) {
        if (epics.containsKey(id)) {
            Epic epic = epics.get(id);
            System.out.println(epic.getName() + " " + epic.getDescription());
            return epic;
        } else {
            System.out.println("Неправильный id");
        }
        return null;
    }

    public Subtask getSubtask(Integer id) {
        if (subtasks.containsKey(id)) {
            Subtask subtask = subtasks.get(id);
            System.out.println(subtask.getName() + " " + subtask.getDescription());
            return subtask;
        } else {
            System.out.println("Неправильный id");
        }
        return null;
    }


    public void createTask(Task task) {
        task.id = task.hashCode();
        tasks.put(task.id, task);
        System.out.println("Задача создана");
    }

    public void createEpic(Epic epic) {
        epic.id = epic.hashCode();
        epics.put(epic.id, epic);
        System.out.println("Сверхзадача создана");
    }

    public void createSubtask(Subtask subtask) {
        if (epics.containsKey(subtask.getEpicId())) {
            subtask.id = subtask.hashCode();
            epics.get(subtask.getEpicId()).addOneSubtask(subtask.id);

            subtasks.put(subtask.id, subtask);
            changeStatus(subtask.getEpicId());
            System.out.println("Подзадача создана");
        } else {
            System.out.println("ID сверхзадачи неверен");
        }
    }


    public void updateTask(Task task) {
        tasks.put(task.getId(), task);
    }

    public void updateEpic(Epic epic) {
        epics.put(epic.getId(), epic);
    }

    public void updateSubtask(Subtask subtask) {
        if (subtasks.containsKey(subtask.getEpicId())) {
            subtasks.put(subtask.getId(), subtask);
            changeStatus(subtask.getEpicId());
        } else {
            System.out.println("Неправильный id сверхзадачи");
        }
    }

    public void deleteTaskByID(Task task) {
        tasks.remove(task.getId());
    }

    public void deleteEpicByID(Epic epic) {
        epics.remove(epic.getId());
    }

    public void deleteSubaskByID(Subtask subtask) {
        epics.get(subtask.getEpicId()).deleteOneSubtask(subtask.getId());
        subtasks.remove(subtask.getId());
    }


    public ArrayList<Subtask> getListOfSubtasksOfEpic(Epic epic) {
        ArrayList<Subtask> listOfSubtasks = new ArrayList<>();
        for (Subtask subtask : subtasks.values()) {
            if (epic.getSubtasksId().contains(subtask.getId())) {
                listOfSubtasks.add(subtask);
                System.out.println(subtask.getName() + " " + subtask.getDescription());
            }
        }
        return listOfSubtasks;
    }


    private void changeStatus(Integer id) {
        boolean allNew = true;
        boolean allDone = true; //создание переменных для проверки статуса эпика
        Epic epic = epics.get(id);
        for (Integer subtaskId : epic.getSubtasksId()) {
            if (subtasks.get(subtaskId).status != Status.NEW) { //почему-то субтаскс гет выдает нулл
                allNew = false;
            }
            if (subtasks.get(subtaskId).status != Status.DONE) {
                allDone = false;
            }
        }
        if (allNew) {
            epic.status = Status.NEW;
        } else if (allDone) {
            epic.status = Status.DONE;
        } else {
            epic.status = Status.IN_PROGRESS;
        }
        epics.put(epic.getId(), epic);
    }
}





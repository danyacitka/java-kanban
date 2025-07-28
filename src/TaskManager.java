import org.w3c.dom.ls.LSOutput;

import java.util.ArrayList;
import java.util.HashMap;

public class TaskManager {
    private HashMap<Integer, Task> tasks = new HashMap();
    private HashMap<Integer, Epic> epics = new HashMap();
    private HashMap<Integer, Subtask> subtasks = new HashMap();
    private int nextId = 1;

    public ArrayList<Task> getAllTasksList() { //Метод теперь возвращает список обьектов, а не просто печатает в консоль
        ArrayList<Task> tasksList = new ArrayList<>();
        for (Task task : tasks.values()) {
            System.out.println(task.getName() + " " + task.getId());
            tasksList.add(task);
        }
        return tasksList;
    }

    public ArrayList<Epic> getAllEpicsList() {
        ArrayList<Epic> epicsList = new ArrayList<>();
        for (Epic epic : epics.values()) {
            System.out.println(epic.getName() + " " + epic.getId());
            epicsList.add(epic);
        }
        return epicsList;
    }

    public ArrayList<Subtask> getAllSubtasksList() {
        ArrayList<Subtask> subtasksList = new ArrayList<>();
        for (Subtask subtask : subtasks.values()) {
            System.out.println(subtask.getName() + " " + subtask.getId());
            subtasksList.add(subtask);
        }
        return subtasksList;
    }


    public void deleteAllTasks() {
        tasks.clear();
    }

    public void deleteAllEpics() {
        epics.clear();
        subtasks.clear();
    }

    public void deleteAllSubtasks() {
        for (Epic epic : epics.values()){
            epic.deleteAllSubtasks();
            changeStatus(epic.getId());
        }
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
        task.id = nextId++;
        tasks.put(task.getId(), task);
        System.out.println("Задача создана");
    }

    public void createEpic(Epic epic) {
        epic.id = nextId++;
        epics.put(epic.getId(), epic);
        System.out.println("Сверхзадача создана");
    }

    public void createSubtask(Subtask subtask) {
        if (epics.containsKey(subtask.getEpicId())) {
            subtask.id = nextId++;;
            epics.get(subtask.getEpicId()).addOneSubtask(subtask.getId());
            subtasks.put(subtask.getId(), subtask);
            changeStatus(subtask.getEpicId());
            System.out.println("Подзадача создана");
        } else {
            System.out.println("ID сверхзадачи неверен");
        }
    }


    public void updateTask(Task task) {
        if (tasks.containsKey(task.getId())) {
            tasks.put(task.getId(), task);
            System.out.println("Задача обновлена");
        } else {
            System.out.println("Задачи под этим id не существует");
        }
    }

    public void updateEpic(Epic epic) {
       if (epics.containsKey(epic.getId())){
           Epic savedEpic = epics.get(epic.getId());
           savedEpic.setName(epic.getName());
           savedEpic.setDescription(epic.getDescription());
           System.out.println("Сверхзадача обновлена");
       } else {
           System.out.println("Сверхзадачи под этим id не существует");
       }
    }

    public void updateSubtask(Subtask subtask) {
        if (subtasks.containsKey(subtask.getId())) {
            Subtask savedSubtask = subtasks.get(subtask.getId());
            if (!savedSubtask.getEpicId().equals(subtask.getEpicId())){
                System.out.println("Нелья сменить принадлежность к сверхзадаче");
                return;
            }
            subtasks.put(subtask.getId(), subtask);
            changeStatus(subtask.getEpicId());
            System.out.println("Подзадача обновлена");
        } else {
            System.out.println("Неправильный id подзадачи");
        }
    }

    public void deleteTaskByID(Integer id) {
        tasks.remove(id);
    }

    public void deleteEpicByID(Integer id) {
        if (epics.containsKey(id)) {
            epics.get(id).deleteAllSubtasks();
            epics.remove(id);
        }
    }

    public void deleteSubaskByID(Integer id) {
        Subtask subtask = subtasks.get(id);
        epics.get(subtask.getEpicId()).deleteOneSubtask(subtask.getId());
        subtasks.remove(subtask.getId());
        changeStatus(subtask.getEpicId());
    }


    public ArrayList<Subtask> getListOfSubtasksOfEpic(Integer id) {
        Epic epic = epics.get(id);
        ArrayList<Subtask> listOfSubtasks = new ArrayList<>();
        for (Integer subId : epic.getSubtasksId()) {
            listOfSubtasks.add(subtasks.get(subId));
            }
        return listOfSubtasks;
    }


    private void changeStatus(Integer id) {
        boolean allNew = true;
        boolean allDone = true; //создание переменных для проверки статуса эпика
        Epic epic = epics.get(id);
        for (Integer subtaskId : epic.getSubtasksId()) {
            if (subtasks.get(subtaskId).getStatus() != Status.NEW) { //почему-то субтаскс гет выдает нулл
                allNew = false;
            }
            if (subtasks.get(subtaskId).getStatus() != Status.DONE) {
                allDone = false;
            }
        }
        if (allNew) {
            epic.setStatus(Status.NEW);
        } else if (allDone) {
            epic.setStatus(Status.DONE);
        } else {
            epic.setStatus(Status.IN_PROGRESS);
        }
    }
}





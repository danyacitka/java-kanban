import java.util.ArrayList;


public class Epic extends Task{


    public ArrayList<Integer> getSubtasksId() {
        return subtasksId;
    } //нужно для получения списка id подзадач эпика в методе GetAllSubtasksOfEpic

    private ArrayList<Integer> subtasksId = new ArrayList<>(); //список id подзадач для мапы в taskmanager

    public Epic(String name, String description) {
        super(name, description, Status.NEW);

    }

    public void deleteOneSubtask(Integer id) {
        subtasksId.remove(id);
    }

    public void addOneSubtask(Integer id){
        subtasksId.add(id);
    }

    public void deleteAllSubtasks(){
        subtasksId.clear();
    }



        }


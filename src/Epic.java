import java.util.ArrayList;


public class Epic extends Task {
    private ArrayList<Integer> subtasksId = new ArrayList<>();

    public Epic(String name, String description) {
        super(name, description, Status.NEW);

    }

    public ArrayList<Integer> getSubtasksId() {
        return subtasksId;
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


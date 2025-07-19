import java.util.ArrayList;
import java.util.HashMap;

public class Epic extends Task{
    HashMap<String, Subtask> subTasks = new HashMap();

    public Epic(String name, String description) {
        super(name, description);
    }

    public void checkStatus(){
        for (Subtask subTask: subTasks.values()) {
            int count = 0;
            if (subTask.status==Status.DONE){
                count++;
            }
            if (count == subTasks.size()) {  //если все подзадачи эпика выполнены то он сам будет выполнен
                status = Status.DONE;
            }
        }
    }
}

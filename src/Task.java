import java.io.Serializable;

public class Task  implements Serializable {

    private String name;
    private boolean isCompleted;

    public Task(String name){
        this.name = name;
        this.isCompleted = false;
    }

    public String getName() {
        return name;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    //Méthode pour marquer la tâche comme terminée
    public void markAsCompleted() {
        this.isCompleted = true;
    }

    public String getStatus(){
        return isCompleted ? "Terminée" : "En cours";
    }
}

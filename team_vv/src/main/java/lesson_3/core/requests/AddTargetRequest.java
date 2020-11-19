package lesson_3.core.requests;

public class AddTargetRequest {

    private String name;
    private String description;
    private Integer deadline;


    public AddTargetRequest(String name, String description, Integer deadline){
        this.name = name;
        this.description = description;
        this.deadline = deadline;
    }

    public Integer getDeadline() {
        return deadline;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}

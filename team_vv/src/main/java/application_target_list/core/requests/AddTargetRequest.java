package application_target_list.core.requests;

import application_target_list.Target;

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

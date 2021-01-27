package java2.application_target_list.core.requests.target;

import java.math.BigInteger;

public class AddTargetRequest {

    private String name;
    private String description;
    private Long deadline;


    public AddTargetRequest(String name, String description, Long deadline){
        this.name = name;
        this.description = description;
        this.deadline = deadline;
    }

    public Long getDeadline() {
        return deadline;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}

package application_target_list.core.requests;

public class SearchTargetByNameRequest {

    private final String name;


    public SearchTargetByNameRequest(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}

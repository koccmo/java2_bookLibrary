package application_target_list.core.requests;

public class SearchTargetByDescriptionRequest {

    private String description;

    public SearchTargetByDescriptionRequest(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}

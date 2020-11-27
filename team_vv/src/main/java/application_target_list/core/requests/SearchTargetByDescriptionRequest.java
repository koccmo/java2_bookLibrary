package application_target_list.core.requests;

public class SearchTargetByDescriptionRequest {

    private final String description;

    private Paging paging;

    public SearchTargetByDescriptionRequest(String description) {
        this.description = description;
    }

    public SearchTargetByDescriptionRequest(String description, Paging paging) {
        this.description = description;
        this.paging = paging;
    }

    public Paging getPaging() {
        return paging;
    }

    public String getDescription() {
        return description;
    }
}

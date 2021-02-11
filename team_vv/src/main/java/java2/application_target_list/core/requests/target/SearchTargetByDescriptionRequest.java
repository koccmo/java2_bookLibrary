package java2.application_target_list.core.requests.target;

import java2.application_target_list.core.requests.Ordering;
import java2.application_target_list.core.requests.Paging;

public class SearchTargetByDescriptionRequest {

    private String description;
    private Paging paging;
    private Ordering ordering;

    public SearchTargetByDescriptionRequest() {
    }

    public SearchTargetByDescriptionRequest(String description) {
        this.description = description;
    }

    public SearchTargetByDescriptionRequest(String description, Ordering ordering){
        this.description = description;
        this.ordering = ordering;
    }

    public SearchTargetByDescriptionRequest(String description, Ordering ordering, Paging paging){
        this.description = description;
        this.ordering = ordering;
        this.paging = paging;
    }

    public SearchTargetByDescriptionRequest(String description, Paging paging) {
        this.description = description;
        this.paging = paging;
    }

    public Ordering getOrdering() {
        return ordering;
    }

    public Paging getPaging() {
        return paging;
    }

    public String getDescription() {
        return description;
    }

    public void setPaging(Paging paging) {
        this.paging = paging;
    }

    public void setOrdering(Ordering ordering) {
        this.ordering = ordering;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

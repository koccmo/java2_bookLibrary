package java2.application_target_list.core.requests.target;

import java2.application_target_list.core.requests.Ordering;
import java2.application_target_list.core.requests.Paging;

public class SearchTargetByNameRequest {

    private String name;
    private Paging paging;
    private Ordering ordering;

    public SearchTargetByNameRequest() {
    }

    public SearchTargetByNameRequest(String name) {
        this.name = name;
    }

    public SearchTargetByNameRequest(String name, Paging paging) {
        this.name = name;
        this.paging = paging;
    }

    public SearchTargetByNameRequest(String name, Ordering ordering) {
        this.name = name;
        this.ordering = ordering;
    }

    public SearchTargetByNameRequest(String name,Ordering ordering, Paging paging) {
        this.name = name;
        this.ordering = ordering;
        this.paging = paging;
    }

    public Ordering getOrdering() {
        return ordering;
    }

    public Paging getPaging() {
        return paging;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPaging(Paging paging) {
        this.paging = paging;
    }

    public void setOrdering(Ordering ordering) {
        this.ordering = ordering;
    }
}

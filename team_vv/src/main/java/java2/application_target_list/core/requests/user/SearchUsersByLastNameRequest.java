package java2.application_target_list.core.requests.user;

import java2.application_target_list.core.requests.Ordering;
import java2.application_target_list.core.requests.Paging;

public class SearchUsersByLastNameRequest {

    private String lastName;
    private Paging paging;
    private Ordering ordering;

    public SearchUsersByLastNameRequest() {
    }

    public SearchUsersByLastNameRequest(String lastName) {
        this.lastName = lastName;
    }

    public SearchUsersByLastNameRequest(String lastName, Paging paging) {
        this.lastName = lastName;
        this.paging = paging;
    }

    public SearchUsersByLastNameRequest(String lastName, Paging paging, Ordering ordering) {
        this.lastName = lastName;
        this.paging = paging;
        this.ordering = ordering;
    }

    public SearchUsersByLastNameRequest(String lastName, Ordering ordering) {
        this.lastName = lastName;
        this.ordering = ordering;
    }

    public String getLastName() {
        return lastName;
    }

    public Paging getPaging() {
        return paging;
    }

    public Ordering getOrdering() {
        return ordering;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setPaging(Paging paging) {
        this.paging = paging;
    }

    public void setOrdering(Ordering ordering) {
        this.ordering = ordering;
    }
}

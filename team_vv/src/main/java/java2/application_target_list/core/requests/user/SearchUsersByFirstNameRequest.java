package java2.application_target_list.core.requests.user;

import java2.application_target_list.core.requests.Ordering;
import java2.application_target_list.core.requests.Paging;

public class SearchUsersByFirstNameRequest {

    private String firstName;
    private Paging paging;
    private Ordering ordering;

    public SearchUsersByFirstNameRequest() {
    }

    public SearchUsersByFirstNameRequest(String firstName) {
        this.firstName = firstName;
    }

    public SearchUsersByFirstNameRequest(String firstName, Paging paging) {
        this.firstName = firstName;
        this.paging = paging;
    }

    public SearchUsersByFirstNameRequest(String firstName, Paging paging, Ordering ordering) {
        this.firstName = firstName;
        this.paging = paging;
        this.ordering = ordering;
    }

    public SearchUsersByFirstNameRequest(String firstName, Ordering ordering) {
        this.firstName = firstName;
        this.ordering = ordering;
    }

    public String getFirstName() {
        return firstName;
    }

    public Paging getPaging() {
        return paging;
    }

    public Ordering getOrdering() {
        return ordering;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setPaging(Paging paging) {
        this.paging = paging;
    }

    public void setOrdering(Ordering ordering) {
        this.ordering = ordering;
    }
}

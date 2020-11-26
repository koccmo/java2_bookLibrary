package internet_store.core.requests.customer;

import internet_store.core.requests.Ordering;
import internet_store.core.requests.Paging;

public class SearchCustomerRequest {

    private String name;
    private String surname;

    private Ordering ordering;
    private Paging paging;

    public SearchCustomerRequest(String name, String surname, Ordering ordering, Paging paging) {
        this.name = name;
        this.surname = surname;
        this.ordering = ordering;
        this.paging = paging;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public Ordering getOrdering(){
        return ordering;
    }

    public Paging getPaging(){
        return paging;
    }

}

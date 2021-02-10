package dental_clinic.core.requests.patient;

import dental_clinic.core.domain.OrderingDirection;
import dental_clinic.core.requests.Ordering;
import dental_clinic.core.requests.Paging;

public class SearchPatientRequest {

    private String inputForSearch;
    private Ordering ordering = new Ordering("name", OrderingDirection.ASC);
    private Paging paging = new Paging(1, 100);

    public SearchPatientRequest() { }

    public SearchPatientRequest(String inputForSearch, Ordering ordering, Paging paging) {
        this.inputForSearch = inputForSearch;
        this.ordering = ordering;
        this.paging = paging;
    }

    public String getInputForSearch() {
        return inputForSearch;
    }

    public Ordering getOrdering(){
        return ordering;
    }

    public Paging getPaging(){
        return paging;
    }

    public void setInputForSearch(String inputForSearch) {
        this.inputForSearch = inputForSearch;
    }

    public void setOrdering(Ordering ordering) {
        this.ordering = ordering;
    }

    public void setPaging(Paging paging) {
        this.paging = paging;
    }
}

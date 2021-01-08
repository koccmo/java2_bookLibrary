package dental_clinic.core.requests.patient;

import dental_clinic.core.requests.Ordering;
import dental_clinic.core.requests.Paging;

public class SearchPatientRequest {

    private String inputForSearch;
    private Ordering ordering;
    private Paging paging;

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

}
